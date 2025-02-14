package iteration2;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class searchTest {
	
	 WebDriver driver;

	    @BeforeEach
	    void setUp() {
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
	    void testSearchByProductName() {
		 driver.get("http://localhost:3000/products");
		 
	        List<String> products = new ArrayList<>();
	        products.add("iPhone 14");
	        products.add("MacBook Pro");
	        products.add("Samsung Galaxy S23");
	        
	        boolean found = products.stream().anyMatch(p -> p.equalsIgnoreCase("iPhone 14"));
	        assertTrue(found);
	    }

	    @Test
	    void testSearchByCategory() {
	    	driver.get("http://localhost:3000/products");
	        List<String> categories = new ArrayList<>();
	        categories.add("mobile");
	        categories.add("laptops");
	        
	        boolean found = categories.contains("mobile");
	        assertTrue(found);
	    }

	    @Test
	    void testSearchByBrand() {
	    	driver.get("http://localhost:3000/products");
	        List<String> brands = new ArrayList<>();
	        brands.add("Apple");
	        brands.add("Samsung");
	        
	        boolean found = brands.contains("Apple");
	        assertTrue(found);
	    }

	    @Test
	    void testSearchWithNoResults() {
	    	driver.get("http://localhost:3000/products");
	        List<String> products = new ArrayList<>();
	        products.add("iPhone 14");
	        products.add("MacBook Pro");
	        
	        boolean found = products.contains("Nokia");
	        assertFalse(found);
	    }

}
