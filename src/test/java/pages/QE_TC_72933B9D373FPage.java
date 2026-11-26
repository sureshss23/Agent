package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Page Object representing the search page used in QE_TC_72933B9D373F.
 */
public class QE_TC_72933B9D373FPage {
    private final WebDriver driver;
    private final By searchBox = By.name("q"); // Google search box name
    private final By suggestions = By.cssSelector("ul[role='listbox'] li");
    private final By clearButton = By.cssSelector("button[aria-label='Clear']"); // Adjust if needed

    public QE_TC_72933B9D373FPage(WebDriver driver) {
        this.driver = driver;
        // Wait for the search box to be visible before interacting
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(searchBox));
    }

    public void clickSearchBox() {
        driver.findElement(searchBox).click();
    }

    public void typeIntoSearchBox(String text) {
        WebElement box = driver.findElement(searchBox);
        box.clear();
        box.sendKeys(text);
    }

    public void clearSearchBoxUsingBackspace() {
        WebElement box = driver.findElement(searchBox);
        box.sendKeys(Keys.BACK_SPACE);
    }

    public void clickClearButton() {
        try {
            driver.findElement(clearButton).click();
        } catch (NoSuchElementException e) {
            // Fallback: clear via sendKeys if explicit clear button is not present
            driver.findElement(searchBox).clear();
        }
    }

    public List<String> getSuggestions() {
        try {
            List<WebElement> elems = driver.findElements(suggestions);
            return elems.stream()
                    .map(WebElement::getText)
                    .collect(Collectors.toList());
        } catch (NoSuchElementException e) {
            return java.util.Collections.emptyList();
        }
    }

    public String getSearchBoxValue() {
        return driver.findElement(searchBox).getAttribute("value");
    }
}
