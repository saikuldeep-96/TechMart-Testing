package iteration1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

class UserLogoutTest {

    WebDriver driver;

    @BeforeEach
    void setUp() {
    	driver = new EdgeDriver();
        new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() {
        // Quit the driver after test completion
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testLogout() {
        // Navigate to the Products page (Assuming user is logged in)
        driver.get("http://localhost:3000/products");

        // Find and click the "Logout" button
        WebElement logoutButton = driver.findElement(By.xpath("//button[contains(text(),'Logout')]"));
        logoutButton.click();

        // Wait for the page to load or redirect after logout
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/"));

        // Verify if the user is redirected to the Home page (or login page)
        String currentUrl = driver.getCurrentUrl();
        assertEquals("http://localhost:3000/", currentUrl, "User is not redirected to the homepage after logout.");

        // Alternatively, check for the presence of the login button
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));
        assertTrue(loginButton.isDisplayed(), "Login button is not displayed after logout.");
    }
}

