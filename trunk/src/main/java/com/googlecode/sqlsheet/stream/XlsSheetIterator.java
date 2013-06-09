/*
 * Copyright 2012 sqlsheet.googlecode.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.googlecode.sqlsheet.stream;

import org.apache.poi.hssf.eventusermodel.*;
import org.apache.poi.hssf.eventusermodel.EventWorkbookBuilder.SheetRecordCollectingListener;
import org.apache.poi.hssf.eventusermodel.dummyrecord.LastCellOfRowDummyRecord;
import org.apache.poi.hssf.eventusermodel.dummyrecord.MissingCellDummyRecord;
import org.apache.poi.hssf.model.HSSFFormulaParser;
import org.apache.poi.hssf.record.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.NPOIFSFileSystem;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Streaming iterator over XLS files
 * Derived from:
 * http://svn.apache.org/repos/asf/poi/trunk/src/examples/src/org/apache/poi/hssf/eventusermodel/examples/XLS2CSVmra.java
 */
public class XlsSheetIterator extends AbstractXlsSheetIterator implements HSSFListener {

    NPOIFSFileSystem fileSystem;
    boolean inRequiredSheet;
    // Create a new RecordStream and use that
    RecordFactoryInputStream recordStream;
    PublicMorozoffHSSFRequest requestPublic;
    int lastRowNumber;
    int lastColumnNumber;
    /**
     * Should we output the formula, or the value it has?
     */
    boolean outputFormulaValues;
    /**
     * For parsing Formulas
     */
    SheetRecordCollectingListener workbookBuildingListener;
    HSSFWorkbook stubWorkbook;
    // Records we pick up as we postConstruct
    SSTRecord sstRecord;
    FormatTrackingHSSFListener formatListener;
    /**
     * So we known which sheet we're on
     */
    int sheetIndex;
    BoundSheetRecord[] orderedBSRs;
    ArrayList boundSheetRecords;
    // For handling formulas with string results
    int nextRow;
    int nextColumn;
    boolean outputNextStringRecord;

    public XlsSheetIterator(URL filename, String sheetName) throws SQLException {
        super(filename, sheetName);
    }

    /**
     * Initiates the processing
     * - position stream to the right sheet
     * - extracts columns
     * - extracts first row
     */
    public void postConstruct() throws SQLException {
        try {
            boundSheetRecords = new ArrayList();
            sheetIndex = -1;
            inRequiredSheet = false;
            outputFormulaValues = true;

            fileSystem = new NPOIFSFileSystem(getFileName().openStream());
            recordStream = new RecordFactoryInputStream(fileSystem.getRoot().createDocumentInputStream("Workbook"), false);
            MissingRecordAwareHSSFListener listener = new MissingRecordAwareHSSFListener(this);
            formatListener = new FormatTrackingHSSFListener(listener);
            requestPublic = new PublicMorozoffHSSFRequest();
            if (outputFormulaValues) {
                requestPublic.addListenerForAllRecords(formatListener);
            } else {
                workbookBuildingListener = new EventWorkbookBuilder.SheetRecordCollectingListener(formatListener);
                requestPublic.addListenerForAllRecords(workbookBuildingListener);
            }

            // Process each record as they come in till we get to the right sheet
            while (!inRequiredSheet) {
                Record r = recordStream.nextRecord();
                if (r == null) {
                    break;
                }
                try {
                    short userCode = requestPublic.processRecord(r);
                    if (userCode != 0) {
                        break;
                    }

                } catch (HSSFUserException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
            }
            //Flush rows counter
            setCurrentSheetRowIndex(0L);
            //Fill current row
            processNextRecords();
        } catch (IOException e) {
            throw new SQLException(e.getMessage(), e);
        }

    }

    /**
     * Process few records to get current and maybe few next rows loaded
     * into memory
     */
    protected void processNextRecords() throws SQLException {
        Long nextRowIndex = getCurrentSheetRowIndex() + 2L;
        while (inRequiredSheet && (!getCurrentSheetRowIndex().equals(nextRowIndex))) {
            Record r = recordStream.nextRecord();
            if (r == null) {
                break;
            }
            try {
                short userCode = requestPublic.processRecord(r);
                if (userCode != 0) {
                    break;
                }
            } catch (HSSFUserException e) {
                throw new SQLException(e.getMessage(), e);
            }
        }
    }

    /**
     * Main HSSFListener method, processes events
     */
    public void processRecord(Record record) {
        int thisRow = -1;
        int thisColumn = -1;
        CellValueHolder thisCellValue = new CellValueHolder();

        switch (record.getSid()) {
            case BoundSheetRecord.sid:
                boundSheetRecords.add(record);
                break;
            case BOFRecord.sid:
                BOFRecord br = (BOFRecord) record;
                if (br.getType() == BOFRecord.TYPE_WORKSHEET) {
                    // Create sub workbook if required
                    if (workbookBuildingListener != null && stubWorkbook == null) {
                        stubWorkbook = workbookBuildingListener.getStubHSSFWorkbook();
                    }
                    // Output the worksheet name
                    // Works by ordering the BSRs by the location of
                    //  their BOFRecords, and then knowing that we
                    //  postConstruct BOFRecords in byte offset order
                    sheetIndex++;
                    if (orderedBSRs == null) {
                        orderedBSRs = BoundSheetRecord.orderByBofPosition(boundSheetRecords);
                    }
                    inRequiredSheet = getSheetName().equalsIgnoreCase(orderedBSRs[sheetIndex].getSheetname())
                            || ("\"" + orderedBSRs[sheetIndex].getSheetname() + "\"").equalsIgnoreCase(getSheetName());
                }
                break;

            case SSTRecord.sid:
                sstRecord = (SSTRecord) record;
                break;

            case BlankRecord.sid:
                BlankRecord brec = (BlankRecord) record;

                thisRow = brec.getRow();
                thisColumn = brec.getColumn();
                thisCellValue.stringValue = "";
                break;
            case BoolErrRecord.sid:
                BoolErrRecord berec = (BoolErrRecord) record;
                thisRow = berec.getRow();
                thisColumn = berec.getColumn();
                thisCellValue.stringValue = "";
                break;
            case FormulaRecord.sid:
                FormulaRecord frec = (FormulaRecord) record;
                thisRow = frec.getRow();
                thisColumn = frec.getColumn();
                if (outputFormulaValues) {
                    if (Double.isNaN(frec.getValue())) {
                        // Formula result is a string
                        // This is stored in the next record
                        outputNextStringRecord = true;
                        nextRow = frec.getRow();
                        nextColumn = frec.getColumn();
                    } else {
                        thisCellValue.stringValue = formatListener.formatNumberDateCell(frec);
                        thisCellValue.doubleValue = frec.getValue();
                        thisCellValue.dateValue = convertDateValue(frec.getValue(), formatListener.getFormatIndex(frec), formatListener.getFormatString(frec));
                    }
                } else {
                    thisCellValue.stringValue = HSSFFormulaParser.toFormulaString(stubWorkbook, frec.getParsedExpression());
                    thisCellValue.doubleValue = frec.getValue();
                    thisCellValue.dateValue = convertDateValue(frec.getValue(), formatListener.getFormatIndex(frec), formatListener.getFormatString(frec));
                }
                break;
            case StringRecord.sid:
                if (outputNextStringRecord) {
                    // String for formula
                    StringRecord srec = (StringRecord) record;
                    thisCellValue.stringValue = srec.getString();
                    thisRow = nextRow;
                    thisColumn = nextColumn;
                    outputNextStringRecord = false;
                }
                break;

            case LabelRecord.sid:
                LabelRecord lrec = (LabelRecord) record;
                thisRow = lrec.getRow();
                thisColumn = lrec.getColumn();
                thisCellValue.stringValue = lrec.getValue();
                break;
            case LabelSSTRecord.sid:
                LabelSSTRecord lsrec = (LabelSSTRecord) record;
                thisRow = lsrec.getRow();
                thisColumn = lsrec.getColumn();
                if (sstRecord == null) {
                    thisCellValue.stringValue = '"' + "(No SST Record, can't identify string)" + '"';
                } else {
                    thisCellValue.stringValue = sstRecord.getString(lsrec.getSSTIndex()).toString();
                }
                break;
            case NoteRecord.sid:
                NoteRecord nrec = (NoteRecord) record;
                thisRow = nrec.getRow();
                thisColumn = nrec.getColumn();
                // TODO: Find object to match nrec.getShapeId()
                thisCellValue.stringValue = '"' + "(TODO)" + '"';
                break;
            case NumberRecord.sid:
                NumberRecord numrec = (NumberRecord) record;
                thisRow = numrec.getRow();
                thisColumn = numrec.getColumn();
                // Format
                thisCellValue.stringValue = formatListener.formatNumberDateCell(numrec);
                thisCellValue.doubleValue = numrec.getValue();
                thisCellValue.dateValue = convertDateValue(numrec.getValue(), formatListener.getFormatIndex(numrec), formatListener.getFormatString(numrec));
                break;
            case RKRecord.sid:
                RKRecord rkrec = (RKRecord) record;
                thisRow = rkrec.getRow();
                thisColumn = rkrec.getColumn();
                thisCellValue.stringValue = '"' + "(TODO)" + '"';
                break;
            default:
                break;
        }
        // Handle new row
        if (thisRow != -1 && thisRow != lastRowNumber) {
            lastColumnNumber = -1;
        }
        // Handle missing column
        if (record instanceof MissingCellDummyRecord) {
            MissingCellDummyRecord mc = (MissingCellDummyRecord) record;
            thisRow = mc.getRow();
            thisColumn = mc.getColumn();
            thisCellValue.stringValue = "";
        }
        // If we got something to print out, do so
        if (thisCellValue.stringValue != null) {
            //If we are on the first row - fill column names
            if (getCurrentSheetRowIndex().equals(0L) && inRequiredSheet) {
                getColumns().add(thisCellValue);
            } else if (inRequiredSheet) {
                addCurrentRowValue(thisCellValue);
            }
        }
        // Update column and row count
        if (thisRow > -1)
            lastRowNumber = thisRow;
        if (thisColumn > -1)
            lastColumnNumber = thisColumn;
        // Handle end of row
        if (record instanceof LastCellOfRowDummyRecord) {
            // We're onto a new row
            lastColumnNumber = -1;
            // End the row
            setCurrentSheetRowIndex(getCurrentSheetRowIndex() + 1);
        }
    }

    @Override
    protected void onClose() throws SQLException {
        try {
            if (fileSystem != null) {
                fileSystem.close();
            }
        } catch (IOException e) {
            throw new SQLException(e.getMessage(), e);
        }
    }

    class PublicMorozoffHSSFRequest extends HSSFRequest {
        public short processRecord(Record rec) throws HSSFUserException {
            return super.processRecord(rec);
        }
    }

}