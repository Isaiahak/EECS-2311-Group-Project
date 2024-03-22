package backend.dog.trait;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import backend.dog.trait.Sex;

class SexTest {

	@Test
    public void testConstructorAndGetters() {

		
		Sex maleSex = new Sex(0);
        assertEquals("Male", maleSex.getName()); // Creating a new Sex object with weight 0 (Male)
        assertEquals(0, maleSex.getWeight());
        assertEquals(1, maleSex.getType());

        Sex femaleSex = new Sex(1);
        assertEquals("Female", femaleSex.getName()); // Verifying that the name is set correctly
        assertEquals(1, femaleSex.getWeight()); // Verifying that the weight is set correctly
        assertEquals(1, femaleSex.getType());// Verifying that the type is set correctly
    }

    @Test
    public void testToString() {
    	Sex sex0 = new Sex(0);
        assertEquals("Male", sex0.toString()); // Checks if toString() returns the correct name

        Sex sex1 = new Sex(1);
        assertEquals("Female", sex1.toString()); // Checks if toString() returns the correct name
    }

    @Test
    public void testGetNames() {
    	
    	Sex sex0 = new Sex(0);
        String[] expectedNames = {"Male", "Female"};
        assertArrayEquals(expectedNames, sex0.getNames()); // Verifying that getNames() returns the correct array

        Sex sex1 = new Sex(1);
        assertArrayEquals(expectedNames, sex1.getNames()); // Verifying that getNames() returns the correct array
    }

    @Test
    public void testGetType() {
        
        Sex sex0 = new Sex(0);
        assertEquals(1, sex0.getType()); // Verifying that getType() returns the correct type

        Sex sex1 = new Sex(1);
        assertEquals(1, sex1.getType()); // Verifying that getType() returns the correct type
    }

}
