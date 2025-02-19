import backend.dog.trait.Age;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AgeTest {
    private Age age0, age1, age2, age3, age4;

    @BeforeEach
    void setUp() {
        age0 = new Age(0); // Puppy
        age1 = new Age(1); // Adolescent
        age2 = new Age(2); // Adult
        age3 = new Age(3); // Mature Adult
        age4 = new Age(4); // Senior
    }

    @Test
    void testAgeNames() {
        assertEquals("Puppy: 0 to 1", age0.getName());
        assertEquals("Adolescent: 1 to 2", age1.getName());
        assertEquals("Adult: 2 to 6", age2.getName());
        assertEquals("Mature: Adult 6 to 9", age3.getName());
        assertEquals("Senior: 9 +", age4.getName());
    }

    @Test
    void testGetWeight() {
        assertEquals(0, age0.getWeight());
        assertEquals(1, age1.getWeight());
        assertEquals(2, age2.getWeight());
        assertEquals(3, age3.getWeight());
        assertEquals(4, age4.getWeight());
    }

    @Test
    void testGetType() {
        assertEquals(0, age0.getType());
    }

    @Test
    void testGetNames() {
        String[] expectedNames = {"Puppy: 0 to 1", "Adolescent: 1 to 2", "Adult: 2 to 6", "Mature: Adult 6 to 9", "Senior: 9 +"};
        assertArrayEquals(expectedNames, age0.getNames());
    }

    @Test
    void testToString() {
        assertEquals("Puppy: 0 to 1", age0.toString());
        assertEquals("Adolescent: 1 to 2", age1.toString());
        assertEquals("Adult: 2 to 6", age2.toString());
        assertEquals("Mature: Adult 6 to 9", age3.toString());
        assertEquals("Senior: 9 +", age4.toString());
    }

    @Test
    void testEquals() {
        Age anotherAge = new Age(2);
        assertEquals(age2, anotherAge);
        assertNotEquals(age0, age1);
    }
}

