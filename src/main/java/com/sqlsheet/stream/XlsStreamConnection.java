/*
 * Copyright 2012 sqlsheet.googlecode.com
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
package com.sqlsheet.stream;

import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.logging.Logger;

import com.sqlsheet.XlsDatabaseMetaData;

/**
 * SqlSheet implementation of java.sql.Connection which uses steaming over XLS
 *
 * @author <a href='http://code.google.com/p/sqlsheet'>sqlsheet</a>
 */
public class XlsStreamConnection implements Connection {

    private static final Logger logger = Logger.getLogger(XlsStreamConnection.class.getName());

    URL                         xlsFile;

    public XlsStreamConnection(URL xlsFile) throws SQLException {
        this.xlsFile = xlsFile;
    }

    public Statement createStatement() throws SQLException {
        return new XlsStreamStatement(this);
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return new XlsStreamPreparedStatement(this, sql);
    }

    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return prepareStatement(sql);
    }

    public void close() throws SQLException {
    }

    public boolean getAutoCommit() {
        return false;
    }

    public void setAutoCommit(boolean autoCommit) throws SQLException {
        nyi();
    }

    public boolean isClosed() {
        return false;
    }

    public boolean isReadOnly() {
        return true;
    }

    public void setReadOnly(boolean readOnly) throws SQLException {
        nyi();
    }

    public String getCatalog() {
        return null;
    }

    public void setCatalog(String catalog) throws SQLException {
        nyi();
    }

    public int getTransactionIsolation() {
        return Connection.TRANSACTION_NONE;
    }

    public void setTransactionIsolation(int level) throws SQLException {
        nyi();
    }

    public SQLWarning getWarnings() {
        return null;
    }

    @SuppressWarnings("unchecked")
    public Map getTypeMap() throws SQLException {
        return null;
    }

    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
        nyi();
    }

    public void commit() throws SQLException {
    }

    public void rollback() throws SQLException {
    }

    public void clearWarnings() throws SQLException {
    }

    public DatabaseMetaData getMetaData() throws SQLException {
        return new XlsStreamDatabaseMetaData(xlsFile);
    }

    public CallableStatement prepareCall(String sql) throws SQLException {
        nyi();
        return null;
    }

    public String nativeSQL(String sql) throws SQLException {
        nyi();
        return null;
    }

    public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
        nyi();
        return null;
    }

    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        nyi();
        return null;
    }

    public int getHoldability() throws SQLException {
        nyi();
        return -1;
    }

    public void setHoldability(int param) throws SQLException {
        nyi();
    }

    public CallableStatement prepareCall(String str, int param, int param2, int param3) throws SQLException {
        nyi();
        return null;
    }

    public PreparedStatement prepareStatement(String str, int param) throws SQLException {
        nyi();
        return null;
    }

    public PreparedStatement prepareStatement(String str, int[] values) throws SQLException {
        nyi();
        return null;
    }

    public PreparedStatement prepareStatement(String str, String[] str1) throws SQLException {
        nyi();
        return null;
    }

    public Clob createClob() throws SQLException {
        nyi();
        return null;
    }

    public Blob createBlob() throws SQLException {
        nyi();
        return null;
    }

    public NClob createNClob() throws SQLException {
        nyi();
        return null;
    }

    public SQLXML createSQLXML() throws SQLException {
        nyi();
        return null;
    }

    public boolean isValid(int timeout) throws SQLException {
        nyi();
        return false;
    }

    public void setClientInfo(String name, String value) throws SQLClientInfoException {
    }

    public String getClientInfo(String name) throws SQLException {
        nyi();
        return null;
    }

    public Properties getClientInfo() throws SQLException {
        nyi();
        return null;
    }

    public void setClientInfo(Properties properties) throws SQLClientInfoException {
    }

    public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
        nyi();
        return null;
    }

    public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
        nyi();
        return null;
    }

    public PreparedStatement prepareStatement(String str, int param, int param2, int param3) throws SQLException {
        nyi();
        return null;

    }

    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
        nyi();
    }

    public void rollback(Savepoint savepoint) throws SQLException {
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

    public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        nyi();
        return null;
    }

    private void nyi() throws SQLException {
        throw new SQLException("NYI");
    }

    public <T> T unwrap(Class<T> iface) throws SQLException {
        nyi();
        return null;
    }

    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        nyi();
        return false;
    }

    public String getSchema() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setSchema(String string) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void abort(Executor exctr) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setNetworkTimeout(Executor exctr, int i) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getNetworkTimeout() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
