package com.googlecode.sqlsheet.stream;

import org.apache.poi.ss.usermodel.DateUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public abstract class AbstractXlsSheetIterator implements Iterable<Object>, Iterator<Object> {

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

    }

    URL fileName;
    String sheetName;
    List<String> columns = new ArrayList<String>();
    Map<Long, List<CellValueHolder>> rowValues = new HashMap<Long, List<CellValueHolder>>();

    //Counter includes columns row
    Long currentSheetRowIndex = 0L;
    Long currentIteratorRowIndex = 0L;

    /**
     * @param filename The file to postConstruct
     * @throws IOException
     */
    public AbstractXlsSheetIterator(URL filename, String sheetName) throws SQLException {
        this.fileName = filename;
        this.sheetName = sheetName;
        postConstruct();
    }

    protected abstract void postConstruct() throws SQLException;

    protected abstract void processNextRecords() throws SQLException;

    protected abstract void onClose() throws SQLException;

    public Iterator<Object> iterator() {
        return this;
    }

    public boolean hasNext() {
        return rowValues.get(currentIteratorRowIndex + 1) != null;
    }

    public Object next(){
        try {
            rowValues.remove(currentIteratorRowIndex);
            currentIteratorRowIndex++;
            //Fill current row
            processNextRecords();
            if (rowValues.get(currentIteratorRowIndex + 1L) == null) {
                processNextRecords();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
        return rowValues.get(currentIteratorRowIndex);
    }

    void addCurrentRowValue(CellValueHolder cellValue) {
        if (rowValues.get(currentSheetRowIndex) == null) {
            rowValues.put(currentSheetRowIndex, new ArrayList<CellValueHolder>());
        }
        rowValues.get(currentSheetRowIndex).add(cellValue);
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
}
