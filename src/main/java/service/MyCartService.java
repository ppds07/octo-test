package service;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import SwagLabElement.LoginElement;
import SwagLabElement.MyCartElement;
import SwagLabElement.ProductElement;

public class MyCartService {

    private WebDriver driver;
    private ProductElement productElement;
    private MyCartElement cartElement;

    public MyCartService(WebDriver driver) {
        this.driver = driver;
        this.productElement = new ProductElement(driver);
        this.cartElement = new MyCartElement(driver);
    }

    public void loginAndAddProducts() throws InterruptedException {
        LoginElement loginElement = new LoginElement(driver);
        loginElement.setUsername("standard_user");
        loginElement.setPassword("secret_sauce");
        loginElement.setLoginButton();
        System.out.println("SwagLabs Login successful - username: standard_user and password: secret_sauce");

        productElement.addMultipleProducts(6);
        Thread.sleep(5000);
        productElement.clickCartIcon();
    }

    public void verifyCartItemCount() throws InterruptedException {
        Thread.sleep(5000);
        int count = cartElement.checkCartItems();
        System.out.println("Cart item count: " + count);
        Assert.assertTrue(count > 0, "Cart should have items");
    }

    public void verifyCartContents() {
        Map<String, String> productMap = productElement.getProductPriceMap();
        Assert.assertFalse(productMap.isEmpty(), "Cart should contain products");

        productMap.forEach((name, price) -> {
            System.out.println("Product: " + name + " | Price: " + price);
        });
    }

    public void verifyEmptyCartMessage() throws InterruptedException {
        productElement.removeAddedProducts();
        int count = cartElement.checkCartItems();
        if (count == 0) {
            System.out.println("Cart is empty as expected");
        } else {
            System.out.println("Cart is not empty");
        }
    }

    public void checkOutButtonFunction() throws InterruptedException {
        cartElement.clickCheckOutButton();
        System.out.println("Checkout button clicked successfully.");
    }
}