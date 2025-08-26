package SwagLabsTestCases;

import java.util.List;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import Common.BrowserFactory;
import service.LoginService;
import utils.ExcelReader;

public class LoginTestCase extends BrowserFactory {

    @Test(priority = 1)
    public void userLogin() {
        String filePath = System.getProperty("user.dir") + "/ExcelFile/SwagLabLoginData.xlsx";
        List<String[]> testData = ExcelReader.readSheetData(filePath, 0);

        LoginService loginService = new LoginService(driver);

        for (int i = 0; i < testData.size(); i++) {
            String[] row = testData.get(i);
            String username = row[0];
            String password = row[1];
            String expectedResult = row[2];

            loginService.performLoginTest(username, password, expectedResult);
            driver.get("https://www.saucedemo.com/");
        }
    }

    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
