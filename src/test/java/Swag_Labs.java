
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
 
public class Swag_Labs {
	WebDriver driver;
 
	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.edge.driver", "C:\\Users\\anuku.vamsi\\Downloads\\edgedriver_win64\\msedgedriver.exe");
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com");
	}
	
	@Test
	public void login() {
		WebElement uName = driver.findElement(By.id("user-name"));
		uName.sendKeys("standard_user");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		WebElement password = driver.findElement(By.id("password")) ;
		password.sendKeys("secret_sauce");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		WebElement Login = driver.findElement(By.id("login-button"));
		Login.submit();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
	}
	
	@Test(dependsOnMethods= {"login"})
	public void inventoryPage() throws InterruptedException {
		
		WebElement addBackpack = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
		addBackpack.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
//		driver.switchTo().alert().accept();
		
		
		Thread.sleep(5000);
		
		WebElement removeBackpack = driver.findElement(By.id("remove-sauce-labs-backpack"));
		removeBackpack.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		
		WebElement BikeLight = driver.findElement(By.xpath("//div[@class='inventory_item_name ']"));
		BikeLight.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		WebElement addLight = driver.findElement(By.id("add-to-cart"));
		addLight.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		WebElement removeLight = driver.findElement(By.id("remove"));
		removeLight.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		driver.navigate().back();
		
		WebElement addTshirt = driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt"));
		addTshirt.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		WebElement cart = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
//		cart.click();
		
//		WebElement removeTshirt = driver.findElement(By.id("remove-sauce-labs-bolt-t-shirt"));
//		removeTshirt.click();
		
//		driver.navigate().back();
//		Thread.sleep(3000);
		
		WebElement jacket = driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket"));
		jacket.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
//		WebElement cart1 = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
		cart.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		WebElement removeTshirt = driver.findElement(By.id("remove-sauce-labs-bolt-t-shirt"));
 
		removeTshirt.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
	}
	
 
	
	@Test(dependsOnMethods= {"inventoryPage","login"})
	public void checkout() {
		WebElement chkout = driver.findElement(By.id("checkout"));
		chkout.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		WebElement firstName = driver.findElement(By.id("first-name"));
		firstName.sendKeys("Reddy");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		WebElement lastName = driver.findElement(By.id("last-name"));
		lastName.sendKeys("M");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		WebElement postalcode = driver.findElement(By.id("postal-code"));
		postalcode.sendKeys("524002");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		WebElement continu = driver.findElement(By.id("continue"));
		continu.click();
		
		
	}
	
	@Test(dependsOnMethods= {"checkout"})
	public void checkoutOverview() {
		WebElement finish = driver.findElement(By.id("finish"));
		finish.click();
	}
	
	@Test(dependsOnMethods= {"checkoutOverview"})
	public void navHome() {
		WebElement home = driver.findElement(By.id("back-to-products"));
		home.click();
	}
	
	@Test(dependsOnMethods= {"navHome"})
	public void logout() {
		WebElement menu =driver.findElement(By.id("react-burger-menu-btn"));
		menu.click();
		
		WebElement sideLogout = driver.findElement(By.id("logout_sidebar_link"));
		sideLogout.click();
	}
	
	@AfterClass
	public void teardown() {
		if(driver != null) {
			driver.close();
		}
	}
	
}
