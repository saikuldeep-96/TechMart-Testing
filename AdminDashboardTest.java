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

class AdminDashboardTest {

    WebDriver driver;

    @BeforeEach
    void setUp() {
    	driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testViewProducts() {
    	driver.get("http://localhost:3000/admin-dashboard");
 
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement productTable = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product-list table")));

        // Verify that the product table is displayed
        assertTrue(productTable.isDisplayed(), "Product table should be displayed.");
    }

    @Test
    void testAddProduct() {
       
        driver.get("http://localhost:3000/admin-dashboard");

        // Wait for the product list to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product-list table")));

        // Fill in the new product form
        WebElement productNameInput = driver.findElement(By.cssSelector(".add-product input[type='text']"));
        WebElement productPriceInput = driver.findElement(By.cssSelector(".add-product input[type='number']"));
        WebElement productDescriptionInput = driver.findElement(By.cssSelector(".add-product textarea"));

        productNameInput.sendKeys("Test Product");
        productPriceInput.sendKeys("99.99");
        productDescriptionInput.sendKeys("This is a test product.");

        // Click the "Add Product" button
        WebElement addProductButton = driver.findElement(By.cssSelector(".add-product button"));
        addProductButton.click();

        // Wait for the product to be added and the table to refresh
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table//td[text()='Test Product']")));

        // Verify that the new product is added to the table
        WebElement addedProduct = driver.findElement(By.xpath("//table//td[text()='Test Product']"));
        assertNotNull(addedProduct, "New product should be visible in the product list.");
    }

    @Test
    void testDeleteProduct() {
        
        driver.get("http://localhost:3000/admin-dashboard");

        // Wait for the product list to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product-list table")));

        // Find the delete button for the first product (Assuming product is already there)
        WebElement deleteButton = driver.findElement(By.xpath("//table//tr[1]//button[text()='Delete']"));
        
        // Click the delete button
        deleteButton.click();

        // Wait for the product to be deleted (check if the product table is empty or the first row is removed)
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//table//td[text()='Test Product']")));

        // Verify that the product was deleted (by checking if it's no longer in the table)
        WebElement deletedProduct = driver.findElement(By.xpath("//table//td[text()='Test Product']"));
        assertNull(deletedProduct, "Product should no longer exist after deletion.");
    }

    @Test
    void testAdminLogout() {
       
        driver.get("http://localhost:3000/admin-dashboard");

        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product-list table")));

        // Click the logout button
        WebElement logoutButton = driver.findElement(By.cssSelector(".logout-btn"));
        logoutButton.click();

      
        wait.until(ExpectedConditions.urlContains("http://localhost:3000/"));

       
        assertTrue(driver.getCurrentUrl().contains("http://localhost:3000/"), "User should be redirected to the homepage after logout.");
    }
}

