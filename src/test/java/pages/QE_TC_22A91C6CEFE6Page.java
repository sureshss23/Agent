package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class QE_TC_22A91C6CEFE6Page {
    private WebDriver driver;

    // Example locators – adjust to actual application DOM
    private By exportButton = By.id("exportBtn");
    private By formatDropdown = By.id("formatSelect");
    private By confirmButton = By.id("confirmExport");
    private By errorMessage = By.id("exportError");
    private By downloadSuccessToast = By.id("downloadSuccess");

    public QE_TC_22A91C6CEFE6Page(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Triggers the export action for the given format.
     * Supported formats: PDF, CSV. Any other value will be treated as an unsupported request.
     */
    public void exportReport(String format) {
        driver.findElement(exportButton).click();
        WebElement dropdown = driver.findElement(formatDropdown);
        dropdown.click();
        // Select the option matching the visible text
        dropdown.findElement(By.xpath(".//option[normalize-space()='" + format + "']")).click();
        driver.findElement(confirmButton).click();
    }

    /**
     * Checks whether a download success toast appears after an export.
     * This is a placeholder implementation; replace with actual file-system verification if needed.
     */
    public boolean isFileDownloaded(String format) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(drv -> drv.findElement(downloadSuccessToast).isDisplayed());
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Verifies that the expected error message is displayed for unsupported formats.
     */
    public boolean isErrorMessageDisplayed(String expectedMessage) {
        try {
            WebElement error = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(drv -> drv.findElement(errorMessage));
            return error.getText().contains(expectedMessage);
        } catch (TimeoutException e) {
            return false;
        }
    }
}
