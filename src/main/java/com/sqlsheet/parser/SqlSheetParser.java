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
package com.sqlsheet.parser;

import net.sf.jsqlparser.expression.*;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.ItemsList;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import net.sf.jsqlparser.statement.drop.Drop;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.*;

import java.io.StringReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper around the JSQLParser which does all of the real work. This class and the rest of the
 * interface in the package are here just to insulate the parent package from the particulars of a
 * particular parser.
 *
 * @author <a href='http://www.pcal.net'>pcal</a>
 * @author <a href='http://code.google.com/p/sqlsheet'>sqlsheet</a>
 */
public class SqlSheetParser {

  /**
   * Excel silently truncates long sheet names to 31 chars. We may truncate table names explicitly
   * for every usage.
   */
  private static final int MAX_SENSITIVE_SHEET_NAME_LEN = 31;

  private CCJSqlParserManager parser;

  // support for workaround until we get a fix for
  // http://sourceforge.net/forum/forum.php?thread_id=1975052&forum_id=360150
  private static String stripUnderscores(String columnName) {
    return columnName.replace('_', ' ');
  }

  public ParsedStatement parse(String sql) throws SQLException {
    if (parser == null) parser = new CCJSqlParserManager();
    net.sf.jsqlparser.statement.Statement statement;
    try {
      statement = parser.parse(new StringReader(sql));
    } catch (Exception e) {
      throw new SQLException(e);
    }
    //
    // SELECT
    //
    if (statement instanceof Select) {
      SelectBody body = ((Select) statement).getSelectBody();
      if (!(body instanceof PlainSelect)) {
        throw new SQLException("ORDER BY and UNION not supported on Excel sheets.");
      }
      final FromItem from = ((PlainSelect) body).getFromItem();

      if (!(from instanceof Table)) {
        throw new SQLException("Subselects not supported on Excel sheets.");
      }
      if (((PlainSelect) body).getDistinct() != null) {
        throw new SQLException("DISTINCT not supported on Excel sheets.");
      }
      if (((PlainSelect) body).getIntoTables() != null) {
        throw new SQLException("SELECT INTO not supported on Excel sheets.");
      }
      if (((PlainSelect) body).getHaving() != null) {
        throw new SQLException("HAVING not supported on Excel sheets.");
      }
      if (((PlainSelect) body).getGroupBy() != null) {
        throw new SQLException("GROUP BY not supported on Excel sheets.");
      }
      List<SelectItem> selectItems = ((PlainSelect) body).getSelectItems();
      if (selectItems == null
          || selectItems.isEmpty()
          || selectItems.size() > 1
          || !(selectItems.get(0) instanceof AllColumns)) {
        throw new SQLException("Only 'SELECT *' is supported on Excel sheets");
      }

      final String tableName = prepareTableIdentifier(((Table) from).getName());
      return new SqlSheetSelectStarStatement(tableName);

    } else
    //
    // CREATE TABLE
    //
    if (statement instanceof CreateTable) {
      final String table = prepareTableIdentifier(((CreateTable) statement).getTable().getName());
      List<ColumnDefinition> cols = ((CreateTable) statement).getColumnDefinitions();
      final List<String> names = new ArrayList<>();
      final List<String> types = new ArrayList<>();
      for (ColumnDefinition cd : cols) {
        names.add(prepareColumnIdentifier(stripUnderscores(cd.getColumnName())));
        types.add(cd.getColDataType().getDataType());
      }
      return new SqlSheetCreateTableStatement(table, names, types);
    } else
    //
    // INSERT INTO
    //
    if (statement instanceof Insert) {
      final String table = prepareTableIdentifier(((Insert) statement).getTable().getName());
      List<net.sf.jsqlparser.schema.Column> cols = ((Insert) statement).getColumns();
      final List<String> names = new ArrayList<>();
      final List<Object> values = new ArrayList<>();
      for (net.sf.jsqlparser.schema.Column cd : cols) {
        names.add(prepareColumnIdentifier(stripUnderscores(cd.getColumnName())));
      }
      ItemsList ilist = ((Insert) statement).getItemsList();
      if (!(ilist instanceof ExpressionList)) {
        throw new SQLException("INSERT from subselect not supported with excel sheets");
      }
      List<Expression> exps = ((ExpressionList) ilist).getExpressions();
      for (Expression exp : exps) {
        // java.lang.String
        if (exp instanceof StringValue) {
          values.add(((StringValue) exp).getValue());
          continue;
        }
        // java.sql.Date
        else if (exp instanceof DateValue) {
          values.add(((DateValue) exp).getValue());
          continue;
        }
        // java.sql.Timestamp
        else if (exp instanceof TimestampValue) {
          values.add(((TimestampValue) exp).getValue());
          continue;
        }
        // java.sql.Time
        else if (exp instanceof TimeValue) {
          values.add(((TimeValue) exp).getValue());
          continue;
        }
        // java.lang.Double
        else if (exp instanceof DoubleValue) {
          values.add(((DoubleValue) exp).getValue()); // java.sql.Date
          continue;
        }
        // java.lang.Long
        else if (exp instanceof LongValue) {
          values.add(((LongValue) exp).getValue()); // java.sql.Date
          continue;
        }
        // null
        else if (exp instanceof NullValue) {
          values.add(null);
          continue;
        } else if (exp instanceof net.sf.jsqlparser.expression.JdbcParameter) {
          values.add(JdbcParameter.INSTANCE);
          continue;
        }
        // doh
        else {
          throw new SQLException(
              "Cannot handle expression of class "
                  + exp.getClass().getName()
                  + ", value = "
                  + exp.toString());
        }
      }
      return new SqlSheetInsertIntoStatement(table, names, values);
    } else if (statement instanceof Drop) {

      final String table = prepareTableIdentifier(((Drop) statement).getName().getName());
      return new SqlSheetDropTableStatement(table);

    }
    //
    // doh!
    //
    else {
      throw new SQLException(
          "Could not process " + sql + " (" + statement.getClass().getName() + ")");
    }
  }

  private String prepareTableIdentifier(String tableName) {
    String newName = truncateQuotes(tableName);
    return newName.substring(0, Math.min(MAX_SENSITIVE_SHEET_NAME_LEN, newName.length())).trim();
  }

  private String prepareColumnIdentifier(String columnName) {
    return truncateQuotes(columnName);
  }

  private String truncateQuotes(String name) {
    return name.replaceAll("\"", "");
  }


  private static class SqlSheetSelectStarStatement implements SelectStarStatement {
    private final String table;

    public SqlSheetSelectStarStatement(String table) {
      this.table = table;
    }

    public String getTable() {
      return table;
    }
  }

  private static class SqlSheetDropTableStatement implements DropTableStatement {
    private final String table;

    public SqlSheetDropTableStatement(String table) {
      this.table = table;
    }

    public String getTable() {
      return table;
    }
  }

  private static class SqlSheetInsertIntoStatement implements InsertIntoStatement {
    private final String table;
    private final List<String> names;
    private final List<Object> values;

    public SqlSheetInsertIntoStatement(String table, List<String> names, List<Object> values) {
      this.table = table;
      this.names = names;
      this.values = values;
    }

    public String getTable() {
      return table;
    }

    public List<String> getColumns() {
      return names;
    }

    public List<Object> getValues() {
      return values;
    }
  }

  private static class SqlSheetCreateTableStatement implements CreateTableStatement {
    private final String table;
    private final List<String> names;
    private final List<String> types;

    public SqlSheetCreateTableStatement(String table, List<String> names, List<String> types) {
      this.table = table;
      this.names = names;
      this.types = types;
    }

    public String getTable() {
      return table;
    }

    public List<String> getColumns() {
      return names;
    }

    public List<String> getTypes() {
      return types;
    }
  }
}
