package com.sqlsheet;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import scriptella.configuration.ConfigurationFactory;
import scriptella.execution.EtlExecutor;
import scriptella.execution.EtlExecutorException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XlsDriverIntegrationTest {

    private static final String EXTRACT_XLS_SCRIPT_FILE = "xlsextract.xml";
    private static final String LOAD_XLS_SCRIPT_FILE = "xlsload.xml";
    private static final String TEST_EXTRACT_XLS_FILE = "extracttest.xls";
    private static final String TEST_EXTRACT_XLSX_FILE = "extracttest.xlsx";

    @Test
    public void testExctractFromXls() throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("filePath", dumpExcelToTempFile(TEST_EXTRACT_XLS_FILE, ".xls").toString());
        params.put("params", params);

        Map<String, Object> results = executeEtlScriptWithResult(
                ClassLoader.getSystemResource(EXTRACT_XLS_SCRIPT_FILE), params);

        Assertions.assertNotNull(results.get("results"));
        List<ResultMock> results1 = (List<ResultMock>) results.get("results");
        Assertions.assertNotNull(results1);
        Assertions.assertEquals(3, results1.size());
        for (ResultMock customBean : results1) {
            Assertions.assertNotNull(customBean.getDate());
            Assertions.assertNotNull(customBean.getID());
            Assertions.assertNotNull(customBean.getName());
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
        Map<String, Object> results = executeEtlScriptWithResult(
                ClassLoader.getSystemResource(LOAD_XLS_SCRIPT_FILE), params);
        params.put("filePath", xls.toURL());
        params.put("params", params);

        // script must fill results parameter
        results = executeEtlScriptWithResult(ClassLoader.getSystemResource(EXTRACT_XLS_SCRIPT_FILE),
                params);
        Assertions.assertNotNull(results.get("results"));
        List<ResultMock> results1 = (List<ResultMock>) results.get("results");
        Assertions.assertNotNull(results1);
        Assertions.assertEquals(3, results1.size());
    }

    @Test
    public void testExctractFromXlsx() throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("filePath", dumpExcelToTempFile(TEST_EXTRACT_XLSX_FILE, ".xlsx").toString());
        params.put("params", params);
        Map<String, Object> results = executeEtlScriptWithResult(
                ClassLoader.getSystemResource(EXTRACT_XLS_SCRIPT_FILE), params);
        Assertions.assertNotNull(results.get("results"));
        List<ResultMock> results1 = (List<ResultMock>) results.get("results");
        Assertions.assertNotNull(results1);
        Assertions.assertEquals(3, results1.size());
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
        Map<String, Object> results = executeEtlScriptWithResult(
                ClassLoader.getSystemResource(LOAD_XLS_SCRIPT_FILE), params);

        // Extract data from loaded data
        params.put("filePath", xls.toURL().toString());
        params.put("params", params);
        // script must fill results parameter
        Map<String, Object> results1 = executeEtlScriptWithResult(
                ClassLoader.getSystemResource(EXTRACT_XLS_SCRIPT_FILE), params);
        Assertions.assertNotNull(results1.get("results"));
        List<ResultMock> results2 = (List<ResultMock>) results1.get("results");
        Assertions.assertNotNull(results2);
        Assertions.assertEquals(3, results2.size());
    }

    private URL dumpExcelToTempFile(String resource, String format) {
        URL url;
        try {
            File xlsFile = File.createTempFile("tmp.", format);
            FileUtils.writeByteArrayToFile(xlsFile,
                    IOUtils.toByteArray(ClassLoader.getSystemResourceAsStream(resource)));
            url = xlsFile.toURL();
            xlsFile.deleteOnExit();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return url;
    }

    private Map<String, Object> executeEtlScriptWithResult(URL scriptFile,
            Map<String, Object> parameters)
            throws EtlExecutorException {
        EtlExecutor etlExecutor = new EtlExecutor();
        ConfigurationFactory cf = new ConfigurationFactory();
        cf.setResourceURL(scriptFile);
        cf.setExternalParameters(parameters);
        etlExecutor.setConfiguration(cf.createConfiguration());
        etlExecutor.execute();
        return parameters;
    }
}
