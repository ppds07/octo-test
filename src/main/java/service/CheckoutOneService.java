package service;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import SwagLabElement.CheckOutOneElement;
import SwagLabElement.LoginElement;
import SwagLabElement.MyCartElement;
import SwagLabElement.ProductElement;
import utils.ExcelReader;
import utils.ScreenshotService;

public class CheckoutOneService {

    private WebDriver driver;
    private String checkOutUrl;

    public CheckoutOneService(WebDriver driver) {
        this.driver = driver;
    }

    public void loginAndNavigateToCheckout() {
        LoginElement loginElement = new LoginElement(driver);
        loginElement.setUsername("standard_user");
        loginElement.setPassword("secret_sauce");
        loginElement.setLoginButton();
        System.out.println("SwagLabs Login successful - username: standard_user and password: secret_sauce");

        try {
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("inventory.html"), "Login failed or incorrect redirect.");
        } catch (AssertionError ae) {
            System.out.println("Assertion Failed: Login URL validation failed.");
            System.out.println("Details: " + ae.getMessage());
            ScreenshotService.captureScreenshot(driver, "LoginError");
            throw ae;
        }

        ProductElement productElement = new ProductElement(driver);
        productElement.addSingleProduct();
        productElement.clickCartIcon();

        try {
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("cart.html"), "Cart page not loaded.");
        } catch (AssertionError ae) {
            System.out.println("Assertion Failed: Cart page URL validation failed.");
            System.out.println("Details: " + ae.getMessage());
            ScreenshotService.captureScreenshot(driver, "CartPageError");
            throw ae;
        }

        MyCartElement cartElement = new MyCartElement(driver);
        cartElement.clickCheckOutButton();

        try {
            String currentUrl = driver.getCurrentUrl();
            if (currentUrl.contains("checkout-step-one.html")) {
                checkOutUrl = currentUrl;
            }
            Assert.assertTrue(currentUrl.contains("checkout-step-one.html"), "Checkout page not loaded.");
        } catch (AssertionError ae) {
            System.out.println("Assertion Failed: Checkout page URL validation failed.");
            System.out.println("Details: " + ae.getMessage());
            ScreenshotService.captureScreenshot(driver, "CheckoutPageError");
            throw ae;
        }
    }

    public void performCheckoutTests(String filePath, SoftAssert softAssert) {
        List<String[]> testData = ExcelReader.readSheetData(filePath, 1);

        for (int i = 0; i < testData.size(); i++) {
            String[] row = testData.get(i);
            String firstname = row[0];
            String lastname = row[1];
            String postalcode = row[2];
            String expected_result = row[3];

            System.out.println();
            System.out.println("Data " + (i + 1) + " Testing checkout with: FirstName :" + firstname + ", LastName :" + lastname + ", PostalCode:" + postalcode);

            CheckOutOneElement checkoutElement = new CheckOutOneElement(driver);
            checkoutElement.setFirstName(firstname);
            checkoutElement.setLastName(lastname);
            checkoutElement.setPostalCode(postalcode);

            MyCartElement cartElement = new MyCartElement(driver);
            int cartCount = cartElement.checkCartItems();

            try {
                Assert.assertTrue(cartCount > 0, "Cart is empty. Cannot proceed to checkout.");
                System.out.println("Cart has " + cartCount + " item(s)");
                checkoutElement.clickContinueButton();
            } catch (Exception e) {
                System.out.println("Cart validation or continue button failed: " + e.getMessage());
                ScreenshotService.captureScreenshot(driver, "CartError_" + firstname);
            }

            boolean checkoutSuccess = false;
            String errorMessage = "";

            try {
                checkoutSuccess = driver.getCurrentUrl().contains("checkout-step-two.html");
            } catch (Exception e) {
                try {
                    errorMessage = driver.findElement(By.className("error-message-container")).getText();
                    System.out.println("Error Message displayed: " + errorMessage);
                } catch (Exception inner) {
                    System.out.println("Error message not found: " + inner.getMessage());
                }
                ScreenshotService.captureScreenshot(driver, "CheckoutError_" + firstname);
            }

            System.out.println("Actual Result: " + checkoutSuccess + ", Expected: " + expected_result);

            try {
                if (expected_result.equalsIgnoreCase("Success")) {
                    softAssert.assertTrue(checkoutSuccess, "Expected checkout to succeed for: " + firstname);
                } else {
                    softAssert.assertFalse(checkoutSuccess, "Expected checkout to fail for: " + firstname);
                }
            } catch (AssertionError ae) {
                System.out.println("Details: " + ae.getMessage());
                ScreenshotService.captureScreenshot(driver, "AssertionError_" + firstname);
            }

            driver.get(checkOutUrl);
        }
    }
}