package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class QE_TC_C019D762263BPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators
    private final By searchBox = By.name("q");
    private final By searchButton = By.name("btnK");
    private final By resultsStats = By.id("result-stats"); // element present on results page

    public QE_TC_C019D762263BPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Opens the given URL.
     */
    public void open(String url) {
        driver.get(url);
        // Wait for the search box to be visible to ensure page is loaded
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
    }

    /**
     * Enters the provided query into the Google search textbox.
     */
    public void enterSearchQuery(String query) {
        WebElement box = wait.until(ExpectedConditions.elementToBeClickable(searchBox));
        box.clear();
        box.sendKeys(query);
        // Google dynamically loads the search button after typing; wait briefly for it to be clickable
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
    }

    /**
     * Clicks the Google Search button.
     */
    public void clickSearch() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        button.click();
    }

    /**
     * Checks whether the results page is displayed and contains the search query.
     */
    public boolean isResultsPageDisplayed(String query) {
        // Wait for the results stats element to appear, indicating the results page has loaded
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(resultsStats));
        } catch (Exception e) {
            return false;
        }
        // Verify that the page title contains the query (Google appends the query to the title)
        String title = driver.getTitle();
        return title != null && title.toLowerCase().contains(query.toLowerCase());
    }
}
