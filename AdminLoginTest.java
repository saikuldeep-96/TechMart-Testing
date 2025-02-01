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

class AdminLoginTest {

    WebDriver driver;

    @BeforeEach
    void setUp() {
    	driver = new EdgeDriver();
        new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testAdminLogin() {
        driver.get("http://localhost:3000/login-signup");

    
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-email")));

      
        WebElement emailField = driver.findElement(By.id("login-email"));
        WebElement passwordField = driver.findElement(By.id("login-password"));
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='button']"));

        emailField.sendKeys("admin123@gmail.com");
        passwordField.sendKeys("admin123");

    
        loginButton.click();

       
        wait.until(ExpectedConditions.urlContains("admin-dashboard"));

       
        assertTrue(driver.getCurrentUrl().contains("admin-dashboard"), "Admin should be redirected to the dashboard.");

   
        WebElement dashboardHeader = driver.findElement(By.tagName("h1"));
        assertEquals("Admin Dashboard", dashboardHeader.getText(), "Admin should be on the dashboard page.");
    }
}
