package Elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDescriptionElements {
	WebDriver driver;

	public ProductDescriptionElements(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	@FindBy(xpath = "//*[@id=\"cart_contents_container\"]/div/div[1]/div[3]/div[2]/div[1]")
	public WebElement backpackDescriptionInCart;

    @FindBy(xpath = "//*[@id=\"cart_contents_container\"]/div/div[1]/div[4]/div[2]/div[1]")
    public WebElement bikeLightDescriptionInCart;

    public String getBackpackDescriptionInCart() {
        return backpackDescriptionInCart.getText();
    }

    public String getBikeLightDescriptionInCart() {
        return bikeLightDescriptionInCart.getText();
    }


}
