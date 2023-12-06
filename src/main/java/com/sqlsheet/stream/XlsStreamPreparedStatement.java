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

import com.sqlsheet.parser.ParsedStatement;
import com.sqlsheet.parser.SelectStarStatement;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

/**
 * SqlSheet implementation of java.sql.PreparedStatement which uses steaming over XLS
 *
 * @author <a href='<a href="http://code.google.com/p/sqlsheet">...</a>'>sqlsheet</a>
 */
public class XlsStreamPreparedStatement extends XlsStreamStatement implements PreparedStatement {

    private final ParsedStatement statement;
    private final List<Object> parameters = new ArrayList<>();
    private boolean closeOnCompletion;

    private final XlsStreamingResultSetMetaData metadata;
    private final int firstSheetRowOffset;
    private final int firstSheetColOffset;
    Iterator<Row> iterator;

    public XlsStreamPreparedStatement(XlsStreamConnection conn, String sql, int firstSheetRowOffset,
            int firstSheetColOffset) throws SQLException {
        super(conn);
        this.statement = super.parse(sql);
        this.firstSheetRowOffset = firstSheetRowOffset;
        this.firstSheetColOffset = firstSheetColOffset;

        if (this.statement instanceof SelectStarStatement) {
            String tableName = ((SelectStarStatement) this.statement).getTable();
            Sheet sheet = conn.workbook.getSheet(tableName);
            this.iterator = sheet.rowIterator();
            metadata = new XlsStreamingResultSetMetaData(sheet, null, firstSheetRowOffset,
                    firstSheetColOffset);

        } else {
            iterator = null;
            metadata = null;
        }
    }

    public void addBatch() throws SQLException {
        nyi();
    }

    public void clearParameters() throws SQLException {
        parameters.clear();
    }

    public boolean execute() throws SQLException {
        ResultSet rs = executeQuery();
        if (closeOnCompletion) {
            rs.close();
        }
        return true;
    }

    public int executeUpdate() throws SQLException {
        ResultSet rs = executeQuery();
        if (closeOnCompletion) {
            rs.close();
        }
        return -1;
    }

    public ResultSet executeQuery() throws SQLException {
        if (statement == null) {
            throw new IllegalStateException("null statement");
        }
        if (statement instanceof SelectStarStatement) {
            return super.doSelect((SelectStarStatement) statement);
        }
        throw new IllegalStateException(
                "Execute Query Exception: " + statement.getClass().getName());
    }

    private void setParameter(int p, Object val) {
        int p1 = p - 1;
        while (parameters.size() <= p1) {
            parameters.add(null);
        }
        parameters.set(p1, val);
    }

    public void setBigDecimal(int arg0, BigDecimal arg1) throws SQLException {
        setParameter(arg0, arg1);
    }

    public void setBoolean(int arg0, boolean arg1) throws SQLException {
        setParameter(arg0, arg1);
    }

    public void setByte(int arg0, byte arg1) throws SQLException {
        setParameter(arg0, arg1);
    }

    public void setDate(int arg0, Date arg1) throws SQLException {
        setParameter(arg0, arg1);
    }

    public void setDouble(int arg0, double arg1) throws SQLException {
        setParameter(arg0, arg1);
    }

    public void setFloat(int arg0, float arg1) throws SQLException {
        setParameter(arg0, arg1);
    }

    public void setInt(int arg0, int arg1) throws SQLException {
        setParameter(arg0, arg1);
    }

    public void setLong(int arg0, long arg1) throws SQLException {
        setParameter(arg0, arg1);
    }

    public void setNull(int arg0, int arg1) throws SQLException {
        setParameter(arg0, arg1);
    }

    public void setNull(int arg0, int arg1, String arg2) throws SQLException {
        setParameter(arg0, arg1);
    }

    public void setShort(int arg0, short arg1) throws SQLException {
        setParameter(arg0, arg1);
    }

    public void setString(int arg0, String arg1) throws SQLException {
        setParameter(arg0, arg1);
    }

    public void setTime(int arg0, Time arg1) throws SQLException {
        setParameter(arg0, arg1);
    }

    public void setTimestamp(int arg0, Timestamp arg1) throws SQLException {
        setParameter(arg0, arg1);
    }

    public void setCharacterStream(int parameterIndex, Reader reader, int length)
            throws SQLException {
        char[] buff = new char[length];
        try {
            int read = reader.read(buff, 0, length);
            if (read == length) {
                setString(parameterIndex, new String(buff));
            } else {
                throw new IOException("Not all bytes have been read.");
            }
        } catch (IOException e) {
            SQLException sqe = new SQLException(e.getMessage(), e);
            throw sqe;
        }
    }

    public void setObject(int arg0, Object arg1) throws SQLException {
        // REVIEW really just passing the buck here if the send us something
        // weird
        setParameter(arg0, arg1);
    }

    public void setTimestamp(int arg0, Timestamp arg1, Calendar arg2) throws SQLException {
        nyi();
    }

    public void setTime(int arg0, Time arg1, Calendar arg2) throws SQLException {
        nyi();
    }

    public void setArray(int arg0, Array arg1) throws SQLException {
        nyi();
    }

    public void setAsciiStream(int arg0, InputStream arg1, int arg2) throws SQLException {
        nyi();
    }

    public void setDate(int arg0, Date arg1, Calendar arg2) throws SQLException {
        nyi();
    }

    public void setBytes(int arg0, byte[] arg1) throws SQLException {
        nyi();
    }

    public void setClob(int arg0, Clob arg1) throws SQLException {
        nyi();
    }

    public void setObject(int arg0, Object arg1, int arg2) throws SQLException {
        nyi();
    }

    public void setObject(int arg0, Object arg1, int arg2, int arg3) throws SQLException {
        nyi();
    }

    public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {
        nyi();
    }

    public void setBinaryStream(int parameterIndex, InputStream x, long length)
            throws SQLException {
        nyi();
    }

    public void setCharacterStream(int parameterIndex, Reader reader, long length)
            throws SQLException {
        nyi();
    }

    public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
        nyi();
    }

    public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
        nyi();
    }

    public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
        nyi();
    }

    public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
        nyi();
    }

    public void setClob(int parameterIndex, Reader reader) throws SQLException {
        nyi();
    }

    public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
        nyi();
    }

    public void setNClob(int parameterIndex, Reader reader) throws SQLException {
        nyi();
    }

    public void setRef(int arg0, Ref arg1) throws SQLException {
        nyi();
    }

    public ResultSetMetaData getMetaData() throws SQLException {
        return metadata;
    }

    public ParameterMetaData getParameterMetaData() throws SQLException {
        nyi();
        return null;
    }

    public void setRowId(int parameterIndex, RowId x) throws SQLException {
        nyi();
    }

    public void setNString(int parameterIndex, String value) throws SQLException {
        nyi();
    }

    public void setNCharacterStream(int parameterIndex, Reader value, long length)
            throws SQLException {
        nyi();
    }

    public void setNClob(int parameterIndex, NClob value) throws SQLException {
        nyi();
    }

    public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
        nyi();
    }

    public void setBlob(int parameterIndex, InputStream inputStream, long length)
            throws SQLException {
        nyi();
    }

    public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
        nyi();
    }

    public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
        nyi();
    }

    public void setBinaryStream(int arg0, InputStream arg1, int arg2) throws SQLException {
        nyi();
    }

    public void setBlob(int arg0, Blob arg1) throws SQLException {
        nyi();
    }

    public void setURL(int arg0, URL arg1) throws SQLException {
        nyi();
    }

    public void setUnicodeStream(int arg0, InputStream arg1, int arg2) throws SQLException {
        nyi();
    }

    public void closeOnCompletion() throws SQLException {
        closeOnCompletion = true;
    }

    public boolean isCloseOnCompletion() throws SQLException {
        return closeOnCompletion;
    }
}
