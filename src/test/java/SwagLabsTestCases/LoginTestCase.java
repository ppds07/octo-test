package SwagLabsTestCases;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;

import Common.BrowserFactory;
import SwagLabElement.LoginElement;

public class LoginTestCase extends BrowserFactory {

    @Test(priority=1)
    public void userLogin() throws IOException {

       
        LoginElement loginElement=new LoginElement(driver);

        String filePath = System.getProperty("user.dir") + "/ExcelFile/SwagLabLoginData.xlsx";
        FileInputStream fileLocation = new FileInputStream(filePath);

        try (XSSFWorkbook workbook = new XSSFWorkbook(fileLocation)) {
            XSSFSheet sheet = workbook.getSheetAt(0);
            int lastRow = sheet.getLastRowNum();
            System.out.println("Total rows in Excel: " + lastRow);

            DataFormatter dft = new DataFormatter();

            for (int i = 1; i <= lastRow; i++) {
                XSSFRow row = sheet.getRow(i);
                if (row == null) continue;

                String username = dft.formatCellValue(row.getCell(0));
                String password = dft.formatCellValue(row.getCell(1));
                String expectedResult = dft.formatCellValue(row.getCell(2));

                System.out.println("Testing login for: " + username);
                loginElement.setUsername(username);
                loginElement.setPassword(password);
                loginElement.setLoginButton();

                boolean loginSuccess;
                String errorMessage = "";

                try {
                    loginSuccess = driver.findElement(By.className("inventory_list")).isDisplayed();
                } catch (NoSuchElementException e) {
                    loginSuccess = false;
                    try {
                        errorMessage = driver.findElement(By.cssSelector("h3[data-test='error']")).getText();
                    } catch (NoSuchElementException ignored) {}
                }
                try {
                	
                	if (expectedResult.equalsIgnoreCase("Success")) {
                		Assert.assertTrue(loginSuccess, "Expected login to succeed for user: " + username);
                	} else {
                		Assert.assertFalse(loginSuccess, "Expected login to fail for user: " + username + ". Error: " + errorMessage);
                	}
                }
                catch(AssertionError ae) {


                    System.out.println("Assertion failed for user: " + username);
                    System.out.println("Error Message : "+errorMessage);
                    System.out.println("Details: " + ae.getMessage());
                    throw ae; 
                }

                driver.get("https://www.saucedemo.com/");
            }
        }
    }

}
