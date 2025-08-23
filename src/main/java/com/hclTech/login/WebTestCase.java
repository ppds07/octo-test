package com.hclTech.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class WebTestCase {
	
	 
	public class Testng_1 {
		WebDriver d;
		@BeforeMethod
		public void launchBrowser() {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\settipallipav.kumar\\eclipse-workspace\\resources\\chromedriver-win64\\chromedriver.exe"); // Path to ChromeDriver
			d=new ChromeDriver();
			d.manage().window().maximize();
			
		}
		@Test
		public void testcase1() {
			d.get("https://www.saucedemo.com/");
			d.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("standard_usr");
	 
			d.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret_sauce");
	 
			d.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
			d.close();
	  }
		@Test(timeOut = 2000)
		public void testcase2() {
			d.get("https://www.saucedemo.com/");
			d.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("standar_d_user");
	 
			d.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret_sauce");
	 
			d.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
			d.close();
	  }
		@Test(timeOut = 2000)
		public void testcase3() {
			d.get("https://www.saucedemo.com/");
			d.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("standard_user#");
	 
			d.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret_sauce");
	 
			d.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
			d.close();
	  }
		@Test(timeOut = 2000)
		public void testcase4() {
			d.get("https://www.saucedemo.com/");
			d.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("standard_usEr");
	 
			d.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret_sauce");
	 
			d.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
			d.close();
	  }
		@Test(timeOut = 2000)
		public void testcase5() {
			d.get("https://www.saucedemo.com/");
			d.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("standard_User");
	 
			d.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret_sauce");
	 
			d.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
			d.close();
	  }
		@Test(timeOut = 2000)
		public void testcase6() {
			d.get("https://www.saucedemo.com/");
			d.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("Standard_user");
	 
			d.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret_sauce");
	 
			d.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
			d.close();
	  }
		@Test(timeOut = 2000)
		public void testcase7() {
			d.get("https://www.saucedemo.com/");
			d.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("standa@d_user");
	 
			d.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret_sauce");
	 
			d.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
			d.close();
	  }
		@Test(timeOut = 2000)
		public void testcase8() {
			d.get("https://www.saucedemo.com/");
			d.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("standarduser");
	 
			d.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret_sauce");
	 
			d.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
			d.close();
	  }
		@Test(timeOut = 2000)
		public void testcase9() {
			d.get("https://www.saucedemo.com/");
			d.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("stadard_user");
	 
			d.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret_sauce");
	 
			d.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
			d.close();
	  }
		@Test(timeOut = 2000)
		public void testcase10() {
			d.get("https://www.saucedemo.com/");
			d.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("standard_user");
	 
			d.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret_sauce");
	 
			d.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
	  }
	 
	  
	}
	 

}
