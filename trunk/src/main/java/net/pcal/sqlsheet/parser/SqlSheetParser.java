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
package net.pcal.sqlsheet.parser;

import net.sf.jsqlparser.expression.*;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.ItemsList;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.*;

import java.io.StringReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper around the JSQLParser which does all of the real work. This class
 * and the rest of the interface in the package are here just to insulate the
 * parent package from the particulars of a particular parser.
 *
 * @author <a href='http://www.pcal.net'>pcal</a>
 */
public class SqlSheetParser {

    // =========================================================================
    // Fields

    private CCJSqlParserManager parser;

    // =========================================================================
    // Public methods

    public ParsedStatement parse(String sql) throws SQLException {
        if (parser == null) parser = new CCJSqlParserManager();
        net.sf.jsqlparser.statement.Statement statement;
        try {
            statement = parser.parse(new StringReader(sql));
        } catch (Exception e) {
            SQLException sq = new SQLException(e.getMessage());
            sq.initCause(e);
            throw sq;
        }
        //
        // SELECT
        //
        if (statement instanceof Select) {
            SelectBody body = ((Select) statement).getSelectBody();
            if (!(body instanceof PlainSelect)) {
                throw new SQLException(
                        "ORDER BY and UNION not supported on Excel sheets.");
            }
            final FromItem from = ((PlainSelect) body).getFromItem();

            if (!(from instanceof Table)) {
                throw new SQLException("Subselects not supported on Excel sheets.");
            }
            if (((PlainSelect) body).getDistinct() != null) {
                throw new SQLException("DISTINCT not supported on Excel sheets.");
            }
            if (((PlainSelect) body).getInto() != null) {
                throw new SQLException("SELECT INTO not supported on Excel sheets.");
            }
            if (((PlainSelect) body).getHaving() != null) {
                throw new SQLException("HAVING not supported on Excel sheets.");
            }
            if (((PlainSelect) body).getGroupByColumnReferences() != null) {
                throw new SQLException("GROUP BY not supported on Excel sheets.");
            }
            List<SelectItem> selectItems = ((PlainSelect) body).getSelectItems();
            if (selectItems == null || selectItems.isEmpty()
                    || selectItems.size() > 1
                    || !(selectItems.get(0) instanceof AllColumns)) {
                throw new SQLException("Only 'SELECT *' is supported on Excel sheets");
            }
            return new SelectStarStatement() {
                public String getTable() {
                    return (((Table) from).getName());
                }
            };

        } else
            //
            // CREATE TABLE
            //
            if (statement instanceof CreateTable) {
                final String table = ((CreateTable) statement).getTable().getName();
                List<ColumnDefinition> cols =
                        ((CreateTable) statement).getColumnDefinitions();
                final List<String> names = new ArrayList<String>();
                final List<String> types = new ArrayList<String>();
                for (ColumnDefinition cd : cols) {
                    names.add(stripUnderscores(cd.getColumnName()));
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
                    final String table = ((Insert) statement).getTable().getName();
                    List<net.sf.jsqlparser.schema.Column> cols =
                            ((Insert) statement).getColumns();
                    final List<String> names = new ArrayList<String>();
                    final List<Object> values = new ArrayList<Object>();
                    for (net.sf.jsqlparser.schema.Column cd : cols) {
                        names.add(stripUnderscores(cd.getColumnName()));
                    }
                    ItemsList ilist = ((Insert) statement).getItemsList();
                    if (!(ilist instanceof ExpressionList)) {
                        throw new SQLException(
                                "INSERT from subselect not supported with excel sheets");
                    }
                    List<Expression> exps = ((ExpressionList) ilist).getExpressions();
                    for (Expression exp : exps) {
                        // java.lang.String
                        System.out.println("get class for the expression type: " + exp.toString());
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
                            throw new SQLException("Cannot handle expression of class "
                                    + exp.getClass().getName() + ", value = " + exp.toString());
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
                }
                //
                // doh!
                //
                else {
                    throw new SQLException("Could not process " + sql + " ("
                            + statement.getClass().getName() + ")");
                }

    }

    // =========================================================================
    // Private methods

    // support for workaround until we get a fix for
    // http://sourceforge.net/forum/forum.php?thread_id=1975052&forum_id=360150

    private static String stripUnderscores(String columnName) {
        return columnName.replace('_', ' ');
    }

}
