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

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;


/**
 * SqlSheet implementation of java.sql.ResultSetMetaData.
 *
 * @author <a href='http://www.pcal.net'>pcal</a>
 * @author <a href='http://code.google.com/p/sqlsheet'>sqlsheet</a>
 */
public class XlsResultSetMetaData implements ResultSetMetaData {

    private List<String> columnNames;
    private final DataFormatter formatter;
    private XlsResultSet resultset;

    /**
     * A map to get consistently the same data type
     */
    Map<Integer, Integer> columnTypeMap = new HashMap<Integer, Integer>();

    public XlsResultSetMetaData(Sheet sheet, XlsResultSet resultset, int firstSheetRowOffset) throws SQLException {
        if (sheet == null) throw new IllegalArgumentException();
        this.resultset = resultset;
        Row row = sheet.getRow(firstSheetRowOffset - 1);
        if (row == null) {
            throw new SQLException("No header row in sheet");
        }
        formatter = new DataFormatter();
        columnNames = new ArrayList<String>();
        for (short c = 0; c < row.getLastCellNum(); c++) {
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

    public String getCatalogName(int arg0) throws SQLException {
        return null;
    }

    public String getColumnClassName(int jdbcColumn) throws SQLException {
        return resultset.getNextRowObject(jdbcColumn).getClass().getName();
    }

    public int getColumnDisplaySize(int arg0) {
        return 0;
    }

    public int getColumnType(int jdbcColumn) throws SQLException {

        Integer typeCode = columnTypeMap.get(jdbcColumn);
        if (typeCode != null) {
            return typeCode;
        } else {
            try {
                if (resultset.getNextRowObject(jdbcColumn).getClass().isAssignableFrom(String.class)) {
                    typeCode = Types.VARCHAR;
                } else if (resultset.getNextRowObject(jdbcColumn).getClass().isAssignableFrom(Double.class)) {
                    typeCode = Types.DOUBLE;
                } else if (resultset.getNextRowObject(jdbcColumn).getClass().isAssignableFrom(Date.class)) {
                    typeCode = Types.DATE;
                } else {
                    typeCode = Types.VARCHAR;
                }
            } catch (Exception e) {
                // TODO: If the first cell of the first row is blank, we got a java.lang.RuntimeException: java.lang.NullPointerException
                typeCode = Types.VARCHAR;
            }
            columnTypeMap.put(jdbcColumn, typeCode);
            return typeCode;
        }
    }

    public String getColumnTypeName(int jdbcColumn) throws SQLException {
        return resultset.getNextRowObject(jdbcColumn).getClass().getName();
    }

    public int getPrecision(int arg0) throws SQLException {
        return 0;
    }

    public int getScale(int arg0) throws SQLException {
        return 0;
    }

    public String getSchemaName(int arg0) throws SQLException {
        return null;
    }

    public String getTableName(int arg0) throws SQLException {
        return null;
    }

    public boolean isAutoIncrement(int arg0) throws SQLException {
        return false;
    }

    public boolean isCaseSensitive(int arg0) throws SQLException {
        return false;
    }

    public boolean isCurrency(int arg0) throws SQLException {
        return false;
    }

    public boolean isDefinitelyWritable(int arg0) throws SQLException {
        return false;
    }

    public int isNullable(int arg0) throws SQLException {
        return 0;
    }

    public boolean isReadOnly(int arg0) throws SQLException {
        return false;
    }

    public boolean isSearchable(int arg0) throws SQLException {
        return false;
    }

    public boolean isSigned(int arg0) throws SQLException {
        return false;
    }

    public boolean isWritable(int arg0) throws SQLException {
        return false;
    }

    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

}
