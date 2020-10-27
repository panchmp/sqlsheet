/*
 * Copyright 2020 Andreas Reichel <andreas@manticore-projects.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sqlsheet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.util.Locale;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.DateFormatConverter;
import org.junit.*;

/**
 *
 * @author Andreas Reichel <andreas@manticore-projects.com>
 */
public class Issue13_NPEOnEmptyCells {

  public static final int DEFAULT_HEADLINE = 3;
  public static final int DEFAULT_FIRST_COL = 1;

  private final Connection conn;
  private final DatabaseMetaData metaData;

  private final String[] columnNames = {"String", "Date", "Boolean", "Double", "String", "Date", "Boolean", "Double"};
  private final Object[][] cellValues = {
    {"Row1", new java.util.Date(), Boolean.TRUE, 1, "Test", new java.util.Date(), Boolean.FALSE, Math.PI},
    {"Row2", new java.util.Date(), Boolean.FALSE, 2, "Test", new java.util.Date(), Boolean.TRUE, 2 * Math.PI},
    {"Row3", new java.util.Date(), Boolean.TRUE, 3, "Test", new java.util.Date(), Boolean.FALSE, 3 * Math.PI}
  };

  public Issue13_NPEOnEmptyCells() throws SQLException, IOException, ClassNotFoundException {
    Workbook workBook = WorkbookFactory.create(true);
    Sheet sheet = workBook.createSheet("TestSheet1");
    int r = DEFAULT_HEADLINE - 1;
    Row row = sheet.createRow(r);
    for (int c = DEFAULT_FIRST_COL; c < columnNames.length + DEFAULT_FIRST_COL; c++) {
      Cell cell = row.createCell(c);
      cell.setCellValue(columnNames[c - DEFAULT_FIRST_COL] + c);
    }

    DataFormat dateFormat = workBook.createDataFormat();
    short dateFormatIndex =
          dateFormat.getFormat(DateFormatConverter.convert(Locale.US, DateFormat.getDateInstance(DateFormat.SHORT,
                  Locale.US)));

    CellStyle dateCellStyle = workBook.createCellStyle();
    dateCellStyle.setDataFormat(dateFormatIndex);

    while (r < cellValues.length + DEFAULT_HEADLINE - 1) {
      r++;
      row = sheet.createRow(r);

      // fill only even rows with cells
      if (r % 2 == 0)
        for (int c = DEFAULT_FIRST_COL; c < columnNames.length + DEFAULT_FIRST_COL; c++) {
          Cell cell = row.createCell(c);

          Object value = cellValues[r - DEFAULT_HEADLINE][c - DEFAULT_FIRST_COL];
          if (value instanceof java.util.Date) {
            cell.setCellValue((java.util.Date) value);
            cell.setCellStyle(dateCellStyle);
          } else if (value instanceof Boolean)
            cell.setCellValue((Boolean) value);
          else if (value instanceof Double)
            cell.setCellValue((Double) value);
          else if (value instanceof String)
            cell.setCellValue((String) value);
        }
    }

    File file = File.createTempFile(Issue13_NPEOnEmptyCells.class.getSimpleName() + "_", ".xlsx");
    file.deleteOnExit();

    FileOutputStream fileOutputStream = new FileOutputStream(file);
    workBook.write(fileOutputStream);
    fileOutputStream.flush();
    fileOutputStream.close();

    conn = DriverManager.getConnection(
    "jdbc:xls:" + file.toURI().toASCIIString() + "?headLine=" + DEFAULT_HEADLINE + "&firstColumn=" + DEFAULT_FIRST_COL);
    metaData = conn.getMetaData();
  }

  @BeforeClass
  public static void loadDriverClass() throws ClassNotFoundException {
    Class.forName("com.sqlsheet.XlsDriver");
  }

  @Test
  public void getData() throws SQLException {
    Statement st = null;
    ResultSet rs = null;

    try {
      st = conn.createStatement();

      st.closeOnCompletion();

      rs = st.executeQuery("SELECT * from TestSheet1");
      int r = 0;
      while (rs.next()) {
        for (int c = 0; c < columnNames.length; c++) {
          Object value = 0;
          if (columnNames[c].equals("String"))
            value = rs.getString(c + 1);
          else if (columnNames[c].equals("Date"))
            value = rs.getDate(c + 1);
          else if (columnNames[c].equals("Boolean"))
            value = rs.getBoolean(c + 1);
          else if (columnNames[c].equals("Double"))
            value = rs.getDouble(c + 1);

          if (r % 2 == 0)
            Assert.assertTrue("Column " + columnNames[c] + " in row " + r + " should be NULL.", rs.wasNull());
        }
        r++;
      }
    } finally {
      try {
        if (rs != null && !rs.isClosed())
          rs.close();
      } catch (Exception ex) {
        // fail silently
      }

      try {
        if (st != null && !st.isClosed())
          st.close();
      } catch (Exception ex) {
        // fail silently
      }
    }
  }
}
