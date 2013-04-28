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

import net.pcal.sqlsheet.parser.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * SqlSheet implementation of java.sql.PreparedStatement.
 *
 * @author <a href='http://www.pcal.net'>pcal</a>
 * @author <a href='http://code.google.com/p/sqlsheet'>sqlsheet</a>
 */
public class XlsPreparedStatement extends XlsStatement implements PreparedStatement {

    private static final Logger logger = Logger.getLogger(XlsPreparedStatement.class.getName());
    private ParsedStatement statement;
    private List<Object> parameters = new ArrayList<Object>();

    public XlsPreparedStatement(XlsConnection conn, String sql) throws SQLException {
        super(conn);
        this.statement = super.parse(sql);
    }

    public void addBatch() throws SQLException {
        nyi();
    }

    public void clearParameters() throws SQLException {
        parameters.clear();
    }

    public boolean execute() throws SQLException {
        executeQuery();
        return true;
    }

    public int executeUpdate() throws SQLException {
        executeQuery();
        return -1;
    }

    public ResultSet executeQuery() throws SQLException {
        if (statement == null) {
            throw new IllegalStateException("null statement");
        }
        if (statement instanceof SelectStarStatement) {
            return super.doSelect((SelectStarStatement) statement);
        }
        if (statement instanceof CreateTableStatement) {
            return super.doCreateTable((CreateTableStatement) statement);
        }
        if (statement instanceof InsertIntoStatement) {
            final InsertIntoStatement iis = (InsertIntoStatement) statement;
            final List<Object> substitutedValues = new ArrayList<Object>();
            substitutedValues.addAll(iis.getValues());
            int paramIndex = 0;
            for (int i = 0; i < substitutedValues.size(); i++) {
                Object val = substitutedValues.get(i);
                logger.log(Level.FINE, "execute Query Insertintostatement: " + substitutedValues.get(i));
                if (val instanceof JdbcParameter) {
                    substitutedValues.set(i, parameters.get(paramIndex++));
                }
            }

            return super.doInsert(new InsertIntoStatement() {
                public List<String> getColumns() {
                    return iis.getColumns();
                }

                public String getTable() {
                    return iis.getTable();
                }

                public List<Object> getValues() {
                    return substitutedValues;
                }
            });
        }
        throw new IllegalStateException("Execute Query Exception: " + statement.getClass().getName());
    }

    private void setParameter(int p, Object val) {
        p = p - 1;
        while (parameters.size() <= p)
            parameters.add(null);
        parameters.set(p, val);
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

    public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
        char[] buff = new char[length];
        try {
            reader.read(buff, 0, length);
            setString(parameterIndex, new String(buff));
        } catch (IOException e) {
            SQLException sqe = new SQLException(e.getMessage());
            sqe.initCause(e);
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

    public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
      nyi();
    }

    public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
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
        nyi();
        return null;
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

    public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
      nyi();
    }

    public void setNClob(int parameterIndex, NClob value) throws SQLException {
      nyi();
    }

    public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
      nyi();
    }

    public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {
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
		throw new UnsupportedOperationException("Not supported yet.");
}

	public boolean isCloseOnCompletion() throws SQLException {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
