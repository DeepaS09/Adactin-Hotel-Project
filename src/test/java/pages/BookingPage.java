package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BookingPage {

    private WebDriver driver;

    // Locators (updated for Adactin)
    private By locationDropdown = By.id("location");
    private By roomCountDropdown = By.id("room_nos");
    private By adultsPerRoomDropdown = By.id("adult_room");
    private By checkInInput = By.id("datepick_in");
    private By checkOutInput = By.id("datepick_out");
    private By searchButton = By.id("Submit");

    // Constructor
    public BookingPage(WebDriver driver) {
        this.driver = driver;
    }

    // Page Actions
    public void enterDestination(String location) {
        driver.findElement(locationDropdown).sendKeys(location);
    }

    public void enterNumberOfRooms(String rooms) {
        driver.findElement(roomCountDropdown).sendKeys(rooms);
    }

    public void enterAdultsPerRoom(String adults) {
        driver.findElement(adultsPerRoomDropdown).sendKeys(adults);
    }

    public void selectCheckInDate(String checkInDate) {
        driver.findElement(checkInInput).clear();
        driver.findElement(checkInInput).sendKeys(checkInDate);
    }

    public void selectCheckOutDate(String checkOutDate) {
        driver.findElement(checkOutInput).clear();
        driver.findElement(checkOutInput).sendKeys(checkOutDate);
    }

    public void clickSearch() {
        driver.findElement(searchButton).click();
    }

    public String getConfirmationMessage() {
        // After clicking search, you’ll be redirected to “Select Hotel” page
        // Let’s confirm that navigation
        return driver.getTitle(); // e.g. “Adactin.com - Select Hotel”
    }
}
