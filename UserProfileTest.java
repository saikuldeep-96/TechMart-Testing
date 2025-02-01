package iteration1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

class UserProfileTest {

    WebDriver driver;

    @BeforeEach
    void setUp() {
        // Initialize ChromeDriver
        driver = new ChromeDriver();
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
    void testChangePassword() {
        // Navigate to the profile update page
        driver.get("http://localhost:3000/update-profile");

        // Enter current password, new password, and confirm password
        WebElement currentPasswordInput = driver.findElement(By.xpath("//input[@placeholder='Enter current password']"));
        WebElement newPasswordInput = driver.findElement(By.xpath("//input[@placeholder='Enter new password']"));
        WebElement confirmPasswordInput = driver.findElement(By.xpath("//input[@placeholder='Confirm new password']"));
        WebElement changePasswordButton = driver.findElement(By.xpath("//button[contains(text(),'Change Password')]"));

        // Assuming the test user has the email 'user@example.com' and current password 'OldPassword123'
        String currentPassword = "OldPassword123";
        String newPassword = "NewPassword123";

        // Fill out the form
        currentPasswordInput.sendKeys(currentPassword);
        newPasswordInput.sendKeys(newPassword);
        confirmPasswordInput.sendKeys(newPassword);

        // Submit the form
        changePasswordButton.click();

        // Wait for the success or error message (assuming the message will show up on the page after clicking)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("message")));

        // Get the message text and validate it
        String messageText = messageElement.getText();
        assertEquals("Password updated successfully!", messageText, "Password update failed or message mismatch");

        // Alternatively, check for the success alert
        // Uncomment this block if there's an alert pop-up after successful password change:
        /*
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        assertEquals("Password updated successfully!", alertText);
        alert.accept();
        */
    }

    @Test
    void testChangePassword_withMismatch() {
        // Navigate to the profile update page
        driver.get("http://localhost:3000/update-profile");

        // Enter current password, new password, and confirm password
        WebElement currentPasswordInput = driver.findElement(By.xpath("//input[@placeholder='Enter current password']"));
        WebElement newPasswordInput = driver.findElement(By.xpath("//input[@placeholder='Enter new password']"));
        WebElement confirmPasswordInput = driver.findElement(By.xpath("//input[@placeholder='Confirm new password']"));
        WebElement changePasswordButton = driver.findElement(By.xpath("//button[contains(text(),'Change Password')]"));

        // Test case with mismatched passwords
        String currentPassword = "OldPassword123";
        String newPassword = "NewPassword123";
        String confirmPassword = "DifferentPassword123"; // Mismatch here

        // Fill out the form
        currentPasswordInput.sendKeys(currentPassword);
        newPasswordInput.sendKeys(newPassword);
        confirmPasswordInput.sendKeys(confirmPassword);

        // Submit the form
        changePasswordButton.click();

        // Wait for the error message
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("message")));

        // Get the error message and validate it
        String messageText = messageElement.getText();
        assertEquals("Passwords do not match.", messageText, "Error message mismatch");
    }

    @Test
    void testChangePassword_withEmptyFields() {
        // Navigate to the profile update page
        driver.get("http://localhost:3000/update-profile");

        // Leave fields empty and try to submit
        WebElement changePasswordButton = driver.findElement(By.xpath("//button[contains(text(),'Change Password')]"));
        changePasswordButton.click();

        // Wait for the error message
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("message")));

        // Get the error message and validate it
        String messageText = messageElement.getText();
        assertEquals("Please fill in all fields.", messageText, "Error message mismatch");
    }
}



