package com.sqlsheet;

import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;

/**
 * @since 6.6
 * @author Klaus Hauschild
 */
public class HeadLineTest {

  @BeforeClass
  public static void loadDriverClass() throws ClassNotFoundException {
    Class.forName("com.sqlsheet.XlsDriver");
  }

  @Test
  public void headLineTest() throws Exception {
    final Connection connection = DriverManager
                     .getConnection(
                             "jdbc:xls:file:" + ClassLoader.getSystemResource("headline.xlsx").getFile() + "?headLine=5");
    final Statement statement = connection.createStatement();
    final ResultSet resultSet = statement.executeQuery("SELECT * FROM headline");
    int line = 1;
    while (resultSet.next()) {
      assertEquals(line * 1, resultSet.getInt("A"));
      assertEquals(line * 2, resultSet.getInt("B"));
      assertEquals(line * 3, resultSet.getInt("C"));
      assertEquals(line * 4, resultSet.getInt("D"));
      assertEquals(line * 5, resultSet.getInt("E"));
      line++;
    }
    assertEquals(4, line);
    connection.close();
  }

}
