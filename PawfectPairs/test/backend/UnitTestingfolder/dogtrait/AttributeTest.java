package backend.dog.trait;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import backend.dog.trait.Attribute;

class AttributeTest {

	@Test
    public void testCompareTo() {
        Attribute attribute1 = new Age(1);
        Attribute attribute2 = new Age(2);
        Attribute attribute3 = new Age(3);
        Attribute attribute4 = new Age(2);

        assertEquals(-1, attribute1.compareTo(attribute2)); // Checks if attribute1 is less than attribute2
        assertEquals(0, attribute2.compareTo(attribute4)); // Checks if attribute2 is equal to attribute4
        assertEquals(1, attribute3.compareTo(attribute1)); // Checks if attribute3 is greater than attribute1
    }

    @Test
    public void testGetWeight() {
        Attribute attribute = new Age(2);
        assertEquals(2, attribute.getWeight()); // Checks if the weight is returned correctly
    }

    @Test
    public void testSetWeight() {
        Attribute attribute = new Age(3);
        attribute.setWeight(4);
        assertEquals(4, attribute.getWeight()); // Checks if the weight is set correctly
    }

    @Test
    public void testGetName() {
        Attribute attribute = new Age(0);
        assertEquals("Puppy: 0 to 1", attribute.getName()); // Checks if the name is returned correctly
    }

    @Test
    public void testSetName() {
        Attribute attribute = new Age(0);
        attribute.setName("New Name");
        assertEquals("New Name", attribute.getName()); // Checks if the name is set correctly
    }

    @Test
    public void testEquals() {
        Attribute attribute1 = new Age(2);
        Attribute attribute2 = new Age(2);
        Attribute attribute3 = new Age(3);

        assertTrue(attribute1.compareTo(attribute2) == 0); // Checks if attribute1 is not equal to attribute2
        assertFalse(attribute1.compareTo(attribute3) == 0); // Checks if attribute1 is not equal to attribute3
    }

}