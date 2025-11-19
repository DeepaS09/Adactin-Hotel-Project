package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.BaseTest;

public class BookAHotelPage extends BaseTest {

    private WebDriverWait wait;

    private By firstNameInput = By.id("first_name");
    private By lastNameInput = By.id("last_name");
    private By addressInput = By.id("address");
    private By ccNumInput = By.id("cc_num");
    private By ccTypeDropdown = By.id("cc_type");
    private By ccExpMonthDropdown = By.id("cc_exp_month");
    private By ccExpYearDropdown = By.id("cc_exp_year");
    private By cvvInput = By.id("cc_cvv");
    private By bookNowBtn = By.id("book_now");
    private By orderNumber = By.id("order_no");

    public BookAHotelPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Wait for booking form to be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));
        System.out.println("✅ Booking form loaded successfully.");
    }

    public void fillBookingDetails(String firstName, String lastName, String address,
                                   String ccNum, String ccType, String expMonth,
                                   String expYear, String cvv) {

        WebElement firstNameField = wait.until(ExpectedConditions.elementToBeClickable(firstNameInput));
        firstNameField.clear();
        firstNameField.sendKeys(firstName);

        WebElement lastNameField = wait.until(ExpectedConditions.elementToBeClickable(lastNameInput));
        lastNameField.clear();
        lastNameField.sendKeys(lastName);

        WebElement addressField = wait.until(ExpectedConditions.elementToBeClickable(addressInput));
        addressField.clear();
        addressField.sendKeys(address);

        WebElement ccNumField = wait.until(ExpectedConditions.elementToBeClickable(ccNumInput));
        ccNumField.clear();
        ccNumField.sendKeys(ccNum);

        new Select(wait.until(ExpectedConditions.elementToBeClickable(ccTypeDropdown))).selectByVisibleText(ccType);
        new Select(wait.until(ExpectedConditions.elementToBeClickable(ccExpMonthDropdown))).selectByVisibleText(expMonth);
        new Select(wait.until(ExpectedConditions.elementToBeClickable(ccExpYearDropdown))).selectByVisibleText(expYear);

        WebElement cvvField = wait.until(ExpectedConditions.elementToBeClickable(cvvInput));
        cvvField.clear();
        cvvField.sendKeys(cvv);

        WebElement bookBtn = wait.until(ExpectedConditions.elementToBeClickable(bookNowBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", bookBtn);
    }

    public void clickBookNow() {
        wait.until(ExpectedConditions.elementToBeClickable(bookNowBtn)).click();
    }

    public String getOrderNumber() {
        WebElement orderElement = wait.until(ExpectedConditions.visibilityOfElementLocated(orderNumber));
        return orderElement.getAttribute("value");
    }
}
