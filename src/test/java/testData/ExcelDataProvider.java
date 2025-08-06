package testData;

import java.io.*;
import org.testng.annotations.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class ExcelDataProvider {
	
	@DataProvider(name = "excelData")
	public Object[][] excelDataProvider() throws IOException
	{
		String excelPath = "C:\\Projects\\POC\\test-data\\TestData.xlsx";
		FileInputStream fis = new FileInputStream(excelPath);
		
		Workbook wb = new XSSFWorkbook(fis);
		Sheet sheet = wb.getSheetAt(0);
		
		int rowcount = sheet.getLastRowNum() + 1;
		Object[][] data = new Object[rowcount][2];
		
		for(int i=0; i<rowcount; i++)
		{
			Row row = sheet.getRow(i);
			if(row==null || row.getCell(0)==null || row.getCell(i)== null) continue;
			
			data[i][0] = row.getCell(0).toString();
			data[i][1] = row.getCell(1).toString();
		}
		
		wb.close();
		fis.close();
		return data;
		
	}

}
