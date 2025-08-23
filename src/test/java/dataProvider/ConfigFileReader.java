package dataProvider;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;
public class ConfigFileReader {
	
	 
	
			public void init()
			{
				WebDriver d;
				Properties prop = new Properties();
			    try {
			    	FileInputStream input = new FileInputStream("C:\\Users\\jeevaraj.shettys\\eclipse-workspace\\CucmberPractise\\src\\test\\resources\\config.properties");
			    	prop.load(input);
	 
			            String baseUrl = prop.getProperty("baseurl");
			            String username = prop.getProperty("username");
			            String password = prop.getProperty("password");
			            String browser = prop.getProperty("browser");
	 
			            System.out.println("Base URL: " + baseUrl);
			            System.out.println("Username: " + username);
			            System.out.println("Password: " + password);
			            System.out.println("Browser: " + browser);
			            
			            if(browser.equalsIgnoreCase("edge"))
			            {
			            System.setProperty("webdriver.edge.driver", "C:\\Users\\anuku.vamsi\\Downloads\\edgedriver_win64\\msedgedriver.exe");
						d=new EdgeDriver();
						d.manage().timeouts().implicitlyWait (Duration.ofSeconds(10));
						d.get(baseUrl);
						d.manage().window().maximize();
						
						d.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys(username);
						d.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(password);
						d.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
			            }
	 
			        } catch (IOException e) {
			            e.printStackTrace();
			        }
			}
		}

}
