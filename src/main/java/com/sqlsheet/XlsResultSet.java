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
package com.sqlsheet;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;

import org.apache.poi.ss.usermodel.*;

/**
 * SqlSheet implementation of java.sql.ResultSet.
 *
 * @author <a href='http://www.pcal.net'>pcal</a>
 * @author <a href='http://code.google.com/p/sqlsheet'>sqlsheet</a>
 */
public class XlsResultSet implements ResultSet {

  private static final MathContext CTX_NN_15_EVEN = new MathContext(15, RoundingMode.HALF_EVEN);

  private static final double BAD_DOUBLE = 0;
  protected Statement statement;
  private Workbook workbook;
  private FormulaEvaluator evaluator;
  private Sheet sheet;
  private XlsResultSetMetaData metadata;
  private int firstSheetRowOffset = 0;
  private int firstSheetColOffset = 0;
  private int cursorSheetRow;
  private CellStyle dateStyle = null;
  private DataFormatter formatter;
  private boolean isClosed;
  private boolean wasNull;

  public XlsResultSet(Workbook wb, Sheet s, int firstSheetRowOffset, int firstSheetColOffset)
      throws SQLException {
    if (s == null) throw new IllegalArgumentException("null sheet");
    if (wb == null) throw new IllegalArgumentException("null workbook");
    formatter = new DataFormatter();
    workbook = wb;
    sheet = s;

    evaluator = wb.getCreationHelper().createFormulaEvaluator();

    this.firstSheetRowOffset = firstSheetRowOffset;
    this.firstSheetColOffset = firstSheetColOffset;

    cursorSheetRow = this.firstSheetRowOffset - 1;
    metadata = new XlsResultSetMetaData(s, this, firstSheetRowOffset, firstSheetColOffset);
    // set the default date cell format
    dateStyle = workbook.createCellStyle();
    dateStyle.setDataFormat(workbook.createDataFormat().getFormat("yyyy-mm-dd"));
  }

  private static SQLException wrapped(Throwable t) {
    SQLException out = new SQLException(t.getMessage());
    out.initCause(t);
    return out;
  }

  @Override
  public ResultSetMetaData getMetaData() throws SQLException {
    return metadata;
  }

  @Override
  public boolean getBoolean(int columnIndex) throws SQLException {
    Cell cell = getCell(columnIndex);
    if (cell == null || cell.getCellType().equals(CellType.BLANK)) {
      wasNull = true;
      return false;
    } else if (cell.getCellType().equals(CellType.FORMULA)) {
      switch (evaluator.evaluateFormulaCell(cell)) {
        case BOOLEAN:
          wasNull = false;
          return cell.getBooleanCellValue();

          // @todo: maybe try to parse the Double
        case NUMERIC:
          throw new SQLException(
              "Found a formula returning a Double, when a Boolean was expected.");

          // @todo: maybe try to parse the String
        case STRING:
          throw new SQLException("Found a formula returning a String, when a Double was expected.");

        case BLANK:
          wasNull = true;
          return false;

        case ERROR:
          throw new SQLException(
              "Found a formula returning an Error, when a Double was expected.\n("
                  + cell.getErrorCellValue()
                  + ")");
      }
    } else {
      wasNull = false;
      return cell.getBooleanCellValue();
    }

    // this should never happen
    wasNull = true;
    return false;
  }

  @Override
  public boolean getBoolean(String columnLabel) throws SQLException {
    int columnIndex = getSheetColumnNamed(columnLabel) + 1;
    return getBoolean(columnIndex);
  }

  @Override
  public double getDouble(int columnIndex) throws SQLException {
    Cell cell = getCell(columnIndex);
    if (cell == null || cell.getCellType().equals(CellType.BLANK)) {
      wasNull = true;
      return 0d;
    } else if (cell.getCellType().equals(CellType.FORMULA)) {
      switch (evaluator.evaluateFormulaCell(cell)) {
          // @todo: maybe try to parse the Boolean
        case BOOLEAN:
          throw new SQLException(
              "Found a formula returning a Boolean, when a Numeric was expected.");
        case NUMERIC:
          wasNull = false;
          return cell.getNumericCellValue();

          // @todo: maybe try to parse the String
        case STRING:
          throw new SQLException(
              "Found a formula returning a String, when a Numeric was expected.");

        case BLANK:
          wasNull = true;
          return 0d;

        case ERROR:
          throw new SQLException(
              "Found a formula returning an Error, when a Double was expected.\n("
                  + cell.getErrorCellValue()
                  + ")");
      }
    } else {
      wasNull = false;
      return cell.getNumericCellValue();
    }

    // this should never happen
    wasNull = true;
    return 0d;
  }

  @Override
  public double getDouble(String columnLabel) throws SQLException {
    int columnIndex = getSheetColumnNamed(columnLabel) + 1;
    return getDouble(columnIndex);
  }

  @Override
  public byte getByte(int columnIndex) throws SQLException {
    Cell cell = getCell(columnIndex);
    if (cell == null || cell.getCellType().equals(CellType.BLANK)) {
      wasNull = true;
      return (byte) 0;
    } else if (cell.getCellType().equals(CellType.FORMULA)) {
      switch (evaluator.evaluateFormulaCell(cell)) {

          // @todo: maybe try to parse the Boolean
        case BOOLEAN:
          throw new SQLException(
              "Found a formula returning a Boolean, when a Numeric was expected.");

        case NUMERIC:
          wasNull = false;
          return Double.valueOf(cell.getNumericCellValue()).byteValue();

          // @todo: maybe try to parse the String
        case STRING:
          throw new SQLException(
              "Found a formula returning a String, when a Numeric was expected.");

        case BLANK:
          wasNull = true;
          return (byte) 0;

        case ERROR:
          throw new SQLException(
              "Found a formula returning an Error, when a Numeric was expected.\n("
                  + cell.getErrorCellValue()
                  + ")");
      }
    } else {
      wasNull = false;
      return Double.valueOf(cell.getNumericCellValue()).byteValue();
    }

    // this should never happen
    wasNull = true;
    return (byte) 0;
  }

  @Override
  public byte getByte(String columnLabel) throws SQLException {
    int columnIndex = getSheetColumnNamed(columnLabel) + 1;
    return getByte(columnIndex);
  }

  @Override
  public float getFloat(int columnIndex) throws SQLException {
    Cell cell = getCell(columnIndex);
    if (cell == null || cell.getCellType().equals(CellType.BLANK)) {
      wasNull = true;
      return 0f;
    } else if (cell.getCellType().equals(CellType.FORMULA)) {
      switch (evaluator.evaluateFormulaCell(cell)) {

          // @todo: maybe try to parse the Boolean
        case BOOLEAN:
          throw new SQLException(
              "Found a formula returning a Boolean, when a Numeric was expected.");

        case NUMERIC:
          wasNull = false;
          return Double.valueOf(cell.getNumericCellValue()).floatValue();

          // @todo: maybe try to parse the String
        case STRING:
          throw new SQLException(
              "Found a formula returning a String, when a Numeric was expected.");

        case BLANK:
          wasNull = true;
          return (float) 0;

        case ERROR:
          throw new SQLException(
              "Found a formula returning an Error, when a Numeric was expected.\n("
                  + cell.getErrorCellValue()
                  + ")");
      }
    } else {
      wasNull = false;
      return Double.valueOf(cell.getNumericCellValue()).floatValue();
    }

    // this should never happen
    wasNull = true;
    return 0f;
  }

  @Override
  public float getFloat(String columnLabel) throws SQLException {
    int columnIndex = getSheetColumnNamed(columnLabel) + 1;
    return getFloat(columnIndex);
  }

  @Override
  public int getInt(int columnIndex) throws SQLException {
    Cell cell = getCell(columnIndex);
    if (cell == null || cell.getCellType().equals(CellType.BLANK)) {
      wasNull = true;
      return 0;
    } else if (cell.getCellType().equals(CellType.FORMULA)) {
      switch (evaluator.evaluateFormulaCell(cell)) {

          // @todo: maybe try to parse the Boolean
        case BOOLEAN:
          throw new SQLException(
              "Found a formula returning a Boolean, when a Numeric was expected.");

        case NUMERIC:
          wasNull = false;
          return Double.valueOf(cell.getNumericCellValue()).intValue();

          // @todo: maybe try to parse the String
        case STRING:
          throw new SQLException(
              "Found a formula returning a String, when a Numeric was expected.");

        case BLANK:
          wasNull = true;
          return (int) 0;

        case ERROR:
          throw new SQLException(
              "Found a formula returning an Error, when a Numeric was expected.\n("
                  + cell.getErrorCellValue()
                  + ")");
      }
    } else {
      wasNull = false;
      return Double.valueOf(cell.getNumericCellValue()).intValue();
    }

    // this should never happen
    wasNull = true;
    return 0;
  }

  @Override
  public int getInt(String columnLabel) throws SQLException {
    int columnIndex = getSheetColumnNamed(columnLabel) + 1;
    return getInt(columnIndex);
  }

  @Override
  public long getLong(int columnIndex) throws SQLException {
    Cell cell = getCell(columnIndex);
    if (cell == null || cell.getCellType().equals(CellType.BLANK)) {
      wasNull = true;
      return 0;
    } else if (cell.getCellType().equals(CellType.FORMULA)) {
      switch (evaluator.evaluateFormulaCell(cell)) {

          // @todo: maybe try to parse the Boolean
        case BOOLEAN:
          throw new SQLException(
              "Found a formula returning a Boolean, when a Numeric was expected.");

        case NUMERIC:
          wasNull = false;
          return Double.valueOf(cell.getNumericCellValue()).longValue();

          // @todo: maybe try to parse the String
        case STRING:
          throw new SQLException(
              "Found a formula returning a String, when a Numeric was expected.");

        case BLANK:
          wasNull = true;
          return (long) 0;

        case ERROR:
          throw new SQLException(
              "Found a formula returning an Error, when a Numeric was expected.\n("
                  + cell.getErrorCellValue()
                  + ")");
      }
    } else {
      wasNull = false;
      return Double.valueOf(cell.getNumericCellValue()).longValue();
    }

    // this should never happen
    wasNull = true;
    return 0l;
  }

  @Override
  public long getLong(String columnLabel) throws SQLException {
    int columnIndex = getSheetColumnNamed(columnLabel) + 1;
    return getLong(columnIndex);
  }

  @Override
  public Object getObject(int columnIndex) throws SQLException {
    Cell cell = getCell(columnIndex);
    int columnType = metadata.getColumnType(columnIndex);
    try {
      if (cell == null || cell.getCellType().equals(CellType.BLANK)) {
        wasNull = true;
        return null;
      }
      switch (cell.getCellType()) {
        case BOOLEAN:
          if (columnType == Types.VARCHAR || columnType == Types.BOOLEAN) {
            wasNull = false;
            return cell.getBooleanCellValue();
          } else
            throw new RuntimeException(
                "The cell ("
                    + getCurrentRow()
                    + ","
                    + columnIndex
                    + ") is a boolean and cannot be cast to ("
                    + XlsResultSetMetaData.columnTypeNameMap.get(columnType)
                    + ".");
        case STRING:
          if (columnType == Types.VARCHAR) {
            wasNull = false;
            return cell.getStringCellValue();
          } else
            throw new RuntimeException(
                "The cell ("
                    + getCurrentRow()
                    + ","
                    + columnIndex
                    + ") is a string cell and cannot be cast to ("
                    + XlsResultSetMetaData.columnTypeNameMap.get(columnType)
                    + ".");
        case NUMERIC:
          if (DateUtil.isCellDateFormatted(cell)) {
            java.util.Date value = cell.getDateCellValue();
            wasNull = false;
            return new java.sql.Date(value.getTime());
          } else {
            wasNull = false;
            return new BigDecimal(cell.getNumericCellValue(), CTX_NN_15_EVEN).doubleValue();
          }
        case FORMULA:
          switch (evaluator.evaluateFormulaCell(cell)) {

              // @todo: maybe try to parse the Boolean
            case BOOLEAN:
              wasNull = false;
              return cell.getBooleanCellValue();

            case NUMERIC:
              wasNull = false;
              return cell.getNumericCellValue();

              // @todo: maybe try to parse the String
            case STRING:
              wasNull = false;
              return cell.getStringCellValue();

            case BLANK:
              wasNull = true;
              return null;

            case ERROR:
              throw new SQLException(
                  "Found a formula returning an Error, when a Numeric was expected.\n("
                      + cell.getErrorCellValue()
                      + ")");
          }
        default:
          wasNull = true;
          return null;
      }
    } catch (Exception e) {
      throw wrapped(e);
    }
  }

  @Override
  public Object getObject(String columnLabel) throws SQLException {
    int columnIndex = getSheetColumnNamed(columnLabel) + 1;
    return getObject(columnIndex);
  }

  @Override
  public <T> T getObject(int columnIndex, Class<T> type) throws SQLException {
    return (T) getObject(columnIndex);
  }

  @Override
  public <T> T getObject(String columnLabel, Class<T> type) throws SQLException {
    return (T) getObject(columnLabel);
  }

  // Object getNextRowObject(int jdbcColumn) throws SQLException {
  // if (sheet.getRow(cursorSheetRow + 1) != null) {
  // Cell cell = sheet.getRow(cursorSheetRow + 1).getCell((short) (jdbcColumn - 1));
  // return getObject(cell);
  // }
  // return getObject(jdbcColumn);
  // }
  @Override
  public Timestamp getTimestamp(int columnIndex) throws SQLException {
    java.util.Date date = (java.util.Date) getObject(columnIndex);
    if (date == null) {
      wasNull = true;
      return null;
    } else {
      wasNull = true;
      return new Timestamp(date.getTime());
    }
  }

  @Override
  public Timestamp getTimestamp(String columnLabel) throws SQLException {
    int columnIndex = getSheetColumnNamed(columnLabel) + 1;
    return getTimestamp(columnIndex);
  }

  @Override
  public Timestamp getTimestamp(int columnIndex, Calendar cal) throws SQLException {
    throw nyi();
  }

  @Override
  public Timestamp getTimestamp(String jdbcColumn, Calendar cal) throws SQLException {
    throw nyi();
  }

  @Override
  public short getShort(int columnIndex) throws SQLException {
    Cell cell = getCell(columnIndex);
    if (cell == null || cell.getCellType().equals(CellType.BLANK)) {
      wasNull = true;
      return 0;
    } else if (cell.getCellType().equals(CellType.FORMULA)) {
      switch (evaluator.evaluateFormulaCell(cell)) {

          // @todo: maybe try to parse the Boolean
        case BOOLEAN:
          throw new SQLException(
              "Found a formula returning a Boolean, when a Numeric was expected.");

        case NUMERIC:
          wasNull = false;
          return Double.valueOf(cell.getNumericCellValue()).shortValue();

          // @todo: maybe try to parse the String
        case STRING:
          throw new SQLException(
              "Found a formula returning a String, when a Numeric was expected.");

        case BLANK:
          wasNull = true;
          return (short) 0;

        case ERROR:
          throw new SQLException(
              "Found a formula returning an Error, when a Numeric was expected.\n("
                  + cell.getErrorCellValue()
                  + ")");
      }
    } else {
      wasNull = false;
      return Double.valueOf(cell.getNumericCellValue()).shortValue();
    }

    // this should never happen
    wasNull = true;
    return (short) 0;
  }

  @Override
  public short getShort(String columnLabel) throws SQLException {
    int columnIndex = getSheetColumnNamed(columnLabel) + 1;
    return getShort(columnIndex);
  }

  @Override
  public String getString(int columnIndex) throws SQLException {
    Cell cell = getCell(columnIndex);
    int columnType = metadata.getColumnType(columnIndex);
    try {
      if (cell == null || cell.getCellType().equals(CellType.BLANK)) {
        wasNull = true;
        return null;
      }
      switch (cell.getCellType()) {
        case BOOLEAN:
          if (columnType == Types.VARCHAR || columnType == Types.BOOLEAN) {
            wasNull = false;
            return Boolean.toString(cell.getBooleanCellValue());
          } else
            throw new RuntimeException(
                "The cell ("
                    + getCurrentRow()
                    + ","
                    + columnIndex
                    + ") is a boolean and cannot be cast to ("
                    + XlsResultSetMetaData.columnTypeNameMap.get(columnType)
                    + ".");
        case STRING:
          if (columnType == Types.VARCHAR) {
            wasNull = false;
            return cell.getStringCellValue();
          } else
            throw new RuntimeException(
                "The cell ("
                    + getCurrentRow()
                    + ","
                    + columnIndex
                    + ") is a string cell and cannot be cast to ("
                    + XlsResultSetMetaData.columnTypeNameMap.get(columnType)
                    + ".");
        case NUMERIC:
          if (DateUtil.isCellDateFormatted(cell)) {
            java.util.Date value = cell.getDateCellValue();
            wasNull = false;
            return new java.sql.Date(value.getTime()).toString();
          } else {
            wasNull = false;
            return new BigDecimal(cell.getNumericCellValue(), CTX_NN_15_EVEN).toPlainString();
          }
        case FORMULA:
          switch (evaluator.evaluateFormulaCell(cell)) {
            case BOOLEAN:
              if (columnType == Types.VARCHAR || columnType == Types.BOOLEAN) {
                wasNull = false;
                return Boolean.toString(cell.getBooleanCellValue());
              } else
                throw new RuntimeException(
                    "The cell ("
                        + getCurrentRow()
                        + ","
                        + columnIndex
                        + ") is a boolean and cannot be cast to ("
                        + XlsResultSetMetaData.columnTypeNameMap.get(columnType)
                        + ".");

            case NUMERIC:
              if (DateUtil.isCellDateFormatted(cell)) {
                java.util.Date value = cell.getDateCellValue();
                wasNull = false;
                return new java.sql.Date(value.getTime()).toString();
              } else {
                wasNull = false;
                return new BigDecimal(cell.getNumericCellValue(), CTX_NN_15_EVEN).toPlainString();
              }

            case STRING:
              if (columnType == Types.VARCHAR) {
                wasNull = false;
                return cell.getStringCellValue();
              } else
                throw new RuntimeException(
                    "The cell ("
                        + getCurrentRow()
                        + ","
                        + columnIndex
                        + ") is a string cell and cannot be cast to ("
                        + XlsResultSetMetaData.columnTypeNameMap.get(columnType)
                        + ".");

            case BLANK:
              wasNull = true;
              return null;

            case ERROR:
              throw new SQLException(
                  "Found a formula returning an Error, when a Numeric was expected.\n("
                      + cell.getErrorCellValue()
                      + ")");
          }
        default:
          wasNull = true;
          return null;
      }
    } catch (Exception e) {
      throw wrapped(e);
    }
  }

  public String getString(String columnLabel) throws SQLException {
    int columnIndex = getSheetColumnNamed(columnLabel) + 1;
    return getString(columnIndex);
  }

  public void updateBoolean(int columnIndex, boolean x) throws SQLException {
    Cell cell = getCell(columnIndex);
    if (cell != null) cell.setCellValue(x);
  }

  public void updateBoolean(String jdbcColumn, boolean x) throws SQLException {
    Cell cell = getCell(jdbcColumn);
    if (cell != null) cell.setCellValue(x);
  }

  public void updateByte(int columnIndex, byte x) throws SQLException {
    Cell cell = getCell(columnIndex);
    if (cell != null) cell.setCellValue(x);
  }

  public void updateByte(String jdbcColumn, byte x) throws SQLException {
    Cell cell = getCell(jdbcColumn);
    if (cell != null) cell.setCellValue(x);
  }

  public void updateDouble(int columnIndex, double x) throws SQLException {
    Cell cell = getCell(columnIndex);
    if (cell != null) cell.setCellValue(x);
  }

  public void updateDouble(String jdbcColumn, double x) throws SQLException {
    Cell cell = getCell(jdbcColumn);
    if (cell != null) cell.setCellValue(x);
  }

  public void updateFloat(int columnIndex, float x) throws SQLException {
    Cell cell = getCell(columnIndex);
    if (cell != null) cell.setCellValue(x);
  }

  public void updateFloat(String jdbcColumn, float x) throws SQLException {
    Cell cell = getCell(jdbcColumn);
    if (cell != null) cell.setCellValue(x);
  }

  public void updateInt(int columnIndex, int x) throws SQLException {
    Cell cell = getCell(columnIndex);
    if (cell != null) cell.setCellValue(x);
  }

  public void updateInt(String jdbcColumn, int x) throws SQLException {
    Cell cell = getCell(jdbcColumn);
    if (cell != null) cell.setCellValue(x);
  }

  public void updateLong(int columnIndex, long x) throws SQLException {
    Cell cell = getCell(columnIndex);
    if (cell != null) cell.setCellValue(x);
  }

  public void updateLong(String jdbcColumn, long x) throws SQLException {
    Cell cell = getCell(jdbcColumn);
    if (cell != null) cell.setCellValue(x);
  }

  public void updateNull(int columnIndex) throws SQLException {
    Cell cell = getCell(columnIndex);
    if (cell != null) cell.setCellValue((String) null); // REVIEW
  }

  public void updateNull(String jdbcColumn) throws SQLException {
    Cell cell = getCell(jdbcColumn);
    if (cell != null) cell.setCellValue((String) null); // REVIEW
  }

  public void updateObject(int columnIndex, Object x) throws SQLException {
    updateObject(findOrCreateCell(columnIndex), x);
  }

  public void updateObject(String jdbcColumn, Object x) throws SQLException {
    updateObject(findOrCreateCell(jdbcColumn), x);
  }

  public void updateShort(int columnIndex, short x) throws SQLException {
    Cell cell = findOrCreateCell(columnIndex);
    if (cell != null) cell.setCellValue(x);
  }

  public void updateShort(String jdbcColumn, short x) throws SQLException {
    Cell cell = findOrCreateCell(jdbcColumn);
    if (cell != null) cell.setCellValue(x);
  }

  public void updateString(int columnIndex, String x) throws SQLException {
    Cell cell = findOrCreateCell(columnIndex);
    if (cell != null) cell.setCellValue(x);
  }

  public void updateString(String jdbcColumn, String x) throws SQLException {
    Cell cell = findOrCreateCell(jdbcColumn);
    if (cell != null) cell.setCellValue(x);
  }

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

  public void setFetchDirection(int direction) throws SQLException {
    throw nyi();
  }

  public int getFetchSize() throws SQLException {
    return 0;
  }

  public void setFetchSize(int rows) throws SQLException {
    // better just ignore it if configuration is not supported
    // throw nyi();
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
    return !isAfterLast() && (sheet.getRow(cursorSheetRow) != null);
  }

  public boolean previous() throws SQLException {
    if (isBeforeFirst()) return false;
    cursorSheetRow--;
    return isBeforeFirst();
  }

  public void moveToInsertRow() throws SQLException {
    for (; true; cursorSheetRow++) {
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

  private void updateObject(Cell cell, Object x) throws SQLException {
    if (x instanceof String) cell.setCellValue((String) x);
    else if (x instanceof char[]) cell.setCellValue(new String((char[]) x));
    else if (x instanceof Double)
      if (x.equals(Double.NEGATIVE_INFINITY)
          || x.equals(Double.POSITIVE_INFINITY)
          || x.equals(Double.NaN)) cell.setCellValue(BAD_DOUBLE);
      else cell.setCellValue((Double) x);
    else if (x instanceof Number)
      cell.setCellValue(((Number) x).doubleValue()); // } else if (x instanceof java.sql.Date) {
    // cell.setCellValue(new java.util.Date(((java.sql.Date)x).getTime()));
    // if (dateStyle != null) cell.setCellStyle(dateStyle);
    else if (x instanceof java.util.Date) {
      cell.setCellValue(DateUtil.getExcelDate((java.util.Date) x));
      if (dateStyle != null) cell.setCellStyle(dateStyle);
    } else if (x instanceof Boolean) cell.setCellValue((Boolean) x);
    else if (x == null) cell.setCellValue((String) null);
    else
      throw new SQLException(
          "Unknown value type for ExcelResultSet.updateObject: "
              + x
              + " ("
              + x.getClass().getName()
              + ")");
  }

  private Row getCurrentRow() {
    if (sheet.getRow(cursorSheetRow) == null) sheet.createRow(cursorSheetRow);
    return sheet.getRow(cursorSheetRow);
  }

  private Cell findOrCreateCell(int jdbcColumn) {
    Cell cell = getCell(jdbcColumn);
    if (cell == null) cell = getCurrentRow().createCell((short) (jdbcColumn - 1));
    return cell;
  }

  private Cell findOrCreateCell(String jdbcColumn) throws SQLException {
    int col = getSheetColumnNamed(jdbcColumn);
    return findOrCreateCell(col);
  }

  /**
   * Protected because used also in the resultset metadata to scan the column type
   *
   * @param columnIndex The index of the column, starting at 1.
   * @return the Cell
   */
  protected Cell getCell(int columnIndex) {
    Row row = sheet.getRow(cursorSheetRow);

    return row != null ? row.getCell((short) (columnIndex + firstSheetColOffset - 1)) : null;
  }

  private Cell getCell(String columnLabel) throws SQLException {
    Row row = sheet.getRow(cursorSheetRow);

    return row != null ? row.getCell(getSheetColumnNamed(columnLabel) + firstSheetColOffset) : null;
  }

  private short getSheetColumnNamed(String name) throws SQLException {
    int count = metadata.getColumnCount();
    for (short i = 0; i < count; i++) {
      String col = metadata.getColumnName(i + 1);
      if (col.equalsIgnoreCase(name)) return i;
    }
    throw new SQLException(
        "Column "
            + name
            + " not found. Avaliable Columns are "
            + Arrays.deepToString(
                metadata.columnNames.toArray(new String[metadata.columnNames.size()])));
  }

  private IllegalStateException nyi() {
    return new IllegalStateException("NYI");
  }

  public void cancelRowUpdates() throws SQLException {
    throw nyi();
  }

  public void clearWarnings() throws SQLException {
    throw nyi();
  }

  @Override
  public void close() throws SQLException {
    isClosed = true;

    // help the GC by nulling all objects
    workbook = null;
    evaluator = null;
    sheet = null;
    metadata = null;
    dateStyle = null;
    formatter = null;

    if (statement != null & !statement.isClosed() && statement.isCloseOnCompletion())
      statement.close();

    statement = null;
  }

  public void deleteRow() throws SQLException {
    throw nyi();
  }

  @Override
  public int findColumn(String columnLabel) throws SQLException {
    return getSheetColumnNamed(columnLabel);
  }

  public Array getArray(int i) throws SQLException {
    throw nyi();
  }

  public Array getArray(String colName) throws SQLException {
    throw nyi();
  }

  @Override
  public InputStream getAsciiStream(int columnIndex) throws SQLException {
    throw nyi();
  }

  public InputStream getAsciiStream(String jdbcColumn) throws SQLException {
    throw nyi();
  }

  @Override
  public BigDecimal getBigDecimal(int columnIndex) throws SQLException {
    Cell cell = getCell(columnIndex);
    if (cell == null || cell.getCellType().equals(CellType.BLANK)) {
      wasNull = true;
      return null;
    } else if (cell.getCellType().equals(CellType.FORMULA)) {
      switch (evaluator.evaluateFormulaCell(cell)) {

          // @todo: maybe try to parse the Boolean
        case BOOLEAN:
          throw new SQLException(
              "Found a formula returning a Boolean, when a Numeric was expected.");

        case NUMERIC:
          wasNull = false;
          return BigDecimal.valueOf(cell.getNumericCellValue());

          // @todo: maybe try to parse the String
        case STRING:
          throw new SQLException(
              "Found a formula returning a String, when a Numeric was expected.");

        case BLANK:
          wasNull = true;
          return null;

        case ERROR:
          throw new SQLException(
              "Found a formula returning an Error, when a Numeric was expected.\n("
                  + cell.getErrorCellValue()
                  + ")");
      }
    } else {
      wasNull = false;
      return BigDecimal.valueOf(cell.getNumericCellValue());
    }

    // this should never happen
    wasNull = true;
    return null;
  }

  @Override
  public BigDecimal getBigDecimal(String columnLabel) throws SQLException {
    int columnIndex = getSheetColumnNamed(columnLabel) + 1;
    return getBigDecimal(columnIndex);
  }

  @Override
  public BigDecimal getBigDecimal(int columnIndex, int scale) throws SQLException {
    BigDecimal d = getBigDecimal(columnIndex);
    return d != null ? d.setScale(scale) : null;
  }

  @Override
  public BigDecimal getBigDecimal(String columnLabel, int scale) throws SQLException {
    BigDecimal d = getBigDecimal(columnLabel);
    return d != null ? d.setScale(scale) : null;
  }

  @Override
  public InputStream getBinaryStream(int columnIndex) throws SQLException {
    throw nyi();
  }

  public InputStream getBinaryStream(String jdbcColumn) throws SQLException {
    throw nyi();
  }

  public Blob getBlob(int columnIndex) throws SQLException {
    throw nyi();
  }

  public Blob getBlob(String colName) throws SQLException {
    throw nyi();
  }

  @Override
  public byte[] getBytes(int columnIndex) throws SQLException {
    throw nyi();
  }

  public byte[] getBytes(String jdbcColumn) throws SQLException {
    throw nyi();
  }

  @Override
  public Reader getCharacterStream(int columnIndex) throws SQLException {
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

  @Override
  public Date getDate(int columnIndex) throws SQLException {
    Cell cell = getCell(columnIndex);
    if (cell == null || cell.getCellType().equals(CellType.BLANK)) {
      wasNull = true;
      return null;
    } else if (cell.getCellType().equals(CellType.FORMULA)) {
      switch (evaluator.evaluateFormulaCell(cell)) {
        case BOOLEAN:
          throw new SQLException(
              "Found a formula returning a Boolean, when a Numeric was expected.");

        case NUMERIC:
          wasNull = false;
          return new Date(cell.getDateCellValue().getTime());

          // @todo: maybe try to parse the String
        case STRING:
          throw new SQLException(
              "Found a formula returning a String, when a Numeric was expected.");

        case BLANK:
          wasNull = true;
          return null;

        case ERROR:
          throw new SQLException(
              "Found a formula returning an Error, when a Numeric was expected.\n("
                  + cell.getErrorCellValue()
                  + ")");
      }
    } else {
      wasNull = false;
      return new Date(cell.getDateCellValue().getTime());
    }

    wasNull = true;
    return null;
  }

  @Override
  public Date getDate(String columnLabel) throws SQLException {
    int columnIndex = getSheetColumnNamed(columnLabel) + 1;
    return getDate(columnIndex);
  }

  @Override
  public Date getDate(int columnIndex, Calendar cal) throws SQLException {
    Date date = getDate(columnIndex);
    if (date == null) return null;
    else {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      calendar.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY));
      calendar.set(Calendar.MINUTE, cal.get(Calendar.MINUTE));
      calendar.set(Calendar.SECOND, cal.get(Calendar.SECOND));
      calendar.set(Calendar.MILLISECOND, cal.get(Calendar.MILLISECOND));
      return new Date(calendar.getTime().getTime());
    }
  }

  @Override
  public Date getDate(String columnLabel, Calendar cal) throws SQLException {
    int columnIndex = getSheetColumnNamed(columnLabel) + 1;
    return getDate(columnIndex, cal);
  }

  @Override
  public Object getObject(int columnIndex, Map<String, Class<?>> map) throws SQLException {
    throw nyi();
  }

  @Override
  public Object getObject(String colName, Map<String, Class<?>> map) throws SQLException {
    throw nyi();
  }

  public Ref getRef(int columnIndex) throws SQLException {
    throw nyi();
  }

  public Ref getRef(String colName) throws SQLException {
    throw nyi();
  }

  public Statement getStatement() throws SQLException {
    return statement;
  }

  public Time getTime(int columnIndex) throws SQLException {
    throw nyi();
  }

  public Time getTime(String jdbcColumn) throws SQLException {
    throw nyi();
  }

  public Time getTime(int columnIndex, Calendar cal) throws SQLException {
    throw nyi();
  }

  public Time getTime(String jdbcColumn, Calendar cal) throws SQLException {
    throw nyi();
  }

  public URL getURL(int columnIndex) throws SQLException {
    throw nyi();
  }

  public URL getURL(String jdbcColumn) throws SQLException {
    throw nyi();
  }

  public InputStream getUnicodeStream(int columnIndex) throws SQLException {
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

  public void updateArray(int columnIndex, Array x) throws SQLException {
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

  @Override
  public boolean isClosed() throws SQLException {
    return isClosed;
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

  public void updateNCharacterStream(String columnLabel, Reader reader, long length)
      throws SQLException {
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

  public void updateAsciiStream(String columnLabel, InputStream x, long length)
      throws SQLException {
    throw nyi();
  }

  public void updateBinaryStream(String columnLabel, InputStream x, long length)
      throws SQLException {
    throw nyi();
  }

  public void updateCharacterStream(String columnLabel, Reader reader, long length)
      throws SQLException {
    throw nyi();
  }

  public void updateBlob(int columnIndex, InputStream inputStream, long length)
      throws SQLException {
    throw nyi();
  }

  public void updateBlob(String columnLabel, InputStream inputStream, long length)
      throws SQLException {
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

  public void updateAsciiStream(int jdbcColumn, InputStream x, int length) throws SQLException {
    throw nyi();
  }

  public void updateAsciiStream(String jdbcColumn, InputStream x, int length) throws SQLException {
    throw nyi();
  }

  public void updateBigDecimal(int jdbcColumn, BigDecimal x) throws SQLException {
    throw nyi();
  }

  public void updateBigDecimal(String jdbcColumn, BigDecimal x) throws SQLException {
    throw nyi();
  }

  public void updateBinaryStream(int jdbcColumn, InputStream x, int length) throws SQLException {
    throw nyi();
  }

  public void updateBinaryStream(String jdbcColumn, InputStream x, int length) throws SQLException {
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

  public void updateCharacterStream(int jdbcColumn, Reader x, int length) throws SQLException {
    throw nyi();
  }

  public void updateCharacterStream(String jdbcColumn, Reader reader, int length)
      throws SQLException {
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

  public void updateObject(int jdbcColumn, Object x, int scale) throws SQLException {
    throw nyi();
  }

  public void updateObject(String jdbcColumn, Object x, int scale) throws SQLException {
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

  public void updateTimestamp(int jdbcColumn, Timestamp x) throws SQLException {
    throw nyi();
  }

  public void updateTimestamp(String jdbcColumn, Timestamp x) throws SQLException {
    throw nyi();
  }

  @Override
  public boolean wasNull() throws SQLException {
    return wasNull;
  }

  public <T> T unwrap(Class<T> iface) throws SQLException {
    throw nyi();
  }

  public boolean isWrapperFor(Class<?> iface) throws SQLException {
    throw nyi();
  }
}
