/*
 * Copyright 2008 pcal.net
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
package net.pcal.sqlsheet;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * SqlSheet implementation of java.sql.Driver.
 * 
 * @author <a href='http://www.pcal.net'>pcal</a>
 */
public class XlsDriver implements Driver {

  // =========================================================================
  // Constants

  private final static String URL_SCHEME = "jdbc:xls:";

  // =========================================================================
  // Static initializer

  static {
    try {
      DriverManager.registerDriver(new XlsDriver());
    } catch (SQLException e) {
      System.err.println("Couldn't register " + XlsDriver.class.getName());
      e.printStackTrace();
    }
  }

  // =========================================================================
  // Driver implementation

  public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) {
    return new DriverPropertyInfo[0];
  }

  public Connection connect(String jdbcUrl, Properties info)
      throws SQLException
  {
    if (jdbcUrl == null) throw new IllegalArgumentException("Null url");
    if (!acceptsURL(jdbcUrl)) return null; // why is this necessary?
    if (!jdbcUrl.startsWith(URL_SCHEME)) {
      throw new IllegalArgumentException("URL is not " + URL_SCHEME + " ("
          + jdbcUrl + ")");
    }
    try {
      String workbookUrl = jdbcUrl.substring(URL_SCHEME.length());

      if (workbookUrl.startsWith("file:")) {
        File saveFile = new File(workbookUrl.substring("file:".length()));
        HSSFWorkbook workbook;
        if (saveFile.exists()) {
          FileInputStream fin = null;
          try {
            fin = new FileInputStream(saveFile);
            POIFSFileSystem fs = new POIFSFileSystem(fin);
            workbook = new HSSFWorkbook(fs);
          } finally {
            if (fin != null) fin.close();
          }
        } else {
          workbook = new HSSFWorkbook();
        }
        return new XlsConnection(workbook, saveFile);
      } else {
        URL url = new URL(workbookUrl);
        POIFSFileSystem fs = new POIFSFileSystem(url.openStream());
        HSSFWorkbook workbook = new HSSFWorkbook(fs);
        return new XlsConnection(workbook);
      }
    } catch (Exception e) {
      SQLException sqe = new SQLException(e.getMessage());
      sqe.initCause(e);
      throw sqe;
    }
  }

  public boolean acceptsURL(String url) throws SQLException {
    if (url == null) return false;
    boolean yes = url.trim().startsWith(URL_SCHEME);
    return yes;
  }

  public boolean jdbcCompliant() { // LOLZ!
    return false;
  }

  public int getMajorVersion() {
    return 1;
  }

  public int getMinorVersion() {
    return 0;
  }

}
