import backend.poster.Poster;
import backend.dog.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

class PosterTest {
    private Poster poster1;
    private Poster poster2;
    private Dog dog;

    @BeforeEach
    void setUp() {
        poster1 = new Poster(5, "John Doe", 1, "123-456-7890", "johndoe@example.com", 100.0);
        poster2 = new Poster(4, "Jane Doe", 2, "987-654-3210", "janedoe@example.com", 50.0);
        dog = new Dog("Buddy", 1, 2, 3, 4, 1, 6, false, "path", "bio");
    }

    @Test
    void testGetDisplayName() {
        assertEquals("John Doe", poster1.getDisplayName());
    }

    @Test
    void testSetDisplayName() {
        poster1.setDisplayName("John Smith");
        assertEquals("John Smith", poster1.getDisplayName());
    }

    @Test
    void testGetEmail() {
        assertEquals("johndoe@example.com", poster1.getEmail());
    }

    @Test
    void testGetPhone() {
        assertEquals("123-456-7890", poster1.getPhone());
    }

    @Test
    void testDepositDonation() {
        poster1.depositDonation(50.0);
        assertEquals(150.0, poster1.getBalance());
    }

    @Test
    void testGetBalance() {
        assertEquals(100.0, poster1.getBalance());
    }

    @Test
    void testSetBalance() {
        poster1.setBalance(200.0);
        assertEquals(200.0, poster1.getBalance());
    }

    @Test
    void testSetAndGetDogList() {
        ArrayList<Dog> dogList = new ArrayList<>();
        dogList.add(dog);
        poster1.setDogList(dogList);
        assertEquals(dogList, poster1.getDogList());
    }

    @Test
    void testCompareTo() {
        assertTrue(poster1.compareTo(poster2) < 0);
    }

    @Test
    void testToString() {
        assertTrue(poster1.toString().contains("John Doe"));
    }
}
