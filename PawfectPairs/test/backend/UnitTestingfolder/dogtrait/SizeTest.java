package backend.dog.trait;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import backend.dog.trait.Size;

class SizeTest {

	@Test
    public void testConstructorAndGetters() {
		
		Size smallSize = new Size(0);
        assertEquals("Tiny", smallSize.getName()); // Verifying that the name is set correctly
        assertEquals(0, smallSize.getWeight()); // Verifying that the weight is set correctly
        assertEquals(3, smallSize.getType()); // Verifying that the type is set correctly

        Size mediumSize = new Size(1);
        assertEquals("Small", mediumSize.getName()); // Verifying that the name is set correctly
        assertEquals(1, mediumSize.getWeight()); // Verifying that the weight is set correctly
        assertEquals(3, mediumSize.getType()); // Verifying that the type is set correctly

        Size largeSize = new Size(2);
        assertEquals("Medium", largeSize.getName()); // Verifying that the name is set correctly
        assertEquals(2, largeSize.getWeight()); // Verifying that the weight is set correctly
        assertEquals(3, largeSize.getType()); // Verifying that the type is set correctly
    }

    // Test toString() method
    @Test
    public void testToString() {
    	Size size = new Size(2); // Creating a new Size object with weight 2 (Large)
        assertEquals("Medium", size.toString()); // Verifying that toString() returns the correct name

        Size anotherSize = new Size(1);
        assertEquals("Small", anotherSize.toString());
    }

    // Test getNames() method
    @Test
    public void testGetNames() {
        Size size = new Size(0); // Creating a new Size object with weight 0 (Small)
        String[] expectedNames = {"Tiny","Small", "Medium","Large","Humongous"};
        assertArrayEquals(expectedNames, size.getNames()); // Verifying that getNames() returns the correct array
        
        Size anotherSize = new Size(2);
        assertArrayEquals(expectedNames, anotherSize.getNames());
    }

    // Test getType() method
    @Test
    public void testGetType() {
        Size size = new Size(2); // Creating a new Size object with weight 2 (Large) 
        assertEquals(3, size.getType()); // Verifying that getType() returns the correct type    
        
        Size anotherSize = new Size(1);
        assertEquals(3, anotherSize.getType());
    }

    @Test
    public void testGetNameBoundaryValues() {
        // Test with weight 0
    	Size size1 = new Size(0);
        assertEquals("Tiny", size1.getName());

        // Test with weight equal to the length of the names array - 1
        Size size2 = new Size(2);
        assertEquals("Medium", size2.getName());
    }

    @Test
    public void testGetTypeBoundaryValues() {
        // Test with weight 0
        Size size1 = new Size(0);
        assertEquals(3, size1.getType());

        // Test with weight equal to the length of the names array - 1
        Size size2 = new Size(2);
        assertEquals(3, size2.getType());
    }

}