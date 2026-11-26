package tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.QE_TC_C019D762263BPage;

/**
 * QE-TC:QE-TC-C019D762263B:v1
 */
@Tag("smoke")
@DisplayName("Google Search returns results for a valid query")
public class QE_TC_C019D762263BTest {

    private WebDriver driver;
    private QE_TC_C019D762263BPage page;

    @BeforeEach
    public void setUp() {
        // Assuming chromedriver is available in system PATH or configured via webdriver.chrome.driver env variable
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        page = new QE_TC_C019D762263BPage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Description("Verify that entering a valid search term and initiating search displays the results page with relevant results.")
    public void testGoogleSearchReturnsResults() {
        String baseUrl = System.getenv("QE_BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "https://www.google.com"; // fallback default
        }
        String query = System.getenv("QE_CRED_SEARCH_QUERY");
        if (query == null || query.isEmpty()) {
            query = "OpenAI ChatGPT"; // fallback test data from approved test case
        }

        stepOpenHomePage(baseUrl);
        stepEnterSearchQuery(query);
        stepClickSearchButton();
        stepVerifyResultsPage(query);
    }

    @Step("Open Google home page: {0}")
    private void stepOpenHomePage(String url) {
        page.open(url);
    }

    @Step("Enter search query: {0}")
    private void stepEnterSearchQuery(String query) {
        page.enterSearchQuery(query);
    }

    @Step("Click the Google Search button")
    private void stepClickSearchButton() {
        page.clickSearch();
    }

    @Step("Verify that the results page contains the query: {0}")
    private void stepVerifyResultsPage(String query) {
        boolean displayed = page.isResultsPageDisplayed(query);
        Allure.step("Results page displayed: " + displayed);
        if (!displayed) {
            throw new AssertionError("Search results page does not contain expected query: " + query);
        }
    }
}
