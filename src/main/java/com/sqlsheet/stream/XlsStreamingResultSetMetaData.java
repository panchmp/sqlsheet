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
package com.sqlsheet.stream;

import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.util.Date;

/**
 * SqlSheet implementation of java.sql.ResultSetMetaData.
 *
 * @author <a href='http://www.pcal.net'>pcal</a>
 * @author <a href='http://code.google.com/p/sqlsheet'>sqlsheet</a>
 */
public class XlsStreamingResultSetMetaData implements ResultSetMetaData {

  private AbstractXlsSheetIterator iterator;

  public XlsStreamingResultSetMetaData(AbstractXlsSheetIterator iterator) {
    this.iterator = iterator;
  }

  public int getColumnCount() {
    return iterator.getColumns().size();
  }

  public String getColumnLabel(int jdbcCol) {
    return iterator.getColumns().get(jdbcCol - 1).stringValue;
  }

  public String getColumnName(int jdbcCol) {
    return iterator.getColumns().get(jdbcCol - 1).stringValue;
  }

  public String getCatalogName(int arg0) {
    return "";
  }

  public String getColumnClassName(int jdbcCol) {
    return iterator.getCurrentRowValue(jdbcCol - 1).getType().getName();
  }

  public int getColumnDisplaySize(int arg0) {
    return 0;
  }

  public int getColumnType(int jdbcCol) {
    if (iterator.getCurrentRowValue(jdbcCol - 1).getType().isAssignableFrom(String.class)) {
      return Types.VARCHAR;
    } else if (iterator.getCurrentRowValue(jdbcCol - 1).getType().isAssignableFrom(Double.class)) {
      return Types.DOUBLE;
    } else if (iterator.getCurrentRowValue(jdbcCol - 1).getType().isAssignableFrom(Date.class)) {
      return Types.DATE;
    }
    return Types.OTHER;
  }

  public String getColumnTypeName(int jdbcCol) {
    if (iterator.getCurrentIteratorRowIndex() == 0) {
      return iterator.getNextRowValue(jdbcCol - 1).getType().getName();
    } else {
      return iterator.getCurrentRowValue(jdbcCol - 1).getType().getName();
    }
  }

  public int getPrecision(int arg0) {
    return 0;
  }

  public int getScale(int arg0) {
    return 0;
  }

  public String getSchemaName(int arg0) {
    return "";
  }

  public String getTableName(int arg0) {
    return iterator.getSheetName();
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
    return ResultSetMetaData.columnNoNulls;
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
