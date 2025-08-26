package SwagLabElement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class CheckOutOneElement {

    WebDriver driver;

    @FindBy(xpath = "//input[@id='first-name']")
    WebElement firstName;

    @FindBy(xpath = "//input[@id='last-name']")
    WebElement lastName;

    @FindBy(xpath = "//input[@id='postal-code']")
    WebElement postalCode;

    @FindBy(xpath = "//input[@id='continue']")
    WebElement continueButton;

    @FindBy(xpath = "//button[@id='cancel']")
    WebElement cancelButton;

    public CheckOutOneElement(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setFirstName(String firstname) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(firstName));
        firstName.clear();
        firstName.sendKeys(firstname);
    }

    public void setLastName(String lastname) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(lastName));
        lastName.clear();
        lastName.sendKeys(lastname);
    }

    public void setPostalCode(String postalcode) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(postalCode));
        postalCode.clear();
        postalCode.sendKeys(postalcode);
    }

    public void clickContinueButton() {
        continueButton.click();
    }

    public void clickCancelButton() {
        cancelButton.click();
    }
}
