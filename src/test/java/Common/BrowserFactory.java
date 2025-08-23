package Common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BrowserFactory {
	
public WebDriver driver;


	
	@BeforeSuite
	@Parameters("browser")
	public void setup(@Optional("chrome")String browser1) {
		
		
		if (browser1.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\rathi.prabhae\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
			ChromeOptions options= new ChromeOptions();
			options.addArguments("--disable-notifications");
			driver = new ChromeDriver(options);
		} 
		else if (browser1.equalsIgnoreCase("edge")) {
			
			System.setProperty("webdriver.edge.driver", "C:\\Users\\rathi.prabhae\\Downloads\\edgedriver_win64\\msedgedriver.exe");
			driver = new EdgeDriver();
		 
		}
		else {
			System.out.println("Invalid Browser"+browser1);
			throw new IllegalArgumentException("Browser not supported: " + browser1);
		}
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();
		
	 
	}
	

	@AfterSuite
	public void closeBrowser() {
	    driver.close();
	    
	}


    public WebDriver getDriver() {
        return driver;
    }


}
