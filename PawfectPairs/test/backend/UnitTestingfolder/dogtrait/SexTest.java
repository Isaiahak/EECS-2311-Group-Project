package backend.dog.trait;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import backend.dog.trait.Sex;

class SexTest {

	@Test
    public void testConstructorAndGetters() {
        Sex sex = new Sex(0); // Creating a new Sex object with weight 0 (Male)
        assertEquals("Male", sex.getName()); // Verifying that the name is set correctly
        assertEquals(0, sex.getWeight()); // Verifying that the weight is set correctly
        assertEquals(1, sex.getType()); // Verifying that the type is set correctly
    }

    @Test
    public void testToString() {
        Sex sex = new Sex(1); // Creating a new Sex object with weight 1 (Female)
        assertEquals("Female", sex.toString()); // Verifying that toString() returns the correct name
    }

    @Test
    public void testGetNames() {
        Sex sex = new Sex(0); // Creating a new Sex object with weight 0 (Male)
        String[] expectedNames = {"Male", "Female"};
        assertArrayEquals(expectedNames, sex.getNames()); // Verifying that getNames() returns the correct array
    }

    @Test
    public void testGetType() {
        Sex sex = new Sex(1); // Creating a new Sex object with weight 1 (Female)
        assertEquals(1, sex.getType()); // Verifying that getType() returns the correct type
    }

}
