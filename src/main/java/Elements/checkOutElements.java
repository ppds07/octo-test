package Elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class checkOutElements {
	WebDriver driver;

    public checkOutElements(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "checkout")
    public WebElement checkoutButton;

    public void clickCheckoutButton() {
        checkoutButton.click();
    }

    public WebElement getCheckoutButtonElement() {
        return checkoutButton;
    }

}
