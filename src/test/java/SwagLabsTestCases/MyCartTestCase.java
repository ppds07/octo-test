package SwagLabsTestCases;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Common.BrowserFactory;
import SwagLabElement.LoginElement;
import SwagLabElement.MyCartElement;
import SwagLabElement.ProductElement;

public class MyCartTestCase extends BrowserFactory {

    ProductElement productElement;
    MyCartElement cartElement;

    @BeforeClass
    public void cartLoginProcess() throws InterruptedException {
        LoginElement loginElement = new LoginElement(driver);
        loginElement.setUsername("standard_user");
        loginElement.setPassword("secret_sauce");
        loginElement.setLoginButton();
        System.out.println("SwagLabs Login successful - username: standard_user and password: secret_sauce");

        productElement = new ProductElement(driver);
        productElement.addMultipleProducts(6);
        Thread.sleep(5000);
        productElement.clickCartIcon();
    }

    @Test(priority = 1)
    public void verifyCartItemCount() throws InterruptedException {
        cartElement = new MyCartElement(driver);
        Thread.sleep(5000);
        int count = cartElement.checkCartItems();
        System.out.println("Cart item count: " + count);
        Assert.assertTrue(count > 0, "Cart should have items");
    }

    @Test(priority = 2)
    public void verifyCartContents() {
        productElement = new ProductElement(driver);
        Map<String, String> productMap = productElement.getProductPriceMap();
        Assert.assertFalse(productMap.isEmpty(), "Cart should contain products");

        productMap.forEach((name, price) -> {
            System.out.println("Product: " + name + " | Price: " + price);
        });
    }

    @Test(priority = 3)
    public void verifyRemoveFunctionality() throws InterruptedException {
    	
    	cartElement = new MyCartElement(driver);
    	cartElement.removeAddedProducts();
        
        productElement.printAllProductDetails();
    	Thread.sleep(3000);
        int counts = cartElement.checkCartItems();
        System.out.println("Cart item count after removal: " + counts);
        Assert.assertEquals(counts, 0, "Cart should be empty after removing items");
    }

    @Test(priority = 4)
    public void verifyEmptyCartMessage() throws InterruptedException {
    	
    	productElement = new ProductElement(driver);
    	productElement.removeAddedProducts();
    	
        cartElement = new MyCartElement(driver);
        
        int count = cartElement.checkCartItems();
        if (count == 0) {
            System.out.println("Cart is empty as expected");
        } else {
            System.out.println("Cart is not empty");
        }
        
    }

    @Test(priority = 5)
    public void myCartFunction() throws InterruptedException {
    	
        cartElement = new MyCartElement(driver);
        cartElement.clickCheckOutButton();
        System.out.println("Checkout button clicked successfully.");
    }
}
