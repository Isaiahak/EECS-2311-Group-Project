package backend.dog.trait;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import backend.dog.trait.Age;

class AgeTest {

	 @Test
	    public void testConstructorAndGetters() {
	        Age age = new Age(0);
	        assertEquals("puppy 0 to 1", age.getName()); // Checks if the name is set correctly
	        assertEquals(0, age.getWeight()); // Checks if the weight is set correctly
	        assertEquals(0, age.getType()); // Checks if the type is set correctly
	    }

	    @Test
	    public void testToString() {
	        Age age = new Age(2);
	        assertEquals("Adult 2 and 6", age.toString()); // Checks if toString() returns the correct name
	    }

	    @Test
	    public void testGetNames() {
	        Age age = new Age(3);
	        String[] expectedNames = {"puppy 0 to 1", "Adolescent 1 to 2", "Adult 2 and 6", "Mature Adult 6 to 9", "Senior 9 +"};
	        assertArrayEquals(expectedNames, age.getNames()); // Checks if getNames() returns the correct array
	    }

	    @Test
	    public void testGetType() {
	        Age age = new Age(4);
	        assertEquals(0, age.getType()); // Assuming type 0 for all ages
	    }

	    @Test
	    public void testGetNameBoundaryValues() {
	        // Test with weight 0
	        Age age1 = new Age(0);
	        assertEquals("puppy 0 to 1", age1.getName());

	        // Test with weight equal to the length of the names array - 1
	        Age age2 = new Age(4);
	        assertEquals("Senior 9 +", age2.getName());
	    }

	    @Test
	    public void testGetTypeBoundaryValues() {
	        // Test with weight 0
	        Age age1 = new Age(0);
	        assertEquals(0, age1.getType());

	        // Test with weight equal to the length of the names array - 1
	        Age age2 = new Age(4);
	        assertEquals(0, age2.getType());
	    }

}
