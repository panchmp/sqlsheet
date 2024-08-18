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

import com.sqlsheet.XlsResultSetMetaData;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;

import static com.sqlsheet.XlsResultSet.CTX_NN_15_EVEN;
import static com.sqlsheet.XlsResultSet.wrapped;

/**
 * SqlSheet implementation of java.sql.ResultSet which uses steaming over XLS
 *
 * @author <a href='http://code.google.com/p/sqlsheet'>sqlsheet</a>
 */
public class XlsStreamResultSet implements ResultSet {

    protected Statement statement;
    private final XlsStreamingResultSetMetaData metadata;
    private final int firstSheetColOffset;
    Iterator<Row> iterator;
    Row row = null;
    int rowNum = 0;

    public XlsStreamResultSet(Sheet sheet,
            int firstSheetRowOffset, int firstSheetColOffset)
            throws SQLException {

        this.firstSheetColOffset = firstSheetColOffset;
        this.iterator = sheet.rowIterator();
        metadata = new XlsStreamingResultSetMetaData(sheet, this, firstSheetRowOffset,
                firstSheetColOffset);

        // re-init after we iterated behind the header row
        this.iterator = sheet.rowIterator();
        for (int i = 0; i < firstSheetRowOffset; i++) {
            row = iterator.next();
        }
    }

    protected Cell getCell(int columnIndex) {
        return row != null ? row.getCell((short) (columnIndex + firstSheetColOffset - 1)) : null;
    }

    private short getSheetColumnNamed(String name) throws SQLException {
        int count = metadata.getColumnCount();
        for (short i = 0; i < count; i++) {
            String col = metadata.getColumnName(i + 1);
            if (col.equalsIgnoreCase(name)) {
                return i;
            }
        }
        throw new SQLException(
                "Column "
                        + name
                        + " not found. Available Columns are "
                        + Arrays.deepToString(
                                metadata.columnNames
                                        .toArray(new String[metadata.columnNames.size()])));
    }

    public ResultSetMetaData getMetaData() throws SQLException {
        return metadata;
    }

    @Override
    public BigDecimal getBigDecimal(int columnIndex) throws SQLException {
        Cell cell = getCell(columnIndex);
        if (cell == null || cell.getCellType().equals(CellType.BLANK)) {
            return null;
        } else if (cell.getCellType().equals(CellType.FORMULA)) {
            nyi();
        } else {
            return BigDecimal.valueOf(cell.getNumericCellValue());
        }

        // this should never happen
        return null;
    }

    @Override
    public BigDecimal getBigDecimal(String columnLabel) throws SQLException {
        int columnIndex = getSheetColumnNamed(columnLabel) + 1;
        return getBigDecimal(columnIndex);
    }

    @Override
    public BigDecimal getBigDecimal(int columnIndex, int scale) throws SQLException {
        BigDecimal d = getBigDecimal(columnIndex);
        return d != null ? d.setScale(scale) : null;
    }

    @Override
    public BigDecimal getBigDecimal(String columnLabel, int scale) throws SQLException {
        BigDecimal d = getBigDecimal(columnLabel);
        return d != null ? d.setScale(scale) : null;
    }

    @Override
    public Date getDate(int columnIndex) throws SQLException {
        Cell cell = getCell(columnIndex);
        if (cell == null || cell.getCellType().equals(CellType.BLANK)) {
            return null;
        } else if (cell.getCellType().equals(CellType.FORMULA)) {
            nyi();
        } else {
            return new Date(cell.getDateCellValue().getTime());
        }

        return null;
    }

    @Override
    public Date getDate(String columnLabel) throws SQLException {
        int columnIndex = getSheetColumnNamed(columnLabel) + 1;
        return getDate(columnIndex);
    }

    @Override
    public Date getDate(int columnIndex, Calendar cal) throws SQLException {
        Date date = getDate(columnIndex);
        if (date == null) {
            return null;
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY));
            calendar.set(Calendar.MINUTE, cal.get(Calendar.MINUTE));
            calendar.set(Calendar.SECOND, cal.get(Calendar.SECOND));
            calendar.set(Calendar.MILLISECOND, cal.get(Calendar.MILLISECOND));
            return new Date(calendar.getTime().getTime());
        }
    }

    @Override
    public Date getDate(String columnLabel, Calendar cal) throws SQLException {
        int columnIndex = getSheetColumnNamed(columnLabel) + 1;
        return getDate(columnIndex, cal);
    }

    @Override
    public boolean getBoolean(int columnIndex) throws SQLException {
        Cell cell = getCell(columnIndex);
        if (cell == null || cell.getCellType().equals(CellType.BLANK)) {
            return false;
        } else if (cell.getCellType().equals(CellType.FORMULA)) {
            nyi();
        } else {
            return cell.getBooleanCellValue();
        }

        // this should never happen
        return false;
    }

    @Override
    public boolean getBoolean(String columnLabel) throws SQLException {
        int columnIndex = getSheetColumnNamed(columnLabel) + 1;
        return getBoolean(columnIndex);
    }

    @Override
    public double getDouble(int columnIndex) throws SQLException {
        Cell cell = getCell(columnIndex);
        if (cell == null || cell.getCellType().equals(CellType.BLANK)) {
            return 0d;
        } else if (cell.getCellType().equals(CellType.FORMULA)) {
            nyi();
        } else {
            return cell.getNumericCellValue();
        }

        // this should never happen
        return 0d;
    }

    @Override
    public double getDouble(String columnLabel) throws SQLException {
        int columnIndex = getSheetColumnNamed(columnLabel) + 1;
        return getDouble(columnIndex);
    }

    @Override
    public byte getByte(int columnIndex) throws SQLException {
        Cell cell = getCell(columnIndex);
        if (cell == null || cell.getCellType().equals(CellType.BLANK)) {
            return (byte) 0;
        } else if (cell.getCellType().equals(CellType.FORMULA)) {
            nyi();
        } else {
            return Double.valueOf(cell.getNumericCellValue()).byteValue();
        }

        // this should never happen
        return (byte) 0;
    }

    @Override
    public byte getByte(String columnLabel) throws SQLException {
        int columnIndex = getSheetColumnNamed(columnLabel) + 1;
        return getByte(columnIndex);
    }

    @Override
    public float getFloat(int columnIndex) throws SQLException {
        Cell cell = getCell(columnIndex);
        if (cell == null || cell.getCellType().equals(CellType.BLANK)) {
            return 0f;
        } else if (cell.getCellType().equals(CellType.FORMULA)) {
            nyi();
        } else {
            return Double.valueOf(cell.getNumericCellValue()).floatValue();
        }

        // this should never happen
        return 0f;
    }

    @Override
    public float getFloat(String columnLabel) throws SQLException {
        int columnIndex = getSheetColumnNamed(columnLabel) + 1;
        return getFloat(columnIndex);
    }

    @Override
    public int getInt(int columnIndex) throws SQLException {
        Cell cell = getCell(columnIndex);
        if (cell == null || cell.getCellType().equals(CellType.BLANK)) {
            return 0;
        } else if (cell.getCellType().equals(CellType.FORMULA)) {
            nyi();
        } else {
            return Double.valueOf(cell.getNumericCellValue()).intValue();
        }

        // this should never happen
        return 0;
    }

    @Override
    public int getInt(String columnLabel) throws SQLException {
        int columnIndex = getSheetColumnNamed(columnLabel) + 1;
        return getInt(columnIndex);
    }

    @Override
    public long getLong(int columnIndex) throws SQLException {
        Cell cell = getCell(columnIndex);
        if (cell == null || cell.getCellType().equals(CellType.BLANK)) {
            return 0;
        } else if (cell.getCellType().equals(CellType.FORMULA)) {
            nyi();
        } else {
            return Double.valueOf(cell.getNumericCellValue()).longValue();
        }

        // this should never happen
        return 0L;
    }

    @Override
    public long getLong(String columnLabel) throws SQLException {
        int columnIndex = getSheetColumnNamed(columnLabel) + 1;
        return getLong(columnIndex);
    }

    @Override
    public Object getObject(int columnIndex) throws SQLException {
        Cell cell = getCell(columnIndex);
        int columnType = metadata.getColumnType(columnIndex);
        try {
            if (cell == null || cell.getCellType().equals(CellType.BLANK)) {
                return null;
            }
            switch (cell.getCellType()) {
                case BOOLEAN:
                    if (columnType == Types.VARCHAR || columnType == Types.BOOLEAN) {
                        return cell.getBooleanCellValue();
                    } else {
                        throw new RuntimeException(
                                "The cell ("
                                        + row
                                        + ","
                                        + columnIndex
                                        + ") is a boolean and cannot be cast to ("
                                        + XlsResultSetMetaData.COLUMN_TYPE_NAMES.get(columnType)
                                        + ".");
                    }
                case STRING:
                    if (columnType == Types.VARCHAR) {
                        return cell.getStringCellValue();
                    } else {
                        throw new RuntimeException(
                                "The cell ("
                                        + row
                                        + ","
                                        + columnIndex
                                        + ") is a string cell and cannot be cast to ("
                                        + XlsResultSetMetaData.COLUMN_TYPE_NAMES.get(columnType)
                                        + ".");
                    }
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        java.util.Date value = cell.getDateCellValue();
                        return new Date(value.getTime());
                    } else {
                        return new BigDecimal(Double.valueOf(cell.getNumericCellValue()).toString(),
                                CTX_NN_15_EVEN)
                                .doubleValue();
                    }
                case FORMULA:
                default:
                    return null;
            }
        } catch (Exception e) {
            throw wrapped(e);
        }
    }

    @Override
    public Object getObject(String columnLabel) throws SQLException {
        int columnIndex = getSheetColumnNamed(columnLabel) + 1;
        return getObject(columnIndex);
    }

    @Override
    public <T> T getObject(int columnIndex, Class<T> type) throws SQLException {
        return (T) getObject(columnIndex);
    }

    @Override
    public <T> T getObject(String columnLabel, Class<T> type) throws SQLException {
        return (T) getObject(columnLabel);
    }

    @Override
    public Timestamp getTimestamp(int columnIndex) throws SQLException {
        java.util.Date date = (java.util.Date) getObject(columnIndex);
        if (date == null) {
            return null;
        } else {
            return new Timestamp(date.getTime());
        }
    }

    @Override
    public Timestamp getTimestamp(String columnLabel) throws SQLException {
        int columnIndex = getSheetColumnNamed(columnLabel) + 1;
        return getTimestamp(columnIndex);
    }

    @Override
    public Timestamp getTimestamp(int columnIndex, Calendar cal) {
        throw nyi();
    }

    @Override
    public Timestamp getTimestamp(String jdbcColumn, Calendar cal) {
        throw nyi();
    }

    @Override
    public short getShort(int columnIndex) throws SQLException {
        Cell cell = getCell(columnIndex);
        if (cell == null || cell.getCellType().equals(CellType.BLANK)) {
            return 0;
        } else if (cell.getCellType().equals(CellType.FORMULA)) {
            nyi();
        } else {
            return Double.valueOf(cell.getNumericCellValue()).shortValue();
        }

        // this should never happen
        return (short) 0;
    }

    @Override
    public short getShort(String columnLabel) throws SQLException {
        int columnIndex = getSheetColumnNamed(columnLabel) + 1;
        return getShort(columnIndex);
    }

    @Override
    public String getString(int columnIndex) throws SQLException {
        Cell cell = getCell(columnIndex);
        int columnType = metadata.getColumnType(columnIndex);
        try {
            if (cell == null || cell.getCellType().equals(CellType.BLANK)) {
                return null;
            }
            switch (cell.getCellType()) {
                case BOOLEAN:
                    if (columnType == Types.VARCHAR || columnType == Types.BOOLEAN) {
                        return Boolean.toString(cell.getBooleanCellValue());
                    } else {
                        throw new RuntimeException(
                                "The cell ("
                                        + row
                                        + ","
                                        + columnIndex
                                        + ") is a boolean and cannot be cast to ("
                                        + XlsResultSetMetaData.COLUMN_TYPE_NAMES.get(columnType)
                                        + ".");
                    }
                case STRING:
                    if (columnType == Types.VARCHAR) {
                        return cell.getStringCellValue();
                    } else {
                        throw new RuntimeException(
                                "The cell ("
                                        + row
                                        + ","
                                        + columnIndex
                                        + ") is a string cell and cannot be cast to ("
                                        + XlsResultSetMetaData.COLUMN_TYPE_NAMES.get(columnType)
                                        + ".");
                    }
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        java.util.Date value = cell.getDateCellValue();
                        return new Date(value.getTime()).toString();
                    } else {
                        BigDecimal bd = BigDecimal.valueOf(cell.getNumericCellValue())
                                .round(CTX_NN_15_EVEN).stripTrailingZeros();
                        return bd.toPlainString();
                    }
                case FORMULA:
                    nyi();
                default:
                    return null;
            }
        } catch (Exception e) {
            throw wrapped(e);
        }
    }

    public String getString(String columnLabel) throws SQLException {
        int columnIndex = getSheetColumnNamed(columnLabel) + 1;
        return getString(columnIndex);
    }


    public void updateBoolean(int jdbcColumn, boolean x) throws SQLException {
        throw nyi();
    }

    public void updateBoolean(String jdbcColumn, boolean x) throws SQLException {
        throw nyi();
    }

    public void updateByte(int jdbcColumn, byte x) throws SQLException {
        throw nyi();
    }

    public void updateByte(String jdbcColumn, byte x) throws SQLException {
        throw nyi();
    }

    public void updateDouble(int jdbcColumn, double x) throws SQLException {
        throw nyi();
    }

    public void updateDouble(String jdbcColumn, double x) throws SQLException {
        throw nyi();
    }

    public void updateFloat(int jdbcColumn, float x) throws SQLException {
        throw nyi();
    }

    public void updateFloat(String jdbcColumn, float x) throws SQLException {
        throw nyi();
    }

    public void updateInt(int jdbcColumn, int x) throws SQLException {
        throw nyi();
    }

    public void updateInt(String jdbcColumn, int x) throws SQLException {
        throw nyi();
    }

    public void updateLong(int jdbcColumn, long x) throws SQLException {
        throw nyi();
    }

    public void updateLong(String jdbcColumn, long x) throws SQLException {
        throw nyi();
    }

    public void updateNull(int jdbcColumn) throws SQLException {
        throw nyi();
    }

    public void updateNull(String jdbcColumn) throws SQLException {
        throw nyi();
    }

    public void updateObject(int jdbcColumn, Object x) throws SQLException {
        throw nyi();
    }

    public void updateObject(String jdbcColumn, Object x) throws SQLException {
        throw nyi();
    }

    public void updateShort(int jdbcColumn, short x) throws SQLException {
        throw nyi();
    }

    public void updateShort(String jdbcColumn, short x) throws SQLException {
        throw nyi();
    }

    public void updateString(int jdbcColumn, String x) throws SQLException {
        throw nyi();
    }

    public void updateString(String jdbcColumn, String x) throws SQLException {
        throw nyi();
    }

    // ResultSet implementation - everything else
    public boolean absolute(int row) throws SQLException {
        return true;
    }

    public void afterLast() throws SQLException {
        // nothing
    }

    public void beforeFirst() throws SQLException {
        // nothing
    }

    public boolean first() throws SQLException {
        return true;
    }

    public int getFetchDirection() throws SQLException {
        return FETCH_FORWARD;
    }

    public void setFetchDirection(int direction) throws SQLException {
        throw nyi();
    }

    public int getFetchSize() throws SQLException {
        return 0;
    }

    public void setFetchSize(int rows) throws SQLException {
        throw nyi();
    }

    public int getRow() throws SQLException {
        return rowNum;
    }

    public int getType() throws SQLException {
        return TYPE_FORWARD_ONLY;
    }

    public boolean isAfterLast() throws SQLException {
        return iterator.hasNext();
    }

    public boolean isBeforeFirst() throws SQLException {
        return rowNum == 0;
    }

    public boolean isFirst() throws SQLException {
        return rowNum == 1;
    }

    public boolean isLast() throws SQLException {
        return iterator.hasNext();
    }

    public boolean last() throws SQLException {
        return iterator.hasNext();
    }

    public boolean next() throws SQLException {
        if (iterator.hasNext()) {
            row = iterator.next();
            rowNum++;
            return true;
        } else {
            return false;
        }
    }

    public boolean previous() throws SQLException {
        return false;
    }

    public void moveToInsertRow() throws SQLException {
        throw nyi();
    }

    // Private methods

    public void insertRow() throws SQLException {
        throw nyi();
    }

    private IllegalStateException nyi() {
        return new IllegalStateException("NYI");
    }

    public void cancelRowUpdates() throws SQLException {
        throw nyi();
    }

    public void clearWarnings() throws SQLException {
        throw nyi();
    }

    public void close() throws SQLException {
        // isClosed = true;

        // help the GC by nulling all objects
        // workbook = null;
        // evaluator = null;
        // sheet = null;
        // metadata = null;
        // dateStyle = null;
        //
        // if (statement != null && !statement.isClosed() && statement.isCloseOnCompletion()) {
        // statement.close();
        // }
        //
        // statement = null;
    }

    public void deleteRow() throws SQLException {
        throw nyi();
    }

    public int findColumn(String jdbcColumn) throws SQLException {
        throw nyi();
    }

    public Array getArray(int i) throws SQLException {
        throw nyi();
    }

    public Array getArray(String colName) throws SQLException {
        throw nyi();
    }

    public InputStream getAsciiStream(int jdbcColumn) throws SQLException {
        throw nyi();
    }

    public InputStream getAsciiStream(String jdbcColumn) throws SQLException {
        throw nyi();
    }

    public InputStream getBinaryStream(int jdbcColumn) throws SQLException {
        throw nyi();
    }

    public InputStream getBinaryStream(String jdbcColumn) throws SQLException {
        throw nyi();
    }

    public Blob getBlob(int i) throws SQLException {
        throw nyi();
    }

    public Blob getBlob(String colName) throws SQLException {
        throw nyi();
    }

    public byte[] getBytes(int jdbcColumn) throws SQLException {
        throw nyi();
    }

    public byte[] getBytes(String jdbcColumn) throws SQLException {
        throw nyi();
    }

    public Reader getCharacterStream(int jdbcColumn) throws SQLException {
        throw nyi();
    }

    public Reader getCharacterStream(String jdbcColumn) throws SQLException {
        throw nyi();
    }

    public Clob getClob(int i) throws SQLException {
        throw nyi();
    }

    public Clob getClob(String colName) throws SQLException {
        throw nyi();
    }

    public int getConcurrency() throws SQLException {
        throw nyi();
    }

    public String getCursorName() throws SQLException {
        throw nyi();
    }

    public Object getObject(int i, Map<String, Class<?>> map) throws SQLException {
        throw nyi();
    }

    public Object getObject(String colName, Map<String, Class<?>> map) throws SQLException {
        throw nyi();
    }

    public Ref getRef(int i) throws SQLException {
        throw nyi();
    }

    public Ref getRef(String colName) throws SQLException {
        throw nyi();
    }

    public Statement getStatement() throws SQLException {
        return statement;
    }

    public Time getTime(int jdbcColumn) throws SQLException {
        throw nyi();
    }

    public Time getTime(String jdbcColumn) throws SQLException {
        throw nyi();
    }

    public Time getTime(int jdbcColumn, Calendar cal) throws SQLException {
        throw nyi();
    }

    public Time getTime(String jdbcColumn, Calendar cal) throws SQLException {
        throw nyi();
    }

    public URL getURL(int jdbcColumn) throws SQLException {
        throw nyi();
    }

    public URL getURL(String jdbcColumn) throws SQLException {
        throw nyi();
    }

    public InputStream getUnicodeStream(int jdbcColumn) throws SQLException {
        throw nyi();
    }

    public InputStream getUnicodeStream(String jdbcColumn) throws SQLException {
        throw nyi();
    }

    public SQLWarning getWarnings() throws SQLException {
        throw nyi();
    }

    public void moveToCurrentRow() throws SQLException {
        throw nyi();
    }

    public void refreshRow() throws SQLException {
        throw nyi();
    }

    public boolean relative(int rows) throws SQLException {
        throw nyi();
    }

    public boolean rowDeleted() throws SQLException {
        throw nyi();
    }

    public boolean rowInserted() throws SQLException {
        throw nyi();
    }

    public boolean rowUpdated() throws SQLException {
        throw nyi();
    }

    public void updateArray(int jdbcColumn, Array x) throws SQLException {
        throw nyi();
    }

    public void updateArray(String jdbcColumn, Array x) throws SQLException {
        throw nyi();
    }

    public RowId getRowId(int columnIndex) throws SQLException {
        throw nyi();
    }

    public RowId getRowId(String columnLabel) throws SQLException {
        throw nyi();
    }

    public void updateRowId(int columnIndex, RowId x) throws SQLException {
        throw nyi();
    }

    public void updateRowId(String columnLabel, RowId x) throws SQLException {
        throw nyi();
    }

    public int getHoldability() throws SQLException {
        throw nyi();
    }

    public boolean isClosed() throws SQLException {
        return false;
    }

    public void updateNString(int columnIndex, String nString) throws SQLException {
        throw nyi();
    }

    public void updateNString(String columnLabel, String nString) throws SQLException {
        throw nyi();
    }

    public void updateNClob(int columnIndex, NClob nClob) throws SQLException {
        throw nyi();
    }

    public void updateNClob(String columnLabel, NClob nClob) throws SQLException {
        throw nyi();
    }

    public NClob getNClob(int columnIndex) throws SQLException {
        throw nyi();
    }

    public NClob getNClob(String columnLabel) throws SQLException {
        throw nyi();
    }

    public SQLXML getSQLXML(int columnIndex) throws SQLException {
        throw nyi();
    }

    public SQLXML getSQLXML(String columnLabel) throws SQLException {
        throw nyi();
    }

    public void updateSQLXML(int columnIndex, SQLXML xmlObject) throws SQLException {
        throw nyi();
    }

    public void updateSQLXML(String columnLabel, SQLXML xmlObject) throws SQLException {
        throw nyi();
    }

    public String getNString(int columnIndex) throws SQLException {
        throw nyi();
    }

    public String getNString(String columnLabel) throws SQLException {
        throw nyi();
    }

    public Reader getNCharacterStream(int columnIndex) throws SQLException {
        throw nyi();
    }

    public Reader getNCharacterStream(String columnLabel) throws SQLException {
        throw nyi();
    }

    public void updateNCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
        throw nyi();
    }

    public void updateNCharacterStream(String columnLabel, Reader reader, long length)
            throws SQLException {
        throw nyi();
    }

    public void updateAsciiStream(int columnIndex, InputStream x, long length) throws SQLException {
        throw nyi();
    }

    public void updateBinaryStream(int columnIndex, InputStream x, long length)
            throws SQLException {
        throw nyi();
    }

    public void updateCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
        throw nyi();
    }

    public void updateAsciiStream(String columnLabel, InputStream x, long length)
            throws SQLException {
        throw nyi();
    }

    public void updateBinaryStream(String columnLabel, InputStream x, long length)
            throws SQLException {
        throw nyi();
    }

    public void updateCharacterStream(String columnLabel, Reader reader, long length)
            throws SQLException {
        throw nyi();
    }

    public void updateBlob(int columnIndex, InputStream inputStream, long length)
            throws SQLException {
        throw nyi();
    }

    public void updateBlob(String columnLabel, InputStream inputStream, long length)
            throws SQLException {
        throw nyi();
    }

    public void updateClob(int columnIndex, Reader reader, long length) throws SQLException {
        throw nyi();
    }

    public void updateClob(String columnLabel, Reader reader, long length) throws SQLException {
        throw nyi();
    }

    public void updateNClob(int columnIndex, Reader reader, long length) throws SQLException {
        throw nyi();
    }

    public void updateNClob(String columnLabel, Reader reader, long length) throws SQLException {
        throw nyi();
    }

    public void updateNCharacterStream(int columnIndex, Reader x) throws SQLException {
        throw nyi();
    }

    public void updateNCharacterStream(String columnLabel, Reader reader) throws SQLException {
        throw nyi();
    }

    public void updateAsciiStream(int columnIndex, InputStream x) throws SQLException {
        throw nyi();
    }

    public void updateBinaryStream(int columnIndex, InputStream x) throws SQLException {
        throw nyi();
    }

    public void updateCharacterStream(int columnIndex, Reader x) throws SQLException {
        throw nyi();
    }

    public void updateAsciiStream(String columnLabel, InputStream x) throws SQLException {
        throw nyi();
    }

    public void updateBinaryStream(String columnLabel, InputStream x) throws SQLException {
        throw nyi();
    }

    public void updateCharacterStream(String columnLabel, Reader reader) throws SQLException {
        throw nyi();
    }

    public void updateBlob(int columnIndex, InputStream inputStream) throws SQLException {
        throw nyi();
    }

    public void updateBlob(String columnLabel, InputStream inputStream) throws SQLException {
        throw nyi();
    }

    public void updateClob(int columnIndex, Reader reader) throws SQLException {
        throw nyi();
    }

    public void updateClob(String columnLabel, Reader reader) throws SQLException {
        throw nyi();
    }

    public void updateNClob(int columnIndex, Reader reader) throws SQLException {
        throw nyi();
    }

    public void updateNClob(String columnLabel, Reader reader) throws SQLException {
        throw nyi();
    }

    public void updateAsciiStream(int jdbcColumn, InputStream x, int length) throws SQLException {
        throw nyi();
    }

    public void updateAsciiStream(String jdbcColumn, InputStream x, int length)
            throws SQLException {
        throw nyi();
    }

    public void updateBigDecimal(int jdbcColumn, BigDecimal x) throws SQLException {
        throw nyi();
    }

    public void updateBigDecimal(String jdbcColumn, BigDecimal x) throws SQLException {
        throw nyi();
    }

    public void updateBinaryStream(int jdbcColumn, InputStream x, int length) throws SQLException {
        throw nyi();
    }

    public void updateBinaryStream(String jdbcColumn, InputStream x, int length)
            throws SQLException {
        throw nyi();
    }

    public void updateBlob(int jdbcColumn, Blob x) throws SQLException {
        throw nyi();
    }

    public void updateBlob(String jdbcColumn, Blob x) throws SQLException {
        throw nyi();
    }

    public void updateBytes(int jdbcColumn, byte[] x) throws SQLException {
        throw nyi();
    }

    public void updateBytes(String jdbcColumn, byte[] x) throws SQLException {
        throw nyi();
    }

    public void updateCharacterStream(int jdbcColumn, Reader x, int length) throws SQLException {
        throw nyi();
    }

    public void updateCharacterStream(String jdbcColumn, Reader reader, int length)
            throws SQLException {
        throw nyi();
    }

    public void updateClob(int jdbcColumn, Clob x) throws SQLException {
        throw nyi();
    }

    public void updateClob(String jdbcColumn, Clob x) throws SQLException {
        throw nyi();
    }

    public void updateDate(int jdbcColumn, Date x) throws SQLException {
        throw nyi();
    }

    public void updateDate(String jdbcColumn, Date x) throws SQLException {
        throw nyi();
    }

    public void updateObject(int jdbcColumn, Object x, int scale) throws SQLException {
        throw nyi();
    }

    public void updateObject(String jdbcColumn, Object x, int scale) throws SQLException {
        throw nyi();
    }

    public void updateRef(int jdbcColumn, Ref x) throws SQLException {
        throw nyi();
    }

    public void updateRef(String jdbcColumn, Ref x) throws SQLException {
        throw nyi();
    }

    public void updateRow() throws SQLException {
        throw nyi();
    }

    public void updateTime(int jdbcColumn, Time x) throws SQLException {
        throw nyi();
    }

    public void updateTime(String jdbcColumn, Time x) throws SQLException {
        throw nyi();
    }

    public void updateTimestamp(int jdbcColumn, Timestamp x) throws SQLException {
        throw nyi();
    }

    public void updateTimestamp(String jdbcColumn, Timestamp x) throws SQLException {
        throw nyi();
    }

    public boolean wasNull() throws SQLException {
        throw nyi();
    }

    public <T> T unwrap(Class<T> iface) throws SQLException {
        throw nyi();
    }

    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        throw nyi();
    }
}
