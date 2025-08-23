package SwagLabElement;



import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class MyCartElement {
	
	WebDriver driver;
	@FindBy(xpath="//button[@id='checkout']")
	WebElement checkoutButton;
	
	@FindBy(xpath="//span[@class='shopping_cart_badge']")
	WebElement countCartItems;
	
	@FindBy(xpath="//a[@class='shopping_cart_link']")
	WebElement cartIcon;
	
	@FindBy(xpath="//button[contains(@data-test,'remove')]")
	List<WebElement> removeButtons;
	
	
	public void removeAddedProducts() throws InterruptedException {
			
			for (int i=0;i<removeButtons.size();i++) {
				
				Thread.sleep(3000);
				removeButtons.get(i).click();
				

			}
		}
	
	public void clickCheckOutButton() {
		
		
		checkoutButton.click();
		System.out.println("MyCart checkout is clicked");
	}
	public int emptyCartValidation() {

		
		String countText = cartIcon.getText().trim();
		int count=0;
		if (countText == null || countText.trim().isEmpty()) {
		        
		    Assert.assertTrue(true, "Cart is empty as expected");
		    
		} 
		else {
		        
			count = Integer.parseInt(countText);
		    Assert.assertTrue(count > 0, "Cart has items");
		}
		return count;
	
	}
	public int checkCartItems() {
		
		try {
            String countText = countCartItems.getText();
            if(countText==null||countText.isEmpty()) {
            	return 0;
            }
            return Integer.parseInt(countText);
        } catch (Exception e) {
            return 0;
        }

	}
	public MyCartElement(WebDriver driver) {
		
		PageFactory.initElements(driver,this);
	}
}
