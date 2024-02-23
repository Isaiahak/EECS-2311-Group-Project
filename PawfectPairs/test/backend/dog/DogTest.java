
package test.backend.dog;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import backend.dog.Dog;
import backend.poster.Poster;
import backend.tag.Tag;

public class DogTest {

    @Test
    public void testDogCreation() {
        Dog dog = new Dog("Max", 1, 3, 0, 1, "M", new Poster(0, "John", 1), false);
        assertNotNull(dog);
        assertEquals("Max", dog.getName());
        assertEquals(1, dog.getId());
        assertEquals(3, dog.getAge());
        assertEquals("Lazy", dog.getEnergyLevel()); // Assuming energy level 0 corresponds to "Lazy"
        assertEquals("Medium", dog.getSize()); // Assuming size 1 corresponds to "Small"
        assertEquals("M", dog.getSex());
        assertFalse(dog.getAdopted());
        assertNotNull(dog.getPoster());
    }

    @Test
    public void testSettingAndGettingAttributes() {
        Dog dog = new Dog("Max", 1, 3, 0, 1, "M", new Poster(0, "John", 1), false);
        assertEquals("Max", dog.getName());
        dog.setName("Buddy");
        assertEquals("Buddy", dog.getName());

        assertEquals(1, dog.getId());
        dog.setId(2);
        assertEquals(2, dog.getId());

        assertEquals(3, dog.getAge());
        dog.setAge(4);
        assertEquals(4, dog.getAge());

  
        

        assertEquals("M", dog.getSex());
        dog.setSex("F");
        assertEquals("F", dog.getSex());

        assertFalse(dog.getAdopted());
        dog.setAdopted(true);
        assertTrue(dog.getAdopted());

        Poster newPoster = new Poster(1, "Jane", 2);
        dog.setPoster(newPoster);
        assertEquals(newPoster, dog.getPoster());
    }
    
    @Test
    public void testInvalidEnergyLevelCreation() {
        // Expecting IllegalArgumentException when creating Dog with invalid energy level
        assertThrows(IllegalArgumentException.class, () -> {
            new Dog("Max", 1, 3, 3, 1, "M", new Poster(0, "John", 1), false);
        });
    }

    @Test
    public void testInvalidSizeCreation() {
        // Expecting IllegalArgumentException when creating Dog with invalid size
        assertThrows(IllegalArgumentException.class, () -> {
            new Dog("Max", 1, 3, 0, 3, "M", new Poster(0, "John", 1), false);
        });
    }

    @Test
    public void testSetInvalidEnergyLevel() {
        
        // Expecting IllegalArgumentException when setting invalid energy level
        assertThrows(IllegalArgumentException.class, () -> {
        	Dog dog = new Dog("Max", 1, 3, 3, 2, "M", new Poster(0, "John", 1), false);
        });
    }

    @Test
    public void testSetInvalidSize() {
       
        // Expecting IllegalArgumentException when setting invalid size
        assertThrows(IllegalArgumentException.class, () -> {
        	Dog dog = new Dog("Max", 1, 3, 0, 3, "M", new Poster(0, "John", 1), false);
        });
    }
    

  
}
=======
package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import backend.dog.Dog;
import backend.poster.Poster;
import backend.tag.Tag;

public class DogTest {

    @Test
    public void testDogCreation() {
        Dog dog = new Dog("Max", 1, 3, 0, 1, "M", new Poster(0, "John", 1), false);
        assertNotNull(dog);
        assertEquals("Max", dog.getName());
        assertEquals(1, dog.getId());
        assertEquals(3, dog.getAge());
        assertEquals("Lazy", dog.getEnergyLevel()); // Assuming energy level 0 corresponds to "Lazy"
        assertEquals("Medium", dog.getSize()); // Assuming size 1 corresponds to "Small"
        assertEquals("M", dog.getSex());
        assertFalse(dog.getAdopted());
        assertNotNull(dog.getPoster());
    }

    @Test
    public void testSettingAndGettingAttributes() {
        Dog dog = new Dog("Max", 1, 3, 0, 1, "M", new Poster(0, "John", 1), false);
        assertEquals("Max", dog.getName());
        dog.setName("Buddy");
        assertEquals("Buddy", dog.getName());

        assertEquals(1, dog.getId());
        dog.setId(2);
        assertEquals(2, dog.getId());

        assertEquals(3, dog.getAge());
        dog.setAge(4);
        assertEquals(4, dog.getAge());

  
        

        assertEquals("M", dog.getSex());
        dog.setSex("F");
        assertEquals("F", dog.getSex());

        assertFalse(dog.getAdopted());
        dog.setAdopted(true);
        assertTrue(dog.getAdopted());

        Poster newPoster = new Poster(1, "Jane", 2);
        dog.setPoster(newPoster);
        assertEquals(newPoster, dog.getPoster());
    }
    
    @Test
    public void testInvalidEnergyLevelCreation() {
        // Expecting IllegalArgumentException when creating Dog with invalid energy level
        assertThrows(IllegalArgumentException.class, () -> {
            new Dog("Max", 1, 3, 3, 1, "M", new Poster(0, "John", 1), false);
        });
    }

    @Test
    public void testInvalidSizeCreation() {
        // Expecting IllegalArgumentException when creating Dog with invalid size
        assertThrows(IllegalArgumentException.class, () -> {
            new Dog("Max", 1, 3, 0, 3, "M", new Poster(0, "John", 1), false);
        });
    }

    @Test
    public void testSetInvalidEnergyLevel() {
        
        // Expecting IllegalArgumentException when setting invalid energy level
        assertThrows(IllegalArgumentException.class, () -> {
        	Dog dog = new Dog("Max", 1, 3, 3, 2, "M", new Poster(0, "John", 1), false);
        });
    }

    @Test
    public void testSetInvalidSize() {
       
        // Expecting IllegalArgumentException when setting invalid size
        assertThrows(IllegalArgumentException.class, () -> {
        	Dog dog = new Dog("Max", 1, 3, 0, 3, "M", new Poster(0, "John", 1), false);
        });
    }
    

  
}

