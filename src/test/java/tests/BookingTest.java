package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.BookingPage;
import utils.BaseTest;

public class BookingTest extends BaseTest {

    @Test(priority = 1)
    public void performLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("deepa0918", "84OD62");
    }

    @Test(priority = 2, dependsOnMethods = "performLogin")
    public void verifyBookingIsSuccessful() throws InterruptedException {
        BookingPage bookingPage = new BookingPage(driver);

        bookingPage.enterDestination("Sydney");
        bookingPage.enterNumberOfRooms("2 - Two");
        bookingPage.enterAdultsPerRoom("2 - Two");
        bookingPage.selectCheckInDate("10/11/2025");
        bookingPage.selectCheckOutDate("15/11/2025");
        bookingPage.clickSearch();

        Thread.sleep(2000);

        String pageTitle = bookingPage.getConfirmationMessage();
        Assert.assertTrue(pageTitle.contains("Select Hotel"),
                "Booking step did not navigate to Select Hotel page!");
    }
}
