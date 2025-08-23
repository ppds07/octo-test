package Elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class removeButtonElements {
	WebDriver driver;

    public removeButtonElements(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id=\"remove-sauce-labs-backpack\"]")
    public WebElement removeBackpackButton;

    public void clickRemoveBackpack() {
        removeBackpackButton.click();
    }

    public boolean isBackpackRemoved() {
        try {
            return !removeBackpackButton.isDisplayed();
        } catch (Exception e) {
            return true;
        }
    }

}
