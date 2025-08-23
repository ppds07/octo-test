package com.hclTech.login;
 
//package com.ppds07.testing1;

import static org.junit.jupiter.api.Assertions.assertTrue;
 
import java.io.*;
 
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
 
public class Screenshot {
 
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
        String excelFilePath = "C:\\Users\\anuku.vamsi\\Downloads\\login_test_data.xlsx";
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
        File firstSrc=((RemoteWebDriver) d).getScreenshotAs(OutputType.FILE);
		File destination=new File("./snaps/img.png");
		try {
			FileHandler.copy(firstSrc, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		WebElement element=d.findElement(By.xpath("/html/body/div"));
		File secondSrc=element.getScreenshotAs(OutputType.FILE);
		File desti=new File("C:\\Projects\\testing1\\Screenshots\\img.png");
		try {
			FileHandler.copy(secondSrc, desti);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
 