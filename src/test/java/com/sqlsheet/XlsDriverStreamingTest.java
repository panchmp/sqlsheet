package com.sqlsheet;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

public class XlsDriverStreamingTest {

    @BeforeAll
    public static void loadDriverClass() throws ClassNotFoundException {
        Class.forName("com.sqlsheet.XlsDriver");
    }

    @Test
    public void testConnect() throws Exception {
        Connection conn = DriverManager.getConnection(
                "jdbc:xls:file:" + ClassLoader.getSystemResource("test.xls").getFile());
        Statement stmt = conn.createStatement();
        ResultSet results = stmt.executeQuery("SELECT * FROM \"2009\"");
        Assertions.assertEquals(results.getMetaData().getColumnCount(), 3L);
        long count = 0L;
        while (results.next()) {
            Assertions.assertEquals(Double.class, results.getObject(1).getClass());
            Assertions.assertEquals(String.class, results.getObject(2).getClass());
            Assertions.assertEquals(java.sql.Date.class, results.getObject(3).getClass());
            count++;
        }
        Assertions.assertEquals(count, 3L);
        results.close();
        stmt.close();
        conn.close();
    }

    @Test
    public void testXlsConnectReadStream() throws Exception {
        Assertions.assertThrows(java.sql.SQLException.class, new Executable() {
                    @Override
                    public void execute() throws Throwable {
                        Connection conn = DriverManager
                                .getConnection(
                                        "jdbc:xls:file:" + ClassLoader.getSystemResource("test.xls").getFile()
                                                + "?readStreaming=true");
                    }
                }
        );
    }

    @Test
    public void testXlsxConnectReadStream() throws Exception {
        Connection conn = DriverManager
                .getConnection(
                        "jdbc:xls:file:" + ClassLoader.getSystemResource("test.xlsx").getFile()
                                + "?readStreaming=true");
        Statement stmt = conn.createStatement();
        ResultSet results = stmt.executeQuery("SELECT * FROM \"2009\"");
        Assertions.assertEquals(3L, results.getMetaData().getColumnCount());
        long count = 0L;
        while (results.next()) {
            Assertions.assertEquals(Double.class, results.getObject(1).getClass());
            Assertions.assertEquals(String.class, results.getObject(2).getClass());
            Assertions.assertEquals(java.sql.Date.class, results.getObject(3).getClass());
            count++;
        }
        Assertions.assertEquals(3L, count);
        results.close();
        stmt.close();
        conn.close();
    }

    @Test
    public void testXlsxConnectReadStreamBigTable() throws Exception {
        Connection conn = DriverManager.getConnection(
                "jdbc:xls:file:" + ClassLoader.getSystemResource("big-grid.xlsx").getFile()
                        + "?readStreaming=true");
        Statement stmt = conn.createStatement();
        ResultSet results = stmt.executeQuery("SELECT * FROM \"Big Grid\"");
        Assertions.assertEquals(20L, results.getMetaData().getColumnCount());
        long count = 0L;
        while (results.next()) {
            Assertions.assertEquals(String.class, results.getObject(1).getClass());
            Assertions.assertEquals(Double.class, results.getObject(2).getClass());
            Assertions.assertEquals(Double.class, results.getObject(3).getClass());
            Assertions.assertEquals(Double.class, results.getObject(4).getClass());
            Assertions.assertEquals(java.sql.Date.class, results.getObject(5).getClass());
            count++;
        }
        Assertions.assertEquals(65535L, count);
        results.close();
        stmt.close();
        conn.close();
    }

    @Test
    @Disabled
    public void testXlsConnectWriteStream() throws Exception {
        File test = File.createTempFile("testXlsConnectWriteStream", ".xlsx");
        FileUtils.copyFile(new File(ClassLoader.getSystemResource("test.xlsx").getFile()), test);

        Connection writeConnection = DriverManager
                .getConnection("jdbc:xls:file:" + test.getPath() + "?writeStreaming=true");
        Statement writeStatement = writeConnection.createStatement();
        writeStatement
                .executeUpdate("CREATE TABLE TEST_INSERT(COL1 INT, COL2 VARCHAR(255), COL3 DATE)");
        writeStatement.close();

        PreparedStatement writeStatement2 = writeConnection
                .prepareStatement("INSERT INTO TEST_INSERT(COL1, COL2, COL3) VALUES(?,?,?)");
        for (int i = 0; i < 3; i++) {
            writeStatement2.setDouble(1, i);
            writeStatement2.setString(2, "Row" + i);
            writeStatement2.setDate(3, new java.sql.Date(new Date().getTime()));
            writeStatement2.execute();
        }
        writeStatement2.close();
        writeConnection.close();

        Connection readConnection = DriverManager
                .getConnection("jdbc:xls:file:" + test.getPath() + "?readStreaming=true");
        Statement readStatementstmt = readConnection.createStatement();
        ResultSet results = readStatementstmt.executeQuery("SELECT * FROM TEST_INSERT");
        Assertions.assertEquals(3L, results.getMetaData().getColumnCount());
        long count = 0L;
        while (results.next()) {
            Assertions.assertEquals(Double.class, results.getObject(1).getClass());
            Assertions.assertEquals(String.class, results.getObject(2).getClass());
            Assertions.assertEquals(java.sql.Date.class, results.getObject(3).getClass());
            count++;
        }
        Assertions.assertEquals(3L, count);
        results.close();
        readStatementstmt.close();
        readConnection.close();

        Connection readConnection2 = DriverManager.getConnection("jdbc:xls:file:" + test.getPath());
        Statement readStatementstmt2 = readConnection.createStatement();
        ResultSet results2 = readStatementstmt.executeQuery("SELECT * FROM TEST_INSERT");
        Assertions.assertEquals(3L, results2.getMetaData().getColumnCount());
        long count2 = 0L;
        while (results2.next()) {
            Assertions.assertEquals(Double.class, results2.getObject(1).getClass());
            Assertions.assertEquals(String.class, results2.getObject(2).getClass());
            Assertions.assertEquals(Date.class, results2.getObject(3).getClass());
            count2++;
        }
        Assertions.assertEquals(3L, count2);
        results2.close();
        readStatementstmt2.close();
        readConnection2.close();
    }

}
