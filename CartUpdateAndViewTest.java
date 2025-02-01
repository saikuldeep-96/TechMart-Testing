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

class CartUpdateAndViewTest {

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
    void testUpdateCartAndViewTotal() {
      
        driver.get("http://localhost:3000/cart");

       
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cart-items")));

       
        WebElement firstCartItem = driver.findElement(By.xpath("//div[@class='cart-item'][1]"));
        WebElement quantityElement = firstCartItem.findElement(By.xpath(".//span"));
        WebElement priceElement = firstCartItem.findElement(By.xpath(".//p"));

      
        int initialQuantity = Integer.parseInt(quantityElement.getText());
        double initialPrice = Double.parseDouble(priceElement.getText().substring(1));

       
        WebElement increaseButton = firstCartItem.findElement(By.xpath(".//button[2]"));
        increaseButton.click();

       
        wait.until(ExpectedConditions.textToBePresentInElement(quantityElement, String.valueOf(initialQuantity + 1)));

       
        int updatedQuantity = Integer.parseInt(quantityElement.getText());
        double updatedPrice = Double.parseDouble(priceElement.getText().substring(1));

       
        assertEquals(initialQuantity + 1, updatedQuantity, "Quantity should increase by 1.");
        assertEquals(initialPrice * updatedQuantity, updatedPrice, "Price should update based on quantity.");

        
        WebElement totalPriceElement = driver.findElement(By.className("total-price"));
        double totalPrice = Double.parseDouble(totalPriceElement.getText().substring(8));  

        
        double expectedTotalPrice = initialPrice * updatedQuantity;
        assertEquals(expectedTotalPrice, totalPrice, "Total price should be updated based on cart item quantity.");

        
        WebElement removeButton = firstCartItem.findElement(By.className("remove-btn"));
        removeButton.click();

   
        wait.until(ExpectedConditions.stalenessOf(firstCartItem));

        
        WebElement updatedTotalPriceElement = driver.findElement(By.className("total-price"));
        double updatedTotalPrice = Double.parseDouble(updatedTotalPriceElement.getText().substring(8));  // Remove "$" from the string

        
        assertEquals(0, updatedTotalPrice, "Total price should be 0 after removing all items.");
    }
}
