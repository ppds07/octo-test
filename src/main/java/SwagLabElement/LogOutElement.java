package SwagLabElement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogOutElement {
	
	@FindBy(id="react-burger-menu-btn")
	WebElement menuButton;
	
	@FindBy(id="logout_sidebar_link")
	WebElement logoutLink;
	
	public LogOutElement(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
	public void clickLogout() {
		
		menuButton.click();
		logoutLink.click();
		System.out.println("User Logout");
	}
}
