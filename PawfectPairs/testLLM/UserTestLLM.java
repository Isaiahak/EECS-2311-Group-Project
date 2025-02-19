import backend.user.User;
import backend.dog.Dog;
import backend.dog.trait.Attribute;
import backend.poster.Poster;
import backend.tag.Tag;
import backend.wallet.Wallet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.*;

class UserTestLLM {
    private User user;
    private Dog dog;
    private Poster poster;
    private Tag tag;

    @BeforeEach
    void setUp() {
        user = new User("testUser", "password123");
        dog = new Dog("Buddy", 1, 2, 3, 4, 1, 6, false, "path", "bio");
        poster = new Poster(4, "John Doe", 1, "123-456-7890", "johndoe@example.com", 100.0);
        tag = new Tag("Friendly", 1);
    }

    @Test
    void testSetAndGetUsername() {
        user.setUsername("newUser");
        assertEquals("newUser", user.getUsername());
    }

    @Test
    void testSetAndGetPassword() {
        user.setPassword("newPassword");
        assertEquals("newPassword", user.getPassword());
    }

    @Test
    void testSetAndGetWallet() {
        Wallet wallet = new Wallet(200.0, 1);
        user.setWallet(wallet);
        assertEquals(wallet, user.getWallet());
    }

    @Test
    void testAddAndGetLikedDogs() {
        user.addLikedDogs(dog);
        assertTrue(user.getLikedDogs().contains(dog));
    }


    @Test
    void testAddAndGetPassedDogs() {
        user.addPassedDogs(dog);
        assertTrue(user.getPassedDogs().contains(dog));
    }

    @Test
    void testSetAndGetTagPreferences() {
        Hashtable<Integer, Tag> tagPreferences = new Hashtable<>();
        tagPreferences.put(1, tag);
        user.setTagPreferences(tagPreferences);
        assertEquals(tagPreferences, user.getTagPreferences());
    }

    @Test
    void testArePreferencesEqual() {
        Hashtable<Integer, Tag> tags = new Hashtable<>();
        tags.put(1, tag);
        user.setTagPreferences(tags);
        assertTrue(user.arePreferencesEqual(tags));
    }

    @Test
    void testAreAttributesEqual() {
        ArrayList<Attribute> age = new ArrayList<>();
        ArrayList<Attribute> sex = new ArrayList<>();
        ArrayList<Attribute> size = new ArrayList<>();
        ArrayList<Attribute> energy = new ArrayList<>();
        user.setAgePreferences(age);
        user.setSexPreferences(sex);
        user.setSizePreferences(size);
        user.setEnergyLevelPreferences(energy);
        assertTrue(user.areAttributesEqual(sex, age, size, energy));
    }
}
