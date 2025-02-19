import backend.tag.Tag;
import backend.dog.*;
import backend.dog.trait.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class ManualDogTest {
    private Dog dog1;
    private Dog dog2;
    private Tag tag1;
    private Tag tag2;
    private Tag tag3;
    private Tag tag4;


    @BeforeEach
    void setUp() {
        dog1 = new Dog("Buddy", 1, 2, 3, 4, 1, 6, false, "path", "bio");
        dog2 = new Dog("Charlie", 2, 3, 4, 1, 0, 7, true, "path2", "bio2");
        tag1 = new Tag("Friendly", 1);
        tag2 = new Tag("Playful", 2);
        tag3 = new Tag("Angry",3);
        tag4 = new Tag("Silly",4);
    }

    @Test
    void testConstructor1(){
        Dog newDog = new Dog(1,1);
        assertEquals(newDog.getSize(), new Size(1));
        assertEquals(newDog.getEnergyLevel(), new EnergyLevel(1));
    }

    @Test
    void testConstructor2(){
        Dog newDog = new Dog("Timmy", 2, 3, 4, 1, 0, 7, true, "path2", "bio2");
        assertEquals(newDog.getName(), "Timmy");
        assertEquals(newDog.getId(),2);
        assertEquals(newDog.getAge(), new Age(3));
        assertEquals(newDog.getEnergyLevel(),new EnergyLevel(4));
        assertEquals(newDog.getSize(), new Size(1));
        assertEquals(newDog.getSex(), new Sex(0));
        assertEquals(newDog.getPosterId(), 7);
        assertTrue(newDog.getAdopted());
        assertEquals(newDog.getImagePath(), "path2");
        assertEquals(newDog.getBiography(), "bio2");
    }

    @Test
    void testConstructor3(){
        Hashtable<Integer, Tag> newTags = new Hashtable<Integer, Tag>();
        newTags.put(tag1.getTagId(),tag1);
        newTags.put(tag2.getTagId(),tag2);
        dog1.setTags(newTags);
        Dog newDog = new Dog(dog1);
        assertEquals(newDog.getName(),dog1.getName());
        assertEquals(newDog.getId(),dog1.getId());
        assertEquals(newDog.getAge(),dog1.getAge());
        assertEquals(newDog.getEnergyLevel(),dog1.getEnergyLevel());
        assertEquals(newDog.getSize(), dog1.getSize());
        assertEquals(newDog.getSex(), dog1.getSex());
        int counter = 0;
        Hashtable<Integer, Tag> newDogTags = newDog.getTags();
        ArrayList<Integer> newDogTagIds = new ArrayList<Integer>(newDogTags.keySet());
        for(int i = 0; i < newTags.size();i++){
            if (newTags.containsKey(newDogTagIds.get(i))){
                counter++;
            }
        }
        assertTrue(counter == newDogTagIds.size());

    }

    @Test
    void testConstuctor4(){
        Dog newDog = new Dog("Timmy", 2, 3, 4, 1, 0);
        assertEquals(newDog.getName(), "Timmy");
        assertEquals(newDog.getId(),2);
        assertEquals(newDog.getAge(), new Age(3));
        assertEquals(newDog.getEnergyLevel(),new EnergyLevel(4));
        assertEquals(newDog.getSize(), new Size(1));
        assertEquals(newDog.getSex(), new Sex(0));
    }

    @Test
    void testGeneralGettersandSetters(){
        dog1.setName("test");
        dog1.setId(100000);
        dog1.setAge(new Age(1));
        dog1.setEnergyLevel(new EnergyLevel(3));
        dog1.setSize(new Size(1));
        dog1.setSex(new Sex(0));
        dog1.setPosterId(123);
        dog1.setAdopted(true);
        dog1.setImagePath("newPath");
        dog1.setBiography("newBio");
        assertEquals(dog1.getName(), "test");
        assertEquals(dog1.getId(),100000);
        assertEquals(dog1.getAge(), new Age(1));
        assertEquals(dog1.getEnergyLevel(), new EnergyLevel(3));
        assertEquals(dog1.getSize(), new Size(1));
        assertEquals(dog1.getSex(), new Sex(0));
        assertEquals(dog1.getPosterId(), 123);
        assertTrue(dog1.getAdopted());
        assertEquals(dog1.getImagePath(),"newPath");
        assertEquals(dog1.getBiography(),"newBio");

    }

    @Test
    void testCalculateScore(){
        Hashtable<Integer, Tag> userTags = new Hashtable<Integer, Tag>();
        userTags.put(tag1.getTagId(), tag1);
        userTags.put(tag2.getTagId(), tag2);
        userTags.put(tag3.getTagId(), tag3);
        userTags.put(tag4.getTagId(), tag4);

        Hashtable<Integer, Tag> dog1Tags = new Hashtable<Integer, Tag>();
        dog1Tags.put(tag1.getTagId(), tag1);
        dog1Tags.put(tag2.getTagId(), tag2);
        dog1.setTags(dog1Tags);
        assertEquals(dog1.calculateScore(userTags), 2);
        assertEquals(dog1.getScore(), 2);

        Hashtable<Integer, Tag> dog2Tags = new Hashtable<Integer, Tag>();
        dog2Tags.put(tag1.getTagId(), tag1);
        dog2Tags.put(tag2.getTagId(), tag2);
        dog2Tags.put(tag3.getTagId(), tag3);
        dog2Tags.put(tag4.getTagId(), tag4);
        dog2.setTags(dog2Tags);
        assertEquals(dog2.calculateScore(userTags), 4);
        assertEquals(dog2.getScore(), 4);
    }

    @Test
    void testGetandSetOldScore(){
        dog1.setOldScore(123);
        assertEquals(dog1.getOldScore(), 123);
    }

    @Test
    void testCompareTo(){
        Hashtable<Integer, Tag> userTags = new Hashtable<Integer, Tag>();
        userTags.put(tag1.getTagId(), tag1);
        userTags.put(tag2.getTagId(), tag2);
        userTags.put(tag3.getTagId(), tag3);
        userTags.put(tag4.getTagId(), tag4);

        Hashtable<Integer, Tag> dog1Tags = new Hashtable<Integer, Tag>();
        dog1Tags.put(tag1.getTagId(), tag1);
        dog1Tags.put(tag2.getTagId(), tag2);
        dog1Tags.put(tag3.getTagId(), tag3);
        dog1.setTags(dog1Tags);
        dog1.calculateScore(userTags);
        int dog1score = dog1.getScore();


        Hashtable<Integer, Tag> dog2Tags = new Hashtable<Integer, Tag>();
        dog2Tags.put(tag1.getTagId(), tag1);
        dog2Tags.put(tag2.getTagId(), tag2);
        dog2Tags.put(tag3.getTagId(), tag3);
        dog2Tags.put(tag4.getTagId(), tag4);
        dog2.setTags(dog2Tags);
        dog2.calculateScore(userTags);
        int dog2score = dog2.getScore();

        assertTrue(dog2score > dog1score);
        assertTrue(dog1score < dog2score);
        assertTrue(dog1score == dog1score);
        assertTrue(dog2score == dog2score);


    }

    @Test
    void testEqualTo(){
        assertTrue(dog1.equals(dog1));
        assertFalse(dog1.equals(dog2));
        assertFalse(dog2.equals(dog1));
        assertTrue(dog2.equals(dog2));

    }

    @Test
    void TestToString(){
        assertEquals(dog1.toString(),"Dog: Buddy\nID: 1\nAge: Adult: 2 to 6\nEnergy Level: Energetic\nSize: Humongous\nSex: Female\nAdopted: false\nTags:\n");
    }
}