package SwagLabsTestCases;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Common.BrowserFactory;
import SwagLabElement.CheckOutOneElement;
import SwagLabElement.CheckOutTwoElement;
import SwagLabElement.ConfirmationPageElement;
import SwagLabElement.LogOutElement;
import SwagLabElement.LoginElement;
import SwagLabElement.MyCartElement;
import SwagLabElement.ProductElement;
import utils.ScreenshotService;

public class ConfirmationPageTestCase extends BrowserFactory{

	@BeforeClass
	public void LoginProcessForConfirmationPage() throws InterruptedException {
		
		LoginElement obj2=new LoginElement(driver);
		obj2.setUsername("standard_user");
		obj2.setPassword("secret_sauce");
		obj2.setLoginButton();
		Thread.sleep(3000);
		
		ProductElement productElement=new ProductElement(driver);
		productElement.addTwoProduct();
		productElement.clickCartIcon();
		
		MyCartElement cartElement=new MyCartElement(driver);
		cartElement.clickCheckOutButton();
		
		CheckOutOneElement checkOne=new CheckOutOneElement(driver);
		checkOne.setFirstName("Priyadharshan");
		checkOne.setLastName("P");
		checkOne.setPostalCode("600119");
		checkOne.clickContinueButton();
		
		CheckOutTwoElement checkTwo=new CheckOutTwoElement(driver);
		checkTwo.finishButton();
	}
	@Test(priority = 1)
	public void ThankYouPagescreenshot() throws IOException {
		
		ScreenshotService.captureScreenshot(driver, "thankyouPage");
		
	}
	@Test(priority = 2)
	public void validateThankYouPage() {
		
		ConfirmationPageElement confirm=new ConfirmationPageElement(driver);
		
		confirm.clickBackHomeButton();
		
		String currentUrl = driver.getCurrentUrl();
		Assert.assertTrue(currentUrl.contains("inventory.html"), "Back Home button did not redirect to products page");
		
	}
	@Test(priority=3)
	public void validateLogout() {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn")));

		LogOutElement logout = new LogOutElement(driver);
		logout.clickLogout();
		
		String currentUrl = driver.getCurrentUrl();
		Assert.assertTrue(currentUrl.contains("saucedemo.com"),"LogOut Function not redirected to login page");
		
	}
	
	@AfterSuite
	public void tearDown() {
	    if (driver != null) {
	        driver.quit();
	    }
	}
}
