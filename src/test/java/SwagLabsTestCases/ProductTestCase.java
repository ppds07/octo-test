package SwagLabsTestCases;
import org.testng.annotations.Test;

import Common.BrowserFactory;
import SwagLabElement.LoginElement;
import SwagLabElement.ProductElement;

public class ProductTestCase extends BrowserFactory {
	
	
	@Test
	public void clickCartIcon() throws InterruptedException {
		
		LoginElement obj1=new LoginElement(driver);
		obj1.setUsername("standard_user");
		obj1.setPassword("secret_sauce");
		obj1.setLoginButton();
		Thread.sleep(3000);
		
		ProductElement productElement=new ProductElement(driver);
		productElement.addSingleProduct();
		productElement.clickCartIcon();
		
		
	}
}
