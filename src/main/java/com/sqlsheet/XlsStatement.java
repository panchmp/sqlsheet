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

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.sqlsheet.parser.CreateTableStatement;
import com.sqlsheet.parser.DropTableStatement;
import com.sqlsheet.parser.InsertIntoStatement;
import com.sqlsheet.parser.ParsedStatement;
import com.sqlsheet.parser.SelectStarStatement;
import com.sqlsheet.parser.SqlSheetParser;
import java.sql.*;
import java.util.HashSet;

/**
 * SqlSheet implementation of java.sql.Statement.
 *
 * @author <a href='http://www.pcal.net'>pcal</a>
 * @author <a href='http://code.google.com/p/sqlsheet'>sqlsheet</a>
 */
public class XlsStatement implements Statement {
  public static final int DEFAULT_HEADLINE = 1;
  public static final int DEFAULT_FIRST_COL = 0;

  private XlsConnection connection;
  private final Map<String, XlsResultSet> sheet2rs = new HashMap<>();
  private SqlSheetParser parser;
  private boolean isClosed;
  private boolean isCloseOnCompletion;

  public XlsStatement(XlsConnection c) {
    if (c == null) throw new IllegalArgumentException();
    this.connection = c;
  }

  private static Sheet getSheetNamed(Workbook wb, String name) throws SQLException {
    if (name == null) throw new IllegalArgumentException();
    name = name.trim();
    String allSheetNames = "";
    int count = wb.getNumberOfSheets();
    for (int i = 0; i < count; i++) {
      String sheetName = wb.getSheetName(i);
      allSheetNames += sheetName + ",";
      if (sheetName == null) continue;
      if (sheetName.equalsIgnoreCase(name) || ("\"" + sheetName + "\"").equalsIgnoreCase(name)) {
        return wb.getSheetAt(i);
      }
    }

    String message = "No sheet named '" + name;
    if (count == 0) {
      message += " can be found. Are you sure of the Excel file path ?";
    } else {
      if (allSheetNames.length() > 2) {
        allSheetNames = allSheetNames.substring(0, allSheetNames.length() - 1);
      }
      message += ". Only the following " + count + " sheets can be found (" + allSheetNames + ")";
    }
    throw new SQLException(message);
  }

  public Connection getConnection() throws SQLException {
    return connection;
  }

  @Override
  public void close() throws SQLException {
    sheet2rs.clear();
    parser = null;

    isClosed = true;
  }

  @Override
  public boolean execute(String sql) throws SQLException {
    ParsedStatement parsed = parse(sql);
    if (parsed instanceof DropTableStatement) {
      doDropTable((DropTableStatement) parsed);
    } else {
      executeQuery(parsed);
    }
    return true;
  }

  private ResultSet executeQuery(ParsedStatement parsed) throws SQLException {
    if (parsed instanceof SelectStarStatement) {
      return doSelect((SelectStarStatement) parsed);
    } else if (parsed instanceof InsertIntoStatement) {
      return doInsert((InsertIntoStatement) parsed);
    } else if (parsed instanceof CreateTableStatement) {
      return doCreateTable((CreateTableStatement) parsed);
    } else {
      throw new IllegalStateException(parsed.getClass().getName());
    }
  }

  @Override
  public int executeUpdate(String sql) throws SQLException {
    executeQuery(sql);
    return 1;
  }

  @Override
  public ResultSet executeQuery(String query) throws SQLException {
    ParsedStatement parsed = parse(query);
    return executeQuery(parsed);
  }

  private void doDropTable(DropTableStatement dropTableStatement) {
    connection.setWriteRequired(true);
    String tableName = dropTableStatement.getTable();
    int sheetIndexToRemove = connection.getWorkBook().getSheetIndex(tableName);
    connection.getWorkBook().removeSheetAt(sheetIndexToRemove);
  }

  protected ParsedStatement parse(String sql) throws SQLException {
    if (sql == null) throw new IllegalArgumentException();
    if (parser == null) parser = new SqlSheetParser();
    return parser.parse(sql);
  }

  protected ResultSet doSelect(SelectStarStatement sss) throws SQLException {
    XlsResultSet out = findOrCreateResultSetFor(sss.getTable());
    out.beforeFirst();
    out.statement = this;
    return out;
  }

  protected ResultSet doCreateTable(CreateTableStatement cts) throws SQLException {
    connection.setWriteRequired(true);
    String tableName = cts.getTable().replace("\"", "");
    Sheet newSheet = connection.getWorkBook().createSheet(tableName);
    Row row = newSheet.createRow(0);
    for (short i = 0; i < cts.getColumns().size(); i++) {
      Cell cell = row.createCell(i);
      cell.setCellValue(cts.getColumns().get(i));
    }
    return findOrCreateResultSetFor(cts.getTable()); // REVIEW
  }

  protected ResultSet doInsert(InsertIntoStatement insert) throws SQLException {
    connection.setWriteRequired(true);
    XlsResultSet rs = findOrCreateResultSetFor(insert.getTable());
    rs.moveToInsertRow();
    for (int i = 0; i < insert.getColumns().size(); i++) {
      rs.updateObject(i + 1, insert.getValues().get(i));
    }
    return rs;
  }

  private XlsResultSet findOrCreateResultSetFor(String tableName) throws SQLException {
    tableName = tableName.trim().toUpperCase();
    XlsResultSet out = sheet2rs.get(tableName);
    if (out == null) {
      Sheet sheet = getSheetNamed(connection.getWorkBook(), tableName);
      out =
          new XlsResultSet(
              connection.getWorkBook(),
              sheet,
              connection.getInt(XlsDriver.HEADLINE, DEFAULT_HEADLINE),
              connection.getInt(XlsDriver.FIRST_COL, DEFAULT_FIRST_COL));
      out.statement = this;
      sheet2rs.put(tableName, out);
    }
    return out;
  }

  public void setEscapeProcessing(boolean p0) throws SQLException {
    nyi();
  }

  public void setCursorName(String p0) throws SQLException {
    nyi();
  }

  public int getMaxFieldSize() throws SQLException {
    nyi();
    return -1;
  }

  public void setMaxFieldSize(int p0) throws SQLException {
    nyi();
  }

  public int getMaxRows() throws SQLException {
    nyi();
    return -1;
  }

  public void setMaxRows(int p0) throws SQLException {
    nyi();
  }

  public int getQueryTimeout() throws SQLException {
    nyi();
    return -1;
  }

  public void setQueryTimeout(int p0) throws SQLException {
    nyi();
  }

  public SQLWarning getWarnings() throws SQLException {
    nyi();
    return null;
  }

  public ResultSet getResultSet() throws SQLException {
    nyi();
    return null;
  }

  public int getUpdateCount() throws SQLException {
    nyi();
    return -1;
  }

  @Override
  public boolean getMoreResults() throws SQLException {
    throw new SQLFeatureNotSupportedException("Fetch Size is not supported.");
  }

  @Override
  public boolean getMoreResults(int current) throws SQLException {
    throw new SQLFeatureNotSupportedException("Fetch Size is not supported.");
  }

  @Override
  public int getFetchDirection() throws SQLException {
    throw new SQLFeatureNotSupportedException("Fetch Size is not supported.");
  }

  @Override
  public void setFetchDirection(int p0) throws SQLException {
    throw new SQLFeatureNotSupportedException("Fetch Size is not supported.");
  }

  @Override
  public int getFetchSize() throws SQLException {
    throw new SQLFeatureNotSupportedException("Fetch Size is not supported.");
  }

  @Override
  public void setFetchSize(int p0) throws SQLException {
    throw new SQLFeatureNotSupportedException("Fetch Size is not supported.");
  }

  @Override
  public int getResultSetConcurrency() throws SQLException {
    return ResultSet.CONCUR_READ_ONLY;
  }

  @Override
  public int getResultSetType() throws SQLException {
    return ResultSet.TYPE_FORWARD_ONLY;
  }

  @Override
  public void cancel() throws SQLException {
    throw new SQLFeatureNotSupportedException("Fetch Size is not supported.");
  }

  public void clearWarnings() throws SQLException {
    nyi();
  }

  public void addBatch(String p0) throws SQLException {
    nyi();
  }

  public void clearBatch() throws SQLException {
    nyi();
  }

  public int[] executeBatch() throws SQLException {
    nyi();
    return null;
  }

  @Override
  public ResultSet getGeneratedKeys() throws SQLException {
    throw new SQLFeatureNotSupportedException();
  }

  public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
    throw new SQLFeatureNotSupportedException();
  }

  @Override
  public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
    throw new SQLFeatureNotSupportedException();
  }

  @Override
  public int executeUpdate(String sql, String[] columnNames) throws SQLException {
    throw new SQLFeatureNotSupportedException();
  }

  @Override
  public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
    throw new SQLFeatureNotSupportedException();
  }

  @Override
  public boolean execute(String sql, int[] columnIndexes) throws SQLException {
    throw new SQLFeatureNotSupportedException();
  }

  @Override
  public boolean execute(String sql, String[] columnNames) throws SQLException {
    throw new SQLFeatureNotSupportedException();
  }

  @Override
  public int getResultSetHoldability() throws SQLException {
    return ResultSet.CLOSE_CURSORS_AT_COMMIT;
  }

  protected void nyi() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet."));
  }

  @Override
  public boolean isClosed() throws SQLException {
    return isClosed;
  }

  @Override
  public boolean isPoolable() throws SQLException {
    return false;
  }

  @Override
  public void setPoolable(boolean poolable) throws SQLException {}

  public boolean isWrapperFor(Class<?> iface) throws SQLException {
    return false;
  }

  public <T> T unwrap(Class<T> iface) throws SQLException {
    return null;
  }

  @Override
  public void closeOnCompletion() throws SQLException {
    isCloseOnCompletion = true;
  }

  @Override
  public boolean isCloseOnCompletion() throws SQLException {
    return isCloseOnCompletion;
  }
}
