package SwagLabsTestCases;

import org.testng.annotations.Test;

import Common.BrowserFactory;
import SwagLabElement.LoginElement;
import SwagLabElement.MyCartElement;
import SwagLabElement.ProductElement;

public class MyCartTestCase extends BrowserFactory {
	
	@Test
	public void myCartFunction() {
		LoginElement loginElement=new LoginElement(driver);
		loginElement.setUsername("standard_user");
		loginElement.setPassword("secret_sauce");
		loginElement.setLoginButton();
		System.out.println("SwagLabs Login successfull - username: standard_user and password: secret_sauce");
		
		ProductElement productElement=new ProductElement(driver);
		productElement.clickCartIcon();
		System.out.println("ProductPage CartIcon is clicked");
		MyCartElement cartElement=new MyCartElement(driver);
		
		cartElement.clickCheckOutButton();
		System.out.println("MyCart checkout is clicked");
		
		
	}

}
