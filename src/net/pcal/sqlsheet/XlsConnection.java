/*
 * Copyright 2008 pcal.net
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * SqlSheet implementation of java.sql.Connection.
 * 
 * @author <a href='http://www.pcal.net'>pcal</a>
 */
class XlsConnection implements Connection {

  // =========================================================================
  // Fields

  private HSSFWorkbook workbook = null;
  private File saveFile = null;

  // =========================================================================
  // Constructor

  XlsConnection(HSSFWorkbook workbook) {
    if (workbook == null) throw new IllegalArgumentException();
    this.workbook = workbook;
    this.saveFile = null;
  }

  XlsConnection(HSSFWorkbook workbook, File saveFile) {
    if (workbook == null) throw new IllegalArgumentException();
    if (saveFile == null) throw new IllegalArgumentException();
    this.workbook = workbook;
    this.saveFile = saveFile;
  }

  // =========================================================================
  // Package methods

  HSSFWorkbook getWorkBook() {
    return workbook;
  }

  // =========================================================================
  // Interesting implementation

  public Statement createStatement() throws SQLException {
    return new XlsStatement(this);
  }

  public PreparedStatement prepareStatement(String sql) throws SQLException {
    return new XlsPreparedStatement(this, sql);
  }

  public PreparedStatement prepareStatement(String sql, int resultSetType,
                                            int resultSetConcurrency)
      throws SQLException
  {
    return prepareStatement(sql);
  }

  public void close() throws SQLException {
    if (saveFile != null) {
      FileOutputStream fileOut = null;
      try {
        fileOut = new FileOutputStream(saveFile);
        workbook.write(fileOut);
      } catch (IOException ioe) {
        SQLException sqe = new SQLException(ioe.getMessage());
        sqe.initCause(sqe);
        throw sqe;
      } finally {
        if (fileOut != null) try {
          fileOut.close();
        } catch (IOException ohwell) {
          ohwell.printStackTrace();
        }
      }
    }
  }

  // =========================================================================
  // Boring implementation

  public boolean getAutoCommit() {
    return false;
  }

  public boolean isClosed() {
    return false;
  }

  public boolean isReadOnly() {
    return true;
  }

  public String getCatalog() {
    return null;
  }

  public int getTransactionIsolation() {
    return Connection.TRANSACTION_NONE;
  }

  public SQLWarning getWarnings() {
    return null;
  }

  @SuppressWarnings("unchecked")
  public Map getTypeMap() throws SQLException
  {
    return null;
  }

  public void commit() throws SQLException {}

  public void rollback() throws SQLException {}

  public void clearWarnings() throws SQLException {}

  public DatabaseMetaData getMetaData() throws SQLException {
    // nyi();
    return null;
  }

  // =========================================================================
  // NYI

  public void setAutoCommit(boolean autoCommit) throws SQLException {
    nyi();
  }

  public void setReadOnly(boolean readOnly) throws SQLException {
    nyi();
  }

  public void setCatalog(String catalog) throws SQLException {
    nyi();
  }

  public void setTransactionIsolation(int level) throws SQLException {
    nyi();
  }

  public void setTypeMap(Map map) throws SQLException {
    nyi();
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
      throws SQLException
  {
    nyi();
    return null;
  }

  public CallableStatement prepareCall(String sql, int resultSetType,
                                       int resultSetConcurrency)
      throws SQLException
  {
    nyi();
    return null;
  }

  public int getHoldability() throws SQLException {
    nyi();
    return -1;
  }

  public CallableStatement prepareCall(String str, int param, int param2,
                                       int param3) throws SQLException
  {
    nyi();
    return null;
  }

  public PreparedStatement prepareStatement(String str, int param)
      throws SQLException
  {
    nyi();
    return null;
  }

  public PreparedStatement prepareStatement(String str, int[] values)
      throws SQLException
  {
    nyi();
    return null;
  }

  public PreparedStatement prepareStatement(String str, String[] str1)
      throws SQLException
  {
    nyi();
    return null;
  }

  public PreparedStatement prepareStatement(String str, int param,
                                            int param2, int param3)
      throws SQLException
  {
    nyi();
    return null;

  }

  public void releaseSavepoint(Savepoint savepoint) throws SQLException {
    nyi();
  }

  public void rollback(Savepoint savepoint) throws SQLException {
    nyi();
  }

  public void setHoldability(int param) throws SQLException {
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

  public Statement createStatement(int resultSetType,
                                   int resultSetConcurrency,
                                   int resultSetHoldability)
      throws SQLException
  {
    nyi();
    return null;
  }

  // =========================================================================
  // Private methods

  private void nyi() throws SQLException {
    throw new SQLException("NYI");
  }

}
