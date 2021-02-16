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

import com.sqlsheet.parser.ParsedStatement;
import com.sqlsheet.parser.SelectStarStatement;
import com.sqlsheet.parser.SqlSheetParser;

import java.sql.*;

/**
 * SqlSheet implementation of java.sql.Statement which uses steaming over XLS
 *
 * @author <a href='http://code.google.com/p/sqlsheet'>sqlsheet</a>
 */
public class XlsStreamStatement implements Statement {

  XlsStreamConnection connection;
  SqlSheetParser parser;

  public XlsStreamStatement(XlsStreamConnection c) {
    if (c == null) throw new IllegalArgumentException();
    this.connection = c;
  }

  public Connection getConnection() throws SQLException {
    return connection;
  }

  public void close() throws SQLException {}

  public boolean execute(String sql) throws SQLException {
    executeQuery(sql);
    return false;
  }

  public int executeUpdate(String sql) throws SQLException {
    executeQuery(sql);
    return 1;
  }

  public ResultSet executeQuery(String query) throws SQLException {
    ParsedStatement parsed = parse(query);
    if (parsed instanceof SelectStarStatement) {
      return doSelect((SelectStarStatement) parsed);
    } else {
      throw new IllegalStateException(parsed.getClass().getName());
    }
  }

  protected ParsedStatement parse(String sql) throws SQLException {
    if (sql == null) throw new IllegalArgumentException();
    if (parser == null) parser = new SqlSheetParser();
    return parser.parse(sql);
  }

  protected ResultSet doSelect(SelectStarStatement sss) throws SQLException {
    XlsStreamResultSet out = findOrCreateResultSetFor(sss.getTable());
    out.beforeFirst();
    return out;
  }

  private XlsStreamResultSet findOrCreateResultSetFor(String tableName) throws SQLException {
    return new XlsStreamResultSet(tableName, connection);
  }

  public void setEscapeProcessing(boolean p0) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void setCursorName(String p0) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public int getMaxFieldSize() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void setMaxFieldSize(int p0) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public int getMaxRows() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void setMaxRows(int p0) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public int getQueryTimeout() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void setQueryTimeout(int p0) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public SQLWarning getWarnings() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public ResultSet getResultSet() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public int getUpdateCount() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public boolean getMoreResults() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public boolean getMoreResults(int current) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public int getFetchDirection() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void setFetchDirection(int p0) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public int getFetchSize() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void setFetchSize(int p0) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public int getResultSetConcurrency() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public int getResultSetType() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void cancel() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void clearWarnings() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void addBatch(String p0) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void clearBatch() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public int[] executeBatch() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public ResultSet getGeneratedKeys() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public int executeUpdate(String sql, String[] columnNames) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public boolean execute(String sql, int[] columnIndexes) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public boolean execute(String sql, String[] columnNames) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public int getResultSetHoldability() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public boolean isClosed() throws SQLException {
    return false;
  }

  public boolean isPoolable() throws SQLException {
    return false;
  }

  public void setPoolable(boolean poolable) throws SQLException {}

  public boolean isWrapperFor(Class<?> iface) throws SQLException {
    return false;
  }

  public <T> T unwrap(Class<T> iface) throws SQLException {
    return null;
  }

  public void closeOnCompletion() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public boolean isCloseOnCompletion() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }
}
