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
package com.googlecode.sqlsheet.stream;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;


/**
 * SqlSheet implementation of java.sql.ResultSetMetaData.
 *
 * @author <a href='http://www.pcal.net'>pcal</a>
 * @author <a href='http://code.google.com/p/sqlsheet'>sqlsheet</a>
 */
public class XlsStreamingResultSetMetaData implements ResultSetMetaData {

    private String[] columnNames;

    public XlsStreamingResultSetMetaData(String[] columnNames) throws SQLException {
        this.columnNames = new String[columnNames.length];
        for (short c = 0; c < columnNames.length; c++) {
            this.columnNames[c] = columnNames[c];
        }
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public String getColumnLabel(int jdbcCol) {
        return columnNames[jdbcCol - 1];
    }

    public String getColumnName(int jdbcCol) {
        return columnNames[jdbcCol - 1];
    }

    public String getCatalogName(int arg0) throws SQLException {
        return null;
    }

    public String getColumnClassName(int arg0) throws SQLException {
        return null;
    }

    public int getColumnDisplaySize(int arg0) {
        return 0;
    }

    public int getColumnType(int arg0) throws SQLException {
        return 0;
    }

    public String getColumnTypeName(int arg0) throws SQLException {
        return null;
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
