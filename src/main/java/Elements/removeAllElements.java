package Elements;

import java.util.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class removeAllElements {
	WebDriver driver;

    public removeAllElements(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[text()='Remove']")
    public List<WebElement> removeButtons;

    public void removeAllItems() {
        for (WebElement button : removeButtons) {
            button.click();
        }
    }

    public boolean isCartEmpty() {
        return removeButtons.isEmpty();
    }

}
