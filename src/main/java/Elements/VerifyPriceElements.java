package Elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VerifyPriceElements {
	WebDriver driver;

	public VerifyPriceElements(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

	@FindBy(xpath = "//*[@id=\"inventory_container\"]/div/div[1]/div[2]/div[2]/div")
    public WebElement backpackPriceOnProductPage;

	@FindBy(xpath = "//*[@id=\"inventory_container\"]/div/div[2]/div[2]/div[2]/div")
    public WebElement bikeLightPriceOnProductPage;
	
	@FindBy(xpath = "//*[@id=\"cart_contents_container\"]/div/div[1]/div[3]/div[2]/div[2]/div")
    public WebElement backpackPriceInCart;

	@FindBy(xpath = "//*[@id=\"cart_contents_container\"]/div/div[1]/div[4]/div[2]/div[2]/div")
    public WebElement bikeLightPriceInCart;

	public String getBackpackPriceOnProductPage() {
        return backpackPriceOnProductPage.getText();
    }

    public String getBikeLightPriceOnProductPage() {
        return bikeLightPriceOnProductPage.getText();
    }

    public String getBackpackPriceInCart() {
        return backpackPriceInCart.getText();
    }

    public String getBikeLightPriceInCart() {
        return bikeLightPriceInCart.getText();
    }


}
