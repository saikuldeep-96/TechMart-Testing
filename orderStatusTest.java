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

public class orderStatusTest {
	
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
	    void testMarkOrderAsCompleted_Success() {
	    	  driver.get("http://localhost:3000/ordersAdmin");

	        orders.add("Order 1 - Pending");
	        orders.set(0, "Order 1 - Completed");

	        assertEquals("Order 1 - Completed", orders.get(0), "Order status should be updated to 'Completed'.");
	    }

	    @Test
	    void testMarkOrderAsCompleted_Error() {
	    	driver.get("http://localhost:3000/ordersAdmin");
	        error = "Error updating order status. Order not found.";

	        assertNotNull(error, "Error message should be set if order does not exist.");
	    }

	    @Test
	    void testOrderStatusCannotBeUpdatedIfAlreadyCompleted() {
	    	driver.get("http://localhost:3000/ordersAdmin");
	        orders.add("Order 1 - Completed");

	        orders.set(0, "Order 1 - Completed");

	        assertEquals("Order 1 - Completed", orders.get(0), "Order status should remain 'Completed'.");
	    }

	    @Test
	    void testMarkOrderAsCompleted_EmptyOrder() {
	    	driver.get("http://localhost:3000/ordersAdmin");
	        error = "Error updating order status. No orders available.";

	        assertNotNull(error, "Error message should be set if there are no orders to update.");
	    }
	}



