package SwagLabsTestCases;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Common.BrowserFactory;
import service.CheckoutOneService;

public class CheckOutOneTestCase extends BrowserFactory {

    CheckoutOneService checkoutService;

    @BeforeClass
    public void setup() {
        checkoutService = new CheckoutOneService(driver);
        checkoutService.loginAndNavigateToCheckout();
    }

    @Test(priority = 1)
    public void CheckOut1Functions() {
        String filePath = System.getProperty("user.dir") + "/ExcelFile/SwagLabLoginData.xlsx";
        SoftAssert softAssert = new SoftAssert();
        checkoutService.performCheckoutTests(filePath, softAssert);
        // softAssert.assertAll(); // Uncomment if you want to aggregate all assertions
    }

    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
