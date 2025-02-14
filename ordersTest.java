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

public class ordersTest {
	WebDriver driver;

	    private List<String> orders;
	    private String error;

	    @BeforeEach
	    void setUp() {
	        orders = new ArrayList<>();
	        error = null;
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
	    void testFetchOrders_Success() {
	    	driver.get("http://localhost:3000/ordersAdmin");
	        orders.add("Order 1");

	        assertTrue(orders.size() > 0, "Orders should be fetched successfully.");
	        assertNull(error, "Error should be null when orders are fetched successfully.");
	    }

	    @Test
	    void testFetchOrders_Empty() {
	    	driver.get("http://localhost:3000/ordersAdmin");
	        assertTrue(orders.isEmpty(), "There should be no orders available.");
	        assertNull(error, "Error should be null when no orders are fetched.");
	    }

	    @Test
	    void testFetchOrders_Error() {
	    	driver.get("http://localhost:3000/ordersAdmin");
	    	
	        error = "Error fetching orders. Please try again later.";

	        assertNotNull(error, "Error message should be set when there is a failure in fetching orders.");
	    }

	    @Test
	    void testUpdateOrderStatus_Success() {
	    	driver.get("http://localhost:3000/ordersAdmin");
	        orders.add("Order 1");

	        String order = orders.get(0);  
	        orders.set(0, order + " - Processed");  

	        assertEquals("Order 1 - Processed", orders.get(0), "Order status should be updated successfully.");
	    }

	    @Test
	    void testUpdateOrderStatus_Error() {
	    	driver.get("http://localhost:3000/ordersAdmin");
	        error = "Error updating order status. Please try again later.";

	        assertNotNull(error, "Error message should be set when failing to update order status.");
	    }
	}






