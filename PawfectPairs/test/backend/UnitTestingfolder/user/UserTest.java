package test.backend.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import backend.user.User;

class UserTest {

    @Test
    void userUsernameTest() {
        User user1 = new User("Bob","12345");
        String expected = "Bob";
        assertEquals(expected, user1.getUsername());
    }

    @Test
    void userPasswordTest() {
        User user1 = new User("Bob","12345");
        String expected = "12345";
        assertEquals(expected, user1.getPassword());
    }

    @Test
    void userChangeUsernameTest() {
        User user1 = new User("Bob", "12345");
        user1.setUsername("Bob2");
        String expected = "Bob2";
        assertEquals(expected, user1.getUsername());
    }

    @Test
    void userChangePasswordTest() {
        User user1 = new User("Bob", "12345");
        user1.setPassword("abcde");
        String expected = "abcde";
        assertEquals(expected, user1.getPassword());
    }

    

}


