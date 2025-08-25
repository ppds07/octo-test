package SwagLabsTestCases;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Common.BrowserFactory;
import SwagLabElement.CheckOut1Element;
import SwagLabElement.CheckOutTwoElement;
import SwagLabElement.LoginElement;
import SwagLabElement.MyCartElement;
import SwagLabElement.ProductElement;
import utils.ScreenshotService;

public class CheckOutTwoTestCase extends BrowserFactory {

    @BeforeClass
    public void LoginProcessForCheckOutTwo() throws InterruptedException {
        LoginElement obj2 = new LoginElement(driver);
        obj2.setUsername("standard_user");
        obj2.setPassword("secret_sauce");
        obj2.setLoginButton();
        Thread.sleep(3000);

        ProductElement productElement = new ProductElement(driver);
        productElement.addTwoProduct();
        productElement.clickCartIcon();

        MyCartElement cartElement = new MyCartElement(driver);
        cartElement.clickCheckOutButton();

        CheckOut1Element checkOne = new CheckOut1Element(driver);
        checkOne.setFirstName("Rathi Prabha");
        checkOne.setLastName("E");
        checkOne.setPostalCode("636030");
        checkOne.clickContinueButton();
    }

    @Test(priority = 1)
    public void verifyPageLoad() {
        String expectedUrl = "https://www.saucedemo.com/checkout-step-two.html";
        String expectedTitle = "Swag Labs";

        try {
            Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "URL mismatch");
            Assert.assertEquals(driver.getTitle(), expectedTitle, "Title mismatch");
        } catch (AssertionError ae) {
            ScreenshotService.captureScreenshot(driver, "PageLoadError");
            throw ae;
        }
    }

    @Test(priority = 2)
    public void validateCheckoutSummary() {
        CheckOutTwoElement checkoutTwo = new CheckOutTwoElement(driver);

        try {
            int productCount = checkoutTwo.getProductCount();
            Assert.assertTrue(productCount > 0, "No products found in checkout summary");

            for (int i = 0; i < productCount; i++) {
                boolean nameVisible = checkoutTwo.getProductName(i);
                boolean priceVisible = checkoutTwo.getProductPrice(i);
                boolean quantityVisible = checkoutTwo.getProductQuantity(i);

                System.out.println("Product " + (i + 1) + ": Name visible = " + nameVisible +
                        ", Price visible = " + priceVisible +
                        ", Quantity visible = " + quantityVisible);

                Assert.assertTrue(nameVisible, "Product name not displayed for item " + i);
                Assert.assertTrue(priceVisible, "Product price not displayed for item " + i);
                Assert.assertTrue(quantityVisible, "Product quantity not displayed for item " + i);
            }

            boolean taxVisible = checkoutTwo.getTax();
            boolean totalVisible = checkoutTwo.getTotal();

            System.out.println("Tax visible: " + taxVisible);
            System.out.println("Total visible: " + totalVisible);

            Assert.assertTrue(taxVisible, "Tax not displayed");
            Assert.assertTrue(totalVisible, "Total not displayed");

        } catch (AssertionError ae) {
            ScreenshotService.captureScreenshot(driver, "CheckoutSummaryError");
            throw ae;
        }
    }

    @Test(priority = 3)
    public void validateTotalPriceCalculationWithTax() {
        CheckOutTwoElement checkoutTwo = new CheckOutTwoElement(driver);

        try {
            List<Double> prices = checkoutTwo.getProductPrices();
            double tax = checkoutTwo.getTaxAmount();
            double displayedTotal = checkoutTwo.getTotalAmount();

            double calculatedTotal = prices.stream().mapToDouble(Double::doubleValue).sum() + tax;

            System.out.println("Calculated Total: $" + calculatedTotal);
            System.out.println("Displayed Total: $" + displayedTotal);

            Assert.assertEquals(displayedTotal, calculatedTotal, 0.01, "Total price mismatch");

        } catch (AssertionError ae) {
            ScreenshotService.captureScreenshot(driver, "TotalPriceMismatch");
            throw ae;
        }
    }

    @Test(priority = 4)
    public void clickFinishButton() {
        CheckOutTwoElement checkoutTwo = new CheckOutTwoElement(driver);
        checkoutTwo.finishButton();

        String confirmUrl = driver.getCurrentUrl();
        try {
            Assert.assertTrue(confirmUrl.contains("checkout-complete.html"), "Redirection to confirmation page failed");
            System.out.println("Redirection to confirmation page successful.");
        } catch (AssertionError ae) {
            ScreenshotService.captureScreenshot(driver, "FinishButtonError");
            throw ae;
        }

        driver.navigate().back();
    }

    @Test(priority = 5)
    public void testCancelButtonFunctionality() {
        CheckOutTwoElement checkoutTwo = new CheckOutTwoElement(driver);
        checkoutTwo.clickCancelButton();

        String currentUrl = driver.getCurrentUrl();
        try {
            Assert.assertTrue(currentUrl.contains("inventory.html"), "Cancel button did not redirect to cart page");
            System.out.println("Cancel button redirected to cart page successfully.");
        } catch (AssertionError ae) {
            ScreenshotService.captureScreenshot(driver, "CancelButtonError");
            throw ae;
        }

        driver.navigate().back();
    }

    @Test(priority = 6)
    public void validatePaymentAndShippingInfo() {
        CheckOutTwoElement checkoutTwo = new CheckOutTwoElement(driver);

        try {
            String paymentInfo = checkoutTwo.getPaymentInfo();
            String shippingInfo = checkoutTwo.getShippingInfo();

            System.out.println("Payment Info: " + paymentInfo);
            System.out.println("Shipping Info: " + shippingInfo);

            Assert.assertTrue(paymentInfo.contains("SauceCard"), "Payment info incorrect");
            Assert.assertTrue(shippingInfo.contains("Free Pony Express Delivery!"), "Shipping info incorrect");

        } catch (AssertionError ae) {
            ScreenshotService.captureScreenshot(driver, "PaymentShippingInfoError");
            throw ae;
        }
    }

//    @AfterSuite
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
}
