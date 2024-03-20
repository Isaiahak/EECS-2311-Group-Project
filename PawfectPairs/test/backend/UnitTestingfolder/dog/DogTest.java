package test.backend.dog;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;


import backend.dog.Dog;
import backend.dog.trait.Age;
import backend.poster.Poster;
import backend.tag.Tag;


public class DogTest {
	Poster pi = new Poster(0, "John", 1,"123-456","poster@email.com", 123.5);

    @Test
    public void testDogCreation() {
        Dog dog = new Dog("Max", 1, 3, 0, 1, 0, pi.getUniqueId(), false,null,"bio");
        assertNotNull(dog);
        assertEquals("Max", dog.getName());
        assertEquals(1, dog.getId());
        Age newAge = new Age(3);
        assertEquals(newAge.toString(), dog.getAge().toString());
        assertEquals("Lazy", dog.getEnergyLevel().toString()); //  energy level 0 corresponds to "Lazy"
        assertEquals("Medium", dog.getSize().toString()); //  size 1 corresponds to "Small"
        assertEquals("Male", dog.getSex().toString());
        assertFalse(dog.getAdopted());
       // assertNotNull(dog.getPoster());
    }

    @Test
    public void testSettingAndGettingAttributes() {
    	Poster pi = new Poster(0, "John", 1,"123-456","poster@email.com", 123.5);

        Dog dog = new Dog("Max", 1, 3, 0, 1, 0,pi.getUniqueId(), false,null,"bio");
        assertEquals("Max", dog.getName());
        dog.setName("Buddy");
        assertEquals("Buddy", dog.getName());

        assertEquals(1, dog.getId());
        dog.setId(2);
        assertEquals(2, dog.getId());

//        assertEquals(3, dog.getAge());
//        dog.setAge(4);
//        assertEquals(4, dog.getAge());

  
//        
//
//        assertEquals("M", dog.getSex());
//        dog.setSex("F");
//        assertEquals("F", dog.getSex());
//
//        assertFalse(dog.getAdopted());
        dog.setAdopted(true);
        assertTrue(dog.getAdopted());

        Poster newPoster = new Poster(1, "Jane", 2,"123-456","poster@email.com", 123.5);
        dog.setPosterId(newPoster.getUniqueId());
        assertEquals(newPoster.getUniqueId(), dog.getPosterId());
    }
    
    @Test
    public void testInvalidEnergyLevelCreation() {
        // Expecting IllegalArgumentException when creating Dog with invalid energy level
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            new Dog("Max", 1, 3, 3, 1, 0, pi.getUniqueId(), false,null,"bio");
        });
    }

    @Test
    public void testInvalidSizeCreation() {
        // Expecting IllegalArgumentException when creating Dog with invalid size
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            new Dog("Max", 1, 3, 0, 3, 0,  pi.getUniqueId(), false,null,"bio");
        });
    }

    @Test
    public void testSetInvalidEnergyLevel() {
    	Poster pi = new Poster(0, "John", 1,"123-456","poster@email.com", 123.5);

        
        // Expecting IllegalArgumentException when setting invalid energy level
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
        	Dog dog = new Dog("Max", 1, 3, 3, 2, 0, pi.getUniqueId(), false,null,"bio");
        });
    }

    @Test
    public void testSetInvalidSize() {
    	Poster pi = new Poster(0, "John", 1,"123-456","poster@email.com", 123.5);

        // Expecting IllegalArgumentException when setting invalid size
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
        	Dog dog = new Dog("Max", 1, 3, 0, 3, 0, pi.getUniqueId(), false,null,"bio");
        });
    }
    

  
}




