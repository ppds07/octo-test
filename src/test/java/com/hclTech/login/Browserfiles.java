package com.hclTech.login;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
 
public class Browserfiles {
	static WebDriver driver;
	
	@Parameters("browser")
	
	@BeforeClass
	
	public void setup(String browser) {
	
	if (browser.equalsIgnoreCase("chrome")) {
	
	driver = new ChromeDriver();
	
	
	} else if (browser.equalsIgnoreCase("firefox")) {
	
	driver = new FirefoxDriver();
	
	} else if (browser.equalsIgnoreCase("edge")) {
	
	driver = new EdgeDriver();
	
	} else {
	
	throw new IllegalArgumentException("Browser not supported");
	
	}
	
	driver.manage().window().maximize();
	
	}
	
	@Test
	
	public void openApp() {
	
	driver.get("https://www.google.com");
	
	System.out.println("Title: " + driver.getTitle());
	
	}
	
	@AfterClass
	
	public void tearDown() {
	
		driver.quit();
	}
	
	
}