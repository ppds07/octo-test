package Elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class cartIconCount {
	WebDriver driver;

	public cartIconCount(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

	@FindBy(className = "shopping_cart_badge")
		WebElement cartBadge;

    public WebElement getCartBadgeElement() {
        return cartBadge;
    }

    public String getCartBadgeCount() {
        return cartBadge.getText();
    }

}
