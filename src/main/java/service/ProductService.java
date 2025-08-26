package service;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import SwagLabElement.LoginElement;
import SwagLabElement.ProductElement;

public class ProductService {

    private WebDriver driver;
    private ProductElement productElement;
    private String inventoryURL;

    public ProductService(WebDriver driver) {
        this.driver = driver;
        this.productElement = new ProductElement(driver);
    }

    public void loginToInventory() throws InterruptedException {
        LoginElement loginElement = new LoginElement(driver);
        loginElement.setUsername("standard_user");
        loginElement.setPassword("secret_sauce");
        loginElement.setLoginButton();
        Thread.sleep(3000);
        inventoryURL = driver.getCurrentUrl();
    }

    public void checkCartIconWithoutAddingProducts() throws InterruptedException {
        productElement.clickCartIcon();
    }

    public void checkCartIconAfterAddingSingleProduct() throws InterruptedException {
        productElement.addSingleProduct();
        productElement.clickCartIcon();
        productElement.removeAddedProducts();
        driver.get(inventoryURL);
    }

    public void checkCartIconAfterAddingMultipleProducts(int count) throws InterruptedException {
        productElement.addMultipleProducts(count);
        int cartCount = productElement.getCartItemCount();
        Assert.assertEquals(cartCount, count, "Cart should have " + count + " items");
        productElement.clickCartIcon();
        productElement.removeAddedProducts();
        driver.get(inventoryURL);
    }

    public void validateAllProductPrices() {
        Map<String, String> expectedPrices = new HashMap<>();
        expectedPrices.put("Sauce Labs Backpack", "$29.99");
        expectedPrices.put("Sauce Labs Bike Light", "$9.99");
        expectedPrices.put("Sauce Labs Bolt T-Shirt", "$15.99");
        expectedPrices.put("Sauce Labs Fleece Jacket", "$49.99");
        expectedPrices.put("Sauce Labs Onesie", "$7.99");
        expectedPrices.put("Test.allTheThings() T-Shirt (Red)", "$15.99");

        Map<String, String> actualPrices = productElement.getProductPriceMap();

        for (Map.Entry<String, String> entry : expectedPrices.entrySet()) {
            String productName = entry.getKey();
            String expectedPrice = entry.getValue();
            String actualPrice = actualPrices.get(productName);

            if (expectedPrice.equals(actualPrice)) {
                System.out.println("Matched: " + productName + " - " + actualPrice);
            } else {
                System.out.println("Mismatch: " + productName + " - Expected: " + expectedPrice + ", Found: " + actualPrice);
            }

            Assert.assertEquals(actualPrice, expectedPrice, "Price mismatch for " + productName);
        }
    }

    public void printAllProductsWithDetails() {
        productElement.printAllProductDetails();
    }

    public void validateAllFilters() {
        productElement.testAllFilters();
    }
}