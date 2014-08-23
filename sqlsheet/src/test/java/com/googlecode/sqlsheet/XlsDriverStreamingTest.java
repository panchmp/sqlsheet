package com.googlecode.sqlsheet;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.sql.*;
import java.util.Date;

public class XlsDriverStreamingTest {

    @Test
    public void testConnect() throws Exception {
        Class.forName("com.googlecode.sqlsheet.Driver");
        Connection conn = DriverManager.getConnection("jdbc:xls:file:" + ClassLoader.getSystemResource("test.xls").getFile());
        Statement stmt = conn.createStatement();
        ResultSet results = stmt.executeQuery("SELECT * FROM \"2009\"");
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

    @Test
    public void testXlsConnectReadStream() throws Exception {
        Class.forName("com.googlecode.sqlsheet.Driver");
        Connection conn = DriverManager.getConnection("jdbc:xls:file:" + ClassLoader.getSystemResource("test.xls").getFile() + "?readStreaming=true");
        Statement stmt = conn.createStatement();
        ResultSet results = stmt.executeQuery("SELECT * FROM \"2009\"");
        Assert.assertEquals(3L, results.getMetaData().getColumnCount());
        Long count = 0L;
        while (results.next()) {
            Assert.assertEquals(Double.class, results.getObject(1).getClass());
            Assert.assertEquals(String.class, results.getObject(2).getClass());
            Assert.assertEquals(Date.class, results.getObject(3).getClass());
            count++;
        }
        Assert.assertEquals(3L, count.longValue());
        results.close();
        stmt.close();
        conn.close();
    }

    @Test
    public void testXlsxConnectReadStream() throws Exception {
        Class.forName("com.googlecode.sqlsheet.Driver");
        Connection conn = DriverManager.getConnection("jdbc:xls:file:" + ClassLoader.getSystemResource("test.xlsx").getFile() + "?readStreaming=true");
        Statement stmt = conn.createStatement();
        ResultSet results = stmt.executeQuery("SELECT * FROM \"2009\"");
        Assert.assertEquals(3L, results.getMetaData().getColumnCount());
        Long count = 0L;
        while (results.next()) {
            Assert.assertEquals(Double.class, results.getObject(1).getClass());
            Assert.assertEquals(String.class, results.getObject(2).getClass());
            Assert.assertEquals(Date.class, results.getObject(3).getClass());
            count++;
        }
        Assert.assertEquals(3L, count.longValue());
        results.close();
        stmt.close();
        conn.close();
    }

    @Test
    public void testXlsConnectReadStreamBigTable() throws Exception {
        Class.forName("com.googlecode.sqlsheet.Driver");
        Connection conn = DriverManager.getConnection("jdbc:xls:file:" + ClassLoader.getSystemResource("big-grid.xls").getFile() + "?readStreaming=true");
        Statement stmt = conn.createStatement();
        ResultSet results = stmt.executeQuery("SELECT * FROM \"Big Grid\"");
        Assert.assertEquals(20L, results.getMetaData().getColumnCount());
        Long count = 0L;
        while (results.next()) {
            Assert.assertEquals(String.class, results.getObject(1).getClass());
            Assert.assertEquals(Double.class, results.getObject(2).getClass());
            Assert.assertEquals(Double.class, results.getObject(3).getClass());
            Assert.assertEquals(Double.class, results.getObject(4).getClass());
            Assert.assertEquals(Date.class, results.getObject(5).getClass());
            count++;
        }
        Assert.assertEquals(65535L, count.longValue());
        results.close();
        stmt.close();
        conn.close();
    }

    @Test
    public void testXlsxConnectReadStreamBigTable() throws Exception {
        Class.forName("com.googlecode.sqlsheet.Driver");
        Connection conn = DriverManager.getConnection("jdbc:xls:file:" + ClassLoader.getSystemResource("big-grid.xlsx").getFile() + "?readStreaming=true");
        Statement stmt = conn.createStatement();
        ResultSet results = stmt.executeQuery("SELECT * FROM \"Big Grid\"");
        Assert.assertEquals(20L, results.getMetaData().getColumnCount());
        Long count = 0L;
        while (results.next()) {
            Assert.assertEquals(String.class, results.getObject(1).getClass());
            Assert.assertEquals(Double.class, results.getObject(2).getClass());
            Assert.assertEquals(Double.class, results.getObject(3).getClass());
            Assert.assertEquals(Double.class, results.getObject(4).getClass());
            Assert.assertEquals(Date.class, results.getObject(5).getClass());
            count++;
        }
        Assert.assertEquals(65535L, count.longValue());
        results.close();
        stmt.close();
        conn.close();
    }

    @Test
    public void testXlsConnectWriteStream() throws Exception {
        Class.forName("com.googlecode.sqlsheet.Driver");
        File test = File.createTempFile("testXlsConnectWriteStream",".xlsx");
        FileUtils.copyFile(new File(ClassLoader.getSystemResource("test.xlsx").getFile()),test);

        Connection writeConnection = DriverManager.getConnection("jdbc:xls:file:" + test.getPath() + "?writeStreaming=true");
        Statement writeStatement = writeConnection.createStatement();
        writeStatement.executeUpdate("CREATE TABLE TEST_INSERT(COL1 INT, COL2 VARCHAR(255), COL3 DATE)");
        writeStatement.close();

        PreparedStatement writeStatement2 = writeConnection.prepareStatement("INSERT INTO TEST_INSERT(COL1, COL2, COL3) VALUES(?,?,?)");
        for(int i = 0; i<3;i++){
            writeStatement2.setDouble(1, i);
            writeStatement2.setString(2, "Row" + i);
            writeStatement2.setDate(3, new java.sql.Date(new Date().getTime()));
            writeStatement2.execute();
        }
        writeStatement2.close();
        writeConnection.close();

        Connection readConnection = DriverManager.getConnection("jdbc:xls:file:" + test.getPath() + "?readStreaming=true");
        Statement readStatementstmt = readConnection.createStatement();
        ResultSet results = readStatementstmt.executeQuery("SELECT * FROM TEST_INSERT");
        Assert.assertEquals(3L, results.getMetaData().getColumnCount());
        Long count = 0L;
        while (results.next()) {
            Assert.assertEquals(Double.class, results.getObject(1).getClass());
            Assert.assertEquals(String.class, results.getObject(2).getClass());
            Assert.assertEquals(Date.class, results.getObject(3).getClass());
            count++;
        }
        Assert.assertEquals(3L, count.longValue());
        results.close();
        readStatementstmt.close();
        readConnection.close();

        Connection readConnection2 = DriverManager.getConnection("jdbc:xls:file:" + test.getPath());
        Statement readStatementstmt2 = readConnection.createStatement();
        ResultSet results2 = readStatementstmt.executeQuery("SELECT * FROM TEST_INSERT");
        Assert.assertEquals(3L, results2.getMetaData().getColumnCount());
        Long count2 = 0L;
        while (results2.next()) {
            Assert.assertEquals(Double.class, results2.getObject(1).getClass());
            Assert.assertEquals(String.class, results2.getObject(2).getClass());
            Assert.assertEquals(Date.class, results2.getObject(3).getClass());
            count2++;
        }
        Assert.assertEquals(3L, count2.longValue());
        results2.close();
        readStatementstmt2.close();
        readConnection2.close();
    }


}
