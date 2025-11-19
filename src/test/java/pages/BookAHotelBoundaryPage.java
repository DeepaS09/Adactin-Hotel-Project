package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class BookAHotelBoundaryPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Same locators reused
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

    public BookAHotelBoundaryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void fillBoundaryBookingDetails(String firstName, String lastName, String address,
                                           String ccNum, String ccType, String expMonth,
                                           String expYear, String cvv) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));

        driver.findElement(firstNameInput).sendKeys(firstName);
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(addressInput).sendKeys(address);
        driver.findElement(ccNumInput).sendKeys(ccNum);
        new Select(driver.findElement(ccTypeDropdown)).selectByVisibleText(ccType);
        new Select(driver.findElement(ccExpMonthDropdown)).selectByVisibleText(expMonth);
        new Select(driver.findElement(ccExpYearDropdown)).selectByVisibleText(expYear);
        driver.findElement(cvvInput).sendKeys(cvv);
    }

    public void clickBookNow() {
        driver.findElement(bookNowBtn).click();
    }

    public String getOrderNumber() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(orderNumber)).getAttribute("value");
    }
}
