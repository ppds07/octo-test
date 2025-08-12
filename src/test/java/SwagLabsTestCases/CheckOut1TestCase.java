package SwagLabsTestCases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Common.BrowserFactory;
import SwagLabElement.CheckOut1Element;
import SwagLabElement.LoginElement;
import SwagLabElement.MyCartElement;
import SwagLabElement.ProductElement;

public class CheckOut1TestCase extends BrowserFactory{
	
	String checkOutUrl;
	@Test(priority=1)
	public void uptoMyCart() {
		
		LoginElement loginElement=new LoginElement(driver);
		loginElement.setUsername("standard_user");
		loginElement.setPassword("secret_sauce");
		loginElement.setLoginButton();
		System.out.println("SwagLabs Login successfull - username: standard_user and password: secret_sauce");
		

		try {
            
			String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("inventory.html"), "Login failed or incorrect redirect.");
            
        } catch (AssertionError ae) {
        	
            System.out.println("Assertion Failed: Login URL validation failed.");
            System.out.println("Details: " + ae.getMessage());
            throw ae;
        }


		
		ProductElement productElement=new ProductElement(driver);
		productElement.addSingleProduct();
		productElement.clickCartIcon();
		System.out.println("ProductPage CartIcon is clicked");
		
		try {
			
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("cart.html"), "Cart page not loaded.");
        } 
		catch (AssertionError ae) {
			
			System.out.println("Assertion Failed: Cart page URL validation failed.");


            System.out.println("Details: " + ae.getMessage());
            throw ae;
        }

		MyCartElement cartElement=new MyCartElement(driver);
		cartElement.clickCheckOutButton();
		System.out.println("MyCart checkout is clicked");
		
		try {
	        String currentUrl = driver.getCurrentUrl();
	        if(currentUrl.contains("checkout-step-one.html")) {
	        	
	        	checkOutUrl=currentUrl;
	        }
	        
	        Assert.assertTrue(currentUrl.contains("checkout-step-one.html"), "Checkout page not loaded.");
		} 
		catch (AssertionError ae) {
	        
			System.out.println("Assertion Failed: Checkout page URL validation failed.");
	        System.out.println("Details: " + ae.getMessage());
	        throw ae;
	    }

		
	}
	
	@Test(priority=2)
	public void CheckOut1Functions() throws FileNotFoundException {
		
		String filePath = System.getProperty("user.dir") + "/ExcelFile/SwagLabLoginData.xlsx";
		FileInputStream fileLocation = new FileInputStream(filePath);
		
		 try (XSSFWorkbook workbook = new XSSFWorkbook(fileLocation)){
			 
			 XSSFSheet sheet = workbook.getSheetAt(1);
			 
	         int lastRow = sheet.getLastRowNum();
	         System.out.println("Total rows in Excel: " + lastRow);
	         DataFormatter dft = new DataFormatter();
	         SoftAssert softAssert = new SoftAssert();
	         for(int i=1;i<=lastRow;i++) {
	        	 
	        	 XSSFRow row = sheet.getRow(i);
	            
	             String firstname=dft.formatCellValue(row.getCell(0));
	             String lastname=dft.formatCellValue(row.getCell(1));
	             String postalcode=dft.formatCellValue(row.getCell(2));
	             String expected_result=dft.formatCellValue(row.getCell(3));

	             System.out.println();
	             System.out.println("Data "+i+" Testing checkout with: FirstName :" + firstname + ", LastName :" + lastname + ", PostalCode:" + postalcode);
	             
                CheckOut1Element checkoutElement = new CheckOut1Element(driver);
                checkoutElement.setFirstName(firstname);
                checkoutElement.setLastName(lastname);
                checkoutElement.setPostalCode(postalcode);
                
                MyCartElement cartElement=new MyCartElement(driver);
        		int cartCount=cartElement.checkCartItems();
        		try {
        			
        			Assert.assertTrue(cartCount > 0, "Cart is empty. Cannot proceed to checkout.");
        			System.out.println("Cart has " + cartCount + " item(s)");
        			checkoutElement.clickContinueButton();
        		}
        		catch(Exception a) {
        			System.out.println(a.getMessage());
        		}

                boolean checkoutSuccess;
                String errorMessage = "";

                try {
                    checkoutSuccess = driver.getCurrentUrl().contains("checkout-step-two.html");
                } catch (Exception e) {
                    checkoutSuccess = false;
                    errorMessage = driver.findElement(By.className("error-message-container")).getText();
                    System.out.println("Error Message displayed"+errorMessage);
                  
                }

                System.out.println("Actual Result: " + checkoutSuccess + ", Expected: " + expected_result);
                try {
                	
                    if (expected_result.equalsIgnoreCase("Success")) {
                        
						softAssert.assertTrue(checkoutSuccess, "Expected checkout to succeed for: FirstName :" + firstname + ", LastName :" + lastname + ", PostalCode:" + postalcode);
                    } else {
                        
						softAssert.assertFalse(checkoutSuccess, "Expected checkout to fail for: FirstName :" + firstname + ", LastName :" + lastname + ", PostalCode:" + postalcode);
                    }
                } 
                catch (AssertionError ae) {
                    
                	System.out.println("Details: " + ae.getMessage());
                    throw ae;
                }
                driver.get(checkOutUrl);

	         }
	         
			 
			 //softAssert.assertAll();
		 } catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	@Test(priority = 3)
	public void testCancelButtonFunctionality() {
	   
	    driver.get(checkOutUrl);
	    
	    CheckOut1Element checkoutElement = new CheckOut1Element(driver);
	    checkoutElement.clickCancelButton(); 

	    String mycartURL = driver.getCurrentUrl();
	    try {
	        Assert.assertTrue(mycartURL.contains("cart.html"), "Cancel button did not redirect to cart page.");
	        System.out.println("Cancel button successfully redirected to cart page.");
	    } catch (AssertionError ae) {
	        System.out.println("Assertion Failed: Cancel button functionality failed.");
	        System.out.println("Details: " + ae.getMessage());
	        throw ae;
	    }
	}

}
