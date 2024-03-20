package backend.dog.trait;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import backend.dog.trait.EnergyLevel;

class EnergyLevelTest {

	 @Test
	    public void testConstructorAndGetters() {
	        EnergyLevel energyLevel = new EnergyLevel(1);
	        assertEquals("Moderate", energyLevel.getName()); // Checks if the name is set correctly
	        assertEquals(1, energyLevel.getWeight()); // Checks if the weight is set correctly
	        assertEquals(2, energyLevel.getType()); // Checks if the type is set correctly
	    }

	    @Test
	    public void testToString() {
	        EnergyLevel energyLevel = new EnergyLevel(2);
	        assertEquals("Energetic", energyLevel.toString()); // Checks if toString() returns the correct name
	    }

	    @Test
	    public void testGetNames() {
	        EnergyLevel energyLevel = new EnergyLevel(0);
	        String[] expectedNames = {"Lazy", "Moderate", "Energetic"};
	        assertArrayEquals(expectedNames, energyLevel.getNames());  // Checks if getNames() returns the correct array
	    }

	    @Test
	    public void testGetType() {
	        EnergyLevel energyLevel = new EnergyLevel(2);
	        assertEquals(2, energyLevel.getType());  // Checks if getType() returns the correct type
	    }

}
