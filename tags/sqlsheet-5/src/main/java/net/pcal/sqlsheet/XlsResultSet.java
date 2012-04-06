/*
 * Copyright 2012 pcal.net
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
package net.pcal.sqlsheet;

import org.apache.poi.ss.usermodel.*;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.Map;

/**
 * SqlSheet implementation of java.sql.ResultSet.
 *
 * @author <a href='http://www.pcal.net'>pcal</a>
 * @author <a href='http://code.google.com/p/sqlsheet'>sqlsheet</a>
 */
public class XlsResultSet implements ResultSet {

    private static final double BAD_DOUBLE = 0;
    private Workbook workbook;
    private Sheet sheet;
    private XlsResultSetMetaData metadata;
    private int firstSheetRowOffset = 0;
    private int cursorSheetRow = firstSheetRowOffset - 1;
    private CellStyle dateStyle = null;

    public XlsResultSet(Workbook wb, Sheet s) throws SQLException {
        if (s == null) throw new IllegalArgumentException("null sheet");
        if (wb == null) throw new IllegalArgumentException("null workbook");
        this.workbook = wb;
        this.sheet = s;
        firstSheetRowOffset = 1;
        cursorSheetRow = firstSheetRowOffset - 1;
        metadata = new XlsResultSetMetaData(s);
        // set the default date cell format
        dateStyle = workbook.createCellStyle();

        dateStyle.setDataFormat(workbook.createDataFormat().getFormat("yyyy-mm-dd"));
    }

    @Deprecated
    public XlsResultSet(Sheet s, boolean b) {
        if (b == true) throw new IllegalStateException();
        if (s == null) throw new IllegalArgumentException();
        this.sheet = s;
        firstSheetRowOffset = 0;
        cursorSheetRow = firstSheetRowOffset - 1;
        metadata = null;
    }

    /**
     * Set the style to be applied to updated or created Date cells.
     */
    public void setOutputDateStyle(CellStyle style) {
        this.dateStyle = style;
    }

    public ResultSetMetaData getMetaData() throws SQLException {
        return metadata;
    }

    public boolean getBoolean(int jdbcColumn) throws SQLException {
        Cell cell = getCell(jdbcColumn);
        return cell == null ? false : cell.getBooleanCellValue();
    }

    public boolean getBoolean(String jdbcColumn) throws SQLException {
        Cell cell = getCell(jdbcColumn);
        return cell == null ? false : cell.getBooleanCellValue();
    }

    public double getDouble(int jdbcColumn) throws SQLException {
        Cell cell = getCell(jdbcColumn);
        return cell == null ? 0 : cell.getNumericCellValue();
    }

    public double getDouble(String jdbcColumn) throws SQLException {
        Cell cell = getCell(jdbcColumn);
        return cell == null ? 0 : cell.getNumericCellValue();
    }

    public byte getByte(int jdbcColumn) throws SQLException {
        Cell cell = getCell(jdbcColumn);
        return (byte) (cell == null ? 0 : cell.getNumericCellValue());
    }

    public byte getByte(String jdbcColumn) throws SQLException {
        Cell cell = getCell(jdbcColumn);
        return (byte) (cell == null ? 0 : cell.getNumericCellValue());
    }

    public float getFloat(int jdbcColumn) throws SQLException {
        Cell cell = getCell(jdbcColumn);
        return (float) (cell == null ? 0 : cell.getNumericCellValue());
    }

    public float getFloat(String jdbcColumn) throws SQLException {
        Cell cell = getCell(jdbcColumn);
        return (float) (cell == null ? 0 : cell.getNumericCellValue());
    }

    public int getInt(int jdbcColumn) throws SQLException {
        Cell cell = getCell(jdbcColumn);
        return (int) (cell == null ? 0 : cell.getNumericCellValue());
    }

    public int getInt(String jdbcColumn) throws SQLException {
        Cell cell = getCell(jdbcColumn);
        return (int) (cell == null ? 0 : cell.getNumericCellValue());
    }

    public long getLong(int jdbcColumn) throws SQLException {
        Cell cell = getCell(jdbcColumn);
        return (long) (cell == null ? 0 : cell.getNumericCellValue());
    }

    public long getLong(String jdbcColumn) throws SQLException {
        Cell cell = getCell(jdbcColumn);
        return (long) (cell == null ? 0 : cell.getNumericCellValue());
    }

    public Object getObject(String jdbcColumn) throws SQLException {
        return getObject(getCell(jdbcColumn));
    }

    public Object getObject(int jdbcColumn) throws SQLException {
        return getObject(getCell(jdbcColumn));
    }

    private static Object getObject(Cell cell) throws SQLException {
        try {
            if (cell == null) return null;
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_BOOLEAN:
                    return cell.getBooleanCellValue();
                case Cell.CELL_TYPE_STRING:
                    return cell.getStringCellValue();
                case Cell.CELL_TYPE_NUMERIC:

                    if (DateUtil.isCellDateFormatted(cell)) {
                        return cell.getDateCellValue();
                    }
                    return cell.getNumericCellValue();

                default:
                    return null;
            }
        } catch (Exception e) {
            throw wrapped(e);
        }
    }

    private static SQLException wrapped(Throwable t) {
        SQLException out = new SQLException(t.getMessage());
        out.initCause(t);
        return out;
    }

    public short getShort(int jdbcColumn) throws SQLException {
        Cell cell = getCell(jdbcColumn);
        return (short) (cell == null ? 0 : cell.getNumericCellValue());
    }

    public short getShort(String jdbcColumn) throws SQLException {
        Cell cell = getCell(jdbcColumn);
        return (short) (cell == null ? 0 : cell.getNumericCellValue());
    }

    public String getString(int jdbcColumn) throws SQLException {
        Cell cell = getCell(jdbcColumn);
        return cell == null ? null : cell.getStringCellValue();
    }

    public String getString(String jdbcColumn) throws SQLException {
        Cell cell = getCell(jdbcColumn);
        return cell == null ? null : cell.getStringCellValue();
    }

    public void updateBoolean(int jdbcColumn, boolean x) throws SQLException {
        Cell cell = getCell(jdbcColumn);
        if (cell != null) cell.setCellValue(x);
    }

    public void updateBoolean(String jdbcColumn, boolean x) throws SQLException {
        Cell cell = getCell(jdbcColumn);
        if (cell != null) cell.setCellValue(x);
    }

    public void updateByte(int jdbcColumn, byte x) throws SQLException {
        Cell cell = getCell(jdbcColumn);
        if (cell != null) cell.setCellValue(x);
    }

    public void updateByte(String jdbcColumn, byte x) throws SQLException {
        Cell cell = getCell(jdbcColumn);
        if (cell != null) cell.setCellValue(x);
    }

    public void updateDouble(int jdbcColumn, double x) throws SQLException {
        Cell cell = getCell(jdbcColumn);
        if (cell != null) cell.setCellValue(x);
    }

    public void updateDouble(String jdbcColumn, double x) throws SQLException {
        Cell cell = getCell(jdbcColumn);
        if (cell != null) cell.setCellValue(x);
    }

    public void updateFloat(int jdbcColumn, float x) throws SQLException {
        Cell cell = getCell(jdbcColumn);
        if (cell != null) cell.setCellValue(x);
    }

    public void updateFloat(String jdbcColumn, float x) throws SQLException {
        Cell cell = getCell(jdbcColumn);
        if (cell != null) cell.setCellValue(x);
    }

    public void updateInt(int jdbcColumn, int x) throws SQLException {
        Cell cell = getCell(jdbcColumn);
        if (cell != null) cell.setCellValue(x);
    }

    public void updateInt(String jdbcColumn, int x) throws SQLException {
        Cell cell = getCell(jdbcColumn);
        if (cell != null) cell.setCellValue(x);
    }

    public void updateLong(int jdbcColumn, long x) throws SQLException {
        Cell cell = getCell(jdbcColumn);
        if (cell != null) cell.setCellValue(x);
    }

    public void updateLong(String jdbcColumn, long x) throws SQLException {
        Cell cell = getCell(jdbcColumn);
        if (cell != null) cell.setCellValue(x);
    }

    public void updateNull(int jdbcColumn) throws SQLException {
        Cell cell = getCell(jdbcColumn);
        if (cell != null) cell.setCellValue((String) null); // REVIEW
    }

    public void updateNull(String jdbcColumn) throws SQLException {
        Cell cell = getCell(jdbcColumn);
        if (cell != null) cell.setCellValue((String) null); // REVIEW
    }

    public void updateObject(int jdbcColumn, Object x) throws SQLException {
        updateObject(findOrCreateCell(jdbcColumn), x);
    }

    public void updateObject(String jdbcColumn, Object x) throws SQLException {
        updateObject(findOrCreateCell(jdbcColumn), x);
    }

    public void updateShort(int jdbcColumn, short x) throws SQLException {
        Cell cell = findOrCreateCell(jdbcColumn);
        if (cell != null) cell.setCellValue(x);
    }

    public void updateShort(String jdbcColumn, short x) throws SQLException {
        Cell cell = findOrCreateCell(jdbcColumn);
        if (cell != null) cell.setCellValue(x);
    }

    public void updateString(int jdbcColumn, String x) throws SQLException {
        Cell cell = findOrCreateCell(jdbcColumn);
        if (cell != null) cell.setCellValue(x);
    }

    public void updateString(String jdbcColumn, String x) throws SQLException {
        Cell cell = findOrCreateCell(jdbcColumn);
        if (cell != null) cell.setCellValue(x);
    }

    // =========================================================================
    // ResultSet implementation - everything else

    public boolean absolute(int row) throws SQLException {
        cursorSheetRow = (row - 1) + firstSheetRowOffset;
        return true;
    }

    public void afterLast() throws SQLException {
        cursorSheetRow = sheet.getLastRowNum() + 1;
    }

    public void beforeFirst() throws SQLException {
        cursorSheetRow = firstSheetRowOffset - 1;
    }

    public boolean first() throws SQLException {
        cursorSheetRow = firstSheetRowOffset;
        return true;
    }

    public int getFetchDirection() throws SQLException {
        return FETCH_UNKNOWN;
    }

    public int getFetchSize() throws SQLException {
        return 0;
    }

    public int getRow() throws SQLException {
        return cursorSheetRow - firstSheetRowOffset + 1;
    }

    public int getType() throws SQLException {
        return TYPE_SCROLL_INSENSITIVE;
    }

    public boolean isAfterLast() throws SQLException {
        return cursorSheetRow > sheet.getLastRowNum();
    }

    public boolean isBeforeFirst() throws SQLException {
        return cursorSheetRow < firstSheetRowOffset;
    }

    public boolean isFirst() throws SQLException {
        return cursorSheetRow == firstSheetRowOffset;
    }

    public boolean isLast() throws SQLException {
        return cursorSheetRow == sheet.getLastRowNum();
    }

    public boolean last() throws SQLException {
        cursorSheetRow = sheet.getLastRowNum();
        return true;
    }

    public boolean next() throws SQLException {
        if (isAfterLast()) return false;
        cursorSheetRow++;
        return !isAfterLast();
    }

    public boolean previous() throws SQLException {
        if (isBeforeFirst()) return false;
        cursorSheetRow--;
        return isBeforeFirst();
    }

    public void moveToInsertRow() throws SQLException {
        for (cursorSheetRow = 1; true; cursorSheetRow++) {
            Row row = sheet.getRow(cursorSheetRow);
            if (row == null || (row.getCell((short) 0) == null)) {
                sheet.createRow(cursorSheetRow);
                return;
            }
        }
    }

    public void insertRow() throws SQLException {
        cursorSheetRow++;
    }

    // =========================================================================
    // Private methods

    private void updateObject(Cell cell, Object x) throws SQLException {
        if (x instanceof String) {
            cell.setCellValue((String) x);
        } else if (x instanceof char[]) {
            cell.setCellValue(new String((char[]) x));
        } else if (x instanceof Double) {
            if ((Double) x == Double.NEGATIVE_INFINITY
                    || (Double) x == Double.POSITIVE_INFINITY || (Double) x == Double.NaN) {
                cell.setCellValue(BAD_DOUBLE);
            } else {
                cell.setCellValue((Double) x);
            }
        } else if (x instanceof Number) {
            cell.setCellValue(((Number) x).doubleValue());
            // } else if (x instanceof java.sql.Date) {
            // cell.setCellValue(new java.util.Date(((java.sql.Date)x).getTime()));
            // if (dateStyle != null) cell.setCellStyle(dateStyle);
        } else if (x instanceof java.util.Date) {
            cell.setCellValue(DateUtil.getExcelDate((java.util.Date) x));
            if (dateStyle != null) cell.setCellStyle(dateStyle);
        } else if (x instanceof Boolean) {
            cell.setCellValue((Boolean) x);
        } else if (x == null) {
            cell.setCellValue((String) null);
        } else {
            throw new SQLException(
                    "Unknown value type for ExcelResultSet.updateObject: " + x + " ("
                            + x.getClass().getName() + ")");
        }
    }

    private Row getCurrentRow() {
        return sheet.getRow(cursorSheetRow);
    }

    private Cell findOrCreateCell(int jdbcColumn) {
        Cell cell = getCell(jdbcColumn);
        if (cell == null) {
            cell = getCurrentRow().createCell((short) (jdbcColumn - 1));
        }
        return cell;
    }

    private Cell findOrCreateCell(String jdbcColumn) {
        int col = getSheetColumnNamed(jdbcColumn);
        return findOrCreateCell(col);
    }

    private Cell getCell(int jdbcColumn) {
        return sheet.getRow(cursorSheetRow).getCell((short) (jdbcColumn - 1));
    }

    private Cell getCell(String named) {
        return sheet.getRow(cursorSheetRow).getCell(getSheetColumnNamed(named));
    }

    private short getSheetColumnNamed(String name) {
        int count = metadata.getColumnCount();
        for (short i = 0; i < count; i++) {
            String col = metadata.getColumnName(i + 1);
            if (col.equalsIgnoreCase(name)) return i;
        }
        return -1;
    }

    private IllegalStateException nyi() {
        return new IllegalStateException("NYI");
    }

    // =========================================================================
    // Beyond Here Be Unimplemented Methods

    public void cancelRowUpdates() throws SQLException {
        throw nyi();
    }

    public void clearWarnings() throws SQLException {
        throw nyi();
    }

    public void close() throws SQLException {
    }

    public void deleteRow() throws SQLException {
        throw nyi();
    }

    public int findColumn(String jdbcColumn) throws SQLException {
        throw nyi();
    }

    public Array getArray(int i) throws SQLException {
        throw nyi();
    }

    public Array getArray(String colName) throws SQLException {
        throw nyi();
    }

    public InputStream getAsciiStream(int jdbcColumn) throws SQLException {
        throw nyi();
    }

    public InputStream getAsciiStream(String jdbcColumn) throws SQLException {
        throw nyi();
    }

    public BigDecimal getBigDecimal(int jdbcColumn) throws SQLException {
        throw nyi();
    }

    public BigDecimal getBigDecimal(String jdbcColumn) throws SQLException {
        throw nyi();
    }

    public BigDecimal getBigDecimal(int jdbcColumn, int scale)
            throws SQLException {
        throw nyi();
    }

    public BigDecimal getBigDecimal(String jdbcColumn, int scale)
            throws SQLException {
        throw nyi();
    }

    public InputStream getBinaryStream(int jdbcColumn) throws SQLException {
        throw nyi();
    }

    public InputStream getBinaryStream(String jdbcColumn) throws SQLException {
        throw nyi();
    }

    public Blob getBlob(int i) throws SQLException {
        throw nyi();
    }

    public Blob getBlob(String colName) throws SQLException {
        throw nyi();
    }

    public byte[] getBytes(int jdbcColumn) throws SQLException {
        throw nyi();
    }

    public byte[] getBytes(String jdbcColumn) throws SQLException {
        throw nyi();
    }

    public Reader getCharacterStream(int jdbcColumn) throws SQLException {
        throw nyi();
    }

    public Reader getCharacterStream(String jdbcColumn) throws SQLException {
        throw nyi();
    }

    public Clob getClob(int i) throws SQLException {
        throw nyi();
    }

    public Clob getClob(String colName) throws SQLException {
        throw nyi();
    }

    public int getConcurrency() throws SQLException {
        throw nyi();
    }

    public String getCursorName() throws SQLException {
        throw nyi();
    }

    public Date getDate(int jdbcColumn) throws SQLException {
        throw nyi();
    }

    public Date getDate(String jdbcColumn) throws SQLException {
        throw nyi();
    }

    public Date getDate(int jdbcColumn, Calendar cal) throws SQLException {
        throw nyi();
    }

    public Date getDate(String jdbcColumn, Calendar cal) throws SQLException {
        throw nyi();
    }

    public Object getObject(int i, Map<String, Class<?>> map)
            throws SQLException {
        throw nyi();
    }

    public Object getObject(String colName, Map<String, Class<?>> map)
            throws SQLException {
        throw nyi();
    }

    public Ref getRef(int i) throws SQLException {
        throw nyi();
    }

    public Ref getRef(String colName) throws SQLException {
        throw nyi();
    }

    public Statement getStatement() throws SQLException {
        throw nyi();
    }

    public Time getTime(int jdbcColumn) throws SQLException {
        throw nyi();
    }

    public Time getTime(String jdbcColumn) throws SQLException {
        throw nyi();
    }

    public Time getTime(int jdbcColumn, Calendar cal) throws SQLException {
        throw nyi();
    }

    public Time getTime(String jdbcColumn, Calendar cal) throws SQLException {
        throw nyi();
    }

    public Timestamp getTimestamp(int jdbcColumn) throws SQLException {
        throw nyi();
    }

    public Timestamp getTimestamp(String jdbcColumn) throws SQLException {
        throw nyi();
    }

    public Timestamp getTimestamp(int jdbcColumn, Calendar cal)
            throws SQLException {
        throw nyi();
    }

    public Timestamp getTimestamp(String jdbcColumn, Calendar cal)
            throws SQLException {
        throw nyi();
    }

    public URL getURL(int jdbcColumn) throws SQLException {
        throw nyi();
    }

    public URL getURL(String jdbcColumn) throws SQLException {
        throw nyi();
    }

    public InputStream getUnicodeStream(int jdbcColumn) throws SQLException {
        throw nyi();
    }

    public InputStream getUnicodeStream(String jdbcColumn) throws SQLException {
        throw nyi();
    }

    public SQLWarning getWarnings() throws SQLException {
        throw nyi();
    }

    public void moveToCurrentRow() throws SQLException {
        throw nyi();
    }

    public void refreshRow() throws SQLException {
        throw nyi();
    }

    public boolean relative(int rows) throws SQLException {
        throw nyi();
    }

    public boolean rowDeleted() throws SQLException {
        throw nyi();
    }

    public boolean rowInserted() throws SQLException {
        throw nyi();
    }

    public boolean rowUpdated() throws SQLException {
        throw nyi();
    }

    public void setFetchDirection(int direction) throws SQLException {
        throw nyi();
    }

    public void setFetchSize(int rows) throws SQLException {
        throw nyi();
    }

    public void updateArray(int jdbcColumn, Array x) throws SQLException {
        throw nyi();
    }

    public void updateArray(String jdbcColumn, Array x) throws SQLException {
        throw nyi();
    }

    public void updateAsciiStream(int jdbcColumn, InputStream x, int length)
            throws SQLException {
        throw nyi();
    }

    public void updateAsciiStream(String jdbcColumn, InputStream x, int length)
            throws SQLException {
        throw nyi();
    }

    public void updateBigDecimal(int jdbcColumn, BigDecimal x)
            throws SQLException {
        throw nyi();
    }

    public void updateBigDecimal(String jdbcColumn, BigDecimal x)
            throws SQLException {
        throw nyi();
    }

    public void updateBinaryStream(int jdbcColumn, InputStream x, int length)
            throws SQLException {
        throw nyi();
    }

    public void updateBinaryStream(String jdbcColumn, InputStream x, int length)
            throws SQLException {
        throw nyi();
    }

    public void updateBlob(int jdbcColumn, Blob x) throws SQLException {
        throw nyi();
    }

    public void updateBlob(String jdbcColumn, Blob x) throws SQLException {
        throw nyi();
    }

    public void updateBytes(int jdbcColumn, byte[] x) throws SQLException {
        throw nyi();
    }

    public void updateBytes(String jdbcColumn, byte[] x) throws SQLException {
        throw nyi();
    }

    public void updateCharacterStream(int jdbcColumn, Reader x, int length)
            throws SQLException {
        throw nyi();
    }

    public void updateCharacterStream(String jdbcColumn, Reader reader,
                                      int length) throws SQLException {
        throw nyi();
    }

    public void updateClob(int jdbcColumn, Clob x) throws SQLException {
        throw nyi();
    }

    public void updateClob(String jdbcColumn, Clob x) throws SQLException {
        throw nyi();
    }

    public void updateDate(int jdbcColumn, Date x) throws SQLException {
        throw nyi();
    }

    public void updateDate(String jdbcColumn, Date x) throws SQLException {
        throw nyi();
    }

    public void updateObject(int jdbcColumn, Object x, int scale)
            throws SQLException {
        throw nyi();
    }

    public void updateObject(String jdbcColumn, Object x, int scale)
            throws SQLException {
        throw nyi();
    }

    public void updateRef(int jdbcColumn, Ref x) throws SQLException {
        throw nyi();
    }

    public void updateRef(String jdbcColumn, Ref x) throws SQLException {
        throw nyi();
    }

    public void updateRow() throws SQLException {
        throw nyi();
    }

    public void updateTime(int jdbcColumn, Time x) throws SQLException {
        throw nyi();
    }

    public void updateTime(String jdbcColumn, Time x) throws SQLException {
        throw nyi();
    }

    public void updateTimestamp(int jdbcColumn, Timestamp x)
            throws SQLException {
        throw nyi();
    }

    public void updateTimestamp(String jdbcColumn, Timestamp x)
            throws SQLException {
        throw nyi();
    }

    public boolean wasNull() throws SQLException {
        throw nyi();
    }


}
