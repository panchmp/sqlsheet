/*
 * Copyright 2012 pcal.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.sqlsheet.parser;

import net.sf.jsqlparser.expression.DateValue;
import net.sf.jsqlparser.expression.DoubleValue;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.NullValue;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.TimeValue;
import net.sf.jsqlparser.expression.TimestampValue;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import net.sf.jsqlparser.statement.drop.Drop;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.AllColumns;
import net.sf.jsqlparser.statement.select.FromItem;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.SelectItem;
import net.sf.jsqlparser.statement.select.Values;

import java.io.StringReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper around the JSQLParser which does all the real work. This class and the rest of the
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
        if (parser == null) {
            parser = new CCJSqlParserManager();
        }
        net.sf.jsqlparser.statement.Statement statement;
        try {
            statement = parser.parse(new StringReader(sql));
        } catch (Exception e) {
            SQLException sq = new SQLException(e.getMessage(), e);
            throw sq;
        }
        //
        // SELECT
        //
        if (statement instanceof PlainSelect) {
            PlainSelect select = (PlainSelect) statement;
            final FromItem from = select.getFromItem();

            if (!(from instanceof Table)) {
                throw new SQLException("Subselects not supported on Excel sheets.");
            }
            if (select.getDistinct() != null) {
                throw new SQLException("DISTINCT not supported on Excel sheets.");
            }
            if (select.getIntoTables() != null) {
                throw new SQLException("SELECT INTO not supported on Excel sheets.");
            }
            if (select.getHaving() != null) {
                throw new SQLException("HAVING not supported on Excel sheets.");
            }
            if (select.getGroupBy() != null) {
                throw new SQLException("GROUP BY not supported on Excel sheets.");
            }
            List<SelectItem<?>> selectItems = select.getSelectItems();
            if (selectItems == null
                    || selectItems.size() != 1
                    || !(selectItems.get(0).getExpression() instanceof AllColumns)) {
                throw new SQLException("Only 'SELECT *' is supported on Excel sheets");
            }
            return new SelectStarStatement() {
                public String getTable() {
                    Table table = (Table) from;
                    String tableName = table.getName().replace("\"", "");
                    return prepareTableIdentifier(tableName);
                }
            };

        } else
        //
        // CREATE TABLE
        //
        if (statement instanceof CreateTable) {
            final String table =
                    prepareTableIdentifier(((CreateTable) statement).getTable().getName());
            List<ColumnDefinition> cols = ((CreateTable) statement).getColumnDefinitions();
            final List<String> names = new ArrayList<String>();
            final List<String> types = new ArrayList<String>();
            for (ColumnDefinition cd : cols) {
                names.add(prepareColumnIdentifier(stripUnderscores(cd.getColumnName())));
                types.add(cd.getColDataType().getDataType());
            }
            return new CreateTableStatement() {
                public String getTable() {
                    return table;
                }

                public List<String> getColumns() {
                    return names;
                }

                public List<String> getTypes() {
                    return types;
                }
            };
        } else
        //
        // INSERT INTO
        //
        if (statement instanceof Insert) {
            Insert insert = (Insert) statement;
            final String table = prepareTableIdentifier(insert.getTable().getName());
            List<net.sf.jsqlparser.schema.Column> cols = insert.getColumns();
            final List<String> names = new ArrayList<>();
            final List<Object> values = new ArrayList<Object>();
            for (net.sf.jsqlparser.schema.Column cd : cols) {
                names.add(prepareColumnIdentifier(stripUnderscores(cd.getColumnName())));
            }
            if (!(insert.getSelect() instanceof Values)) {
                throw new SQLException(
                        "INSERT must have a VALUES clause and sub-selects are not supported with excel sheets");
            }
            Values valuesClause = insert.getValues();
            for (Expression exp : valuesClause.getExpressions()) {
                // java.lang.String
                if (exp instanceof StringValue) {
                    values.add(((StringValue) exp).getValue());
                    continue;
                } /* java.sql.Date */ else if (exp instanceof DateValue) {
                    values.add(((DateValue) exp).getValue());
                    continue;
                } /* java.sql.Timestamp */ else if (exp instanceof TimestampValue) {
                    values.add(((TimestampValue) exp).getValue());
                    continue;
                } /* java.sql.Time */ else if (exp instanceof TimeValue) {
                    values.add(((TimeValue) exp).getValue());
                    continue;
                } /* java.lang.Double */ else if (exp instanceof DoubleValue) {
                    values.add(((DoubleValue) exp).getValue()); // java.sql.Date
                    continue;
                } /* java.lang.Long */ else if (exp instanceof LongValue) {
                    values.add(((LongValue) exp).getValue()); // java.sql.Date
                    continue;
                } /* null */ else if (exp instanceof NullValue) {
                    values.add(null);
                    continue;
                } else if (exp instanceof net.sf.jsqlparser.expression.JdbcParameter) {
                    values.add(JdbcParameter.INSTANCE);
                    continue;
                } else {
                    throw new SQLException(
                            "Cannot handle expression of class "
                                    + exp.getClass().getName()
                                    + ", value = "
                                    + exp);
                }
            }
            return new InsertIntoStatement() {
                public String getTable() {
                    return table;
                }

                public List<String> getColumns() {
                    return names;
                }

                public List<Object> getValues() {
                    return values;
                }
            };
        } else if (statement instanceof Drop) {

            final String table = prepareTableIdentifier(((Drop) statement).getName().getName());
            return new DropTableStatement() {
                public String getTable() {
                    return table;
                }
            };

        } else {
            throw new SQLException(
                    "Could not process " + sql + " (" + statement.getClass().getName() + ")");
        }
    }

    private String prepareTableIdentifier(String tableName) {
        String newName = truncateQuotes(tableName);
        return newName.substring(0, Math.min(MAX_SENSITIVE_SHEET_NAME_LEN, newName.length()))
                .trim();
    }

    private String prepareColumnIdentifier(String columnName) {
        return truncateQuotes(columnName);
    }

    private String truncateQuotes(String name) {
        return name.replaceAll("\"", "");
    }
}
