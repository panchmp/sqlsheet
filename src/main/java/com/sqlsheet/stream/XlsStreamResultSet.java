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

  public void updateBoolean(int jdbcColumn, boolean x) {
    throw nyi();
  }

  public void updateBoolean(String jdbcColumn, boolean x) {
    throw nyi();
  }

  public void updateByte(int jdbcColumn, byte x) {
    throw nyi();
  }

  public void updateByte(String jdbcColumn, byte x) {
    throw nyi();
  }

  public void updateDouble(int jdbcColumn, double x) {
    throw nyi();
  }

  public void updateDouble(String jdbcColumn, double x) {
    throw nyi();
  }

  public void updateFloat(int jdbcColumn, float x) {
    throw nyi();
  }

  public void updateFloat(String jdbcColumn, float x) {
    throw nyi();
  }

  public void updateInt(int jdbcColumn, int x) {
    throw nyi();
  }

  public void updateInt(String jdbcColumn, int x) {
    throw nyi();
  }

  public void updateLong(int jdbcColumn, long x) {
    throw nyi();
  }

  public void updateLong(String jdbcColumn, long x) {
    throw nyi();
  }

  public void updateNull(int jdbcColumn) {
    throw nyi();
  }

  public void updateNull(String jdbcColumn) {
    throw nyi();
  }

  public void updateObject(int jdbcColumn, Object x) {
    throw nyi();
  }

  public void updateObject(String jdbcColumn, Object x) {
    throw nyi();
  }

  public void updateShort(int jdbcColumn, short x) {
    throw nyi();
  }

  public void updateShort(String jdbcColumn, short x) {
    throw nyi();
  }

  public void updateString(int jdbcColumn, String x) {
    throw nyi();
  }

  public void updateString(String jdbcColumn, String x) {
    throw nyi();
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

  public void setFetchDirection(int direction) {
    throw nyi();
  }

  public int getFetchSize() {
    return 0;
  }

  public void setFetchSize(int rows) {
    throw nyi();
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

  public void moveToInsertRow() {
    throw nyi();
  }

  // Private methods

  public void insertRow() {
    throw nyi();
  }

  private IllegalStateException nyi() {
    return new IllegalStateException("NYI");
  }

  public void cancelRowUpdates() {
    throw nyi();
  }

  public void clearWarnings() {
    throw nyi();
  }

  public void close() throws SQLException {
    iterator.onClose();
  }

  public void deleteRow() {
    throw nyi();
  }

  public int findColumn(String jdbcColumn) {
    throw nyi();
  }

  public Array getArray(int i) {
    throw nyi();
  }

  public Array getArray(String colName) {
    throw nyi();
  }

  public InputStream getAsciiStream(int jdbcColumn) {
    throw nyi();
  }

  public InputStream getAsciiStream(String jdbcColumn) {
    throw nyi();
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

  public InputStream getBinaryStream(int jdbcColumn) {
    throw nyi();
  }

  public InputStream getBinaryStream(String jdbcColumn) {
    throw nyi();
  }

  public Blob getBlob(int i) {
    throw nyi();
  }

  public Blob getBlob(String colName) {
    throw nyi();
  }

  public byte[] getBytes(int jdbcColumn) {
    throw nyi();
  }

  public byte[] getBytes(String jdbcColumn) {
    throw nyi();
  }

  public Reader getCharacterStream(int jdbcColumn) {
    throw nyi();
  }

  public Reader getCharacterStream(String jdbcColumn) {
    throw nyi();
  }

  public Clob getClob(int i) {
    throw nyi();
  }

  public Clob getClob(String colName) {
    throw nyi();
  }

  public int getConcurrency() {
    throw nyi();
  }

  public String getCursorName() {
    throw nyi();
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

  public Object getObject(int i, Map<String, Class<?>> map) {
    throw nyi();
  }

  public Object getObject(String colName, Map<String, Class<?>> map) {
    throw nyi();
  }

  public Ref getRef(int i) {
    throw nyi();
  }

  public Ref getRef(String colName) {
    throw nyi();
  }

  public Statement getStatement() {
    throw nyi();
  }

  public Time getTime(int jdbcColumn) {
    throw nyi();
  }

  public Time getTime(String jdbcColumn) {
    throw nyi();
  }

  public Time getTime(int jdbcColumn, Calendar cal) {
    throw nyi();
  }

  public Time getTime(String jdbcColumn, Calendar cal) {
    throw nyi();
  }

  public Timestamp getTimestamp(int jdbcColumn, Calendar cal) {
    throw nyi();
  }

  public Timestamp getTimestamp(String jdbcColumn, Calendar cal) {
    throw nyi();
  }

  public URL getURL(int jdbcColumn) {
    throw nyi();
  }

  public URL getURL(String jdbcColumn) {
    throw nyi();
  }

  public InputStream getUnicodeStream(int jdbcColumn) {
    throw nyi();
  }

  public InputStream getUnicodeStream(String jdbcColumn) {
    throw nyi();
  }

  public SQLWarning getWarnings() {
    throw nyi();
  }

  public void moveToCurrentRow() {
    throw nyi();
  }

  public void refreshRow() {
    throw nyi();
  }

  public boolean relative(int rows) {
    throw nyi();
  }

  public boolean rowDeleted() {
    throw nyi();
  }

  public boolean rowInserted() {
    throw nyi();
  }

  public boolean rowUpdated() {
    throw nyi();
  }

  public void updateArray(int jdbcColumn, Array x) {
    throw nyi();
  }

  public void updateArray(String jdbcColumn, Array x) {
    throw nyi();
  }

  public RowId getRowId(int columnIndex) {
    throw nyi();
  }

  public RowId getRowId(String columnLabel) {
    throw nyi();
  }

  public void updateRowId(int columnIndex, RowId x) {
    throw nyi();
  }

  public void updateRowId(String columnLabel, RowId x) {
    throw nyi();
  }

  public int getHoldability() {
    throw nyi();
  }

  public boolean isClosed() {
    throw nyi();
  }

  public void updateNString(int columnIndex, String nString) {
    throw nyi();
  }

  public void updateNString(String columnLabel, String nString) {
    throw nyi();
  }

  public void updateNClob(int columnIndex, NClob nClob) {
    throw nyi();
  }

  public void updateNClob(String columnLabel, NClob nClob) {
    throw nyi();
  }

  public NClob getNClob(int columnIndex) {
    throw nyi();
  }

  public NClob getNClob(String columnLabel) {
    throw nyi();
  }

  public SQLXML getSQLXML(int columnIndex) {
    throw nyi();
  }

  public SQLXML getSQLXML(String columnLabel) {
    throw nyi();
  }

  public void updateSQLXML(int columnIndex, SQLXML xmlObject) {
    throw nyi();
  }

  public void updateSQLXML(String columnLabel, SQLXML xmlObject) {
    throw nyi();
  }

  public String getNString(int columnIndex) {
    throw nyi();
  }

  public String getNString(String columnLabel) {
    throw nyi();
  }

  public Reader getNCharacterStream(int columnIndex) {
    throw nyi();
  }

  public Reader getNCharacterStream(String columnLabel) {
    throw nyi();
  }

  public void updateNCharacterStream(int columnIndex, Reader x, long length) {
    throw nyi();
  }

  public void updateNCharacterStream(String columnLabel, Reader reader, long length) {
    throw nyi();
  }

  public void updateAsciiStream(int columnIndex, InputStream x, long length) {
    throw nyi();
  }

  public void updateBinaryStream(int columnIndex, InputStream x, long length) {
    throw nyi();
  }

  public void updateCharacterStream(int columnIndex, Reader x, long length) {
    throw nyi();
  }

  public void updateAsciiStream(String columnLabel, InputStream x, long length) {
    throw nyi();
  }

  public void updateBinaryStream(String columnLabel, InputStream x, long length) {
    throw nyi();
  }

  public void updateCharacterStream(String columnLabel, Reader reader, long length) {
    throw nyi();
  }

  public void updateBlob(int columnIndex, InputStream inputStream, long length) {
    throw nyi();
  }

  public void updateBlob(String columnLabel, InputStream inputStream, long length) {
    throw nyi();
  }

  public void updateClob(int columnIndex, Reader reader, long length) {
    throw nyi();
  }

  public void updateClob(String columnLabel, Reader reader, long length) {
    throw nyi();
  }

  public void updateNClob(int columnIndex, Reader reader, long length) {
    throw nyi();
  }

  public void updateNClob(String columnLabel, Reader reader, long length) {
    throw nyi();
  }

  public void updateNCharacterStream(int columnIndex, Reader x) {
    throw nyi();
  }

  public void updateNCharacterStream(String columnLabel, Reader reader) {
    throw nyi();
  }

  public void updateAsciiStream(int columnIndex, InputStream x) {
    throw nyi();
  }

  public void updateBinaryStream(int columnIndex, InputStream x) {
    throw nyi();
  }

  public void updateCharacterStream(int columnIndex, Reader x) {
    throw nyi();
  }

  public void updateAsciiStream(String columnLabel, InputStream x) {
    throw nyi();
  }

  public void updateBinaryStream(String columnLabel, InputStream x) {
    throw nyi();
  }

  public void updateCharacterStream(String columnLabel, Reader reader) {
    throw nyi();
  }

  public void updateBlob(int columnIndex, InputStream inputStream) {
    throw nyi();
  }

  public void updateBlob(String columnLabel, InputStream inputStream) {
    throw nyi();
  }

  public void updateClob(int columnIndex, Reader reader) {
    throw nyi();
  }

  public void updateClob(String columnLabel, Reader reader) {
    throw nyi();
  }

  public void updateNClob(int columnIndex, Reader reader) {
    throw nyi();
  }

  public void updateNClob(String columnLabel, Reader reader) {
    throw nyi();
  }

  public void updateAsciiStream(int jdbcColumn, InputStream x, int length) {
    throw nyi();
  }

  public void updateAsciiStream(String jdbcColumn, InputStream x, int length) {
    throw nyi();
  }

  public void updateBigDecimal(int jdbcColumn, BigDecimal x) {
    throw nyi();
  }

  public void updateBigDecimal(String jdbcColumn, BigDecimal x) {
    throw nyi();
  }

  public void updateBinaryStream(int jdbcColumn, InputStream x, int length) {
    throw nyi();
  }

  public void updateBinaryStream(String jdbcColumn, InputStream x, int length) {
    throw nyi();
  }

  public void updateBlob(int jdbcColumn, Blob x) {
    throw nyi();
  }

  public void updateBlob(String jdbcColumn, Blob x) {
    throw nyi();
  }

  public void updateBytes(int jdbcColumn, byte[] x) {
    throw nyi();
  }

  public void updateBytes(String jdbcColumn, byte[] x) {
    throw nyi();
  }

  public void updateCharacterStream(int jdbcColumn, Reader x, int length) {
    throw nyi();
  }

  public void updateCharacterStream(String jdbcColumn, Reader reader, int length) {
    throw nyi();
  }

  public void updateClob(int jdbcColumn, Clob x) {
    throw nyi();
  }

  public void updateClob(String jdbcColumn, Clob x) {
    throw nyi();
  }

  public void updateDate(int jdbcColumn, Date x) {
    throw nyi();
  }

  public void updateDate(String jdbcColumn, Date x) {
    throw nyi();
  }

  public void updateObject(int jdbcColumn, Object x, int scale) {
    throw nyi();
  }

  public void updateObject(String jdbcColumn, Object x, int scale) {
    throw nyi();
  }

  public void updateRef(int jdbcColumn, Ref x) {
    throw nyi();
  }

  public void updateRef(String jdbcColumn, Ref x) {
    throw nyi();
  }

  public void updateRow() {
    throw nyi();
  }

  public void updateTime(int jdbcColumn, Time x) {
    throw nyi();
  }

  public void updateTime(String jdbcColumn, Time x) {
    throw nyi();
  }

  public void updateTimestamp(int jdbcColumn, Timestamp x) {
    throw nyi();
  }

  public void updateTimestamp(String jdbcColumn, Timestamp x) {
    throw nyi();
  }

  public boolean wasNull() {
    throw nyi();
  }

  public <T> T unwrap(Class<T> iface) {
    throw nyi();
  }

  public boolean isWrapperFor(Class<?> iface) {
    throw nyi();
  }

  private static enum XlsType {
    XLS,
    XLSX
  }
}
