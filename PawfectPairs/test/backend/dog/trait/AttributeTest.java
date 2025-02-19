package test.backend.dog.trait;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import backend.dog.trait.Age;
import backend.dog.trait.EnergyLevel;
import backend.dog.trait.Sex;
import backend.dog.trait.Size;
import backend.poster.Poster;

import org.junit.jupiter.api.Test;


public class AttributeTest {
	  @Test
	    public void testEnergyLevelConstructorAndGetters() {
	        EnergyLevel energyLevel = new EnergyLevel(1);
	        assertEquals(1, energyLevel.getWeight());
	        assertEquals("Lazy", energyLevel.getName());

//	        energyLevel.setWeight(2);
//	        assertEquals(2, energyLevel.getWeight());
//	        assertEquals("Energetic", energyLevel.getName());
	    }

	    @Test
	    public void testEnergyLevelToString() {
	        EnergyLevel energyLevel = new EnergyLevel(0);
	        assertEquals("Vegetable", energyLevel.toString());
	    }
	    
	    
	    
	    @Test
	    public void testSexConstructorAndGetters() {
	        Sex sex = new Sex(0);
	        assertEquals(0, sex.getWeight());
	        assertEquals("Male", sex.getName());

//	        sex.setWeight(1);
//	        assertEquals(1, sex.getWeight());
//	        assertEquals("Female", sex.getName());
	    }

	    @Test
	    public void testSexToString() {
	        Sex sex = new Sex(1);
	        assertEquals("Female", sex.toString());
	    }

	    // Add more tests as needed
	    
	    

	    @Test
	    public void testSizeConstructorAndGetters() {
	        Size size = new Size(2);
	        assertEquals(2, size.getWeight());
	        assertEquals("Medium", size.getName());

//	        size.setWeight(0);
//	        assertEquals(0, size.getWeight());
//	        assertEquals("Small", size.getName());
	    }

	    @Test
	    public void testSizeToString() {
	        Size size = new Size(1);
	        assertEquals("Small", size.toString());
	    }

	    // Add more tests as needed

	    @Test
	    public void testAgeConstructorAndGetters() {
	        Age age = new Age(4);
	        assertEquals(4, age.getWeight());
	        assertEquals("Senior: 9 +", age.getName());

//	        age.setWeight(1);
//	        assertEquals(1, age.getWeight());
//	        assertEquals("Adolescent 1 to 2", age.getName());
	    }

	    @Test
	    public void testAgeToString() {
	        Age age = new Age(2);
	        assertEquals("Adult: 2 to 6", age.toString());
	    }

	    // Add more tests as needed
    }
