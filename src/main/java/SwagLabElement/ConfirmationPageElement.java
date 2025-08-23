package SwagLabElement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPageElement {
	
	
	@FindBy(id="back-to-products")
	WebElement backhomebtn;
	
	public void clickBackHomeButton() {
		
		backhomebtn.click();
		
	}
	public ConfirmationPageElement(WebDriver driver){
		PageFactory.initElements(driver,this);
	}

}
