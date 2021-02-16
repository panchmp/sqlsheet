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
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.DateFormatConverter;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.util.Locale;

import static org.junit.Assert.*;

/**
 *
 * @author Andreas Reichel <andreas@manticore-projects.com>
 */
public class XlsDatabaseMetaDataTest {

  private static final Logger logger = LoggerFactory.getLogger(XlsDatabaseMetaDataTest.class);
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

  public XlsDatabaseMetaDataTest() throws SQLException, IOException {
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
  public void testAllProceduresAreCallable() {
  }

  @Test
  public void testAllTablesAreSelectable() {
  }

  @Test
  public void testGetURL() {
  }

  @Test
  public void testGetUserName() {
  }

  @Test
  public void testIsReadOnly() {
  }

  @Test
  public void testNullsAreSortedHigh() {
  }

  @Test
  public void testNullsAreSortedLow() {
  }

  @Test
  public void testNullsAreSortedAtStart() {
  }

  @Test
  public void testNullsAreSortedAtEnd() {
  }

  @Test
  public void testGetDatabaseProductName() {
  }

  @Test
  public void testGetDatabaseProductVersion() {
  }

  @Test
  public void testGetDriverName() {
  }

  @Test
  public void testGetDriverVersion() {
  }

  @Test
  public void testGetDriverMajorVersion() {
  }

  @Test
  public void testGetDriverMinorVersion() {
  }

  @Test
  public void testUsesLocalFiles() {
  }

  @Test
  public void testUsesLocalFilePerTable() {
  }

  @Test
  public void testSupportsMixedCaseIdentifiers() {
  }

  @Test
  public void testStoresUpperCaseIdentifiers() {
  }

  @Test
  public void testStoresLowerCaseIdentifiers() {
  }

  @Test
  public void testStoresMixedCaseIdentifiers() {
  }

  @Test
  public void testSupportsMixedCaseQuotedIdentifiers() {
  }

  @Test
  public void testStoresUpperCaseQuotedIdentifiers() {
  }

  @Test
  public void testStoresLowerCaseQuotedIdentifiers() {
  }

  @Test
  public void testStoresMixedCaseQuotedIdentifiers() {
  }

  @Test
  public void testGetIdentifierQuoteString() {
  }

  @Test
  public void testGetSQLKeywords() {
  }

  @Test
  public void testGetNumericFunctions() {
  }

  @Test
  public void testGetStringFunctions() {
  }

  @Test
  public void testGetSystemFunctions() {
  }

  @Test
  public void testGetTimeDateFunctions() {
  }

  @Test
  public void testGetSearchStringEscape() {
  }

  @Test
  public void testGetExtraNameCharacters() {
  }

  @Test
  public void testSupportsAlterTableWithAddColumn() {
  }

  @Test
  public void testSupportsAlterTableWithDropColumn() {
  }

  @Test
  public void testSupportsColumnAliasing() {
  }

  @Test
  public void testNullPlusNonNullIsNull() {
  }

  @Test
  public void testSupportsConvert_0args() {
  }

  @Test
  public void testSupportsConvert_int_int() {
  }

  @Test
  public void testSupportsTableCorrelationNames() {
  }

  @Test
  public void testSupportsDifferentTableCorrelationNames() {
  }

  @Test
  public void testSupportsExpressionsInOrderBy() {
  }

  @Test
  public void testSupportsOrderByUnrelated() {
  }

  @Test
  public void testSupportsGroupBy() {
  }

  @Test
  public void testSupportsGroupByUnrelated() {
  }

  @Test
  public void testSupportsGroupByBeyondSelect() {
  }

  @Test
  public void testSupportsLikeEscapeClause() {
  }

  @Test
  public void testSupportsMultipleResultSets() {
  }

  @Test
  public void testSupportsMultipleTransactions() {
  }

  @Test
  public void testSupportsNonNullableColumns() {
  }

  @Test
  public void testSupportsMinimumSQLGrammar() {
  }

  @Test
  public void testSupportsCoreSQLGrammar() {
  }

  @Test
  public void testSupportsExtendedSQLGrammar() {
  }

  @Test
  public void testSupportsANSI92EntryLevelSQL() {
  }

  @Test
  public void testSupportsANSI92IntermediateSQL() {
  }

  @Test
  public void testSupportsANSI92FullSQL() {
  }

  @Test
  public void testSupportsIntegrityEnhancementFacility() {
  }

  @Test
  public void testSupportsOuterJoins() {
  }

  @Test
  public void testSupportsFullOuterJoins() {
  }

  @Test
  public void testSupportsLimitedOuterJoins() {
  }

  @Test
  public void testGetSchemaTerm() {
  }

  @Test
  public void testGetProcedureTerm() {
  }

  @Test
  public void testGetCatalogTerm() {
  }

  @Test
  public void testIsCatalogAtStart() {
  }

  @Test
  public void testGetCatalogSeparator() {
  }

  @Test
  public void testSupportsSchemasInDataManipulation() {
  }

  @Test
  public void testSupportsSchemasInProcedureCalls() {
  }

  @Test
  public void testSupportsSchemasInTableDefinitions() {
  }

  @Test
  public void testSupportsSchemasInIndexDefinitions() {
  }

  @Test
  public void testSupportsSchemasInPrivilegeDefinitions() {
  }

  @Test
  public void testSupportsCatalogsInDataManipulation() {
  }

  @Test
  public void testSupportsCatalogsInProcedureCalls() {
  }

  @Test
  public void testSupportsCatalogsInTableDefinitions() {
  }

  @Test
  public void testSupportsCatalogsInIndexDefinitions() {
  }

  @Test
  public void testSupportsCatalogsInPrivilegeDefinitions() {
  }

  @Test
  public void testSupportsPositionedDelete() {
  }

  @Test
  public void testSupportsPositionedUpdate() {
  }

  @Test
  public void testSupportsSelectForUpdate() {
  }

  @Test
  public void testSupportsStoredProcedures() {
  }

  @Test
  public void testSupportsSubqueriesInComparisons() {
  }

  @Test
  public void testSupportsSubqueriesInExists() {
  }

  @Test
  public void testSupportsSubqueriesInIns() {
  }

  @Test
  public void testSupportsSubqueriesInQuantifieds() {
  }

  @Test
  public void testSupportsCorrelatedSubqueries() {
  }

  @Test
  public void testSupportsUnion() {
  }

  @Test
  public void testSupportsUnionAll() {
  }

  @Test
  public void testSupportsOpenCursorsAcrossCommit() {
  }

  @Test
  public void testSupportsOpenCursorsAcrossRollback() {
  }

  @Test
  public void testSupportsOpenStatementsAcrossCommit() {
  }

  @Test
  public void testSupportsOpenStatementsAcrossRollback() {
  }

  @Test
  public void testGetMaxBinaryLiteralLength() {
  }

  @Test
  public void testGetMaxCharLiteralLength() {
  }

  @Test
  public void testGetMaxColumnNameLength() {
  }

  @Test
  public void testGetMaxColumnsInGroupBy() {
  }

  @Test
  public void testGetMaxColumnsInIndex() {
  }

  @Test
  public void testGetMaxColumnsInOrderBy() {
  }

  @Test
  public void testGetMaxColumnsInSelect() {
  }

  @Test
  public void testGetMaxColumnsInTable() {
  }

  @Test
  public void testGetMaxConnections() {
  }

  @Test
  public void testGetMaxCursorNameLength() {
  }

  @Test
  public void testGetMaxIndexLength() {
  }

  @Test
  public void testGetMaxSchemaNameLength() {
  }

  @Test
  public void testGetMaxProcedureNameLength() {
  }

  @Test
  public void testGetMaxCatalogNameLength() {
  }

  @Test
  public void testGetMaxRowSize() {
  }

  @Test
  public void testDoesMaxRowSizeIncludeBlobs() {
  }

  @Test
  public void testGetMaxStatementLength() {
  }

  @Test
  public void testGetMaxStatements() {
  }

  @Test
  public void testGetMaxTableNameLength() {
  }

  @Test
  public void testGetMaxTablesInSelect() {
  }

  @Test
  public void testGetMaxUserNameLength() {
  }

  @Test
  public void testGetDefaultTransactionIsolation() {
  }

  @Test
  public void testSupportsTransactions() {
  }

  @Test
  public void testSupportsTransactionIsolationLevel() {
  }

  @Test
  public void testSupportsDataDefinitionAndDataManipulationTransactions() {
  }

  @Test
  public void testSupportsDataManipulationTransactionsOnly() {
  }

  @Test
  public void testDataDefinitionCausesTransactionCommit() {
  }

  @Test
  public void testDataDefinitionIgnoredInTransactions() {
  }

  @Test
  public void testGetProcedures() {
  }

  @Test
  public void testGetProcedureColumns() {
  }

  @Test
  public void testGetTables() throws Exception {
    ResultSet rs = metaData.getTables(null, "DatabaseMetaData_%", "%", null);
    String tableTypeName = null;
    int r = 0;
    while (rs.next()) {
      assertNull(rs.getString(1));
      assertTrue(rs.getString(2).startsWith("DatabaseMetaData_"));
      assertEquals(rs.getString(3), "TestSheet" + (r + 1));
      assertEquals("TABLE", rs.getString(4));
      r++;
    }
    rs.close();

    assertEquals(3, r);
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

    assertEquals(1, r);
    assertTrue(schemaName.startsWith("DatabaseMetaData_"));
    assertNull(catalogName);
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
    assertEquals("Number of found Catalogues", 0, r);
    assertNull(catalogName);
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

    assertEquals(1, r);
    assertEquals("TABLE", tableTypeName);
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
    logger.debug(at.render());

    assertEquals("Numbers of Columns in Table", columnNames.length, r);
  }

  @Test
  public void testGetColumnPrivileges() {
  }

  @Test
  public void testGetTablePrivileges() {
  }

  @Test
  public void testGetBestRowIdentifier() {
  }

  @Test
  public void testGetVersionColumns() {
  }

  @Test
  public void testGetPrimaryKeys() {
  }

  @Test
  public void testGetImportedKeys() {
  }

  @Test
  public void testGetExportedKeys() {
  }

  @Test
  public void testGetCrossReference() {
  }

  @Test
  public void testGetTypeInfo() {
  }

  @Test
  public void testGetIndexInfo() {
  }

  @Test
  public void testSupportsResultSetType() {
  }

  @Test
  public void testSupportsResultSetConcurrency() {
  }

  @Test
  public void testOwnUpdatesAreVisible() {
  }

  @Test
  public void testOwnDeletesAreVisible() {
  }

  @Test
  public void testOwnInsertsAreVisible() {
  }

  @Test
  public void testOthersUpdatesAreVisible() {
  }

  @Test
  public void testOthersDeletesAreVisible() {
  }

  @Test
  public void testOthersInsertsAreVisible() {
  }

  @Test
  public void testUpdatesAreDetected() {
  }

  @Test
  public void testDeletesAreDetected() {
  }

  @Test
  public void testInsertsAreDetected() {
  }

  @Test
  public void testSupportsBatchUpdates() {
  }

  @Test
  public void testGetUDTs() {
  }

  @Test
  public void testGetConnection() {
  }

  @Test
  public void testSupportsSavepoints() {
  }

  @Test
  public void testSupportsNamedParameters() {
  }

  @Test
  public void testSupportsMultipleOpenResults() {
  }

  @Test
  public void testSupportsGetGeneratedKeys() {
  }

  @Test
  public void testGetSuperTypes() {
  }

  @Test
  public void testGetSuperTables() {
  }

  @Test
  public void testGetAttributes() {
  }

  @Test
  public void testSupportsResultSetHoldability() {
  }

  @Test
  public void testGetResultSetHoldability() {
  }

  @Test
  public void testGetDatabaseMajorVersion() {
  }

  @Test
  public void testGetDatabaseMinorVersion() {
  }

  @Test
  public void testGetJDBCMajorVersion() {
  }

  @Test
  public void testGetJDBCMinorVersion() {
  }

  @Test
  public void testGetSQLStateType() {
  }

  @Test
  public void testLocatorsUpdateCopy() {
  }

  @Test
  public void testSupportsStatementPooling() {
  }

  @Test
  public void testGetRowIdLifetime() {
  }

  @Test
  public void testGetSchemas_String_String() {
  }

  @Test
  public void testSupportsStoredFunctionsUsingCallSyntax() {
  }

  @Test
  public void testAutoCommitFailureClosesAllResultSets() {
  }

  @Test
  public void testGetClientInfoProperties() {
  }

  @Test
  public void testGetFunctions() {
  }

  @Test
  public void testGetFunctionColumns() {
  }

  @Test
  public void testGetPseudoColumns() {
  }

  @Test
  public void testGeneratedKeyAlwaysReturned() {
  }

  @Test
  public void testUnwrap() {
  }

  @Test
  public void testIsWrapperFor() {
  }

}
