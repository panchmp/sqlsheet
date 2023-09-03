package com.sqlsheet;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Klaus Hauschild
 */
public class DubbleHeaderNameTest {

    private Connection connection;

    @BeforeAll
    public static void loadDriverClass() throws ClassNotFoundException {
        Class.forName("com.sqlsheet.XlsDriver");
    }

    @AfterEach
    public void after() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    @BeforeEach
    public void before() throws Exception {
        connection = DriverManager.getConnection(
                "jdbc:xls:file:"
                        + ClassLoader.getSystemResource("duplicateHeaderName.xlsx").getFile()
                        + "?readStreaming=false");
    }

    @Test
    public void testDuplicate() throws Exception {
        final Statement stmt = connection.createStatement();
        final ResultSet results = stmt.executeQuery("SELECT * FROM \"duplicate\""); // Duplicate is
        // a keyword
        Assertions.assertEquals("A_1",
                results.getMetaData().getColumnName(2), "The column name must A_1");
        Assertions.assertNotEquals(results.getMetaData().getColumnName(2),
                results.getMetaData().getColumnName(1), "The column names must not be the same");

    }

}
