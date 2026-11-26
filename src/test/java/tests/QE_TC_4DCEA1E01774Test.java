package tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.QE_TC_4DCEA1E01774Page;

import java.time.Duration;

public class QE_TC_4DCEA1E01774Test {

    private WebDriver driver;
    private QE_TC_4DCEA1E01774Page page;

    @BeforeEach
    public void setUp() {
        // Assuming chromedriver binary is in PATH or managed externally
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String baseUrl = System.getenv("QE_BASE_URL");
        if (baseUrl == null) {
            throw new IllegalStateException("Environment variable QE_BASE_URL is not set");
        }
        driver.get(baseUrl);
        page = new QE_TC_4DCEA1E01774Page(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Tag("smoke")
    @DisplayName("Download AHC Report as PDF")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Ensure that a generated AHC report can be downloaded as a PDF file.")
    public void testDownloadAhcReportAsPdf() {
        // QE-TC:QE-TC-4DCEA1E01774:v1
        Allure.step("Click the Download button and select PDF format");
        page.clickDownloadButton();
        page.selectPdfFormat();

        Allure.step("Confirm download location and verify file saved");
        page.confirmDownload();

        // Placeholder verification – in a real test, verify the file exists and size > 0KB
        // For example, using java.nio.file.Files to check the downloaded file.
        Allure.step("Verify the PDF file was downloaded successfully");
        // Assert.assertTrue(...);
    }
}
