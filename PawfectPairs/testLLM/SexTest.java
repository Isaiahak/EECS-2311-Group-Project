import backend.dog.trait.Sex;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SexTest {
    private Sex male, female;

    @BeforeEach
    void setUp() {
        male = new Sex(0); // Male
        female = new Sex(1); // Female
    }

    @Test
    void testSexNames() {
        assertEquals("Male", male.getName());
        assertEquals("Female", female.getName());
    }

    @Test
    void testGetWeight() {
        assertEquals(0, male.getWeight());
        assertEquals(1, female.getWeight());
    }

    @Test
    void testGetType() {
        assertEquals(1, male.getType());
        assertEquals(1, female.getType());
    }

    @Test
    void testGetNames() {
        String[] expectedNames = {"Male", "Female"};
        assertArrayEquals(expectedNames, male.getNames());
    }

    @Test
    void testToString() {
        assertEquals("Male", male.toString());
        assertEquals("Female", female.toString());
    }

    @Test
    void testEquals() {
        Sex anotherMale = new Sex(0);
        Sex anotherFemale = new Sex(1);
        assertEquals(male, anotherMale);
        assertEquals(female, anotherFemale);
        assertNotEquals(male, female);
    }
}
