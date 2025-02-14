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

public class categoryTest {
	 WebDriver driver;
	
	    private List<String> categories;

	    @BeforeEach
	    void setUp() {
	    	driver = new EdgeDriver();
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        categories = new ArrayList<>();
	    }
	    
	    @AfterEach
	    void tearDown() {
	        if (driver != null) {
	            driver.quit(); 
	        }
	    }    

	    @Test
	    void testAddCategory() {
	    	driver.get("http://localhost:3000/categoryAdmin");
	        String categoryName = "tablets";

	        categories.add(categoryName);
	        assertTrue(categories.contains(categoryName), "Category should be added to the list");
	    }

	    @Test
	    void testEditCategory() {
	    	driver.get("http://localhost:3000/categoryAdmin");
	        String oldCategoryName = "laptops";
	        String newCategoryName = "gaming-laptops";
	        categories.add(oldCategoryName);
	        categories.remove(oldCategoryName);
	        categories.add(newCategoryName);
	        assertFalse(categories.contains(oldCategoryName), "Old category should be removed");
	        assertTrue(categories.contains(newCategoryName), "New category should be added");
	    }

	    @Test
	    void testDeleteCategory() {
	    	driver.get("http://localhost:3000/categoryAdmin");
	        String categoryName = "accessories";
	        categories.add(categoryName);
	        categories.remove(categoryName);
	        assertFalse(categories.contains(categoryName), "Category should be deleted from the list");
	    }
	}


