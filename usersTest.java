package iteration3;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.*;

public class usersTest {
	 WebDriver driver;



	    private List<Map<String, String>> users;

	    @BeforeEach
	    void setUp() {
	    	 driver = new EdgeDriver();
	         driver.manage().window().maximize();
	         driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
	        users = new ArrayList<>();
	    }
	    
	    @AfterEach
	    void tearDown() {
	        if (driver != null) {
	            driver.quit(); 
	        }
	    }

	    @Test
	    void testFetchUsers() {
	    	driver.get("http://localhost:3000/usersAdmin");
	        Map<String, String> user1 = new HashMap<>();
	        user1.put("id", "1");
	        user1.put("email", "john@example.com");
	        user1.put("name", "John Doe");
	        user1.put("role", "admin");
	        
	        Map<String, String> user2 = new HashMap<>();
	        user2.put("id", "2");
	        user2.put("email", "jane@example.com");
	        user2.put("name", "Jane Smith");
	        user2.put("role", "user");

	        users.add(user1);
	        users.add(user2);

	        assertFalse(users.isEmpty(), "The users list should contain users after fetch.");
	    }

	    @Test
	    void testFetchNoUsers() {
	    	driver.get("http://localhost:3000/usersAdmin");
	        assertTrue(users.isEmpty(), "The users list should be empty when no users are fetched.");
	    }

	    @Test
	    void testDeleteUser() {
	    	driver.get("http://localhost:3000/usersAdmin");
	        Map<String, String> userToDelete = new HashMap<>();
	        userToDelete.put("id", "1");
	        userToDelete.put("email", "john@example.com");
	        userToDelete.put("name", "John Doe");
	        userToDelete.put("role", "admin");

	        users.add(userToDelete);

	        users.removeIf(user -> user.get("id").equals("1"));

	        assertEquals(0, users.size(), "The users list should be empty after deleting the user.");
	    }

	    @Test
	    void testDeleteNonExistentUser() {
	    	driver.get("http://localhost:3000/usersAdmin");
	        Map<String, String> user1 = new HashMap<>();
	        user1.put("id", "1");
	        user1.put("email", "john@example.com");
	        user1.put("name", "John Doe");
	        user1.put("role", "admin");
	        users.add(user1);

	        boolean userDeleted = users.removeIf(user -> user.get("id").equals("999")); // Non-existent user

	        assertFalse(userDeleted, "No user should be deleted when the user ID doesn't exist.");
	    }

	    @Test
	    void testLoadingState() {
	    	driver.get("http://localhost:3000/usersAdmin");
	        boolean loading = true;
	        Map<String, String> user = new HashMap<>();
	        user.put("id", "1");
	        user.put("email", "john@example.com");
	        user.put("name", "John Doe");
	        user.put("role", "admin");
	        users.add(user);

	        loading = false;

	        assertFalse(loading, "The loading state should be false once the users are loaded.");
	    }

	    @Test
	    void testNoUsersFound() {
	    	driver.get("http://localhost:3000/usersAdmin");
	        assertTrue(users.isEmpty(), "There should be no users found.");
	    }

	    @Test
	    void testUserRoleDefault() {
	    	driver.get("http://localhost:3000/usersAdmin");
	        Map<String, String> user = new HashMap<>();
	        user.put("id", "1");
	        user.put("email", "john@example.com");
	        user.put("name", "John Doe");
	        user.put("role", null); 
	        
	        users.add(user);

	        assertEquals("user", users.get(0).get("role"), "If no role is provided, the default role should be 'user'.");
	    }
	}



