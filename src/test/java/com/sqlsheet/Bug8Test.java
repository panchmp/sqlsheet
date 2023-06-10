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
public class Bug8Test {

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
        connection = DriverManager
                .getConnection(
                        "jdbc:xls:file:" + ClassLoader.getSystemResource("bug8.xlsx").getFile()
                                + "?readStreaming=true");
    }

    @Test
    public void testbug8() throws Exception {
        final Statement stmt = connection.createStatement();
        final ResultSet results = stmt.executeQuery("SELECT * FROM bug8");
        Assertions.assertTrue(results.next());
        Assertions.assertNull(results.getString("PARENT"));
        Assertions.assertEquals("Foo", results.getString("CHILD"));
        Assertions.assertEquals(1, results.getInt("MIN"));
        Assertions.assertEquals(1, results.getInt("MAX"));
        Assertions.assertTrue(results.next());
        Assertions.assertEquals("Foo", results.getString("PARENT"));
        Assertions.assertEquals("Bar", results.getString("CHILD"));
        Assertions.assertEquals(0, results.getInt("MIN"));
        Assertions.assertEquals(3, results.getInt("MAX"));
    }

}
