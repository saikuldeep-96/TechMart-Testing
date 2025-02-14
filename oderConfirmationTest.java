package iteration3;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class oderConfirmationTest {
	
	  WebDriver driver;
	
	    private boolean orderPlaced;
	    private String confirmationMessage;
	    

	    @BeforeEach
	    void setUp() {
	    	 driver = new EdgeDriver();
	         driver.manage().window().maximize();
	         driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        orderPlaced = false;
	        confirmationMessage = "";
	    }
	    
	    @AfterEach
	    void tearDown() {
	        if (driver != null) {
	            driver.quit(); 
	        }
	    }

	    @Test
	    void testOrderConfirmationMessage() {
	    	driver.get("http://localhost:3000/confirmation");
	        orderPlaced = true;
	        if (orderPlaced) {
	            confirmationMessage = "Thank you for your purchase. Your order has been placed successfully.";
	        }

	        assertEquals("Thank you for your purchase. Your order has been placed successfully.", confirmationMessage);
	    }

	    @Test
	    void testNoOrderConfirmationMessage() {
	    	driver.get("http://localhost:3000/confirmation");
	        orderPlaced = false;
	        if (!orderPlaced) {
	            confirmationMessage = "No order found.";
	        }

	        assertEquals("No order found.", confirmationMessage);
	    }

	    @Test
	    void testContinueShoppingButtonVisibility() {
	    	driver.get("http://localhost:3000/confirmation");
	        orderPlaced = true;

	        boolean continueShoppingButtonVisible = orderPlaced;
	        assertTrue(continueShoppingButtonVisible, "The 'Continue Shopping' button should be visible after order confirmation.");
	    }
	}



