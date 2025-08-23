package com.hclTech.login;
import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.ArrayList;
import java.util.*;

import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
public class TutorialsPoint {
	
	public static void main(String[] args) {
		
		System.setProperty("webdriver.edge.driver", "C:\\Users\\anuku.vamsi\\Downloads\\edgedriver_win64\\Driver_Notes\\msedgedriver.exe");
		WebDriver driver=new EdgeDriver();
		driver.manage().window().maximize();
		String pageUrl="https://www.tutorialspoint.com/selenium/practice/webtables.php";
		//driver.get(pageUrl);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		List<String> names=Arrays.asList("Kierra","Alden");
		returnNames(driver,pageUrl,names);
	}
	public static void returnNames(WebDriver driver,String pageUrl,List<String> names){
		driver.get(pageUrl);
		//driver.findElement(By.xpath("//*[@id='navMenus']/li[4]")).click();
		List<WebElement> rows=driver.findElements(By.xpath("//table//tr[position()>1]"));
		//List<String> names=new ArrayList<>();
		for(WebElement row:rows) {
			List<WebElement> cells=row.findElements(By.tagName("td"));
			//System.
			//if (!cells.isEmpty()) {
				//names.add(cells.get(0).getText());
				String firstname=cells.get(0).getText();
			
			if(names.contains(firstname)) {
				row.findElement(By.xpath("/td[7]/a[2]/svg")).click();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
			}
		}
				
		
	}

}
