package com.automation.framework.core.datadriven.objects;

public class DataMapping {
    public static final String DATA_TYPE_CSV = "CSV";
    public static final String DATA_TYPE_JSON = "JSON";

    public static final String COL_NAME_TEST_CASE_ID = "test_case_id";
    public static final String COL_NAME_DATA_TYPE = "data_type";
    public static final String COL_NAME_DATA_FILE_PATH = "data_file_path";
    public static final String COL_NAME_DATA_FOLDER = "data_folder";
    public static final String DEFAULT_TESTDATA_PATH = System.getProperty("user.dir") + "\\TestDataFolder\\test.csv";
}
