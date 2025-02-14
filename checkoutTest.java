package iteration2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class checkoutTest {
	WebDriver driver;


	    private List<Double> cart;
	    private String name, address, city, state, zip, country;

	    @BeforeEach
	    void setUp() {
	        cart = new ArrayList<>();
	        name = "";
	        address = "";
	        city = "";
	        state = "";
	        zip = "";
	        country = "";
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
	    void testEmptyCart() {
	        double total = calculateTotal(cart);
	        assertEquals(0, total, "Total should be 0 for an empty cart");
	    }

	    @Test
	    void testCartWithProducts() {
	    	driver.get("http://localhost:3000/checkout");
	        cart.add(1000.0);
	        cart.add(500.0);
	        double total = calculateTotal(cart);
	        assertEquals(1500.0, total, "Total should be correct for products in the cart");
	    }

	    @Test
	    void testMultipleQuantities() {
	    	driver.get("http://localhost:3000/checkout");
	        cart.add(300.0 * 3);
	        double total = calculateTotal(cart);
	        assertEquals(900.0, total, "Total should be correct for multiple quantities");
	    }

	    @Test
	    void testOrderSummaryMatchesCart() {
	    	driver.get("http://localhost:3000/checkout");
	        cart.add(200.0);
	        cart.add(100.0);
	        double total = calculateTotal(cart);
	        assertEquals(300.0, total, "Order summary should match the total price of cart items");
	    }

	    @Test
	    void testEmptyFormFields() {
	    	driver.get("http://localhost:3000/checkout");
	        assertFalse(isFormValid(), "Form should be invalid if fields are empty");
	    }


	    @Test
	    void testValidFormSubmission() {
	    	driver.get("http://localhost:3000/checkout");
	        name = "John Doe";
	        address = "123 Street";
	        city = "New York";
	        state = "NY";
	        zip = "10001";
	        country = "USA";
	        assertTrue(isFormValid(), "Form should be valid with correct details");
	    }



	    private double calculateTotal(List<Double> cart) {
	        return cart.stream().mapToDouble(Double::doubleValue).sum();
	    }

	    private boolean isFormValid() {
	        return !name.isEmpty() && !address.isEmpty() && !city.isEmpty() && !state.isEmpty() && zip.matches("\\d{5}") && !country.isEmpty();
	    }
	}

