package com.automation.framework.core.datadriven.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class CSVFileHelper {
    public static ArrayList<HashMap<String, String>> getDataFromFile(String filePath) throws IOException {
        ArrayList<HashMap<String, String>> dataList = new ArrayList<>();

        Reader in = new FileReader(filePath);
        CSVParser parser = CSVFormat.RFC4180.parse(in);

        // read the first line as header
        Iterable<CSVRecord> records = parser.getRecords();

        Iterator itr = records.iterator();
        Iterator headerItr = ((CSVRecord) itr.next()).iterator();

        List<String> headers = new ArrayList<>();
        while(headerItr.hasNext()) {
            String headerText = (String) headerItr.next();

            headers.add(headerText);
        }

        while(itr.hasNext()) {
            CSVRecord record = (CSVRecord) itr.next();

            HashMap<String, String> data = new HashMap<>();

            for(int i = 0; i < headers.size(); i++) {
                data.put(headers.get(i), record.get(i));
            }

            dataList.add(data);
        }

        return dataList;
    }
}
