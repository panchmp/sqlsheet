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

import org.apache.poi.hssf.record.CellValueRecordInterface;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Streaming iterator over XLSX files
 * Derived from:
 * http://svn.apache.org/repos/asf/poi/trunk/src/examples/src/org/apache/poi/xssf/eventusermodel/XLSX2CSV.java
 */
public class XlsxSheetIterator extends AbstractXlsSheetIterator {

    /**
     * The type of the data value is indicated by an attribute on the cell.
     * The value is usually in a "v" element within the cell.
     */
    enum xssfDataType {
        BOOL,
        ERROR,
        FORMULA,
        INLINESTR,
        SSTINDEX,
        NUMBER,
    }

    OPCPackage xlsxPackage;
    XMLEventReader reader;
    StylesTable styles;
    ReadOnlySharedStringsTable strings;
    XSSFSheetEventHandler handler;

    public XlsxSheetIterator(URL filename, String sheetName) throws SQLException {
        super(filename, sheetName);
    }

    @Override
    protected void postConstruct() throws SQLException {
        try {
            //Open and pre process XLSX file
            xlsxPackage = OPCPackage.open(fileName.getPath(), PackageAccess.READ);
            strings = new ReadOnlySharedStringsTable(this.xlsxPackage);
            XSSFReader xssfReader = new XSSFReader(this.xlsxPackage);
            styles = xssfReader.getStylesTable();
            //Find appropriate sheet
            XSSFReader.SheetIterator iter = (XSSFReader.SheetIterator) xssfReader.getSheetsData();
            while (iter.hasNext()) {
                InputStream stream = iter.next();
                String currentSheetName = iter.getSheetName();
                if (currentSheetName.equalsIgnoreCase(sheetName) ||
                        ("\"" + currentSheetName + "\"").equalsIgnoreCase(sheetName)) {
                    handler = new XSSFSheetEventHandler(styles, strings);
                    XMLInputFactory factory = XMLInputFactory.newInstance();
                    reader = factory.createXMLEventReader(stream);
                    //Start sheet processing
                    while (reader.hasNext() && currentSheetRowIndex == 0) {
                        processNextEvent();
                    }
                    processNextRecords();
                }
            }
        } catch (Exception e) {
            throw new SQLException(e.getMessage(), e);
        }
    }

    @Override
    protected void processNextRecords() throws SQLException {
        Long nextRowIndex = currentSheetRowIndex + 2L;
        while (reader.hasNext() && (currentSheetRowIndex != nextRowIndex)) {
            try {
                processNextEvent();
            } catch (XMLStreamException e) {
                throw new SQLException(e.getMessage(), e);
            }
        }
    }

    @Override
    protected void onClose() throws SQLException {
        try {
            if (xlsxPackage != null) {
                xlsxPackage.close();
            }
            if (reader != null) {
                reader.close();
            }
        } catch (Exception e) {
            new SQLException(e.getMessage(), e);
        }
    }

    /**
     * Parses and shows the content of one sheet
     * using the specified styles and shared-strings tables.
     */
    public void processNextEvent() throws XMLStreamException {
        if (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            XMLEvent nextEvent = reader.peek();
            switch (event.getEventType()) {
                case XMLEvent.START_ELEMENT:
                    handler.startElement(event.asStartElement());
                    if (nextEvent.isCharacters()) {
                        Characters c = reader.nextEvent().asCharacters();
                        if (!c.isWhiteSpace())
                            handler.characters(c.getData().toCharArray());
                    }
                    break;
                case XMLEvent.END_ELEMENT:
                    handler.endElement(event.asEndElement());
                    break;
            }
        }
    }

    /**
     * Derived from http://poi.apache.org/spreadsheet/how-to.html#xssf_sax_api
     * <p/>
     * Also see Standard ECMA-376, 1st edition, part 4, pages 1928ff, at
     * http://www.ecma-international.org/publications/standards/Ecma-376.htm
     * <p/>
     */
    class XSSFSheetEventHandler {

        /**
         * Table with styles
         */
        private StylesTable stylesTable;

        /**
         * Table with unique strings
         */
        private ReadOnlySharedStringsTable sharedStringsTable;

        // Set when V start element is seen
        private boolean vIsOpen;

        // Set when cell start element is seen;
        // used when cell close element is seen.
        private XlsxSheetIterator.xssfDataType nextDataType;

        // Used to format numeric cell values.
        private short formatIndex;
        private String formatString;
        private final DataFormatter formatter;

        private int thisColumn = -1;
        // The last column printed to the output stream
        private int lastColumnNumber = -1;

        // Gathers characters as they are seen.
        private StringBuffer value;

        /**
         * Accepts objects needed while parsing.
         *
         * @param styles  Table of styles
         * @param strings Table of shared strings
         */
        public XSSFSheetEventHandler(StylesTable styles,
                                     ReadOnlySharedStringsTable strings) {
            this.stylesTable = styles;
            this.sharedStringsTable = strings;
            this.value = new StringBuffer();
            this.nextDataType = XlsxSheetIterator.xssfDataType.NUMBER;
            this.formatter = new DataFormatter();
        }

        public void startElement(StartElement startElement) {
            Map<String, String> attributes = new HashMap<String, String>();
            Iterator attributesIterator = startElement.getAttributes();
            while (attributesIterator.hasNext()) {
                Attribute attr = (Attribute) attributesIterator.next();
                attributes.put(attr.getName().getLocalPart(), attr.getValue());
            }

            if ("inlineStr".equals(startElement.getName().getLocalPart())
                    || "v".equals(startElement.getName().getLocalPart())
                    || "is".equals(startElement.getName().getLocalPart())) {
                vIsOpen = true;
                // Clear contents cache
                value.setLength(0);
            }
            // c => cell
            else if ("c".equals(startElement.getName().getLocalPart())) {
                // Get the cell reference
                String r = attributes.get("r");
                int firstDigit = -1;
                for (int c = 0; c < r.length(); ++c) {
                    if (Character.isDigit(r.charAt(c))) {
                        firstDigit = c;
                        break;
                    }
                }
                thisColumn = nameToColumn(r.substring(0, firstDigit));

                // Set up defaults.
                this.nextDataType = XlsxSheetIterator.xssfDataType.NUMBER;
                this.formatIndex = -1;
                this.formatString = null;
                String cellType = attributes.get("t");
                String cellStyleStr = attributes.get("s");
                if ("b".equals(cellType))
                    nextDataType = XlsxSheetIterator.xssfDataType.BOOL;
                else if ("e".equals(cellType))
                    nextDataType = XlsxSheetIterator.xssfDataType.ERROR;
                else if ("inlineStr".equals(cellType))
                    nextDataType = XlsxSheetIterator.xssfDataType.INLINESTR;
                else if ("s".equals(cellType))
                    nextDataType = XlsxSheetIterator.xssfDataType.SSTINDEX;
                else if ("str".equals(cellType))
                    nextDataType = XlsxSheetIterator.xssfDataType.FORMULA;
                else if (cellStyleStr != null) {
                    // It's a number, but almost certainly one
                    //  with a special style or format
                    int styleIndex = Integer.parseInt(cellStyleStr);
                    XSSFCellStyle style = stylesTable.getStyleAt(styleIndex);
                    this.formatIndex = style.getDataFormat();
                    this.formatString = style.getDataFormatString();
                    if (this.formatString == null)
                        this.formatString = BuiltinFormats.getBuiltinFormat(this.formatIndex);
                }
            }

        }

        public void endElement(EndElement endElement) {
            CellValueHolder thisCellValue = new CellValueHolder();
            //String thisStr = null;
            // v => contents of a cell
            if ("v".equals(endElement.getName().getLocalPart())
                    || ("c".equals(endElement.getName().getLocalPart()) && xssfDataType.INLINESTR.equals(nextDataType))) {
                // Process the value contents as required.
                // Do now, as characters() may be called more than once
                switch (nextDataType) {
                    case BOOL:
                        char first = value.charAt(0);
                        thisCellValue.stringValue = first == '0' ? "FALSE" : "TRUE";
                        break;
                    case ERROR:
                        thisCellValue.stringValue = "\"ERROR:" + value.toString() + '"';
                        break;
                    case FORMULA:
                        // A formula could result in a string value,
                        // so always add double-quote characters.
                        thisCellValue.stringValue = value.toString();
                        break;
                    case INLINESTR:
                        // TODO: have seen an example of this, so it's untested.
                        XSSFRichTextString rtsi = new XSSFRichTextString(value.toString());
                        thisCellValue.stringValue = rtsi.toString();
                        break;
                    case SSTINDEX:
                        String sstIndex = value.toString();
                        try {
                            int idx = Integer.parseInt(sstIndex);
                            XSSFRichTextString rtss = new XSSFRichTextString(sharedStringsTable.getEntryAt(idx));
                            thisCellValue.stringValue = rtss.toString();
                        } catch (NumberFormatException ex) {
                            thisCellValue.stringValue = "Failed to parse SST index '" + sstIndex + "': " + ex.toString();
                        }
                        break;
                    case NUMBER:
                        String n = value.toString();
                        if (this.formatString != null) {
                            thisCellValue.stringValue = formatter.formatRawCellContents(Double.parseDouble(n), this.formatIndex, this.formatString);
                        } else {
                            thisCellValue.stringValue = n;
                        }
                        thisCellValue.doubleValue = Double.parseDouble(n);
                        thisCellValue.dateValue = convertDateValue(thisCellValue.doubleValue, this.formatIndex, this.formatString);
                        break;
                    default:
                        thisCellValue.stringValue = "(TODO: Unexpected type: " + nextDataType + ")";
                        break;
                }
                // Output after we've seen the string contents
                // Emit commas for any fields that were missing on this row
                if (lastColumnNumber == -1) {
                    lastColumnNumber = 0;
                }
                //TODO: Check if some actions required
                for (int i = lastColumnNumber; i < thisColumn; ++i) {
                    //  output.print(',');
                }
                if (currentSheetRowIndex == 0) {
                    columns.add(thisCellValue.stringValue);
                } else {
                    // Might be the empty string.
                    addCurrentRowValue(thisCellValue);
                }
                // Update column
                if (thisColumn > -1)
                    lastColumnNumber = thisColumn;
            } else if ("row".equals(endElement.getName().getLocalPart())) {
                // We're onto a new row
                //output.println();
                lastColumnNumber = -1;
                currentSheetRowIndex++;
            }

        }

        /**
         * Captures characters only if a suitable element is open.
         * Originally was just "v"; extended for inlineStr also.
         */
        public void characters(char[] ch) {
            if (vIsOpen)
                value.append(ch);
        }

        /**
         * Converts an Excel column name like "C" to a zero-based index.
         *
         * @param name
         * @return Index corresponding to the specified name
         */
        private int nameToColumn(String name) {
            int column = -1;
            for (int i = 0; i < name.length(); ++i) {
                int c = name.charAt(i);
                column = (column + 1) * 26 + c - 'A';
            }
            return column;
        }

    }

}
