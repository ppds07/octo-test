package SwagLabElement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOut1Element {
	
	WebDriver driver;
	@FindBy(xpath="//input[@id='first-name']")
	WebElement firstName;
	
	@FindBy(xpath="//input[@id='last-name']")
	WebElement lastName;
	
	@FindBy(xpath="//input[@id='postal-code']")
	WebElement postalCode;
	
	@FindBy(xpath="//input[@id='continue']")
	WebElement continueButton;
	
	@FindBy(xpath="//button[@id='cancel']")
	WebElement cancelButton;
	
	public void setFirstName(String firstname) {
		firstName.sendKeys(firstname);
	}
	public void setLastName(String lastname) {
		lastName.sendKeys(lastname);
	}
	public void setPostalCode(String postalcode) {
		postalCode.sendKeys(postalcode);
	}
	public void clickContinueButton() {
		continueButton.click();
	}
	public void clickCancelButton() {
		cancelButton.click();
	}
	public CheckOut1Element(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}

}
