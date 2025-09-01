package SwagLabsTestCases;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import Common.BrowserFactory;
import service.ProductService;

public class ProductTestCase extends BrowserFactory {

    ProductService productService;

    @BeforeClass
    public void setup() throws InterruptedException {
        productService = new ProductService(driver);
        productService.loginToInventory();
    }

    @Test(priority = 1)
    public void checkCartIconIfNoProductsAdded() throws InterruptedException {
        productService.checkCartIconWithoutAddingProducts();
    }

    @Test(priority = 2)
    public void checkCartIconIfProductsAdded() throws InterruptedException {
        productService.checkCartIconAfterAddingSingleProduct();
    }

    @Test(priority = 3)
    public void checkCartIconAfterAddingMultipleProducts() throws InterruptedException {
        productService.checkCartIconAfterAddingMultipleProducts(3);
    }

    @Test(priority = 4)
    public void validateAllProductPrices() {
        productService.validateAllProductPrices();
    }

    @Test(priority = 5)
    public void printAllProductsWithDetails() {
        productService.printAllProductsWithDetails();
    }

    @Test(priority = 7)
    public void validateAllFilters() {
        productService.validateAllFilters();
    }

    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}