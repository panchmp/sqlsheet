/*
 * Copyright 2020 are.
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
package com.sqlsheet;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Date;
import java.sql.*;
import java.util.*;

/** @author are */
public class ResultSetImpl implements ResultSet {
  private boolean isClosed;
  private Object value = null;
  private int r = -1;
  private final ArrayList<Object[]> rowData = new ArrayList<>();
  private final LinkedList<String> columnNames = new LinkedList<>();
  private final LinkedList<Class> columnClasses = new LinkedList<>();

  public ResultSetImpl(Object[]... columns) throws SQLException {
    for (Object[] columnDefinition : columns)
      if (columnDefinition.length == 2
          && columnDefinition[0] instanceof String
          && columnDefinition[1] instanceof Class) {
        columnNames.add((String) columnDefinition[0]);
        columnClasses.add((Class) columnDefinition[1]);
      } else
        throw new SQLException(
            "Invalid column definition + " + Arrays.deepToString(columnDefinition));
  }

  public void addRow(Object... data) throws SQLException {
    if (data.length == columnNames.size()) {
      for (int c = 0; c < data.length; c++) {
        if (data[c] != null && !columnClasses.get(c).isInstance(data[c]))
          throw new SQLException(
              "Invalid object at column + " + c + " in " + Arrays.deepToString(data));
      }
      rowData.add(data);
    } else
      throw new SQLException(
          "Invalid row data (Length does not match Columns). + " + Arrays.deepToString(data));
  }

  @Override
  public boolean next() {
    if (r < rowData.size() - 1) {
      r++;
      return true;
    } else return false;
  }

  @Override
  public void close() {
    columnNames.clear();
    columnClasses.clear();
    rowData.clear();
    isClosed = true;
  }

  @Override
  public boolean wasNull() {
    return value == null;
  }

  @Override
  public String getString(int columnIndex) throws SQLException {
    int internalColumnIndex = columnIndex - 1;

    if (columnClasses.size() > internalColumnIndex) {
      Class clazz = columnClasses.get(internalColumnIndex);
      if (clazz.equals(String.class)) return (String) (value = rowData.get(r)[internalColumnIndex]);
      else throw new SQLException("Column " + columnIndex + " is not a String.");

    } else throw new SQLException("Column " + columnIndex + " does not exist.");
  }

  @Override
  public boolean getBoolean(int columnIndex) throws SQLException {
    int internalColumnIndex = columnIndex - 1;

    if (columnClasses.size() > internalColumnIndex) {
      Class clazz = columnClasses.get(internalColumnIndex);
      if (clazz.equals(Boolean.class))
        return (Boolean) (value = rowData.get(r)[internalColumnIndex]);
      else throw new SQLException("Column " + columnIndex + " is not a Boolean.");

    } else throw new SQLException("Column " + columnIndex + " does not exist.");
  }

  @Override
  public byte getByte(int columnIndex) throws SQLException {
    int internalColumnIndex = columnIndex - 1;

    if (columnClasses.size() > internalColumnIndex) {
      Class clazz = columnClasses.get(internalColumnIndex);
      if (clazz.equals(Byte.class)) return (Byte) (value = rowData.get(r)[internalColumnIndex]);
      else throw new SQLException("Column " + columnIndex + " is not a Byte.");

    } else throw new SQLException("Column " + columnIndex + " does not exist.");
  }

  @Override
  public short getShort(int columnIndex) throws SQLException {
    int internalColumnIndex = columnIndex - 1;

    if (columnClasses.size() > internalColumnIndex) {
      Class clazz = columnClasses.get(internalColumnIndex);
      if (clazz.equals(Short.class)) return (Short) (value = rowData.get(r)[internalColumnIndex]);
      else throw new SQLException("Column " + columnIndex + " is not a Short.");

    } else throw new SQLException("Column " + columnIndex + " does not exist.");
  }

  @Override
  public int getInt(int columnIndex) throws SQLException {
    int internalColumnIndex = columnIndex - 1;

    if (columnClasses.size() > internalColumnIndex) {
      Class clazz = columnClasses.get(internalColumnIndex);
      if (clazz.equals(Integer.class))
        return (Integer) (value = rowData.get(r)[internalColumnIndex]);
      else throw new SQLException("Column " + columnIndex + " is not an Integer.");

    } else throw new SQLException("Column " + columnIndex + " does not exist.");
  }

  @Override
  public long getLong(int columnIndex) throws SQLException {
    int internalColumnIndex = columnIndex - 1;

    if (columnClasses.size() > internalColumnIndex) {
      Class clazz = columnClasses.get(internalColumnIndex);
      if (clazz.equals(Long.class)) return (Long) (value = rowData.get(r)[internalColumnIndex]);
      else throw new SQLException("Column " + columnIndex + " is not a Long.");

    } else throw new SQLException("Column " + columnIndex + " does not exist.");
  }

  @Override
  public float getFloat(int columnIndex) throws SQLException {
    int internalColumnIndex = columnIndex - 1;

    if (columnClasses.size() > internalColumnIndex) {
      Class clazz = columnClasses.get(internalColumnIndex);
      if (clazz.equals(Float.class)) return (Float) (value = rowData.get(r)[internalColumnIndex]);
      else throw new SQLException("Column " + columnIndex + " is not a Float.");

    } else throw new SQLException("Column " + columnIndex + " does not exist.");
  }

  @Override
  public double getDouble(int columnIndex) throws SQLException {
    int internalColumnIndex = columnIndex - 1;

    if (columnClasses.size() > internalColumnIndex) {
      Class clazz = columnClasses.get(internalColumnIndex);
      if (clazz.equals(Double.class)) return (Double) (value = rowData.get(r)[internalColumnIndex]);
      else throw new SQLException("Column " + columnIndex + " is not a Double.");

    } else throw new SQLException("Column " + columnIndex + " does not exist.");
  }

  @Override
  public BigDecimal getBigDecimal(int columnIndex, int scale) throws SQLException {
    return getBigDecimal(columnIndex).setScale(scale);
  }

  @Override
  public byte[] getBytes(int columnIndex) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Date getDate(int columnIndex) throws SQLException {
    int internalColumnIndex = columnIndex - 1;

    if (columnClasses.size() > internalColumnIndex) {
      Class clazz = columnClasses.get(internalColumnIndex);
      if (clazz.equals(Date.class)) return (Date) (value = rowData.get(r)[internalColumnIndex]);
      else throw new SQLException("Column " + columnIndex + " is not a Date.");

    } else throw new SQLException("Column " + columnIndex + " does not exist.");
  }

  @Override
  public Time getTime(int columnIndex) throws SQLException {
    int internalColumnIndex = columnIndex - 1;

    if (columnClasses.size() > internalColumnIndex) {
      Class clazz = columnClasses.get(internalColumnIndex);
      if (clazz.equals(Time.class)) return (Time) (value = rowData.get(r)[internalColumnIndex]);
      else throw new SQLException("Column " + columnIndex + " is not a Time.");

    } else throw new SQLException("Column " + columnIndex + " does not exist.");
  }

  @Override
  public Timestamp getTimestamp(int columnIndex) throws SQLException {
    int internalColumnIndex = columnIndex - 1;

    if (columnClasses.size() > internalColumnIndex) {
      Class clazz = columnClasses.get(internalColumnIndex);
      if (clazz.equals(java.sql.Timestamp.class))
        return (java.sql.Timestamp) (value = rowData.get(r)[internalColumnIndex]);
      else throw new SQLException("Column " + columnIndex + " is not a Timestamp.");

    } else throw new SQLException("Column " + columnIndex + " does not exist.");
  }

  @Override
  public InputStream getAsciiStream(int columnIndex) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public InputStream getUnicodeStream(int columnIndex) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public InputStream getBinaryStream(int columnIndex) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String getString(String columnLabel) throws SQLException {
    int columnIndex = columnNames.indexOf(columnLabel) + 1;
    if (columnIndex > 0) return getString(columnIndex);
    else throw new SQLException("Column " + columnIndex + " does not exist.");
  }

  @Override
  public boolean getBoolean(String columnLabel) throws SQLException {
    int columnIndex = columnNames.indexOf(columnLabel) + 1;
    if (columnIndex > 0) return getBoolean(columnIndex);
    else throw new SQLException("Column " + columnIndex + " does not exist.");
  }

  @Override
  public byte getByte(String columnLabel) throws SQLException {
    int columnIndex = columnNames.indexOf(columnLabel) + 1;
    if (columnIndex > 0) return getByte(columnIndex);
    else throw new SQLException("Column " + columnIndex + " does not exist.");
  }

  @Override
  public short getShort(String columnLabel) throws SQLException {
    int columnIndex = columnNames.indexOf(columnLabel) + 1;
    if (columnIndex > 0) return getShort(columnIndex);
    else throw new SQLException("Column " + columnIndex + " does not exist.");
  }

  @Override
  public int getInt(String columnLabel) throws SQLException {
    int columnIndex = columnNames.indexOf(columnLabel) + 1;
    if (columnIndex > 0) return getInt(columnIndex);
    else throw new SQLException("Column " + columnIndex + " does not exist.");
  }

  @Override
  public long getLong(String columnLabel) throws SQLException {
    int columnIndex = columnNames.indexOf(columnLabel) + 1;
    if (columnIndex > 0) return getLong(columnIndex);
    else throw new SQLException("Column " + columnIndex + " does not exist.");
  }

  @Override
  public float getFloat(String columnLabel) throws SQLException {
    int columnIndex = columnNames.indexOf(columnLabel) + 1;
    if (columnIndex > 0) return getFloat(columnIndex);
    else throw new SQLException("Column " + columnIndex + " does not exist.");
  }

  @Override
  public double getDouble(String columnLabel) throws SQLException {
    int columnIndex = columnNames.indexOf(columnLabel) + 1;
    if (columnIndex > 0) return getDouble(columnIndex);
    else throw new SQLException("Column " + columnIndex + " does not exist.");
  }

  @Override
  public BigDecimal getBigDecimal(String columnLabel, int scale) throws SQLException {
    return getBigDecimal(columnLabel).setScale(scale);
  }

  @Override
  public byte[] getBytes(String columnLabel) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Date getDate(String columnLabel) throws SQLException {
    int columnIndex = columnNames.indexOf(columnLabel) + 1;
    if (columnIndex > 0) return getDate(columnIndex);
    else throw new SQLException("Column " + columnIndex + " does not exist.");
  }

  @Override
  public Time getTime(String columnLabel) throws SQLException {
    int columnIndex = columnNames.indexOf(columnLabel) + 1;
    if (columnIndex > 0) return getTime(columnIndex);
    else throw new SQLException("Column " + columnIndex + " does not exist.");
  }

  @Override
  public Timestamp getTimestamp(String columnLabel) throws SQLException {
    int columnIndex = columnNames.indexOf(columnLabel) + 1;
    if (columnIndex > 0) return getTimestamp(columnIndex);
    else throw new SQLException("Column " + columnIndex + " does not exist.");
  }

  @Override
  public InputStream getAsciiStream(String columnLabel) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public InputStream getUnicodeStream(String columnLabel) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public InputStream getBinaryStream(String columnLabel) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public SQLWarning getWarnings() {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void clearWarnings() {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String getCursorName() {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public ResultSetMetaData getMetaData() {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Object getObject(int columnIndex) throws SQLException {
    int internalColumnIndex = columnIndex - 1;

    if (columnClasses.size() > internalColumnIndex) {
      return value = rowData.get(r)[internalColumnIndex];

    } else throw new SQLException("Column " + columnIndex + " does not exist.");
  }

  @Override
  public Object getObject(String columnLabel) throws SQLException {
    int columnIndex = columnNames.indexOf(columnLabel) + 1;
    if (columnIndex > 0) return getObject(columnIndex);
    else throw new SQLException("Column " + columnIndex + " does not exist.");
  }

  @Override
  public int findColumn(String columnLabel) throws SQLException {
    int columnIndex = columnNames.indexOf(columnLabel) + 1;
    if (columnIndex > 0) return columnIndex;
    else throw new SQLException("Column " + columnIndex + " does not exist.");
  }

  @Override
  public Reader getCharacterStream(int columnIndex) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Reader getCharacterStream(String columnLabel) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public BigDecimal getBigDecimal(int columnIndex) throws SQLException {
    int internalColumnIndex = columnIndex - 1;

    if (columnClasses.size() > internalColumnIndex) {
      Class clazz = columnClasses.get(internalColumnIndex);
      if (clazz.equals(BigDecimal.class))
        return (BigDecimal) (value = rowData.get(r)[internalColumnIndex]);
      else throw new SQLException("Column " + columnIndex + " is not a BigDecimal.");

    } else throw new SQLException("Column " + columnIndex + " does not exist.");
  }

  @Override
  public BigDecimal getBigDecimal(String columnLabel) throws SQLException {
    int columnIndex = columnNames.indexOf(columnLabel) + 1;
    if (columnIndex > 0) return getBigDecimal(columnIndex);
    else throw new SQLException("Column " + columnIndex + " does not exist.");
  }

  @Override
  public boolean isBeforeFirst() {
    return r < 0;
  }

  @Override
  public boolean isAfterLast() {
    return r >= rowData.size();
  }

  @Override
  public boolean isFirst() {
    return r == 0;
  }

  @Override
  public boolean isLast() {
    return r == rowData.size() - 1;
  }

  @Override
  public void beforeFirst() {
    r = -1;
  }

  @Override
  public void afterLast() {
    r = rowData.size();
  }

  @Override
  public boolean first() {
    r = 0;
    return true;
  }

  @Override
  public boolean last() {
    r = rowData.size() - 1;
    return true;
  }

  @Override
  public int getRow() {
    return r;
  }

  @Override
  public boolean absolute(int row) {
    r = row;
    return r >= 0 && r < rowData.size();
  }

  @Override
  public boolean relative(int rows) {
    r += rows;
    return r >= 0 && r < rowData.size();
  }

  @Override
  public boolean previous() {
    r--;
    return r >= 0 && r < rowData.size();
  }

  @Override
  public void setFetchDirection(int direction) {}

  @Override
  public int getFetchDirection() {
    return ResultSet.FETCH_UNKNOWN;
  }

  @Override
  public void setFetchSize(int rows) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public int getFetchSize() {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public int getType() {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public int getConcurrency() {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public boolean rowUpdated() {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public boolean rowInserted() {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public boolean rowDeleted() {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateNull(int columnIndex) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateBoolean(int columnIndex, boolean x) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateByte(int columnIndex, byte x) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateShort(int columnIndex, short x) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateInt(int columnIndex, int x) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateLong(int columnIndex, long x) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateFloat(int columnIndex, float x) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateDouble(int columnIndex, double x) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateBigDecimal(int columnIndex, BigDecimal x) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateString(int columnIndex, String x) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateBytes(int columnIndex, byte[] x) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateDate(int columnIndex, Date x) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateTime(int columnIndex, Time x) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateTimestamp(int columnIndex, Timestamp x) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateAsciiStream(int columnIndex, InputStream x, int length) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateBinaryStream(int columnIndex, InputStream x, int length) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateCharacterStream(int columnIndex, Reader x, int length) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateObject(int columnIndex, Object x, int scaleOrLength) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateObject(int columnIndex, Object x) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateNull(String columnLabel) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateBoolean(String columnLabel, boolean x) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateByte(String columnLabel, byte x) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateShort(String columnLabel, short x) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateInt(String columnLabel, int x) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateLong(String columnLabel, long x) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateFloat(String columnLabel, float x) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateDouble(String columnLabel, double x) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateBigDecimal(String columnLabel, BigDecimal x) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateString(String columnLabel, String x) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateBytes(String columnLabel, byte[] x) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateDate(String columnLabel, Date x) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateTime(String columnLabel, Time x) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateTimestamp(String columnLabel, Timestamp x) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateAsciiStream(String columnLabel, InputStream x, int length) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateBinaryStream(String columnLabel, InputStream x, int length) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateCharacterStream(String columnLabel, Reader reader, int length) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateObject(String columnLabel, Object x, int scaleOrLength) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateObject(String columnLabel, Object x) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void insertRow() {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateRow() {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void deleteRow() {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void refreshRow() {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void cancelRowUpdates() {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void moveToInsertRow() {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void moveToCurrentRow() {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Statement getStatement() {
    return null;
  }

  @Override
  public Object getObject(int columnIndex, Map<String, Class<?>> map) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Ref getRef(int columnIndex) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Blob getBlob(int columnIndex) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Clob getClob(int columnIndex) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Array getArray(int columnIndex) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Object getObject(String columnLabel, Map<String, Class<?>> map) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Ref getRef(String columnLabel) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Blob getBlob(String columnLabel) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Clob getClob(String columnLabel) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Array getArray(String columnLabel) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Date getDate(int columnIndex, Calendar cal) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Date getDate(String columnLabel, Calendar cal) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Time getTime(int columnIndex, Calendar cal) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Time getTime(String columnLabel, Calendar cal) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Timestamp getTimestamp(int columnIndex, Calendar cal) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Timestamp getTimestamp(String columnLabel, Calendar cal) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public URL getURL(int columnIndex) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public URL getURL(String columnLabel) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateRef(int columnIndex, Ref x) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateRef(String columnLabel, Ref x) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateBlob(int columnIndex, Blob x) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateBlob(String columnLabel, Blob x) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateClob(int columnIndex, Clob x) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateClob(String columnLabel, Clob x) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateArray(int columnIndex, Array x) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateArray(String columnLabel, Array x) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public RowId getRowId(int columnIndex) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public RowId getRowId(String columnLabel) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateRowId(int columnIndex, RowId x) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateRowId(String columnLabel, RowId x) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public int getHoldability() {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public boolean isClosed() {
    return isClosed;
  }

  @Override
  public void updateNString(int columnIndex, String nString) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateNString(String columnLabel, String nString) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateNClob(int columnIndex, NClob nClob) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateNClob(String columnLabel, NClob nClob) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public NClob getNClob(int columnIndex) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public NClob getNClob(String columnLabel) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public SQLXML getSQLXML(int columnIndex) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public SQLXML getSQLXML(String columnLabel) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateSQLXML(int columnIndex, SQLXML xmlObject) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateSQLXML(String columnLabel, SQLXML xmlObject) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String getNString(int columnIndex) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String getNString(String columnLabel) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Reader getNCharacterStream(int columnIndex) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Reader getNCharacterStream(String columnLabel) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateNCharacterStream(int columnIndex, Reader x, long length) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateNCharacterStream(String columnLabel, Reader reader, long length) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateAsciiStream(int columnIndex, InputStream x, long length) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateBinaryStream(int columnIndex, InputStream x, long length) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateCharacterStream(int columnIndex, Reader x, long length) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateAsciiStream(String columnLabel, InputStream x, long length) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateBinaryStream(String columnLabel, InputStream x, long length) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateCharacterStream(String columnLabel, Reader reader, long length) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateBlob(int columnIndex, InputStream inputStream, long length) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateBlob(String columnLabel, InputStream inputStream, long length) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateClob(int columnIndex, Reader reader, long length) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateClob(String columnLabel, Reader reader, long length) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateNClob(int columnIndex, Reader reader, long length) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateNClob(String columnLabel, Reader reader, long length) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateNCharacterStream(int columnIndex, Reader x) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateNCharacterStream(String columnLabel, Reader reader) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateAsciiStream(int columnIndex, InputStream x) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateBinaryStream(int columnIndex, InputStream x) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateCharacterStream(int columnIndex, Reader x) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateAsciiStream(String columnLabel, InputStream x) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateBinaryStream(String columnLabel, InputStream x) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateCharacterStream(String columnLabel, Reader reader) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateBlob(int columnIndex, InputStream inputStream) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateBlob(String columnLabel, InputStream inputStream) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateClob(int columnIndex, Reader reader) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateClob(String columnLabel, Reader reader) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateNClob(int columnIndex, Reader reader) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void updateNClob(String columnLabel, Reader reader) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public <T> T getObject(int columnIndex, Class<T> type) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public <T> T getObject(String columnLabel, Class<T> type) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public <T> T unwrap(Class<T> iface) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public boolean isWrapperFor(Class<?> iface) {
    throw new UnsupportedOperationException(
        "Not supported yet."); // To change body of generated methods, choose Tools | Templates.
  }
}
