import java.io.IOException;
import java.time.Duration;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;
public class autoit {
	WebDriver driver;
	@Test
	  public void auto() throws InterruptedException {
		System.setProperty("webdriver.edge.driver", "C:\\Users\\anuku.vamsi\\Downloads\\edgedriver_win64\\Driver_Notes\\msedgedriver.exe");
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.tutorialspoint.com/selenium/practice/selenium_automation_practice.php");
		Thread.sleep(10000);
		String scriptPath="C:\\Users\\anuku.vamsi\\OneDrive - HCL TECHNOLOGIES LIMITED\\Desktop\\New AutoIt v3 Script (3).exe";
	
	 
		ProcessBuilder builder = new ProcessBuilder(scriptPath);
	
		builder.redirectErrorStream(true); 
	 
	        try {
	        
	            Process process = builder.start();
	            
	        
	            process.waitFor();
	            
	        } catch (IOException | InterruptedException e) {
	            e.printStackTrace();
	        }
	 
	  }
}