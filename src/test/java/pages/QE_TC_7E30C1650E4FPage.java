package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class QE_TC_7E30C1650E4FPage {

    private final WebDriver driver;

    // Locators (replace with actual identifiers from the application under test)
    private final By statusDropdown = By.id("statusDropdown");
    private final By inpatientRows = By.cssSelector("table#inpatientTable tbody tr");
    private final By statusCellInRow = By.cssSelector("td.status");

    public QE_TC_7E30C1650E4FPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Select status '{status}' from the dropdown")
    public void selectStatus(String status) {
        WebElement dropdownElement = driver.findElement(statusDropdown);
        Select select = new Select(dropdownElement);
        select.selectByVisibleText(status);
        // Wait for the table to refresh if needed – this can be replaced with explicit waits.
        try {
            Thread.sleep(2000); // Simple static wait for demo purposes only.
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Step("Verify that every row in the inpatient list has status '{expectedStatus}'")
    public boolean allRowsHaveStatus(String expectedStatus) {
        List<WebElement> rows = driver.findElements(inpatientRows);
        for (WebElement row : rows) {
            WebElement statusCell = row.findElement(statusCellInRow);
            String actualStatus = statusCell.getText().trim();
            if (!expectedStatus.equalsIgnoreCase(actualStatus)) {
                return false;
            }
        }
        return true;
    }
}
