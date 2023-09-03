package com.sqlsheet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Klaus Hauschild
 */
public class Bug9Test {

    @BeforeAll
    public static void loadDriverClass() throws ClassNotFoundException {
        Class.forName("com.sqlsheet.XlsDriver");
    }

    @Test
    public void testWithoutStreaming() throws Exception {
        final Connection connection = DriverManager
                .getConnection(
                        "jdbc:xls:file:" + ClassLoader.getSystemResource("bug9.xlsx").getFile()
                                + "?readStreaming=false");
        final Statement statement = connection.createStatement();
        final ResultSet resultSet = statement.executeQuery("SELECT * FROM bug9");
        Assertions.assertTrue(resultSet.next());
        Assertions.assertEquals("9.0", resultSet.getString("BUG9"));
        connection.close();
    }

    @Test
    public void testWithStreaming() throws Exception {
        final Connection connection = DriverManager
                .getConnection(
                        "jdbc:xls:file:" + ClassLoader.getSystemResource("bug9.xlsx").getFile()
                                + "?readStreaming=true");
        final Statement statement = connection.createStatement();
        final ResultSet resultSet = statement.executeQuery("SELECT * FROM bug9");
        Assertions.assertTrue(resultSet.next());
        Assertions.assertEquals("9", resultSet.getString("BUG9"));
        connection.close();
    }

}
