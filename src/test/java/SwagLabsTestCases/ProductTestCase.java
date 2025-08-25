package SwagLabsTestCases;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Common.BrowserFactory;
import SwagLabElement.LoginElement;
import SwagLabElement.ProductElement;

public class ProductTestCase extends BrowserFactory {
	
	String inventoryURL;
	@BeforeClass
	public void LoginProcess() throws InterruptedException {
		LoginElement obj1=new LoginElement(driver);
		obj1.setUsername("standard_user");
		obj1.setPassword("secret_sauce");
		obj1.setLoginButton();
		Thread.sleep(3000);
		
	}
	@Test(priority=1)
	public void checkCartIconIfNoProductsAdded() throws InterruptedException {
		
		ProductElement productElement=new ProductElement(driver);
		productElement.clickCartIcon();
	}
	@Test(priority=2)
	public void checkCartIconIfProductsAdded() throws InterruptedException {
		
		ProductElement productElement=new ProductElement(driver);
		inventoryURL=driver.getCurrentUrl();
		productElement.addSingleProduct();
		productElement.clickCartIcon();
		productElement.removeAddedProducts();
		driver.get(inventoryURL);
	}
	

	@Test(priority=3)
	public void checkCartIconAfterAddingMultipleProducts() throws InterruptedException {
	   
		ProductElement productElement=new ProductElement(driver);
	    productElement.addMultipleProducts(3);
	    int cartCount=productElement.getCartItemCount();

	    Assert.assertEquals(cartCount, 3, "Cart should have 3 items");
	    productElement.clickCartIcon();
	    productElement.removeAddedProducts();
	    driver.get(inventoryURL);
	    
	}
	
	@Test(priority = 4)
	public void validateAllProductPrices() {
		
		ProductElement productElement=new ProductElement(driver);
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
	    	} 
	    	else {
	    		System.out.println("Mismatch: " + productName + " - Expected: " + expectedPrice + ", Found: " + actualPrice);
	    	}


	    	Assert.assertEquals(actualPrice, expectedPrice, "Price mismatch for " + productName);

	    }
	    
	}


	@Test(priority = 5)
	public void printAllProductsWithDetails() {
		
	    ProductElement productElement = new ProductElement(driver);
	    productElement.printAllProductDetails();
	}
	

	@Test(priority = 7)
	public void validateAllFilters() {
		
	    ProductElement productElement = new ProductElement(driver);
	    productElement.testAllFilters();
	}
	
//	@AfterSuite
//	public void tearDown() {
//	    if (driver != null) {
//	        driver.quit();
//	    }
//	}



}
