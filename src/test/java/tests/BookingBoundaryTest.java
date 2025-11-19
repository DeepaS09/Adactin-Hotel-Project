package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utils.BaseTest;

public class BookingBoundaryTest extends BaseTest {

    @Test(description = "Verify booking with max-length input data (boundary test)")
    public void verifyBoundaryInputBooking() {
        try {
            // Step 1: Login
            LoginPage loginPage = new LoginPage(driver);
            loginPage.loginAs("deepa0918", "84OD62");
            System.out.println("✅ Logged in successfully.");

            // Step 2: Search hotel
            BookingPage bookingPage = new BookingPage(driver);
            bookingPage.enterDestination("Sydney");
            bookingPage.enterNumberOfRooms("1 - One");
            bookingPage.enterAdultsPerRoom("2 - Two");
            bookingPage.selectCheckInDate("20/11/2025");
            bookingPage.selectCheckOutDate("22/11/2025");
            bookingPage.clickSearch();

            // Step 3: Select hotel
            SelectHotelPage selectHotelPage = new SelectHotelPage(driver);
            selectHotelPage.selectHotelAndContinue();

            // Step 4: Fill long inputs (boundary)
            BookAHotelBoundaryPage boundaryPage = new BookAHotelBoundaryPage(driver);
            boundaryPage.fillBoundaryBookingDetails(
                "AAAAAAAAAAAAAAAAAAAAAAAAA", // 25-char max
                "BBBBBBBBBBBBBBBBBBBBBBBBB",
                "This is a really long address input that should test field limits",
                "4111111111111111", "VISA",
                "February", "2026", "123"
            );

            boundaryPage.clickBookNow();

            String orderNo = boundaryPage.getOrderNumber();
            Assert.assertNotNull(orderNo, "Order number was not generated!");
            System.out.println("🎉 Boundary input booking successful. Order No: " + orderNo);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("❌ Boundary input booking test failed: " + e.getMessage());
        }
    }
}
