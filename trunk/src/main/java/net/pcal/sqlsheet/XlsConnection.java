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
package net.pcal.sqlsheet;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * SqlSheet implementation of java.sql.Connection.
 *
 * @author <a href='http://www.pcal.net'>pcal</a>
 * @author <a href='http://code.google.com/p/sqlsheet'>sqlsheet</a>
 */
class XlsConnection implements Connection {

    private static final Logger logger = Logger.getLogger(XlsConnection.class.getName());

    private Workbook workbook = null;
    private File saveFile = null;
    private Boolean writeRequired =  false;

    XlsConnection(Workbook workbook) {
        if (workbook == null)
            throw new IllegalArgumentException();
        this.workbook = workbook;
        this.saveFile = null;
    }

    XlsConnection(Workbook workbook, File saveFile) {
        if (workbook == null)
            throw new IllegalArgumentException();
        if (saveFile == null)
            throw new IllegalArgumentException();
        this.workbook = workbook;
        this.saveFile = saveFile;
    }

    Workbook getWorkBook() {
        return workbook;
    }

    public Statement createStatement() throws SQLException {
        return new XlsStatement(this);
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return new XlsPreparedStatement(this, sql);
    }

    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
            throws SQLException {
        return prepareStatement(sql);
    }

    public void close() throws SQLException {
        if (saveFile != null && writeRequired) {
            FileOutputStream fileOut = null;
            try {
                File newFile = File.createTempFile("xlsdriver","xlsx");
                fileOut = new FileOutputStream(newFile);
                workbook.write(fileOut);
                File backup = backupFile(saveFile);
                saveFile.delete();
                moveFile(newFile,saveFile);
            } catch (IOException ioe) {
                SQLException sqe = new SQLException(ioe.getMessage());
                sqe.initCause(sqe);
                throw sqe;
            } finally {
                if (fileOut != null)
                    try {
                        fileOut.close();
                    } catch (IOException e) {
                        logger.log(Level.WARNING,e.getMessage(),e);
                    }
            }
        }
    }

    private File backupFile(File input) throws IOException{
        File output = null;
        if (input != null) {
            output = File.createTempFile(input.getName()+".",".bkp",null);
            moveFile(input, output);
        }
        return output;
    }

    private void moveFile(File sourceFile, File destFile) throws IOException {
        boolean moved;
        moved = sourceFile.renameTo(destFile);
        if (!moved) {
            FileChannel source = null;
            FileChannel destination = null;
            try {
                source = new FileInputStream(sourceFile).getChannel();
                destination = new FileOutputStream(destFile).getChannel();

                // previous code: destination.transferFrom(source, 0, source.size());
                // to avoid infinite loops, should be:
                long count = 0;
                long size = source.size();
                while((count += destination.transferFrom(source, 0, size-count))<size);
            }
            finally {
                if(source != null) {
                    source.close();
                }
                if(destination != null) {
                    destination.close();
                }
            }
        }
    }

    public boolean getAutoCommit() {
        return false;
    }

    public boolean isClosed() {
        return false;
    }

    public boolean isReadOnly() {
        return true;
    }

    public String getCatalog() {
        return null;
    }

    public int getTransactionIsolation() {
        return Connection.TRANSACTION_NONE;
    }

    public SQLWarning getWarnings() {
        return null;
    }

    @SuppressWarnings("unchecked")
    public Map getTypeMap() throws SQLException {
        return null;
    }

    public void commit() throws SQLException {
    }

    public void rollback() throws SQLException {
    }

    public void clearWarnings() throws SQLException {
    }

    public DatabaseMetaData getMetaData() throws SQLException {
        return null;
    }

    public void setAutoCommit(boolean autoCommit) throws SQLException {
        nyi();
    }

    public void setReadOnly(boolean readOnly) throws SQLException {
        nyi();
    }

    public void setCatalog(String catalog) throws SQLException {
        nyi();
    }

    public void setTransactionIsolation(int level) throws SQLException {
        nyi();
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

    public void setClientInfo(Properties properties) throws SQLClientInfoException {
    }

    public String getClientInfo(String name) throws SQLException {
        nyi();
        return null;
    }

    public Properties getClientInfo() throws SQLException {
        nyi();
        return null;
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

    public void setHoldability(int param) throws SQLException {
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

    public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
            throws SQLException {
        nyi();
        return null;
    }

    private void nyi() throws SQLException {
        throw new SQLException("NYI");
    }

    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
        nyi();
    }

    public <T> T unwrap(Class<T> iface) throws SQLException {
        nyi();
        return null;
    }

    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        nyi();
        return false;
    }

    public Boolean getWriteRequired() {
        return writeRequired;
    }

    public void setWriteRequired(Boolean writeRequired) {
        this.writeRequired = writeRequired;
    }
}
