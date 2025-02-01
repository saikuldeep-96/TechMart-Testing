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

class LoginTest {
	
	
	 WebDriver driver;

	    @BeforeEach
	    void setUp() {
	        // Initialize EdgeDriver
	        driver = new EdgeDriver();
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Implicit wait
	    }

	    @AfterEach
	    void tearDown() {
	        if (driver != null) {
	            driver.quit(); 
	        }
	    }

	    @Test
	    void userLoginTest() {
	        driver.get("http://localhost:3000/login-signup"); // Adjust the URL if needed

	        // Find and fill in the login fields
	        WebElement emailField = driver.findElement(By.id("login-email"));
	        emailField.sendKeys("sai1234@gmail.com");  // Replace with a test user email

	        WebElement passwordField = driver.findElement(By.id("login-password"));
	        passwordField.sendKeys("123456789"); // Replace with the correct test password

	        // Click login button
	        WebElement loginButton = driver.findElement(By.cssSelector(".submit-btn-new"));
	        loginButton.click();

	        // Wait for the page to load after login
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement products = wait.until(ExpectedConditions.elementToBeClickable(By.id("products")));

	     
	        assertTrue(products.isDisplayed(), "User dashboard should be displayed after login");
	    }

}
