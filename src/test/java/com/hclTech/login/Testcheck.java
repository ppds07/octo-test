package com.hclTech.login;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Testcheck {
	
	WebDriver driver;
	@BeforeClass
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\anuku.vamsi\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe"); 
		driver=new ChromeDriver();
		driver.get("https://www.flipkart.com/");
	}
	@Test(priority=1)
	public void verifyHomePage() {
		String title=driver.getTitle();
		Assert.assertTrue(title.contains("Flipkart"),"Home page title does not contain 'Flipkart'");
	}
	@Test(priority=2)
		public void loginTest() {
		
			
		}
	}
	
	


