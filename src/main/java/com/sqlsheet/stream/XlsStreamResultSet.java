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
package com.sqlsheet.stream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.RoundingMode;
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

  AbstractXlsSheetIterator iterator;
  private XlsStreamingResultSetMetaData metadata;

  public XlsStreamResultSet(String tableName, XlsStreamConnection connection) throws SQLException {
    try {
      switch (getXlsType(connection.xlsFile)) {
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
    metadata = new XlsStreamingResultSetMetaData(iterator);
  }

  private static Object getObject(XlsSheetIterator.CellValueHolder cell) {
    if (cell == null) return null;
    if (cell.dateValue != null) return cell.dateValue;
    if (cell.doubleValue != null) return cell.doubleValue;
    if (cell.stringValue != null) return cell.stringValue;
    return null;
  }

  XlsType getXlsType(URL inputXls) throws IOException {
    InputStream input = inputXls.openStream();
    try {
      Workbook wb = WorkbookFactory.create(input);
      if (wb instanceof HSSFWorkbook) {
        return XlsType.XLS;
      } else if (wb instanceof XSSFWorkbook) {
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
    return iterator.getCurrentRowValue(jdbcColumn - 1);
  }

  private XlsSheetIterator.CellValueHolder getCell(String jdbcColumn) {
    int jdbcColumnIndex = -1;
    boolean found = false;
    for (AbstractXlsSheetIterator.CellValueHolder valueHolder : iterator.getColumns()) {
      jdbcColumnIndex++;
      if (jdbcColumn.equalsIgnoreCase(valueHolder.stringValue)) {
        found = true;
        break;
      }
    }
    if (!found) {
      throw new IllegalArgumentException("Column with name " + jdbcColumn + " not found");
    }
    return iterator.getCurrentRowValue(jdbcColumnIndex);
  }

  public ResultSetMetaData getMetaData() {
    return metadata;
  }

  public <T> T getObject(int columnIndex, Class<T> type) {
    return (T) getObject(columnIndex);
  }

  public <T> T getObject(String columnName, Class<T> type) {
    return (T) getObject(columnName);
  }

  public Timestamp getTimestamp(int jdbcColumn) {
    return new Timestamp(((java.util.Date) getObject(jdbcColumn)).getTime());
  }

  public Timestamp getTimestamp(String jdbcColumn) {
    return new Timestamp(((java.util.Date) getObject(jdbcColumn)).getTime());
  }

  public boolean getBoolean(int jdbcColumn) {
    XlsSheetIterator.CellValueHolder cell = getCell(jdbcColumn);
    return cell != null && Boolean.parseBoolean(cell.stringValue);
  }

  public boolean getBoolean(String jdbcColumn) {
    XlsSheetIterator.CellValueHolder cell = getCell(jdbcColumn);
    return cell != null && Boolean.parseBoolean(cell.stringValue);
  }

  public double getDouble(int jdbcColumn) {
    XlsSheetIterator.CellValueHolder cell = getCell(jdbcColumn);
    return cell == null ? 0 : cell.doubleValue;
  }

  public double getDouble(String jdbcColumn) {
    XlsSheetIterator.CellValueHolder cell = getCell(jdbcColumn);
    return cell == null ? 0 : cell.doubleValue;
  }

  public byte getByte(int jdbcColumn) {
    XlsSheetIterator.CellValueHolder cell = getCell(jdbcColumn);
    return (byte) (cell == null ? 0 : cell.doubleValue);
  }

  public byte getByte(String jdbcColumn) {
    XlsSheetIterator.CellValueHolder cell = getCell(jdbcColumn);
    return (byte) (cell == null ? 0 : cell.doubleValue);
  }

  public float getFloat(int jdbcColumn) {
    XlsSheetIterator.CellValueHolder cell = getCell(jdbcColumn);
    return (float) (cell == null ? 0 : cell.doubleValue);
  }

  public float getFloat(String jdbcColumn) {
    XlsSheetIterator.CellValueHolder cell = getCell(jdbcColumn);
    return (float) (cell == null ? 0 : cell.doubleValue);
  }

  public int getInt(int jdbcColumn) {
    XlsSheetIterator.CellValueHolder cell = getCell(jdbcColumn);
    return (int) (cell == null ? 0 : cell.doubleValue);
  }

  public int getInt(String jdbcColumn) {
    XlsSheetIterator.CellValueHolder cell = getCell(jdbcColumn);
    return (int) (cell == null ? 0 : cell.doubleValue);
  }

  public long getLong(int jdbcColumn) {
    XlsSheetIterator.CellValueHolder cell = getCell(jdbcColumn);
    return (long) (cell == null ? 0 : cell.doubleValue);
  }

  public long getLong(String jdbcColumn) {
    XlsSheetIterator.CellValueHolder cell = getCell(jdbcColumn);
    return (long) (cell == null ? 0 : cell.doubleValue);
  }

  public Object getObject(String jdbcColumn) {
    return getObject(getCell(jdbcColumn));
  }

  public Object getObject(int jdbcColumn) {
    return getObject(getCell(jdbcColumn));
  }

  public short getShort(int jdbcColumn) {
    XlsSheetIterator.CellValueHolder cell = getCell(jdbcColumn);
    return (short) (cell == null ? 0 : cell.doubleValue);
  }

  public short getShort(String jdbcColumn) {
    XlsSheetIterator.CellValueHolder cell = getCell(jdbcColumn);
    return (short) (cell == null ? 0 : cell.doubleValue);
  }

  public String getString(int jdbcColumn) {
    XlsSheetIterator.CellValueHolder cell = getCell(jdbcColumn);
    return cell == null ? null : cell.stringValue;
  }

  public String getString(String jdbcColumn) {
    XlsSheetIterator.CellValueHolder cell = getCell(jdbcColumn);
    return cell == null ? null : cell.stringValue;
  }

  public void updateBoolean(int jdbcColumn, boolean x) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateBoolean(String jdbcColumn, boolean x) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateByte(int jdbcColumn, byte x) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateByte(String jdbcColumn, byte x) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateDouble(int jdbcColumn, double x) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateDouble(String jdbcColumn, double x) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateFloat(int jdbcColumn, float x) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateFloat(String jdbcColumn, float x) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateInt(int jdbcColumn, int x) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateInt(String jdbcColumn, int x) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateLong(int jdbcColumn, long x) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateLong(String jdbcColumn, long x) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateNull(int jdbcColumn) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateNull(String jdbcColumn) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateObject(int jdbcColumn, Object x) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateObject(String jdbcColumn, Object x) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateShort(int jdbcColumn, short x) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateShort(String jdbcColumn, short x) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateString(int jdbcColumn, String x) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateString(String jdbcColumn, String x) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  // ResultSet implementation - everything else
  public boolean absolute(int row) {
    return true;
  }

  public void afterLast() {}

  public void beforeFirst() {}

  public boolean first() {
    return true;
  }

  public int getFetchDirection() {
    return FETCH_FORWARD;
  }

  public void setFetchDirection(int direction) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public int getFetchSize() {
    return 0;
  }

  public void setFetchSize(int rows) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public int getRow() {
    return iterator.getCurrentIteratorRowIndex().intValue();
  }

  public int getType() {
    return TYPE_FORWARD_ONLY;
  }

  public boolean isAfterLast() {
    return iterator.hasNext();
  }

  public boolean isBeforeFirst() {
    return iterator.getCurrentIteratorRowIndex() == 0;
  }

  public boolean isFirst() {
    return iterator.getCurrentIteratorRowIndex() == 1;
  }

  public boolean isLast() {
    return iterator.hasNext();
  }

  public boolean last() {
    return iterator.hasNext();
  }

  public boolean next() {
    if (iterator.hasNext()) {
      iterator.next();
      return true;
    } else {
      return false;
    }
  }

  public boolean previous() {
    return false;
  }

  public void moveToInsertRow() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  // Private methods

  public void insertRow() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void cancelRowUpdates() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void clearWarnings() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void close() throws SQLException {
    iterator.onClose();
  }

  public void deleteRow() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public int findColumn(String jdbcColumn) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public Array getArray(int i) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public Array getArray(String colName) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public InputStream getAsciiStream(int jdbcColumn) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public InputStream getAsciiStream(String jdbcColumn) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public BigDecimal getBigDecimal(int jdbcColumn) {
    XlsSheetIterator.CellValueHolder cell = getCell(jdbcColumn);
    return cell == null ? null : BigDecimal.valueOf(cell.doubleValue);
  }

  public BigDecimal getBigDecimal(String jdbcColumn) {
    XlsSheetIterator.CellValueHolder cell = getCell(jdbcColumn);
    return cell == null ? null : BigDecimal.valueOf(cell.doubleValue);
  }

  public BigDecimal getBigDecimal(int jdbcColumn, int scale) {
    XlsSheetIterator.CellValueHolder cell = getCell(jdbcColumn);
    if (cell == null) {
      return null;
    } else {
      return BigDecimal.valueOf(cell.doubleValue)
              .setScale(scale, RoundingMode.HALF_UP);
    }
  }

  public BigDecimal getBigDecimal(String jdbcColumn, int scale) {
    XlsSheetIterator.CellValueHolder cell = getCell(jdbcColumn);
    if (cell == null) {
      return null;
    } else {
      return BigDecimal.valueOf(cell.doubleValue)
              .setScale(scale, RoundingMode.HALF_UP);
    }
  }

  public InputStream getBinaryStream(int jdbcColumn) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public InputStream getBinaryStream(String jdbcColumn) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public Blob getBlob(int i) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public Blob getBlob(String colName) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public byte[] getBytes(int jdbcColumn) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public byte[] getBytes(String jdbcColumn) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public Reader getCharacterStream(int jdbcColumn) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public Reader getCharacterStream(String jdbcColumn) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public Clob getClob(int i) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public Clob getClob(String colName) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public int getConcurrency() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public String getCursorName() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public Date getDate(int jdbcColumn) {
    XlsSheetIterator.CellValueHolder cell = getCell(jdbcColumn);
    return cell == null ? null : new Date(cell.dateValue.getTime());
  }

  public Date getDate(String jdbcColumn) {
    XlsSheetIterator.CellValueHolder cell = getCell(jdbcColumn);
    return cell == null ? null : new Date(cell.dateValue.getTime());
  }

  public Date getDate(int jdbcColumn, Calendar cal) {
    XlsSheetIterator.CellValueHolder cell = getCell(jdbcColumn);
    if (cell == null) {
      return null;
    } else {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(cell.dateValue);
      calendar.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY));
      calendar.set(Calendar.MINUTE, cal.get(Calendar.MINUTE));
      calendar.set(Calendar.SECOND, cal.get(Calendar.SECOND));
      calendar.set(Calendar.MILLISECOND, cal.get(Calendar.MILLISECOND));
      return new Date(calendar.getTime().getTime());
    }
  }

  public Date getDate(String jdbcColumn, Calendar cal) {
    XlsSheetIterator.CellValueHolder cell = getCell(jdbcColumn);
    if (cell == null) {
      return null;
    } else {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(cell.dateValue);
      calendar.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY));
      calendar.set(Calendar.MINUTE, cal.get(Calendar.MINUTE));
      calendar.set(Calendar.SECOND, cal.get(Calendar.SECOND));
      calendar.set(Calendar.MILLISECOND, cal.get(Calendar.MILLISECOND));
      return new Date(calendar.getTime().getTime());
    }
  }

  public Object getObject(int i, Map<String, Class<?>> map) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public Object getObject(String colName, Map<String, Class<?>> map) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public Ref getRef(int i) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public Ref getRef(String colName) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public Statement getStatement() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public Time getTime(int jdbcColumn) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public Time getTime(String jdbcColumn) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public Time getTime(int jdbcColumn, Calendar cal) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public Time getTime(String jdbcColumn, Calendar cal) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public Timestamp getTimestamp(int jdbcColumn, Calendar cal) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public Timestamp getTimestamp(String jdbcColumn, Calendar cal) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public URL getURL(int jdbcColumn) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public URL getURL(String jdbcColumn) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public InputStream getUnicodeStream(int jdbcColumn) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public InputStream getUnicodeStream(String jdbcColumn) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public SQLWarning getWarnings() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void moveToCurrentRow() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void refreshRow() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public boolean relative(int rows) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public boolean rowDeleted() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public boolean rowInserted() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public boolean rowUpdated() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateArray(int jdbcColumn, Array x) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateArray(String jdbcColumn, Array x) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public RowId getRowId(int columnIndex) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public RowId getRowId(String columnLabel) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateRowId(int columnIndex, RowId x) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateRowId(String columnLabel, RowId x) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public int getHoldability() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public boolean isClosed() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateNString(int columnIndex, String nString) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateNString(String columnLabel, String nString) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateNClob(int columnIndex, NClob nClob) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateNClob(String columnLabel, NClob nClob) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public NClob getNClob(int columnIndex) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public NClob getNClob(String columnLabel) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public SQLXML getSQLXML(int columnIndex) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public SQLXML getSQLXML(String columnLabel) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateSQLXML(int columnIndex, SQLXML xmlObject) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateSQLXML(String columnLabel, SQLXML xmlObject) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public String getNString(int columnIndex) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public String getNString(String columnLabel) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public Reader getNCharacterStream(int columnIndex) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public Reader getNCharacterStream(String columnLabel) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateNCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateNCharacterStream(String columnLabel, Reader reader, long length)
      throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateAsciiStream(int columnIndex, InputStream x, long length) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateBinaryStream(int columnIndex, InputStream x, long length) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateAsciiStream(String columnLabel, InputStream x, long length)
      throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateBinaryStream(String columnLabel, InputStream x, long length)
      throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateCharacterStream(String columnLabel, Reader reader, long length)
      throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateBlob(int columnIndex, InputStream inputStream, long length)
      throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateBlob(String columnLabel, InputStream inputStream, long length)
      throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateClob(int columnIndex, Reader reader, long length) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateClob(String columnLabel, Reader reader, long length) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateNClob(int columnIndex, Reader reader, long length) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateNClob(String columnLabel, Reader reader, long length) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateNCharacterStream(int columnIndex, Reader x) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateNCharacterStream(String columnLabel, Reader reader) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateAsciiStream(int columnIndex, InputStream x) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateBinaryStream(int columnIndex, InputStream x) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateCharacterStream(int columnIndex, Reader x) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateAsciiStream(String columnLabel, InputStream x) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateBinaryStream(String columnLabel, InputStream x) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateCharacterStream(String columnLabel, Reader reader) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateBlob(int columnIndex, InputStream inputStream) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateBlob(String columnLabel, InputStream inputStream) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateClob(int columnIndex, Reader reader) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateClob(String columnLabel, Reader reader) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateNClob(int columnIndex, Reader reader) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateNClob(String columnLabel, Reader reader) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateAsciiStream(int jdbcColumn, InputStream x, int length) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateAsciiStream(String jdbcColumn, InputStream x, int length) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateBigDecimal(int jdbcColumn, BigDecimal x) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateBigDecimal(String jdbcColumn, BigDecimal x) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateBinaryStream(int jdbcColumn, InputStream x, int length) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateBinaryStream(String jdbcColumn, InputStream x, int length) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateBlob(int jdbcColumn, Blob x) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateBlob(String jdbcColumn, Blob x) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateBytes(int jdbcColumn, byte[] x) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateBytes(String jdbcColumn, byte[] x) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateCharacterStream(int jdbcColumn, Reader x, int length) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateCharacterStream(String jdbcColumn, Reader reader, int length)
      throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateClob(int jdbcColumn, Clob x) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateClob(String jdbcColumn, Clob x) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateDate(int jdbcColumn, Date x) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateDate(String jdbcColumn, Date x) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateObject(int jdbcColumn, Object x, int scale) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateObject(String jdbcColumn, Object x, int scale) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateRef(int jdbcColumn, Ref x) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateRef(String jdbcColumn, Ref x) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateRow() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateTime(int jdbcColumn, Time x) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateTime(String jdbcColumn, Time x) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateTimestamp(int jdbcColumn, Timestamp x) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void updateTimestamp(String jdbcColumn, Timestamp x) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public boolean wasNull() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public <T> T unwrap(Class<T> iface) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public boolean isWrapperFor(Class<?> iface) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  private enum XlsType {
    XLS,
    XLSX
  }
}
