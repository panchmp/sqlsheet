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

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import net.pcal.sqlsheet.parser.CreateTableStatement;
import net.pcal.sqlsheet.parser.InsertIntoStatement;
import net.pcal.sqlsheet.parser.ParsedStatement;
import net.pcal.sqlsheet.parser.SelectStarStatement;
import net.pcal.sqlsheet.parser.SqlSheetParser;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * SqlSheet implementation of java.sql.Statement.
 * 
 * @author <a href='http://www.pcal.net'>pcal</a>
 */
public class XlsStatement implements Statement {

  // =========================================================================
  // Fields

  private XlsConnection connection;
  private Map<String, XlsResultSet> sheet2rs =
      new HashMap<String, XlsResultSet>();
  private SqlSheetParser parser;

  // =========================================================================
  // Constructors

  public XlsStatement(XlsConnection c) {
    if (c == null) throw new IllegalArgumentException();
    this.connection = c;
  }

  // =========================================================================
  // Interesting implementation

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
      return doSelect((SelectStarStatement)parsed);
    } else if (parsed instanceof InsertIntoStatement) {
      return doInsert((InsertIntoStatement)parsed);
    } else if (parsed instanceof CreateTableStatement) {
      return doCreateTable((CreateTableStatement)parsed);
    } else {
      throw new IllegalStateException(parsed.getClass().getName());
    }
  }

  // =========================================================================
  // Protected methods

  protected ParsedStatement parse(String sql) throws SQLException {
    if (sql == null) throw new IllegalArgumentException();
    if (parser == null) parser = new SqlSheetParser();
    return parser.parse(sql);
  }

  protected ResultSet doSelect(SelectStarStatement sss) throws SQLException {
    XlsResultSet out = findOrCreateResultSetFor(sss.getTable());
    out.beforeFirst();
    return out;
  }

  protected ResultSet doCreateTable(CreateTableStatement cts)
      throws SQLException
  {
    HSSFSheet newSheet = connection.getWorkBook().createSheet(cts.getTable());
    HSSFRow row = newSheet.createRow(0);
    for (short i = 0; i < cts.getColumns().size(); i++) {
      HSSFCell cell = row.createCell(i);
      cell.setCellValue(cts.getColumns().get(i));
    }
    return findOrCreateResultSetFor(cts.getTable()); // REVIEW
  }

  protected ResultSet doInsert(InsertIntoStatement insert)
      throws SQLException
  {
    XlsResultSet rs = findOrCreateResultSetFor(insert.getTable());
    rs.moveToInsertRow();
    for (int i = 0; i < insert.getColumns().size(); i++) {
      rs.updateObject(i + 1, insert.getValues().get(i));
    }
    return rs;
  }

  // =========================================================================
  // Private methods

  private XlsResultSet findOrCreateResultSetFor(String tableName)
      throws SQLException
  {
    tableName = tableName.trim().toUpperCase();
    XlsResultSet out = sheet2rs.get(tableName);
    if (out == null) {
      HSSFSheet sheet = getSheetNamed(connection.getWorkBook(), tableName);
      out = new XlsResultSet(connection.getWorkBook(), sheet);
      sheet2rs.put(tableName, out);
    }
    return out;
  }

  private static HSSFSheet getSheetNamed(HSSFWorkbook wb, String name)
      throws SQLException
  {
    if (name == null) throw new IllegalArgumentException();
    name = name.trim();

    // Workaround an apparent bug in POI - getNumberOfSheets() returns 0
    // unless we do this first
    wb.getSheetAt(0);

    int count = wb.getNumberOfSheets();
    for (int i = 0; i < count; i++) {
      String sheetName = wb.getSheetName(i);
      if (sheetName == null) continue;
      if (sheetName.equalsIgnoreCase(name)) {
        return wb.getSheetAt(i);
      }
    }
    throw new SQLException("No sheet named '" + name + "'");
  }

  // =========================================================================
  // All NYI below here

  public void setMaxFieldSize(int p0) throws SQLException {
    nyi();
  }

  public void setMaxRows(int p0) throws SQLException {
    nyi();
  }

  public void setEscapeProcessing(boolean p0) throws SQLException {
    nyi();
  }

  public void setQueryTimeout(int p0) throws SQLException {
    nyi();
  }

  public void setCursorName(String p0) throws SQLException {
    nyi();
  }

  public void setFetchDirection(int p0) throws SQLException {
    nyi();
  }

  public void setFetchSize(int p0) throws SQLException {
    nyi();
  }

  public int getMaxFieldSize() throws SQLException {
    nyi();
    return -1;
  }

  public int getMaxRows() throws SQLException {
    nyi();
    return -1;
  }

  public int getQueryTimeout() throws SQLException {
    nyi();
    return -1;
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

  public boolean getMoreResults() throws SQLException {
    nyi();
    return false;
  }

  public boolean getMoreResults(int current) throws SQLException {
    nyi();
    return false;
  }

  public int getFetchDirection() throws SQLException {
    nyi();
    return -1;
  }

  public int getFetchSize() throws SQLException {
    nyi();
    return -1;
  }

  public int getResultSetConcurrency() throws SQLException {
    nyi();
    return -1;
  }

  public int getResultSetType() throws SQLException {
    nyi();
    return -1;
  }

  public void cancel() throws SQLException {
    nyi();
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

  public ResultSet getGeneratedKeys() throws SQLException {
    nyi();
    return null;
  }

  public int executeUpdate(String sql, int autoGeneratedKeys)
      throws SQLException
  {
    nyi();
    return -1;
  }

  public int executeUpdate(String sql, int[] columnIndexes)
      throws SQLException
  {
    nyi();
    return -1;
  }

  public int executeUpdate(String sql, String[] columnNames)
      throws SQLException
  {
    nyi();
    return -1;
  }

  public boolean execute(String sql, int autoGeneratedKeys)
      throws SQLException
  {
    nyi();
    return false;
  }

  public boolean execute(String sql, int[] columnIndexes) throws SQLException

  {
    nyi();
    return false;
  }

  public boolean execute(String sql, String[] columnNames)
      throws SQLException
  {
    nyi();
    return false;

  }

  public int getResultSetHoldability() throws SQLException {
    nyi();
    return -1;
  }

  // =========================================================================
  // Private methods

  protected void nyi() throws SQLException {
    throw new SQLException("NYI");
  }

}
