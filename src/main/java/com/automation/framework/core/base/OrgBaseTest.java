package com.automation.framework.core.base;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;

public class OrgBaseTest {
    private static final Logger LOGGER = Logger.getLogger(OrgBaseTest.class);

    private static final String LABEL_TEST_FOLDER_NAME = "testDataFolder";
    private static String testDataFolder;

    private static final String LABEL_TEST_ENV = "environment";
    private static String environment;

    private static final String LABEL_DATA_MAPPING_FILE_NAME = "dataMappingFileName";
    private static String dataMappingFileName;

    @BeforeSuite(
            alwaysRun = true
    )
    @Parameters({LABEL_TEST_FOLDER_NAME, LABEL_TEST_ENV, LABEL_DATA_MAPPING_FILE_NAME})
    public void beforeSuite(ITestContext context,
                            @Optional("TestData") String testDataFolder,
                            @Optional("QA") String environment,
                            @Optional("DataMappings.csv") String dataMappingFileName) {
        OrgBaseTest.testDataFolder = testDataFolder;
        OrgBaseTest.environment = environment;
        OrgBaseTest.dataMappingFileName = dataMappingFileName;
    }

    public static String getDataMappingFilePath() {
        return System.getProperty("user.dir") + File.separator + testDataFolder + File.separator +
                environment + File.separator + dataMappingFileName;
    }

    public static String getDataFolderPath() {
        return System.getProperty("user.dir") + File.separator + testDataFolder + File.separator +
                environment;
    }

    @BeforeMethod(
            alwaysRun = true
    )
    public void beforeMethod(Method method, Object[] testData) {
        Test testMethod = (Test)method.getAnnotation(Test.class);
        LOGGER.info("Start Test: " + (testMethod.testName().equals("") ? method.getName() : testMethod.testName()));
    }
}
