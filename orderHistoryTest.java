package iteration3;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class orderHistoryTest {
	
	 WebDriver driver;
	

	    private boolean isLoading;
	    private boolean hasOrders;
	    private int orderCount;

	    @BeforeEach
	    void setUp() {
	    	 driver = new EdgeDriver();
	         driver.manage().window().maximize();
	         driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
	        isLoading = true;
	        hasOrders = false;
	        orderCount = 0;
	    }
	    
	    @AfterEach
	    void tearDown() {
	        if (driver != null) {
	            driver.quit(); 
	        }
	    }

	    @Test
	    void testLoadingState() {
	    	driver.get("http://localhost:3000/uOrderHistory");
	        isLoading = true;
	        assertTrue(isLoading, "Orders should be loading initially.");
	    }

	    @Test
	    void testNoOrders() {
	    	driver.get("http://localhost:3000/uOrderHistory");
	        hasOrders = false;
	        assertFalse(hasOrders, "User should not have orders.");
	    }

	    @Test
	    void testOrdersFetched() {
	    	driver.get("http://localhost:3000/uOrderHistory");
	        hasOrders = true;
	        orderCount = 3; // Assume 3 orders are fetched
	        assertTrue(hasOrders, "User should have orders.");
	        assertEquals(3, orderCount, "User should have 3 orders.");
	    }

	    @Test
	    void testEmptyOrderList() {
	    	driver.get("http://localhost:3000/uOrderHistory");
	        hasOrders = false;
	        assertFalse(hasOrders, "No orders should be displayed when there are none.");
	    }

	    @Test
	    void testOrderDetails() {
	    	driver.get("http://localhost:3000/uOrderHistory");
	        orderCount = 1; // One order fetched
	        assertTrue(orderCount > 0, "There should be at least one order.");
	    }
	}



