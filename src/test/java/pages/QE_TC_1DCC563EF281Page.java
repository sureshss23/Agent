package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class QE_TC_1DCC563EF281Page {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Actions actions;

    // Locators
    private final By searchBox = By.name("q");
    private final By feelingLuckyButton = By.name("btnI");
    private final By gmailLink = By.xpath("//a[contains(text(),'Gmail')]");

    public QE_TC_1DCC563EF281Page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.actions = new Actions(driver);
    }

    @Step("Open Google home page at {url}")
    public void openHomePage(String url) {
        driver.get(url);
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
    }

    @Step("Enter search text: {text}")
    public void enterSearchText(String text) {
        WebElement box = wait.until(ExpectedConditions.elementToBeClickable(searchBox));
        box.clear();
        box.sendKeys(text);
    }

    @Step("Submit search via ENTER key")
    public void submitSearch() {
        WebElement box = driver.findElement(searchBox);
        box.sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.titleContains("- Google Search"));
    }

    @Step("Assert that the results page title contains: {expected}")
    public void assertResultsContain(String expected) {
        wait.until(ExpectedConditions.titleContains(expected));
    }

    @Step("Navigate back to Google home page")
    public void goToHomePage() {
        driver.navigate().to("https://www.google.com");
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
    }

    @Step("Clear the search box")
    public void clearSearchBox() {
        WebElement box = driver.findElement(searchBox);
        box.clear();
    }

    @Step("Click the \"I\'m Feeling Lucky\" button")
    public void clickFeelingLucky() {
        WebElement luckyBtn = wait.until(ExpectedConditions.elementToBeClickable(feelingLuckyButton));
        luckyBtn.click();
        // Wait for navigation to complete – simple heuristic
        wait.until(driver -> !driver.getCurrentUrl().contains("google.com/search"));
    }

    @Step("Click the Gmail navigation link")
    public void clickGmailLink() {
        // Gmail link is located below the search box on the Google homepage
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(gmailLink));
        link.click();
        // Switch to new tab if opened
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
        wait.until(ExpectedConditions.titleContains("Gmail"));
    }

    @Step("Assert the current page title contains: {text}")
    public void assertPageTitleContains(String text) {
        wait.until(ExpectedConditions.titleContains(text));
    }
}
