package Elements;

import java.util.*;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class cartPageElements {
	WebDriver driver;
	public List<String> addedProducts = new ArrayList<>();

	public cartPageElements(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

	@FindBy(id = "add-to-cart-sauce-labs-backpack")
    WebElement addBackpack;
	
	@FindBy(id = "add-to-cart-sauce-labs-bike-light")
    WebElement bikelight;

	@FindBy(className = "shopping_cart_link")
    WebElement cartIcon;

	@FindBy(className = "inventory_item_name")
    List<WebElement> cartItems;

	public void addBackpackToCart() {
        addBackpack.click();
        addedProducts.add("Sauce Labs Backpack");
    }
	public void addBikeLight() {
		bikelight.click();
		addedProducts.add("Sauce Labs Bike Light");
	}

	public void clickCartIcon() {
        cartIcon.click();
    }

	public List<String> getCartProductNames() {
        return cartItems.stream().map(WebElement::getText).collect(Collectors.toList());
    }

	public List<String> getAddedProducts() {
        return addedProducts;
    }

}
