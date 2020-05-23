package com.automation.framework.core.datadriven.utils;

import com.automation.framework.core.base.OrgBaseTest;
import com.automation.framework.core.datadriven.objects.DataMapping;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import org.testng.ITestContext;

import org.testng.annotations.DataProvider;

public class DataProviderClass {
    private static final Logger LOGGER = Logger.getLogger(DataProviderClass.class);

    private static ArrayList<HashMap<String, String>> dataMappings = null;

    @DataProvider(name = "CreateData")
    public Object[] createData(Method m) throws IOException {
        LOGGER.info("Data Provider - Mapping Method: " + m.getName());

        ArrayList<HashMap<String, String>> dataSetList = new ArrayList<HashMap<String, String>>();

        // Get data mapping information
        if (dataMappings == null) {
            dataMappings = CSVFileHelper.getDataFromFile(OrgBaseTest.
                    getDataMappingFilePath());
        }

        // Map the Data to the specific test case by name
        HashMap<String, String> data = dataMappings.stream().filter(
                x -> x.get(DataMapping.COL_NAME_TEST_CASE_ID).equalsIgnoreCase(m.getName())
        ).findFirst().orElse(null);

        if (data != null) {
            String dataFilePath = OrgBaseTest.getDataFolderPath() +
                    File.separator + data.get(DataMapping.COL_NAME_DATA_FILE_PATH);

            switch (data.get(DataMapping.COL_NAME_DATA_TYPE)) {
                case DataMapping.DATA_TYPE_CSV:
                    // Data is CSV
                    dataSetList = CSVFileHelper.getDataFromFile(dataFilePath);
                    break;
                case DataMapping.DATA_TYPE_JSON:
                    // TODO: data can be JSON
                    break;
                default:
                    break;
            }
        }

        return dataSetList.toArray();
    }

    @DataProvider(name = "FilterData")
    public Object[] createDataVersion2(Method m) throws IOException {
        LOGGER.info("Data Provider - Mapping Method: " + m.getName());

        ArrayList<HashMap<String, String>> dataSetList = new ArrayList<HashMap<String, String>>();

        // Get data mapping information
        if (dataMappings == null) {
            dataMappings = CSVFileHelper.getDataFromFile(OrgBaseTest.getDataMappingFilePath());
        }

        // Map the Data to the specific test case by name
        HashMap<String, String> data = dataMappings.stream().filter(
                x -> x.get(DataMapping.COL_NAME_TEST_CASE_ID).equalsIgnoreCase(m.getName())
        ).findFirst().orElse(null);

        if (data != null) {
            String dataFilePath = OrgBaseTest.getDataFolderPath() +
                    File.separator + data.get(DataMapping.COL_NAME_DATA_FOLDER)+ "\\" + data.get(DataMapping.COL_NAME_DATA_FILE_PATH);

            System.out.println(dataFilePath);

            switch (data.get(DataMapping.COL_NAME_DATA_TYPE)) {
                case DataMapping.DATA_TYPE_CSV:
                    // Data is CSV
                    dataSetList = CSVFileHelper.getDataFromFile(dataFilePath);
                    break;
                case DataMapping.DATA_TYPE_JSON:
                    // TODO: data can be JSON
                    break;
                default:
                    break;
            }
        }

        return dataSetList.toArray();
    }
}
