package com.automation.framework.core.datadriven.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import org.apache.commons.io.FileUtils;

public class ExcelHelper {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet = null;
    private XSSFRow row = null;
    private String excelPath;

    public ExcelHelper(String xlFilePath) throws IOException {
        String path = Objects.requireNonNull(getClass().getClassLoader().getResource(xlFilePath)).getFile();
        excelPath = xlFilePath;
        // fix for running Jenkins
        FileInputStream fis = new FileInputStream(path.replace("%20", " "));
        workbook = new XSSFWorkbook(fis);
        fis.close();
    }

    public int getRowCount(String sheetName) {
        sheet = workbook.getSheet(sheetName);
        return sheet.getLastRowNum() + 1;
    }

    public int getColumnCount(String sheetName) {
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(0);
        return row.getLastCellNum();
    }

    public List<String> getColumnNames(String sheetName, int columns) {
        List<String> columnNames = new ArrayList<>();
        for (int i = 0; i < columns; i++) {
            columnNames.add(this.getCellData(sheetName, i, 0));
        }
        return columnNames;
    }

    public String getCellData(String sheetName, String colName, int rowNum) {
        int colNum = -1;
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(0);
        for (int i = 0; i < row.getLastCellNum(); i++) {
            if (row.getCell(i).getStringCellValue().trim().equalsIgnoreCase(colName.trim()))
                colNum = i;
        }
        return getStringCellValue(colNum, rowNum);
    }

    public String getCellData(String sheetName, int colNum, int rowNum) {
        sheet = workbook.getSheet(sheetName);
        return getStringCellValue(colNum, rowNum);
    }

    public String getStringCellValue(int colNum, int rowNum) {
        row = sheet.getRow(rowNum);
        XSSFCell cell = row.getCell(colNum, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        if (cell.getCellTypeEnum() == CellType.STRING) {
            return cell.getStringCellValue();
        } else if (cell.getCellTypeEnum() == CellType.NUMERIC || cell.getCellTypeEnum() == CellType.FORMULA) {
            String cellValue = String.valueOf(cell.getNumericCellValue());
            if (DateUtil.isCellDateFormatted(cell)) {
                DateFormat df = new SimpleDateFormat("dd/MM/yy");
                Date date = cell.getDateCellValue();
                cellValue = df.format(date);
            }
            return cellValue;
        } else if (cell.getCellTypeEnum() == CellType.BLANK) {
            return "";
        } else {
            return String.valueOf(cell.getBooleanCellValue());
        }
    }

    public void setStringCellValue(String stringValue, String sheetName, int rowNum, String columnName) throws IOException {
        XSSFSheet sheet = workbook.getSheet(sheetName);
        int columnIndex = 0;
        XSSFRow row = sheet.getRow(0);
        for (int i = 0; i < row.getLastCellNum(); i++) {
            if (row.getCell(i).getStringCellValue().trim().equalsIgnoreCase(columnName.trim()))
                columnIndex = i;
        }
        row = sheet.getRow(rowNum);
        Cell cell = row.getCell(columnIndex);
        cell.setCellValue(stringValue);
//        FileOutputStream outputStream = new FileOutputStream(new File(FileUtils.getAbsolutePathTestResourceFile(excelPath)));
        FileOutputStream outputStream = new FileOutputStream(new File(excelPath).getAbsolutePath());
        workbook.write(outputStream);
        outputStream.close();
    }
}
