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

import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.channels.FileChannel;
import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * SqlSheet implementation of java.sql.Connection.
 *
 * @author <a href='http://www.pcal.net'>pcal</a>
 * @author <a href='http://code.google.com/p/sqlsheet'>sqlsheet</a>
 */
class XlsConnection implements Connection {

  private static final Logger logger = LoggerFactory.getLogger(XlsConnection.class.getName());
  protected Workbook workbook;
  protected File saveFile;
  private Properties info;
  private boolean writeRequired;

  XlsConnection(Workbook workbook, Properties info) {
    this(workbook, null, info);
  }

  XlsConnection(Workbook workbook, File saveFile, Properties info) {
    if (workbook == null) throw new IllegalArgumentException();
    this.workbook = workbook;
    this.saveFile = saveFile;
    this.info = info;
  }

  int getInt(String key, int defaultValue) {
    Object value = info.get(key);
    if (value == null) {
      logger.info("Key {} not present.", key);
      return defaultValue;
    }
    return Integer.parseInt(value.toString());
  }

  Workbook getWorkBook() {
    return workbook;
  }

  public Statement createStatement() {
    return new XlsStatement(this);
  }

  public PreparedStatement prepareStatement(String sql) throws SQLException {
    return new XlsPreparedStatement(this, sql);
  }

  public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
      throws SQLException {
    return prepareStatement(sql);
  }

  public void close() throws SQLException {
    if (saveFile == null || !writeRequired) return;
    try {
      OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(saveFile));
      workbook.write(outputStream);
      outputStream.close();
    } catch (IOException exception) {
      throw new SQLException("Error while persisting changes.", exception);
    }
  }

  public void _close() throws SQLException {
    if (saveFile != null && writeRequired) {
      FileOutputStream fileOut;
      try {
        File newFile = File.createTempFile("xlsdriver", "xlsx");
        fileOut = new FileOutputStream(newFile);
        workbook.write(fileOut);
        if (fileOut != null)
          try {
            fileOut.close();
          } catch (IOException e) {
              logger.warn(e.getMessage(), e);
          }
        // Move already existing data to not corrupt on fail
        File backup = backupFile(saveFile);
        logger.info("Created backup:{}", backup.getAbsolutePath());
        // Try to override file with new content
        // backup should rename file or copy content into new one, handle second condition
        if (saveFile.exists() && !saveFile.delete())
          logger.warn("Unable to delete file:{}, you may lose the results.", saveFile.getAbsolutePath());
        moveFile(newFile, saveFile);
      } catch (IOException ioe) {
        SQLException sqe = new SQLException(ioe.getMessage(), ioe);
        sqe.initCause(sqe);
        throw sqe;
      }
    }
  }

  private File backupFile(File input) throws IOException {

    File output = null;
    if (input != null) {
      output = File.createTempFile(input.getName() + ".", ".bkp", null);
      moveFile(input, output);
    }
    return output;
  }

  private void moveFile(File sourceFile, File destFile) throws IOException {
    boolean moved;
    moved = sourceFile.renameTo(destFile);
    if (!moved) {
      logger.warn("Unable to rename file during move:{} to {}, performing full copy of data.", sourceFile.getAbsolutePath(), destFile.getAbsolutePath());
      FileChannel source = null;
      FileChannel destination = null;
      try {
        source = new FileInputStream(sourceFile).getChannel();
        destination = new FileOutputStream(destFile).getChannel();
        // previous code: destination.transferFrom(source, 0, source.size());
        // to avoid infinite loops, should be:
        long count = 0;
        long size = source.size();
        do {
          count += destination.transferFrom(source, 0, size - count);
        } while (count < size);
      } finally {
        if (source != null) source.close();
        if (destination != null) destination.close();
      }
    }
  }

  @Override
  public boolean getAutoCommit() {
    return false;
  }

  @Override
  public void setAutoCommit(boolean autoCommit) throws SQLException {
    nyi();
  }

  @Override
  public boolean isClosed() {
    return false;
  }

  @Override
  public boolean isReadOnly() {
    return true;
  }

  @Override
  public void setReadOnly(boolean readOnly) throws SQLException {
    nyi();
  }

  @Override
  public String getCatalog() {
    return null;
  }

  @Override
  public void setCatalog(String catalog) throws SQLException {
    nyi();
  }

  @Override
  public int getTransactionIsolation() {
    return Connection.TRANSACTION_NONE;
  }

  @Override
  public void setTransactionIsolation(int level) throws SQLException {
    nyi();
  }

  @Override
  public SQLWarning getWarnings() {
    return null;
  }

  @SuppressWarnings("unchecked")
  public Map getTypeMap() {
    return null;
  }

  public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
    nyi();
  }

  @Override
  public void commit() {}

  @Override
  public void rollback() {}

  @Override
  public void clearWarnings() {}

  @Override
  public DatabaseMetaData getMetaData() {
    return new XlsDatabaseMetaData(this);
  }

  public CallableStatement prepareCall(String sql) throws SQLException {
    nyi();
    return null;
  }

  public String nativeSQL(String sql) throws SQLException {
    nyi();
    return null;
  }

  public Statement createStatement(int resultSetType, int resultSetConcurrency)
      throws SQLException {
    nyi();
    return null;
  }

  public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency)
      throws SQLException {
    nyi();
    return null;
  }

  public int getHoldability() throws SQLException {
    nyi();
    return -1;
  }

  public void setHoldability(int param) throws SQLException {
    nyi();
  }

  public CallableStatement prepareCall(String str, int param, int param2, int param3)
      throws SQLException {
    nyi();
    return null;
  }

  public PreparedStatement prepareStatement(String str, int param) throws SQLException {
    nyi();
    return null;
  }

  public PreparedStatement prepareStatement(String str, int[] values) throws SQLException {
    nyi();
    return null;
  }

  public PreparedStatement prepareStatement(String str, String[] str1) throws SQLException {
    nyi();
    return null;
  }

  public Clob createClob() throws SQLException {
    nyi();
    return null;
  }

  public Blob createBlob() throws SQLException {
    nyi();
    return null;
  }

  public NClob createNClob() throws SQLException {
    nyi();
    return null;
  }

  public SQLXML createSQLXML() throws SQLException {
    nyi();
    return null;
  }

  public boolean isValid(int timeout) throws SQLException {
    nyi();
    return false;
  }

  public void setClientInfo(String name, String value) {}

  public String getClientInfo(String name) throws SQLException {
    nyi();
    return null;
  }

  public Properties getClientInfo() throws SQLException {
    nyi();
    return null;
  }

  public void setClientInfo(Properties properties) {}

  public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
    nyi();
    return null;
  }

  public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
    nyi();
    return null;
  }

  public PreparedStatement prepareStatement(String str, int param, int param2, int param3)
      throws SQLException {
    nyi();
    return null;
  }

  public void releaseSavepoint(Savepoint savepoint) throws SQLException {
    nyi();
  }

  public void rollback(Savepoint savepoint) throws SQLException {
    nyi();
  }

  public Savepoint setSavepoint() throws SQLException {
    nyi();
    return null;
  }

  public Savepoint setSavepoint(String str) throws SQLException {
    nyi();
    return null;
  }

  public Statement createStatement(
      int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
    nyi();
    return null;
  }

  private void nyi() throws SQLException {
    throw new SQLException("NYI");
  }

  public <T> T unwrap(Class<T> iface) throws SQLException {
    nyi();
    return null;
  }

  public boolean isWrapperFor(Class<?> iface) throws SQLException {
    nyi();
    return false;
  }

  public Boolean getWriteRequired() {
    return writeRequired;
  }

  public void setWriteRequired(Boolean writeRequired) {
    this.writeRequired = writeRequired;
  }

  public String getSchema() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public void setSchema(String string) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public void abort(Executor exctr) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public void setNetworkTimeout(Executor exctr, int i) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public int getNetworkTimeout() {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
