/*
 * Copyright 2012 pcal.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.sqlsheet;

import com.sqlsheet.stream.XlsStreamConnection;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;

/**
 * SqlSheet implementation of java.sql.Driver.
 *
 * @author <a href='http://www.pcal.net'>pcal</a>
 * @author <a href='http://code.google.com/p/sqlsheet'>sqlsheet</a>
 */
public class XlsDriver implements java.sql.Driver {

    static final String READ_STREAMING = "readStreaming";
    static final String WRITE_STREAMING = "writeStreaming";
    static final String HEADLINE = "headLine";
    static final String FIRST_COL = "firstColumn";
    private static final String URL_SCHEME = "jdbc:xls:";
    private static final Logger LOGGER = Logger.getLogger(XlsDriver.class.getName());

    static {
        try {
            DriverManager.registerDriver(new XlsDriver());
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Couldn't register " + XlsDriver.class.getName(), e);
        }
    }

    /**
     * @return the actual $HOME folder of the user
     */
    public static File getHomeFolder() {
        return new File(System.getProperty("user.home"));
    }

    /**
     * @param uriStr the String representation of an URI containing "~" or "${user.home}"
     * @return the expanded URI (resolving "~" and "${user.home}" to the actual $HOME folder
     */
    public static String resolveHomeUriStr(String uriStr) {
        String homePathStr = getHomeFolder().toURI().getPath();

        String expandedURIStr = uriStr.replaceFirst("~", Matcher.quoteReplacement(homePathStr));
        expandedURIStr =
                expandedURIStr.replaceFirst("\\$\\{user.home\\}",
                        Matcher.quoteReplacement(homePathStr));

        return expandedURIStr;
    }

    @Override
    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) {
        return new DriverPropertyInfo[0];
    }

    /**
     * Attempts to make a database connection to the given URL. The driver should return "null" if
     * it realizes it is the wrong kind of driver to connect to the given URL. This will be common,
     * as when the JDBC driver manager is asked to connect to a given URL it passes the URL to each
     * loaded driver in turn.
     *
     * <p>
     * The driver should throw an <code>SQLException</code> if it is the right driver to connect to
     * the given URL but has trouble connecting to the database.
     *
     * <p>
     * The {@code url} should point to a file or a resource in the class path.
     *
     * <p>
     * Valid samples are:
     *
     * <ul>
     * <li>jdbc:xls:file://${user.home}/dataSource.xlsx
     * <li>jdbc:xls:file://~/dataSource.xlsx
     * <li>jdbc:xls:resource:/com/sqlsheet/dataSource.xlsx
     * </ul>
     *
     * <p>
     * The {@code Properties} argument can be used to pass arbitrary string tag/value pairs as
     * connection arguments. Normally at least "user" and "password" properties should be included
     * in the {@code Properties} object.
     *
     * <p>
     * <B>Note:</B> If a property is specified as part of the {@code url} and is also specified in
     * the {@code Properties} object, it is implementation-defined as to which value will take
     * precedence. For maximum portability, an application should only specify a property once.
     *
     * @param url the URL of the database to which to connect
     * @param info a list of arbitrary string tag/value pairs as connection arguments. Normally at
     *        least a "user" and "password" property should be included.
     * @return a <code>Connection</code> object that represents a connection to the URL
     * @throws SQLException if a database access error occurs or the url is {@code null}
     */
    @SuppressWarnings("PMD.NPathComplexity")
    public Connection connect(String url, Properties info) throws SQLException {
        if (url == null) {
            throw new IllegalArgumentException("Null url");
        }
        if (!acceptsURL(url)) {
            return null; // why is this necessary?
        }
        if (!url.toLowerCase().startsWith(URL_SCHEME)) {
            throw new IllegalArgumentException("URL is not " + URL_SCHEME + " (" + url + ")");
        }
        // strip any properties from end of URL and set them as additional properties
        String urlProperties;
        int questionIndex = url.indexOf('?');
        if (questionIndex >= 0) {
            urlProperties = url.substring(questionIndex);
            String[] split = urlProperties.substring(1).split("&");
            for (String each : split) {
                String[] property = each.split("=");
                try {
                    if (property.length == 2) {
                        String key = URLDecoder.decode(property[0], "UTF-8");
                        String value = URLDecoder.decode(property[1], "UTF-8");
                        info.setProperty(key, value);
                    } else if (property.length == 1) {
                        String key = URLDecoder.decode(property[0], "UTF-8");
                        info.setProperty(key, Boolean.TRUE.toString());
                    } else {
                        throw new SQLException("Invalid property: " + each);
                    }
                } catch (UnsupportedEncodingException e) {
                    // we know UTF-8 is available
                }
            }
        }
        String strippedUrlStr = questionIndex >= 0
                ? url.substring(0, questionIndex)
                : url;
        try {
            String workbookUriStr = strippedUrlStr.substring(URL_SCHEME.length());
            workbookUriStr = resolveHomeUriStr(workbookUriStr);
            URL workbookUrl = null;
            try {
                URI workbookUri = new URI(workbookUriStr);
                String scheme = workbookUri.getScheme();
                if (scheme.equalsIgnoreCase("file")) {
                    workbookUrl = new URL(workbookUriStr);
                } else if (scheme.equalsIgnoreCase("classpath")) {
                    workbookUrl = XlsDriver.class.getResource(workbookUri.getPath());
                }
            } catch (RuntimeException ex) {
                workbookUrl = new URL(workbookUriStr);
            }

            // If streaming requested for read
            if (has(info, READ_STREAMING)) {
                return new XlsStreamConnection(workbookUrl);
            } else if (workbookUrl.getProtocol().equalsIgnoreCase("file")) {
                // If streaming requested for write
                if (has(info, WRITE_STREAMING)) {
                    return new XlsConnection(
                            getOrCreateXlsxStream(workbookUrl), new File(workbookUrl.getPath()),
                            info);
                }
                return new XlsConnection(
                        getOrCreateWorkbook(workbookUrl), new File(workbookUrl.getPath()), info);
            } else {
                // If plain url provided
                return new XlsConnection(WorkbookFactory.create(workbookUrl.openStream()), info);
            }
        } catch (Exception e) {
            SQLException sqe = new SQLException(e.getMessage(), e);
            throw sqe;
        }
    }

    boolean has(Properties info, String key) {
        Object value = info.get(key);
        if (value == null) {
            return false;
        }
        return value.equals(Boolean.TRUE.toString());
    }

    private SXSSFWorkbook getOrCreateXlsxStream(URL workbookUrl)
            throws IOException, InvalidFormatException {
        if (workbookUrl.getProtocol().equalsIgnoreCase("file")) {
            File source = new File(workbookUrl.getPath());
            if (source.exists() || source.length() != 0) {
                LOGGER.warning(
                        "File " + source.getPath() + " is not empty, and will parsed to memory!");
            } else {
                Workbook workbook = new XSSFWorkbook();
                flushWorkbook(workbook, source);
            }
        }
        return new SXSSFWorkbook(new XSSFWorkbook(workbookUrl.openStream()), 1000, false);
    }

    private Workbook getOrCreateWorkbook(URL workbookUrl)
            throws IOException, InvalidFormatException {
        if (workbookUrl.getProtocol().equalsIgnoreCase("file")) {
            File file = new File(workbookUrl.getPath());
            if (!file.exists() || file.length() == 0) {
                Workbook workbook;
                if (file.getPath().toLowerCase().endsWith("x")) {
                    workbook = new XSSFWorkbook();
                } else {
                    workbook = new HSSFWorkbook();
                }
                flushWorkbook(workbook, file);
            }
        }
        return WorkbookFactory.create(workbookUrl.openStream());
    }

    private void flushWorkbook(Workbook workbook, File file) throws IOException {
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream(file);
            workbook.write(fileOut);
            fileOut.flush();
        } finally {
            if (fileOut != null) {
                try {
                    fileOut.close();
                } catch (IOException e) {
                    LOGGER.log(Level.WARNING, e.getMessage(), e);
                }
            }
        }
    }

    public boolean acceptsURL(String url) throws SQLException {
        return url != null && url.trim().toLowerCase().startsWith(URL_SCHEME);
    }

    public boolean jdbcCompliant() { // LOLZ!
        return true;
    }

    public int getMajorVersion() {
        return 1;
    }

    public int getMinorVersion() {
        return 0;
    }

    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
