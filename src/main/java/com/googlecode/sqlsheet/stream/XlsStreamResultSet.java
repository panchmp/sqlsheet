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

import org.apache.poi.POIXMLDocument;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.Map;

/**
 * SqlSheet implementation of java.sql.ResultSet which uses steaming over XLS
 *
 * @author <a href='http://code.google.com/p/sqlsheet'>sqlsheet</a>
 */
public class XlsStreamResultSet implements ResultSet {

    private static enum XlsType {XLS, XLSX}

    AbstractXlsSheetIterator iterator;
    private XlsStreamingResultSetMetaData metadata;

    public XlsStreamResultSet(String tableName, XlsStreamConnection connection) throws SQLException {
        try {
            switch (getXlsType(connection.xlsFile)){
                case XLS:
                    iterator = new XlsSheetIterator(connection.xlsFile, tableName);
                    break;
                case XLSX:
                    iterator = new XlsxSheetIterator(connection.xlsFile, tableName);
                    break;
                default:
                    throw new SQLException("Cannot detect type of XLS file");
            }
        } catch (IOException e) {
            throw new SQLException(e.getMessage(), e);
        }
        metadata = new XlsStreamingResultSetMetaData(iterator.columns.toArray(new String[]{}));
    }

    XlsType getXlsType(URL inputXls) throws IOException {
        InputStream input = inputXls.openStream();
        try {
            if (POIFSFileSystem.hasPOIFSHeader(inputXls.openStream())) {
                return XlsType.XLS;
            }
            if (POIXMLDocument.hasOOXMLHeader(input)) {
                return XlsType.XLSX;
            }
        } finally {
            if (input != null) {
                input.close();
            }
        }
        return null;
    }

    private XlsSheetIterator.CellValueHolder getCell(int jdbcColumn) {
        return iterator.getCurrentRowValue(jdbcColumn-1);
    }

    private XlsSheetIterator.CellValueHolder getCell(String jdbcColumn) {
        int jdbcColumnIndex = iterator.columns.indexOf(jdbcColumn);
        return iterator.getCurrentRowValue(jdbcColumnIndex);
    }

    public ResultSetMetaData getMetaData() throws SQLException {
        return metadata;
    }

    public <T> T getObject(int columnIndex, Class<T> type) throws SQLException {
        return (T)getObject(columnIndex);
    }

    public <T> T getObject(String columnName, Class<T> type) throws SQLException {
        return (T)getObject(columnName);
    }

    public boolean getBoolean(int jdbcColumn) throws SQLException {
        XlsSheetIterator.CellValueHolder cell = getCell(jdbcColumn);
        return cell == null ? false : Boolean.parseBoolean(cell.stringValue);
    }

    public boolean getBoolean(String jdbcColumn) throws SQLException {
        XlsSheetIterator.CellValueHolder cell = getCell(jdbcColumn);
        return cell == null ? false : Boolean.parseBoolean(cell.stringValue);
    }

    public double getDouble(int jdbcColumn) throws SQLException {
        XlsSheetIterator.CellValueHolder cell = getCell(jdbcColumn);
        return cell == null ? 0 : cell.doubleValue;
    }

    public double getDouble(String jdbcColumn) throws SQLException {
        XlsSheetIterator.CellValueHolder cell = getCell(jdbcColumn);
        return cell == null ? 0 : cell.doubleValue;
    }

    public byte getByte(int jdbcColumn) throws SQLException {
        XlsSheetIterator.CellValueHolder cell = getCell(jdbcColumn);
        return (byte) (cell == null ? 0 : cell.doubleValue);
    }

    public byte getByte(String jdbcColumn) throws SQLException {
        XlsSheetIterator.CellValueHolder cell = getCell(jdbcColumn);
        return (byte) (cell == null ? 0 : cell.doubleValue);
    }

    public float getFloat(int jdbcColumn) throws SQLException {
        XlsSheetIterator.CellValueHolder cell = getCell(jdbcColumn);
        return (float) (cell == null ? 0 : cell.doubleValue);
    }

    public float getFloat(String jdbcColumn) throws SQLException {
        XlsSheetIterator.CellValueHolder cell = getCell(jdbcColumn);
        return (float) (cell == null ? 0 : cell.doubleValue);
    }

    public int getInt(int jdbcColumn) throws SQLException {
        XlsSheetIterator.CellValueHolder cell = getCell(jdbcColumn);
        return (int) (cell == null ? 0 : cell.doubleValue);
    }

    public int getInt(String jdbcColumn) throws SQLException {
        XlsSheetIterator.CellValueHolder cell = getCell(jdbcColumn);
        return (int) (cell == null ? 0 : cell.doubleValue);
    }

    public long getLong(int jdbcColumn) throws SQLException {
        XlsSheetIterator.CellValueHolder cell = getCell(jdbcColumn);
        return (long) (cell == null ? 0 : cell.doubleValue);
    }

    public long getLong(String jdbcColumn) throws SQLException {
        XlsSheetIterator.CellValueHolder cell = getCell(jdbcColumn);
        return (long) (cell == null ? 0 : cell.doubleValue);
    }

    public Object getObject(String jdbcColumn) throws SQLException {
        return getObject(getCell(jdbcColumn));
    }

    public Object getObject(int jdbcColumn) throws SQLException {
        return getObject(getCell(jdbcColumn));
    }

    public short getShort(int jdbcColumn) throws SQLException {
        XlsSheetIterator.CellValueHolder cell = getCell(jdbcColumn);
        return (short) (cell == null ? 0 : cell.doubleValue);
    }

    public short getShort(String jdbcColumn) throws SQLException {
        XlsSheetIterator.CellValueHolder cell = getCell(jdbcColumn);
        return (short) (cell == null ? 0 : cell.doubleValue);
    }

    public String getString(int jdbcColumn) throws SQLException {
        XlsSheetIterator.CellValueHolder cell = getCell(jdbcColumn);
        return cell == null ? null : cell.stringValue;
    }

    public String getString(String jdbcColumn) throws SQLException {
        XlsSheetIterator.CellValueHolder cell = getCell(jdbcColumn);
        return cell == null ? null : cell.stringValue;
    }

    private static Object getObject(XlsSheetIterator.CellValueHolder cell) throws SQLException {
        if (cell == null) return null;
        if (cell.dateValue != null) return cell.dateValue;
        if (cell.doubleValue != null) return cell.doubleValue;
        if (cell.stringValue != null) return cell.stringValue;
        return null;
    }

    public void updateBoolean(int jdbcColumn, boolean x) throws SQLException {
        throw nyi();
    }

    public void updateBoolean(String jdbcColumn, boolean x) throws SQLException {
        throw nyi();
    }

    public void updateByte(int jdbcColumn, byte x) throws SQLException {
        throw nyi();
    }

    public void updateByte(String jdbcColumn, byte x) throws SQLException {
        throw nyi();
    }

    public void updateDouble(int jdbcColumn, double x) throws SQLException {
        throw nyi();
    }

    public void updateDouble(String jdbcColumn, double x) throws SQLException {
        throw nyi();
    }

    public void updateFloat(int jdbcColumn, float x) throws SQLException {
        throw nyi();
    }

    public void updateFloat(String jdbcColumn, float x) throws SQLException {
        throw nyi();
    }

    public void updateInt(int jdbcColumn, int x) throws SQLException {
        throw nyi();
    }

    public void updateInt(String jdbcColumn, int x) throws SQLException {
        throw nyi();
    }

    public void updateLong(int jdbcColumn, long x) throws SQLException {
        throw nyi();
    }

    public void updateLong(String jdbcColumn, long x) throws SQLException {
        throw nyi();
    }

    public void updateNull(int jdbcColumn) throws SQLException {
        throw nyi();
    }

    public void updateNull(String jdbcColumn) throws SQLException {
        throw nyi();
    }

    public void updateObject(int jdbcColumn, Object x) throws SQLException {
        throw nyi();
    }

    public void updateObject(String jdbcColumn, Object x) throws SQLException {
        throw nyi();
    }

    public void updateShort(int jdbcColumn, short x) throws SQLException {
        throw nyi();
    }

    public void updateShort(String jdbcColumn, short x) throws SQLException {
        throw nyi();
    }

    public void updateString(int jdbcColumn, String x) throws SQLException {
        throw nyi();
    }

    public void updateString(String jdbcColumn, String x) throws SQLException {
        throw nyi();
    }

    // ResultSet implementation - everything else
    public boolean absolute(int row) throws SQLException {
        return true;
    }

    public void afterLast() throws SQLException {
    }

    public void beforeFirst() throws SQLException {
    }

    public boolean first() throws SQLException {
        return true;
    }

    public int getFetchDirection() throws SQLException {
        return FETCH_FORWARD;
    }

    public int getFetchSize() throws SQLException {
        return 0;
    }

    public int getRow() throws SQLException {
        return iterator.currentIteratorRowIndex.intValue();
    }

    public int getType() throws SQLException {
        return TYPE_FORWARD_ONLY;
    }

    public boolean isAfterLast() throws SQLException {
        return iterator.hasNext();
    }

    public boolean isBeforeFirst() throws SQLException {
        return iterator.currentIteratorRowIndex == 0;
    }

    public boolean isFirst() throws SQLException {
        return iterator.currentIteratorRowIndex == 1;
    }

    public boolean isLast() throws SQLException {
        return iterator.hasNext();
    }

    public boolean last() throws SQLException {
        return iterator.hasNext();
    }

    public boolean next() throws SQLException {
        if (iterator.hasNext()) {
            iterator.next();
            return true;
        } else {
            return false;
        }
    }

    public boolean previous() throws SQLException {
        return false;
    }

    public void moveToInsertRow() throws SQLException {
        throw nyi();
    }

    public void insertRow() throws SQLException {
        throw nyi();
    }

    // Private methods

    private IllegalStateException nyi() {
        return new IllegalStateException("NYI");
    }

    public void cancelRowUpdates() throws SQLException {
        throw nyi();
    }

    public void clearWarnings() throws SQLException {
        throw nyi();
    }

    public void close() throws SQLException {
        iterator.onClose();
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

    public RowId getRowId(int columnIndex) throws SQLException {
        throw nyi();
    }

    public RowId getRowId(String columnLabel) throws SQLException {
        throw nyi();
    }

    public void updateRowId(int columnIndex, RowId x) throws SQLException {
        throw nyi();
    }

    public void updateRowId(String columnLabel, RowId x) throws SQLException {
        throw nyi();
    }

    public int getHoldability() throws SQLException {
        throw nyi();
    }

    public boolean isClosed() throws SQLException {
        throw nyi();
    }

    public void updateNString(int columnIndex, String nString) throws SQLException {
        throw nyi();
    }

    public void updateNString(String columnLabel, String nString) throws SQLException {
        throw nyi();
    }

    public void updateNClob(int columnIndex, NClob nClob) throws SQLException {
        throw nyi();
    }

    public void updateNClob(String columnLabel, NClob nClob) throws SQLException {
        throw nyi();
    }

    public NClob getNClob(int columnIndex) throws SQLException {
        throw nyi();
    }

    public NClob getNClob(String columnLabel) throws SQLException {
        throw nyi();
    }

    public SQLXML getSQLXML(int columnIndex) throws SQLException {
        throw nyi();
    }

    public SQLXML getSQLXML(String columnLabel) throws SQLException {
        throw nyi();
    }

    public void updateSQLXML(int columnIndex, SQLXML xmlObject) throws SQLException {
        throw nyi();
    }

    public void updateSQLXML(String columnLabel, SQLXML xmlObject) throws SQLException {
        throw nyi();
    }

    public String getNString(int columnIndex) throws SQLException {
        throw nyi();
    }

    public String getNString(String columnLabel) throws SQLException {
        throw nyi();
    }

    public Reader getNCharacterStream(int columnIndex) throws SQLException {
        throw nyi();
    }

    public Reader getNCharacterStream(String columnLabel) throws SQLException {
        throw nyi();
    }

    public void updateNCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
        throw nyi();
    }

    public void updateNCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
        throw nyi();
    }

    public void updateAsciiStream(int columnIndex, InputStream x, long length) throws SQLException {
        throw nyi();
    }

    public void updateBinaryStream(int columnIndex, InputStream x, long length) throws SQLException {
        throw nyi();
    }

    public void updateCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
        throw nyi();
    }

    public void updateAsciiStream(String columnLabel, InputStream x, long length) throws SQLException {
        throw nyi();
    }

    public void updateBinaryStream(String columnLabel, InputStream x, long length) throws SQLException {
        throw nyi();
    }

    public void updateCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
        throw nyi();
    }

    public void updateBlob(int columnIndex, InputStream inputStream, long length) throws SQLException {
        throw nyi();
    }

    public void updateBlob(String columnLabel, InputStream inputStream, long length) throws SQLException {
        throw nyi();
    }

    public void updateClob(int columnIndex, Reader reader, long length) throws SQLException {
        throw nyi();
    }

    public void updateClob(String columnLabel, Reader reader, long length) throws SQLException {
        throw nyi();
    }

    public void updateNClob(int columnIndex, Reader reader, long length) throws SQLException {
        throw nyi();
    }

    public void updateNClob(String columnLabel, Reader reader, long length) throws SQLException {
        throw nyi();
    }

    public void updateNCharacterStream(int columnIndex, Reader x) throws SQLException {
        throw nyi();
    }

    public void updateNCharacterStream(String columnLabel, Reader reader) throws SQLException {
        throw nyi();
    }

    public void updateAsciiStream(int columnIndex, InputStream x) throws SQLException {
        throw nyi();
    }

    public void updateBinaryStream(int columnIndex, InputStream x) throws SQLException {
        throw nyi();
    }

    public void updateCharacterStream(int columnIndex, Reader x) throws SQLException {
        throw nyi();
    }

    public void updateAsciiStream(String columnLabel, InputStream x) throws SQLException {
        throw nyi();
    }

    public void updateBinaryStream(String columnLabel, InputStream x) throws SQLException {
        throw nyi();
    }

    public void updateCharacterStream(String columnLabel, Reader reader) throws SQLException {
        throw nyi();
    }

    public void updateBlob(int columnIndex, InputStream inputStream) throws SQLException {
        throw nyi();
    }

    public void updateBlob(String columnLabel, InputStream inputStream) throws SQLException {
        throw nyi();
    }

    public void updateClob(int columnIndex, Reader reader) throws SQLException {
        throw nyi();
    }

    public void updateClob(String columnLabel, Reader reader) throws SQLException {
        throw nyi();
    }

    public void updateNClob(int columnIndex, Reader reader) throws SQLException {
        throw nyi();
    }

    public void updateNClob(String columnLabel, Reader reader) throws SQLException {
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


    public <T> T unwrap(Class<T> iface) throws SQLException {
        throw nyi();
    }

    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        throw nyi();
    }
}
