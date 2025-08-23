package com.hclTech.login;


import static org.junit.jupiter.api.Assertions.assertTrue;
 
import java.io.*;
 
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
 
public class Excel {
 
    static WebDriver d;
 
    @BeforeSuite
    public void setup() throws IOException {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\anuku.vamsi\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        d = new ChromeDriver();
        d.manage().window().maximize();
        d.get("https://www.saucedemo.com/");
    }
 
    @DataProvider(name = "excelData")
    public Object[][] excelDataProvider() throws IOException {
        String excelFilePath = "C:\\Users\\anuku.vamsi\\downloads\\TestData.xlsx";
        FileInputStream fis = new FileInputStream(excelFilePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);
 
        int rowCount = sheet.getLastRowNum() + 1;
        Object[][] data = new Object[rowCount][2];
 
        for (int i = 0; i < rowCount; i++) {
            Row row = sheet.getRow(i);
            if (row == null || row.getCell(0) == null || row.getCell(1) == null) continue;
 
            data[i][0] = row.getCell(0).toString();
            data[i][1] = row.getCell(1).toString();
        }
 
        workbook.close();
        fis.close();
        return data;
    }
 
    @Test(dataProvider = "excelData")
    public void loginTest(String username, String password) {
        d.findElement(By.id("user-name")).clear();
        d.findElement(By.id("user-name")).sendKeys(username);
        d.findElement(By.id("password")).clear();
        d.findElement(By.id("password")).sendKeys(password);
        d.findElement(By.id("login-button")).click();
 
        assertTrue(d.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html"), "Login failed!");
        System.out.println("Login successful with user: " + username);
        d.navigate().back(); // Return to login page for next test
    }
 
    @Test(expectedExceptions = org.openqa.selenium.NoSuchElementException.class)
    public static void tc1() {
        d.findElement(By.id("non-existent-element")).click();
    }
 
    @AfterSuite
    public void tearDown() {
        d.quit();
    }
}
 
