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

import org.apache.poi.ss.usermodel.*;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SqlSheet implementation of java.sql.ResultSetMetaData.
 *
 * @author <a href='http://www.pcal.net'>pcal</a>
 * @author <a href='http://code.google.com/p/sqlsheet'>sqlsheet</a>
 */
public class XlsResultSetMetaData implements ResultSetMetaData {

  /** A map between the code ID and the type name */
  static Map<Integer, String> columnTypeNameMap = new HashMap<Integer, String>();
  /** A map between the code ID and the type class */
  static Map<Integer, String> columnTypeClassMap = new HashMap<Integer, String>();

  static {
    columnTypeNameMap.put(Types.VARCHAR, "VARCHAR");
    columnTypeNameMap.put(Types.DOUBLE, "DOUBLE");
    columnTypeNameMap.put(Types.DATE, "DATE");

    columnTypeClassMap.put(Types.VARCHAR, "java.lang.String.class");
    columnTypeClassMap.put(Types.DOUBLE, "java.lang.Double.class");
    columnTypeClassMap.put(Types.DATE, "java.sql.Date.class");
  }

  static {
  }

  private final DataFormatter formatter;
  /** A map to get consistently the same data type */
  Map<Integer, Integer> columnTypeMap = new HashMap<Integer, Integer>();

  protected List<String> columnNames;
  private XlsResultSet resultset;

  public XlsResultSetMetaData(
      Sheet sheet, XlsResultSet resultset, int firstSheetRowOffset, int firstSheetColOffset)
      throws SQLException {

    if (sheet == null) throw new IllegalArgumentException();
    this.resultset = resultset;
    Row row = sheet.getRow(firstSheetRowOffset - 1);
    if (row == null) {
      throw new SQLException("No header row in sheet");
    }
    formatter = new DataFormatter();
    columnNames = new ArrayList<String>();
    for (short c = (short) firstSheetColOffset; c < row.getLastCellNum(); c++) {
      Cell cell = row.getCell(c);
      String columnName = formatter.formatCellValue(cell);

      // Is it unique in the column name set
      int suffix;
      while (columnNames.contains(columnName)) {
        suffix = 1;
        columnName += "_" + suffix;
      }

      columnNames.add(columnName);
    }

    // Data Type profiling on the whole excel file
    int currentRowNumber = resultset.getRow();

    // A double map to back the relation between the column Id and the count of type
    Map<Integer, Map<Integer, Integer>> columnTypeScan =
        new HashMap<Integer, Map<Integer, Integer>>();
    while (resultset.next()) {
      int typeCode;
      for (int columnId = 1; columnId <= getColumnCount(); columnId++) {

        Cell cell = resultset.getCell(columnId);
        if (cell != null) {

          CellType excelCellType = cell.getCellType();
          switch (excelCellType) {
            case BOOLEAN:
              typeCode = Types.BOOLEAN;
              break;
            case STRING:
              typeCode = Types.VARCHAR;
              break;
            case NUMERIC:
              if (DateUtil.isCellDateFormatted(cell)) {
                typeCode = Types.DATE;
              } else {
                typeCode = Types.DOUBLE;
              }
              break;
            case BLANK:
              typeCode = Types.NULL;
              break;
            case FORMULA:
              try {
                cell.getStringCellValue();
                typeCode = Types.VARCHAR;
              } catch (Exception e) {
                cell.getNumericCellValue();
                typeCode = Types.DOUBLE;
              }
              break;
            case ERROR:
              throw new RuntimeException(
                  "The ExcelType ( ERROR ) is not supported - Cell ("
                      + resultset.getRow()
                      + ","
                      + columnId
                      + ")");

            default:
              throw new RuntimeException(
                  "The ExcelType ("
                      + excelCellType
                      + ") is not supported - Cell ("
                      + resultset.getRow()
                      + ","
                      + columnId
                      + ")");
          }
        } else {
          typeCode = Types.NULL;
        }
        Map<Integer, Integer> columnIdTypeMap = columnTypeScan.get(columnId);
        if (columnIdTypeMap == null) {
          columnIdTypeMap = new HashMap<Integer, Integer>();
          columnIdTypeMap.put(typeCode, 1);
          columnTypeScan.put(columnId, columnIdTypeMap);
        } else {
          Integer columnIdType = columnIdTypeMap.get(typeCode);
          if (columnIdType == null) {
            columnIdTypeMap.put(typeCode, 1);
          } else {
            int count = columnIdTypeMap.get(typeCode) + 1;
            columnIdTypeMap.put(typeCode, count);
          }
        }
      }
      // Retrieve only one type
      for (Integer columnId : columnTypeScan.keySet()) {

        Integer numberOfVarchar = 0;
        Integer numberOfDouble = 0;
        Integer numberOfDate = 0;

        for (Map.Entry<Integer, Integer> columnIdTypeMap :
            columnTypeScan.get(columnId).entrySet()) {
          if (columnIdTypeMap.getKey() == Types.VARCHAR) {
            numberOfVarchar = columnIdTypeMap.getValue();
          } else if (columnIdTypeMap.getKey() == Types.DOUBLE) {
            numberOfDouble = columnIdTypeMap.getValue();
          } else if (columnIdTypeMap.getKey() == Types.DATE) {
            numberOfDate = columnIdTypeMap.getValue();
          }
        }
        Integer finalColumnType = null;
        if (numberOfVarchar != 0) {
          finalColumnType = Types.VARCHAR;
        } else {
          if (numberOfDouble != 0 && numberOfDate == 0) {
            finalColumnType = Types.DOUBLE;
          }
          if (numberOfDouble == 0 && numberOfDate != 0) {
            finalColumnType = Types.DATE;
          }
        }
        if (finalColumnType == null) {
          finalColumnType = Types.VARCHAR;
        }
        columnTypeMap.put(columnId, finalColumnType);
      }
    }

    // Go back to the current row
    resultset.absolute(currentRowNumber);
  }

  public int getColumnCount() {
    return columnNames.size();
  }

  public String getColumnLabel(int jdbcCol) {
    return columnNames.get(jdbcCol - 1);
  }

  public String getColumnName(int jdbcCol) {
    return columnNames.get(jdbcCol - 1);
  }

  public String getCatalogName(int arg0) {
    return null;
  }

  public String getColumnClassName(int jdbcColumn) {
    return columnTypeClassMap.get(getColumnType(jdbcColumn));
  }

  public int getColumnDisplaySize(int arg0) {
    return 0;
  }

  public int getColumnType(int jdbcColumn) {

    return columnTypeMap.get(jdbcColumn);
  }

  public String getColumnTypeName(int jdbcColumn) {

    return columnTypeNameMap.get(getColumnType(jdbcColumn));
  }

  public int getPrecision(int arg0) {
    return 0;
  }

  public int getScale(int arg0) {
    return 0;
  }

  public String getSchemaName(int arg0) {
    return null;
  }

  public String getTableName(int arg0) {
    return null;
  }

  public boolean isAutoIncrement(int arg0) {
    return false;
  }

  public boolean isCaseSensitive(int arg0) {
    return false;
  }

  public boolean isCurrency(int arg0) {
    return false;
  }

  public boolean isDefinitelyWritable(int arg0) {
    return false;
  }

  public int isNullable(int arg0) {
    return 0;
  }

  public boolean isReadOnly(int arg0) {
    return false;
  }

  public boolean isSearchable(int arg0) {
    return false;
  }

  public boolean isSigned(int arg0) {
    return false;
  }

  public boolean isWritable(int arg0) {
    return false;
  }

  public boolean isWrapperFor(Class<?> iface) {
    return false;
  }

  public <T> T unwrap(Class<T> iface) {
    return null;
  }
}
