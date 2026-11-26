package tests;

import io.qameta.allure.Feature;


import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.AllureJunit4;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.QE_TC_7E30C1650E4FPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * QE-TC:QE-TC-7E30C1650E4F:v1
 */
@Tag("smoke")
@DisplayName("Filter Inpatients by Status on Dashboard")
@Feature("Generated automation")
public class QE_TC_7E30C1650E4FTest {

    private WebDriver driver;
    private QE_TC_7E30C1650E4FPage page;

    @BeforeEach
    public void setUp() {
        // Initialize ChromeDriver (ensure chromedriver binary is on PATH or set via webdriver.chrome.driver)
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Read base URL from environment variable
        String baseUrl = System.getenv("QE_BASE_URL");
        if (baseUrl == null) {
            throw new IllegalStateException("Environment variable QE_BASE_URL is not set");
        }
        driver.get(baseUrl);

        // Optional login using credentials from environment (if the application requires it)
        String username = System.getenv("QE_CRED_USERNAME");
        String password = System.getenv("QE_CRED_PASSWORD");
        if (username != null && password != null) {
            // Implement login steps here or delegate to a LoginPage object.
            // For brevity, this is omitted.
        }

        page = new QE_TC_7E30C1650E4FPage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Verify that the status filter shows only Discharged inpatients")
    public void testFilterInpatientsByStatus() {
        Allure.step("Select 'Discharged' status from the filter dropdown", () -> {
            page.selectStatus("Discharged");
        });

        Allure.step("Validate that all displayed rows have status 'Discharged'", () -> {
            boolean allMatch = page.allRowsHaveStatus("Discharged");
            assertTrue(allMatch, "Not all rows are marked as Discharged");
        });
    }
}
