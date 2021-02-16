/*
 *
 *  Copyright (C) 2012 Andreas Reichel <andreas@manticore-projects.com>
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

import com.hrakaroo.glob.GlobPattern;
import com.hrakaroo.glob.MatchingEngine;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.sql.*;
import java.util.Arrays;
import java.util.LinkedList;

import static com.sqlsheet.XlsStatement.DEFAULT_FIRST_COL;
import static com.sqlsheet.XlsStatement.DEFAULT_HEADLINE;

public class XlsDatabaseMetaData implements DatabaseMetaData {

  private final XlsConnection connection;

  public XlsDatabaseMetaData(XlsConnection connection) {
    this.connection = connection;
  }

  @Override
  public boolean allProceduresAreCallable() {
    return false;
  }

  @Override
  public boolean allTablesAreSelectable() {
    return true;
  }

  @Override
  public String getURL() {
    return "jdbc:xls:file:filename[?property1=value[&property2=value]]";
  }

  @Override
  public String getUserName() {
    return "";
  }

  @Override
  public boolean isReadOnly() {
    return false;
  }

  @Override
  public boolean nullsAreSortedHigh() {
    return true;
  }

  @Override
  public boolean nullsAreSortedLow() {
    return false;
  }

  @Override
  public boolean nullsAreSortedAtStart() {
    return true;
  }

  @Override
  public boolean nullsAreSortedAtEnd() {
    return false;
  }

  @Override
  public String getDatabaseProductName() {
    return "Microsoft Excel";
  }

  @Override
  public String getDatabaseProductVersion() {
    return (connection.workbook instanceof XSSFWorkbook) ? "2007" : "97";
  }

  @Override
  public String getDriverName() {
    return "SQLSheet";
  }

  @Override
  public String getDriverVersion() {
    return "7.2.0";
  }

  @Override
  public int getDriverMajorVersion() {
    return 7;
  }

  @Override
  public int getDriverMinorVersion() {
    return 2;
  }

  @Override
  public boolean usesLocalFiles() {
    return true;
  }

  @Override
  public boolean usesLocalFilePerTable() {
    return false;
  }

  @Override
  public boolean supportsMixedCaseIdentifiers() {
    return true;
  }

  @Override
  public boolean storesUpperCaseIdentifiers() {
    return false;
  }

  @Override
  public boolean storesLowerCaseIdentifiers() {
    return false;
  }

  @Override
  public boolean storesMixedCaseIdentifiers() {
    return true;
  }

  @Override
  public boolean supportsMixedCaseQuotedIdentifiers() {
    return true;
  }

  @Override
  public boolean storesUpperCaseQuotedIdentifiers() {
    return false;
  }

  @Override
  public boolean storesLowerCaseQuotedIdentifiers() {
    return false;
  }

  @Override
  public boolean storesMixedCaseQuotedIdentifiers() {
    return true;
  }

  @Override
  public String getIdentifierQuoteString() {
    return "\"";
  }

  @Override
  public String getSQLKeywords() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  @Override
  public String getNumericFunctions() {
    return "";
  }

  @Override
  public String getStringFunctions() {
    return "";
  }

  @Override
  public String getSystemFunctions() {
    return "";
  }

  @Override
  public String getTimeDateFunctions() {
    return "";
  }

  @Override
  public String getSearchStringEscape() {
    return "";
  }

  @Override
  public String getExtraNameCharacters() {
    return "";
  }

  @Override
  public boolean supportsAlterTableWithAddColumn() {
    return false;
  }

  @Override
  public boolean supportsAlterTableWithDropColumn() {
    return false;
  }

  @Override
  public boolean supportsColumnAliasing() {
    return false;
  }

  @Override
  public boolean nullPlusNonNullIsNull() {
    return false;
  }

  @Override
  public boolean supportsConvert() {
    return false;
  }

  @Override
  public boolean supportsConvert(int i, int i1) {
    return false;
  }

  @Override
  public boolean supportsTableCorrelationNames() {
    return false;
  }

  @Override
  public boolean supportsDifferentTableCorrelationNames() {
    return false;
  }

  @Override
  public boolean supportsExpressionsInOrderBy() {
    return false;
  }

  @Override
  public boolean supportsOrderByUnrelated() {
    return false;
  }

  @Override
  public boolean supportsGroupBy() {
    return false;
  }

  @Override
  public boolean supportsGroupByUnrelated() {
    return false;
  }

  @Override
  public boolean supportsGroupByBeyondSelect() {
    return false;
  }

  @Override
  public boolean supportsLikeEscapeClause() {
    return false;
  }

  @Override
  public boolean supportsMultipleResultSets() {
    return false;
  }

  @Override
  public boolean supportsMultipleTransactions() {
    return false;
  }

  @Override
  public boolean supportsNonNullableColumns() {
    return false;
  }

  @Override
  public boolean supportsMinimumSQLGrammar() {
    return true;
  }

  @Override
  public boolean supportsCoreSQLGrammar() {
    return false;
  }

  @Override
  public boolean supportsExtendedSQLGrammar() {
    return false;
  }

  @Override
  public boolean supportsANSI92EntryLevelSQL() {
    return false;
  }

  @Override
  public boolean supportsANSI92IntermediateSQL() {
    return false;
  }

  @Override
  public boolean supportsANSI92FullSQL() {
    return false;
  }

  @Override
  public boolean supportsIntegrityEnhancementFacility() {
    return false;
  }

  @Override
  public boolean supportsOuterJoins() {
    return false;
  }

  @Override
  public boolean supportsFullOuterJoins() {
    return false;
  }

  @Override
  public boolean supportsLimitedOuterJoins() {
    return false;
  }

  @Override
  public String getSchemaTerm() {
    return "Filename";
  }

  @Override
  public String getProcedureTerm() {
    return "Procedure";
  }

  @Override
  public String getCatalogTerm() {
    return "Folder";
  }

  @Override
  public boolean isCatalogAtStart() {
    return false;
  }

  @Override
  public String getCatalogSeparator() {
    return ".";
  }

  @Override
  public boolean supportsSchemasInDataManipulation() {
    return false;
  }

  @Override
  public boolean supportsSchemasInProcedureCalls() {
    return false;
  }

  @Override
  public boolean supportsSchemasInTableDefinitions() {
    return false;
  }

  @Override
  public boolean supportsSchemasInIndexDefinitions() {
    return false;
  }

  @Override
  public boolean supportsSchemasInPrivilegeDefinitions() {
    return false;
  }

  @Override
  public boolean supportsCatalogsInDataManipulation() {
    return false;
  }

  @Override
  public boolean supportsCatalogsInProcedureCalls() {
    return false;
  }

  @Override
  public boolean supportsCatalogsInTableDefinitions() {
    return false;
  }

  @Override
  public boolean supportsCatalogsInIndexDefinitions() {
    return false;
  }

  @Override
  public boolean supportsCatalogsInPrivilegeDefinitions() {
    return false;
  }

  @Override
  public boolean supportsPositionedDelete() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  @Override
  public boolean supportsPositionedUpdate() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  @Override
  public boolean supportsSelectForUpdate() {
    return false;
  }

  @Override
  public boolean supportsStoredProcedures() {
    return false;
  }

  @Override
  public boolean supportsSubqueriesInComparisons() {
    return false;
  }

  @Override
  public boolean supportsSubqueriesInExists() {
    return false;
  }

  @Override
  public boolean supportsSubqueriesInIns() {
    return false;
  }

  @Override
  public boolean supportsSubqueriesInQuantifieds() {
    return false;
  }

  @Override
  public boolean supportsCorrelatedSubqueries() {
    return false;
  }

  @Override
  public boolean supportsUnion() {
    return false;
  }

  @Override
  public boolean supportsUnionAll() {
    return false;
  }

  @Override
  public boolean supportsOpenCursorsAcrossCommit() {
    return false;
  }

  @Override
  public boolean supportsOpenCursorsAcrossRollback() {
    return false;
  }

  @Override
  public boolean supportsOpenStatementsAcrossCommit() {
    return false;
  }

  @Override
  public boolean supportsOpenStatementsAcrossRollback() {
    return false;
  }

  @Override
  public int getMaxBinaryLiteralLength() {
    return 0;
  }

  @Override
  public int getMaxCharLiteralLength() {
    // @todo: check and set the correct value
    return 256;
  }

  @Override
  public int getMaxColumnNameLength() {
    // @todo: check and set the correct value
    return 256;
  }

  @Override
  public int getMaxColumnsInGroupBy() {
    return 0;
  }

  @Override
  public int getMaxColumnsInIndex() {
    return 0;
  }

  @Override
  public int getMaxColumnsInOrderBy() {
    return 0;
  }

  @Override
  public int getMaxColumnsInSelect() {
    // @todo: check and set the correct value, we accept only '*' so far
    return 0;
  }

  @Override
  public int getMaxColumnsInTable() {
    // @todo: check and set the correct value
    return 256;
  }

  @Override
  public int getMaxConnections() {
    // @todo: check and set the correct value
    return 1024;
  }

  @Override
  public int getMaxCursorNameLength() {
    return 0;
  }

  @Override
  public int getMaxIndexLength() {
    return 0;
  }

  @Override
  public int getMaxSchemaNameLength() {
    // @todo: check and set the correct value
    return 256;
  }

  @Override
  public int getMaxProcedureNameLength() {
    return 0;
  }

  @Override
  public int getMaxCatalogNameLength() {
    // @todo: check and set the correct value
    return 256;
  }

  @Override
  public int getMaxRowSize() {
    // @todo: obeye the correct connection's ROW OFFSET
    return (connection.getWorkBook() instanceof XSSFWorkbook)
        ? SpreadsheetVersion.EXCEL2007.getMaxRows()
        : SpreadsheetVersion.EXCEL97.getMaxRows();
  }

  @Override
  public boolean doesMaxRowSizeIncludeBlobs() {
    return true;
  }

  @Override
  public int getMaxStatementLength() {
    // @todo: check and set the correct value
    return Integer.MAX_VALUE;
  }

  @Override
  public int getMaxStatements() {
    // @todo: check and set the correct value
    return Integer.MAX_VALUE;
  }

  @Override
  public int getMaxTableNameLength() {
    // @todo: check and set the correct value
    return 32;
  }

  @Override
  public int getMaxTablesInSelect() {
    return 1;
  }

  @Override
  public int getMaxUserNameLength() {
    return 0;
  }

  @Override
  public int getDefaultTransactionIsolation() {
    return java.sql.Connection.TRANSACTION_NONE;
  }

  @Override
  public boolean supportsTransactions() {
    return false;
  }

  @Override
  public boolean supportsTransactionIsolationLevel(int i) {
    return (java.sql.Connection.TRANSACTION_NONE == i);
  }

  @Override
  public boolean supportsDataDefinitionAndDataManipulationTransactions() {
    return false;
  }

  @Override
  public boolean supportsDataManipulationTransactionsOnly() {
    return false;
  }

  @Override
  public boolean dataDefinitionCausesTransactionCommit() {
    return false;
  }

  @Override
  public boolean dataDefinitionIgnoredInTransactions() {
    return false;
  }

  @Override
  public ResultSet getProcedures(String string, String string1, String string2)
      throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  @Override
  public ResultSet getProcedureColumns(
      String string, String string1, String string2, String string3) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  @Override
  public ResultSet getTables(
      String catalog, String schemaPattern, String tableNamePattern, String[] types)
      throws SQLException {

    ResultSetImpl resultSet =
        new ResultSetImpl(
            new Object[] {"TABLE_CAT", String.class},
            new Object[] {"TABLE_SCHEM", String.class},
            new Object[] {"TABLE_NAME", String.class},
            new Object[] {"TABLE_TYPE", String.class},
            new Object[] {"REMARKS", String.class},
            new Object[] {"TYPE_CAT", String.class},
            new Object[] {"TYPE_SCHEM", String.class},
            new Object[] {"TYPE_NAME", String.class},
            new Object[] {"SELF_REFERENCING_COL_NAME", String.class},
            new Object[] {"REF_GENERATION", String.class});

    MatchingEngine schemaMatcher =
        GlobPattern.compile(
            schemaPattern != null ? schemaPattern : "%", '%', '_', GlobPattern.HANDLE_ESCAPES);
    MatchingEngine tableNameMatcher =
        GlobPattern.compile(
            tableNamePattern != null ? tableNamePattern : "%",
            '%',
            '_',
            GlobPattern.HANDLE_ESCAPES);

    String fileName = connection.saveFile.getName();
    int indexOf = fileName.indexOf('.');
    if (indexOf >= 0) fileName = fileName.substring(0, indexOf);

    if ((schemaPattern == null || schemaMatcher.matches(fileName))
        && (types == null || Arrays.asList(types).contains("TABLE")))
      for (int i = 0; i < connection.workbook.getNumberOfSheets(); i++) {
        String tableName = connection.workbook.getSheetName(i);

        if (!tableName.startsWith("!") && tableNameMatcher.matches(tableName))
          resultSet.addRow(
              null,
              fileName,
              connection.workbook.getSheetName(i),
              "TABLE",
              "",
              null,
              null,
              null,
              null,
              null);
      }
    return resultSet;
  }

  @Override
  public ResultSet getSchemas() throws SQLException {
    ResultSetImpl resultSet =
        new ResultSetImpl(
            new Object[] {"TABLE_SCHEM", String.class},
            new Object[] {"TABLE_CATALOG", String.class});

    String fileName = connection.saveFile.getName();
    int indexOf = fileName.indexOf('.');
    if (indexOf >= 0) fileName = fileName.substring(0, indexOf);
    resultSet.addRow(fileName, null);

    return resultSet;
  }

  @Override
  public ResultSet getCatalogs() throws SQLException {
    return new ResultSetImpl(new Object[] {"TABLE_CAT", String.class});
  }

  @Override
  public ResultSet getTableTypes() throws SQLException {
    ResultSetImpl resultSet = new ResultSetImpl(new Object[] {"TABLE_TYPE", String.class});
    resultSet.addRow("TABLE");
    return resultSet;
  }

  @Override
  public ResultSet getColumns(
      String catalog, String schemaPattern, String tableNamePattern, String columnNamePattern)
      throws SQLException {

    ResultSetImpl resultSet =
        new ResultSetImpl(
            new Object[] {"TABLE_CAT", String.class},
            new Object[] {"TABLE_SCHEM", String.class},
            new Object[] {"TABLE_NAME", String.class},
            new Object[] {"COLUMN_NAME", String.class},
            new Object[] {"DATA_TYPE", Integer.class},
            new Object[] {"TYPE_NAME", String.class},
            new Object[] {"COLUMN_SIZE", Integer.class},
            new Object[] {"BUFFER_LENGTH", String.class},
            new Object[] {"DECIMAL_DIGITS", Integer.class},
            new Object[] {"NUM_PREC_RADIX", Integer.class},
            new Object[] {"NULLABLE", Integer.class},
            new Object[] {"REMARKS", String.class},
            new Object[] {"COLUMN_DEF", String.class},
            new Object[] {"SQL_DATA_TYPE", Integer.class},
            new Object[] {"SQL_DATETIME_SUB", Integer.class},
            new Object[] {"CHAR_OCTET_LENGTH", Integer.class},
            new Object[] {"ORDINAL_POSITION", Integer.class},
            new Object[] {"IS_NULLABLE", String.class},
            new Object[] {"SCOPE_CATALOG", String.class},
            new Object[] {"SCOPE_SCHEMA", String.class},
            new Object[] {"SCOPE_TABLE", String.class},
            new Object[] {"SOURCE_DATA_TYPE", Short.class},
            new Object[] {"IS_AUTOINCREMENT", String.class},
            new Object[] {"IS_GENERATEDCOLUMN", String.class});

    String fileName = connection.saveFile.getName();
    int indexOf = fileName.indexOf('.');
    if (indexOf >= 0) fileName = fileName.substring(0, indexOf);

    int firstSheetRowOffset = connection.getInt(XlsDriver.HEADLINE, DEFAULT_HEADLINE);
    int firstSheetColOffset = connection.getInt(XlsDriver.FIRST_COL, DEFAULT_FIRST_COL);

    MatchingEngine schemaMatcher =
        GlobPattern.compile(
            schemaPattern != null ? schemaPattern : "%", '%', '_', GlobPattern.HANDLE_ESCAPES);
    MatchingEngine tableNameMatcher =
        GlobPattern.compile(
            tableNamePattern != null ? tableNamePattern : "%",
            '%',
            '_',
            GlobPattern.HANDLE_ESCAPES);
    MatchingEngine columnNameMatcher =
        GlobPattern.compile(
            columnNamePattern != null ? columnNamePattern : "%",
            '%',
            '_',
            GlobPattern.HANDLE_ESCAPES);

    if (schemaPattern == null || schemaMatcher.matches(fileName))
      for (int i = 0; i < connection.workbook.getNumberOfSheets(); i++) {
        Sheet sheet = connection.workbook.getSheetAt(i);
        String tableName = connection.workbook.getSheetName(i);

        if (tableNameMatcher.matches(tableName)) {
          LinkedList<String> columnNames = new LinkedList<>();
          LinkedList<Integer> columnTypes = new LinkedList<>();
          DataFormatter formatter = new DataFormatter();

          int r = firstSheetRowOffset - 1;

          Row row = sheet.getRow(r);
          if (row == null) throw new SQLException("No header row in sheet");
          for (short c = (short) firstSheetColOffset; c < row.getLastCellNum(); c++) {
            Cell cell = row.getCell(c);
            String columnName = formatter.formatCellValue(cell);

            // Is it unique in the column name set
            int suffix;
            while (columnNames.contains(columnName)) {
              suffix = 1;
              columnName += "_" + suffix;
            }
            columnNames.add(columnName);
          }

          row = sheet.getRow(++r);
          if (row == null)
            for (short c = (short) firstSheetColOffset;
                c < (short) firstSheetColOffset + columnNames.size();
                c++) {
              CellStyle columnStyle = sheet.getColumnStyle(c);
              short dataFormat = columnStyle.getDataFormat();
              // @todo: set the actual type based on the formats
              columnTypes.add(Types.VARCHAR);
            }
          else
            for (short c = (short) firstSheetColOffset;
                c < (short) firstSheetColOffset + columnNames.size();
                c++) {
              Cell cell = row.getCell(c);
              if (cell != null) {
                int typeCode;
                CellType excelCellType = cell.getCellType();
                switch (excelCellType) {
                  case BOOLEAN:
                    typeCode = Types.BOOLEAN;
                    break;
                  case STRING:
                    typeCode = Types.VARCHAR;
                    break;
                  case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) typeCode = Types.DATE;
                    else typeCode = Types.DOUBLE;
                    break;
                  case BLANK:
                    typeCode = Types.NULL;
                    break;
                  case FORMULA:
                    try {
                      cell.getStringCellValue();
                      typeCode = Types.VARCHAR;
                    } catch (Exception e) {
                      cell.getNumericCellValue();
                      typeCode = Types.DOUBLE;
                    }
                    break;
                  case ERROR:
                    throw new RuntimeException(
                        "The ExcelType ( ERROR ) is not supported - Cell (" + r + "," + c + ")");

                  default:
                    throw new RuntimeException(
                        "The ExcelType ("
                            + excelCellType
                            + ") is not supported - Cell ("
                            + r
                            + ","
                            + c
                            + ")");
                }
                columnTypes.add(typeCode);
              } else {
                CellStyle columnStyle = sheet.getColumnStyle(c);
                short dataFormat = columnStyle.getDataFormat();
                // @todo: set the actual type based on the formats

                columnTypes.add(Types.VARCHAR);
              }
            }

          for (int c = 0; c < columnNames.size(); c++)
            if (columnNameMatcher.matches(columnNames.get(c))) {

              String typeName;
              int columnSize;
              int decimalDigits;

              switch (columnTypes.get(c)) {
                case Types.BOOLEAN:
                  typeName = Boolean.class.getName();
                  // @todo: check if 1 is correct
                  columnSize = 1;
                  decimalDigits = 0;
                  break;
                case Types.DATE:
                  typeName = java.sql.Date.class.getName();
                  // @todo: check if 8 is correct
                  columnSize = 8;
                  decimalDigits = 0;
                  break;
                case Types.DOUBLE:
                  typeName = Double.class.getName();
                  columnSize = Double.BYTES;
                  decimalDigits = 15;
                  break;
                default:
                  typeName = String.class.getName();
                  columnSize = 4096;
                  decimalDigits = 0;
                  break;
              }

              resultSet.addRow(
                  null, // TABLE_CAT
                  fileName, // TABLE_SCHEM
                  tableName, // TABLE_NAME
                  columnNames.get(c), // COLUMN_NAME
                  columnTypes.get(c), // DATA_TYPE
                  typeName, // TYPE_NAME
                  columnSize, // COLUMN_SIZE
                  null, // BUFFER_LENGTH
                  decimalDigits, // DECIMAL_DIGITS
                  10, // NUM_PREC_RADIX
                  DatabaseMetaData.columnNullable, // NULLABLE
                  "", // REMARKS
                  null, // COLUMN_DEF String => default value for the column, which should be
                        // interpreted as a string when the value is enclosed in single quotes (may
                        // be null)
                  null, // SQL_DATA_TYPE int => unused
                  null, // SQL_DATETIME_SUB int => unused
                  4096, // CHAR_OCTET_LENGTH int => for char types the maximum number of bytes in
                        // the column
                  c, // ORDINAL_POSITION
                  "YES", // String => ISO rules are used to determine the nullability for a column.
                  null, // SCOPE_CATALOG
                  null, // SCOPE_SCHEMA
                  null, // SCOPE_TABLE
                  null, // SOURCE_DATA_TYPE
                  "NO", // IS_AUTOINCREMENT String => Indicates whether this column is auto
                        // incremented
                  "NO" // IS_GENERATEDCOLUMN String => Indicates whether this is a generated column
                  );
            }
        }
      }
    return resultSet;
  }

  @Override
  public ResultSet getColumnPrivileges(
      String string, String string1, String string2, String string3) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  @Override
  public ResultSet getTablePrivileges(String string, String string1, String string2)
      throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  @Override
  public ResultSet getBestRowIdentifier(
      String string, String string1, String string2, int i, boolean bln) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  @Override
  public ResultSet getVersionColumns(String string, String string1, String string2)
      throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  @Override
  public ResultSet getPrimaryKeys(String catalog, String schema, String table) throws SQLException {
    return new ResultSetImpl(
        new Object[] {"TABLE_CAT", String.class},
        new Object[] {"TABLE_SCHEM", String.class},
        new Object[] {"TABLE_NAME", String.class},
        new Object[] {"COLUMN_NAME", String.class},
        new Object[] {"KEY_SEQ", Short.class},
        new Object[] {"PK_NAME", String.class});
  }

  @Override
  public ResultSet getImportedKeys(String catalog, String schema, String table)
      throws SQLException {
    return new ResultSetImpl(
        new Object[] {"PKTABLE_CAT", String.class},
        new Object[] {"PKTABLE_SCHEM", String.class},
        new Object[] {"PKTABLE_NAME", String.class},
        new Object[] {"PKCOLUMN_NAME", String.class},
        new Object[] {"FKTABLE_CAT", String.class},
        new Object[] {"FKTABLE_SCHEM", String.class},
        new Object[] {"FKTABLE_NAME", String.class},
        new Object[] {"FKCOLUMN_NAME", String.class},
        new Object[] {"KEY_SEQ", Short.class},
        new Object[] {"UPDATE_RULE", Short.class},
        new Object[] {"DELETE_RULE", Short.class},
        new Object[] {"FK_NAME", String.class},
        new Object[] {"PK_NAME", String.class},
        new Object[] {"DEFERRABILITY", Short.class});
  }

  @Override
  public ResultSet getExportedKeys(String catalog, String schema, String table)
      throws SQLException {
    return new ResultSetImpl(
        new Object[] {"PKTABLE_CAT", String.class},
        new Object[] {"PKTABLE_SCHEM", String.class},
        new Object[] {"PKTABLE_NAME", String.class},
        new Object[] {"PKCOLUMN_NAME", String.class},
        new Object[] {"FKTABLE_CAT", String.class},
        new Object[] {"FKTABLE_SCHEM", String.class},
        new Object[] {"FKTABLE_NAME", String.class},
        new Object[] {"FKCOLUMN_NAME", String.class},
        new Object[] {"KEY_SEQ", Short.class},
        new Object[] {"UPDATE_RULE", Short.class},
        new Object[] {"DELETE_RULE", Short.class},
        new Object[] {"FK_NAME", String.class},
        new Object[] {"PK_NAME", String.class},
        new Object[] {"DEFERRABILITY", Short.class});
  }

  @Override
  public ResultSet getCrossReference(
      String parentCatalog,
      String parentSchema,
      String parentTable,
      String foreignCatalog,
      String foreignSchema,
      String foreignTable)
      throws SQLException {
    return new ResultSetImpl(
        new Object[] {"PKTABLE_CAT", String.class},
        new Object[] {"PKTABLE_SCHEM", String.class},
        new Object[] {"PKTABLE_NAME", String.class},
        new Object[] {"PKCOLUMN_NAME", String.class},
        new Object[] {"FKTABLE_CAT", String.class},
        new Object[] {"FKTABLE_SCHEM", String.class},
        new Object[] {"FKTABLE_NAME", String.class},
        new Object[] {"FKCOLUMN_NAME", String.class},
        new Object[] {"KEY_SEQ", Short.class},
        new Object[] {"UPDATE_RULE", Short.class},
        new Object[] {"DELETE_RULE", Short.class},
        new Object[] {"FK_NAME", String.class},
        new Object[] {"PK_NAME", String.class},
        new Object[] {"DEFERRABILITY", Short.class});
  }

  @Override
  public ResultSet getTypeInfo() throws SQLException {
    ResultSetImpl resultSet =
        new ResultSetImpl(
            new Object[] {"TYPE_NAME", String.class},
            new Object[] {"DATA_TYPE", Integer.class},
            new Object[] {"PRECISION", Integer.class},
            new Object[] {"LITERAL_PREFIX", String.class},
            new Object[] {"LITERAL_SUFFIX", String.class},
            new Object[] {"CREATE_PARAMS", String.class},
            new Object[] {"NULLABLE", Short.class},
            new Object[] {"CASE_SENSITIVE", Boolean.class},
            new Object[] {"SEARCHABLE", String.class},
            new Object[] {"UNSIGNED_ATTRIBUTE", Boolean.class},
            new Object[] {"FIXED_PREC_SCALE", Boolean.class},
            new Object[] {"AUTO_INCREMENT", Boolean.class},
            new Object[] {"LOCAL_TYPE_NAME", String.class},
            new Object[] {"MINIMUM_SCALE", String.class},
            new Object[] {"MAXIMUM_SCALE", String.class},
            new Object[] {"SQL_DATA_TYPE", String.class},
            new Object[] {"SQL_DATETIME_SUB", String.class},
            new Object[] {"NUM_PREC_RADIX", String.class});

    // @todo: maybe encapsulate these 4 Data Types in order to get consistent parameters

    resultSet.addRow(
        "VARCHAR", // TYPE_NAME String => Type name
        Types.VARCHAR, // DATA_TYPE int => SQL data type from java.sql.Types
        4096, // PRECISION int => maximum precision
        null, //  LITERAL_PREFIX String => prefix used to quote a literal (may be null)
        null, // LITERAL_SUFFIX String => suffix used to quote a literal (may be null)
        null, // CREATE_PARAMS String => parameters used in creating the type (may be null)
        typeNullable, // NULLABLE short => can you use NULL for this type.
        true, // CASE_SENSITIVE boolean=> is it case sensitive.
        typePredNone, // SEARCHABLE short => can you use "WHERE" based on this type:
        true, // UNSIGNED_ATTRIBUTE boolean => is it unsigned.
        false, // FIXED_PREC_SCALE boolean => can it be a money value.
        false, // AUTO_INCREMENT boolean => can it be used for an auto-increment value.
        null, // LOCAL_TYPE_NAME String => localized version of type name (may be null)
        0, // MINIMUM_SCALE short => minimum scale supported
        0, // MAXIMUM_SCALE short => maximum scale supported
        0, // SQL_DATA_TYPE int => unused
        0, // SQL_DATETIME_SUB int => unused
        10 // NUM_PREC_RADIX int => usually 2 or 10
        );

    resultSet.addRow(
        "DOUBLE", // TYPE_NAME String => Type name
        Types.DOUBLE, // DATA_TYPE int => SQL data type from java.sql.Types
        Double.BYTES, // PRECISION int => maximum precision
        null, //  LITERAL_PREFIX String => prefix used to quote a literal (may be null)
        null, // LITERAL_SUFFIX String => suffix used to quote a literal (may be null)
        null, // CREATE_PARAMS String => parameters used in creating the type (may be null)
        typeNullable, // NULLABLE short => can you use NULL for this type.
        false, // CASE_SENSITIVE boolean=> is it case sensitive.
        typePredNone, // SEARCHABLE short => can you use "WHERE" based on this type:
        true, // UNSIGNED_ATTRIBUTE boolean => is it unsigned.
        false, // FIXED_PREC_SCALE boolean => can it be a money value.
        false, // AUTO_INCREMENT boolean => can it be used for an auto-increment value.
        null, // LOCAL_TYPE_NAME String => localized version of type name (may be null)
        0, // MINIMUM_SCALE short => minimum scale supported
        15, // MAXIMUM_SCALE short => maximum scale supported
        0, // SQL_DATA_TYPE int => unused
        0, // SQL_DATETIME_SUB int => unused
        10 // NUM_PREC_RADIX int => usually 2 or 10
        );

    resultSet.addRow(
        "BOOLEAN", // TYPE_NAME String => Type name
        Types.BOOLEAN, // DATA_TYPE int => SQL data type from java.sql.Types
        // @todo: doublcheck the correct precision
        1, // PRECISION int => maximum precision
        null, //  LITERAL_PREFIX String => prefix used to quote a literal (may be null)
        null, // LITERAL_SUFFIX String => suffix used to quote a literal (may be null)
        null, // CREATE_PARAMS String => parameters used in creating the type (may be null)
        typeNullable, // NULLABLE short => can you use NULL for this type.
        false, // CASE_SENSITIVE boolean=> is it case sensitive.
        typePredNone, // SEARCHABLE short => can you use "WHERE" based on this type:
        true, // UNSIGNED_ATTRIBUTE boolean => is it unsigned.
        false, // FIXED_PREC_SCALE boolean => can it be a money value.
        false, // AUTO_INCREMENT boolean => can it be used for an auto-increment value.
        null, // LOCAL_TYPE_NAME String => localized version of type name (may be null)
        0, // MINIMUM_SCALE short => minimum scale supported
        0, // MAXIMUM_SCALE short => maximum scale supported
        0, // SQL_DATA_TYPE int => unused
        0, // SQL_DATETIME_SUB int => unused
        10 // NUM_PREC_RADIX int => usually 2 or 10
        );

    resultSet.addRow(
        "DATE", // TYPE_NAME String => Type name
        Types.DATE, // DATA_TYPE int => SQL data type from java.sql.Types
        // @todo: doublcheck the correct precision
        8, // PRECISION int => maximum precision
        null, //  LITERAL_PREFIX String => prefix used to quote a literal (may be null)
        null, // LITERAL_SUFFIX String => suffix used to quote a literal (may be null)
        null, // CREATE_PARAMS String => parameters used in creating the type (may be null)
        typeNullable, // NULLABLE short => can you use NULL for this type.
        false, // CASE_SENSITIVE boolean=> is it case sensitive.
        typePredNone, // SEARCHABLE short => can you use "WHERE" based on this type:
        true, // UNSIGNED_ATTRIBUTE boolean => is it unsigned.
        false, // FIXED_PREC_SCALE boolean => can it be a money value.
        false, // AUTO_INCREMENT boolean => can it be used for an auto-increment value.
        null, // LOCAL_TYPE_NAME String => localized version of type name (may be null)
        0, // MINIMUM_SCALE short => minimum scale supported
        0, // MAXIMUM_SCALE short => maximum scale supported
        0, // SQL_DATA_TYPE int => unused
        0, // SQL_DATETIME_SUB int => unused
        10 // NUM_PREC_RADIX int => usually 2 or 10
        );

    return resultSet;
  }

  @Override
  public ResultSet getIndexInfo(
      String catalog, String schema, String table, boolean unique, boolean approximate)
      throws SQLException {

    return new ResultSetImpl(
        new Object[] {"TABLE_CAT", String.class},
        new Object[] {"TABLE_SCHEM", String.class},
        new Object[] {"TABLE_NAME", String.class},
        new Object[] {"NON_UNIQUE", Boolean.class},
        new Object[] {"INDEX_QUALIFIER", String.class},
        new Object[] {"INDEX_NAME", String.class},
        new Object[] {"TYPE", Short.class},
        new Object[] {"ORDINAL_POSITION", Short.class},
        new Object[] {"COLUMN_NAME", String.class},
        new Object[] {"ASC_OR_DESC", String.class},
        new Object[] {"CARDINALITY", String.class},
        new Object[] {"PAGES", Integer.class},
        new Object[] {"FILTER_CONDITION", String.class});
  }

  @Override
  public boolean supportsResultSetType(int i) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  @Override
  public boolean supportsResultSetConcurrency(int i, int i1) {
    return false;
  }

  @Override
  public boolean ownUpdatesAreVisible(int i) {
    return true;
  }

  @Override
  public boolean ownDeletesAreVisible(int i) {
    return true;
  }

  @Override
  public boolean ownInsertsAreVisible(int i) {
    return true;
  }

  @Override
  public boolean othersUpdatesAreVisible(int i) {
    return true;
  }

  @Override
  public boolean othersDeletesAreVisible(int i) {
    return true;
  }

  @Override
  public boolean othersInsertsAreVisible(int i) {
    return true;
  }

  @Override
  public boolean updatesAreDetected(int i) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  @Override
  public boolean deletesAreDetected(int i) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  @Override
  public boolean insertsAreDetected(int i) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  @Override
  public boolean supportsBatchUpdates() {
    return false;
  }

  @Override
  public ResultSet getUDTs(String string, String string1, String string2, int[] ints)
      throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  @Override
  public Connection getConnection() {
    return connection;
  }

  @Override
  public boolean supportsSavepoints() {
    return false;
  }

  @Override
  public boolean supportsNamedParameters() {
    return false;
  }

  @Override
  public boolean supportsMultipleOpenResults() {
    return false;
  }

  @Override
  public boolean supportsGetGeneratedKeys() {
    return false;
  }

  @Override
  public ResultSet getSuperTypes(String string, String string1, String string2)
      throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  @Override
  public ResultSet getSuperTables(String string, String string1, String string2)
      throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  @Override
  public ResultSet getAttributes(String string, String string1, String string2, String string3)
      throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  @Override
  public boolean supportsResultSetHoldability(int i) {
    return (ResultSet.CLOSE_CURSORS_AT_COMMIT == i);
  }

  @Override
  public int getResultSetHoldability() {
    return ResultSet.CLOSE_CURSORS_AT_COMMIT;
  }

  @Override
  public int getDatabaseMajorVersion() {
    return (connection.workbook instanceof XSSFWorkbook) ? 2007 : 97;
  }

  @Override
  public int getDatabaseMinorVersion() {
    return 0;
  }

  @Override
  public int getJDBCMajorVersion() {
    return 4;
  }

  @Override
  public int getJDBCMinorVersion() {
    return 0;
  }

  @Override
  public int getSQLStateType() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  @Override
  public boolean locatorsUpdateCopy() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  @Override
  public boolean supportsStatementPooling() {
    return false;
  }

  @Override
  public RowIdLifetime getRowIdLifetime() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  @Override
  public ResultSet getSchemas(String catalog, String schemaPattern) throws SQLException {
    return getSchemas();
  }

  @Override
  public boolean supportsStoredFunctionsUsingCallSyntax() {
    return false;
  }

  @Override
  public boolean autoCommitFailureClosesAllResultSets() {
    return true;
  }

  @Override
  public ResultSet getClientInfoProperties() throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  @Override
  public ResultSet getFunctions(String string, String string1, String string2) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  @Override
  public ResultSet getFunctionColumns(String string, String string1, String string2, String string3)
      throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  @Override
  public ResultSet getPseudoColumns(String string, String string1, String string2, String string3)
      throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  @Override
  public boolean generatedKeyAlwaysReturned() {
    return false;
  }

  @Override
  public <T> T unwrap(Class<T> type) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }

  @Override
  public boolean isWrapperFor(Class<?> type) throws SQLException {
    throw new SQLException(new UnsupportedOperationException("Not supported yet"));
  }
}
