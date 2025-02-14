package iteration3;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class paymentTest {
    WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testSuccessfulPayment() {
        driver.get("http://localhost:3000/payment");
        double amount = 100.0;
        String cardNumber = "4242424242424242";
        String expiryDate = "12/25";
        String cvv = "123";
        String zipCode = "12345"; 

        boolean result = processPayment(amount, cardNumber, expiryDate, cvv, zipCode);
        assertTrue(result, "Valid payment should be successful.");
    }

    @Test
    void testPaymentFailsDueToInvalidCardNumber() {
        driver.get("http://localhost:3000/payment");
        double amount = 50.0;
        String cardNumber = "1234567890123456"; 
        String expiryDate = "12/25";
        String cvv = "123";
        String zipCode = "12345";

        boolean result = processPayment(amount, cardNumber, expiryDate, cvv, zipCode);
        assertFalse(result, "Payment should fail due to an invalid card number.");
    }

    @Test
    void testPaymentFailsDueToExpiredCard() {
        driver.get("http://localhost:3000/payment");
        double amount = 50.0;
        String cardNumber = "4242424242424242";
        String expiryDate = "01/20"; 
        String cvv = "123";
        String zipCode = "12345";

        boolean result = processPayment(amount, cardNumber, expiryDate, cvv, zipCode);
        assertFalse(result, "Payment should fail due to an expired card.");
    }

    @Test
    void testPaymentFailsDueToIncorrectCVV() {
        driver.get("http://localhost:3000/payment");
        double amount = 50.0;
        String cardNumber = "4242424242424242";
        String expiryDate = "12/25";
        String cvv = "12"; 
        String zipCode = "12345";

        boolean result = processPayment(amount, cardNumber, expiryDate, cvv, zipCode);
        assertFalse(result, "Payment should fail due to an incorrect CVV.");
    }

    @Test
    void testPaymentFailsDueToInvalidZipCode() {
        driver.get("http://localhost:3000/payment");
        double amount = 50.0;
        String cardNumber = "4242424242424242";
        String expiryDate = "12/25";
        String cvv = "123";
        String zipCode = "1234"; 

        boolean result = processPayment(amount, cardNumber, expiryDate, cvv, zipCode);
        assertFalse(result, "Payment should fail due to an invalid ZIP code.");
    }

    private boolean processPayment(double amount, String cardNumber, String expiryDate, String cvv, String zipCode) {
        if (cardNumber.length() != 16 || !cardNumber.matches("\\d+")) return false;
        if (cvv.length() != 3 || !cvv.matches("\\d+")) return false;
        if (expiryDate.compareTo("12/24") < 0) return false;
        if (zipCode.length() != 5 || !zipCode.matches("\\d+")) return false; 

        return true;
    }
}
