package com.sqlsheet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.DriverManager;

/**
 * @since 6.6
 * @author Klaus Hauschild
 */
public class HeadLineFirstColTest {

  @BeforeAll
  public static void loadDriverClass() throws ClassNotFoundException {
    Class.forName("com.sqlsheet.XlsDriver");
  }

  @Test
  public void headLineTest() throws Exception {
    final XlsConnection connection = (XlsConnection) DriverManager
                        .getConnection("jdbc:xls:file:" + ClassLoader.getSystemResource("headline_firstcol.xlsx").
                                       getFile() + "?headLine=3&firstColumn=1");
    final XlsStatement statement = (XlsStatement) connection.createStatement();
    final XlsResultSet resultSet = (XlsResultSet) statement.executeQuery("SELECT * FROM data");
    if (resultSet.next()) {
      Assertions.assertEquals("loan_1", resultSet.getString("id_instrument"));
      Assertions.assertEquals("loan", resultSet.getString("id_instrument_type"));
    }

    if (resultSet.next()) {
      Assertions.assertEquals("loan_2", resultSet.getString("id_instrument"));
      Assertions.assertEquals("loan", resultSet.getString("id_instrument_type"));
    }

    if (resultSet.next()) {
      Assertions.assertEquals("loan_3", resultSet.getString("id_instrument"));
      Assertions.assertEquals("loan", resultSet.getString("id_instrument_type"));
    }

    if (resultSet.next()) {
      Assertions.assertEquals("loan_4", resultSet.getString("id_instrument"));
      Assertions.assertEquals("loan", resultSet.getString("id_instrument_type"));
    }

    if (resultSet.next()) {
      Assertions.assertEquals("loan_5", resultSet.getString("id_instrument"));
      Assertions.assertEquals("loan", resultSet.getString("id_instrument_type"));
    }

    connection.close();
  }

}
