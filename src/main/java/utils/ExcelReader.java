package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

    public static List<String[]> readSheetData(String filePath, int sheetIndex) {
        List<String[]> dataList = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(file)) {

            XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
            DataFormatter formatter = new DataFormatter();

            int lastRow = sheet.getLastRowNum();
            for (int i = 1; i <= lastRow; i++) {
                XSSFRow row = sheet.getRow(i);
                String[] rowData = new String[row.getLastCellNum()];

                for (int j = 0; j < row.getLastCellNum(); j++) {
                    rowData[j] = formatter.formatCellValue(row.getCell(j));
                }

                dataList.add(rowData);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataList;
    }
}
