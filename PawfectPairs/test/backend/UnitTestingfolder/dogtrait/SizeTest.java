package backend.dog.trait;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import backend.dog.trait.Size;

class SizeTest {

	@Test
    public void testConstructorAndGetters() {
        Size size = new Size(1); // Creating a new Size object with weight 1 (Medium)
        assertEquals("Medium", size.getName()); // Verifying that the name is set correctly
        assertEquals(1, size.getWeight()); // Verifying that the weight is set correctly
        assertEquals(3, size.getType()); // Verifying that the type is set correctly
    }

    // Test toString() method
    @Test
    public void testToString() {
        Size size = new Size(2); // Creating a new Size object with weight 2 (Large)
        assertEquals("Large", size.toString()); // Verifying that toString() returns the correct name
    }

    // Test getNames() method
    @Test
    public void testGetNames() {
        Size size = new Size(0); // Creating a new Size object with weight 0 (Small)
        String[] expectedNames = {"Small", "Medium", "Large"};
        assertArrayEquals(expectedNames, size.getNames()); // Verifying that getNames() returns the correct array
    }

    // Test getType() method
    @Test
    public void testGetType() {
        Size size = new Size(2); // Creating a new Size object with weight 2 (Large) 
        assertEquals(3, size.getType()); // Verifying that getType() returns the correct type    
    }

}
