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

import org.apache.poi.ss.usermodel.DateUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public abstract class AbstractXlsSheetIterator implements Iterable<Object>, Iterator<Object> {

    private URL fileName;
    private String sheetName;
    private List<CellValueHolder> columns = new ArrayList<CellValueHolder>();
    private Map<Long, List<CellValueHolder>> rowValues = new HashMap<Long, List<CellValueHolder>>();

    //Counter includes columns row
    private Long currentSheetRowIndex = 0L;
    private Long currentIteratorRowIndex = 0L;

    /**
     * @param filename The file to postConstruct
     */
    public AbstractXlsSheetIterator(URL filename, String sheetName) throws SQLException {
        this.setFileName(filename);
        this.setSheetName(sheetName);
        postConstruct();
    }

    protected abstract void postConstruct() throws SQLException;

    protected abstract void processNextRecords() throws SQLException;

    protected abstract void onClose() throws SQLException;

    public Iterator<Object> iterator() {
        return this;
    }

    public boolean hasNext() {
        return getRowValues().get(getCurrentIteratorRowIndex() + 1) != null;
    }

    public Object next(){
        try {
            getRowValues().remove(getCurrentIteratorRowIndex());
            setCurrentIteratorRowIndex(getCurrentIteratorRowIndex() + 1);
            //Fill current row
            processNextRecords();
            if (getRowValues().get(getCurrentIteratorRowIndex() + 1L) == null) {
                processNextRecords();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
        return getRowValues().get(getCurrentIteratorRowIndex());
    }

    void addCurrentRowValue(CellValueHolder cellValue) {
        if (getRowValues().get(getCurrentSheetRowIndex()) == null) {
            getRowValues().put(getCurrentSheetRowIndex(), new ArrayList<CellValueHolder>());
        }
        getRowValues().get(getCurrentSheetRowIndex()).add(cellValue);
    }

    CellValueHolder getCurrentRowValue(int column){
         CellValueHolder result = new CellValueHolder();
        if(getRowValues().get(getCurrentIteratorRowIndex())!=null){
            if(column < getRowValues().get(getCurrentIteratorRowIndex()).size()){
                result = getRowValues().get(getCurrentIteratorRowIndex()).get(column);
            }
        }
        return result;
    }


    CellValueHolder getNextRowValue(int column){
        CellValueHolder result = new CellValueHolder();
        if(getRowValues().get(getCurrentIteratorRowIndex() + 1)!=null){
            if(column < getRowValues().get(getCurrentIteratorRowIndex() + 1).size()){
                result = getRowValues().get(getCurrentIteratorRowIndex() + 1).get(column);
            }
        }
        return result;
    }

    public void remove() {
    }

    Date convertDateValue(double value,int formatIndex,String formatString) {
        // Get the built in format, if there is one
        if (DateUtil.isADateFormat(formatIndex, formatString)) {
            if (DateUtil.isValidExcelDate(value)) {
                return DateUtil.getJavaDate(value, false);
            }
        }
        return null;
    }

    protected URL getFileName() {
        return fileName;
    }

    protected void setFileName(URL fileName) {
        this.fileName = fileName;
    }

    protected String getSheetName() {
        return sheetName;
    }

    protected void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    protected List<CellValueHolder> getColumns() {
        return columns;
    }

    protected void setColumns(List<CellValueHolder> columns) {
        this.columns = columns;
    }

    protected Map<Long, List<CellValueHolder>> getRowValues() {
        return rowValues;
    }

    protected void setRowValues(Map<Long, List<CellValueHolder>> rowValues) {
        this.rowValues = rowValues;
    }

    protected Long getCurrentSheetRowIndex() {
        return currentSheetRowIndex;
    }

    protected void setCurrentSheetRowIndex(Long currentSheetRowIndex) {
        this.currentSheetRowIndex = currentSheetRowIndex;
    }

    protected Long getCurrentIteratorRowIndex() {
        return currentIteratorRowIndex;
    }

    protected void setCurrentIteratorRowIndex(Long currentIteratorRowIndex) {
        this.currentIteratorRowIndex = currentIteratorRowIndex;
    }

    class CellValueHolder {
        String stringValue;
        Double doubleValue;
        Date dateValue;

        @Override
        public String toString() {
            return "CellValueHolder{" +
                    "stringValue='" + stringValue + '\'' +
                    ", doubleValue=" + doubleValue +
                    ", dateValue=" + dateValue +
                    '}';
        }

        public Class getType(){
            if (this.dateValue != null) return dateValue.getClass();
            if (this.doubleValue != null) return doubleValue.getClass();
            if (this.stringValue != null) return stringValue.getClass();
            return Object.class;
        }

    }

}
