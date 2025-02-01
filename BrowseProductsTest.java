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
import java.util.List;

class BrowseProductsTest {

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
    void testBrowseProductsByCategory() {
        // Navigate to the Products page (Assuming user is logged in)
        driver.get("http://localhost:3000/products");

      
        WebElement categoryButton = driver.findElement(By.xpath("//button[contains(text(),'Categories')]"));
        categoryButton.click();

       
        WebElement mobilesCategory = driver.findElement(By.xpath("//button[contains(text(),'Mobiles')]"));
        mobilesCategory.click();

        // Wait for the products to load (Assuming products will load dynamically)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("product-card")));

      
        List<WebElement> productCards = driver.findElements(By.className("product-card"));
        boolean allProductsAreMobiles = productCards.stream()
                .allMatch(card -> card.findElement(By.xpath(".//p[contains(text(),'mobiles')]")) != null);

        assertTrue(allProductsAreMobiles, "Not all displayed products are from the 'Mobiles' category.");

        
        categoryButton.click();
        WebElement laptopsCategory = driver.findElement(By.xpath("//button[contains(text(),'Laptops')]"));
        laptopsCategory.click();

        // Wait for the products to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("product-card")));

        
        productCards = driver.findElements(By.className("product-card"));
        boolean allProductsAreLaptops = productCards.stream()
                .allMatch(card -> card.findElement(By.xpath(".//p[contains(text(),'laptops')]")) != null);

        assertTrue(allProductsAreLaptops, "Not all displayed products are from the 'Laptops' category.");
    }
}



