package test;

import dogs.DogList;
import poster.Poster;
import dogs.Dog;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DogTinderMainTester {

	private DogList list;
    private Dog dog1;
    private Dog dog2;
    private Dog dog3;

    @BeforeEach
    public void setUp() {
        list = new DogList();
        dog1 = new Dog("Max", 1, 3, 0, 2, "M", new Poster(0, "John", 1), false);
        dog2 = new Dog("Buddy", 2, 4, 1, 2, "M", new Poster(0, "John", 2), false);
        dog3 = new Dog("Bella", 3, 2, 1, 1, "F", new Poster(0, "Anna", 1), false);

        list.addDog(dog1.getName(), dog1);
        list.addDog(dog2.getName(), dog2);
        list.addDog(dog3.getName(), dog3);
    }

    @Test
    public void testAddDogAndGetDog() {
        assertEquals(dog1, list.getDog("Max"));
        assertEquals(dog2, list.getDog("Buddy"));
        assertEquals(dog3, list.getDog("Bella"));
    }

    @Test
    public void testToString() {
        assertEquals("DogList [Bella, Buddy, Max]", list.toString());
    }
    
    @Test
    public void testDog() {
        assertEquals ("Max", dog1.getName());
        assertEquals (1, dog1.getId());
        assertEquals (3, dog1.getAge());
        //still need energyLeve and Size tests
        assertEquals ("M",dog1.getSex());
    }
   
}
