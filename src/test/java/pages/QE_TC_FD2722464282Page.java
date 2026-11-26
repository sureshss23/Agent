package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Page Object representing the Google Search homepage for the test case QE‑TC‑FD2722464282.
 */
public class QE_TC_FD2722464282Page {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators – these are generic placeholders; adjust as needed for the actual application
    private final By searchBox = By.name("q");
    private final By searchButton = By.name("btnK");
    private final By validationMessage = By.id("validation-message"); // hypothetical element

    public QE_TC_FD2722464282Page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Opens the base URL. Not used directly because the test navigates via driver.get(...).
     */
    public void open(String baseUrl) {
        driver.get(baseUrl);
    }

    /**
     * Enters the provided query into the search textbox.
     */
    public void enterSearchQuery(String query) {
        WebElement box = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
        box.clear();
        box.sendKeys(query);
    }

    /**
     * Clicks the Google Search button.
     */
    public void clickSearch() {
        // The search button may be hidden until the input is non‑empty; wait for it to be clickable
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        button.click();
    }

    /**
     * Presses the ENTER key while the search box is focused.
     */
    public void pressEnter() {
        WebElement box = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
        box.sendKeys(Keys.ENTER);
    }

    /**
     * Returns the validation message text, if displayed.
     */
    public String getValidationMessage() {
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(validationMessage));
        return message.getText();
    }

    /**
     * Checks whether the validation message element is displayed.
     */
    public boolean isValidationMessageDisplayed() {
        try {
            WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(validationMessage));
            return message.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Simple helper to verify that the search box contains any value (used for sanity check).
     */
    public boolean isSearchBoxValuePresent() {
        try {
            WebElement box = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
            return !box.getAttribute("value").isEmpty();
        } catch (TimeoutException e) {
            return false;
        }
    }
}
