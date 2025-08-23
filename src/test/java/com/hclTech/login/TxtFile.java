package com.hclTech.login;


import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.*;
import java.time.Duration;
public class TxtFile {
	
	    WebDriver driver;
 
	    @BeforeClass
	    public void setup() {
	        System.setProperty("webdriver.edge.driver", "C:\\Users\\anuku.vamsi\\Downloads\\edgedriver_win64\\msedgedriver.exe");
	        driver = new EdgeDriver();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        driver.manage().window().maximize();
	    }
 
	    @Test
	    public void runLoginTestsFromTxt() throws IOException {
	        String txtFilePath = "C:\\Users\\anuku.vamsi\\Downloads\\login_test_data.txt";
	        BufferedReader reader = new BufferedReader(new FileReader(txtFilePath));
	        String line;
 
	        while ((line = reader.readLine()) != null) {
	            String[] parts = line.split(",");
	            if (parts.length != 3) continue;
 
	            String username = parts[0].trim();
	            String password = parts[1].trim();
	            String expectedResult = parts[2].trim();
 
	            testLogin(username, password, expectedResult);
	        }
 
	        reader.close();
	    }
 
	    public void testLogin(String username, String password, String expectedResult) {
	        driver.get("https://www.saucedemo.com/");
	        driver.findElement(By.id("user-name")).clear();
	        driver.findElement(By.id("user-name")).sendKeys(username);
	        driver.findElement(By.id("password")).clear();
	        driver.findElement(By.id("password")).sendKeys(password);
	        driver.findElement(By.id("login-button")).click();
 
	        boolean loginSuccess = driver.getCurrentUrl().contains("inventory.html");
 
	        if (expectedResult.equalsIgnoreCase("success")) {
	            assert loginSuccess : "Expected success but login failed for user: " + username;
	        } else {
	            assert !loginSuccess : "Expected failure but login succeeded for user: " + username;
	        }
 
	        if (loginSuccess) {
	            driver.findElement(By.id("react-burger-menu-btn")).click();
	            driver.findElement(By.id("logout_sidebar_link")).click();
	        }
	    }
 
	    @AfterClass
	    public void tearDown() {
	        driver.quit();
	    }
	}
