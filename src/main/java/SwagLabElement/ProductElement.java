package SwagLabElement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductElement {
	
	WebDriver driver;
	@FindBy(xpath="//a[@class='shopping_cart_link']")
	WebElement cartIcon;
	
	@FindBy(xpath="//button[@data-test='add-to-cart-sauce-labs-backpack']")
	WebElement addSingleProduct;
	public void clickCartIcon() {
		
		try {
			 String countText = cartIcon.getText();
	         int c=Integer.parseInt(countText);
	         if(c>0) {
	        	 cartIcon.click();
	         }
	         else {
	        	 throw new IllegalStateException("Cart is empty. Cannot proceed to Mycart page");
	         }
	         
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void addSingleProduct() {
		
		addSingleProduct.click();
	}
	public ProductElement(WebDriver driver) {
		
		PageFactory.initElements(driver,this);
	}
}
