package Elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class login_elements {
	WebDriver driver;
	// Constructor to initialize WebDriver
    public login_elements(WebDriver driver) {
      
        PageFactory.initElements(driver, this);
    }
	
    @FindBy(id="user-name")
    WebElement usernameField;
    
    @FindBy(id="password")
    WebElement passwordField;
    
    @FindBy(id="login-button")
    WebElement button;
    
//    @FindBy(id="add-to-cart-sauce-labs-backpack")
//    WebElement pro1;
//    
//    @FindBy(id="add-to-cart-sauce-labs-bike-light")
//    WebElement pro2;
    
    public void login(String username,String password) {
    	usernameField.sendKeys(username);
    	passwordField.sendKeys(password);
    	button.click();
//    	pro1.click();
//    	pro2.click();
    }
    
}
