package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SelectHotelPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By allHotelRadios = By.xpath("//input[@type='radio' and contains(@id,'radiobutton_')]");
    private By continueBtn = By.id("continue");

    public SelectHotelPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void selectHotelAndContinue() {
        try {
            // ✅ Wait for "Select Hotel" page title to confirm navigation
            wait.until(ExpectedConditions.titleContains("Select Hotel"));
            System.out.println("✅ Select Hotel page loaded.");

            // ✅ Wait for hotel radio buttons to appear
            List<WebElement> hotelRadios = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(allHotelRadios));

            if (hotelRadios.isEmpty()) {
                throw new RuntimeException("❌ No hotel radio buttons found on the page.");
            }

            // ✅ Click the first visible, clickable radio button
            WebElement firstHotel = hotelRadios.get(0);
            wait.until(ExpectedConditions.elementToBeClickable(firstHotel)).click();
            System.out.println("✅ Selected the first available hotel option.");

            // ✅ Click Continue
            WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(continueBtn));
            continueButton.click();
            System.out.println("✅ Clicked Continue button to go to booking page.");

        } catch (Exception e) {
            System.out.println("❌ Failed to select hotel or proceed: " + e.getMessage());
            throw e;
        }
    }
}
