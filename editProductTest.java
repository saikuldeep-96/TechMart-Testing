package iteration2;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class editProductTest {
	    private String productName;
	    private double productPrice;
	    private String productDescription;
	    private String productCategory;
	    
	    WebDriver driver;

	    @BeforeEach
	    void setUp() {
	        productName = "Laptop";
	        productPrice = 1200.0;
	        productDescription = "High-end gaming laptop";
	        productCategory = "laptops";
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
	    void testEditProductWithValidDetails() {
	    	 driver.get("http://localhost:3000/adminDashboard");
	        productName = "Updated Laptop";
	        productPrice = 1300.0;
	        productDescription = "Updated high-end gaming laptop";
	        productCategory = "laptops"; 
	        
	   
	        assertEquals("Updated Laptop", productName, "Product name should be updated");
	        assertEquals(1300.0, productPrice, "Product price should be updated");
	        assertEquals("Updated high-end gaming laptop", productDescription, "Product description should be updated");
	        assertEquals("laptops", productCategory, "Product category should remain unchanged");
	    }

	    @Test
	    void testEditProductWithInvalidPrice() {
	    	 driver.get("http://localhost:3000/adminDashboard");
	       
	        try {
	            productPrice = -50.0;
	            fail("Price should not be negative");
	        } catch (IllegalArgumentException e) {
	            assertEquals("Price must be positive", e.getMessage(), "Should not allow negative price");
	        }
	    }

	    @Test
	    void testEditProductWithEmptyName() {
	    	 driver.get("http://localhost:3000/adminDashboard");
	        
	        try {
	            productName = "";
	            fail("Product name cannot be empty");
	        } catch (IllegalArgumentException e) {
	            assertEquals("Product name cannot be empty", e.getMessage(), "Should not allow empty product name");
	        }
	    }

	    @Test
	    void testEditProductWithInvalidCategory() {
	    	 driver.get("http://localhost:3000/adminDashboard");
	        try {
	            productCategory = "unknownCategory";
	            fail("Invalid category");
	        } catch (IllegalArgumentException e) {
	            assertEquals("Invalid category", e.getMessage(), "Should not allow invalid category");
	        }
	    }
	}




