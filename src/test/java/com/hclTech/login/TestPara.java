package com.hclTech.login;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
public class TestPara {
	WebDriver driver;
	@BeforeClass
	public void chromeBrowser() throws InterruptedException{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\anuku.vamsi\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe"); // Path to ChromeDriver
		driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();
		Thread.sleep(3000);
	}
	
	/*@Test
	void print()
	{
		
	}*/
	
	@Test
	@Parameters({"username","password"})
	public void loginTest(String un,String ps)  {
		driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys(un);
		   driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(ps);
		   driver.findElement(By.id("login-button")).click();
	}

}
