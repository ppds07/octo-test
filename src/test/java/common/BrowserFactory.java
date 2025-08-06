package common;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BrowserFactory {
public static WebDriver driver;
	 
	public void setup(String browser, String url) 
	{
	 
	if (browser.equalsIgnoreCase("chrome")) 
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Projects\\POC\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
	} else if (browser.equalsIgnoreCase("firefox")) 
	{ 
		driver = new FirefoxDriver();
	} else if (browser.equalsIgnoreCase("edge")) 
	{
		System.setProperty("webdriver.edge.driver", "C:\\Projects\\POC\\drivers\\msedgedriver.exe");
		driver = new EdgeDriver();
	} else 
	{
	throw new IllegalArgumentException("Browser not supported");
	}
	
	driver.get(url);
	 
	driver.manage().window().maximize();
	 
	}

}
