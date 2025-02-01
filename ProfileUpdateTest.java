package iteration1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfileUpdateTest {

    WebDriver driver;
    @BeforeEach
    public void setUp() {
    	driver = new EdgeDriver();
        new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void testProfileUpdate() {
        // Navigate to the profile update page
        driver.get("http://localhost:3000/update-profile");

        // Find the input fields and submit button for updating the profile
        WebElement nameField = driver.findElement(By.xpath("//input[@placeholder='Enter your name']"));
        WebElement phoneField = driver.findElement(By.xpath("//input[@placeholder='Enter your phone number']"));
        WebElement addressField = driver.findElement(By.xpath("//input[@placeholder='Enter your address']"));
        WebElement updateButton = driver.findElement(By.xpath("//button[text()='Update Profile']"));

        // Fill in the form with test data
        nameField.clear();
        nameField.sendKeys("John Doe");

        phoneField.clear();
        phoneField.sendKeys("123-456-7890");

        addressField.clear();
        addressField.sendKeys("123 Test St, Test City, ON");

        // Submit the form
        updateButton.click();

        // Wait for the message to appear (add an explicit wait if necessary)
        try {
            Thread.sleep(2000); // This is just a simple wait, replace with WebDriverWait for production code.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Find the success or error message
        WebElement messageElement = driver.findElement(By.className("message"));

        // Assert that the profile update was successful
        String message = messageElement.getText();
        assertEquals("Profile updated successfully!", message);
    }

    @Test
    public void testPasswordChange() {
        // Navigate to the profile update page
        driver.get("http://localhost:3000/update-profile");

        // Find the password input fields and submit button for changing the password
        WebElement currentPasswordField = driver.findElement(By.xpath("//input[@placeholder='Enter current password']"));
        WebElement newPasswordField = driver.findElement(By.xpath("//input[@placeholder='Enter new password']"));
        WebElement confirmPasswordField = driver.findElement(By.xpath("//input[@placeholder='Confirm new password']"));
        WebElement changePasswordButton = driver.findElement(By.xpath("//button[text()='Change Password']"));

        // Fill in the password fields with test data
        currentPasswordField.clear();
        currentPasswordField.sendKeys("CurrentPassword123");

        newPasswordField.clear();
        newPasswordField.sendKeys("NewPassword123");

        confirmPasswordField.clear();
        confirmPasswordField.sendKeys("NewPassword123");

        // Submit the password change
        changePasswordButton.click();

        // Wait for the message to appear
        try {
            Thread.sleep(2000); // This is just a simple wait, replace with WebDriverWait for production code.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Find the success or error message
        WebElement messageElement = driver.findElement(By.className("message"));

        // Assert that the password change was successful
        String message = messageElement.getText();
        assertEquals("Password updated successfully!", message);
    }

    @AfterEach
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
