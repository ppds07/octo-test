package testcases;

import org.testng.annotations.*;

import common.BrowserFactory;
import elements.LoginElement;

public class LoginTestCase extends BrowserFactory{
	
//	@BeforeMethod
//	@Parameters({"browser","url"})
//	public void initt()
//	{
//		setup("chrome","https://saucedemo.com");
//	}
	
	@Test
	public void testcase1() {
		setup("chrome", "https://www.saucedemo.com");
		LoginElement le=new LoginElement(driver);
		le.setUsername("standard_user");
		le.setPassword("secret_sauce");
		le.submitLogin();
		
	}
	
	@Test(dataProvider = "excelData",dataProviderClass = testData.ExcelDataProvider.class)
	public void testExcel(String username, String pwd)
	{
		setup("chrome", "https://www.saucedemo.com");
		LoginElement le = new LoginElement(driver);
		le.setUsername(username);
		le.setPassword(pwd);
		le.submitLogin();
	}

}
