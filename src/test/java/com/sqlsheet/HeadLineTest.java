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
 * @since 6.6
 */
public class HeadLineTest {

    @BeforeAll
    public static void loadDriverClass() throws ClassNotFoundException {
        Class.forName("com.sqlsheet.XlsDriver");
    }

    @Test
    public void headLineTest() throws Exception {
        final Connection connection = DriverManager
                .getConnection(
                        "jdbc:xls:classpath:/headline.xlsx?headLine=5");
        final Statement statement = connection.createStatement();
        final ResultSet resultSet = statement.executeQuery("SELECT * FROM headline");
        int line = 1;
        while (resultSet.next()) {
            Assertions.assertEquals(line, resultSet.getInt("A"));
            Assertions.assertEquals(line * 2, resultSet.getInt("B"));
            Assertions.assertEquals(line * 3, resultSet.getInt("C"));
            Assertions.assertEquals(line * 4, resultSet.getInt("D"));
            Assertions.assertEquals(line * 5, resultSet.getInt("E"));
            line++;
        }
        Assertions.assertEquals(4, line);
        connection.close();
    }

}
