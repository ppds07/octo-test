package Testcases;

import java.util.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import Elements.cartPageElements;
import Elements.login_elements;
import common.BrowserFactory;

public class loginTest {
	public static WebDriver driver;
	public static login_elements logintest;
	
	@BeforeClass
    public void setUp() {
        BrowserFactory bf = new BrowserFactory();
        bf.launchBrowser();
        driver = bf.driver;
        driver.get("https://www.saucedemo.com/");
        logintest = new login_elements(driver);
        
        logintest.login("standard_user","secret_sauce");
    }
	
}
