package elements;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class LoginElement {
	
//	public static WebDriver driver;
	
	@FindBy(xpath = "//*[@id=\"user-name\"]")
	WebElement username;
	
	@FindBy(xpath = "//*[@id=\"password\"]")
	WebElement password;
	
	@FindBy(xpath = "//*[@id=\"login-button\"]")
	WebElement login_button;
	
	
	public void setUsername(String usrn) {
		username.clear(); 
		username.sendKeys(usrn);
		
	}
	 
	public void setPassword(String pwd) {
		password.sendKeys(pwd);
	}
	
	public void submitLogin() {
		login_button.submit();
	}
	
	public LoginElement(WebDriver driver) {
	PageFactory.initElements(driver, this);
		
	}
	
	
	

}
