package com.hclTech.login;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.*;
class WebCases{
	WebDriver driver;
	@BeforeMethod(groups= {"smoke"})
	public void LaunchBrowser() {
		System.setProperty("webdriver.chrome.driver","C:\\\\Users\\\\anuku.vamsi\\\\Downloads\\\\chromedriver-win64\\\\chromedriver-win64\\\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();
	}

@Test
public void testValidLogin() {
    driver.findElement(By.id("user-name")).sendKeys("standard_user");
    driver.findElement(By.id("password")).sendKeys("secret_sauce");
    driver.findElement(By.id("login-button")).click();
}
@Test
public void testinValidLogin() {
driver.findElement(By.id("user-name")).sendKeys("");
driver.findElement(By.id("password")).sendKeys("");
driver.findElement(By.id("login-button")).click();
System.out.println("invalid login");}




	@Test(groups= {"smoke"})
	public void sendUsername() throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("standard_user");
		Thread.sleep(10000);
	}
	@Test(groups= {"smoke","sanity"})
	public void sendPassword() throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret_sauce");
		Thread.sleep(10000);
	}
	@Test(groups= {"regression"})
	public void LoginButton() throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
		Thread.sleep(10000);
	}
	@AfterMethod
	public void drivercloser()  {
		driver.close();
	}
}
