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

class AddProductToCartTest {

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
    void testAddProductToCart() {
        // Navigate to the Products page (Assuming user is logged in)
        driver.get("http://localhost:3000/products");

        // Wait for the product cards to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("product-card")));

        // Get the first product card (Assuming the products are listed)
        WebElement firstProductCard = driver.findElement(By.xpath("//div[@class='product-card'][1]"));
        
        // Click the "Add to Cart" button on the first product
        WebElement addToCartButton = firstProductCard.findElement(By.xpath(".//button[contains(text(),'Add to Cart')]"));
        addToCartButton.click();

        // Wait for the cart icon to reflect the addition
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cart-icon")));

        // Check that the cart count has increased (Assuming there's a counter on the cart icon)
        WebElement cartIcon = driver.findElement(By.className("cart-icon"));
        WebElement cartItemCount = cartIcon.findElement(By.xpath(".//span[@class='item-count']"));  // Adjust according to the actual HTML
        String itemCount = cartItemCount.getText();

        // Assert that the cart item count is updated to 1 (assuming the cart is initially empty)
        assertEquals("1", itemCount, "The cart item count should be 1 after adding a product.");
    }
}



