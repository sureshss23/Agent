package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class QE_TC_4DCEA1E01774Page {

    private final WebDriver driver;

    // Locators (adjust selectors to match the actual application)
    private final By downloadButton = By.id("downloadBtn");
    private final By pdfOption = By.xpath("//select[@id='formatSelect']/option[text()='PDF']");
    private final By confirmButton = By.id("confirmDownloadBtn");

    public QE_TC_4DCEA1E01774Page(WebDriver driver) {
        this.driver = driver;
    }

    public void clickDownloadButton() {
        WebElement button = driver.findElement(downloadButton);
        button.click();
    }

    public void selectPdfFormat() {
        WebElement option = driver.findElement(pdfOption);
        option.click();
    }

    public void confirmDownload() {
        WebElement confirm = driver.findElement(confirmButton);
        confirm.click();
        // Additional logic to handle OS-level download dialog can be added here if needed.
    }
}
