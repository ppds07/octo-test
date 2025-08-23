package Testcases;

import java.time.Duration;
import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Elements.ProductDescriptionElements;
import Elements.VerifyPriceElements;
import Elements.cartIconCount;
import Elements.cartPageElements;
import Elements.checkOutElements;
import Elements.continueShopping;
import Elements.removeAllElements;
import Elements.removeButtonElements;

public class CartPage extends loginTest{
	cartPageElements cartPage;
	
	@BeforeClass
    public void initCartPage() {
        cartPage = new cartPageElements(driver);
    }

	@Test(priority = 1)
    public void addProductToCart() {
        cartPage.addBackpackToCart();
        cartPage.addBikeLight();
    }
	
	@Test(priority = 2, dependsOnMethods = "addProductToCart")
    public void checkCartPageOpened() {
        cartPage.clickCartIcon();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("cart.html"), "Cart page did not open!");
        System.out.println("Cart page opened successfully: " + currentUrl);
        System.out.println();
    }
	
	@Test(priority = 3,dependsOnMethods = "checkCartPageOpened")
	public void verifyCartIconCount() {
		cartIconCount c=new cartIconCount(driver);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(c.getCartBadgeElement()));
		
		String badgeCount = c.getCartBadgeCount();
		Assert.assertEquals(badgeCount, "2", "Cart badge count mismatch!");

		System.out.println("Cart badge shows: " + badgeCount + " items.");
	}

	@Test(priority = 4, dependsOnMethods = "verifyCartIconCount")
    public void verifyCartItems() {
        cartPage.clickCartIcon();
        List<String> productNames = cartPage.getCartProductNames();
        List<String> expectedProducts = cartPage.getAddedProducts();

        Assert.assertFalse(productNames.isEmpty(), "Cart is empty!");
        
        System.out.println();
        System.out.println("Products currently in cart:");
        for (String expected : expectedProducts) {
        	Assert.assertTrue(productNames.contains(expected), expected + " not found in cart!");
        	System.out.println("- " + expected);
        }
        System.out.println();
    }
	

	@Test(priority = 5, dependsOnMethods = "verifyCartItems")
	public void verifyProductPricesInCart() {
		VerifyPriceElements pricePage = new VerifyPriceElements(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		driver.navigate().to("https://www.saucedemo.com/inventory.html");
		
		wait.until(ExpectedConditions.visibilityOf(pricePage.backpackPriceOnProductPage));
		wait.until(ExpectedConditions.visibilityOf(pricePage.bikeLightPriceOnProductPage));

		String backpackPriceProductPage = pricePage.getBackpackPriceOnProductPage();
		String bikeLightPriceProductPage = pricePage.getBikeLightPriceOnProductPage();
		
		driver.findElement(By.className("shopping_cart_link")).click();
		
		wait.until(ExpectedConditions.visibilityOf(pricePage.backpackPriceInCart));
		wait.until(ExpectedConditions.visibilityOf(pricePage.bikeLightPriceInCart));

		String backpackPriceCartPage = pricePage.getBackpackPriceInCart();
		String bikeLightPriceCartPage = pricePage.getBikeLightPriceInCart();

		Assert.assertEquals(backpackPriceCartPage, backpackPriceProductPage, "Backpack price mismatch!");
		Assert.assertEquals(bikeLightPriceCartPage, bikeLightPriceProductPage, "Bike Light price mismatch!");

		System.out.println("Prices matched:");
		System.out.println("- Sauce Labs Backpack: " + backpackPriceCartPage);
		System.out.println("- Sauce Labs Bike Light: " + bikeLightPriceCartPage);
		System.out.println();
	}
	
	@Test(priority = 6, dependsOnMethods = "verifyProductPricesInCart")
	public void verifyProductDescription() {
		ProductDescriptionElements p=new ProductDescriptionElements(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOf(p.backpackDescriptionInCart));
		wait.until(ExpectedConditions.visibilityOf(p.bikeLightDescriptionInCart));
		
		String backpackDesc = p.getBackpackDescriptionInCart();
		String bikeLightDesc = p.getBikeLightDescriptionInCart();
		
		Assert.assertTrue(backpackDesc.contains("carry"), "Backpack description seems incorrect!");
		Assert.assertTrue(bikeLightDesc.contains("light"), "Bike Light description seems incorrect!");

		System.out.println("Product descriptions verified:");
		System.out.println("- Backpack: " + backpackDesc);
		System.out.println("- Bike Light: " + bikeLightDesc);
		System.out.println();
	}
	
	@Test(priority = 7, dependsOnMethods = "verifyProductDescription")
	public void verifyRemoveButtonForBackpack() {
		removeButtonElements removePage = new removeButtonElements(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOf(removePage.removeBackpackButton));

		removePage.clickRemoveBackpack();

		Assert.assertTrue(removePage.isBackpackRemoved(), "Backpack was not removed from cart!");

		System.out.println("Backpack removed successfully from cart.");
		System.out.println();
	}
	
	@Test(priority = 8, dependsOnMethods = "verifyRemoveButtonForBackpack")
	public void verifyCheckout() {
		checkOutElements checkoutPage = new checkOutElements(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOf(checkoutPage.getCheckoutButtonElement()));

		checkoutPage.clickCheckoutButton();

		String currentUrl = driver.getCurrentUrl();
		Assert.assertTrue(currentUrl.contains("checkout-step-one.html"), "Checkout page did not open!");
		System.out.println("Verifying Checkout Button:");
		System.out.println("Successfully navigated to checkout page: " + currentUrl);
		System.out.println();
	}
	
	@Test(priority = 9, dependsOnMethods = "verifyCheckout")
	public void verifyContinueShopping() {
		driver.navigate().to("https://www.saucedemo.com/cart.html");

		continueShopping continuePage = new continueShopping(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOf(continuePage.getContinueShoppingButtonElement()));
		continuePage.clickContinueShopping();

		String currentUrl = driver.getCurrentUrl();
		Assert.assertTrue(currentUrl.contains("inventory.html"), "Continue Shopping did not navigate to product page!");
		System.out.println("Verifying Continue Shopping Button:");
		System.out.println("Successfully navigated back to product page: " + currentUrl);
		System.out.println();
	}

	@Test(priority = 10, dependsOnMethods = "verifyContinueShopping")
	public void removeAllProducts() {
		driver.navigate().to("https://www.saucedemo.com/cart.html");

		removeAllElements removeAll = new removeAllElements(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOfAllElements(removeAll.removeButtons));
		removeAll.removeAllItems();

		boolean empty = removeAll.isCartEmpty();
		Assert.assertTrue(empty, "Cart is not empty after removing all items!");

		System.out.println("All products removed. Cart is empty.");
		System.out.println();
	}

}
