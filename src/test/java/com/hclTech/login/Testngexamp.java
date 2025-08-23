package com.hclTech.login;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class Testngexamp {
	
	WebDriver driver = new ChromeDriver();
	@BeforeSuite
	public  void launchBrowser() {
	
	 
	 System.setProperty("webdriver.chrome.driver", "C:\\Users\\anuku.vamsi\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe"); // Path to ChromeDriver
     
     driver.manage().window().maximize();
     //Actions actions=new Actions(driver1);

     driver.get("https://www.tutorialspoint.com/selenium/practice/text-box.php");
	}
		@BeforeClass
    public void beforeClass() {
        System.out.println("Before Class: Setup for BasicTest.");
    }

	@Test
	public void testToSkip() {
		driver.findElement(By.xpath("//*[@id=\"fullname\"]")).sendKeys("vamsi");
	}
	@Test
	public void testToSkipss() {
		((WebElement) driver.findElements(By.xpath("//*[@id=\"password\"]"))).sendKeys("Tirupati");
	}
	
	@Ignore
	@Test
	public void testToSkips() {
		System.out.println("Ignored using @Ignore");
		driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("anuku");
		
	}
	 	

}
