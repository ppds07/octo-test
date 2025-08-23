package com.hclTech.login;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class Testcases {
	WebDriver driver;
	@Test
	public void chromebrowser() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\anuku.vamsi\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
	}
	
	/*@Test
	public void login() {
		 driver.findElements(By.id("user-name")).sendKeys("standard_user");
		 driver.findElements(By.id("password")).sendkeys("hvdgbjn");
		 	
	}*/
	@Test(dependsOnMethods= {"chromebrowser"})
	public void signIn() {
		System.out.println("execute after chromebrowser");
		
	}

}
