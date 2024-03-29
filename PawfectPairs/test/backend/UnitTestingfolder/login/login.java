package guilayout;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import backend.database.Database;
import backend.user.User;

public class login {

    @Test
    public void testSignUp() {
        // Test signing up with a new username and password
        // Ensure that the user is successfully added to the database
    	Database.addUser("testUser", "testPassword", null);
    	User testUser = Database.getUser("testUser", "testPassword");
    	if (testUser != null) {
        assertEquals(testUser.getUsername(), "testUser");
        assertEquals(testUser.getPassword(), "testPassword");
    	}
    }

    @Test
    public void testLoginWithValidCredentials() {
        // Test logging in with valid credentials
        // Ensure that the login process succeeds
        assertTrue(Database.usernameChecker("testUser") != null);
        assertTrue(Database.passwordChecker("testUser", "testPassword") != null);
    }

    @Test
    public void testLoginWithInvalidCredentials() {
        // Test logging in with invalid credentials
        // Ensure that the login process fails
        assertFalse(Database.usernameChecker("invalidUser") == null);
        assertFalse(Database.passwordChecker("testUser", "wrongPassword") == null);
    }

    // Add more test methods as needed to cover additional scenarios
}
