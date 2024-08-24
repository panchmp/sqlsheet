package com.sqlsheet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SheetAccessTest {

    @BeforeAll
    public static void loadDriverClass() throws ClassNotFoundException {
        Class.forName("com.sqlsheet.XlsDriver");
    }

    @Test
    void accessSheetByIndex() throws SQLException {
        String urlStr = "jdbc:xls:classpath:/bug9.xlsx?readStreaming=false";
        String sqlStr = "SELECT * from \"sheet[0]\"";

        try (final Connection connection = DriverManager.getConnection(urlStr);
                final Statement statement = connection.createStatement();
                final ResultSet resultSet = statement.executeQuery(sqlStr)) {
            Assertions.assertTrue(resultSet.next());
            Assertions.assertEquals("9", resultSet.getString("BUG9"));
        }
    }

    @Test
    void accessSheetByIndexUsingStreaming() throws SQLException {
        String urlStr = "jdbc:xls:classpath:/bug9.xlsx?readStreaming=true";
        String sqlStr = "SELECT * from \"sheet[0]\"";

        try (final Connection connection = DriverManager.getConnection(urlStr);
                final Statement statement = connection.createStatement();
                final ResultSet resultSet = statement.executeQuery(sqlStr)) {
            Assertions.assertTrue(resultSet.next());
            Assertions.assertEquals("9", resultSet.getString("BUG9"));
        }
    }
}
