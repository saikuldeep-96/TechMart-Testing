package iteration3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class billingAddressTest {
    WebDriver driver;
	
private Map<String, String> customerDetails;
    
    @BeforeEach
    void setUp() {
    	
    	  driver = new EdgeDriver();
          driver.manage().window().maximize();
          driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
          
        customerDetails = new HashMap<>();
        customerDetails.put("name", "");
        customerDetails.put("address", "");
        customerDetails.put("city", "");
        customerDetails.put("state", "");
        customerDetails.put("zip", "");
        customerDetails.put("country", "");
    }

    @AfterEach
    void tearDown() {
    	 if (driver != null) {
             driver.quit(); 
         }
        customerDetails.clear();
    }

    @Test
    void testValidBillingAddress() {
    	driver.get("http://localhost:3000/checkout");
        customerDetails.put("name", "John Doe");
        customerDetails.put("address", "123 Main Street");
        customerDetails.put("city", "New York");
        customerDetails.put("state", "NY");
        customerDetails.put("zip", "10001");
        customerDetails.put("country", "USA");

        assertTrue(isValidAddress(customerDetails), "Valid billing address should be accepted.");
    }

    @Test
    void testMissingNameField() {
    	driver.get("http://localhost:3000/checkout");
        customerDetails.put("address", "123 Main Street");
        customerDetails.put("city", "New York");
        customerDetails.put("state", "NY");
        customerDetails.put("zip", "10001");
        customerDetails.put("country", "USA");

        assertFalse(isValidAddress(customerDetails), "Address should be invalid if name is missing.");
    }

    @Test
    void testMissingAddressField() {
    	driver.get("http://localhost:3000/checkout");
        customerDetails.put("name", "John Doe");
        customerDetails.put("city", "New York");
        customerDetails.put("state", "NY");
        customerDetails.put("zip", "10001");
        customerDetails.put("country", "USA");

        assertFalse(isValidAddress(customerDetails), "Address should be invalid if address field is missing.");
    }

    @Test
    void testMissingCityField() {
    	driver.get("http://localhost:3000/checkout");
        customerDetails.put("name", "John Doe");
        customerDetails.put("address", "123 Main Street");
        customerDetails.put("state", "NY");
        customerDetails.put("zip", "10001");
        customerDetails.put("country", "USA");

        assertFalse(isValidAddress(customerDetails), "Address should be invalid if city is missing.");
    }

    @Test
    void testMissingStateField() {
    	driver.get("http://localhost:3000/checkout");
        customerDetails.put("name", "John Doe");
        customerDetails.put("address", "123 Main Street");
        customerDetails.put("city", "New York");
        customerDetails.put("zip", "10001");
        customerDetails.put("country", "USA");

        assertFalse(isValidAddress(customerDetails), "Address should be invalid if state is missing.");
    }

    @Test
    void testMissingZipCodeField() {
    	driver.get("http://localhost:3000/checkout");
        customerDetails.put("name", "John Doe");
        customerDetails.put("address", "123 Main Street");
        customerDetails.put("city", "New York");
        customerDetails.put("state", "NY");
        customerDetails.put("country", "USA");

        assertFalse(isValidAddress(customerDetails), "Address should be invalid if zip code is missing.");
    }

    @Test
    void testMissingCountryField() {
    	driver.get("http://localhost:3000/checkout");
        customerDetails.put("name", "John Doe");
        customerDetails.put("address", "123 Main Street");
        customerDetails.put("city", "New York");
        customerDetails.put("state", "NY");
        customerDetails.put("zip", "10001");

        assertFalse(isValidAddress(customerDetails), "Address should be invalid if country is missing.");
    }

    @Test
    void testInvalidZipCodeFormat() {
    	driver.get("http://localhost:3000/checkout");
        customerDetails.put("name", "John Doe");
        customerDetails.put("address", "123 Main Street");
        customerDetails.put("city", "New York");
        customerDetails.put("state", "NY");
        customerDetails.put("zip", "abcde"); 
        customerDetails.put("country", "USA");

        assertFalse(isValidAddress(customerDetails), "Address should be invalid if zip code is not numeric.");
    }

    @Test
    void testValidZipCodeFormat() {
    	driver.get("http://localhost:3000/checkout");
        customerDetails.put("name", "John Doe");
        customerDetails.put("address", "123 Main Street");
        customerDetails.put("city", "New York");
        customerDetails.put("state", "NY");
        customerDetails.put("zip", "10001");
        customerDetails.put("country", "USA");

        assertTrue(isValidAddress(customerDetails), "Valid address with correct zip code format should be accepted.");
    }

    @Test
    void testAllFieldsEmpty() {
    	driver.get("http://localhost:3000/checkout");
        assertFalse(isValidAddress(customerDetails), "Address should be invalid if all fields are empty.");
    }

    private boolean isValidAddress(Map<String, String> customer) {
        for (String value : customer.values()) {
            if (value == null || value.trim().isEmpty()) {
                return false;
            }
        }
        String zip = customer.get("zip");
        return zip.matches("\\d+");
    }
}

