package tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Tag;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.QE_TC_22A91C6CEFE6Page;

public class QE_TC_22A91C6CEFE6Test {
    private WebDriver driver;
    private QE_TC_22A91C6CEFE6Page page;

    @BeforeEach
    public void setUp() {
        // Initialize WebDriver (ChromeDriver as an example). Ensure the driver executable is in PATH or set via WebDriverManager.
        driver = new ChromeDriver();
        String baseUrl = System.getenv("QE_BASE_URL");
        driver.get(baseUrl);
        page = new QE_TC_22A91C6CEFE6Page(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Tag("smoke")
    @DisplayName("Validate allowed export formats for reports")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Confirm that the system only allows exporting reports in PDF and CSV formats, rejecting any other requested format per the export format business rule.")
    public void testExportFormats() {
        // Export PDF
        page.exportReport("PDF");
        Assertions.assertTrue(page.isFileDownloaded("PDF"), "PDF file should be downloaded");

        // Export CSV
        page.exportReport("CSV");
        Assertions.assertTrue(page.isFileDownloaded("CSV"), "CSV file should be downloaded");

        // Attempt unsupported format
        page.exportReport("XLSX");
        Assertions.assertTrue(page.isErrorMessageDisplayed("Export format not supported"),
                "Error message for unsupported format should be displayed");
    }

    // QE-TC:QE-TC-22A91C6CEFE6:v1
}
