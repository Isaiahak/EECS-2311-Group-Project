package test.backend.dog;

import static org.junit.jupiter.api.Assertions.*;

import java.util.TreeMap;

import backend.dog.*;
import backend.poster.*;

import org.junit.jupiter.api.Test;

class DogListTest {

	@Test
    public void testAddDog() {
        DogList dogList = new DogList();
        Dog dog = new Dog("Max", 1, 3, 0, 1, "M", new Poster(0, "John", 1), false);
        dogList.addDog(dog.getName(), dog);
        dogList.addDog("Max", dog);
        assertEquals(1, dogList.getNumDogs());
        assertEquals(dog, dogList.getDog("Max"));
    }

    @Test
    public void testRemoveDog() {
        DogList dogList = new DogList();
        Dog dog = new Dog("Max", 1, 3, 0, 1, "M", new Poster(0, "John", 1), false);
        dogList.addDog("Max", dog);
        assertEquals(1, dogList.getNumDogs());
        dogList.removeDog("Max", dog);
        assertEquals(0, dogList.getNumDogs());
        assertNull(dogList.getDog("Max"));
    }

    @Test
    public void testGetNumDogs() {
        DogList dogList = new DogList();
        assertEquals(0, dogList.getNumDogs());
        Dog dog1 = new Dog("Max", 1, 3, 0, 1, "M", new Poster(0, "John", 1), false);
        Dog dog2 = new Dog("Buddy", 2, 4, 1, 2, "M", new Poster(0, "John", 1), false);
        dogList.addDog("Max", dog1);
        dogList.addDog("Buddy", dog2);
        assertEquals(2, dogList.getNumDogs());
    }

    @Test
    public void testGetDogMap() {
        DogList dogList = new DogList();
        TreeMap<String, Dog> expectedMap = new TreeMap<>();
        assertEquals(expectedMap, dogList.getDogMap());
        Dog dog1 = new Dog("Max", 1, 3, 0, 1, "M", new Poster(0, "John", 1), false);
        Dog dog2 = new Dog("Buddy", 2, 4, 1, 2, "M", new Poster(0, "John", 1), false);
        dogList.addDog("Max", dog1);
        dogList.addDog("Buddy", dog2);
        expectedMap.put("Max", dog1);
        expectedMap.put("Buddy", dog2);
        assertEquals(expectedMap, dogList.getDogMap());
    }

    @Test
    public void testToString() {
        DogList dogList = new DogList();
        assertEquals("DogList []", dogList.toString());
        Dog dog1 = new Dog("Max", 1, 3, 0, 1, "M", new Poster(0, "John", 1), false);
        Dog dog2 = new Dog("Buddy", 2, 4, 1, 2, "M", new Poster(0, "John", 1), false);
        dogList.addDog("Max", dog1);
        dogList.addDog("Buddy", dog2);
        assertEquals("DogList [Buddy, Max]", dogList.toString());
    }

}
