package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.BaseTest;

public class LoginTest extends BaseTest {
    
    // ✅ Reusable login method (can be called from other tests)
    public void loginValidUser() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("deepa0918", "84OD62");
    }

    @Test(priority = 1)
    public void verifyValidLogin() {
        loginValidUser();

        // Validation — check if redirected to Search Hotel page
        String expectedUrl = "https://adactinhotelapp.com/SearchHotel.php";
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "Login failed!");
    }

    @Test(priority = 2)
    public void verifyInvalidLogin() {
        driver.get("https://adactinhotelapp.com/"); // Reset to login page

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("invalidUser", "invalidPass");

        // Validate error message
        String errorMsg = driver.findElement(By.xpath("//*[@id='login_form']/table/tbody/tr[5]/td[2]/div/b")).getText();
        Assert.assertTrue(errorMsg.contains("Invalid Login"), "Error message not displayed!");
    }
}
