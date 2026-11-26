package tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.QE_TC_72933B9D373FPage;

/**
 * Automated test for QE_TC_72933B9D373F.
 */
public class QE_TC_72933B9D373FTest {
    private WebDriver driver;
    private QE_TC_72933B9D373FPage page;

    @BeforeEach
    public void setUp() {
        // ChromeDriver binary location should be provided via system property or WebDriver manager.
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        String baseUrl = System.getenv("QE_BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            throw new IllegalStateException("Environment variable QE_BASE_URL is not set");
        }
        driver.get(baseUrl);
        page = new QE_TC_72933B9D373FPage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Tag("smoke")
    @DisplayName("Validate search suggestions visibility based on input")
    @Description("Validate that suggestions appear only after entering at least one character and disappear when cleared.")
    public void testSearchSuggestionsBehavior() {
        // Step 1: Click on the search textbox without typing any characters
        page.clickSearchBox();
        Assertions.assertTrue(page.getSuggestions().isEmpty(), "No suggestions should be displayed when textbox is empty.");

        // Step 2: Enter a single character 'a'
        page.typeIntoSearchBox("a");
        Assertions.assertFalse(page.getSuggestions().isEmpty(), "Suggestions should appear after entering a character.");

        // Step 3: Delete the character using backspace
        page.clearSearchBoxUsingBackspace();
        Assertions.assertTrue(page.getSuggestions().isEmpty(), "Suggestions should disappear when textbox is cleared.");

        // Step 4: Enter the string 'test'
        page.typeIntoSearchBox("test");
        Assertions.assertFalse(page.getSuggestions().isEmpty(), "Suggestions should appear for the string 'test'.");

        // Step 5: Click the clear (X) button
        page.clickClearButton();
        Assertions.assertTrue(page.getSearchBoxValue().isEmpty(), "Search box should be empty after clearing.");
        Assertions.assertTrue(page.getSuggestions().isEmpty(), "No suggestions should be visible after clearing.");
    }

    // QE-TC:QE-TC-72933B9D373F:v1
}
