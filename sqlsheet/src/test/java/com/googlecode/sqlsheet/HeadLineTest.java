package com.googlecode.sqlsheet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Assert;
import org.junit.Test;

/**
 * @since 6.6
 * 
 * @author Klaus Hauschild
 */
public class HeadLineTest {

  @Test
  public void headLineTest() throws Exception {
    Class.forName("com.googlecode.sqlsheet.Driver");
    final Connection connection = DriverManager.getConnection("jdbc:xls:file:" + ClassLoader.getSystemResource("headline.xlsx").getFile()
        + "?headLine=5");
    final Statement statement = connection.createStatement();
    final ResultSet resultSet = statement.executeQuery("SELECT * FROM headline");
    int line = 1;
    while (resultSet.next()) {
      Assert.assertEquals(line * 1, resultSet.getInt("A"));
      Assert.assertEquals(line * 2, resultSet.getInt("B"));
      Assert.assertEquals(line * 3, resultSet.getInt("C"));
      Assert.assertEquals(line * 4, resultSet.getInt("D"));
      Assert.assertEquals(line * 5, resultSet.getInt("E"));
      line++;
    }
    Assert.assertEquals(4, line);
    connection.close();
  }

}
