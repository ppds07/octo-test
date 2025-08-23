package com.hclTech.login;

import java.awt.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class  DashboardLinksValidator {
	public static List<String> findAllDashboardLinks(WebDriver driver, String homePageUrl){
		
		List<String> lst=new ArrayList<>();
		return lst;
	}
	
	
	public void loginsetUp() {
		WebDriver driver;
		System.setProperty("webdriver.edge.driver", "C:\\Users\\anuku.vamsi\\Downloads\\edgedriver_win64\\Driver_Notes\\msedgedriver.exe");
		driver=new EdgeDriver();
		WebElement pageURL=driver.get("");
		
	}

}
