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

public class CreateTableTest {

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
                .getConnection("jdbc:xls:file:"
                        + ClassLoader.getSystemResource("create_table_test.xlsx").getFile());
    }

    @Test
    public void test_quoted_column_name() throws Exception {
        final Statement stmt = connection.createStatement();
        stmt.executeUpdate("CREATE TABLE \"table\" (\"column\" VARCHAR)");
        final ResultSet trs = stmt.executeQuery("SELECT * FROM \"table\"");
        String columnName = trs.getMetaData().getColumnName(1);
        Assertions.assertEquals("column", columnName);
        stmt.execute("DROP TABLE \"table\"");
        stmt.close();
        trs.close();
        connection.close();
    }

    @Test
    public void test_truncated_table_name() throws Exception {
        final Statement stmt = connection.createStatement();
        stmt.executeUpdate(
                "CREATE TABLE \"table with very very very very long name\" (\"column\" VARCHAR)");
        final ResultSet trs =
                stmt.executeQuery("SELECT * FROM \"table with very very very very long name\"");
        String columnName = trs.getMetaData().getColumnName(1);
        Assertions.assertEquals("column", columnName);
        stmt.execute("DROP TABLE \"table with very very very very long name\"");
        stmt.close();
        trs.close();
        connection.close();
    }

}
