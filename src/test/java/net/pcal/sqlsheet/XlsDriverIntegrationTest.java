package net.pcal.sqlsheet;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import scriptella.configuration.ConfigurationFactory;
import scriptella.execution.EtlExecutor;
import scriptella.execution.EtlExecutorException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class XlsDriverIntegrationTest {

    private static final String extractXlsScriptFile = "xlsextract.xml";
    private static final String loadXlsScriptFile = "xlsload.xml";
    private static final String testExtractXlsFile = "extracttest.xls";
    private static final String testExtractXlsxFile = "extracttest.xlsx";

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetMinorVersion() throws Exception {

    }

    @Before
    public void init() throws Exception {
    }


    @Test
    public void testExctractFromXls() throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("filePath", dumpExcelToTempFile(testExtractXlsFile, ".xls").toString());
        params.put("params", params);

        Map<String, Object> results = executeEtlScriptWithResult(ClassLoader.getSystemResource(extractXlsScriptFile), params);

        assertNotNull(results.get("results"));
        List<ResultMock> results1 = (List<ResultMock>) results.get("results");
        assertNotNull(results1);
        assertEquals(3, results1.size());
        for (ResultMock customBean : results1) {
            assertNotNull(customBean.getDate());
            assertNotNull(customBean.getID());
            assertNotNull(customBean.getName());
        }
    }

    @Test
    public void testLoadToXls() throws Exception {
        List<ResultMock> pojo = new ArrayList<ResultMock>();
        pojo.add(new ResultMock(1, "test1", new Date()));
        pojo.add(new ResultMock(2, "test2", new Date()));
        pojo.add(new ResultMock(3, "test3", new Date()));

        File xls = File.createTempFile("tmp.", ".xls");
        xls.deleteOnExit();

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("filePath", xls.toURL());
        params.put("params", params);
        params.put("POJO", pojo);

        // Load pojo into excel
        Map<String, Object> results = executeEtlScriptWithResult(ClassLoader.getSystemResource(loadXlsScriptFile), params);
        params.put("filePath", xls.toURL());
        params.put("params", params);

        // script must fill results parameter
        results = executeEtlScriptWithResult(ClassLoader.getSystemResource(extractXlsScriptFile), params);
        assertNotNull(results.get("results"));
        List<ResultMock> results1 = (List<ResultMock>) results.get("results");
        assertNotNull(results1);
        assertEquals(3, results1.size());
    }

    @Test
    public void testExctractFromXlsx() throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("filePath", dumpExcelToTempFile(testExtractXlsxFile, ".xlsx").toString());
        params.put("params", params);
        Map<String, Object> results = executeEtlScriptWithResult(ClassLoader.getSystemResource(extractXlsScriptFile), params);
        assertNotNull(results.get("results"));
        List<ResultMock> results1 = (List<ResultMock>) results.get("results");
        assertNotNull(results1);
        assertEquals(3, results1.size());
    }

    @Test
    public void testLoadToXlsx() throws Exception {
        List<ResultMock> pojo = new ArrayList<ResultMock>();
        pojo.add(new ResultMock(1, "test1", new Date()));
        pojo.add(new ResultMock(2, "test2", new Date()));
        pojo.add(new ResultMock(3, "test3", new Date()));

        File xls = File.createTempFile("tmp.", ".xlsx");
        xls.deleteOnExit();

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("filePath", xls.toURL().toString());
        params.put("params", params);
        params.put("POJO", pojo);

        // Load pojo into excel
        Map<String, Object> results = executeEtlScriptWithResult(ClassLoader.getSystemResource(loadXlsScriptFile), params);

        // Extract data from loaded data
        params.put("filePath", xls.toURL().toString());
        params.put("params", params);
        // script must fill results parameter
        Map<String, Object> results1 = executeEtlScriptWithResult(ClassLoader.getSystemResource(extractXlsScriptFile),params);
        assertNotNull(results1.get("results"));
        List<ResultMock> results2 = (List<ResultMock>) results1.get("results");
        assertNotNull(results2);
        assertEquals(3, results2.size());
    }

    private URL dumpExcelToTempFile(String resource, String format) {
        URL url = null;
        try {
            File xlsFile = File.createTempFile("tmp.", format);
            FileUtils.writeByteArrayToFile(xlsFile, IOUtils.toByteArray(ClassLoader.getSystemResourceAsStream(resource)));
            url = xlsFile.toURL();
            xlsFile.deleteOnExit();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return url;
    }

    private Map<String, Object> executeEtlScriptWithResult(URL scriptFile, Map<String, Object> parameters) throws EtlExecutorException {
        EtlExecutor etlExecutor = new EtlExecutor();
        ConfigurationFactory cf = new ConfigurationFactory();
        cf.setResourceURL(scriptFile);
        cf.setExternalParameters(parameters);
        etlExecutor.setConfiguration(cf.createConfiguration());
        etlExecutor.execute();
        return parameters;
    }
}
