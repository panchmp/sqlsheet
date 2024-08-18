
.. raw:: html

    <div id="floating-toc">
        <div class="search-container">
            <input type="button" id="toc-hide-show-btn"></input>
            <input type="text" id="toc-search" placeholder="Search" />
        </div>
        <ul id="toc-list"></ul>
    </div>



#######################################################################
API 7.2
#######################################################################

Base Package: com.sqlsheet


..  _com.googlecode.sqlsheet:
***********************************************************************
e.sqlsheet
***********************************************************************

..  _com.googlecode.sqlsheet.Driver:

=======================================================================
Driver
=======================================================================

*extends:* :ref:`XlsDriver<com.sqlsheet.XlsDriver>` 

| sqlsheet implementation of java.sql.Driver.

| **Driver** ()



..  _com.sqlsheet:
***********************************************************************
Base
***********************************************************************

..  _com.sqlsheet.ResultSetImpl:

=======================================================================
ResultSetImpl
=======================================================================

*extends:* :ref:`Object<java.lang.Object>` *implements:* :ref:`ResultSet<java.sql.ResultSet>` 

| **ResultSetImpl** (columns)
|          :ref:`Object<java.lang.Object>` columns


| **addRow** (data)
|          :ref:`Object<java.lang.Object>` data


| *@Override*
| **next** () → boolean
|          returns boolean



| *@Override*
| **close** ()


| *@Override*
| **wasNull** () → boolean
|          returns boolean



| *@Override*
| **getString** (columnIndex) → :ref:`String<java.lang.String>`
|          int columnIndex
|          returns :ref:`String<java.lang.String>`



| *@Override*
| **getBoolean** (columnIndex) → boolean
|          int columnIndex
|          returns boolean



| *@Override*
| **getByte** (columnIndex) → byte
|          int columnIndex
|          returns byte



| *@Override*
| **getShort** (columnIndex) → short
|          int columnIndex
|          returns short



| *@Override*
| **getInt** (columnIndex) → int
|          int columnIndex
|          returns int



| *@Override*
| **getLong** (columnIndex) → long
|          int columnIndex
|          returns long



| *@Override*
| **getFloat** (columnIndex) → float
|          int columnIndex
|          returns float



| *@Override*
| **getDouble** (columnIndex) → double
|          int columnIndex
|          returns double



| *@Override*
| **getBigDecimal** (columnIndex, scale) → :ref:`BigDecimal<java.math.BigDecimal>`
|          int columnIndex
|          int scale
|          returns :ref:`BigDecimal<java.math.BigDecimal>`



| *@Override*
| **getBytes** (columnIndex) → byte
|          int columnIndex
|          returns byte



| *@Override*
| **getDate** (columnIndex) → :ref:`Date<java.sql.Date>`
|          int columnIndex
|          returns :ref:`Date<java.sql.Date>`



| *@Override*
| **getTime** (columnIndex) → :ref:`Time<java.sql.Time>`
|          int columnIndex
|          returns :ref:`Time<java.sql.Time>`



| *@Override*
| **getTimestamp** (columnIndex) → :ref:`Timestamp<java.sql.Timestamp>`
|          int columnIndex
|          returns :ref:`Timestamp<java.sql.Timestamp>`



| *@Override*
| **getAsciiStream** (columnIndex) → :ref:`InputStream<java.io.InputStream>`
|          int columnIndex
|          returns :ref:`InputStream<java.io.InputStream>`



| *@Override*
| **getUnicodeStream** (columnIndex) → :ref:`InputStream<java.io.InputStream>`
|          int columnIndex
|          returns :ref:`InputStream<java.io.InputStream>`



| *@Override*
| **getBinaryStream** (columnIndex) → :ref:`InputStream<java.io.InputStream>`
|          int columnIndex
|          returns :ref:`InputStream<java.io.InputStream>`



| *@Override*
| **getString** (columnLabel) → :ref:`String<java.lang.String>`
|          :ref:`String<java.lang.String>` columnLabel
|          returns :ref:`String<java.lang.String>`



| *@Override*
| **getBoolean** (columnLabel) → boolean
|          :ref:`String<java.lang.String>` columnLabel
|          returns boolean



| *@Override*
| **getByte** (columnLabel) → byte
|          :ref:`String<java.lang.String>` columnLabel
|          returns byte



| *@Override*
| **getShort** (columnLabel) → short
|          :ref:`String<java.lang.String>` columnLabel
|          returns short



| *@Override*
| **getInt** (columnLabel) → int
|          :ref:`String<java.lang.String>` columnLabel
|          returns int



| *@Override*
| **getLong** (columnLabel) → long
|          :ref:`String<java.lang.String>` columnLabel
|          returns long



| *@Override*
| **getFloat** (columnLabel) → float
|          :ref:`String<java.lang.String>` columnLabel
|          returns float



| *@Override*
| **getDouble** (columnLabel) → double
|          :ref:`String<java.lang.String>` columnLabel
|          returns double



| *@Override*
| **getBigDecimal** (columnLabel, scale) → :ref:`BigDecimal<java.math.BigDecimal>`
|          :ref:`String<java.lang.String>` columnLabel
|          int scale
|          returns :ref:`BigDecimal<java.math.BigDecimal>`



| *@Override*
| **getBytes** (columnLabel) → byte
|          :ref:`String<java.lang.String>` columnLabel
|          returns byte



| *@Override*
| **getDate** (columnLabel) → :ref:`Date<java.sql.Date>`
|          :ref:`String<java.lang.String>` columnLabel
|          returns :ref:`Date<java.sql.Date>`



| *@Override*
| **getTime** (columnLabel) → :ref:`Time<java.sql.Time>`
|          :ref:`String<java.lang.String>` columnLabel
|          returns :ref:`Time<java.sql.Time>`



| *@Override*
| **getTimestamp** (columnLabel) → :ref:`Timestamp<java.sql.Timestamp>`
|          :ref:`String<java.lang.String>` columnLabel
|          returns :ref:`Timestamp<java.sql.Timestamp>`



| *@Override*
| **getAsciiStream** (columnLabel) → :ref:`InputStream<java.io.InputStream>`
|          :ref:`String<java.lang.String>` columnLabel
|          returns :ref:`InputStream<java.io.InputStream>`



| *@Override*
| **getUnicodeStream** (columnLabel) → :ref:`InputStream<java.io.InputStream>`
|          :ref:`String<java.lang.String>` columnLabel
|          returns :ref:`InputStream<java.io.InputStream>`



| *@Override*
| **getBinaryStream** (columnLabel) → :ref:`InputStream<java.io.InputStream>`
|          :ref:`String<java.lang.String>` columnLabel
|          returns :ref:`InputStream<java.io.InputStream>`



| *@Override*
| **getWarnings** () → :ref:`SQLWarning<java.sql.SQLWarning>`
|          returns :ref:`SQLWarning<java.sql.SQLWarning>`



| *@Override*
| **clearWarnings** ()


| *@Override*
| **getCursorName** () → :ref:`String<java.lang.String>`
|          returns :ref:`String<java.lang.String>`



| *@Override*
| **getMetaData** () → :ref:`ResultSetMetaData<java.sql.ResultSetMetaData>`
|          returns :ref:`ResultSetMetaData<java.sql.ResultSetMetaData>`



| *@Override*
| **getObject** (columnIndex) → :ref:`Object<java.lang.Object>`
|          int columnIndex
|          returns :ref:`Object<java.lang.Object>`



| *@Override*
| **getObject** (columnLabel) → :ref:`Object<java.lang.Object>`
|          :ref:`String<java.lang.String>` columnLabel
|          returns :ref:`Object<java.lang.Object>`



| *@Override*
| **findColumn** (columnLabel) → int
|          :ref:`String<java.lang.String>` columnLabel
|          returns int



| *@Override*
| **getCharacterStream** (columnIndex) → :ref:`Reader<java.io.Reader>`
|          int columnIndex
|          returns :ref:`Reader<java.io.Reader>`



| *@Override*
| **getCharacterStream** (columnLabel) → :ref:`Reader<java.io.Reader>`
|          :ref:`String<java.lang.String>` columnLabel
|          returns :ref:`Reader<java.io.Reader>`



| *@Override*
| **getBigDecimal** (columnIndex) → :ref:`BigDecimal<java.math.BigDecimal>`
|          int columnIndex
|          returns :ref:`BigDecimal<java.math.BigDecimal>`



| *@Override*
| **getBigDecimal** (columnLabel) → :ref:`BigDecimal<java.math.BigDecimal>`
|          :ref:`String<java.lang.String>` columnLabel
|          returns :ref:`BigDecimal<java.math.BigDecimal>`



| *@Override*
| **isBeforeFirst** () → boolean
|          returns boolean



| *@Override*
| **isAfterLast** () → boolean
|          returns boolean



| *@Override*
| **isFirst** () → boolean
|          returns boolean



| *@Override*
| **isLast** () → boolean
|          returns boolean



| *@Override*
| **beforeFirst** ()


| *@Override*
| **afterLast** ()


| *@Override*
| **first** () → boolean
|          returns boolean



| *@Override*
| **last** () → boolean
|          returns boolean



| *@Override*
| **getRow** () → int
|          returns int



| *@Override*
| **absolute** (row) → boolean
|          int row
|          returns boolean



| *@Override*
| **relative** (rows) → boolean
|          int rows
|          returns boolean



| *@Override*
| **previous** () → boolean
|          returns boolean



| *@Override*
| **getFetchDirection** () → int
|          returns int



| *@Override*
| **setFetchDirection** (direction)
|          int direction


| *@Override*
| **getFetchSize** () → int
|          returns int



| *@Override*
| **setFetchSize** (rows)
|          int rows


| *@Override*
| **getType** () → int
|          returns int



| *@Override*
| **getConcurrency** () → int
|          returns int



| *@Override*
| **rowUpdated** () → boolean
|          returns boolean



| *@Override*
| **rowInserted** () → boolean
|          returns boolean



| *@Override*
| **rowDeleted** () → boolean
|          returns boolean



| *@Override*
| **updateNull** (columnIndex)
|          int columnIndex


| *@Override*
| **updateBoolean** (columnIndex, x)
|          int columnIndex
|          boolean x


| *@Override*
| **updateByte** (columnIndex, x)
|          int columnIndex
|          byte x


| *@Override*
| **updateShort** (columnIndex, x)
|          int columnIndex
|          short x


| *@Override*
| **updateInt** (columnIndex, x)
|          int columnIndex
|          int x


| *@Override*
| **updateLong** (columnIndex, x)
|          int columnIndex
|          long x


| *@Override*
| **updateFloat** (columnIndex, x)
|          int columnIndex
|          float x


| *@Override*
| **updateDouble** (columnIndex, x)
|          int columnIndex
|          double x


| *@Override*
| **updateBigDecimal** (columnIndex, x)
|          int columnIndex
|          :ref:`BigDecimal<java.math.BigDecimal>` x


| *@Override*
| **updateString** (columnIndex, x)
|          int columnIndex
|          :ref:`String<java.lang.String>` x


| *@Override*
| **updateBytes** (columnIndex, x)
|          int columnIndex
|          byte x


| *@Override*
| **updateDate** (columnIndex, x)
|          int columnIndex
|          :ref:`Date<java.sql.Date>` x


| *@Override*
| **updateTime** (columnIndex, x)
|          int columnIndex
|          :ref:`Time<java.sql.Time>` x


| *@Override*
| **updateTimestamp** (columnIndex, x)
|          int columnIndex
|          :ref:`Timestamp<java.sql.Timestamp>` x


| *@Override*
| **updateAsciiStream** (columnIndex, x, length)
|          int columnIndex
|          :ref:`InputStream<java.io.InputStream>` x
|          int length


| *@Override*
| **updateBinaryStream** (columnIndex, x, length)
|          int columnIndex
|          :ref:`InputStream<java.io.InputStream>` x
|          int length


| *@Override*
| **updateCharacterStream** (columnIndex, x, length)
|          int columnIndex
|          :ref:`Reader<java.io.Reader>` x
|          int length


| *@Override*
| **updateObject** (columnIndex, x, scaleOrLength)
|          int columnIndex
|          :ref:`Object<java.lang.Object>` x
|          int scaleOrLength


| *@Override*
| **updateObject** (columnIndex, x)
|          int columnIndex
|          :ref:`Object<java.lang.Object>` x


| *@Override*
| **updateNull** (columnLabel)
|          :ref:`String<java.lang.String>` columnLabel


| *@Override*
| **updateBoolean** (columnLabel, x)
|          :ref:`String<java.lang.String>` columnLabel
|          boolean x


| *@Override*
| **updateByte** (columnLabel, x)
|          :ref:`String<java.lang.String>` columnLabel
|          byte x


| *@Override*
| **updateShort** (columnLabel, x)
|          :ref:`String<java.lang.String>` columnLabel
|          short x


| *@Override*
| **updateInt** (columnLabel, x)
|          :ref:`String<java.lang.String>` columnLabel
|          int x


| *@Override*
| **updateLong** (columnLabel, x)
|          :ref:`String<java.lang.String>` columnLabel
|          long x


| *@Override*
| **updateFloat** (columnLabel, x)
|          :ref:`String<java.lang.String>` columnLabel
|          float x


| *@Override*
| **updateDouble** (columnLabel, x)
|          :ref:`String<java.lang.String>` columnLabel
|          double x


| *@Override*
| **updateBigDecimal** (columnLabel, x)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`BigDecimal<java.math.BigDecimal>` x


| *@Override*
| **updateString** (columnLabel, x)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`String<java.lang.String>` x


| *@Override*
| **updateBytes** (columnLabel, x)
|          :ref:`String<java.lang.String>` columnLabel
|          byte x


| *@Override*
| **updateDate** (columnLabel, x)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`Date<java.sql.Date>` x


| *@Override*
| **updateTime** (columnLabel, x)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`Time<java.sql.Time>` x


| *@Override*
| **updateTimestamp** (columnLabel, x)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`Timestamp<java.sql.Timestamp>` x


| *@Override*
| **updateAsciiStream** (columnLabel, x, length)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`InputStream<java.io.InputStream>` x
|          int length


| *@Override*
| **updateBinaryStream** (columnLabel, x, length)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`InputStream<java.io.InputStream>` x
|          int length


| *@Override*
| **updateCharacterStream** (columnLabel, reader, length)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`Reader<java.io.Reader>` reader
|          int length


| *@Override*
| **updateObject** (columnLabel, x, scaleOrLength)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`Object<java.lang.Object>` x
|          int scaleOrLength


| *@Override*
| **updateObject** (columnLabel, x)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`Object<java.lang.Object>` x


| *@Override*
| **insertRow** ()


| *@Override*
| **updateRow** ()


| *@Override*
| **deleteRow** ()


| *@Override*
| **refreshRow** ()


| *@Override*
| **cancelRowUpdates** ()


| *@Override*
| **moveToInsertRow** ()


| *@Override*
| **moveToCurrentRow** ()


| *@Override*
| **getStatement** () → :ref:`Statement<java.sql.Statement>`
|          returns :ref:`Statement<java.sql.Statement>`



| *@Override*
| **getObject** (columnIndex, map) → :ref:`Object<java.lang.Object>`
|          int columnIndex
|          :ref:`Map<java.util.Map>` map
|          returns :ref:`Object<java.lang.Object>`



| *@Override*
| **getRef** (columnIndex) → :ref:`Ref<java.sql.Ref>`
|          int columnIndex
|          returns :ref:`Ref<java.sql.Ref>`



| *@Override*
| **getBlob** (columnIndex) → :ref:`Blob<java.sql.Blob>`
|          int columnIndex
|          returns :ref:`Blob<java.sql.Blob>`



| *@Override*
| **getClob** (columnIndex) → :ref:`Clob<java.sql.Clob>`
|          int columnIndex
|          returns :ref:`Clob<java.sql.Clob>`



| *@Override*
| **getArray** (columnIndex) → :ref:`Array<java.sql.Array>`
|          int columnIndex
|          returns :ref:`Array<java.sql.Array>`



| *@Override*
| **getObject** (columnLabel, map) → :ref:`Object<java.lang.Object>`
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`Map<java.util.Map>` map
|          returns :ref:`Object<java.lang.Object>`



| *@Override*
| **getRef** (columnLabel) → :ref:`Ref<java.sql.Ref>`
|          :ref:`String<java.lang.String>` columnLabel
|          returns :ref:`Ref<java.sql.Ref>`



| *@Override*
| **getBlob** (columnLabel) → :ref:`Blob<java.sql.Blob>`
|          :ref:`String<java.lang.String>` columnLabel
|          returns :ref:`Blob<java.sql.Blob>`



| *@Override*
| **getClob** (columnLabel) → :ref:`Clob<java.sql.Clob>`
|          :ref:`String<java.lang.String>` columnLabel
|          returns :ref:`Clob<java.sql.Clob>`



| *@Override*
| **getArray** (columnLabel) → :ref:`Array<java.sql.Array>`
|          :ref:`String<java.lang.String>` columnLabel
|          returns :ref:`Array<java.sql.Array>`



| *@Override*
| **getDate** (columnIndex, cal) → :ref:`Date<java.sql.Date>`
|          int columnIndex
|          :ref:`Calendar<java.util.Calendar>` cal
|          returns :ref:`Date<java.sql.Date>`



| *@Override*
| **getDate** (columnLabel, cal) → :ref:`Date<java.sql.Date>`
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`Calendar<java.util.Calendar>` cal
|          returns :ref:`Date<java.sql.Date>`



| *@Override*
| **getTime** (columnIndex, cal) → :ref:`Time<java.sql.Time>`
|          int columnIndex
|          :ref:`Calendar<java.util.Calendar>` cal
|          returns :ref:`Time<java.sql.Time>`



| *@Override*
| **getTime** (columnLabel, cal) → :ref:`Time<java.sql.Time>`
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`Calendar<java.util.Calendar>` cal
|          returns :ref:`Time<java.sql.Time>`



| *@Override*
| **getTimestamp** (columnIndex, cal) → :ref:`Timestamp<java.sql.Timestamp>`
|          int columnIndex
|          :ref:`Calendar<java.util.Calendar>` cal
|          returns :ref:`Timestamp<java.sql.Timestamp>`



| *@Override*
| **getTimestamp** (columnLabel, cal) → :ref:`Timestamp<java.sql.Timestamp>`
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`Calendar<java.util.Calendar>` cal
|          returns :ref:`Timestamp<java.sql.Timestamp>`



| *@Override*
| **getURL** (columnIndex) → :ref:`URL<java.net.URL>`
|          int columnIndex
|          returns :ref:`URL<java.net.URL>`



| *@Override*
| **getURL** (columnLabel) → :ref:`URL<java.net.URL>`
|          :ref:`String<java.lang.String>` columnLabel
|          returns :ref:`URL<java.net.URL>`



| *@Override*
| **updateRef** (columnIndex, x)
|          int columnIndex
|          :ref:`Ref<java.sql.Ref>` x


| *@Override*
| **updateRef** (columnLabel, x)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`Ref<java.sql.Ref>` x


| *@Override*
| **updateBlob** (columnIndex, x)
|          int columnIndex
|          :ref:`Blob<java.sql.Blob>` x


| *@Override*
| **updateBlob** (columnLabel, x)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`Blob<java.sql.Blob>` x


| *@Override*
| **updateClob** (columnIndex, x)
|          int columnIndex
|          :ref:`Clob<java.sql.Clob>` x


| *@Override*
| **updateClob** (columnLabel, x)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`Clob<java.sql.Clob>` x


| *@Override*
| **updateArray** (columnIndex, x)
|          int columnIndex
|          :ref:`Array<java.sql.Array>` x


| *@Override*
| **updateArray** (columnLabel, x)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`Array<java.sql.Array>` x


| *@Override*
| **getRowId** (columnIndex) → :ref:`RowId<java.sql.RowId>`
|          int columnIndex
|          returns :ref:`RowId<java.sql.RowId>`



| *@Override*
| **getRowId** (columnLabel) → :ref:`RowId<java.sql.RowId>`
|          :ref:`String<java.lang.String>` columnLabel
|          returns :ref:`RowId<java.sql.RowId>`



| *@Override*
| **updateRowId** (columnIndex, x)
|          int columnIndex
|          :ref:`RowId<java.sql.RowId>` x


| *@Override*
| **updateRowId** (columnLabel, x)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`RowId<java.sql.RowId>` x


| *@Override*
| **getHoldability** () → int
|          returns int



| *@Override*
| **isClosed** () → boolean
|          returns boolean



| *@Override*
| **updateNString** (columnIndex, nString)
|          int columnIndex
|          :ref:`String<java.lang.String>` nString


| *@Override*
| **updateNString** (columnLabel, nString)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`String<java.lang.String>` nString


| *@Override*
| **updateNClob** (columnIndex, nClob)
|          int columnIndex
|          :ref:`NClob<java.sql.NClob>` nClob


| *@Override*
| **updateNClob** (columnLabel, nClob)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`NClob<java.sql.NClob>` nClob


| *@Override*
| **getNClob** (columnIndex) → :ref:`NClob<java.sql.NClob>`
|          int columnIndex
|          returns :ref:`NClob<java.sql.NClob>`



| *@Override*
| **getNClob** (columnLabel) → :ref:`NClob<java.sql.NClob>`
|          :ref:`String<java.lang.String>` columnLabel
|          returns :ref:`NClob<java.sql.NClob>`



| *@Override*
| **getSQLXML** (columnIndex) → :ref:`SQLXML<java.sql.SQLXML>`
|          int columnIndex
|          returns :ref:`SQLXML<java.sql.SQLXML>`



| *@Override*
| **getSQLXML** (columnLabel) → :ref:`SQLXML<java.sql.SQLXML>`
|          :ref:`String<java.lang.String>` columnLabel
|          returns :ref:`SQLXML<java.sql.SQLXML>`



| *@Override*
| **updateSQLXML** (columnIndex, xmlObject)
|          int columnIndex
|          :ref:`SQLXML<java.sql.SQLXML>` xmlObject


| *@Override*
| **updateSQLXML** (columnLabel, xmlObject)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`SQLXML<java.sql.SQLXML>` xmlObject


| *@Override*
| **getNString** (columnIndex) → :ref:`String<java.lang.String>`
|          int columnIndex
|          returns :ref:`String<java.lang.String>`



| *@Override*
| **getNString** (columnLabel) → :ref:`String<java.lang.String>`
|          :ref:`String<java.lang.String>` columnLabel
|          returns :ref:`String<java.lang.String>`



| *@Override*
| **getNCharacterStream** (columnIndex) → :ref:`Reader<java.io.Reader>`
|          int columnIndex
|          returns :ref:`Reader<java.io.Reader>`



| *@Override*
| **getNCharacterStream** (columnLabel) → :ref:`Reader<java.io.Reader>`
|          :ref:`String<java.lang.String>` columnLabel
|          returns :ref:`Reader<java.io.Reader>`



| *@Override*
| **updateNCharacterStream** (columnIndex, x, length)
|          int columnIndex
|          :ref:`Reader<java.io.Reader>` x
|          long length


| *@Override*
| **updateNCharacterStream** (columnLabel, reader, length)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`Reader<java.io.Reader>` reader
|          long length


| *@Override*
| **updateAsciiStream** (columnIndex, x, length)
|          int columnIndex
|          :ref:`InputStream<java.io.InputStream>` x
|          long length


| *@Override*
| **updateBinaryStream** (columnIndex, x, length)
|          int columnIndex
|          :ref:`InputStream<java.io.InputStream>` x
|          long length


| *@Override*
| **updateCharacterStream** (columnIndex, x, length)
|          int columnIndex
|          :ref:`Reader<java.io.Reader>` x
|          long length


| *@Override*
| **updateAsciiStream** (columnLabel, x, length)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`InputStream<java.io.InputStream>` x
|          long length


| *@Override*
| **updateBinaryStream** (columnLabel, x, length)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`InputStream<java.io.InputStream>` x
|          long length


| *@Override*
| **updateCharacterStream** (columnLabel, reader, length)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`Reader<java.io.Reader>` reader
|          long length


| *@Override*
| **updateBlob** (columnIndex, inputStream, length)
|          int columnIndex
|          :ref:`InputStream<java.io.InputStream>` inputStream
|          long length


| *@Override*
| **updateBlob** (columnLabel, inputStream, length)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`InputStream<java.io.InputStream>` inputStream
|          long length


| *@Override*
| **updateClob** (columnIndex, reader, length)
|          int columnIndex
|          :ref:`Reader<java.io.Reader>` reader
|          long length


| *@Override*
| **updateClob** (columnLabel, reader, length)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`Reader<java.io.Reader>` reader
|          long length


| *@Override*
| **updateNClob** (columnIndex, reader, length)
|          int columnIndex
|          :ref:`Reader<java.io.Reader>` reader
|          long length


| *@Override*
| **updateNClob** (columnLabel, reader, length)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`Reader<java.io.Reader>` reader
|          long length


| *@Override*
| **updateNCharacterStream** (columnIndex, x)
|          int columnIndex
|          :ref:`Reader<java.io.Reader>` x


| *@Override*
| **updateNCharacterStream** (columnLabel, reader)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`Reader<java.io.Reader>` reader


| *@Override*
| **updateAsciiStream** (columnIndex, x)
|          int columnIndex
|          :ref:`InputStream<java.io.InputStream>` x


| *@Override*
| **updateBinaryStream** (columnIndex, x)
|          int columnIndex
|          :ref:`InputStream<java.io.InputStream>` x


| *@Override*
| **updateCharacterStream** (columnIndex, x)
|          int columnIndex
|          :ref:`Reader<java.io.Reader>` x


| *@Override*
| **updateAsciiStream** (columnLabel, x)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`InputStream<java.io.InputStream>` x


| *@Override*
| **updateBinaryStream** (columnLabel, x)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`InputStream<java.io.InputStream>` x


| *@Override*
| **updateCharacterStream** (columnLabel, reader)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`Reader<java.io.Reader>` reader


| *@Override*
| **updateBlob** (columnIndex, inputStream)
|          int columnIndex
|          :ref:`InputStream<java.io.InputStream>` inputStream


| *@Override*
| **updateBlob** (columnLabel, inputStream)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`InputStream<java.io.InputStream>` inputStream


| *@Override*
| **updateClob** (columnIndex, reader)
|          int columnIndex
|          :ref:`Reader<java.io.Reader>` reader


| *@Override*
| **updateClob** (columnLabel, reader)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`Reader<java.io.Reader>` reader


| *@Override*
| **updateNClob** (columnIndex, reader)
|          int columnIndex
|          :ref:`Reader<java.io.Reader>` reader


| *@Override*
| **updateNClob** (columnLabel, reader)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`Reader<java.io.Reader>` reader


| *@Override*
| **getObject** (columnIndex, type) → T
|          int columnIndex
|          :ref:`Class<java.lang.Class>` type
|          returns T



| *@Override*
| **getObject** (columnLabel, type) → T
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`Class<java.lang.Class>` type
|          returns T



| *@Override*
| **unwrap** (iface) → T
|          :ref:`Class<java.lang.Class>` iface
|          returns T



| *@Override*
| **isWrapperFor** (iface) → boolean
|          :ref:`Class<java.lang.Class>` iface
|          returns boolean




..  _com.sqlsheet.XlsDatabaseMetaData:

=======================================================================
XlsDatabaseMetaData
=======================================================================

*extends:* :ref:`Object<java.lang.Object>` *implements:* :ref:`DatabaseMetaData<java.sql.DatabaseMetaData>` 

| **XlsDatabaseMetaData** (connection)
|          :ref:`XlsConnection<com.sqlsheet.XlsConnection>` connection


| *@Override*
| **allProceduresAreCallable** () → boolean
|          returns boolean



| *@Override*
| **allTablesAreSelectable** () → boolean
|          returns boolean



| *@Override*
| **getURL** () → :ref:`String<java.lang.String>`
|          returns :ref:`String<java.lang.String>`



| *@Override*
| **getUserName** () → :ref:`String<java.lang.String>`
|          returns :ref:`String<java.lang.String>`



| *@Override*
| **isReadOnly** () → boolean
|          returns boolean



| *@Override*
| **nullsAreSortedHigh** () → boolean
|          returns boolean



| *@Override*
| **nullsAreSortedLow** () → boolean
|          returns boolean



| *@Override*
| **nullsAreSortedAtStart** () → boolean
|          returns boolean



| *@Override*
| **nullsAreSortedAtEnd** () → boolean
|          returns boolean



| *@Override*
| **getDatabaseProductName** () → :ref:`String<java.lang.String>`
|          returns :ref:`String<java.lang.String>`



| *@Override*
| **getDatabaseProductVersion** () → :ref:`String<java.lang.String>`
|          returns :ref:`String<java.lang.String>`



| *@Override*
| **getDriverName** () → :ref:`String<java.lang.String>`
|          returns :ref:`String<java.lang.String>`



| *@Override*
| **getDriverVersion** () → :ref:`String<java.lang.String>`
|          returns :ref:`String<java.lang.String>`



| *@Override*
| **getDriverMajorVersion** () → int
|          returns int



| *@Override*
| **getDriverMinorVersion** () → int
|          returns int



| *@Override*
| **usesLocalFiles** () → boolean
|          returns boolean



| *@Override*
| **usesLocalFilePerTable** () → boolean
|          returns boolean



| *@Override*
| **supportsMixedCaseIdentifiers** () → boolean
|          returns boolean



| *@Override*
| **storesUpperCaseIdentifiers** () → boolean
|          returns boolean



| *@Override*
| **storesLowerCaseIdentifiers** () → boolean
|          returns boolean



| *@Override*
| **storesMixedCaseIdentifiers** () → boolean
|          returns boolean



| *@Override*
| **supportsMixedCaseQuotedIdentifiers** () → boolean
|          returns boolean



| *@Override*
| **storesUpperCaseQuotedIdentifiers** () → boolean
|          returns boolean



| *@Override*
| **storesLowerCaseQuotedIdentifiers** () → boolean
|          returns boolean



| *@Override*
| **storesMixedCaseQuotedIdentifiers** () → boolean
|          returns boolean



| *@Override*
| **getIdentifierQuoteString** () → :ref:`String<java.lang.String>`
|          returns :ref:`String<java.lang.String>`



| *@Override*
| **getSQLKeywords** () → :ref:`String<java.lang.String>`
|          returns :ref:`String<java.lang.String>`



| *@Override*
| **getNumericFunctions** () → :ref:`String<java.lang.String>`
|          returns :ref:`String<java.lang.String>`



| *@Override*
| **getStringFunctions** () → :ref:`String<java.lang.String>`
|          returns :ref:`String<java.lang.String>`



| *@Override*
| **getSystemFunctions** () → :ref:`String<java.lang.String>`
|          returns :ref:`String<java.lang.String>`



| *@Override*
| **getTimeDateFunctions** () → :ref:`String<java.lang.String>`
|          returns :ref:`String<java.lang.String>`



| *@Override*
| **getSearchStringEscape** () → :ref:`String<java.lang.String>`
|          returns :ref:`String<java.lang.String>`



| *@Override*
| **getExtraNameCharacters** () → :ref:`String<java.lang.String>`
|          returns :ref:`String<java.lang.String>`



| *@Override*
| **supportsAlterTableWithAddColumn** () → boolean
|          returns boolean



| *@Override*
| **supportsAlterTableWithDropColumn** () → boolean
|          returns boolean



| *@Override*
| **supportsColumnAliasing** () → boolean
|          returns boolean



| *@Override*
| **nullPlusNonNullIsNull** () → boolean
|          returns boolean



| *@Override*
| **supportsConvert** () → boolean
|          returns boolean



| *@Override*
| **supportsConvert** (i, i1) → boolean
|          int i
|          int i1
|          returns boolean



| *@Override*
| **supportsTableCorrelationNames** () → boolean
|          returns boolean



| *@Override*
| **supportsDifferentTableCorrelationNames** () → boolean
|          returns boolean



| *@Override*
| **supportsExpressionsInOrderBy** () → boolean
|          returns boolean



| *@Override*
| **supportsOrderByUnrelated** () → boolean
|          returns boolean



| *@Override*
| **supportsGroupBy** () → boolean
|          returns boolean



| *@Override*
| **supportsGroupByUnrelated** () → boolean
|          returns boolean



| *@Override*
| **supportsGroupByBeyondSelect** () → boolean
|          returns boolean



| *@Override*
| **supportsLikeEscapeClause** () → boolean
|          returns boolean



| *@Override*
| **supportsMultipleResultSets** () → boolean
|          returns boolean



| *@Override*
| **supportsMultipleTransactions** () → boolean
|          returns boolean



| *@Override*
| **supportsNonNullableColumns** () → boolean
|          returns boolean



| *@Override*
| **supportsMinimumSQLGrammar** () → boolean
|          returns boolean



| *@Override*
| **supportsCoreSQLGrammar** () → boolean
|          returns boolean



| *@Override*
| **supportsExtendedSQLGrammar** () → boolean
|          returns boolean



| *@Override*
| **supportsANSI92EntryLevelSQL** () → boolean
|          returns boolean



| *@Override*
| **supportsANSI92IntermediateSQL** () → boolean
|          returns boolean



| *@Override*
| **supportsANSI92FullSQL** () → boolean
|          returns boolean



| *@Override*
| **supportsIntegrityEnhancementFacility** () → boolean
|          returns boolean



| *@Override*
| **supportsOuterJoins** () → boolean
|          returns boolean



| *@Override*
| **supportsFullOuterJoins** () → boolean
|          returns boolean



| *@Override*
| **supportsLimitedOuterJoins** () → boolean
|          returns boolean



| *@Override*
| **getSchemaTerm** () → :ref:`String<java.lang.String>`
|          returns :ref:`String<java.lang.String>`



| *@Override*
| **getProcedureTerm** () → :ref:`String<java.lang.String>`
|          returns :ref:`String<java.lang.String>`



| *@Override*
| **getCatalogTerm** () → :ref:`String<java.lang.String>`
|          returns :ref:`String<java.lang.String>`



| *@Override*
| **isCatalogAtStart** () → boolean
|          returns boolean



| *@Override*
| **getCatalogSeparator** () → :ref:`String<java.lang.String>`
|          returns :ref:`String<java.lang.String>`



| *@Override*
| **supportsSchemasInDataManipulation** () → boolean
|          returns boolean



| *@Override*
| **supportsSchemasInProcedureCalls** () → boolean
|          returns boolean



| *@Override*
| **supportsSchemasInTableDefinitions** () → boolean
|          returns boolean



| *@Override*
| **supportsSchemasInIndexDefinitions** () → boolean
|          returns boolean



| *@Override*
| **supportsSchemasInPrivilegeDefinitions** () → boolean
|          returns boolean



| *@Override*
| **supportsCatalogsInDataManipulation** () → boolean
|          returns boolean



| *@Override*
| **supportsCatalogsInProcedureCalls** () → boolean
|          returns boolean



| *@Override*
| **supportsCatalogsInTableDefinitions** () → boolean
|          returns boolean



| *@Override*
| **supportsCatalogsInIndexDefinitions** () → boolean
|          returns boolean



| *@Override*
| **supportsCatalogsInPrivilegeDefinitions** () → boolean
|          returns boolean



| *@Override*
| **supportsPositionedDelete** () → boolean
|          returns boolean



| *@Override*
| **supportsPositionedUpdate** () → boolean
|          returns boolean



| *@Override*
| **supportsSelectForUpdate** () → boolean
|          returns boolean



| *@Override*
| **supportsStoredProcedures** () → boolean
|          returns boolean



| *@Override*
| **supportsSubqueriesInComparisons** () → boolean
|          returns boolean



| *@Override*
| **supportsSubqueriesInExists** () → boolean
|          returns boolean



| *@Override*
| **supportsSubqueriesInIns** () → boolean
|          returns boolean



| *@Override*
| **supportsSubqueriesInQuantifieds** () → boolean
|          returns boolean



| *@Override*
| **supportsCorrelatedSubqueries** () → boolean
|          returns boolean



| *@Override*
| **supportsUnion** () → boolean
|          returns boolean



| *@Override*
| **supportsUnionAll** () → boolean
|          returns boolean



| *@Override*
| **supportsOpenCursorsAcrossCommit** () → boolean
|          returns boolean



| *@Override*
| **supportsOpenCursorsAcrossRollback** () → boolean
|          returns boolean



| *@Override*
| **supportsOpenStatementsAcrossCommit** () → boolean
|          returns boolean



| *@Override*
| **supportsOpenStatementsAcrossRollback** () → boolean
|          returns boolean



| *@Override*
| **getMaxBinaryLiteralLength** () → int
|          returns int



| *@Override*
| **getMaxCharLiteralLength** () → int
|          returns int



| *@Override*
| **getMaxColumnNameLength** () → int
|          returns int



| *@Override*
| **getMaxColumnsInGroupBy** () → int
|          returns int



| *@Override*
| **getMaxColumnsInIndex** () → int
|          returns int



| *@Override*
| **getMaxColumnsInOrderBy** () → int
|          returns int



| *@Override*
| **getMaxColumnsInSelect** () → int
|          returns int



| *@Override*
| **getMaxColumnsInTable** () → int
|          returns int



| *@Override*
| **getMaxConnections** () → int
|          returns int



| *@Override*
| **getMaxCursorNameLength** () → int
|          returns int



| *@Override*
| **getMaxIndexLength** () → int
|          returns int



| *@Override*
| **getMaxSchemaNameLength** () → int
|          returns int



| *@Override*
| **getMaxProcedureNameLength** () → int
|          returns int



| *@Override*
| **getMaxCatalogNameLength** () → int
|          returns int



| *@Override*
| **getMaxRowSize** () → int
|          returns int



| *@Override*
| **doesMaxRowSizeIncludeBlobs** () → boolean
|          returns boolean



| *@Override*
| **getMaxStatementLength** () → int
|          returns int



| *@Override*
| **getMaxStatements** () → int
|          returns int



| *@Override*
| **getMaxTableNameLength** () → int
|          returns int



| *@Override*
| **getMaxTablesInSelect** () → int
|          returns int



| *@Override*
| **getMaxUserNameLength** () → int
|          returns int



| *@Override*
| **getDefaultTransactionIsolation** () → int
|          returns int



| *@Override*
| **supportsTransactions** () → boolean
|          returns boolean



| *@Override*
| **supportsTransactionIsolationLevel** (i) → boolean
|          int i
|          returns boolean



| *@Override*
| **supportsDataDefinitionAndDataManipulationTransactions** () → boolean
|          returns boolean



| *@Override*
| **supportsDataManipulationTransactionsOnly** () → boolean
|          returns boolean



| *@Override*
| **dataDefinitionCausesTransactionCommit** () → boolean
|          returns boolean



| *@Override*
| **dataDefinitionIgnoredInTransactions** () → boolean
|          returns boolean



| *@Override*
| **getProcedures** (string, string1, string2) → :ref:`ResultSet<java.sql.ResultSet>`
|          :ref:`String<java.lang.String>` string
|          :ref:`String<java.lang.String>` string1
|          :ref:`String<java.lang.String>` string2
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| *@Override*
| **getProcedureColumns** (string, string1, string2, string3) → :ref:`ResultSet<java.sql.ResultSet>`
|          :ref:`String<java.lang.String>` string
|          :ref:`String<java.lang.String>` string1
|          :ref:`String<java.lang.String>` string2
|          :ref:`String<java.lang.String>` string3
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| *@Override*
| **getTables** (catalog, schemaPattern, tableNamePattern, types) → :ref:`ResultSet<java.sql.ResultSet>`
|          :ref:`String<java.lang.String>` catalog
|          :ref:`String<java.lang.String>` schemaPattern
|          :ref:`String<java.lang.String>` tableNamePattern
|          :ref:`String<java.lang.String>` types
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| *@Override*
| **getSchemas** () → :ref:`ResultSet<java.sql.ResultSet>`
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| *@Override*
| **getCatalogs** () → :ref:`ResultSet<java.sql.ResultSet>`
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| *@Override*
| **getTableTypes** () → :ref:`ResultSet<java.sql.ResultSet>`
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| *@Override*,| *@SuppressWarnings*
| **getColumns** (catalog, schemaPattern, tableNamePattern, columnNamePattern) → :ref:`ResultSet<java.sql.ResultSet>`
|          :ref:`String<java.lang.String>` catalog
|          :ref:`String<java.lang.String>` schemaPattern
|          :ref:`String<java.lang.String>` tableNamePattern
|          :ref:`String<java.lang.String>` columnNamePattern
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| *@Override*
| **getColumnPrivileges** (string, string1, string2, string3) → :ref:`ResultSet<java.sql.ResultSet>`
|          :ref:`String<java.lang.String>` string
|          :ref:`String<java.lang.String>` string1
|          :ref:`String<java.lang.String>` string2
|          :ref:`String<java.lang.String>` string3
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| *@Override*
| **getTablePrivileges** (string, string1, string2) → :ref:`ResultSet<java.sql.ResultSet>`
|          :ref:`String<java.lang.String>` string
|          :ref:`String<java.lang.String>` string1
|          :ref:`String<java.lang.String>` string2
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| *@Override*
| **getBestRowIdentifier** (string, string1, string2, i, bln) → :ref:`ResultSet<java.sql.ResultSet>`
|          :ref:`String<java.lang.String>` string
|          :ref:`String<java.lang.String>` string1
|          :ref:`String<java.lang.String>` string2
|          int i
|          boolean bln
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| *@Override*
| **getVersionColumns** (string, string1, string2) → :ref:`ResultSet<java.sql.ResultSet>`
|          :ref:`String<java.lang.String>` string
|          :ref:`String<java.lang.String>` string1
|          :ref:`String<java.lang.String>` string2
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| *@Override*
| **getPrimaryKeys** (catalog, schema, table) → :ref:`ResultSet<java.sql.ResultSet>`
|          :ref:`String<java.lang.String>` catalog
|          :ref:`String<java.lang.String>` schema
|          :ref:`String<java.lang.String>` table
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| *@Override*
| **getImportedKeys** (catalog, schema, table) → :ref:`ResultSet<java.sql.ResultSet>`
|          :ref:`String<java.lang.String>` catalog
|          :ref:`String<java.lang.String>` schema
|          :ref:`String<java.lang.String>` table
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| *@Override*
| **getExportedKeys** (catalog, schema, table) → :ref:`ResultSet<java.sql.ResultSet>`
|          :ref:`String<java.lang.String>` catalog
|          :ref:`String<java.lang.String>` schema
|          :ref:`String<java.lang.String>` table
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| *@Override*
| **getCrossReference** (parentCatalog, parentSchema, parentTable, foreignCatalog, foreignSchema, foreignTable) → :ref:`ResultSet<java.sql.ResultSet>`
|          :ref:`String<java.lang.String>` parentCatalog
|          :ref:`String<java.lang.String>` parentSchema
|          :ref:`String<java.lang.String>` parentTable
|          :ref:`String<java.lang.String>` foreignCatalog
|          :ref:`String<java.lang.String>` foreignSchema
|          :ref:`String<java.lang.String>` foreignTable
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| *@Override*
| **getTypeInfo** () → :ref:`ResultSet<java.sql.ResultSet>`
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| *@Override*
| **getIndexInfo** (catalog, schema, table, unique, approximate) → :ref:`ResultSet<java.sql.ResultSet>`
|          :ref:`String<java.lang.String>` catalog
|          :ref:`String<java.lang.String>` schema
|          :ref:`String<java.lang.String>` table
|          boolean unique
|          boolean approximate
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| *@Override*
| **supportsResultSetType** (i) → boolean
|          int i
|          returns boolean



| *@Override*
| **supportsResultSetConcurrency** (i, i1) → boolean
|          int i
|          int i1
|          returns boolean



| *@Override*
| **ownUpdatesAreVisible** (i) → boolean
|          int i
|          returns boolean



| *@Override*
| **ownDeletesAreVisible** (i) → boolean
|          int i
|          returns boolean



| *@Override*
| **ownInsertsAreVisible** (i) → boolean
|          int i
|          returns boolean



| *@Override*
| **othersUpdatesAreVisible** (i) → boolean
|          int i
|          returns boolean



| *@Override*
| **othersDeletesAreVisible** (i) → boolean
|          int i
|          returns boolean



| *@Override*
| **othersInsertsAreVisible** (i) → boolean
|          int i
|          returns boolean



| *@Override*
| **updatesAreDetected** (i) → boolean
|          int i
|          returns boolean



| *@Override*
| **deletesAreDetected** (i) → boolean
|          int i
|          returns boolean



| *@Override*
| **insertsAreDetected** (i) → boolean
|          int i
|          returns boolean



| *@Override*
| **supportsBatchUpdates** () → boolean
|          returns boolean



| *@Override*
| **getUDTs** (string, string1, string2, ints) → :ref:`ResultSet<java.sql.ResultSet>`
|          :ref:`String<java.lang.String>` string
|          :ref:`String<java.lang.String>` string1
|          :ref:`String<java.lang.String>` string2
|          int ints
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| *@Override*
| **getConnection** () → :ref:`Connection<java.sql.Connection>`
|          returns :ref:`Connection<java.sql.Connection>`



| *@Override*
| **supportsSavepoints** () → boolean
|          returns boolean



| *@Override*
| **supportsNamedParameters** () → boolean
|          returns boolean



| *@Override*
| **supportsMultipleOpenResults** () → boolean
|          returns boolean



| *@Override*
| **supportsGetGeneratedKeys** () → boolean
|          returns boolean



| *@Override*
| **getSuperTypes** (string, string1, string2) → :ref:`ResultSet<java.sql.ResultSet>`
|          :ref:`String<java.lang.String>` string
|          :ref:`String<java.lang.String>` string1
|          :ref:`String<java.lang.String>` string2
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| *@Override*
| **getSuperTables** (string, string1, string2) → :ref:`ResultSet<java.sql.ResultSet>`
|          :ref:`String<java.lang.String>` string
|          :ref:`String<java.lang.String>` string1
|          :ref:`String<java.lang.String>` string2
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| *@Override*
| **getAttributes** (string, string1, string2, string3) → :ref:`ResultSet<java.sql.ResultSet>`
|          :ref:`String<java.lang.String>` string
|          :ref:`String<java.lang.String>` string1
|          :ref:`String<java.lang.String>` string2
|          :ref:`String<java.lang.String>` string3
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| *@Override*
| **supportsResultSetHoldability** (i) → boolean
|          int i
|          returns boolean



| *@Override*
| **getResultSetHoldability** () → int
|          returns int



| *@Override*
| **getDatabaseMajorVersion** () → int
|          returns int



| *@Override*
| **getDatabaseMinorVersion** () → int
|          returns int



| *@Override*
| **getJDBCMajorVersion** () → int
|          returns int



| *@Override*
| **getJDBCMinorVersion** () → int
|          returns int



| *@Override*
| **getSQLStateType** () → int
|          returns int



| *@Override*
| **locatorsUpdateCopy** () → boolean
|          returns boolean



| *@Override*
| **supportsStatementPooling** () → boolean
|          returns boolean



| *@Override*
| **getRowIdLifetime** () → :ref:`RowIdLifetime<java.sql.RowIdLifetime>`
|          returns :ref:`RowIdLifetime<java.sql.RowIdLifetime>`



| *@Override*
| **getSchemas** (catalog, schemaPattern) → :ref:`ResultSet<java.sql.ResultSet>`
|          :ref:`String<java.lang.String>` catalog
|          :ref:`String<java.lang.String>` schemaPattern
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| *@Override*
| **supportsStoredFunctionsUsingCallSyntax** () → boolean
|          returns boolean



| *@Override*
| **autoCommitFailureClosesAllResultSets** () → boolean
|          returns boolean



| *@Override*
| **getClientInfoProperties** () → :ref:`ResultSet<java.sql.ResultSet>`
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| *@Override*
| **getFunctions** (string, string1, string2) → :ref:`ResultSet<java.sql.ResultSet>`
|          :ref:`String<java.lang.String>` string
|          :ref:`String<java.lang.String>` string1
|          :ref:`String<java.lang.String>` string2
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| *@Override*
| **getFunctionColumns** (string, string1, string2, string3) → :ref:`ResultSet<java.sql.ResultSet>`
|          :ref:`String<java.lang.String>` string
|          :ref:`String<java.lang.String>` string1
|          :ref:`String<java.lang.String>` string2
|          :ref:`String<java.lang.String>` string3
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| *@Override*
| **getPseudoColumns** (string, string1, string2, string3) → :ref:`ResultSet<java.sql.ResultSet>`
|          :ref:`String<java.lang.String>` string
|          :ref:`String<java.lang.String>` string1
|          :ref:`String<java.lang.String>` string2
|          :ref:`String<java.lang.String>` string3
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| *@Override*
| **generatedKeyAlwaysReturned** () → boolean
|          returns boolean



| *@Override*
| **unwrap** (type) → T
|          :ref:`Class<java.lang.Class>` type
|          returns T



| *@Override*
| **isWrapperFor** (type) → boolean
|          :ref:`Class<java.lang.Class>` type
|          returns boolean




..  _com.sqlsheet.XlsDriver:

=======================================================================
XlsDriver
=======================================================================

*extends:* :ref:`Object<java.lang.Object>` *implements:* :ref:`Driver<java.sql.Driver>` *provides:* :ref:`Driver<com.googlecode.sqlsheet.Driver>`, :ref:`XlsDriver<net.pcal.sqlsheet.XlsDriver>` 

| SqlSheet implementation of java.sql.Driver.

| **XlsDriver** ()


| **getHomeFolder** () → :ref:`File<java.io.File>`
|          returns :ref:`File<java.io.File>`  | the actual $HOME folder of the user



| **resolveHomeUriStr** (uriStr) → :ref:`String<java.lang.String>`
|          :ref:`String<java.lang.String>` uriStr  | uriStr the String representation of an URI containing "~" or "${user.home}"
|          returns :ref:`String<java.lang.String>`  | the expanded URI (resolving "~" and "${user.home}" to the actual $HOME folder



| *@Override*
| **getPropertyInfo** (url, info) → :ref:`DriverPropertyInfo<java.sql.DriverPropertyInfo>`
|          :ref:`String<java.lang.String>` url
|          :ref:`Properties<java.util.Properties>` info
|          returns :ref:`DriverPropertyInfo<java.sql.DriverPropertyInfo>`



| *@SuppressWarnings*
| **connect** (url, info) → :ref:`Connection<java.sql.Connection>`
| Attempts to make a database connection to the given URL. The driver should return "null" if it realizes it is the wrong kind of driver to connect to the given URL. This will be common, as when the JDBC driver manager is asked to connect to a given URL it passes the URL to each loaded driver in turn. 
| The driver should throw an ``SQLException`` if it is the right driver to connect to the given URL but has trouble connecting to the database. 
| The `url` should point to a file or a resource in the class path. 
| Valid samples are: ``ul`` ``li``jdbc:xls:file://${user.home}/dataSource.xlsx ``li``jdbc:xls:file://~/dataSource.xlsx ``li``jdbc:xls:resource:/com/sqlsheet/dataSource.xlsx ``/ul`` 
| The `Properties` argument can be used to pass arbitrary string tag/value pairs as connection arguments. Normally at least "user" and "password" properties should be included in the `Properties` object. 
| ``B``Note:``/B`` If a property is specified as part of the `url` and is also specified in the `Properties` object, it is implementation-defined as to which value will take precedence. For maximum portability, an application should only specify a property once.
|          :ref:`String<java.lang.String>` url  | url the URL of the database to which to connect
|          :ref:`Properties<java.util.Properties>` info  | info a list of arbitrary string tag/value pairs as connection arguments. Normally at least a "user" and "password" property should be included.
|          returns :ref:`Connection<java.sql.Connection>`  | a ``Connection`` object that represents a connection to the URL



| **acceptsURL** (url) → boolean
|          :ref:`String<java.lang.String>` url
|          returns boolean



| **jdbcCompliant** () → boolean
|          returns boolean



| **getMajorVersion** () → int
|          returns int



| **getMinorVersion** () → int
|          returns int



| **getParentLogger** () → :ref:`Logger<java.util.logging.Logger>`
|          returns :ref:`Logger<java.util.logging.Logger>`




..  _com.sqlsheet.XlsPreparedStatement:

=======================================================================
XlsPreparedStatement
=======================================================================

*extends:* :ref:`XlsStatement<com.sqlsheet.XlsStatement>` *implements:* :ref:`PreparedStatement<java.sql.PreparedStatement>` 

| SqlSheet implementation of java.sql.PreparedStatement.

| **XlsPreparedStatement** (conn, sql)
|          :ref:`XlsConnection<com.sqlsheet.XlsConnection>` conn
|          :ref:`String<java.lang.String>` sql


| **addBatch** ()


| **clearParameters** ()


| **execute** () → boolean
|          returns boolean



| **executeUpdate** () → int
|          returns int



| **executeQuery** () → :ref:`ResultSet<java.sql.ResultSet>`
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| **setBigDecimal** (arg0, arg1)
|          int arg0
|          :ref:`BigDecimal<java.math.BigDecimal>` arg1


| **setBoolean** (arg0, arg1)
|          int arg0
|          boolean arg1


| **setByte** (arg0, arg1)
|          int arg0
|          byte arg1


| **setDate** (arg0, arg1)
|          int arg0
|          :ref:`Date<java.sql.Date>` arg1


| **setDouble** (arg0, arg1)
|          int arg0
|          double arg1


| **setFloat** (arg0, arg1)
|          int arg0
|          float arg1


| **setInt** (arg0, arg1)
|          int arg0
|          int arg1


| **setLong** (arg0, arg1)
|          int arg0
|          long arg1


| **setNull** (arg0, arg1)
|          int arg0
|          int arg1


| **setNull** (arg0, arg1, arg2)
|          int arg0
|          int arg1
|          :ref:`String<java.lang.String>` arg2


| **setShort** (arg0, arg1)
|          int arg0
|          short arg1


| **setString** (arg0, arg1)
|          int arg0
|          :ref:`String<java.lang.String>` arg1


| **setTime** (arg0, arg1)
|          int arg0
|          :ref:`Time<java.sql.Time>` arg1


| **setTimestamp** (arg0, arg1)
|          int arg0
|          :ref:`Timestamp<java.sql.Timestamp>` arg1


| **setCharacterStream** (parameterIndex, reader, length)
|          int parameterIndex
|          :ref:`Reader<java.io.Reader>` reader
|          int length


| **setObject** (arg0, arg1)
|          int arg0
|          :ref:`Object<java.lang.Object>` arg1


| **setTimestamp** (arg0, arg1, arg2)
|          int arg0
|          :ref:`Timestamp<java.sql.Timestamp>` arg1
|          :ref:`Calendar<java.util.Calendar>` arg2


| **setTime** (arg0, arg1, arg2)
|          int arg0
|          :ref:`Time<java.sql.Time>` arg1
|          :ref:`Calendar<java.util.Calendar>` arg2


| **setArray** (arg0, arg1)
|          int arg0
|          :ref:`Array<java.sql.Array>` arg1


| **setAsciiStream** (arg0, arg1, arg2)
|          int arg0
|          :ref:`InputStream<java.io.InputStream>` arg1
|          int arg2


| **setDate** (arg0, arg1, arg2)
|          int arg0
|          :ref:`Date<java.sql.Date>` arg1
|          :ref:`Calendar<java.util.Calendar>` arg2


| **setBytes** (arg0, arg1)
|          int arg0
|          byte arg1


| **setClob** (arg0, arg1)
|          int arg0
|          :ref:`Clob<java.sql.Clob>` arg1


| **setObject** (arg0, arg1, arg2)
|          int arg0
|          :ref:`Object<java.lang.Object>` arg1
|          int arg2


| **setObject** (arg0, arg1, arg2, arg3)
|          int arg0
|          :ref:`Object<java.lang.Object>` arg1
|          int arg2
|          int arg3


| **setAsciiStream** (parameterIndex, x, length)
|          int parameterIndex
|          :ref:`InputStream<java.io.InputStream>` x
|          long length


| **setBinaryStream** (parameterIndex, x, length)
|          int parameterIndex
|          :ref:`InputStream<java.io.InputStream>` x
|          long length


| **setCharacterStream** (parameterIndex, reader, length)
|          int parameterIndex
|          :ref:`Reader<java.io.Reader>` reader
|          long length


| **setAsciiStream** (parameterIndex, x)
|          int parameterIndex
|          :ref:`InputStream<java.io.InputStream>` x


| **setBinaryStream** (parameterIndex, x)
|          int parameterIndex
|          :ref:`InputStream<java.io.InputStream>` x


| **setCharacterStream** (parameterIndex, reader)
|          int parameterIndex
|          :ref:`Reader<java.io.Reader>` reader


| **setNCharacterStream** (parameterIndex, value)
|          int parameterIndex
|          :ref:`Reader<java.io.Reader>` value


| **setClob** (parameterIndex, reader)
|          int parameterIndex
|          :ref:`Reader<java.io.Reader>` reader


| **setBlob** (parameterIndex, inputStream)
|          int parameterIndex
|          :ref:`InputStream<java.io.InputStream>` inputStream


| **setNClob** (parameterIndex, reader)
|          int parameterIndex
|          :ref:`Reader<java.io.Reader>` reader


| **setRef** (arg0, arg1)
|          int arg0
|          :ref:`Ref<java.sql.Ref>` arg1


| **getMetaData** () → :ref:`ResultSetMetaData<java.sql.ResultSetMetaData>`
|          returns :ref:`ResultSetMetaData<java.sql.ResultSetMetaData>`



| **getParameterMetaData** () → :ref:`ParameterMetaData<java.sql.ParameterMetaData>`
|          returns :ref:`ParameterMetaData<java.sql.ParameterMetaData>`



| **getResultSet** () → :ref:`ResultSet<java.sql.ResultSet>`
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| **setRowId** (parameterIndex, x)
|          int parameterIndex
|          :ref:`RowId<java.sql.RowId>` x


| **setNString** (parameterIndex, value)
|          int parameterIndex
|          :ref:`String<java.lang.String>` value


| **setNCharacterStream** (parameterIndex, value, length)
|          int parameterIndex
|          :ref:`Reader<java.io.Reader>` value
|          long length


| **setNClob** (parameterIndex, value)
|          int parameterIndex
|          :ref:`NClob<java.sql.NClob>` value


| **setClob** (parameterIndex, reader, length)
|          int parameterIndex
|          :ref:`Reader<java.io.Reader>` reader
|          long length


| **setBlob** (parameterIndex, inputStream, length)
|          int parameterIndex
|          :ref:`InputStream<java.io.InputStream>` inputStream
|          long length


| **setNClob** (parameterIndex, reader, length)
|          int parameterIndex
|          :ref:`Reader<java.io.Reader>` reader
|          long length


| **setSQLXML** (parameterIndex, xmlObject)
|          int parameterIndex
|          :ref:`SQLXML<java.sql.SQLXML>` xmlObject


| **setBinaryStream** (arg0, arg1, arg2)
|          int arg0
|          :ref:`InputStream<java.io.InputStream>` arg1
|          int arg2


| **setBlob** (arg0, arg1)
|          int arg0
|          :ref:`Blob<java.sql.Blob>` arg1


| **setURL** (arg0, arg1)
|          int arg0
|          :ref:`URL<java.net.URL>` arg1


| **setUnicodeStream** (arg0, arg1, arg2)
|          int arg0
|          :ref:`InputStream<java.io.InputStream>` arg1
|          int arg2


| **closeOnCompletion** ()


| **isCloseOnCompletion** () → boolean
|          returns boolean




..  _com.sqlsheet.XlsResultSet:

=======================================================================
XlsResultSet
=======================================================================

*extends:* :ref:`Object<java.lang.Object>` *implements:* :ref:`ResultSet<java.sql.ResultSet>` 

| SqlSheet implementation of java.sql.ResultSet.

| **XlsResultSet** (wb, s, firstSheetRowOffset, firstSheetColOffset)
|          Workbook wb
|          Sheet s
|          int firstSheetRowOffset
|          int firstSheetColOffset


| **wrapped** (t) → :ref:`SQLException<java.sql.SQLException>`
|          :ref:`Throwable<java.lang.Throwable>` t
|          returns :ref:`SQLException<java.sql.SQLException>`



| *@Override*
| **getMetaData** () → :ref:`ResultSetMetaData<java.sql.ResultSetMetaData>`
|          returns :ref:`ResultSetMetaData<java.sql.ResultSetMetaData>`



| *@Override*
| **getBoolean** (columnIndex) → boolean
|          int columnIndex
|          returns boolean



| *@Override*
| **getBoolean** (columnLabel) → boolean
|          :ref:`String<java.lang.String>` columnLabel
|          returns boolean



| *@Override*
| **getDouble** (columnIndex) → double
|          int columnIndex
|          returns double



| *@Override*
| **getDouble** (columnLabel) → double
|          :ref:`String<java.lang.String>` columnLabel
|          returns double



| *@Override*
| **getByte** (columnIndex) → byte
|          int columnIndex
|          returns byte



| *@Override*
| **getByte** (columnLabel) → byte
|          :ref:`String<java.lang.String>` columnLabel
|          returns byte



| *@Override*
| **getFloat** (columnIndex) → float
|          int columnIndex
|          returns float



| *@Override*
| **getFloat** (columnLabel) → float
|          :ref:`String<java.lang.String>` columnLabel
|          returns float



| *@Override*
| **getInt** (columnIndex) → int
|          int columnIndex
|          returns int



| *@Override*
| **getInt** (columnLabel) → int
|          :ref:`String<java.lang.String>` columnLabel
|          returns int



| *@Override*
| **getLong** (columnIndex) → long
|          int columnIndex
|          returns long



| *@Override*
| **getLong** (columnLabel) → long
|          :ref:`String<java.lang.String>` columnLabel
|          returns long



| *@Override*
| **getObject** (columnIndex) → :ref:`Object<java.lang.Object>`
|          int columnIndex
|          returns :ref:`Object<java.lang.Object>`



| *@Override*
| **getObject** (columnLabel) → :ref:`Object<java.lang.Object>`
|          :ref:`String<java.lang.String>` columnLabel
|          returns :ref:`Object<java.lang.Object>`



| *@Override*
| **getObject** (columnIndex, type) → T
|          int columnIndex
|          :ref:`Class<java.lang.Class>` type
|          returns T



| *@Override*
| **getObject** (columnLabel, type) → T
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`Class<java.lang.Class>` type
|          returns T



| *@Override*
| **getTimestamp** (columnIndex) → :ref:`Timestamp<java.sql.Timestamp>`
|          int columnIndex
|          returns :ref:`Timestamp<java.sql.Timestamp>`



| *@Override*
| **getTimestamp** (columnLabel) → :ref:`Timestamp<java.sql.Timestamp>`
|          :ref:`String<java.lang.String>` columnLabel
|          returns :ref:`Timestamp<java.sql.Timestamp>`



| *@Override*
| **getTimestamp** (columnIndex, cal) → :ref:`Timestamp<java.sql.Timestamp>`
|          int columnIndex
|          :ref:`Calendar<java.util.Calendar>` cal
|          returns :ref:`Timestamp<java.sql.Timestamp>`



| *@Override*
| **getTimestamp** (jdbcColumn, cal) → :ref:`Timestamp<java.sql.Timestamp>`
|          :ref:`String<java.lang.String>` jdbcColumn
|          :ref:`Calendar<java.util.Calendar>` cal
|          returns :ref:`Timestamp<java.sql.Timestamp>`



| *@Override*
| **getShort** (columnIndex) → short
|          int columnIndex
|          returns short



| *@Override*
| **getShort** (columnLabel) → short
|          :ref:`String<java.lang.String>` columnLabel
|          returns short



| *@Override*
| **getString** (columnIndex) → :ref:`String<java.lang.String>`
|          int columnIndex
|          returns :ref:`String<java.lang.String>`



| **getString** (columnLabel) → :ref:`String<java.lang.String>`
|          :ref:`String<java.lang.String>` columnLabel
|          returns :ref:`String<java.lang.String>`



| **updateBoolean** (columnIndex, x)
|          int columnIndex
|          boolean x


| **updateBoolean** (jdbcColumn, x)
|          :ref:`String<java.lang.String>` jdbcColumn
|          boolean x


| **updateByte** (columnIndex, x)
|          int columnIndex
|          byte x


| **updateByte** (jdbcColumn, x)
|          :ref:`String<java.lang.String>` jdbcColumn
|          byte x


| **updateDouble** (columnIndex, x)
|          int columnIndex
|          double x


| **updateDouble** (jdbcColumn, x)
|          :ref:`String<java.lang.String>` jdbcColumn
|          double x


| **updateFloat** (columnIndex, x)
|          int columnIndex
|          float x


| **updateFloat** (jdbcColumn, x)
|          :ref:`String<java.lang.String>` jdbcColumn
|          float x


| **updateInt** (columnIndex, x)
|          int columnIndex
|          int x


| **updateInt** (jdbcColumn, x)
|          :ref:`String<java.lang.String>` jdbcColumn
|          int x


| **updateLong** (columnIndex, x)
|          int columnIndex
|          long x


| **updateLong** (jdbcColumn, x)
|          :ref:`String<java.lang.String>` jdbcColumn
|          long x


| **updateNull** (columnIndex)
|          int columnIndex


| **updateNull** (jdbcColumn)
|          :ref:`String<java.lang.String>` jdbcColumn


| **updateObject** (columnIndex, x)
|          int columnIndex
|          :ref:`Object<java.lang.Object>` x


| **updateObject** (jdbcColumn, x)
|          :ref:`String<java.lang.String>` jdbcColumn
|          :ref:`Object<java.lang.Object>` x


| **updateShort** (columnIndex, x)
|          int columnIndex
|          short x


| **updateShort** (jdbcColumn, x)
|          :ref:`String<java.lang.String>` jdbcColumn
|          short x


| **updateString** (columnIndex, x)
|          int columnIndex
|          :ref:`String<java.lang.String>` x


| **updateString** (jdbcColumn, x)
|          :ref:`String<java.lang.String>` jdbcColumn
|          :ref:`String<java.lang.String>` x


| **absolute** (row) → boolean
|          int row
|          returns boolean



| **afterLast** ()


| **beforeFirst** ()


| **first** () → boolean
|          returns boolean



| **getFetchDirection** () → int
|          returns int



| **setFetchDirection** (direction)
|          int direction


| **getFetchSize** () → int
|          returns int



| **setFetchSize** (rows)
|          int rows


| **getRow** () → int
|          returns int



| **getType** () → int
|          returns int



| **isAfterLast** () → boolean
|          returns boolean



| **isBeforeFirst** () → boolean
|          returns boolean



| **isFirst** () → boolean
|          returns boolean



| **isLast** () → boolean
|          returns boolean



| **last** () → boolean
|          returns boolean



| **next** () → boolean
|          returns boolean



| **previous** () → boolean
|          returns boolean



| **moveToInsertRow** ()


| **insertRow** ()



                Protected because used also in the resultset metadata to scan the column type
                
                
                |          int columnIndex  | columnIndex The index of the column, starting at 1.

                |          returns Cell  | the Cell


            | **cancelRowUpdates** ()


| **clearWarnings** ()


| *@Override*
| **close** ()


| **deleteRow** ()


| *@Override*
| **findColumn** (columnLabel) → int
|          :ref:`String<java.lang.String>` columnLabel
|          returns int



| **getArray** (i) → :ref:`Array<java.sql.Array>`
|          int i
|          returns :ref:`Array<java.sql.Array>`



| **getArray** (colName) → :ref:`Array<java.sql.Array>`
|          :ref:`String<java.lang.String>` colName
|          returns :ref:`Array<java.sql.Array>`



| *@Override*
| **getAsciiStream** (columnIndex) → :ref:`InputStream<java.io.InputStream>`
|          int columnIndex
|          returns :ref:`InputStream<java.io.InputStream>`



| **getAsciiStream** (jdbcColumn) → :ref:`InputStream<java.io.InputStream>`
|          :ref:`String<java.lang.String>` jdbcColumn
|          returns :ref:`InputStream<java.io.InputStream>`



| *@Override*
| **getBigDecimal** (columnIndex) → :ref:`BigDecimal<java.math.BigDecimal>`
|          int columnIndex
|          returns :ref:`BigDecimal<java.math.BigDecimal>`



| *@Override*
| **getBigDecimal** (columnLabel) → :ref:`BigDecimal<java.math.BigDecimal>`
|          :ref:`String<java.lang.String>` columnLabel
|          returns :ref:`BigDecimal<java.math.BigDecimal>`



| *@Override*
| **getBigDecimal** (columnIndex, scale) → :ref:`BigDecimal<java.math.BigDecimal>`
|          int columnIndex
|          int scale
|          returns :ref:`BigDecimal<java.math.BigDecimal>`



| *@Override*
| **getBigDecimal** (columnLabel, scale) → :ref:`BigDecimal<java.math.BigDecimal>`
|          :ref:`String<java.lang.String>` columnLabel
|          int scale
|          returns :ref:`BigDecimal<java.math.BigDecimal>`



| *@Override*
| **getBinaryStream** (columnIndex) → :ref:`InputStream<java.io.InputStream>`
|          int columnIndex
|          returns :ref:`InputStream<java.io.InputStream>`



| **getBinaryStream** (jdbcColumn) → :ref:`InputStream<java.io.InputStream>`
|          :ref:`String<java.lang.String>` jdbcColumn
|          returns :ref:`InputStream<java.io.InputStream>`



| **getBlob** (columnIndex) → :ref:`Blob<java.sql.Blob>`
|          int columnIndex
|          returns :ref:`Blob<java.sql.Blob>`



| **getBlob** (colName) → :ref:`Blob<java.sql.Blob>`
|          :ref:`String<java.lang.String>` colName
|          returns :ref:`Blob<java.sql.Blob>`



| *@Override*
| **getBytes** (columnIndex) → byte
|          int columnIndex
|          returns byte



| **getBytes** (jdbcColumn) → byte
|          :ref:`String<java.lang.String>` jdbcColumn
|          returns byte



| *@Override*
| **getCharacterStream** (columnIndex) → :ref:`Reader<java.io.Reader>`
|          int columnIndex
|          returns :ref:`Reader<java.io.Reader>`



| **getCharacterStream** (jdbcColumn) → :ref:`Reader<java.io.Reader>`
|          :ref:`String<java.lang.String>` jdbcColumn
|          returns :ref:`Reader<java.io.Reader>`



| **getClob** (i) → :ref:`Clob<java.sql.Clob>`
|          int i
|          returns :ref:`Clob<java.sql.Clob>`



| **getClob** (colName) → :ref:`Clob<java.sql.Clob>`
|          :ref:`String<java.lang.String>` colName
|          returns :ref:`Clob<java.sql.Clob>`



| **getConcurrency** () → int
|          returns int



| **getCursorName** () → :ref:`String<java.lang.String>`
|          returns :ref:`String<java.lang.String>`



| *@Override*
| **getDate** (columnIndex) → :ref:`Date<java.sql.Date>`
|          int columnIndex
|          returns :ref:`Date<java.sql.Date>`



| *@Override*
| **getDate** (columnLabel) → :ref:`Date<java.sql.Date>`
|          :ref:`String<java.lang.String>` columnLabel
|          returns :ref:`Date<java.sql.Date>`



| *@Override*
| **getDate** (columnIndex, cal) → :ref:`Date<java.sql.Date>`
|          int columnIndex
|          :ref:`Calendar<java.util.Calendar>` cal
|          returns :ref:`Date<java.sql.Date>`



| *@Override*
| **getDate** (columnLabel, cal) → :ref:`Date<java.sql.Date>`
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`Calendar<java.util.Calendar>` cal
|          returns :ref:`Date<java.sql.Date>`



| *@Override*
| **getObject** (columnIndex, map) → :ref:`Object<java.lang.Object>`
|          int columnIndex
|          :ref:`Map<java.util.Map>` map
|          returns :ref:`Object<java.lang.Object>`



| *@Override*
| **getObject** (colName, map) → :ref:`Object<java.lang.Object>`
|          :ref:`String<java.lang.String>` colName
|          :ref:`Map<java.util.Map>` map
|          returns :ref:`Object<java.lang.Object>`



| **getRef** (columnIndex) → :ref:`Ref<java.sql.Ref>`
|          int columnIndex
|          returns :ref:`Ref<java.sql.Ref>`



| **getRef** (colName) → :ref:`Ref<java.sql.Ref>`
|          :ref:`String<java.lang.String>` colName
|          returns :ref:`Ref<java.sql.Ref>`



| **getStatement** () → :ref:`Statement<java.sql.Statement>`
|          returns :ref:`Statement<java.sql.Statement>`



| **getTime** (columnIndex) → :ref:`Time<java.sql.Time>`
|          int columnIndex
|          returns :ref:`Time<java.sql.Time>`



| **getTime** (jdbcColumn) → :ref:`Time<java.sql.Time>`
|          :ref:`String<java.lang.String>` jdbcColumn
|          returns :ref:`Time<java.sql.Time>`



| **getTime** (columnIndex, cal) → :ref:`Time<java.sql.Time>`
|          int columnIndex
|          :ref:`Calendar<java.util.Calendar>` cal
|          returns :ref:`Time<java.sql.Time>`



| **getTime** (jdbcColumn, cal) → :ref:`Time<java.sql.Time>`
|          :ref:`String<java.lang.String>` jdbcColumn
|          :ref:`Calendar<java.util.Calendar>` cal
|          returns :ref:`Time<java.sql.Time>`



| **getURL** (columnIndex) → :ref:`URL<java.net.URL>`
|          int columnIndex
|          returns :ref:`URL<java.net.URL>`



| **getURL** (jdbcColumn) → :ref:`URL<java.net.URL>`
|          :ref:`String<java.lang.String>` jdbcColumn
|          returns :ref:`URL<java.net.URL>`



| **getUnicodeStream** (columnIndex) → :ref:`InputStream<java.io.InputStream>`
|          int columnIndex
|          returns :ref:`InputStream<java.io.InputStream>`



| **getUnicodeStream** (jdbcColumn) → :ref:`InputStream<java.io.InputStream>`
|          :ref:`String<java.lang.String>` jdbcColumn
|          returns :ref:`InputStream<java.io.InputStream>`



| **getWarnings** () → :ref:`SQLWarning<java.sql.SQLWarning>`
|          returns :ref:`SQLWarning<java.sql.SQLWarning>`



| **moveToCurrentRow** ()


| **refreshRow** ()


| **relative** (rows) → boolean
|          int rows
|          returns boolean



| **rowDeleted** () → boolean
|          returns boolean



| **rowInserted** () → boolean
|          returns boolean



| **rowUpdated** () → boolean
|          returns boolean



| **updateArray** (columnIndex, x)
|          int columnIndex
|          :ref:`Array<java.sql.Array>` x


| **updateArray** (jdbcColumn, x)
|          :ref:`String<java.lang.String>` jdbcColumn
|          :ref:`Array<java.sql.Array>` x


| **getRowId** (columnIndex) → :ref:`RowId<java.sql.RowId>`
|          int columnIndex
|          returns :ref:`RowId<java.sql.RowId>`



| **getRowId** (columnLabel) → :ref:`RowId<java.sql.RowId>`
|          :ref:`String<java.lang.String>` columnLabel
|          returns :ref:`RowId<java.sql.RowId>`



| **updateRowId** (columnIndex, x)
|          int columnIndex
|          :ref:`RowId<java.sql.RowId>` x


| **updateRowId** (columnLabel, x)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`RowId<java.sql.RowId>` x


| **getHoldability** () → int
|          returns int



| *@Override*
| **isClosed** () → boolean
|          returns boolean



| **updateNString** (columnIndex, nString)
|          int columnIndex
|          :ref:`String<java.lang.String>` nString


| **updateNString** (columnLabel, nString)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`String<java.lang.String>` nString


| **updateNClob** (columnIndex, nClob)
|          int columnIndex
|          :ref:`NClob<java.sql.NClob>` nClob


| **updateNClob** (columnLabel, nClob)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`NClob<java.sql.NClob>` nClob


| **getNClob** (columnIndex) → :ref:`NClob<java.sql.NClob>`
|          int columnIndex
|          returns :ref:`NClob<java.sql.NClob>`



| **getNClob** (columnLabel) → :ref:`NClob<java.sql.NClob>`
|          :ref:`String<java.lang.String>` columnLabel
|          returns :ref:`NClob<java.sql.NClob>`



| **getSQLXML** (columnIndex) → :ref:`SQLXML<java.sql.SQLXML>`
|          int columnIndex
|          returns :ref:`SQLXML<java.sql.SQLXML>`



| **getSQLXML** (columnLabel) → :ref:`SQLXML<java.sql.SQLXML>`
|          :ref:`String<java.lang.String>` columnLabel
|          returns :ref:`SQLXML<java.sql.SQLXML>`



| **updateSQLXML** (columnIndex, xmlObject)
|          int columnIndex
|          :ref:`SQLXML<java.sql.SQLXML>` xmlObject


| **updateSQLXML** (columnLabel, xmlObject)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`SQLXML<java.sql.SQLXML>` xmlObject


| **getNString** (columnIndex) → :ref:`String<java.lang.String>`
|          int columnIndex
|          returns :ref:`String<java.lang.String>`



| **getNString** (columnLabel) → :ref:`String<java.lang.String>`
|          :ref:`String<java.lang.String>` columnLabel
|          returns :ref:`String<java.lang.String>`



| **getNCharacterStream** (columnIndex) → :ref:`Reader<java.io.Reader>`
|          int columnIndex
|          returns :ref:`Reader<java.io.Reader>`



| **getNCharacterStream** (columnLabel) → :ref:`Reader<java.io.Reader>`
|          :ref:`String<java.lang.String>` columnLabel
|          returns :ref:`Reader<java.io.Reader>`



| **updateNCharacterStream** (columnIndex, x, length)
|          int columnIndex
|          :ref:`Reader<java.io.Reader>` x
|          long length


| **updateNCharacterStream** (columnLabel, reader, length)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`Reader<java.io.Reader>` reader
|          long length


| **updateAsciiStream** (columnIndex, x, length)
|          int columnIndex
|          :ref:`InputStream<java.io.InputStream>` x
|          long length


| **updateBinaryStream** (columnIndex, x, length)
|          int columnIndex
|          :ref:`InputStream<java.io.InputStream>` x
|          long length


| **updateCharacterStream** (columnIndex, x, length)
|          int columnIndex
|          :ref:`Reader<java.io.Reader>` x
|          long length


| **updateAsciiStream** (columnLabel, x, length)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`InputStream<java.io.InputStream>` x
|          long length


| **updateBinaryStream** (columnLabel, x, length)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`InputStream<java.io.InputStream>` x
|          long length


| **updateCharacterStream** (columnLabel, reader, length)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`Reader<java.io.Reader>` reader
|          long length


| **updateBlob** (columnIndex, inputStream, length)
|          int columnIndex
|          :ref:`InputStream<java.io.InputStream>` inputStream
|          long length


| **updateBlob** (columnLabel, inputStream, length)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`InputStream<java.io.InputStream>` inputStream
|          long length


| **updateClob** (columnIndex, reader, length)
|          int columnIndex
|          :ref:`Reader<java.io.Reader>` reader
|          long length


| **updateClob** (columnLabel, reader, length)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`Reader<java.io.Reader>` reader
|          long length


| **updateNClob** (columnIndex, reader, length)
|          int columnIndex
|          :ref:`Reader<java.io.Reader>` reader
|          long length


| **updateNClob** (columnLabel, reader, length)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`Reader<java.io.Reader>` reader
|          long length


| **updateNCharacterStream** (columnIndex, x)
|          int columnIndex
|          :ref:`Reader<java.io.Reader>` x


| **updateNCharacterStream** (columnLabel, reader)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`Reader<java.io.Reader>` reader


| **updateAsciiStream** (columnIndex, x)
|          int columnIndex
|          :ref:`InputStream<java.io.InputStream>` x


| **updateBinaryStream** (columnIndex, x)
|          int columnIndex
|          :ref:`InputStream<java.io.InputStream>` x


| **updateCharacterStream** (columnIndex, x)
|          int columnIndex
|          :ref:`Reader<java.io.Reader>` x


| **updateAsciiStream** (columnLabel, x)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`InputStream<java.io.InputStream>` x


| **updateBinaryStream** (columnLabel, x)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`InputStream<java.io.InputStream>` x


| **updateCharacterStream** (columnLabel, reader)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`Reader<java.io.Reader>` reader


| **updateBlob** (columnIndex, inputStream)
|          int columnIndex
|          :ref:`InputStream<java.io.InputStream>` inputStream


| **updateBlob** (columnLabel, inputStream)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`InputStream<java.io.InputStream>` inputStream


| **updateClob** (columnIndex, reader)
|          int columnIndex
|          :ref:`Reader<java.io.Reader>` reader


| **updateClob** (columnLabel, reader)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`Reader<java.io.Reader>` reader


| **updateNClob** (columnIndex, reader)
|          int columnIndex
|          :ref:`Reader<java.io.Reader>` reader


| **updateNClob** (columnLabel, reader)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`Reader<java.io.Reader>` reader


| **updateAsciiStream** (jdbcColumn, x, length)
|          int jdbcColumn
|          :ref:`InputStream<java.io.InputStream>` x
|          int length


| **updateAsciiStream** (jdbcColumn, x, length)
|          :ref:`String<java.lang.String>` jdbcColumn
|          :ref:`InputStream<java.io.InputStream>` x
|          int length


| **updateBigDecimal** (jdbcColumn, x)
|          int jdbcColumn
|          :ref:`BigDecimal<java.math.BigDecimal>` x


| **updateBigDecimal** (jdbcColumn, x)
|          :ref:`String<java.lang.String>` jdbcColumn
|          :ref:`BigDecimal<java.math.BigDecimal>` x


| **updateBinaryStream** (jdbcColumn, x, length)
|          int jdbcColumn
|          :ref:`InputStream<java.io.InputStream>` x
|          int length


| **updateBinaryStream** (jdbcColumn, x, length)
|          :ref:`String<java.lang.String>` jdbcColumn
|          :ref:`InputStream<java.io.InputStream>` x
|          int length


| **updateBlob** (jdbcColumn, x)
|          int jdbcColumn
|          :ref:`Blob<java.sql.Blob>` x


| **updateBlob** (jdbcColumn, x)
|          :ref:`String<java.lang.String>` jdbcColumn
|          :ref:`Blob<java.sql.Blob>` x


| **updateBytes** (jdbcColumn, x)
|          int jdbcColumn
|          byte x


| **updateBytes** (jdbcColumn, x)
|          :ref:`String<java.lang.String>` jdbcColumn
|          byte x


| **updateCharacterStream** (jdbcColumn, x, length)
|          int jdbcColumn
|          :ref:`Reader<java.io.Reader>` x
|          int length


| **updateCharacterStream** (jdbcColumn, reader, length)
|          :ref:`String<java.lang.String>` jdbcColumn
|          :ref:`Reader<java.io.Reader>` reader
|          int length


| **updateClob** (jdbcColumn, x)
|          int jdbcColumn
|          :ref:`Clob<java.sql.Clob>` x


| **updateClob** (jdbcColumn, x)
|          :ref:`String<java.lang.String>` jdbcColumn
|          :ref:`Clob<java.sql.Clob>` x


| **updateDate** (jdbcColumn, x)
|          int jdbcColumn
|          :ref:`Date<java.sql.Date>` x


| **updateDate** (jdbcColumn, x)
|          :ref:`String<java.lang.String>` jdbcColumn
|          :ref:`Date<java.sql.Date>` x


| **updateObject** (jdbcColumn, x, scale)
|          int jdbcColumn
|          :ref:`Object<java.lang.Object>` x
|          int scale


| **updateObject** (jdbcColumn, x, scale)
|          :ref:`String<java.lang.String>` jdbcColumn
|          :ref:`Object<java.lang.Object>` x
|          int scale


| **updateRef** (jdbcColumn, x)
|          int jdbcColumn
|          :ref:`Ref<java.sql.Ref>` x


| **updateRef** (jdbcColumn, x)
|          :ref:`String<java.lang.String>` jdbcColumn
|          :ref:`Ref<java.sql.Ref>` x


| **updateRow** ()


| **updateTime** (jdbcColumn, x)
|          int jdbcColumn
|          :ref:`Time<java.sql.Time>` x


| **updateTime** (jdbcColumn, x)
|          :ref:`String<java.lang.String>` jdbcColumn
|          :ref:`Time<java.sql.Time>` x


| **updateTimestamp** (jdbcColumn, x)
|          int jdbcColumn
|          :ref:`Timestamp<java.sql.Timestamp>` x


| **updateTimestamp** (jdbcColumn, x)
|          :ref:`String<java.lang.String>` jdbcColumn
|          :ref:`Timestamp<java.sql.Timestamp>` x


| *@Override*
| **wasNull** () → boolean
|          returns boolean



| **unwrap** (iface) → T
|          :ref:`Class<java.lang.Class>` iface
|          returns T



| **isWrapperFor** (iface) → boolean
|          :ref:`Class<java.lang.Class>` iface
|          returns boolean




..  _com.sqlsheet.XlsResultSetMetaData:

=======================================================================
XlsResultSetMetaData
=======================================================================

*extends:* :ref:`Object<java.lang.Object>` *implements:* :ref:`ResultSetMetaData<java.sql.ResultSetMetaData>` 

| SqlSheet implementation of java.sql.ResultSetMetaData.

| **XlsResultSetMetaData** (sheet, resultset, firstSheetRowOffset, firstSheetColOffset)
|          Sheet sheet
|          :ref:`XlsResultSet<com.sqlsheet.XlsResultSet>` resultset
|          int firstSheetRowOffset
|          int firstSheetColOffset


| **getColumnCount** () → int
|          returns int



| **getColumnLabel** (jdbcCol) → :ref:`String<java.lang.String>`
|          int jdbcCol
|          returns :ref:`String<java.lang.String>`



| **getColumnName** (jdbcCol) → :ref:`String<java.lang.String>`
|          int jdbcCol
|          returns :ref:`String<java.lang.String>`



| **getCatalogName** (arg0) → :ref:`String<java.lang.String>`
|          int arg0
|          returns :ref:`String<java.lang.String>`



| **getColumnClassName** (jdbcColumn) → :ref:`String<java.lang.String>`
|          int jdbcColumn
|          returns :ref:`String<java.lang.String>`



| **getColumnDisplaySize** (arg0) → int
|          int arg0
|          returns int



| **getColumnType** (jdbcColumn) → int
|          int jdbcColumn
|          returns int



| **getColumnTypeName** (jdbcColumn) → :ref:`String<java.lang.String>`
|          int jdbcColumn
|          returns :ref:`String<java.lang.String>`



| **getPrecision** (arg0) → int
|          int arg0
|          returns int



| **getScale** (arg0) → int
|          int arg0
|          returns int



| **getSchemaName** (arg0) → :ref:`String<java.lang.String>`
|          int arg0
|          returns :ref:`String<java.lang.String>`



| **getTableName** (arg0) → :ref:`String<java.lang.String>`
|          int arg0
|          returns :ref:`String<java.lang.String>`



| **isAutoIncrement** (arg0) → boolean
|          int arg0
|          returns boolean



| **isCaseSensitive** (arg0) → boolean
|          int arg0
|          returns boolean



| **isCurrency** (arg0) → boolean
|          int arg0
|          returns boolean



| **isDefinitelyWritable** (arg0) → boolean
|          int arg0
|          returns boolean



| **isNullable** (arg0) → int
|          int arg0
|          returns int



| **isReadOnly** (arg0) → boolean
|          int arg0
|          returns boolean



| **isSearchable** (arg0) → boolean
|          int arg0
|          returns boolean



| **isSigned** (arg0) → boolean
|          int arg0
|          returns boolean



| **isWritable** (arg0) → boolean
|          int arg0
|          returns boolean



| **isWrapperFor** (iface) → boolean
|          :ref:`Class<java.lang.Class>` iface
|          returns boolean



| **unwrap** (iface) → T
|          :ref:`Class<java.lang.Class>` iface
|          returns T




..  _com.sqlsheet.XlsStatement:

=======================================================================
XlsStatement
=======================================================================

*extends:* :ref:`Object<java.lang.Object>` *implements:* :ref:`Statement<java.sql.Statement>` *provides:* :ref:`XlsPreparedStatement<com.sqlsheet.XlsPreparedStatement>` 

| SqlSheet implementation of java.sql.Statement.

| **XlsStatement** (c)
|          :ref:`XlsConnection<com.sqlsheet.XlsConnection>` c


| **getSheetNamed** (wb, name) → Sheet
|          Workbook wb
|          :ref:`String<java.lang.String>` name
|          returns Sheet



| **getConnection** () → :ref:`Connection<java.sql.Connection>`
|          returns :ref:`Connection<java.sql.Connection>`



| *@Override*
| **close** ()


| *@Override*
| **execute** (sql) → boolean
|          :ref:`String<java.lang.String>` sql
|          returns boolean



| *@Override*
| **executeUpdate** (sql) → int
|          :ref:`String<java.lang.String>` sql
|          returns int



| *@Override*
| **executeQuery** (query) → :ref:`ResultSet<java.sql.ResultSet>`
|          :ref:`String<java.lang.String>` query
|          returns :ref:`ResultSet<java.sql.ResultSet>`




                |          :ref:`String<java.lang.String>` sql

                |          returns :ref:`ParsedStatement<com.sqlsheet.parser.ParsedStatement>`


                
            
                |          :ref:`SelectStarStatement<com.sqlsheet.parser.SelectStarStatement>` sss

                |          returns :ref:`ResultSet<java.sql.ResultSet>`


                
            
                |          :ref:`CreateTableStatement<com.sqlsheet.parser.CreateTableStatement>` cts

                |          returns :ref:`ResultSet<java.sql.ResultSet>`


                
            
                |          :ref:`InsertIntoStatement<com.sqlsheet.parser.InsertIntoStatement>` insert

                |          returns :ref:`ResultSet<java.sql.ResultSet>`


                
            | **setEscapeProcessing** (p0)
|          boolean p0


| **setCursorName** (p0)
|          :ref:`String<java.lang.String>` p0


| **getMaxFieldSize** () → int
|          returns int



| **setMaxFieldSize** (p0)
|          int p0


| **getMaxRows** () → int
|          returns int



| **setMaxRows** (p0)
|          int p0


| **getQueryTimeout** () → int
|          returns int



| **setQueryTimeout** (p0)
|          int p0


| **getWarnings** () → :ref:`SQLWarning<java.sql.SQLWarning>`
|          returns :ref:`SQLWarning<java.sql.SQLWarning>`



| **getResultSet** () → :ref:`ResultSet<java.sql.ResultSet>`
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| **getUpdateCount** () → int
|          returns int



| *@Override*
| **getMoreResults** () → boolean
|          returns boolean



| *@Override*
| **getMoreResults** (current) → boolean
|          int current
|          returns boolean



| *@Override*
| **getFetchDirection** () → int
|          returns int



| *@Override*
| **setFetchDirection** (p0)
|          int p0


| *@Override*
| **getFetchSize** () → int
|          returns int



| *@Override*
| **setFetchSize** (p0)
|          int p0


| *@Override*
| **getResultSetConcurrency** () → int
|          returns int



| *@Override*
| **getResultSetType** () → int
|          returns int



| *@Override*
| **cancel** ()


| **clearWarnings** ()


| **addBatch** (p0)
|          :ref:`String<java.lang.String>` p0


| **clearBatch** ()


| **executeBatch** () → int
|          returns int



| *@Override*
| **getGeneratedKeys** () → :ref:`ResultSet<java.sql.ResultSet>`
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| **executeUpdate** (sql, autoGeneratedKeys) → int
|          :ref:`String<java.lang.String>` sql
|          int autoGeneratedKeys
|          returns int



| *@Override*
| **executeUpdate** (sql, columnIndexes) → int
|          :ref:`String<java.lang.String>` sql
|          int columnIndexes
|          returns int



| *@Override*
| **executeUpdate** (sql, columnNames) → int
|          :ref:`String<java.lang.String>` sql
|          :ref:`String<java.lang.String>` columnNames
|          returns int



| *@Override*
| **execute** (sql, autoGeneratedKeys) → boolean
|          :ref:`String<java.lang.String>` sql
|          int autoGeneratedKeys
|          returns boolean



| *@Override*
| **execute** (sql, columnIndexes) → boolean
|          :ref:`String<java.lang.String>` sql
|          int columnIndexes
|          returns boolean



| *@Override*
| **execute** (sql, columnNames) → boolean
|          :ref:`String<java.lang.String>` sql
|          :ref:`String<java.lang.String>` columnNames
|          returns boolean



| *@Override*
| **getResultSetHoldability** () → int
|          returns int




                |          returns void


                
            | *@Override*
| **isClosed** () → boolean
|          returns boolean



| *@Override*
| **isPoolable** () → boolean
|          returns boolean



| *@Override*
| **setPoolable** (poolable)
|          boolean poolable


| **isWrapperFor** (iface) → boolean
|          :ref:`Class<java.lang.Class>` iface
|          returns boolean



| **unwrap** (iface) → T
|          :ref:`Class<java.lang.Class>` iface
|          returns T



| *@Override*
| **closeOnCompletion** ()


| *@Override*
| **isCloseOnCompletion** () → boolean
|          returns boolean




..  _com.sqlsheet.parser:
***********************************************************************
parser
***********************************************************************

..  _com.sqlsheet.parser.JdbcParameter:

=======================================================================
JdbcParameter
=======================================================================

*extends:* :ref:`Object<java.lang.Object>` 

| Used to represent a jdbc parameter ('?') in a query for a PreparedSatement. A singleton with no fields and no methods. Possibly dumbest class in the world.


..  _com.sqlsheet.parser.SqlSheetParser:

=======================================================================
SqlSheetParser
=======================================================================

*extends:* :ref:`Object<java.lang.Object>` 

| Wrapper around the JSQLParser which does all the real work. This class and the rest of the interface in the package are here just to insulate the parent package from the particulars of a particular parser.

| **SqlSheetParser** ()


| **parse** (sql) → :ref:`ParsedStatement<com.sqlsheet.parser.ParsedStatement>`
|          :ref:`String<java.lang.String>` sql
|          returns :ref:`ParsedStatement<com.sqlsheet.parser.ParsedStatement>`




..  _com.sqlsheet.parser.CreateTableStatement:
=======================================================================
CreateTableStatement
=======================================================================

*implements:* :ref:`ParsedStatement<com.sqlsheet.parser.ParsedStatement>` *provides:*  

| Handle on a parsed SQL statement of the form CREATE TABLE xxx (xxx xxx, xxx xxx)

| **getTable** () → :ref:`String<java.lang.String>`
|          returns :ref:`String<java.lang.String>`



| **getColumns** () → :ref:`List<java.util.List>`
|          returns :ref:`List<java.util.List>`



| **getTypes** () → :ref:`List<java.util.List>`
|          returns :ref:`List<java.util.List>`




..  _com.sqlsheet.parser.DropTableStatement:
=======================================================================
DropTableStatement
=======================================================================

*implements:* :ref:`ParsedStatement<com.sqlsheet.parser.ParsedStatement>` *provides:*  

| Handle on a parsed SQL statement of the form DROP TABLE xxx.

| **getTable** () → :ref:`String<java.lang.String>`
|          returns :ref:`String<java.lang.String>`




..  _com.sqlsheet.parser.InsertIntoStatement:
=======================================================================
InsertIntoStatement
=======================================================================

*implements:* :ref:`ParsedStatement<com.sqlsheet.parser.ParsedStatement>` *provides:*  

| Handle on a parsed SQL statement of the form INSERT INTO xxx (xxx) VALUE (xxx).

| **getTable** () → :ref:`String<java.lang.String>`
|          returns :ref:`String<java.lang.String>`



| **getColumns** () → :ref:`List<java.util.List>`
|          returns :ref:`List<java.util.List>`



| **getValues** () → :ref:`List<java.util.List>`
|          returns :ref:`List<java.util.List>`




..  _com.sqlsheet.parser.ParsedStatement:
=======================================================================
ParsedStatement
=======================================================================

*provides:* :ref:`CreateTableStatement<com.sqlsheet.parser.CreateTableStatement>`, :ref:`DropTableStatement<com.sqlsheet.parser.DropTableStatement>`, :ref:`InsertIntoStatement<com.sqlsheet.parser.InsertIntoStatement>`, :ref:`SelectStarStatement<com.sqlsheet.parser.SelectStarStatement>`,  

| Marker interface for objects which describe a SQL parsed statement.


..  _com.sqlsheet.parser.SelectStarStatement:
=======================================================================
SelectStarStatement
=======================================================================

*implements:* :ref:`ParsedStatement<com.sqlsheet.parser.ParsedStatement>` *provides:*  

| Handle on a parsed SQL statement of the form SELECT * FROM xxx.

| **getTable** () → :ref:`String<java.lang.String>`
|          returns :ref:`String<java.lang.String>`




..  _com.sqlsheet.stream:
***********************************************************************
stream
***********************************************************************

..  _com.sqlsheet.stream.AbstractXlsSheetIterator:

=======================================================================
AbstractXlsSheetIterator
=======================================================================

*extends:* :ref:`Object<java.lang.Object>` *implements:* :ref:`Iterable<java.lang.Iterable>`, :ref:`Iterator<java.util.Iterator>` *provides:* :ref:`XlsSheetIterator<com.sqlsheet.stream.XlsSheetIterator>`, :ref:`XlsxSheetIterator<com.sqlsheet.stream.XlsxSheetIterator>` 

| **AbstractXlsSheetIterator** (filename, sheetName)
|          :ref:`URL<java.net.URL>` filename  | filename The file to postConstruct
|          :ref:`String<java.lang.String>` sheetName  | sheetName The sheet name



                |          returns void


                
            
                |          returns void


                
            
                |          returns void


                
            | **iterator** () → :ref:`Iterator<java.util.Iterator>`
|          returns :ref:`Iterator<java.util.Iterator>`



| **hasNext** () → boolean
|          returns boolean



| **next** () → :ref:`Object<java.lang.Object>`
|          returns :ref:`Object<java.lang.Object>`



| **remove** ()



                |          returns :ref:`URL<java.net.URL>`


            
                |          :ref:`URL<java.net.URL>` fileName

                |          returns void


            
                |          returns :ref:`String<java.lang.String>`


            
                |          :ref:`String<java.lang.String>` sheetName

                |          returns void


            
                |          returns :ref:`List<java.util.List>`


            
                |          :ref:`List<java.util.List>` columns

                |          returns void


            
                |          returns :ref:`Map<java.util.Map>`


            
                |          :ref:`Map<java.util.Map>` rowValues

                |          returns void


            
                |          returns :ref:`Long<java.lang.Long>`


            
                |          :ref:`Long<java.lang.Long>` currentSheetRowIndex

                |          returns void


            
                |          returns :ref:`Long<java.lang.Long>`


            
                |          :ref:`Long<java.lang.Long>` currentIteratorRowIndex

                |          returns void


            
..  _com.sqlsheet.stream.XlsSheetIterator:

=======================================================================
XlsSheetIterator
=======================================================================

*extends:* :ref:`AbstractXlsSheetIterator<com.sqlsheet.stream.AbstractXlsSheetIterator>` 

| Streaming iterator over XLS files Derived from: http://svn.apache.org/repos/asf/poi/trunk/src/examples/src/org/apache/poi/hssf/eventusermodel/examples/XLS2CSVmra.java

| **XlsSheetIterator** (filename, sheetName)
|          :ref:`URL<java.net.URL>` filename
|          :ref:`String<java.lang.String>` sheetName


| **postConstruct** ()
| Initiates the processing - position stream to the right sheet - extracts columns - extracts first row



                Process few records to get current and maybe few next rows loaded into memory
                |          returns void


                
            | *@SuppressWarnings*
| **processRecord** (record)
| Main HSSFListener method, processes events
|          Record record



                |          returns void


                
                
            | **getLastRowNumber** () → int
|          returns int



| **getLastColumnNumber** () → int
|          returns int




..  _com.sqlsheet.stream.XlsStreamConnection:

=======================================================================
XlsStreamConnection
=======================================================================

*extends:* :ref:`Object<java.lang.Object>` *implements:* :ref:`Connection<java.sql.Connection>` 

| SqlSheet implementation of java.sql.Connection which uses steaming over XLS

| **XlsStreamConnection** (xlsFile, info)
|          :ref:`URL<java.net.URL>` xlsFile
|          :ref:`Properties<java.util.Properties>` info


| **getInt** (key, defaultValue) → int
|          :ref:`String<java.lang.String>` key
|          int defaultValue
|          returns int



| **createStatement** () → :ref:`Statement<java.sql.Statement>`
|          returns :ref:`Statement<java.sql.Statement>`



| **prepareStatement** (sql) → :ref:`PreparedStatement<java.sql.PreparedStatement>`
|          :ref:`String<java.lang.String>` sql
|          returns :ref:`PreparedStatement<java.sql.PreparedStatement>`



| **prepareStatement** (sql, resultSetType, resultSetConcurrency) → :ref:`PreparedStatement<java.sql.PreparedStatement>`
|          :ref:`String<java.lang.String>` sql
|          int resultSetType
|          int resultSetConcurrency
|          returns :ref:`PreparedStatement<java.sql.PreparedStatement>`



| **close** ()


| **getAutoCommit** () → boolean
|          returns boolean



| **setAutoCommit** (autoCommit)
|          boolean autoCommit


| **isClosed** () → boolean
|          returns boolean



| **isReadOnly** () → boolean
|          returns boolean



| **setReadOnly** (readOnly)
|          boolean readOnly


| **getCatalog** () → :ref:`String<java.lang.String>`
|          returns :ref:`String<java.lang.String>`



| **setCatalog** (catalog)
|          :ref:`String<java.lang.String>` catalog


| **getTransactionIsolation** () → int
|          returns int



| **setTransactionIsolation** (level)
|          int level


| **getWarnings** () → :ref:`SQLWarning<java.sql.SQLWarning>`
|          returns :ref:`SQLWarning<java.sql.SQLWarning>`



| **getTypeMap** () → :ref:`Map<java.util.Map>`
|          returns :ref:`Map<java.util.Map>`



| **setTypeMap** (map)
|          :ref:`Map<java.util.Map>` map


| **commit** ()


| **rollback** ()


| **clearWarnings** ()


| **getMetaData** () → :ref:`DatabaseMetaData<java.sql.DatabaseMetaData>`
|          returns :ref:`DatabaseMetaData<java.sql.DatabaseMetaData>`



| **prepareCall** (sql) → :ref:`CallableStatement<java.sql.CallableStatement>`
|          :ref:`String<java.lang.String>` sql
|          returns :ref:`CallableStatement<java.sql.CallableStatement>`



| **nativeSQL** (sql) → :ref:`String<java.lang.String>`
|          :ref:`String<java.lang.String>` sql
|          returns :ref:`String<java.lang.String>`



| **createStatement** (resultSetType, resultSetConcurrency) → :ref:`Statement<java.sql.Statement>`
|          int resultSetType
|          int resultSetConcurrency
|          returns :ref:`Statement<java.sql.Statement>`



| **prepareCall** (sql, resultSetType, resultSetConcurrency) → :ref:`CallableStatement<java.sql.CallableStatement>`
|          :ref:`String<java.lang.String>` sql
|          int resultSetType
|          int resultSetConcurrency
|          returns :ref:`CallableStatement<java.sql.CallableStatement>`



| **getHoldability** () → int
|          returns int



| **setHoldability** (param)
|          int param


| **prepareCall** (str, param, param2, param3) → :ref:`CallableStatement<java.sql.CallableStatement>`
|          :ref:`String<java.lang.String>` str
|          int param
|          int param2
|          int param3
|          returns :ref:`CallableStatement<java.sql.CallableStatement>`



| **prepareStatement** (str, param) → :ref:`PreparedStatement<java.sql.PreparedStatement>`
|          :ref:`String<java.lang.String>` str
|          int param
|          returns :ref:`PreparedStatement<java.sql.PreparedStatement>`



| **prepareStatement** (str, values) → :ref:`PreparedStatement<java.sql.PreparedStatement>`
|          :ref:`String<java.lang.String>` str
|          int values
|          returns :ref:`PreparedStatement<java.sql.PreparedStatement>`



| **prepareStatement** (str, str1) → :ref:`PreparedStatement<java.sql.PreparedStatement>`
|          :ref:`String<java.lang.String>` str
|          :ref:`String<java.lang.String>` str1
|          returns :ref:`PreparedStatement<java.sql.PreparedStatement>`



| **createClob** () → :ref:`Clob<java.sql.Clob>`
|          returns :ref:`Clob<java.sql.Clob>`



| **createBlob** () → :ref:`Blob<java.sql.Blob>`
|          returns :ref:`Blob<java.sql.Blob>`



| **createNClob** () → :ref:`NClob<java.sql.NClob>`
|          returns :ref:`NClob<java.sql.NClob>`



| **createSQLXML** () → :ref:`SQLXML<java.sql.SQLXML>`
|          returns :ref:`SQLXML<java.sql.SQLXML>`



| **isValid** (timeout) → boolean
|          int timeout
|          returns boolean



| **setClientInfo** (name, value)
|          :ref:`String<java.lang.String>` name
|          :ref:`String<java.lang.String>` value


| **getClientInfo** (name) → :ref:`String<java.lang.String>`
|          :ref:`String<java.lang.String>` name
|          returns :ref:`String<java.lang.String>`



| **getClientInfo** () → :ref:`Properties<java.util.Properties>`
|          returns :ref:`Properties<java.util.Properties>`



| **setClientInfo** (properties)
|          :ref:`Properties<java.util.Properties>` properties


| **createArrayOf** (typeName, elements) → :ref:`Array<java.sql.Array>`
|          :ref:`String<java.lang.String>` typeName
|          :ref:`Object<java.lang.Object>` elements
|          returns :ref:`Array<java.sql.Array>`



| **createStruct** (typeName, attributes) → :ref:`Struct<java.sql.Struct>`
|          :ref:`String<java.lang.String>` typeName
|          :ref:`Object<java.lang.Object>` attributes
|          returns :ref:`Struct<java.sql.Struct>`



| **prepareStatement** (str, param, param2, param3) → :ref:`PreparedStatement<java.sql.PreparedStatement>`
|          :ref:`String<java.lang.String>` str
|          int param
|          int param2
|          int param3
|          returns :ref:`PreparedStatement<java.sql.PreparedStatement>`



| **releaseSavepoint** (savepoint)
|          :ref:`Savepoint<java.sql.Savepoint>` savepoint


| **rollback** (savepoint)
|          :ref:`Savepoint<java.sql.Savepoint>` savepoint


| **setSavepoint** () → :ref:`Savepoint<java.sql.Savepoint>`
|          returns :ref:`Savepoint<java.sql.Savepoint>`



| **setSavepoint** (str) → :ref:`Savepoint<java.sql.Savepoint>`
|          :ref:`String<java.lang.String>` str
|          returns :ref:`Savepoint<java.sql.Savepoint>`



| **createStatement** (resultSetType, resultSetConcurrency, resultSetHoldability) → :ref:`Statement<java.sql.Statement>`
|          int resultSetType
|          int resultSetConcurrency
|          int resultSetHoldability
|          returns :ref:`Statement<java.sql.Statement>`



| **unwrap** (iface) → T
|          :ref:`Class<java.lang.Class>` iface
|          returns T



| **isWrapperFor** (iface) → boolean
|          :ref:`Class<java.lang.Class>` iface
|          returns boolean



| **getSchema** () → :ref:`String<java.lang.String>`
|          returns :ref:`String<java.lang.String>`



| **setSchema** (string)
|          :ref:`String<java.lang.String>` string


| **abort** (exctr)
|          :ref:`Executor<java.util.concurrent.Executor>` exctr


| **setNetworkTimeout** (exctr, i)
|          :ref:`Executor<java.util.concurrent.Executor>` exctr
|          int i


| **getNetworkTimeout** () → int
|          returns int



| **getWorkBook** () → Workbook
|          returns Workbook




..  _com.sqlsheet.stream.XlsStreamDatabaseMetaData:

=======================================================================
XlsStreamDatabaseMetaData
=======================================================================

*extends:* :ref:`Object<java.lang.Object>` *implements:* :ref:`DatabaseMetaData<java.sql.DatabaseMetaData>` 

| **XlsStreamDatabaseMetaData** (connection)
|          :ref:`XlsStreamConnection<com.sqlsheet.stream.XlsStreamConnection>` connection


| *@Override*
| **allProceduresAreCallable** () → boolean
|          returns boolean



| *@Override*
| **allTablesAreSelectable** () → boolean
|          returns boolean



| *@Override*
| **getURL** () → :ref:`String<java.lang.String>`
|          returns :ref:`String<java.lang.String>`



| *@Override*
| **getUserName** () → :ref:`String<java.lang.String>`
|          returns :ref:`String<java.lang.String>`



| *@Override*
| **isReadOnly** () → boolean
|          returns boolean



| *@Override*
| **nullsAreSortedHigh** () → boolean
|          returns boolean



| *@Override*
| **nullsAreSortedLow** () → boolean
|          returns boolean



| *@Override*
| **nullsAreSortedAtStart** () → boolean
|          returns boolean



| *@Override*
| **nullsAreSortedAtEnd** () → boolean
|          returns boolean



| *@Override*
| **getDatabaseProductName** () → :ref:`String<java.lang.String>`
|          returns :ref:`String<java.lang.String>`



| *@Override*
| **getDatabaseProductVersion** () → :ref:`String<java.lang.String>`
|          returns :ref:`String<java.lang.String>`



| *@Override*
| **getDriverName** () → :ref:`String<java.lang.String>`
|          returns :ref:`String<java.lang.String>`



| *@Override*
| **getDriverVersion** () → :ref:`String<java.lang.String>`
|          returns :ref:`String<java.lang.String>`



| *@Override*
| **getDriverMajorVersion** () → int
|          returns int



| *@Override*
| **getDriverMinorVersion** () → int
|          returns int



| *@Override*
| **usesLocalFiles** () → boolean
|          returns boolean



| *@Override*
| **usesLocalFilePerTable** () → boolean
|          returns boolean



| *@Override*
| **supportsMixedCaseIdentifiers** () → boolean
|          returns boolean



| *@Override*
| **storesUpperCaseIdentifiers** () → boolean
|          returns boolean



| *@Override*
| **storesLowerCaseIdentifiers** () → boolean
|          returns boolean



| *@Override*
| **storesMixedCaseIdentifiers** () → boolean
|          returns boolean



| *@Override*
| **supportsMixedCaseQuotedIdentifiers** () → boolean
|          returns boolean



| *@Override*
| **storesUpperCaseQuotedIdentifiers** () → boolean
|          returns boolean



| *@Override*
| **storesLowerCaseQuotedIdentifiers** () → boolean
|          returns boolean



| *@Override*
| **storesMixedCaseQuotedIdentifiers** () → boolean
|          returns boolean



| *@Override*
| **getIdentifierQuoteString** () → :ref:`String<java.lang.String>`
|          returns :ref:`String<java.lang.String>`



| *@Override*
| **getSQLKeywords** () → :ref:`String<java.lang.String>`
|          returns :ref:`String<java.lang.String>`



| *@Override*
| **getNumericFunctions** () → :ref:`String<java.lang.String>`
|          returns :ref:`String<java.lang.String>`



| *@Override*
| **getStringFunctions** () → :ref:`String<java.lang.String>`
|          returns :ref:`String<java.lang.String>`



| *@Override*
| **getSystemFunctions** () → :ref:`String<java.lang.String>`
|          returns :ref:`String<java.lang.String>`



| *@Override*
| **getTimeDateFunctions** () → :ref:`String<java.lang.String>`
|          returns :ref:`String<java.lang.String>`



| *@Override*
| **getSearchStringEscape** () → :ref:`String<java.lang.String>`
|          returns :ref:`String<java.lang.String>`



| *@Override*
| **getExtraNameCharacters** () → :ref:`String<java.lang.String>`
|          returns :ref:`String<java.lang.String>`



| *@Override*
| **supportsAlterTableWithAddColumn** () → boolean
|          returns boolean



| *@Override*
| **supportsAlterTableWithDropColumn** () → boolean
|          returns boolean



| *@Override*
| **supportsColumnAliasing** () → boolean
|          returns boolean



| *@Override*
| **nullPlusNonNullIsNull** () → boolean
|          returns boolean



| *@Override*
| **supportsConvert** () → boolean
|          returns boolean



| *@Override*
| **supportsConvert** (i, i1) → boolean
|          int i
|          int i1
|          returns boolean



| *@Override*
| **supportsTableCorrelationNames** () → boolean
|          returns boolean



| *@Override*
| **supportsDifferentTableCorrelationNames** () → boolean
|          returns boolean



| *@Override*
| **supportsExpressionsInOrderBy** () → boolean
|          returns boolean



| *@Override*
| **supportsOrderByUnrelated** () → boolean
|          returns boolean



| *@Override*
| **supportsGroupBy** () → boolean
|          returns boolean



| *@Override*
| **supportsGroupByUnrelated** () → boolean
|          returns boolean



| *@Override*
| **supportsGroupByBeyondSelect** () → boolean
|          returns boolean



| *@Override*
| **supportsLikeEscapeClause** () → boolean
|          returns boolean



| *@Override*
| **supportsMultipleResultSets** () → boolean
|          returns boolean



| *@Override*
| **supportsMultipleTransactions** () → boolean
|          returns boolean



| *@Override*
| **supportsNonNullableColumns** () → boolean
|          returns boolean



| *@Override*
| **supportsMinimumSQLGrammar** () → boolean
|          returns boolean



| *@Override*
| **supportsCoreSQLGrammar** () → boolean
|          returns boolean



| *@Override*
| **supportsExtendedSQLGrammar** () → boolean
|          returns boolean



| *@Override*
| **supportsANSI92EntryLevelSQL** () → boolean
|          returns boolean



| *@Override*
| **supportsANSI92IntermediateSQL** () → boolean
|          returns boolean



| *@Override*
| **supportsANSI92FullSQL** () → boolean
|          returns boolean



| *@Override*
| **supportsIntegrityEnhancementFacility** () → boolean
|          returns boolean



| *@Override*
| **supportsOuterJoins** () → boolean
|          returns boolean



| *@Override*
| **supportsFullOuterJoins** () → boolean
|          returns boolean



| *@Override*
| **supportsLimitedOuterJoins** () → boolean
|          returns boolean



| *@Override*
| **getSchemaTerm** () → :ref:`String<java.lang.String>`
|          returns :ref:`String<java.lang.String>`



| *@Override*
| **getProcedureTerm** () → :ref:`String<java.lang.String>`
|          returns :ref:`String<java.lang.String>`



| *@Override*
| **getCatalogTerm** () → :ref:`String<java.lang.String>`
|          returns :ref:`String<java.lang.String>`



| *@Override*
| **isCatalogAtStart** () → boolean
|          returns boolean



| *@Override*
| **getCatalogSeparator** () → :ref:`String<java.lang.String>`
|          returns :ref:`String<java.lang.String>`



| *@Override*
| **supportsSchemasInDataManipulation** () → boolean
|          returns boolean



| *@Override*
| **supportsSchemasInProcedureCalls** () → boolean
|          returns boolean



| *@Override*
| **supportsSchemasInTableDefinitions** () → boolean
|          returns boolean



| *@Override*
| **supportsSchemasInIndexDefinitions** () → boolean
|          returns boolean



| *@Override*
| **supportsSchemasInPrivilegeDefinitions** () → boolean
|          returns boolean



| *@Override*
| **supportsCatalogsInDataManipulation** () → boolean
|          returns boolean



| *@Override*
| **supportsCatalogsInProcedureCalls** () → boolean
|          returns boolean



| *@Override*
| **supportsCatalogsInTableDefinitions** () → boolean
|          returns boolean



| *@Override*
| **supportsCatalogsInIndexDefinitions** () → boolean
|          returns boolean



| *@Override*
| **supportsCatalogsInPrivilegeDefinitions** () → boolean
|          returns boolean



| *@Override*
| **supportsPositionedDelete** () → boolean
|          returns boolean



| *@Override*
| **supportsPositionedUpdate** () → boolean
|          returns boolean



| *@Override*
| **supportsSelectForUpdate** () → boolean
|          returns boolean



| *@Override*
| **supportsStoredProcedures** () → boolean
|          returns boolean



| *@Override*
| **supportsSubqueriesInComparisons** () → boolean
|          returns boolean



| *@Override*
| **supportsSubqueriesInExists** () → boolean
|          returns boolean



| *@Override*
| **supportsSubqueriesInIns** () → boolean
|          returns boolean



| *@Override*
| **supportsSubqueriesInQuantifieds** () → boolean
|          returns boolean



| *@Override*
| **supportsCorrelatedSubqueries** () → boolean
|          returns boolean



| *@Override*
| **supportsUnion** () → boolean
|          returns boolean



| *@Override*
| **supportsUnionAll** () → boolean
|          returns boolean



| *@Override*
| **supportsOpenCursorsAcrossCommit** () → boolean
|          returns boolean



| *@Override*
| **supportsOpenCursorsAcrossRollback** () → boolean
|          returns boolean



| *@Override*
| **supportsOpenStatementsAcrossCommit** () → boolean
|          returns boolean



| *@Override*
| **supportsOpenStatementsAcrossRollback** () → boolean
|          returns boolean



| *@Override*
| **getMaxBinaryLiteralLength** () → int
|          returns int



| *@Override*
| **getMaxCharLiteralLength** () → int
|          returns int



| *@Override*
| **getMaxColumnNameLength** () → int
|          returns int



| *@Override*
| **getMaxColumnsInGroupBy** () → int
|          returns int



| *@Override*
| **getMaxColumnsInIndex** () → int
|          returns int



| *@Override*
| **getMaxColumnsInOrderBy** () → int
|          returns int



| *@Override*
| **getMaxColumnsInSelect** () → int
|          returns int



| *@Override*
| **getMaxColumnsInTable** () → int
|          returns int



| *@Override*
| **getMaxConnections** () → int
|          returns int



| *@Override*
| **getMaxCursorNameLength** () → int
|          returns int



| *@Override*
| **getMaxIndexLength** () → int
|          returns int



| *@Override*
| **getMaxSchemaNameLength** () → int
|          returns int



| *@Override*
| **getMaxProcedureNameLength** () → int
|          returns int



| *@Override*
| **getMaxCatalogNameLength** () → int
|          returns int



| *@Override*
| **getMaxRowSize** () → int
|          returns int



| *@Override*
| **doesMaxRowSizeIncludeBlobs** () → boolean
|          returns boolean



| *@Override*
| **getMaxStatementLength** () → int
|          returns int



| *@Override*
| **getMaxStatements** () → int
|          returns int



| *@Override*
| **getMaxTableNameLength** () → int
|          returns int



| *@Override*
| **getMaxTablesInSelect** () → int
|          returns int



| *@Override*
| **getMaxUserNameLength** () → int
|          returns int



| *@Override*
| **getDefaultTransactionIsolation** () → int
|          returns int



| *@Override*
| **supportsTransactions** () → boolean
|          returns boolean



| *@Override*
| **supportsTransactionIsolationLevel** (i) → boolean
|          int i
|          returns boolean



| *@Override*
| **supportsDataDefinitionAndDataManipulationTransactions** () → boolean
|          returns boolean



| *@Override*
| **supportsDataManipulationTransactionsOnly** () → boolean
|          returns boolean



| *@Override*
| **dataDefinitionCausesTransactionCommit** () → boolean
|          returns boolean



| *@Override*
| **dataDefinitionIgnoredInTransactions** () → boolean
|          returns boolean



| *@Override*
| **getProcedures** (string, string1, string2) → :ref:`ResultSet<java.sql.ResultSet>`
|          :ref:`String<java.lang.String>` string
|          :ref:`String<java.lang.String>` string1
|          :ref:`String<java.lang.String>` string2
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| *@Override*
| **getProcedureColumns** (string, string1, string2, string3) → :ref:`ResultSet<java.sql.ResultSet>`
|          :ref:`String<java.lang.String>` string
|          :ref:`String<java.lang.String>` string1
|          :ref:`String<java.lang.String>` string2
|          :ref:`String<java.lang.String>` string3
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| *@Override*
| **getTables** (catalog, schemaPattern, tableNamePattern, types) → :ref:`ResultSet<java.sql.ResultSet>`
|          :ref:`String<java.lang.String>` catalog
|          :ref:`String<java.lang.String>` schemaPattern
|          :ref:`String<java.lang.String>` tableNamePattern
|          :ref:`String<java.lang.String>` types
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| *@Override*
| **getSchemas** () → :ref:`ResultSet<java.sql.ResultSet>`
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| *@Override*
| **getCatalogs** () → :ref:`ResultSet<java.sql.ResultSet>`
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| *@Override*
| **getTableTypes** () → :ref:`ResultSet<java.sql.ResultSet>`
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| *@Override*,| *@SuppressWarnings*
| **getColumns** (catalog, schemaPattern, tableNamePattern, columnNamePattern) → :ref:`ResultSet<java.sql.ResultSet>`
|          :ref:`String<java.lang.String>` catalog
|          :ref:`String<java.lang.String>` schemaPattern
|          :ref:`String<java.lang.String>` tableNamePattern
|          :ref:`String<java.lang.String>` columnNamePattern
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| *@Override*
| **getColumnPrivileges** (string, string1, string2, string3) → :ref:`ResultSet<java.sql.ResultSet>`
|          :ref:`String<java.lang.String>` string
|          :ref:`String<java.lang.String>` string1
|          :ref:`String<java.lang.String>` string2
|          :ref:`String<java.lang.String>` string3
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| *@Override*
| **getTablePrivileges** (string, string1, string2) → :ref:`ResultSet<java.sql.ResultSet>`
|          :ref:`String<java.lang.String>` string
|          :ref:`String<java.lang.String>` string1
|          :ref:`String<java.lang.String>` string2
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| *@Override*
| **getBestRowIdentifier** (string, string1, string2, i, bln) → :ref:`ResultSet<java.sql.ResultSet>`
|          :ref:`String<java.lang.String>` string
|          :ref:`String<java.lang.String>` string1
|          :ref:`String<java.lang.String>` string2
|          int i
|          boolean bln
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| *@Override*
| **getVersionColumns** (string, string1, string2) → :ref:`ResultSet<java.sql.ResultSet>`
|          :ref:`String<java.lang.String>` string
|          :ref:`String<java.lang.String>` string1
|          :ref:`String<java.lang.String>` string2
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| *@Override*
| **getPrimaryKeys** (catalog, schema, table) → :ref:`ResultSet<java.sql.ResultSet>`
|          :ref:`String<java.lang.String>` catalog
|          :ref:`String<java.lang.String>` schema
|          :ref:`String<java.lang.String>` table
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| *@Override*
| **getImportedKeys** (catalog, schema, table) → :ref:`ResultSet<java.sql.ResultSet>`
|          :ref:`String<java.lang.String>` catalog
|          :ref:`String<java.lang.String>` schema
|          :ref:`String<java.lang.String>` table
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| *@Override*
| **getExportedKeys** (catalog, schema, table) → :ref:`ResultSet<java.sql.ResultSet>`
|          :ref:`String<java.lang.String>` catalog
|          :ref:`String<java.lang.String>` schema
|          :ref:`String<java.lang.String>` table
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| *@Override*
| **getCrossReference** (parentCatalog, parentSchema, parentTable, foreignCatalog, foreignSchema, foreignTable) → :ref:`ResultSet<java.sql.ResultSet>`
|          :ref:`String<java.lang.String>` parentCatalog
|          :ref:`String<java.lang.String>` parentSchema
|          :ref:`String<java.lang.String>` parentTable
|          :ref:`String<java.lang.String>` foreignCatalog
|          :ref:`String<java.lang.String>` foreignSchema
|          :ref:`String<java.lang.String>` foreignTable
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| *@Override*
| **getTypeInfo** () → :ref:`ResultSet<java.sql.ResultSet>`
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| *@Override*
| **getIndexInfo** (catalog, schema, table, unique, approximate) → :ref:`ResultSet<java.sql.ResultSet>`
|          :ref:`String<java.lang.String>` catalog
|          :ref:`String<java.lang.String>` schema
|          :ref:`String<java.lang.String>` table
|          boolean unique
|          boolean approximate
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| *@Override*
| **supportsResultSetType** (i) → boolean
|          int i
|          returns boolean



| *@Override*
| **supportsResultSetConcurrency** (i, i1) → boolean
|          int i
|          int i1
|          returns boolean



| *@Override*
| **ownUpdatesAreVisible** (i) → boolean
|          int i
|          returns boolean



| *@Override*
| **ownDeletesAreVisible** (i) → boolean
|          int i
|          returns boolean



| *@Override*
| **ownInsertsAreVisible** (i) → boolean
|          int i
|          returns boolean



| *@Override*
| **othersUpdatesAreVisible** (i) → boolean
|          int i
|          returns boolean



| *@Override*
| **othersDeletesAreVisible** (i) → boolean
|          int i
|          returns boolean



| *@Override*
| **othersInsertsAreVisible** (i) → boolean
|          int i
|          returns boolean



| *@Override*
| **updatesAreDetected** (i) → boolean
|          int i
|          returns boolean



| *@Override*
| **deletesAreDetected** (i) → boolean
|          int i
|          returns boolean



| *@Override*
| **insertsAreDetected** (i) → boolean
|          int i
|          returns boolean



| *@Override*
| **supportsBatchUpdates** () → boolean
|          returns boolean



| *@Override*
| **getUDTs** (string, string1, string2, ints) → :ref:`ResultSet<java.sql.ResultSet>`
|          :ref:`String<java.lang.String>` string
|          :ref:`String<java.lang.String>` string1
|          :ref:`String<java.lang.String>` string2
|          int ints
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| *@Override*
| **getConnection** () → :ref:`Connection<java.sql.Connection>`
|          returns :ref:`Connection<java.sql.Connection>`



| *@Override*
| **supportsSavepoints** () → boolean
|          returns boolean



| *@Override*
| **supportsNamedParameters** () → boolean
|          returns boolean



| *@Override*
| **supportsMultipleOpenResults** () → boolean
|          returns boolean



| *@Override*
| **supportsGetGeneratedKeys** () → boolean
|          returns boolean



| *@Override*
| **getSuperTypes** (string, string1, string2) → :ref:`ResultSet<java.sql.ResultSet>`
|          :ref:`String<java.lang.String>` string
|          :ref:`String<java.lang.String>` string1
|          :ref:`String<java.lang.String>` string2
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| *@Override*
| **getSuperTables** (string, string1, string2) → :ref:`ResultSet<java.sql.ResultSet>`
|          :ref:`String<java.lang.String>` string
|          :ref:`String<java.lang.String>` string1
|          :ref:`String<java.lang.String>` string2
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| *@Override*
| **getAttributes** (string, string1, string2, string3) → :ref:`ResultSet<java.sql.ResultSet>`
|          :ref:`String<java.lang.String>` string
|          :ref:`String<java.lang.String>` string1
|          :ref:`String<java.lang.String>` string2
|          :ref:`String<java.lang.String>` string3
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| *@Override*
| **supportsResultSetHoldability** (i) → boolean
|          int i
|          returns boolean



| *@Override*
| **getResultSetHoldability** () → int
|          returns int



| *@Override*
| **getDatabaseMajorVersion** () → int
|          returns int



| *@Override*
| **getDatabaseMinorVersion** () → int
|          returns int



| *@Override*
| **getJDBCMajorVersion** () → int
|          returns int



| *@Override*
| **getJDBCMinorVersion** () → int
|          returns int



| *@Override*
| **getSQLStateType** () → int
|          returns int



| *@Override*
| **locatorsUpdateCopy** () → boolean
|          returns boolean



| *@Override*
| **supportsStatementPooling** () → boolean
|          returns boolean



| *@Override*
| **getRowIdLifetime** () → :ref:`RowIdLifetime<java.sql.RowIdLifetime>`
|          returns :ref:`RowIdLifetime<java.sql.RowIdLifetime>`



| *@Override*
| **getSchemas** (catalog, schemaPattern) → :ref:`ResultSet<java.sql.ResultSet>`
|          :ref:`String<java.lang.String>` catalog
|          :ref:`String<java.lang.String>` schemaPattern
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| *@Override*
| **supportsStoredFunctionsUsingCallSyntax** () → boolean
|          returns boolean



| *@Override*
| **autoCommitFailureClosesAllResultSets** () → boolean
|          returns boolean



| *@Override*
| **getClientInfoProperties** () → :ref:`ResultSet<java.sql.ResultSet>`
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| *@Override*
| **getFunctions** (string, string1, string2) → :ref:`ResultSet<java.sql.ResultSet>`
|          :ref:`String<java.lang.String>` string
|          :ref:`String<java.lang.String>` string1
|          :ref:`String<java.lang.String>` string2
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| *@Override*
| **getFunctionColumns** (string, string1, string2, string3) → :ref:`ResultSet<java.sql.ResultSet>`
|          :ref:`String<java.lang.String>` string
|          :ref:`String<java.lang.String>` string1
|          :ref:`String<java.lang.String>` string2
|          :ref:`String<java.lang.String>` string3
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| *@Override*
| **getPseudoColumns** (string, string1, string2, string3) → :ref:`ResultSet<java.sql.ResultSet>`
|          :ref:`String<java.lang.String>` string
|          :ref:`String<java.lang.String>` string1
|          :ref:`String<java.lang.String>` string2
|          :ref:`String<java.lang.String>` string3
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| *@Override*
| **generatedKeyAlwaysReturned** () → boolean
|          returns boolean



| *@Override*
| **unwrap** (type) → T
|          :ref:`Class<java.lang.Class>` type
|          returns T



| *@Override*
| **isWrapperFor** (type) → boolean
|          :ref:`Class<java.lang.Class>` type
|          returns boolean




..  _com.sqlsheet.stream.XlsStreamPreparedStatement:

=======================================================================
XlsStreamPreparedStatement
=======================================================================

*extends:* :ref:`XlsStreamStatement<com.sqlsheet.stream.XlsStreamStatement>` *implements:* :ref:`PreparedStatement<java.sql.PreparedStatement>` 

| SqlSheet implementation of java.sql.PreparedStatement which uses steaming over XLS

| **XlsStreamPreparedStatement** (conn, sql, firstSheetRowOffset, firstSheetColOffset)
|          :ref:`XlsStreamConnection<com.sqlsheet.stream.XlsStreamConnection>` conn
|          :ref:`String<java.lang.String>` sql
|          int firstSheetRowOffset
|          int firstSheetColOffset


| **addBatch** ()


| **clearParameters** ()


| **execute** () → boolean
|          returns boolean



| **executeUpdate** () → int
|          returns int



| **executeQuery** () → :ref:`ResultSet<java.sql.ResultSet>`
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| **setBigDecimal** (arg0, arg1)
|          int arg0
|          :ref:`BigDecimal<java.math.BigDecimal>` arg1


| **setBoolean** (arg0, arg1)
|          int arg0
|          boolean arg1


| **setByte** (arg0, arg1)
|          int arg0
|          byte arg1


| **setDate** (arg0, arg1)
|          int arg0
|          :ref:`Date<java.sql.Date>` arg1


| **setDouble** (arg0, arg1)
|          int arg0
|          double arg1


| **setFloat** (arg0, arg1)
|          int arg0
|          float arg1


| **setInt** (arg0, arg1)
|          int arg0
|          int arg1


| **setLong** (arg0, arg1)
|          int arg0
|          long arg1


| **setNull** (arg0, arg1)
|          int arg0
|          int arg1


| **setNull** (arg0, arg1, arg2)
|          int arg0
|          int arg1
|          :ref:`String<java.lang.String>` arg2


| **setShort** (arg0, arg1)
|          int arg0
|          short arg1


| **setString** (arg0, arg1)
|          int arg0
|          :ref:`String<java.lang.String>` arg1


| **setTime** (arg0, arg1)
|          int arg0
|          :ref:`Time<java.sql.Time>` arg1


| **setTimestamp** (arg0, arg1)
|          int arg0
|          :ref:`Timestamp<java.sql.Timestamp>` arg1


| **setCharacterStream** (parameterIndex, reader, length)
|          int parameterIndex
|          :ref:`Reader<java.io.Reader>` reader
|          int length


| **setObject** (arg0, arg1)
|          int arg0
|          :ref:`Object<java.lang.Object>` arg1


| **setTimestamp** (arg0, arg1, arg2)
|          int arg0
|          :ref:`Timestamp<java.sql.Timestamp>` arg1
|          :ref:`Calendar<java.util.Calendar>` arg2


| **setTime** (arg0, arg1, arg2)
|          int arg0
|          :ref:`Time<java.sql.Time>` arg1
|          :ref:`Calendar<java.util.Calendar>` arg2


| **setArray** (arg0, arg1)
|          int arg0
|          :ref:`Array<java.sql.Array>` arg1


| **setAsciiStream** (arg0, arg1, arg2)
|          int arg0
|          :ref:`InputStream<java.io.InputStream>` arg1
|          int arg2


| **setDate** (arg0, arg1, arg2)
|          int arg0
|          :ref:`Date<java.sql.Date>` arg1
|          :ref:`Calendar<java.util.Calendar>` arg2


| **setBytes** (arg0, arg1)
|          int arg0
|          byte arg1


| **setClob** (arg0, arg1)
|          int arg0
|          :ref:`Clob<java.sql.Clob>` arg1


| **setObject** (arg0, arg1, arg2)
|          int arg0
|          :ref:`Object<java.lang.Object>` arg1
|          int arg2


| **setObject** (arg0, arg1, arg2, arg3)
|          int arg0
|          :ref:`Object<java.lang.Object>` arg1
|          int arg2
|          int arg3


| **setAsciiStream** (parameterIndex, x, length)
|          int parameterIndex
|          :ref:`InputStream<java.io.InputStream>` x
|          long length


| **setBinaryStream** (parameterIndex, x, length)
|          int parameterIndex
|          :ref:`InputStream<java.io.InputStream>` x
|          long length


| **setCharacterStream** (parameterIndex, reader, length)
|          int parameterIndex
|          :ref:`Reader<java.io.Reader>` reader
|          long length


| **setAsciiStream** (parameterIndex, x)
|          int parameterIndex
|          :ref:`InputStream<java.io.InputStream>` x


| **setBinaryStream** (parameterIndex, x)
|          int parameterIndex
|          :ref:`InputStream<java.io.InputStream>` x


| **setCharacterStream** (parameterIndex, reader)
|          int parameterIndex
|          :ref:`Reader<java.io.Reader>` reader


| **setNCharacterStream** (parameterIndex, value)
|          int parameterIndex
|          :ref:`Reader<java.io.Reader>` value


| **setClob** (parameterIndex, reader)
|          int parameterIndex
|          :ref:`Reader<java.io.Reader>` reader


| **setBlob** (parameterIndex, inputStream)
|          int parameterIndex
|          :ref:`InputStream<java.io.InputStream>` inputStream


| **setNClob** (parameterIndex, reader)
|          int parameterIndex
|          :ref:`Reader<java.io.Reader>` reader


| **setRef** (arg0, arg1)
|          int arg0
|          :ref:`Ref<java.sql.Ref>` arg1


| **getMetaData** () → :ref:`ResultSetMetaData<java.sql.ResultSetMetaData>`
|          returns :ref:`ResultSetMetaData<java.sql.ResultSetMetaData>`



| **getParameterMetaData** () → :ref:`ParameterMetaData<java.sql.ParameterMetaData>`
|          returns :ref:`ParameterMetaData<java.sql.ParameterMetaData>`



| **setRowId** (parameterIndex, x)
|          int parameterIndex
|          :ref:`RowId<java.sql.RowId>` x


| **setNString** (parameterIndex, value)
|          int parameterIndex
|          :ref:`String<java.lang.String>` value


| **setNCharacterStream** (parameterIndex, value, length)
|          int parameterIndex
|          :ref:`Reader<java.io.Reader>` value
|          long length


| **setNClob** (parameterIndex, value)
|          int parameterIndex
|          :ref:`NClob<java.sql.NClob>` value


| **setClob** (parameterIndex, reader, length)
|          int parameterIndex
|          :ref:`Reader<java.io.Reader>` reader
|          long length


| **setBlob** (parameterIndex, inputStream, length)
|          int parameterIndex
|          :ref:`InputStream<java.io.InputStream>` inputStream
|          long length


| **setNClob** (parameterIndex, reader, length)
|          int parameterIndex
|          :ref:`Reader<java.io.Reader>` reader
|          long length


| **setSQLXML** (parameterIndex, xmlObject)
|          int parameterIndex
|          :ref:`SQLXML<java.sql.SQLXML>` xmlObject


| **setBinaryStream** (arg0, arg1, arg2)
|          int arg0
|          :ref:`InputStream<java.io.InputStream>` arg1
|          int arg2


| **setBlob** (arg0, arg1)
|          int arg0
|          :ref:`Blob<java.sql.Blob>` arg1


| **setURL** (arg0, arg1)
|          int arg0
|          :ref:`URL<java.net.URL>` arg1


| **setUnicodeStream** (arg0, arg1, arg2)
|          int arg0
|          :ref:`InputStream<java.io.InputStream>` arg1
|          int arg2


| **closeOnCompletion** ()


| **isCloseOnCompletion** () → boolean
|          returns boolean




..  _com.sqlsheet.stream.XlsStreamResultSet:

=======================================================================
XlsStreamResultSet
=======================================================================

*extends:* :ref:`Object<java.lang.Object>` *implements:* :ref:`ResultSet<java.sql.ResultSet>` 

| SqlSheet implementation of java.sql.ResultSet which uses steaming over XLS

| **XlsStreamResultSet** (sheet, firstSheetRowOffset, firstSheetColOffset)
|          Sheet sheet
|          int firstSheetRowOffset
|          int firstSheetColOffset



                |          int columnIndex

                |          returns Cell


            | **getMetaData** () → :ref:`ResultSetMetaData<java.sql.ResultSetMetaData>`
|          returns :ref:`ResultSetMetaData<java.sql.ResultSetMetaData>`



| *@Override*
| **getBigDecimal** (columnIndex) → :ref:`BigDecimal<java.math.BigDecimal>`
|          int columnIndex
|          returns :ref:`BigDecimal<java.math.BigDecimal>`



| *@Override*
| **getBigDecimal** (columnLabel) → :ref:`BigDecimal<java.math.BigDecimal>`
|          :ref:`String<java.lang.String>` columnLabel
|          returns :ref:`BigDecimal<java.math.BigDecimal>`



| *@Override*
| **getBigDecimal** (columnIndex, scale) → :ref:`BigDecimal<java.math.BigDecimal>`
|          int columnIndex
|          int scale
|          returns :ref:`BigDecimal<java.math.BigDecimal>`



| *@Override*
| **getBigDecimal** (columnLabel, scale) → :ref:`BigDecimal<java.math.BigDecimal>`
|          :ref:`String<java.lang.String>` columnLabel
|          int scale
|          returns :ref:`BigDecimal<java.math.BigDecimal>`



| *@Override*
| **getDate** (columnIndex) → :ref:`Date<java.sql.Date>`
|          int columnIndex
|          returns :ref:`Date<java.sql.Date>`



| *@Override*
| **getDate** (columnLabel) → :ref:`Date<java.sql.Date>`
|          :ref:`String<java.lang.String>` columnLabel
|          returns :ref:`Date<java.sql.Date>`



| *@Override*
| **getDate** (columnIndex, cal) → :ref:`Date<java.sql.Date>`
|          int columnIndex
|          :ref:`Calendar<java.util.Calendar>` cal
|          returns :ref:`Date<java.sql.Date>`



| *@Override*
| **getDate** (columnLabel, cal) → :ref:`Date<java.sql.Date>`
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`Calendar<java.util.Calendar>` cal
|          returns :ref:`Date<java.sql.Date>`



| *@Override*
| **getBoolean** (columnIndex) → boolean
|          int columnIndex
|          returns boolean



| *@Override*
| **getBoolean** (columnLabel) → boolean
|          :ref:`String<java.lang.String>` columnLabel
|          returns boolean



| *@Override*
| **getDouble** (columnIndex) → double
|          int columnIndex
|          returns double



| *@Override*
| **getDouble** (columnLabel) → double
|          :ref:`String<java.lang.String>` columnLabel
|          returns double



| *@Override*
| **getByte** (columnIndex) → byte
|          int columnIndex
|          returns byte



| *@Override*
| **getByte** (columnLabel) → byte
|          :ref:`String<java.lang.String>` columnLabel
|          returns byte



| *@Override*
| **getFloat** (columnIndex) → float
|          int columnIndex
|          returns float



| *@Override*
| **getFloat** (columnLabel) → float
|          :ref:`String<java.lang.String>` columnLabel
|          returns float



| *@Override*
| **getInt** (columnIndex) → int
|          int columnIndex
|          returns int



| *@Override*
| **getInt** (columnLabel) → int
|          :ref:`String<java.lang.String>` columnLabel
|          returns int



| *@Override*
| **getLong** (columnIndex) → long
|          int columnIndex
|          returns long



| *@Override*
| **getLong** (columnLabel) → long
|          :ref:`String<java.lang.String>` columnLabel
|          returns long



| *@Override*
| **getObject** (columnIndex) → :ref:`Object<java.lang.Object>`
|          int columnIndex
|          returns :ref:`Object<java.lang.Object>`



| *@Override*
| **getObject** (columnLabel) → :ref:`Object<java.lang.Object>`
|          :ref:`String<java.lang.String>` columnLabel
|          returns :ref:`Object<java.lang.Object>`



| *@Override*
| **getObject** (columnIndex, type) → T
|          int columnIndex
|          :ref:`Class<java.lang.Class>` type
|          returns T



| *@Override*
| **getObject** (columnLabel, type) → T
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`Class<java.lang.Class>` type
|          returns T



| *@Override*
| **getTimestamp** (columnIndex) → :ref:`Timestamp<java.sql.Timestamp>`
|          int columnIndex
|          returns :ref:`Timestamp<java.sql.Timestamp>`



| *@Override*
| **getTimestamp** (columnLabel) → :ref:`Timestamp<java.sql.Timestamp>`
|          :ref:`String<java.lang.String>` columnLabel
|          returns :ref:`Timestamp<java.sql.Timestamp>`



| *@Override*
| **getTimestamp** (columnIndex, cal) → :ref:`Timestamp<java.sql.Timestamp>`
|          int columnIndex
|          :ref:`Calendar<java.util.Calendar>` cal
|          returns :ref:`Timestamp<java.sql.Timestamp>`



| *@Override*
| **getTimestamp** (jdbcColumn, cal) → :ref:`Timestamp<java.sql.Timestamp>`
|          :ref:`String<java.lang.String>` jdbcColumn
|          :ref:`Calendar<java.util.Calendar>` cal
|          returns :ref:`Timestamp<java.sql.Timestamp>`



| *@Override*
| **getShort** (columnIndex) → short
|          int columnIndex
|          returns short



| *@Override*
| **getShort** (columnLabel) → short
|          :ref:`String<java.lang.String>` columnLabel
|          returns short



| *@Override*
| **getString** (columnIndex) → :ref:`String<java.lang.String>`
|          int columnIndex
|          returns :ref:`String<java.lang.String>`



| **getString** (columnLabel) → :ref:`String<java.lang.String>`
|          :ref:`String<java.lang.String>` columnLabel
|          returns :ref:`String<java.lang.String>`



| **updateBoolean** (jdbcColumn, x)
|          int jdbcColumn
|          boolean x


| **updateBoolean** (jdbcColumn, x)
|          :ref:`String<java.lang.String>` jdbcColumn
|          boolean x


| **updateByte** (jdbcColumn, x)
|          int jdbcColumn
|          byte x


| **updateByte** (jdbcColumn, x)
|          :ref:`String<java.lang.String>` jdbcColumn
|          byte x


| **updateDouble** (jdbcColumn, x)
|          int jdbcColumn
|          double x


| **updateDouble** (jdbcColumn, x)
|          :ref:`String<java.lang.String>` jdbcColumn
|          double x


| **updateFloat** (jdbcColumn, x)
|          int jdbcColumn
|          float x


| **updateFloat** (jdbcColumn, x)
|          :ref:`String<java.lang.String>` jdbcColumn
|          float x


| **updateInt** (jdbcColumn, x)
|          int jdbcColumn
|          int x


| **updateInt** (jdbcColumn, x)
|          :ref:`String<java.lang.String>` jdbcColumn
|          int x


| **updateLong** (jdbcColumn, x)
|          int jdbcColumn
|          long x


| **updateLong** (jdbcColumn, x)
|          :ref:`String<java.lang.String>` jdbcColumn
|          long x


| **updateNull** (jdbcColumn)
|          int jdbcColumn


| **updateNull** (jdbcColumn)
|          :ref:`String<java.lang.String>` jdbcColumn


| **updateObject** (jdbcColumn, x)
|          int jdbcColumn
|          :ref:`Object<java.lang.Object>` x


| **updateObject** (jdbcColumn, x)
|          :ref:`String<java.lang.String>` jdbcColumn
|          :ref:`Object<java.lang.Object>` x


| **updateShort** (jdbcColumn, x)
|          int jdbcColumn
|          short x


| **updateShort** (jdbcColumn, x)
|          :ref:`String<java.lang.String>` jdbcColumn
|          short x


| **updateString** (jdbcColumn, x)
|          int jdbcColumn
|          :ref:`String<java.lang.String>` x


| **updateString** (jdbcColumn, x)
|          :ref:`String<java.lang.String>` jdbcColumn
|          :ref:`String<java.lang.String>` x


| **absolute** (row) → boolean
|          int row
|          returns boolean



| **afterLast** ()


| **beforeFirst** ()


| **first** () → boolean
|          returns boolean



| **getFetchDirection** () → int
|          returns int



| **setFetchDirection** (direction)
|          int direction


| **getFetchSize** () → int
|          returns int



| **setFetchSize** (rows)
|          int rows


| **getRow** () → int
|          returns int



| **getType** () → int
|          returns int



| **isAfterLast** () → boolean
|          returns boolean



| **isBeforeFirst** () → boolean
|          returns boolean



| **isFirst** () → boolean
|          returns boolean



| **isLast** () → boolean
|          returns boolean



| **last** () → boolean
|          returns boolean



| **next** () → boolean
|          returns boolean



| **previous** () → boolean
|          returns boolean



| **moveToInsertRow** ()


| **insertRow** ()


| **cancelRowUpdates** ()


| **clearWarnings** ()


| **close** ()


| **deleteRow** ()


| **findColumn** (jdbcColumn) → int
|          :ref:`String<java.lang.String>` jdbcColumn
|          returns int



| **getArray** (i) → :ref:`Array<java.sql.Array>`
|          int i
|          returns :ref:`Array<java.sql.Array>`



| **getArray** (colName) → :ref:`Array<java.sql.Array>`
|          :ref:`String<java.lang.String>` colName
|          returns :ref:`Array<java.sql.Array>`



| **getAsciiStream** (jdbcColumn) → :ref:`InputStream<java.io.InputStream>`
|          int jdbcColumn
|          returns :ref:`InputStream<java.io.InputStream>`



| **getAsciiStream** (jdbcColumn) → :ref:`InputStream<java.io.InputStream>`
|          :ref:`String<java.lang.String>` jdbcColumn
|          returns :ref:`InputStream<java.io.InputStream>`



| **getBinaryStream** (jdbcColumn) → :ref:`InputStream<java.io.InputStream>`
|          int jdbcColumn
|          returns :ref:`InputStream<java.io.InputStream>`



| **getBinaryStream** (jdbcColumn) → :ref:`InputStream<java.io.InputStream>`
|          :ref:`String<java.lang.String>` jdbcColumn
|          returns :ref:`InputStream<java.io.InputStream>`



| **getBlob** (i) → :ref:`Blob<java.sql.Blob>`
|          int i
|          returns :ref:`Blob<java.sql.Blob>`



| **getBlob** (colName) → :ref:`Blob<java.sql.Blob>`
|          :ref:`String<java.lang.String>` colName
|          returns :ref:`Blob<java.sql.Blob>`



| **getBytes** (jdbcColumn) → byte
|          int jdbcColumn
|          returns byte



| **getBytes** (jdbcColumn) → byte
|          :ref:`String<java.lang.String>` jdbcColumn
|          returns byte



| **getCharacterStream** (jdbcColumn) → :ref:`Reader<java.io.Reader>`
|          int jdbcColumn
|          returns :ref:`Reader<java.io.Reader>`



| **getCharacterStream** (jdbcColumn) → :ref:`Reader<java.io.Reader>`
|          :ref:`String<java.lang.String>` jdbcColumn
|          returns :ref:`Reader<java.io.Reader>`



| **getClob** (i) → :ref:`Clob<java.sql.Clob>`
|          int i
|          returns :ref:`Clob<java.sql.Clob>`



| **getClob** (colName) → :ref:`Clob<java.sql.Clob>`
|          :ref:`String<java.lang.String>` colName
|          returns :ref:`Clob<java.sql.Clob>`



| **getConcurrency** () → int
|          returns int



| **getCursorName** () → :ref:`String<java.lang.String>`
|          returns :ref:`String<java.lang.String>`



| **getObject** (i, map) → :ref:`Object<java.lang.Object>`
|          int i
|          :ref:`Map<java.util.Map>` map
|          returns :ref:`Object<java.lang.Object>`



| **getObject** (colName, map) → :ref:`Object<java.lang.Object>`
|          :ref:`String<java.lang.String>` colName
|          :ref:`Map<java.util.Map>` map
|          returns :ref:`Object<java.lang.Object>`



| **getRef** (i) → :ref:`Ref<java.sql.Ref>`
|          int i
|          returns :ref:`Ref<java.sql.Ref>`



| **getRef** (colName) → :ref:`Ref<java.sql.Ref>`
|          :ref:`String<java.lang.String>` colName
|          returns :ref:`Ref<java.sql.Ref>`



| **getStatement** () → :ref:`Statement<java.sql.Statement>`
|          returns :ref:`Statement<java.sql.Statement>`



| **getTime** (jdbcColumn) → :ref:`Time<java.sql.Time>`
|          int jdbcColumn
|          returns :ref:`Time<java.sql.Time>`



| **getTime** (jdbcColumn) → :ref:`Time<java.sql.Time>`
|          :ref:`String<java.lang.String>` jdbcColumn
|          returns :ref:`Time<java.sql.Time>`



| **getTime** (jdbcColumn, cal) → :ref:`Time<java.sql.Time>`
|          int jdbcColumn
|          :ref:`Calendar<java.util.Calendar>` cal
|          returns :ref:`Time<java.sql.Time>`



| **getTime** (jdbcColumn, cal) → :ref:`Time<java.sql.Time>`
|          :ref:`String<java.lang.String>` jdbcColumn
|          :ref:`Calendar<java.util.Calendar>` cal
|          returns :ref:`Time<java.sql.Time>`



| **getURL** (jdbcColumn) → :ref:`URL<java.net.URL>`
|          int jdbcColumn
|          returns :ref:`URL<java.net.URL>`



| **getURL** (jdbcColumn) → :ref:`URL<java.net.URL>`
|          :ref:`String<java.lang.String>` jdbcColumn
|          returns :ref:`URL<java.net.URL>`



| **getUnicodeStream** (jdbcColumn) → :ref:`InputStream<java.io.InputStream>`
|          int jdbcColumn
|          returns :ref:`InputStream<java.io.InputStream>`



| **getUnicodeStream** (jdbcColumn) → :ref:`InputStream<java.io.InputStream>`
|          :ref:`String<java.lang.String>` jdbcColumn
|          returns :ref:`InputStream<java.io.InputStream>`



| **getWarnings** () → :ref:`SQLWarning<java.sql.SQLWarning>`
|          returns :ref:`SQLWarning<java.sql.SQLWarning>`



| **moveToCurrentRow** ()


| **refreshRow** ()


| **relative** (rows) → boolean
|          int rows
|          returns boolean



| **rowDeleted** () → boolean
|          returns boolean



| **rowInserted** () → boolean
|          returns boolean



| **rowUpdated** () → boolean
|          returns boolean



| **updateArray** (jdbcColumn, x)
|          int jdbcColumn
|          :ref:`Array<java.sql.Array>` x


| **updateArray** (jdbcColumn, x)
|          :ref:`String<java.lang.String>` jdbcColumn
|          :ref:`Array<java.sql.Array>` x


| **getRowId** (columnIndex) → :ref:`RowId<java.sql.RowId>`
|          int columnIndex
|          returns :ref:`RowId<java.sql.RowId>`



| **getRowId** (columnLabel) → :ref:`RowId<java.sql.RowId>`
|          :ref:`String<java.lang.String>` columnLabel
|          returns :ref:`RowId<java.sql.RowId>`



| **updateRowId** (columnIndex, x)
|          int columnIndex
|          :ref:`RowId<java.sql.RowId>` x


| **updateRowId** (columnLabel, x)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`RowId<java.sql.RowId>` x


| **getHoldability** () → int
|          returns int



| **isClosed** () → boolean
|          returns boolean



| **updateNString** (columnIndex, nString)
|          int columnIndex
|          :ref:`String<java.lang.String>` nString


| **updateNString** (columnLabel, nString)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`String<java.lang.String>` nString


| **updateNClob** (columnIndex, nClob)
|          int columnIndex
|          :ref:`NClob<java.sql.NClob>` nClob


| **updateNClob** (columnLabel, nClob)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`NClob<java.sql.NClob>` nClob


| **getNClob** (columnIndex) → :ref:`NClob<java.sql.NClob>`
|          int columnIndex
|          returns :ref:`NClob<java.sql.NClob>`



| **getNClob** (columnLabel) → :ref:`NClob<java.sql.NClob>`
|          :ref:`String<java.lang.String>` columnLabel
|          returns :ref:`NClob<java.sql.NClob>`



| **getSQLXML** (columnIndex) → :ref:`SQLXML<java.sql.SQLXML>`
|          int columnIndex
|          returns :ref:`SQLXML<java.sql.SQLXML>`



| **getSQLXML** (columnLabel) → :ref:`SQLXML<java.sql.SQLXML>`
|          :ref:`String<java.lang.String>` columnLabel
|          returns :ref:`SQLXML<java.sql.SQLXML>`



| **updateSQLXML** (columnIndex, xmlObject)
|          int columnIndex
|          :ref:`SQLXML<java.sql.SQLXML>` xmlObject


| **updateSQLXML** (columnLabel, xmlObject)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`SQLXML<java.sql.SQLXML>` xmlObject


| **getNString** (columnIndex) → :ref:`String<java.lang.String>`
|          int columnIndex
|          returns :ref:`String<java.lang.String>`



| **getNString** (columnLabel) → :ref:`String<java.lang.String>`
|          :ref:`String<java.lang.String>` columnLabel
|          returns :ref:`String<java.lang.String>`



| **getNCharacterStream** (columnIndex) → :ref:`Reader<java.io.Reader>`
|          int columnIndex
|          returns :ref:`Reader<java.io.Reader>`



| **getNCharacterStream** (columnLabel) → :ref:`Reader<java.io.Reader>`
|          :ref:`String<java.lang.String>` columnLabel
|          returns :ref:`Reader<java.io.Reader>`



| **updateNCharacterStream** (columnIndex, x, length)
|          int columnIndex
|          :ref:`Reader<java.io.Reader>` x
|          long length


| **updateNCharacterStream** (columnLabel, reader, length)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`Reader<java.io.Reader>` reader
|          long length


| **updateAsciiStream** (columnIndex, x, length)
|          int columnIndex
|          :ref:`InputStream<java.io.InputStream>` x
|          long length


| **updateBinaryStream** (columnIndex, x, length)
|          int columnIndex
|          :ref:`InputStream<java.io.InputStream>` x
|          long length


| **updateCharacterStream** (columnIndex, x, length)
|          int columnIndex
|          :ref:`Reader<java.io.Reader>` x
|          long length


| **updateAsciiStream** (columnLabel, x, length)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`InputStream<java.io.InputStream>` x
|          long length


| **updateBinaryStream** (columnLabel, x, length)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`InputStream<java.io.InputStream>` x
|          long length


| **updateCharacterStream** (columnLabel, reader, length)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`Reader<java.io.Reader>` reader
|          long length


| **updateBlob** (columnIndex, inputStream, length)
|          int columnIndex
|          :ref:`InputStream<java.io.InputStream>` inputStream
|          long length


| **updateBlob** (columnLabel, inputStream, length)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`InputStream<java.io.InputStream>` inputStream
|          long length


| **updateClob** (columnIndex, reader, length)
|          int columnIndex
|          :ref:`Reader<java.io.Reader>` reader
|          long length


| **updateClob** (columnLabel, reader, length)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`Reader<java.io.Reader>` reader
|          long length


| **updateNClob** (columnIndex, reader, length)
|          int columnIndex
|          :ref:`Reader<java.io.Reader>` reader
|          long length


| **updateNClob** (columnLabel, reader, length)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`Reader<java.io.Reader>` reader
|          long length


| **updateNCharacterStream** (columnIndex, x)
|          int columnIndex
|          :ref:`Reader<java.io.Reader>` x


| **updateNCharacterStream** (columnLabel, reader)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`Reader<java.io.Reader>` reader


| **updateAsciiStream** (columnIndex, x)
|          int columnIndex
|          :ref:`InputStream<java.io.InputStream>` x


| **updateBinaryStream** (columnIndex, x)
|          int columnIndex
|          :ref:`InputStream<java.io.InputStream>` x


| **updateCharacterStream** (columnIndex, x)
|          int columnIndex
|          :ref:`Reader<java.io.Reader>` x


| **updateAsciiStream** (columnLabel, x)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`InputStream<java.io.InputStream>` x


| **updateBinaryStream** (columnLabel, x)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`InputStream<java.io.InputStream>` x


| **updateCharacterStream** (columnLabel, reader)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`Reader<java.io.Reader>` reader


| **updateBlob** (columnIndex, inputStream)
|          int columnIndex
|          :ref:`InputStream<java.io.InputStream>` inputStream


| **updateBlob** (columnLabel, inputStream)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`InputStream<java.io.InputStream>` inputStream


| **updateClob** (columnIndex, reader)
|          int columnIndex
|          :ref:`Reader<java.io.Reader>` reader


| **updateClob** (columnLabel, reader)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`Reader<java.io.Reader>` reader


| **updateNClob** (columnIndex, reader)
|          int columnIndex
|          :ref:`Reader<java.io.Reader>` reader


| **updateNClob** (columnLabel, reader)
|          :ref:`String<java.lang.String>` columnLabel
|          :ref:`Reader<java.io.Reader>` reader


| **updateAsciiStream** (jdbcColumn, x, length)
|          int jdbcColumn
|          :ref:`InputStream<java.io.InputStream>` x
|          int length


| **updateAsciiStream** (jdbcColumn, x, length)
|          :ref:`String<java.lang.String>` jdbcColumn
|          :ref:`InputStream<java.io.InputStream>` x
|          int length


| **updateBigDecimal** (jdbcColumn, x)
|          int jdbcColumn
|          :ref:`BigDecimal<java.math.BigDecimal>` x


| **updateBigDecimal** (jdbcColumn, x)
|          :ref:`String<java.lang.String>` jdbcColumn
|          :ref:`BigDecimal<java.math.BigDecimal>` x


| **updateBinaryStream** (jdbcColumn, x, length)
|          int jdbcColumn
|          :ref:`InputStream<java.io.InputStream>` x
|          int length


| **updateBinaryStream** (jdbcColumn, x, length)
|          :ref:`String<java.lang.String>` jdbcColumn
|          :ref:`InputStream<java.io.InputStream>` x
|          int length


| **updateBlob** (jdbcColumn, x)
|          int jdbcColumn
|          :ref:`Blob<java.sql.Blob>` x


| **updateBlob** (jdbcColumn, x)
|          :ref:`String<java.lang.String>` jdbcColumn
|          :ref:`Blob<java.sql.Blob>` x


| **updateBytes** (jdbcColumn, x)
|          int jdbcColumn
|          byte x


| **updateBytes** (jdbcColumn, x)
|          :ref:`String<java.lang.String>` jdbcColumn
|          byte x


| **updateCharacterStream** (jdbcColumn, x, length)
|          int jdbcColumn
|          :ref:`Reader<java.io.Reader>` x
|          int length


| **updateCharacterStream** (jdbcColumn, reader, length)
|          :ref:`String<java.lang.String>` jdbcColumn
|          :ref:`Reader<java.io.Reader>` reader
|          int length


| **updateClob** (jdbcColumn, x)
|          int jdbcColumn
|          :ref:`Clob<java.sql.Clob>` x


| **updateClob** (jdbcColumn, x)
|          :ref:`String<java.lang.String>` jdbcColumn
|          :ref:`Clob<java.sql.Clob>` x


| **updateDate** (jdbcColumn, x)
|          int jdbcColumn
|          :ref:`Date<java.sql.Date>` x


| **updateDate** (jdbcColumn, x)
|          :ref:`String<java.lang.String>` jdbcColumn
|          :ref:`Date<java.sql.Date>` x


| **updateObject** (jdbcColumn, x, scale)
|          int jdbcColumn
|          :ref:`Object<java.lang.Object>` x
|          int scale


| **updateObject** (jdbcColumn, x, scale)
|          :ref:`String<java.lang.String>` jdbcColumn
|          :ref:`Object<java.lang.Object>` x
|          int scale


| **updateRef** (jdbcColumn, x)
|          int jdbcColumn
|          :ref:`Ref<java.sql.Ref>` x


| **updateRef** (jdbcColumn, x)
|          :ref:`String<java.lang.String>` jdbcColumn
|          :ref:`Ref<java.sql.Ref>` x


| **updateRow** ()


| **updateTime** (jdbcColumn, x)
|          int jdbcColumn
|          :ref:`Time<java.sql.Time>` x


| **updateTime** (jdbcColumn, x)
|          :ref:`String<java.lang.String>` jdbcColumn
|          :ref:`Time<java.sql.Time>` x


| **updateTimestamp** (jdbcColumn, x)
|          int jdbcColumn
|          :ref:`Timestamp<java.sql.Timestamp>` x


| **updateTimestamp** (jdbcColumn, x)
|          :ref:`String<java.lang.String>` jdbcColumn
|          :ref:`Timestamp<java.sql.Timestamp>` x


| **wasNull** () → boolean
|          returns boolean



| **unwrap** (iface) → T
|          :ref:`Class<java.lang.Class>` iface
|          returns T



| **isWrapperFor** (iface) → boolean
|          :ref:`Class<java.lang.Class>` iface
|          returns boolean




..  _com.sqlsheet.stream.XlsStreamStatement:

=======================================================================
XlsStreamStatement
=======================================================================

*extends:* :ref:`Object<java.lang.Object>` *implements:* :ref:`Statement<java.sql.Statement>` *provides:* :ref:`XlsStreamPreparedStatement<com.sqlsheet.stream.XlsStreamPreparedStatement>` 

| SqlSheet implementation of java.sql.Statement which uses steaming over XLS

| **XlsStreamStatement** (c)
|          :ref:`XlsStreamConnection<com.sqlsheet.stream.XlsStreamConnection>` c


| **getConnection** () → :ref:`Connection<java.sql.Connection>`
|          returns :ref:`Connection<java.sql.Connection>`



| **close** ()


| **execute** (sql) → boolean
|          :ref:`String<java.lang.String>` sql
|          returns boolean



| **executeUpdate** (sql) → int
|          :ref:`String<java.lang.String>` sql
|          returns int



| **executeQuery** (query) → :ref:`ResultSet<java.sql.ResultSet>`
|          :ref:`String<java.lang.String>` query
|          returns :ref:`ResultSet<java.sql.ResultSet>`




                |          :ref:`String<java.lang.String>` sql

                |          returns :ref:`ParsedStatement<com.sqlsheet.parser.ParsedStatement>`


                
            
                |          :ref:`SelectStarStatement<com.sqlsheet.parser.SelectStarStatement>` sss

                |          returns :ref:`ResultSet<java.sql.ResultSet>`


                
            | **setEscapeProcessing** (p0)
|          boolean p0


| **setCursorName** (p0)
|          :ref:`String<java.lang.String>` p0


| **getMaxFieldSize** () → int
|          returns int



| **setMaxFieldSize** (p0)
|          int p0


| **getMaxRows** () → int
|          returns int



| **setMaxRows** (p0)
|          int p0


| **getQueryTimeout** () → int
|          returns int



| **setQueryTimeout** (p0)
|          int p0


| **getWarnings** () → :ref:`SQLWarning<java.sql.SQLWarning>`
|          returns :ref:`SQLWarning<java.sql.SQLWarning>`



| **getResultSet** () → :ref:`ResultSet<java.sql.ResultSet>`
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| **getUpdateCount** () → int
|          returns int



| **getMoreResults** () → boolean
|          returns boolean



| **getMoreResults** (current) → boolean
|          int current
|          returns boolean



| **getFetchDirection** () → int
|          returns int



| **setFetchDirection** (p0)
|          int p0


| **getFetchSize** () → int
|          returns int



| **setFetchSize** (p0)
|          int p0


| **getResultSetConcurrency** () → int
|          returns int



| **getResultSetType** () → int
|          returns int



| **cancel** ()


| **clearWarnings** ()


| **addBatch** (p0)
|          :ref:`String<java.lang.String>` p0


| **clearBatch** ()


| **executeBatch** () → int
|          returns int



| **getGeneratedKeys** () → :ref:`ResultSet<java.sql.ResultSet>`
|          returns :ref:`ResultSet<java.sql.ResultSet>`



| **executeUpdate** (sql, autoGeneratedKeys) → int
|          :ref:`String<java.lang.String>` sql
|          int autoGeneratedKeys
|          returns int



| **executeUpdate** (sql, columnIndexes) → int
|          :ref:`String<java.lang.String>` sql
|          int columnIndexes
|          returns int



| **executeUpdate** (sql, columnNames) → int
|          :ref:`String<java.lang.String>` sql
|          :ref:`String<java.lang.String>` columnNames
|          returns int



| **execute** (sql, autoGeneratedKeys) → boolean
|          :ref:`String<java.lang.String>` sql
|          int autoGeneratedKeys
|          returns boolean



| **execute** (sql, columnIndexes) → boolean
|          :ref:`String<java.lang.String>` sql
|          int columnIndexes
|          returns boolean



| **execute** (sql, columnNames) → boolean
|          :ref:`String<java.lang.String>` sql
|          :ref:`String<java.lang.String>` columnNames
|          returns boolean



| **getResultSetHoldability** () → int
|          returns int




                |          returns void


                
            | **isClosed** () → boolean
|          returns boolean



| **isPoolable** () → boolean
|          returns boolean



| **setPoolable** (poolable)
|          boolean poolable


| **isWrapperFor** (iface) → boolean
|          :ref:`Class<java.lang.Class>` iface
|          returns boolean



| **unwrap** (iface) → T
|          :ref:`Class<java.lang.Class>` iface
|          returns T



| **closeOnCompletion** ()


| **isCloseOnCompletion** () → boolean
|          returns boolean




..  _com.sqlsheet.stream.XlsStreamingResultSetMetaData:

=======================================================================
XlsStreamingResultSetMetaData
=======================================================================

*extends:* :ref:`Object<java.lang.Object>` *implements:* :ref:`ResultSetMetaData<java.sql.ResultSetMetaData>` 

| SqlSheet implementation of java.sql.ResultSetMetaData.

| **XlsStreamingResultSetMetaData** (sheet, resultset, firstSheetRowOffset, firstSheetColOffset)
|          Sheet sheet
|          :ref:`XlsStreamResultSet<com.sqlsheet.stream.XlsStreamResultSet>` resultset
|          int firstSheetRowOffset
|          int firstSheetColOffset


| **getColumnCount** () → int
|          returns int



| **getColumnLabel** (jdbcCol) → :ref:`String<java.lang.String>`
|          int jdbcCol
|          returns :ref:`String<java.lang.String>`



| **getColumnName** (jdbcCol) → :ref:`String<java.lang.String>`
|          int jdbcCol
|          returns :ref:`String<java.lang.String>`



| **getCatalogName** (arg0) → :ref:`String<java.lang.String>`
|          int arg0
|          returns :ref:`String<java.lang.String>`



| **getColumnClassName** (jdbcColumn) → :ref:`String<java.lang.String>`
|          int jdbcColumn
|          returns :ref:`String<java.lang.String>`



| **getColumnDisplaySize** (arg0) → int
|          int arg0
|          returns int



| **getColumnType** (jdbcColumn) → int
|          int jdbcColumn
|          returns int



| **getColumnTypeName** (jdbcColumn) → :ref:`String<java.lang.String>`
|          int jdbcColumn
|          returns :ref:`String<java.lang.String>`



| **getPrecision** (arg0) → int
|          int arg0
|          returns int



| **getScale** (arg0) → int
|          int arg0
|          returns int



| **getSchemaName** (arg0) → :ref:`String<java.lang.String>`
|          int arg0
|          returns :ref:`String<java.lang.String>`



| **getTableName** (arg0) → :ref:`String<java.lang.String>`
|          int arg0
|          returns :ref:`String<java.lang.String>`



| **isAutoIncrement** (arg0) → boolean
|          int arg0
|          returns boolean



| **isCaseSensitive** (arg0) → boolean
|          int arg0
|          returns boolean



| **isCurrency** (arg0) → boolean
|          int arg0
|          returns boolean



| **isDefinitelyWritable** (arg0) → boolean
|          int arg0
|          returns boolean



| **isNullable** (arg0) → int
|          int arg0
|          returns int



| **isReadOnly** (arg0) → boolean
|          int arg0
|          returns boolean



| **isSearchable** (arg0) → boolean
|          int arg0
|          returns boolean



| **isSigned** (arg0) → boolean
|          int arg0
|          returns boolean



| **isWritable** (arg0) → boolean
|          int arg0
|          returns boolean



| **isWrapperFor** (iface) → boolean
|          :ref:`Class<java.lang.Class>` iface
|          returns boolean



| **unwrap** (iface) → T
|          :ref:`Class<java.lang.Class>` iface
|          returns T




..  _com.sqlsheet.stream.XlsxSheetIterator:

=======================================================================
XlsxSheetIterator
=======================================================================

*extends:* :ref:`AbstractXlsSheetIterator<com.sqlsheet.stream.AbstractXlsSheetIterator>` 

| Streaming iterator over XLSX files Derived from: http://svn.apache.org/repos/asf/poi/trunk/src/examples/src/org/apache/poi/xssf/eventusermodel/XLSX2CSV.java

| **XlsxSheetIterator** (filename, sheetName)
|          :ref:`URL<java.net.URL>` filename
|          :ref:`String<java.lang.String>` sheetName



                |          returns void


                
                
            
                |          returns void


                
                
            
                |          returns void


                
                
            | **processNextEvent** ()
| Parses and shows the content of one sheet using the specified styles and shared-strings tables.



..  _net.pcal.sqlsheet:
***********************************************************************
heet
***********************************************************************

..  _net.pcal.sqlsheet.XlsDriver:

=======================================================================
XlsDriver
=======================================================================

*extends:* :ref:`XlsDriver<com.sqlsheet.XlsDriver>` 

| **XlsDriver** ()


