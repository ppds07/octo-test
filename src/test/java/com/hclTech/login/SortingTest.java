 package com.hclTech.login;
 import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
 
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
 
public class SortingTest {
 
    WebDriver driver;
 
    @BeforeClass
    public void setUp() throws InterruptedException {
    	System.setProperty("webdriver.edge.driver", "C:\\Users\\anuku.vamsi\\Downloads\\edgedriver_win64\\Driver_Notes\\msedgedriver.exe");
    	driver = new EdgeDriver();
    	driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com");
    }
    	       	    public List<String> getProductNames() {
    	        List<WebElement> products = driver.findElements(By.className("inventory_item_name"));
    	        List<String> names = new ArrayList<>();
    	        for (WebElement p : products) {
    	            names.add(p.getText().trim());
    	        }
    	        return names;
    	    }

    	    // Utility to get product prices
    	    public List<Double> getProductPrices() {
    	        List<WebElement> products = driver.findElements(By.className("inventory_item_price"));
    	        List<Double> prices = new ArrayList<>();
    	        for (WebElement p : products) {
    	            prices.add(Double.parseDouble(p.getText().replace("$", "").trim()));
    	        }
    	        return prices;
    	    }

    	    @Test
    	    public void testSortByNameAToZ() throws InterruptedException {
    	        Select filter = new Select(driver.findElement(By.className("product_sort_container")));
    	        filter.selectByValue("az");

    	        List<String> actualNames = getProductNames();
    	        List<String> expectedNames = new ArrayList<>(actualNames);
    	        Collections.sort(expectedNames);

    	        Assert.assertEquals(actualNames, expectedNames, "Products are not sorted A to Z");
    	        System.out.println("✅ Products are correctly sorted from A to Z.");
    	        Thread.sleep(2000);
    	    }

    	    @Test
    	    public void testSortByNameZToA() throws InterruptedException {
    	        Select filter = new Select(driver.findElement(By.className("product_sort_container")));
    	        filter.selectByValue("za");

    	        List<String> actualNames = getProductNames();
    	        List<String> expectedNames = new ArrayList<>(actualNames);
    	        Collections.sort(expectedNames, Collections.reverseOrder());

    	        Assert.assertEquals(actualNames, expectedNames, "Products are not sorted Z to A");
    	        System.out.println("✅ Products are correctly sorted from Z to A.");
    	        Thread.sleep(2000);
    	    }

    	    @Test
    	    public void testSortByPriceLowToHigh() throws InterruptedException {
    	        Select filter = new Select(driver.findElement(By.className("product_sort_container")));
    	        filter.selectByValue("lohi");

    	        List<Double> actualPrices = getProductPrices();
    	        List<Double> expectedPrices = new ArrayList<>(actualPrices);
    	        Collections.sort(expectedPrices);

    	        Assert.assertEquals(actualPrices, expectedPrices, "Products are not sorted Low to High");
    	        System.out.println("✅ Products are correctly sorted by price from Low to High.");
    	        Thread.sleep(2000);
    	    }

    	    @Test
    	    public void testSortByPriceHighToLow() throws InterruptedException {
    	        Select filter = new Select(driver.findElement(By.className("product_sort_container")));
    	        filter.selectByValue("hilo");

    	        List<Double> actualPrices = getProductPrices();
    	        List<Double> expectedPrices = new ArrayList<>(actualPrices);
    	        expectedPrices.sort(Collections.reverseOrder());

    	        Assert.assertEquals(actualPrices, expectedPrices, "Products are not sorted High to Low");
    	        System.out.println("✅ Products are correctly sorted by price from High to Low.");
    	        Thread.sleep(2000);
    	    }

    	    @AfterClass
    	    public void tearDown() {
    	        driver.quit();
    	    }
    	}

 