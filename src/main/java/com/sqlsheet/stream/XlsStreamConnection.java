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

import java.net.URL;
import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.logging.Logger;

/**
 * SqlSheet implementation of java.sql.Connection which uses steaming over XLS
 *
 * @author <a href='http://code.google.com/p/sqlsheet'>sqlsheet</a>
 */
public class XlsStreamConnection implements Connection {

  private static final Logger logger = Logger.getLogger(XlsStreamConnection.class.getName());

  protected final URL xlsFile;

  public XlsStreamConnection(URL xlsFile) {
    this.xlsFile = xlsFile;
  }

  public Statement createStatement() {
    return new XlsStreamStatement(this);
  }

  public PreparedStatement prepareStatement(String sql) throws SQLException {
    return new XlsStreamPreparedStatement(this, sql);
  }

  public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
      throws SQLException {
    return prepareStatement(sql);
  }

  public void close() {}

  public boolean getAutoCommit() {
    return false;
  }

  public void setAutoCommit(boolean autoCommit) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public boolean isClosed() {
    return false;
  }

  public boolean isReadOnly() {
    return true;
  }

  public void setReadOnly(boolean readOnly) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public String getCatalog() {
    return null;
  }

  public void setCatalog(String catalog) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public int getTransactionIsolation() {
    return Connection.TRANSACTION_NONE;
  }

  public void setTransactionIsolation(int level) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public SQLWarning getWarnings() {
    return null;
  }

  public Map<String,Class<?>> getTypeMap() {
    return null;
  }

  public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void commit() {}

  public void rollback() {}

  public void clearWarnings() {}

  public DatabaseMetaData getMetaData() {
    return new XlsStreamDatabaseMetaData(xlsFile);
  }

  public CallableStatement prepareCall(String sql) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public String nativeSQL(String sql) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public Statement createStatement(int resultSetType, int resultSetConcurrency)
      throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency)
      throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public int getHoldability() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void setHoldability(int param) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public CallableStatement prepareCall(String str, int param, int param2, int param3)
      throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public PreparedStatement prepareStatement(String str, int param) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public PreparedStatement prepareStatement(String str, int[] values) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public PreparedStatement prepareStatement(String str, String[] str1) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public Clob createClob() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public Blob createBlob() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public NClob createNClob() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public SQLXML createSQLXML() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public boolean isValid(int timeout) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void setClientInfo(String name, String value) {}

  public String getClientInfo(String name) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public Properties getClientInfo() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void setClientInfo(Properties properties) {}

  public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public PreparedStatement prepareStatement(String str, int param, int param2, int param3)
      throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void releaseSavepoint(Savepoint savepoint) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void rollback(Savepoint savepoint) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public Savepoint setSavepoint() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public Savepoint setSavepoint(String str) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public Statement createStatement(
      int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public <T> T unwrap(Class<T> iface) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public boolean isWrapperFor(Class<?> iface) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public String getSchema() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void setSchema(String string) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void abort(Executor exctr) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public void setNetworkTimeout(Executor exctr, int i) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  public int getNetworkTimeout() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }
}
