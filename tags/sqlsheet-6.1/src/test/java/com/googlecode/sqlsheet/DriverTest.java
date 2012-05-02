package com.googlecode.sqlsheet;

import org.junit.Assert;
import org.junit.Test;

import java.sql.*;
import java.util.Date;

public class DriverTest {

    @Test
    public void testXlsSheetNameQuotes() throws Exception {
        Class.forName("com.googlecode.sqlsheet.Driver");
        Connection conn = DriverManager.getConnection("jdbc:xls:file:" + ClassLoader.getSystemResource("test.xls").getFile());
        processBaseResultset(conn,"SELECT * FROM \"2009\"");
    }

    @Test
    public void testXlsSheetNameNoQuotes() throws Exception {
        Class.forName("com.googlecode.sqlsheet.Driver");
        Connection conn = DriverManager.getConnection("jdbc:xls:file:" + ClassLoader.getSystemResource("test.xls").getFile());
        processBaseResultset(conn,"SELECT * FROM SHEET1");
    }

    @Test
    public void testXlsStreamSheetNameQuotes() throws Exception {
        Class.forName("com.googlecode.sqlsheet.Driver");
        Connection conn = DriverManager.getConnection("jdbc:xls:file:" + ClassLoader.getSystemResource("test.xls").getFile()+"?readStreaming=true");
        processBaseResultset(conn,"SELECT * FROM \"2009\"");
    }

    @Test
    public void testXlsStreamSheetNameNoQuotes() throws Exception {
        Class.forName("com.googlecode.sqlsheet.Driver");
        Connection conn = DriverManager.getConnection("jdbc:xls:file:" + ClassLoader.getSystemResource("test.xls").getFile()+"?readStreaming=true");
        processBaseResultset(conn,"SELECT * FROM SHEET1");
    }


    @Test
    public void testXlsxSheetNameQuotes() throws Exception {
        Class.forName("com.googlecode.sqlsheet.Driver");
        Connection conn = DriverManager.getConnection("jdbc:xls:file:" + ClassLoader.getSystemResource("test.xlsx").getFile());
        processBaseResultset(conn,"SELECT * FROM \"2009\"");
    }


    @Test
    public void testXlsxSheetNameNoQuotes() throws Exception {
        Class.forName("com.googlecode.sqlsheet.Driver");
        Connection conn = DriverManager.getConnection("jdbc:xls:file:" + ClassLoader.getSystemResource("test.xlsx").getFile());
        processBaseResultset(conn,"SELECT * FROM SHEET1");
    }


    @Test
    public void testXlsxStreamSheetNameQuotes() throws Exception {
        Class.forName("com.googlecode.sqlsheet.Driver");
        Connection conn = DriverManager.getConnection("jdbc:xls:file:" + ClassLoader.getSystemResource("test.xlsx").getFile()+"?readStreaming=true");
        processBaseResultset(conn,"SELECT * FROM \"2009\"");
    }


    @Test
    public void testXlsxStreamSheetNameNoQuotes() throws Exception {
        Class.forName("com.googlecode.sqlsheet.Driver");
        Connection conn = DriverManager.getConnection("jdbc:xls:file:" + ClassLoader.getSystemResource("test.xlsx").getFile()+"?readStreaming=true");
        processBaseResultset(conn,"SELECT * FROM SHEET1");
    }


    private void processBaseResultset(Connection conn,String sql) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet results = stmt.executeQuery(sql);
        Assert.assertEquals(results.getMetaData().getColumnCount(), 3L);
        Long count = 0L;
        while (results.next()) {
            Assert.assertEquals(Double.class, results.getObject(1).getClass());
            Assert.assertEquals(String.class, results.getObject(2).getClass());
            Assert.assertEquals(Date.class, results.getObject(3).getClass());
            count++;
        }
        Assert.assertEquals(count.longValue(), 3L);
        results.close();
        stmt.close();
        conn.close();
    }

}
