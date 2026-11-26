package tests;

import io.qameta.allure.Feature;


import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.QE_TC_FD2722464282Page;
import static org.junit.jupiter.api.Assertions.*;

/**
 * QE-TC:QE-TC-FD2722464282:v1
 */
@Epic("Search Functionality")
@Feature("Google Search")
@Story("Negative test: Search with excessively long query string")
@Tag("smoke")
@DisplayName("Negative test: Search with excessively long query string")
public class QE_TC_FD2722464282Test {

    private WebDriver driver;
    private QE_TC_FD2722464282Page searchPage;

    @BeforeEach
    public void setUp() {
        // Assuming chromedriver binary is available via WebDriverManager or system property
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        String baseUrl = System.getenv("QE_BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            throw new IllegalStateException("Environment variable QE_BASE_URL is not set");
        }
        driver.get(baseUrl);
        searchPage = new QE_TC_FD2722464282Page(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that entering an excessively long query string triggers validation and prevents navigation.")
    public void testExcessivelyLongQuery() {
        // Generate a 5000‑character string
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5000; i++) {
            sb.append('a');
        }
        String longQuery = sb.toString();

        // Interact with the page
        searchPage.enterSearchQuery(longQuery);
        // Verify that the textbox contains the expected truncated value (if any)
        assertTrue(searchPage.isSearchBoxValuePresent(), "Search box should contain the entered text");

        // Click the search button and verify validation
        searchPage.clickSearch();
        assertTrue(searchPage.isValidationMessageDisplayed(), "Validation message should be displayed after clicking Search");
        assertEquals("Your query is too long. Please shorten it.", searchPage.getValidationMessage(), "Validation message text should match expected");

        // Press ENTER and verify the same validation behavior
        searchPage.enterSearchQuery(longQuery); // re‑enter because previous attempt may have cleared it
        searchPage.pressEnter();
        assertTrue(searchPage.isValidationMessageDisplayed(), "Validation message should be displayed after pressing ENTER");
    }
}
