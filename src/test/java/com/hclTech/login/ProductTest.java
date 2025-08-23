package com.hclTech.login;
 
import java.time.Duration;

import org.openqa.selenium.*;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;

import org.testng.annotations.*;
 
public class  ProductTest{
 
    WebDriver driver;
 
    @BeforeClass

    public void launchBrowserAndLogin()  {

    	System.setProperty("webdriver.edge.driver", "C:\\Users\\anuku.vamsi\\Downloads\\edgedriver_win64\\Driver_Notes\\msedgedriver.exe");
    	driver = new EdgeDriver();
    	//driver.manage().window().maximize();
        driver.manage().window().maximize();

        driver.get("https://www.saucedemo.com/");
       
 
        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        driver.findElement(By.id("login-button")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

    }
 
    public void verifyProduct(String productName, String expectedPrice, String expectedDescription) throws InterruptedException {

        driver.findElement(By.xpath("//div[text()='" + productName + "']")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
 
        WebElement name = driver.findElement(By.className("inventory_details_name"));

        WebElement price = driver.findElement(By.className("inventory_details_price"));

        WebElement description = driver.findElement(By.className("inventory_details_desc"));
 
        boolean mismatch = false;
 
        if (!name.getText().equals(productName)) {

            mismatch = true;

            System.out.println("Name mismatch for: " + productName);

        }
 
        if (!price.getText().equals(expectedPrice)) {

            mismatch = true;

            System.out.println("Price mismatch for: " + productName);

        }
 
        if (!description.getText().equals(expectedDescription)) {

            mismatch = true;

            System.out.println("Description mismatch for: " + productName);

        }
 
        if (!mismatch) {

            System.out.println("PASS: " + productName);

        } else {

            Assert.fail("Mismatch found for: " + productName);

        }
 
        driver.navigate().back();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));


    }
 
    @Test(priority = 1)

    public void verifyAllProducts() throws InterruptedException {

        verifyProduct(

            "Sauce Labs Backpack",

            "$29.99",

            "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection."

        );
 
        verifyProduct(

            "Sauce Labs Bike Light",

            "$9.99",

            "A red light isn't the desired state in testing but it sure helps when riding your bike at night. Water-resistant with 3 lighting modes, 1 AAA battery included."

        );
 
        verifyProduct(

            "Sauce Labs Bolt T-Shirt",

            "$15.99",

            "Get your testing superhero on with the Sauce Labs bolt T-shirt. From American Apparel, 100% ringspun combed cotton, heather gray with red bolt."

        );
 
        verifyProduct(

            "Sauce Labs Fleece Jacket",

            "$49.99",

            "It's not every day that you come across a midweight quarter-zip fleece jacket capable of handling everything from a relaxing day outdoors to a busy day at the office."

        );
 
        verifyProduct(

            "Sauce Labs Onesie",

            "$7.99",

            "Rib snap infant onesie for the junior automation engineer in development. Reinforced 3-snap bottom closure, two-needle hemmed sleeved and bottom won't unravel."

        );
 
        verifyProduct(

            "Test.allTheThings() T-Shirt (Red)",

            "$15.99",

            "This classic Sauce Labs t-shirt has a timeless style, perfect for tech enthusiasts. 100% ringspun combed cotton, red with white print."

        );

    }
 
    @AfterClass

    public void tearDown() {

        if (driver != null) {

            driver.quit();

        }

    }

}

 