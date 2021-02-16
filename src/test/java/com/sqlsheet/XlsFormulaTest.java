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

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.DateFormatConverter;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

/** @author Andreas Reichel <andreas@manticore-projects.com> */
public class XlsFormulaTest {

  public static final int DEFAULT_HEADLINE = 1;
  public static final int DEFAULT_FIRST_COL = 0;

  private final Connection conn;

  private final String[] columnNames = {"A", "B", "C"};
  private final CellType[] cellTypes = {CellType.NUMERIC, CellType.NUMERIC, CellType.FORMULA};
  private final Object[][] cellValues = {
    {1d, 2d, "A2+B2"},
    {3d, 4d, "A3+B3"},
    {5d, 6d, "A4+B4"}
  };

  public XlsFormulaTest() throws SQLException, IOException {
    Workbook workBook = WorkbookFactory.create(true);
    Sheet sheet = workBook.createSheet("TestSheet1");
    int r = DEFAULT_HEADLINE - 1;
    Row row = sheet.createRow(r);
    for (int c = DEFAULT_FIRST_COL; c < columnNames.length + DEFAULT_FIRST_COL; c++) {
      Cell cell = row.createCell(c);
      cell.setCellValue(columnNames[c - DEFAULT_FIRST_COL]);
    }

    DataFormat dateFormat = workBook.createDataFormat();
    short dateFormatIndex =
        dateFormat.getFormat(
            DateFormatConverter.convert(
                Locale.US, DateFormat.getDateInstance(DateFormat.SHORT, Locale.US)));

    CellStyle dateCellStyle = workBook.createCellStyle();
    dateCellStyle.setDataFormat(dateFormatIndex);

    while (r < 3 + DEFAULT_HEADLINE - 1) {
      r++;
      row = sheet.createRow(r);
      for (int c = DEFAULT_FIRST_COL; c < columnNames.length + DEFAULT_FIRST_COL; c++) {
        Cell cell = row.createCell(c);
        switch (cellTypes[c - DEFAULT_FIRST_COL]) {
          case NUMERIC:
            cell.setCellValue((Double) cellValues[r - DEFAULT_HEADLINE][c - DEFAULT_FIRST_COL]);
            break;
          case FORMULA:
            cell.setCellFormula((String) cellValues[r - DEFAULT_HEADLINE][c - DEFAULT_FIRST_COL]);
            break;
        }
      }
    }

    File file = File.createTempFile("DatabaseMetaData_", ".xlsx");
    // file.deleteOnExit();

    FileOutputStream fileOutputStream = new FileOutputStream(file);
    workBook.write(fileOutputStream);
    fileOutputStream.flush();
    fileOutputStream.close();

    conn =
        DriverManager.getConnection(
            "jdbc:xls:"
                + file.toURI().toASCIIString()
                + "?headLine="
                + DEFAULT_HEADLINE
                + "&firstColumn="
                + DEFAULT_FIRST_COL);
  }

  @BeforeClass
  public static void loadDriverClass() throws ClassNotFoundException {
    Class.forName("com.sqlsheet.XlsDriver");
  }

  @Before
  public void setUp() {}

  @After
  public void tearDown() {
    try {
      if (conn != null && !conn.isClosed()) {
        conn.close();
      }
    } catch (SQLException ex) {
      // nothing we could do
    }
  }

  @Test
  public void readFormulaValue() throws Exception {
    Statement statement = null;
    ResultSet resultSet = null;
    try {
      statement = conn.createStatement();
      resultSet = statement.executeQuery("SELECT * FROM TestSheet1");
      int r = 0;
      while (resultSet.next()) {
        assertEquals(
            resultSet.getDouble(columnNames[0]) + resultSet.getDouble(columnNames[1]),
            resultSet.getDouble(columnNames[2]),
            1E-12);
        r++;
      }
      assertEquals(cellValues.length, r);

    } finally {
      if (resultSet != null && !resultSet.isClosed())
        try {
          resultSet.close();
        } catch (SQLException ex1) {
          // nothing we can do about
        }

      if (statement != null && !statement.isClosed()) {
        try {
          statement.close();
        } catch (SQLException ex1) {
          // nothing we can do about
        }
      }
    }
  }
}
