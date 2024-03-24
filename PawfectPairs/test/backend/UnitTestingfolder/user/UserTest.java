package test.backend.user;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Hashtable;

import org.junit.jupiter.api.Test;

import backend.wallet.*;
import backend.dog.Dog;
import backend.dog.trait.Age;
import backend.dog.trait.Attribute;
import backend.dog.trait.Sex;
import backend.dog.trait.Size;
import backend.tag.Tag;
import backend.user.User;

class UserTest {

    @Test
    void testGetUserID() {
        User user = new User("username", "password");
        user.setUserID(123);
        assertEquals(123, user.getUserID());
    }

    @Test
    void testGetUsername() {
        User user = new User("username", "password");
        assertEquals("username", user.getUsername());
    }

    @Test
    void testSetUsername() {
        User user = new User("username", "password");
        user.setUsername("new_username");
        assertEquals("new_username", user.getUsername());
    }

    @Test
    void testGetEmail() {
        User user = new User("username", "password");
        user.setEmail("user@example.com");
        assertEquals("user@example.com", user.getEmail());
    }

    @Test
    void testSetEmail() {
        User user = new User("username", "password");
        user.setEmail("user@example.com");
        assertEquals("user@example.com", user.getEmail());
    }

    @Test
    void testGetPassword() {
        User user = new User("username", "password");
        assertEquals("password", user.getPassword());
    }

    @Test
    void testSetPassword() {
        User user = new User("username", "password");
        user.setPassword("new_password");
        assertEquals("new_password", user.getPassword());
    }

    @Test
    void testGetLikedDogs() {
        User user = new User("username", "password");
        assertNotNull(user.getLikedDogs());
    }

    @Test
    void testAddLikedDogs() {
        User user = new User("username", "password");
        Dog dog = new Dog("Buddy", 1, 3, 2, 1, 1);
        user.addLikedDogs(dog);
        assertTrue(user.getLikedDogs().contains(dog));
    }

    @Test
    void testGetPassedDogs() {
        User user = new User("username", "password");
        assertNotNull(user.getPassedDogs());
    }

    @Test
    void testGetSizePreferences() {
        User user = new User("username", "password");
        assertNotNull(user.getSizePreferences());
    }

    @Test
    void testGetEnergyLevelPreferences() {
        User user = new User("username", "password");
        assertNotNull(user.getEnergyLevelPreferences());
    }

    @Test
    void testGetSexPreferences() {
        User user = new User("username", "password");
        assertNotNull(user.getSexPreferences());
    }

    @Test
    void testGetAgePreferences() {
        User user = new User("username", "password");
        assertNotNull(user.getAgePreferences());
    }

    @Test
    void testSetTagPreferences() {
        User user = new User("username", "password");
        Hashtable<Integer, Tag> tagPreferences = new Hashtable<>();
        tagPreferences.put(1, new Tag("Tag1"));
        user.setTagPreferences(tagPreferences);
        assertEquals(tagPreferences, user.getTagPreferences());
    }

    // Add more test cases as needed...
}
