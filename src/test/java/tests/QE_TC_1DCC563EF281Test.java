package tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.AllureJunit4;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.QE_TC_1DCC563EF281Page;

import java.time.Duration;

@Tag("smoke")
@DisplayName("End-to-Edge Search Flow on Google Homepage")
public class QE_TC_1DCC563EF281Test {

    private WebDriver driver;
    private QE_TC_1DCC563EF281Page page;

    @BeforeEach
    public void setUp() {
        // Assuming ChromeDriver executable is on the system PATH or managed by WebDriverManager elsewhere.
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        page = new QE_TC_1DCC563EF281Page(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Description("Validate that a user can perform a complete search workflow on Google, including the \"I\'m Feeling Lucky\" feature and Gmail navigation link.")
    @Step("Execute Google search end‑to‑end scenario")
    public void testGoogleSearchEndToEnd() {
        // Source marker for traceability
        Allure.addAttachment("Source Marker", "QE-TC:QE-TC-1DCC563EF281:v1");

        String baseUrl = System.getenv("QE_BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "https://www.google.com"; // fallback default
        }
        page.openHomePage(baseUrl);

        // Step 1: Enter search text and submit
        page.enterSearchText("OpenAI ChatGPT");
        page.submitSearch();
        page.assertResultsContain("OpenAI ChatGPT");

        // Step 2: Return to home, clear, new search with Feeling Lucky
        page.goToHomePage();
        page.clearSearchBox();
        page.enterSearchText("OpenAI");
        page.clickFeelingLucky();
        // No direct assertion possible without a known URL; rely on navigation success.

        // Step 3: Click Gmail link
        page.clickGmailLink();
        // Verify Gmail page title contains "Gmail"
        page.assertPageTitleContains("Gmail");
    }

    // QE-TC:QE-TC-1DCC563EF281:v1
}
