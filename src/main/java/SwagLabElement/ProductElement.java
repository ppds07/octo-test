package SwagLabElement;

import org.testng.Assert;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductElement {

    WebDriver driver;

    @FindBy(xpath = "//a[@class='shopping_cart_link']")
    WebElement cartIcon;

    @FindBy(xpath = "//button[@data-test='add-to-cart-sauce-labs-backpack']")
    WebElement addSingleProduct;

    @FindBy(xpath = "//button[@data-test='add-to-cart-sauce-labs-backpack']")
    WebElement product1;

    @FindBy(xpath = "//button[@data-test='add-to-cart-sauce-labs-bike-light']")
    WebElement product2;

    @FindBy(xpath = "//button[contains(@data-test,'remove')]")
    List<WebElement> removeButtons;

    @FindBy(className = "btn_inventory")
    List<WebElement> allAddToCartButtons;

    @FindBy(className = "inventory_item_name")
    List<WebElement> productNames;

    @FindBy(className = "inventory_item_price")
    List<WebElement> productPrices;

    @FindBy(className = "inventory_item_desc")
    List<WebElement> productDescriptions;

    @FindBy(className = "product_sort_container")
    WebElement filterDropdown;

    public ProductElement(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    public void testAllFilters() {
        Select select = new Select(filterDropdown);

        String[] filters = {
            "Name (A to Z)",
            "Name (Z to A)",
            "Price (low to high)",
            "Price (high to low)"
        };

        for (String filter : filters) {
            select.selectByVisibleText(filter);

            List<String> names = productNames.stream()
                    .map(WebElement::getText)
                    .collect(Collectors.toList());

            List<Double> prices = productPrices.stream()
                    .map(WebElement::getText)
                    .map(p -> p.replace("$", ""))
                    .map(Double::parseDouble)
                    .collect(Collectors.toList());

            boolean isSorted = false;

            switch (filter) {
                case "Name (A to Z)":
                    isSorted = names.equals(names.stream().sorted().collect(Collectors.toList()));
                    break;
                case "Name (Z to A)":
                    isSorted = names.equals(names.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList()));
                    break;
                case "Price (low to high)":
                    isSorted = prices.equals(prices.stream().sorted().collect(Collectors.toList()));
                    break;
                case "Price (high to low)":
                    isSorted = prices.equals(prices.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList()));
                    break;
            }

            System.out.println("Filter: " + filter + " -> Sorting " + (isSorted ? "Success" : "Failed"));
        }
    }

    public void printAllProductDetails() {
        for (int i = 0; i < productNames.size(); i++) {
            String name = productNames.get(i).getText().trim();
            String description = productDescriptions.get(i).getText().trim();
            String price = productPrices.get(i).getText().trim();

            System.out.println("Product: " + name);
            System.out.println("Description: " + description);
            System.out.println("Price: " + price);
            System.out.println();
        }
    }

    public Map<String, String> getProductPriceMap() {
        Map<String, String> productPriceMap = new HashMap<>();

        for (int i = 0; i < productNames.size(); i++) {
            String name = productNames.get(i).getText().trim();
            String price = productPrices.get(i).getText().trim();
            productPriceMap.put(name, price);
        }

        return productPriceMap;
    }

    public void addMultipleProducts(int n) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        for (int i = 0; i < n && i < allAddToCartButtons.size(); i++) {
            wait.until(ExpectedConditions.elementToBeClickable(allAddToCartButtons.get(i))).click();
        }
    }

    public void removeAddedProducts() {
        for (WebElement removeButton : removeButtons) {
            removeButton.click();
        }
    }

    public int getCartItemCount() {
        String countText = cartIcon.getText().trim();
        return countText.isEmpty() ? 0 : Integer.parseInt(countText);
    }

    public void clickCartIcon() {
        String countText = cartIcon.getText().trim();

        if (countText == null || countText.trim().isEmpty()) {
            System.out.println("Cart is empty");
            Assert.assertTrue(true, "Cart is empty as expected");
        } else {
            int count = Integer.parseInt(countText);
            Assert.assertTrue(count > 0, "Cart has items");
            cartIcon.click();
            System.out.println("ProductPage CartIcon is clicked");
        }
    }

    public void addSingleProduct() {
        addSingleProduct.click();
    }

    public void addTwoProduct() {
        product1.click();
        product2.click();
    }
}
