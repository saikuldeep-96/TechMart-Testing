package iteration2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.List;
import java.time.Duration;
import java.util.ArrayList;
import java.util.stream.Collectors;


public class inventorySearch {
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
	    void testSearchProductByName() {
	    	 driver.get("http://localhost:3000/inventory"); 
	        List<Product> products = getSampleProducts();
	        String searchQuery = "iPhone";
	        
	        List<Product> filteredProducts = searchProducts(products, searchQuery);
	        
	        assertEquals(1, filteredProducts.size(), "Should find 1 product with the name 'iPhone'.");
	        assertTrue(filteredProducts.get(0).getName().contains(searchQuery), "Filtered product name should contain the search query.");
	    }

	    @Test
	    void testSearchProductByCategory() {
	    	driver.get("http://localhost:3000/inventory"); 
	        List<Product> products = getSampleProducts();
	        String searchQuery = "laptops";
	        
	        List<Product> filteredProducts = searchProducts(products, searchQuery);

	        assertEquals(2, filteredProducts.size(), "Should find 2 products under the 'laptops' category.");
	        assertTrue(filteredProducts.stream().allMatch(p -> p.getCategory().equalsIgnoreCase(searchQuery)),
	                "All filtered products should belong to the 'laptops' category.");
	    }

	    @Test
	    void testSearchNoResults() {
	    	driver.get("http://localhost:3000/inventory"); 
	        List<Product> products = getSampleProducts();
	        String searchQuery = "iPad";
	        
	        List<Product> filteredProducts = searchProducts(products, searchQuery);

	        assertEquals(0, filteredProducts.size(), "Should find no products for the search query 'iPad'.");
	    }

	    @Test
	    void testSearchEmptyQuery() {
	    	driver.get("http://localhost:3000/inventory"); 
	        List<Product> products = getSampleProducts();
	        String searchQuery = "";
	        
	        List<Product> filteredProducts = searchProducts(products, searchQuery);

	        assertEquals(4, filteredProducts.size(), "Should return all products when the search query is empty.");
	    }

	    
	    private List<Product> searchProducts(List<Product> products, String searchQuery) {
	        return products.stream()
	                .filter(product -> product.getName().toLowerCase().contains(searchQuery.toLowerCase()) ||
	                        product.getCategory().toLowerCase().contains(searchQuery.toLowerCase()))
	                .collect(Collectors.toList());
	    }

	   
	    private List<Product> getSampleProducts() {
	        List<Product> products = new ArrayList<>();
	        products.add(new Product("iPhone 12", "mobile", 999, 10));
	        products.add(new Product("Samsung Galaxy", "mobile", 899, 15));
	        products.add(new Product("MacBook Pro", "laptops", 2499, 5));
	        products.add(new Product("Dell XPS", "laptops", 1899, 8));
	        return products;
	    }
	    
	   
	    static class Product {
	        private String name;
	        private String category;
	        public Product(String name, String category, int price, int stock) {
	            this.name = name;
	            this.category = category;
	        }

	        public String getName() {
	            return name;
	        }

	        public String getCategory() {
	            return category;
	        }
	    }
	}


