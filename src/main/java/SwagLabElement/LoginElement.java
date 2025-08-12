package SwagLabElement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginElement {
	
	WebDriver driver;
	@FindBy(id="user-name")
	WebElement username;
	
	@FindBy(id="password")
	WebElement password;
	
	@FindBy(id="login-button")
	WebElement loginbutton;
	
	public void setUsername(String username1) {
		username.sendKeys(username1);
	}
	
	public void setPassword(String passcode) {
		password.sendKeys(passcode);
	}
	
	public void setLoginButton() {
		loginbutton.click();
	}
	
	public LoginElement(WebDriver driver){
		PageFactory.initElements(driver,this);
	}
	
}
