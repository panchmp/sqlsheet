/*
 * Copyright 2012 sqlsheet.googlecode.com
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
package com.sqlsheet.stream;

import com.sqlsheet.XlsDriver;
import com.sqlsheet.parser.ParsedStatement;
import com.sqlsheet.parser.SelectStarStatement;
import com.sqlsheet.parser.SqlSheetParser;
import org.apache.poi.ss.usermodel.Sheet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import static com.sqlsheet.XlsStatement.DEFAULT_FIRST_COL;
import static com.sqlsheet.XlsStatement.DEFAULT_HEADLINE;
import static com.sqlsheet.XlsStatement.getSheetNamed;

/**
 * SqlSheet implementation of java.sql.Statement which uses steaming over XLS
 *
 * @author <a href='http://code.google.com/p/sqlsheet'>sqlsheet</a>
 */
public class XlsStreamStatement implements Statement {

    private final XlsStreamConnection connection;
    private final Map<String, XlsStreamResultSet> sheet2rs = new HashMap<>();
    private SqlSheetParser parser;
    private boolean closeOneCompletion = false;

    public XlsStreamStatement(XlsStreamConnection c) {
        if (c == null) {
            throw new IllegalArgumentException();
        }
        this.connection = c;
    }

    public Connection getConnection() throws SQLException {
        return connection;
    }

    public void close() throws SQLException {
        // nothing
    }

    public boolean execute(String sql) throws SQLException {
        ResultSet rs = executeQuery(sql);
        if (closeOneCompletion) {
            rs.close();
        }
        return false;
    }

    public int executeUpdate(String sql) throws SQLException {
        ResultSet rs = executeQuery(sql);
        if (closeOneCompletion) {
            rs.close();
        }
        return -1;
    }

    public ResultSet executeQuery(String query) throws SQLException {
        ParsedStatement parsed = parse(query);
        if (parsed instanceof SelectStarStatement) {
            return doSelect((SelectStarStatement) parsed);
        } else {
            throw new IllegalStateException(parsed.getClass().getName());
        }
    }

    protected ParsedStatement parse(String sql) throws SQLException {
        if (sql == null) {
            throw new IllegalArgumentException();
        }
        if (parser == null) {
            parser = new SqlSheetParser();
        }
        return parser.parse(sql);
    }

    protected ResultSet doSelect(SelectStarStatement sss) throws SQLException {
        XlsStreamResultSet out = findOrCreateResultSetFor(sss.getTable());
        out.statement = this;
        return out;
    }

    private XlsStreamResultSet findOrCreateResultSetFor(String tableName) throws SQLException {
        String sanitizedTableName = tableName.trim().toUpperCase();
        XlsStreamResultSet out = sheet2rs.get(sanitizedTableName);
        if (out == null) {
            Sheet sheet = getSheetNamed(connection.getWorkBook(), sanitizedTableName);
            out =
                    new XlsStreamResultSet(
                            sheet,
                            connection.getInt(XlsDriver.HEADLINE, DEFAULT_HEADLINE),
                            connection.getInt(XlsDriver.FIRST_COL, DEFAULT_FIRST_COL));
            out.statement = this;
            sheet2rs.put(sanitizedTableName, out);
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

    public void setFetchDirection(int p0) throws SQLException {
        nyi();
    }

    public int getFetchSize() throws SQLException {
        nyi();
        return -1;
    }

    public void setFetchSize(int p0) throws SQLException {
        nyi();
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

    public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
        nyi();
        return -1;
    }

    public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
        nyi();
        return -1;
    }

    public int executeUpdate(String sql, String[] columnNames) throws SQLException {
        nyi();
        return -1;
    }

    public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
        nyi();
        return false;
    }

    public boolean execute(String sql, int[] columnIndexes) throws SQLException {

        nyi();
        return false;
    }

    public boolean execute(String sql, String[] columnNames) throws SQLException {
        nyi();
        return false;
    }

    public int getResultSetHoldability() throws SQLException {
        nyi();
        return -1;
    }

    protected void nyi() throws SQLException {
        throw new SQLFeatureNotSupportedException("Not implemented yet.");
    }

    public boolean isClosed() throws SQLException {
        return false;
    }

    public boolean isPoolable() throws SQLException {
        return false;
    }

    public void setPoolable(boolean poolable) throws SQLException {
        // nothing
    }

    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    public void closeOnCompletion() throws SQLException {
        closeOneCompletion = true;
    }

    public boolean isCloseOnCompletion() throws SQLException {
        return closeOneCompletion;
    }
}
