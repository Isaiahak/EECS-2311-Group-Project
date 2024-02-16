package backend.dog;

import static org.junit.jupiter.api.Assertions.*;

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
		Size smallSize = new Size(0);
	    Size mediumSize = new Size(1);
	    Size largeSize = new Size(2);

	    assertEquals("Small", smallSize.toString());
        assertEquals("Medium", mediumSize.toString());
        assertEquals("Large", largeSize.toString());
	}
	 
	@Test
	public void testInvalidWeightForSize() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			new Size(3);
	    });

	    assertEquals("Error. No matching name for weight entered.", exception.getMessage());
	}
	 
	@Test
	public void testEnergyLevelConstructorAndToString() {
		EnergyLevel lazyLevel = new EnergyLevel(0);
	    EnergyLevel moderateLevel = new EnergyLevel(1);
        EnergyLevel energeticLevel = new EnergyLevel(2);

	    assertEquals("Lazy", lazyLevel.toString());
	    assertEquals("Moderate", moderateLevel.toString());
        assertEquals("Energetic", energeticLevel.toString());
	}
	 
	@Test
	public void testInvalidWeightForEnergyLevel() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			new EnergyLevel(3);
	    });

	    assertEquals("Error. No matching name for weight entered.", exception.getMessage());
	}

}
