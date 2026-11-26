package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class QE_TC_2598BB655AAFPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By searchBox = By.name("q"); // Google search textbox
    private By searchButton = By.name("btnK"); // Google Search button

    public QE_TC_2598BB655AAFPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open(String baseUrl) {
        driver.get(baseUrl);
        // Wait until the search box is visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
    }

    public void enterSearchText(String text) {
        WebElement box = wait.until(ExpectedConditions.elementToBeClickable(searchBox));
        box.clear();
        box.sendKeys(text);
    }

    public String getSearchText() {
        WebElement box = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
        return box.getAttribute("value");
    }

    public void clickSearchButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        button.click();
    }

    public boolean isSearchButtonEnabled() {
        WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(searchButton));
        return button.isEnabled();
    }
}
