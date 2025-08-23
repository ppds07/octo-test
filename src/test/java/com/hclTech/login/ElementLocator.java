package com.hclTech.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ElementLocator {
	public static String applyLoan(WebDriver driver,String pageUrl,LoanDetails loandetails) {
		driver.get(pageUrl);
		driver.findElement(By.xpath(" ")).sendKeys(loandetails.getFirstName());
		driver.findElement(By.xpath(" ")).sendKeys(loandetails.getLastName());
		driver.findElement(By.xpath(" ")).sendKeys(loandetails.getEmail());
		driver.findElement(By.xpath(" ")).sendKeys(loandetails.getLoantype());
		driver.findElement(By.xpath(" ")).sendKeys(String.valueOf(loandetails.getDuration()));
		
		driver.findElement(By.xpath(" ")).submit();
		String res=driver.findElement(By.tagName("body")).getText();
		return res;
		
		}
	public static List<WebElement> notText(WebDriver driver,String pageUrl){
		driver.get(pageUrl);
		return driver.findElements(By.xpath("//form//input[@type!='text']"));
	}
	public static List<WebElement> idConatct(WebDriver driver,String pageUrl){
		driver.get(pageUrl);
		return driver.findElements(By.xpath("//form//input[starts-with(@name,'contact')"));
	}
	public static List<WebElement> submit(WebDriver driver,String pageUrl){
		driver.get(pageUrl);
		return driver.findElements(By.xpath("//form//button[@id='submit'] "));
	}
	public static List<WebElement> missing(WebDriver driver,String pageUrl){
		driver.get(pageUrl);
		return driver.findElements(By.xpath("//form//input[not('@id')] "));
	}
	public static List<String> scarpeVulnerabilities(WebDriver driver,String pageUrl,String wpVersion){
		driver.get(pageUrl);
		}

}
