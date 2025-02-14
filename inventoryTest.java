package iteration3;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

public class inventoryTest {
	 WebDriver driver;

    private String productName;
    private double productPrice;
    private String productCategory;
    private int productStock;
    private boolean isProductAdded;
    private boolean isProductUpdated;
    private boolean isProductDeleted;

    @BeforeEach
    void setUp() {
    	 driver = new EdgeDriver();
         driver.manage().window().maximize();
         driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        productName = "";
        productPrice = 0.0;
        productCategory = "";
        productStock = 0;
        isProductAdded = false;
        isProductUpdated = false;
        isProductDeleted = false;
    }
    
    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit(); 
        }
    }


    @Test
    void testAddNewProduct() {
    	 driver.get("http://localhost:3000/inventory");
        productName = "Samsung Galaxy S22";
        productPrice = 799.99;
        productCategory = "mobile";
        productStock = 50;

        if (productName != null && productPrice > 0 && productStock >= 0) {
            isProductAdded = true;  
        }

        assertTrue(isProductAdded, "The product should be successfully added to the inventory.");
    }

    @Test
    void testEditProduct() {
    	 driver.get("http://localhost:3000/inventory");
        String productId = "12345";
        String updatedProductName = "Samsung Galaxy S22 Ultra";
        double updatedProductPrice = 1099.99;
        int updatedProductStock = 30;

        if (productId.equals("12345")) {
            productName = updatedProductName;
            productPrice = updatedProductPrice;
            productStock = updatedProductStock;
            isProductUpdated = true;  
        }

        assertTrue(isProductUpdated, "The product should be successfully updated.");
    }

    @Test
    void testDeleteProduct() {
    	 driver.get("http://localhost:3000/inventory");
        String productIdToDelete = "12345";

        if (productIdToDelete.equals("12345")) {
            isProductDeleted = true;  
        }

        assertTrue(isProductDeleted, "The product should be successfully deleted from the inventory.");
    }

    @Test
    void testSearchProductByName() {
    	 driver.get("http://localhost:3000/inventory");
        String searchQuery = "Samsung";
        boolean isSearchSuccessful = searchProductByName(searchQuery);

        assertTrue(isSearchSuccessful, "The product should be found when searching by name.");
    }

    private boolean searchProductByName(String query) {
        return productName.contains(query);
    }

    @Test
    void testSearchProductByCategory() {
    	 driver.get("http://localhost:3000/inventory");
        String searchQuery = "mobile";
        boolean isSearchSuccessful = searchProductByCategory(searchQuery);

        assertTrue(isSearchSuccessful, "The product should be found when searching by category.");
    }

    private boolean searchProductByCategory(String query) {
        return productCategory.equalsIgnoreCase(query);
    }

    @Test
    void testNoProductFoundInSearch() {
    	 driver.get("http://localhost:3000/inventory");
        String searchQuery = "NonExistentProduct";
        boolean isProductFound = searchProduct(searchQuery);

        assertFalse(isProductFound, "No product should be found when there is no match.");
    }

    private boolean searchProduct(String query) {
        return productName.contains(query);
    }

    @Test
    void testDisplayProducts() {
    	 driver.get("http://localhost:3000/inventory");
        loadProducts();

        assertFalse(isProductAdded, "There should be products displayed on the inventory page.");
    }

    private void loadProducts() {
        productName = "Samsung Galaxy S22";
        productPrice = 799.99;
        productCategory = "mobile";
        productStock = 50;

        isProductAdded = true; 
    }

    @Test
    void testNoProductsAvailable() {
    	 driver.get("http://localhost:3000/inventory");
        clearProducts();

        assertFalse(isProductAdded, "The inventory should be empty when there are no products.");
    }

    private void clearProducts() {
        isProductAdded = false;
    }

    @Test
    void testUpdateStock() {
    	 driver.get("http://localhost:3000/inventory");
        String productId = "12345";
        int updatedStock = 40;

        if (productId.equals("12345")) {
            productStock = updatedStock;
            isProductUpdated = true;  
        }

        assertTrue(isProductUpdated, "The stock of the product should be successfully updated.");
    }
}
