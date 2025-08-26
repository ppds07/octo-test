package SwagLabsTestCases;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Common.BrowserFactory;
import service.CheckoutTwoService;

public class CheckOutTwoTestCase extends BrowserFactory {

    CheckoutTwoService checkoutService;

    @BeforeClass
    public void setup() throws InterruptedException {
        checkoutService = new CheckoutTwoService(driver);
        checkoutService.loginAndNavigateToCheckoutStepTwo();
    }

    @Test(priority = 1)
    public void verifyPageLoad() {
        checkoutService.verifyPageLoad();
    }

    @Test(priority = 2)
    public void validateCheckoutSummary() {
        checkoutService.validateCheckoutSummary();
    }

    @Test(priority = 3)
    public void validateTotalPriceCalculationWithTax() {
        checkoutService.validateTotalPriceCalculationWithTax();
    }

    @Test(priority = 4)
    public void clickFinishButton() {
        checkoutService.clickFinishButton();
    }

    @Test(priority = 5)
    public void testCancelButtonFunctionality() {
        checkoutService.testCancelButtonFunctionality();
    }

    @Test(priority = 6)
    public void validatePaymentAndShippingInfo() {
        checkoutService.validatePaymentAndShippingInfo();
    }

    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}