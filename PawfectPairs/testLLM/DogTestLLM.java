import backend.tag.Tag;
        import backend.dog.*;
        import backend.dog.trait.*;

        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;

        import java.util.Hashtable;

        import static org.junit.jupiter.api.Assertions.*;

class DogTestLLM {
    private Dog dog1;
    private Dog dog2;
    private Tag tag1;
    private Tag tag2;

    @BeforeEach
    void setUp() {
        dog1 = new Dog("Buddy", 1, 2, 3, 4, 1, 6, false, "path", "bio");
        dog2 = new Dog("Charlie", 2, 3, 4, 1, 0, 7, true, "path2", "bio2");
        tag1 = new Tag("Friendly", 1);
        tag2 = new Tag("Playful", 2);
    }

    @Test
    void testGetAndSetName() {
        assertEquals("Buddy", dog1.getName());
        dog1.setName("Max");
        assertEquals("Max", dog1.getName());
    }

    @Test
    void testGetAndSetId() {
        assertEquals(1, dog1.getId());
        dog1.setId(10);
        assertEquals(10, dog1.getId());
    }

    @Test
    void testGetAndSetAge() {
        Age newAge = new Age(1);
        dog1.setAge(newAge);
        assertEquals(newAge, dog1.getAge());
    }

    @Test
    void testGetAndSetEnergyLevel() {
        EnergyLevel newEnergy = new EnergyLevel(4);
        dog1.setEnergyLevel(newEnergy);
        assertEquals(newEnergy, dog1.getEnergyLevel());
    }

    @Test
    void testGetAndSetSize() {
        Size newSize = new Size(3);
        dog1.setSize(newSize);
        assertEquals(newSize, dog1.getSize());
    }

    @Test
    void testGetAndSetSex() {
        Sex newSex = new Sex(1);
        dog1.setSex(newSex);
        assertEquals(newSex, dog1.getSex());
    }

    @Test
    void testGetAndSetPosterId() {
        assertEquals(6, dog1.getPosterId());
        dog1.setPosterId(9);
        assertEquals(9, dog1.getPosterId());
    }

    @Test
    void testGetAndSetAdopted() {
        assertFalse(dog1.getAdopted());
        dog1.setAdopted(true);
        assertTrue(dog1.getAdopted());
    }

    @Test
    void testGetAndSetBiography() {
        assertEquals("bio", dog1.getBiography());
        dog1.setBiography("new bio");
        assertEquals("new bio", dog1.getBiography());
    }

    @Test
    void testGetAndSetTags() {
        Hashtable<Integer, Tag> tags = new Hashtable<>();
        tags.put(1, tag1);
        tags.put(2, tag2);
        dog1.setTags(tags);
        assertEquals(tags, dog1.getTags());
    }

    @Test
    void testCalculateScore() {
        Hashtable<Integer, Tag> tags = new Hashtable<>();
        tags.put(1, tag1);
        dog1.setTags(tags);
        assertEquals(1, dog1.calculateScore(tags));
    }

    @Test
    void testEquals() {
        Dog copyDog = new Dog(dog1);
        assertEquals(dog1, copyDog);
        assertNotEquals(dog1, dog2);
    }

    @Test
    void testCompareTo() {
        dog1.calculateScore(new Hashtable<>());
        dog2.calculateScore(new Hashtable<>());
        assertTrue(dog2.compareTo(dog1) > 0 || dog2.compareTo(dog1) == 0);
    }

    @Test
    void testToString() {
        assertTrue(dog1.toString().contains("Buddy"));
    }
}
