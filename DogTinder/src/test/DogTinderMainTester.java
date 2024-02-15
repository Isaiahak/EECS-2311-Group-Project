package poster;

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
        dog1 = new Dog("Max", 1, 3, new EnergyLevel(0), new Size(2), "M", );
        dog2 = new Dog("Buddy", 2, 4, new EnergyLevel(1), new Size(2), "M",);
        dog3 = new Dog("Bella", 3, 2, new EnergyLevel(1), new Size(1), "F",);

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
