package test.backend.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import backend.user.User;

class UserTest {

	@Test
    void test1() {
        User user1 = new User("Bob", "Bob@gmail.com", "12345");
        String expected = "Bob@gmail.com";
        assertEquals(expected, user1.getEmail());
    }

    @Test
    void test2() {
        User user1 = new User("Bob", "Bob@gmail.com", "12345");
        String expected = "Bob";
        assertEquals(expected, user1.getUsername());
    }

    @Test
    void test3() {
        User user1 = new User("Bob", "Bob@gmail.com", "12345");
        String expected = "12345";
        assertEquals(expected, user1.getPassword());
    }

    @Test
    void test4() {
        User user1 = new User("Bob", "Bob@gmail.com", "12345");
        user1.setEmail("BobBoogaloo@gmail.com");
        String expected = "BobBoogaloo@gmail.com";
        assertEquals(expected, user1.getEmail());
    }

    @Test
    void test5() {
        User user1 = new User("Bob", "Bob@gmail.com", "12345");
        user1.setUsername("Bob2");
        String expected = "Bob2";
        assertEquals(expected, user1.getUsername());
    }

    @Test
    void test6() {
        User user1 = new User("Bob", "Bob@gmail.com", "12345");
        user1.setPassword("abcde");
        String expected = "abcde";
        assertEquals(expected, user1.getPassword());
    }


}
