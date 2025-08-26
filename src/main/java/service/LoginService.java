package service;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import SwagLabElement.LoginElement;
import utils.ScreenshotService;

public class LoginService {

    private WebDriver driver;

    public LoginService(WebDriver driver) {
        this.driver = driver;
    }

    public void performLoginTest(String username, String password, String expectedResult) {
        LoginElement loginElement = new LoginElement(driver);

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
    }
}
