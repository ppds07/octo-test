package common;

import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

public class BrowserFactory {
	public WebDriver driver;
	@Test
	public void launchBrowser() {
	       Scanner sc = new Scanner(System.in);
	       System.out.print("Enter browser name (chrome/edge): ");
	       String browser = sc.nextLine();
	       if (browser.equalsIgnoreCase("chrome")) {
	    	   System.setProperty("webdriver.chrome.driver","drivers//chromedriver.exe");
	           driver = new ChromeDriver();
	       } else if (browser.equalsIgnoreCase("edge")) {
	           System.setProperty("webdriver.edge.driver", "drivers//msedgedriver.exe");
	           driver = new EdgeDriver();
	       } else {
	           System.out.println("Invalid browser name.");
	           sc.close();
	           return;
	       }
	       driver.manage().window().maximize();
	       driver.get("https://www.google.com");
	       System.out.println("Title: " + driver.getTitle());
	       System.out.println();
	       sc.close();
	   }
	
}
