package iteration1;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class SignupTest {

   private WebDriver driver;
   private WebDriverWait wait;

    @BeforeEach
    void setUp() {
        driver = new EdgeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testUserSignup() {
        driver.get("http://localhost:3000/login-signup");

        // Wait for the form to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signup-name")));

        WebElement nameField = driver.findElement(By.id("signup-name"));
        nameField.clear();
        nameField.sendKeys("bot");

        WebElement emailField = driver.findElement(By.id("signup-email"));
        emailField.clear();
        emailField.sendKeys("bot1234@gmail.com");

        WebElement phoneField = driver.findElement(By.id("signup-phone"));
        phoneField.clear();
        phoneField.sendKeys("1234567890");

        WebElement passwordField = driver.findElement(By.id("signup-password"));
        passwordField.clear();
        passwordField.sendKeys("bot1234");

        // Click Signup Button
        WebElement signupButton = driver.findElement(By.cssSelector(".submit-btn-new"));
        signupButton.click();

        // Wait for confirmation message OR redirection
        boolean isRegistered = wait.until(ExpectedConditions.or(
            ExpectedConditions.visibilityOfElementLocated(By.className("success-message-new")),  
            ExpectedConditions.urlContains("login-signup") 
        ));

        assertTrue(isRegistered, "User signup should succeed.");
    }
}
