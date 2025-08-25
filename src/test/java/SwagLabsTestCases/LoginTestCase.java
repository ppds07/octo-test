package SwagLabsTestCases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import Common.BrowserFactory;
import SwagLabElement.LoginElement;
import utils.ExcelReader;
import utils.ScreenshotService;

public class LoginTestCase extends BrowserFactory {

    @Test(priority = 1)
    public void userLogin() {
        LoginElement loginElement = new LoginElement(driver);

        String filePath = System.getProperty("user.dir") + "/ExcelFile/SwagLabLoginData.xlsx";
        List<String[]> testData = ExcelReader.readSheetData(filePath, 0);

        for (int i = 0; i < testData.size(); i++) {
            String[] row = testData.get(i);
            String username = row[0];
            String password = row[1];
            String expectedResult = row[2];

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
                ScreenshotService.captureScreenshot(driver, "LoginFailed_" + username);
            }

            try {
                if (expectedResult.equalsIgnoreCase("Success")) {
                    Assert.assertTrue(loginSuccess, "Expected login to succeed for user: " + username);
                } else {
                    Assert.assertFalse(loginSuccess, "Expected login to fail for user: " + username + ". Error: " + errorMessage);
                }
            } catch (AssertionError ae) {
                System.out.println("Assertion failed for user: " + username);
                System.out.println("Error Message: " + errorMessage);
                System.out.println("Details: " + ae.getMessage());
                throw ae;
            }

            driver.get("https://www.saucedemo.com/");
        }
    }

//    @AfterSuite
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
}
