package SwagLabsTestCases;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Common.BrowserFactory;
import service.MyCartService;

public class MyCartTestCase extends BrowserFactory {

    MyCartService cartService;

    @BeforeClass
    public void setup() throws InterruptedException {
        cartService = new MyCartService(driver);
        cartService.loginAndAddProducts();
    }

    @Test(priority = 1)
    public void verifyCartItemCount() throws InterruptedException {
        cartService.verifyCartItemCount();
    }

    @Test(priority = 2)
    public void verifyCartContents() {
        cartService.verifyCartContents();
    }

    @Test(priority = 3)
    public void verifyEmptyCartMessage() throws InterruptedException {
        cartService.verifyEmptyCartMessage();
    }

    @Test(priority = 4)
    public void checkOutButtonFunction() throws InterruptedException {
        cartService.checkOutButtonFunction();
    }

    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}