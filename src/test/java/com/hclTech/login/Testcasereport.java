package com.hclTech.login;
 
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
 
import org.testng.ITestResult;
import org.testng.annotations.*;
 
public class Testcasereport {
 
    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;
 
    @BeforeSuite
    public void setupReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }
 
    @BeforeMethod
    public void setup() {
System.setProperty("webdriver.chrome.driver", "C:\\Users\\anuku.vamsi\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");  // Replace this
        driver = new ChromeDriver();
        driver.manage().window().maximize();
driver.get("https://www.saucedemo.com/");
    }
 
    @Test
    public void testCase1() {
        test = extent.createTest("testCase1 - Google Search Laptop");
driver.findElement(By.id("user-name")).sendKeys("standard_user");
driver.findElement(By.id("password")).sendKeys("secret_sauce");
        //test.log(Status.PASS, "Laptop search completed");
    }
 
    @Test
    public void testCase2() {
        test = extent.createTest("testCase2 - Google Search TV");
driver.findElement(By.id("user-name")).sendKeys("username");
driver.findElement(By.name("password")).sendKeys("jhfv");
        //fatest.log(Status.PASS, "TV search completed");
    }
 
    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "Test Failed: " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "Test Skipped");
        } else {
            test.log(Status.PASS, "Test Passed");
        }
 
        driver.quit();
    }
 
    @AfterSuite
    public void flushReport() {
        extent.flush();
    }
}