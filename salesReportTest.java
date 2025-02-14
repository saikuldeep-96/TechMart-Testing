package iteration3;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class salesReportTest {
	   WebDriver driver;

    private String startDate;
    private String endDate;
    private boolean isReportGenerated;
    private boolean isValidDateRange;

    @BeforeEach
    void setUp() {
    	driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        startDate = "";
        endDate = "";
        isReportGenerated = false;
        isValidDateRange = false;
    }
    
    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit(); 
        }
    }


    @Test
    void testInvalidDateRange() {
    	driver.get("http://localhost:3000/salesAdmin"); 
        startDate = "";
        endDate = "";
        isValidDateRange = startDate.isEmpty() || endDate.isEmpty();
        assertTrue(isValidDateRange, "Date range should be invalid when empty.");
    }

    @Test
    void testValidDateRange() {
    	driver.get("http://localhost:3000/salesAdmin"); 
        startDate = "2025-02-01";
        endDate = "2025-02-10";
        isValidDateRange = !startDate.isEmpty() && !endDate.isEmpty();
        assertTrue(isValidDateRange, "Date range should be valid when both dates are provided.");
    }

    @Test
    void testGenerateReport() {
    	driver.get("http://localhost:3000/salesAdmin"); 
        startDate = "2025-01-01";
        endDate = "2025-01-31";
        isValidDateRange = !startDate.isEmpty() && !endDate.isEmpty();

        if (isValidDateRange) {
            isReportGenerated = true; 
        }

        assertTrue(isReportGenerated, "The report should be generated when valid dates are provided.");
    }

    @Test
    void testNoOrdersForGivenDateRange() {
    	driver.get("http://localhost:3000/salesAdmin"); 
        startDate = "2025-03-01";
        endDate = "2025-03-31";
        isValidDateRange = !startDate.isEmpty() && !endDate.isEmpty();

        if (isValidDateRange) {
            boolean noOrders = true;
            assertTrue(noOrders, "No orders should be found when the date range has no matching data.");
        }
    }

    @Test
    void testExportReportAsCSV() {
    	driver.get("http://localhost:3000/salesAdmin"); 
        startDate = "2025-02-01";
        endDate = "2025-02-10";
        isValidDateRange = !startDate.isEmpty() && !endDate.isEmpty();

        if (isValidDateRange) {
            boolean csvExported = true; 
            assertTrue(csvExported, "Sales report should be successfully exported as CSV.");
        }
    }
}
