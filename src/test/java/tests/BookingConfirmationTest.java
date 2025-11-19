package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.BookingPage;
import pages.SelectHotelPage;
import pages.BookAHotelPage;
import utils.BaseTest;

public class BookingConfirmationTest extends BaseTest {

    @Test(priority = 1, description = "Verify full hotel booking flow from login to confirmation")
    public void verifyFullBookingFlow() {
        try {
            // Step 1: Login
            LoginPage loginPage = new LoginPage(driver);
            loginPage.loginAs("deepa0918", "84OD62");
            System.out.println("✅ Logged in successfully.");

            // Step 2: Search for Hotel
            BookingPage bookingPage = new BookingPage(driver);
            bookingPage.enterDestination("Sydney");
            bookingPage.enterNumberOfRooms("1 - One");
            bookingPage.enterAdultsPerRoom("2 - Two");
            bookingPage.selectCheckInDate("10/11/2025");
            bookingPage.selectCheckOutDate("15/11/2025");
            bookingPage.clickSearch();
            System.out.println("✅ Hotel search completed successfully.");

            // Step 3: Select Hotel
            SelectHotelPage selectHotelPage = new SelectHotelPage(driver);
            selectHotelPage.selectHotelAndContinue();
            System.out.println("✅ Hotel selected and continued to booking page.");

            // Step 4: Fill Booking Details
            BookAHotelPage bookAHotelPage = new BookAHotelPage(driver);
            bookAHotelPage.fillBookingDetails(
                    "Deepa", "Kannan", "Sydney, Australia",
                    "1234567812345678", "VISA",
                    "February", "2026", "123"
            );
            System.out.println("✅ Booking form filled successfully.");

            // Step 5: Submit Booking
            bookAHotelPage.clickBookNow();
            System.out.println("✅ 'Book Now' button clicked.");

            // Step 6: Validate Confirmation
            String orderNumber = bookAHotelPage.getOrderNumber();

            // Assertions
            Assert.assertNotNull(orderNumber, "❌ Booking failed — Order number not generated!");
            Assert.assertFalse(orderNumber.isEmpty(), "❌ Order number field is empty!");

            System.out.println("🎉 Booking successful! ✅ Order No: " + orderNumber);

        } catch (Exception e) {
            // Log the stack trace for better visibility
            e.printStackTrace();

            // Fail the test with a clear message
            Assert.fail("❌ Test failed due to exception: " + e.getMessage());
        }
    }
}
