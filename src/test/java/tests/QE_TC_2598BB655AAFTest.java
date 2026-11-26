package tests;

import io.qameta.allure.Feature;


import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.qameta.allure.*;

import pages.QE_TC_2598BB655AAFPage;

@Epic("Search Functionality")
@Feature("Search textbox boundary length handling")
public class QE_TC_2598BB655AAFTest {

    private WebDriver driver;
    private QE_TC_2598BB655AAFPage page;

    @BeforeEach
    public void setUp() {
        // Assuming chromedriver is available in the system PATH
        driver = new ChromeDriver();
        page = new QE_TC_2598BB655AAFPage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Tag("smoke")
    @DisplayName("Verify search textbox handles max length input")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Enter 2048 characters, verify accepted; then 2049 characters, verify truncation or error")
    public void testSearchTextboxBoundary() {
        // QE-TC:QE-TC-2598BB655AAF:v1
        String baseUrl = System.getenv("QE_BASE_URL");
        Assertions.assertNotNull(baseUrl, "QE_BASE_URL environment variable must be set");
        page.open(baseUrl);

        // Step 1: Input exactly 2048 characters
        String maxInput = "a".repeat(2048);
        page.enterSearchText(maxInput);
        Assertions.assertEquals(maxInput, page.getSearchText(), "Search textbox should contain full 2048 characters");
        Assertions.assertTrue(page.isSearchButtonEnabled(), "Search button should be enabled for max length input");
        page.clickSearchButton();
        // Additional verification for results page can be added here.

        // Step 3: Input 2049 characters (beyond limit)
        String overInput = "b".repeat(2049);
        page.enterSearchText(overInput);
        String displayed = page.getSearchText();
        Assertions.assertTrue(displayed.length() <= 2048, "Textbox should truncate input to 2048 characters");
        // Depending on UI behaviour, the button may become disabled
        Assertions.assertFalse(page.isSearchButtonEnabled(), "Search button should be disabled when input exceeds max length");
    }
}
