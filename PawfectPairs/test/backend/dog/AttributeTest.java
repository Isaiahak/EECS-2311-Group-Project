
package test;

import static org.junit.jupiter.api.Assertions.*;


import backend.dog.*;
import backend.poster.*;

import org.junit.jupiter.api.Test;



class AttributeTest {

	@Test
    public void testCompareTo() {
        Attribute attribute1 = new Attribute(10);
        Attribute attribute2 = new Attribute(5);
        Attribute attribute3 = new Attribute(10);

        assertTrue(attribute1.compareTo(attribute2) > 0);
        assertTrue(attribute2.compareTo(attribute1) < 0);
        assertEquals(0, attribute1.compareTo(attribute3));
    }
	
	@Test
	public void testSizeConstructorAndToString() {
		Dog dog1 = new Dog("Max", 1, 3, 0, 0, "M", new Poster(0, "John", 1), false);
		Dog dog2 = new Dog("Max", 1, 3, 1, 1, "M", new Poster(0, "John", 1), false);
		Dog dog3 = new Dog("Max", 1, 3, 2, 2, "M", new Poster(0, "John", 1), false);
        
		

	    assertEquals("Small", dog1.getSize(), "Size is not correct");
        assertEquals("Medium", dog2.getSize(), "Size is not correct");
        assertEquals("Large", dog3.getSize(), "Size is not correct");
	}
	
	@Test
	public void testEnergyConstructorAndToString() {
		Dog dog1 = new Dog("Max", 1, 3, 0, 0, "M", new Poster(0, "John", 1), false);
		Dog dog2 = new Dog("Max", 1, 3, 1, 1, "M", new Poster(0, "John", 1), false);
		Dog dog3 = new Dog("Max", 1, 3, 2, 2, "M", new Poster(0, "John", 1), false);
        
		

	    assertEquals("Lazy", dog1.getEnergyLevel(), "Energy is not correct");
        assertEquals("Moderate", dog2.getEnergyLevel(), "Energy is not correct");
        assertEquals("Energetic", dog3.getEnergyLevel(), "Energy is not correct");
	}
	 
	
	@Test
	public void testInvalidSize() {
		
		
		
		assertThrows(IllegalArgumentException.class, () -> { new Dog("Max", 1, 3, 0, 3, "M", new Poster(0, "John", 1), false);
	    });

	   
	}
	
	@Test
	public void testInvalidWeightForEnergy() {
		
		
		
		assertThrows(IllegalArgumentException.class, () -> { new Dog("Max", 1, 3, 3, 0, "M", new Poster(0, "John", 1), false);
	    });

	   
	}

}
