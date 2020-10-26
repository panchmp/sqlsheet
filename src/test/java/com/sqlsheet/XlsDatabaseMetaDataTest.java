/*
 * Copyright 2020 Andreas Reichel <andreas@manticore-projects.com>.
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

import de.vandermeer.asciitable.AT_Context;
import de.vandermeer.asciitable.AsciiTable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.util.Locale;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.DateFormatConverter;
import org.junit.*;

/**
 *
 * @author Andreas Reichel <andreas@manticore-projects.com>
 */
public class XlsDatabaseMetaDataTest {

  public static final int DEFAULT_HEADLINE = 3;
  public static final int DEFAULT_FIRST_COL = 1;

  private final Connection conn;
  private final DatabaseMetaData metaData;

  private final String[] columnNames = {"String", "Date", "Boolean", "Double", "String", "Date", "Boolean", "Double"};
  private final Object[][] cellValues = {
    {"Row1", new java.util.Date(), Boolean.TRUE, 1, "Test", new java.util.Date(), Boolean.FALSE, Math.PI},
    {"Row2", new java.util.Date(), Boolean.FALSE, 2, "Test", new java.util.Date(), Boolean.TRUE, 2 * Math.PI},
    {"Row3", new java.util.Date(), Boolean.TRUE, 3, "Test", new java.util.Date(), Boolean.FALSE, 3 * Math.PI}
  };

  public XlsDatabaseMetaDataTest() throws SQLException, IOException, ClassNotFoundException {
    Workbook workBook = WorkbookFactory.create(true);
    Sheet sheet = workBook.createSheet("TestSheet1");
    int r = DEFAULT_HEADLINE - 1;
    Row row = sheet.createRow(r);
    for (int c = DEFAULT_FIRST_COL; c < columnNames.length + DEFAULT_FIRST_COL; c++) {
      Cell cell = row.createCell(c);
      cell.setCellValue(columnNames[c - DEFAULT_FIRST_COL] + c);
    }
    
    DataFormat dateFormat = workBook.createDataFormat();
    short dateFormatIndex =
    dateFormat.getFormat(DateFormatConverter.convert(Locale.US, DateFormat.getDateInstance(DateFormat.SHORT, Locale.US)));

    CellStyle dateCellStyle = workBook.createCellStyle();
    dateCellStyle.setDataFormat(dateFormatIndex);

    while (r < 3 + DEFAULT_HEADLINE - 1) {
      r++;
      row = sheet.createRow(r);
      for (int c = DEFAULT_FIRST_COL; c < columnNames.length + DEFAULT_FIRST_COL; c++) {
        Cell cell = row.createCell(c);

        Object value = cellValues[r - DEFAULT_HEADLINE][c - DEFAULT_FIRST_COL];
        if (value instanceof java.util.Date) {
          cell.setCellValue((java.util.Date) value);
          cell.setCellStyle(dateCellStyle);
        } else if (value instanceof Boolean)
          cell.setCellValue((Boolean) value);
        else if (value instanceof Double)
          cell.setCellValue((Double) value);
        else if (value instanceof String)
          cell.setCellValue((String) value);
      }
    }

    workBook.createSheet("TestSheet2");
    workBook.createSheet("TestSheet3");

    File file = File.createTempFile("DatabaseMetaData_", ".xlsx");
    file.deleteOnExit();

    FileOutputStream fileOutputStream = new FileOutputStream(file);
    workBook.write(fileOutputStream);
    fileOutputStream.flush();
    fileOutputStream.close();

    conn = DriverManager.getConnection(
    "jdbc:xls:" + file.toURI().toASCIIString() + "?headLine=" + DEFAULT_HEADLINE + "&firstColumn=" + DEFAULT_FIRST_COL);
    metaData = conn.getMetaData();
  }

  @BeforeClass
  public static void loadDriverClass() throws ClassNotFoundException {
    Class.forName("com.sqlsheet.XlsDriver");
  }

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }

  @Test
  public void testAllProceduresAreCallable() throws Exception {
  }

  @Test
  public void testAllTablesAreSelectable() throws Exception {
  }

  @Test
  public void testGetURL() throws Exception {
  }

  @Test
  public void testGetUserName() throws Exception {
  }

  @Test
  public void testIsReadOnly() throws Exception {
  }

  @Test
  public void testNullsAreSortedHigh() throws Exception {
  }

  @Test
  public void testNullsAreSortedLow() throws Exception {
  }

  @Test
  public void testNullsAreSortedAtStart() throws Exception {
  }

  @Test
  public void testNullsAreSortedAtEnd() throws Exception {
  }

  @Test
  public void testGetDatabaseProductName() throws Exception {
  }

  @Test
  public void testGetDatabaseProductVersion() throws Exception {
  }

  @Test
  public void testGetDriverName() throws Exception {
  }

  @Test
  public void testGetDriverVersion() throws Exception {
  }

  @Test
  public void testGetDriverMajorVersion() {
  }

  @Test
  public void testGetDriverMinorVersion() {
  }

  @Test
  public void testUsesLocalFiles() throws Exception {
  }

  @Test
  public void testUsesLocalFilePerTable() throws Exception {
  }

  @Test
  public void testSupportsMixedCaseIdentifiers() throws Exception {
  }

  @Test
  public void testStoresUpperCaseIdentifiers() throws Exception {
  }

  @Test
  public void testStoresLowerCaseIdentifiers() throws Exception {
  }

  @Test
  public void testStoresMixedCaseIdentifiers() throws Exception {
  }

  @Test
  public void testSupportsMixedCaseQuotedIdentifiers() throws Exception {
  }

  @Test
  public void testStoresUpperCaseQuotedIdentifiers() throws Exception {
  }

  @Test
  public void testStoresLowerCaseQuotedIdentifiers() throws Exception {
  }

  @Test
  public void testStoresMixedCaseQuotedIdentifiers() throws Exception {
  }

  @Test
  public void testGetIdentifierQuoteString() throws Exception {
  }

  @Test
  public void testGetSQLKeywords() throws Exception {
  }

  @Test
  public void testGetNumericFunctions() throws Exception {
  }

  @Test
  public void testGetStringFunctions() throws Exception {
  }

  @Test
  public void testGetSystemFunctions() throws Exception {
  }

  @Test
  public void testGetTimeDateFunctions() throws Exception {
  }

  @Test
  public void testGetSearchStringEscape() throws Exception {
  }

  @Test
  public void testGetExtraNameCharacters() throws Exception {
  }

  @Test
  public void testSupportsAlterTableWithAddColumn() throws Exception {
  }

  @Test
  public void testSupportsAlterTableWithDropColumn() throws Exception {
  }

  @Test
  public void testSupportsColumnAliasing() throws Exception {
  }

  @Test
  public void testNullPlusNonNullIsNull() throws Exception {
  }

  @Test
  public void testSupportsConvert_0args() throws Exception {
  }

  @Test
  public void testSupportsConvert_int_int() throws Exception {
  }

  @Test
  public void testSupportsTableCorrelationNames() throws Exception {
  }

  @Test
  public void testSupportsDifferentTableCorrelationNames() throws Exception {
  }

  @Test
  public void testSupportsExpressionsInOrderBy() throws Exception {
  }

  @Test
  public void testSupportsOrderByUnrelated() throws Exception {
  }

  @Test
  public void testSupportsGroupBy() throws Exception {
  }

  @Test
  public void testSupportsGroupByUnrelated() throws Exception {
  }

  @Test
  public void testSupportsGroupByBeyondSelect() throws Exception {
  }

  @Test
  public void testSupportsLikeEscapeClause() throws Exception {
  }

  @Test
  public void testSupportsMultipleResultSets() throws Exception {
  }

  @Test
  public void testSupportsMultipleTransactions() throws Exception {
  }

  @Test
  public void testSupportsNonNullableColumns() throws Exception {
  }

  @Test
  public void testSupportsMinimumSQLGrammar() throws Exception {
  }

  @Test
  public void testSupportsCoreSQLGrammar() throws Exception {
  }

  @Test
  public void testSupportsExtendedSQLGrammar() throws Exception {
  }

  @Test
  public void testSupportsANSI92EntryLevelSQL() throws Exception {
  }

  @Test
  public void testSupportsANSI92IntermediateSQL() throws Exception {
  }

  @Test
  public void testSupportsANSI92FullSQL() throws Exception {
  }

  @Test
  public void testSupportsIntegrityEnhancementFacility() throws Exception {
  }

  @Test
  public void testSupportsOuterJoins() throws Exception {
  }

  @Test
  public void testSupportsFullOuterJoins() throws Exception {
  }

  @Test
  public void testSupportsLimitedOuterJoins() throws Exception {
  }

  @Test
  public void testGetSchemaTerm() throws Exception {
  }

  @Test
  public void testGetProcedureTerm() throws Exception {
  }

  @Test
  public void testGetCatalogTerm() throws Exception {
  }

  @Test
  public void testIsCatalogAtStart() throws Exception {
  }

  @Test
  public void testGetCatalogSeparator() throws Exception {
  }

  @Test
  public void testSupportsSchemasInDataManipulation() throws Exception {
  }

  @Test
  public void testSupportsSchemasInProcedureCalls() throws Exception {
  }

  @Test
  public void testSupportsSchemasInTableDefinitions() throws Exception {
  }

  @Test
  public void testSupportsSchemasInIndexDefinitions() throws Exception {
  }

  @Test
  public void testSupportsSchemasInPrivilegeDefinitions() throws Exception {
  }

  @Test
  public void testSupportsCatalogsInDataManipulation() throws Exception {
  }

  @Test
  public void testSupportsCatalogsInProcedureCalls() throws Exception {
  }

  @Test
  public void testSupportsCatalogsInTableDefinitions() throws Exception {
  }

  @Test
  public void testSupportsCatalogsInIndexDefinitions() throws Exception {
  }

  @Test
  public void testSupportsCatalogsInPrivilegeDefinitions() throws Exception {
  }

  @Test
  public void testSupportsPositionedDelete() throws Exception {
  }

  @Test
  public void testSupportsPositionedUpdate() throws Exception {
  }

  @Test
  public void testSupportsSelectForUpdate() throws Exception {
  }

  @Test
  public void testSupportsStoredProcedures() throws Exception {
  }

  @Test
  public void testSupportsSubqueriesInComparisons() throws Exception {
  }

  @Test
  public void testSupportsSubqueriesInExists() throws Exception {
  }

  @Test
  public void testSupportsSubqueriesInIns() throws Exception {
  }

  @Test
  public void testSupportsSubqueriesInQuantifieds() throws Exception {
  }

  @Test
  public void testSupportsCorrelatedSubqueries() throws Exception {
  }

  @Test
  public void testSupportsUnion() throws Exception {
  }

  @Test
  public void testSupportsUnionAll() throws Exception {
  }

  @Test
  public void testSupportsOpenCursorsAcrossCommit() throws Exception {
  }

  @Test
  public void testSupportsOpenCursorsAcrossRollback() throws Exception {
  }

  @Test
  public void testSupportsOpenStatementsAcrossCommit() throws Exception {
  }

  @Test
  public void testSupportsOpenStatementsAcrossRollback() throws Exception {
  }

  @Test
  public void testGetMaxBinaryLiteralLength() throws Exception {
  }

  @Test
  public void testGetMaxCharLiteralLength() throws Exception {
  }

  @Test
  public void testGetMaxColumnNameLength() throws Exception {
  }

  @Test
  public void testGetMaxColumnsInGroupBy() throws Exception {
  }

  @Test
  public void testGetMaxColumnsInIndex() throws Exception {
  }

  @Test
  public void testGetMaxColumnsInOrderBy() throws Exception {
  }

  @Test
  public void testGetMaxColumnsInSelect() throws Exception {
  }

  @Test
  public void testGetMaxColumnsInTable() throws Exception {
  }

  @Test
  public void testGetMaxConnections() throws Exception {
  }

  @Test
  public void testGetMaxCursorNameLength() throws Exception {
  }

  @Test
  public void testGetMaxIndexLength() throws Exception {
  }

  @Test
  public void testGetMaxSchemaNameLength() throws Exception {
  }

  @Test
  public void testGetMaxProcedureNameLength() throws Exception {
  }

  @Test
  public void testGetMaxCatalogNameLength() throws Exception {
  }

  @Test
  public void testGetMaxRowSize() throws Exception {
  }

  @Test
  public void testDoesMaxRowSizeIncludeBlobs() throws Exception {
  }

  @Test
  public void testGetMaxStatementLength() throws Exception {
  }

  @Test
  public void testGetMaxStatements() throws Exception {
  }

  @Test
  public void testGetMaxTableNameLength() throws Exception {
  }

  @Test
  public void testGetMaxTablesInSelect() throws Exception {
  }

  @Test
  public void testGetMaxUserNameLength() throws Exception {
  }

  @Test
  public void testGetDefaultTransactionIsolation() throws Exception {
  }

  @Test
  public void testSupportsTransactions() throws Exception {
  }

  @Test
  public void testSupportsTransactionIsolationLevel() throws Exception {
  }

  @Test
  public void testSupportsDataDefinitionAndDataManipulationTransactions() throws Exception {
  }

  @Test
  public void testSupportsDataManipulationTransactionsOnly() throws Exception {
  }

  @Test
  public void testDataDefinitionCausesTransactionCommit() throws Exception {
  }

  @Test
  public void testDataDefinitionIgnoredInTransactions() throws Exception {
  }

  @Test
  public void testGetProcedures() throws Exception {
  }

  @Test
  public void testGetProcedureColumns() throws Exception {
  }

  @Test
  public void testGetTables() throws Exception {
    ResultSet rs = metaData.getTables(null, "DatabaseMetaData_%", "%", null);
    String tableTypeName = null;
    int r = 0;
    while (rs.next()) {
      assert (rs.getString(1) == null);
      assert (rs.getString(2).startsWith("DatabaseMetaData_"));
      assert (rs.getString(3).equals("TestSheet" + (r + 1)));
      assert (rs.getString(4).equals("TABLE"));
      r++;
    }
    rs.close();

    assert (r == 3);
  }

  @Test
  public void testGetSchemas_0args() throws Exception {
    ResultSet rs = metaData.getSchemas();
    String schemaName = null;
    String catalogName = null;
    int r = 0;
    while (rs.next()) {
      schemaName = rs.getString(1);
      catalogName = rs.getString(2);
      r++;
    }
    rs.close();

    assert (r == 1);
    assert (schemaName.startsWith("DatabaseMetaData_"));
    assert (catalogName == null);
  }

  @Test
  public void testGetCatalogs() throws Exception {
    ResultSet rs = metaData.getCatalogs();
    String catalogName = null;
    int r = 0;
    while (rs.next()) {
      catalogName = rs.getString(1);
      r++;
    }
    rs.close();
    Assert.assertEquals("Number of found Catalogues", 0, r);
    assert (catalogName == null);
  }

  @Test
  public void testGetTableTypes() throws Exception {
    ResultSet rs = metaData.getTableTypes();
    String tableTypeName = null;
    int r = 0;
    while (rs.next()) {
      tableTypeName = rs.getString(1);
      r++;
    }
    rs.close();

    assert (r == 1);
    assert (tableTypeName.equals("TABLE"));
  }

  @Test
  public void testGetColumns() throws Exception {
    ResultSet rs = metaData.getColumns(null, "DatabaseMetaData_%", "TestSheet1", "%");
    int r = 0;

    AT_Context ctx = new AT_Context();
    ctx.setWidth(120);

    AsciiTable at = new AsciiTable(ctx);
    at.addRule();
    at.addRow("TABLE_CAT", "TABLE_SCHEM", "TABLE_NAME", "COLUMN_NAME", "TYPE_NAME", "Length", "Prec.", "Nullable");

    while (rs.next()) {
      at.addRule();

      String catalogName = rs.getString(1);
      String schemaName = rs.getString(2);
      String tableName = rs.getString(3);
      String columnName = rs.getString(4);
      int dataType = rs.getInt(5);
      String typeName = rs.getString(6);
      int columnSize = rs.getInt(7);
      int decimalDigits = rs.getInt(9);
      int nullable = rs.getInt(11);

      at.addRow(catalogName, schemaName, tableName, columnName, typeName, columnSize, decimalDigits, nullable);

      r++;
    }
    at.addRule();
    rs.close();

    System.out.println(at.render());

    Assert.assertEquals("Numbers of Columns in Table", columnNames.length, r);
  }

  @Test
  public void testGetColumnPrivileges() throws Exception {
  }

  @Test
  public void testGetTablePrivileges() throws Exception {
  }

  @Test
  public void testGetBestRowIdentifier() throws Exception {
  }

  @Test
  public void testGetVersionColumns() throws Exception {
  }

  @Test
  public void testGetPrimaryKeys() throws Exception {
  }

  @Test
  public void testGetImportedKeys() throws Exception {
  }

  @Test
  public void testGetExportedKeys() throws Exception {
  }

  @Test
  public void testGetCrossReference() throws Exception {
  }

  @Test
  public void testGetTypeInfo() throws Exception {
  }

  @Test
  public void testGetIndexInfo() throws Exception {
  }

  @Test
  public void testSupportsResultSetType() throws Exception {
  }

  @Test
  public void testSupportsResultSetConcurrency() throws Exception {
  }

  @Test
  public void testOwnUpdatesAreVisible() throws Exception {
  }

  @Test
  public void testOwnDeletesAreVisible() throws Exception {
  }

  @Test
  public void testOwnInsertsAreVisible() throws Exception {
  }

  @Test
  public void testOthersUpdatesAreVisible() throws Exception {
  }

  @Test
  public void testOthersDeletesAreVisible() throws Exception {
  }

  @Test
  public void testOthersInsertsAreVisible() throws Exception {
  }

  @Test
  public void testUpdatesAreDetected() throws Exception {
  }

  @Test
  public void testDeletesAreDetected() throws Exception {
  }

  @Test
  public void testInsertsAreDetected() throws Exception {
  }

  @Test
  public void testSupportsBatchUpdates() throws Exception {
  }

  @Test
  public void testGetUDTs() throws Exception {
  }

  @Test
  public void testGetConnection() throws Exception {
  }

  @Test
  public void testSupportsSavepoints() throws Exception {
  }

  @Test
  public void testSupportsNamedParameters() throws Exception {
  }

  @Test
  public void testSupportsMultipleOpenResults() throws Exception {
  }

  @Test
  public void testSupportsGetGeneratedKeys() throws Exception {
  }

  @Test
  public void testGetSuperTypes() throws Exception {
  }

  @Test
  public void testGetSuperTables() throws Exception {
  }

  @Test
  public void testGetAttributes() throws Exception {
  }

  @Test
  public void testSupportsResultSetHoldability() throws Exception {
  }

  @Test
  public void testGetResultSetHoldability() throws Exception {
  }

  @Test
  public void testGetDatabaseMajorVersion() throws Exception {
  }

  @Test
  public void testGetDatabaseMinorVersion() throws Exception {
  }

  @Test
  public void testGetJDBCMajorVersion() throws Exception {
  }

  @Test
  public void testGetJDBCMinorVersion() throws Exception {
  }

  @Test
  public void testGetSQLStateType() throws Exception {
  }

  @Test
  public void testLocatorsUpdateCopy() throws Exception {
  }

  @Test
  public void testSupportsStatementPooling() throws Exception {
  }

  @Test
  public void testGetRowIdLifetime() throws Exception {
  }

  @Test
  public void testGetSchemas_String_String() throws Exception {
  }

  @Test
  public void testSupportsStoredFunctionsUsingCallSyntax() throws Exception {
  }

  @Test
  public void testAutoCommitFailureClosesAllResultSets() throws Exception {
  }

  @Test
  public void testGetClientInfoProperties() throws Exception {
  }

  @Test
  public void testGetFunctions() throws Exception {
  }

  @Test
  public void testGetFunctionColumns() throws Exception {
  }

  @Test
  public void testGetPseudoColumns() throws Exception {
  }

  @Test
  public void testGeneratedKeyAlwaysReturned() throws Exception {
  }

  @Test
  public void testUnwrap() throws Exception {
  }

  @Test
  public void testIsWrapperFor() throws Exception {
  }

}
