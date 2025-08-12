package SwagLabElement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyCartElement {
	
	WebDriver driver;
	@FindBy(xpath="//button[@id='checkout']")
	WebElement checkoutButton;
	
	@FindBy(xpath="//span[@class='shopping_cart_badge']")
	WebElement countCartItems;
	
	public void clickCheckOutButton() {
		
		checkoutButton.click();
	}
	public int checkCartItems() {
		
		try {
            String countText = countCartItems.getText();
            return Integer.parseInt(countText);
        } catch (Exception e) {
            return 0;
        }

	}
	public MyCartElement(WebDriver driver) {
		
		PageFactory.initElements(driver,this);
	}
}
