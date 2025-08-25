package SwagLabElement;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutTwoElement {
	
	WebDriver driver;
	
	@FindBy(className="cart_item")
	List<WebElement> products;
	
	@FindBy(className="inventory_item_name")
	List<WebElement> productName;
	
	@FindBy(className="inventory_item_price")
	List<WebElement> productPrice;
	
	@FindBy(className="cart_quantity")
	List<WebElement> cartQuantity;
	
	@FindBy(className="summary_tax_label")
	WebElement tax;
	
	@FindBy(className="summary_total_label")
	WebElement total;
	
	@FindBy(xpath="//*[@id=\"finish\"]")
	WebElement finishButton1;
	
	@FindBy(xpath="//*[@id=\"cancel\"]")
	WebElement cancelButton;
	

	@FindBy(className="summary_value_label")
	List<WebElement> summaryLabels;


	public CheckOutTwoElement(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}

	public int getProductCount() {
		
        return products.size();

	}

	public boolean getProductName(int index) {
		
		return productName.get(index).isDisplayed();
	}

	public boolean getProductPrice(int index) {
		
		
		return productPrice.get(index).isDisplayed();
	}

	public boolean getProductQuantity(int index) {
		
		return cartQuantity.get(index).isDisplayed();
	}

	public boolean getTax() {
		
		return tax.isDisplayed();
	}

	public boolean getTotal() {
		
		return total.isDisplayed();
	}

	public void finishButton() {
		
		finishButton1.click();
		
	}

	public void clickCancelButton() {
		
		cancelButton.click();
	}
	
	public String getPaymentInfo(){
		
	    return summaryLabels.get(0).getText();
	}
	
	public String getShippingInfo(){
		
	    return summaryLabels.get(1).getText();
	}
	
	public List<Double> getProductPrices() {
		
	    List<Double> prices = new ArrayList<>();
	    for (WebElement priceElement : productPrice) {
	    	
	        String priceText = priceElement.getText().replace("$", "");
	        prices.add(Double.parseDouble(priceText));
	       
	    }
	    return prices;
	}

	public double getTaxAmount() {
	    String taxText = tax.getText().replace("Tax: $", "");
	    return Double.parseDouble(taxText);
	}

	public double getTotalAmount() {
	    String totalText = total.getText().replace("Total: $", "");
	    return Double.parseDouble(totalText);
	}


}
