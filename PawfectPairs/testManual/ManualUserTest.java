import backend.user.User;
import backend.dog.Dog;
import backend.dog.trait.*;
import backend.poster.Poster;
import backend.tag.Tag;
import backend.wallet.Wallet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ManualUserTest {
    private User user;
    private User user2;
    private Dog dog1;
    private Dog dog2;
    private Dog dog3;
    private Dog dog4;
    private Dog dog5;
    private Dog dog6;
    private Poster poster1;
    private Poster poster2;
    private Tag tag1;
    private Tag tag2;
    private Tag tag3;
    private Tag tag4;
    private ArrayList<Dog> dogList1;
    private ArrayList<Dog> dogList2;

    @BeforeEach
    void setUp() {
        user = new User("testUser", "password123");
        user.setUserID(1);
        user2 = new User("testUser2","password321");
        dog1 = new Dog("bob",2,3,4,1,1,7,false,"path","bio");
        dog2 = new Dog("Buddy", 1, 2, 3, 4, 1, 6, false, "path", "bio");
        dog3 = new Dog("sam",3,3,4,1,1,7,false,"path","bio");
        dog4 = new Dog("sally", 4, 2, 3, 4, 0, 6, false, "path", "bio");
        dog5 = new Dog("todd",5,3,4,1,0,7,false,"path","bio");
        dog6 = new Dog("Bud", 6, 2, 2, 2, 0, 6, false, "path", "bio");
        dogList1 = new ArrayList<Dog>();
        dogList2 = new ArrayList<Dog>();
        poster1 = new Poster(5,"Dylan",2, "987-654-3210", "Dylan@example.com",50.0);
        poster2 = new Poster(4, "John Doe", 1, "123-456-7890", "johndoe@example.com", 100.0);
        tag1 = new Tag("Friendly", 1);
        tag2 = new Tag("Mean", 2);
        tag3 = new Tag("Hyper", 3);
        tag4 = new Tag("Chill", 4);
    }

    @Test
    void testCreatedUser(){
        assertEquals(user.getUsername(), "testUser");
        assertEquals(user.getPassword(),"password123");
        assertEquals(user2.getUsername(), "testUser2");
        assertEquals(user2.getPassword(), "password321");
    }

    @Test
    void testCreateandSetUser(){
        User newUser = new User("123123123","123123123");
        assertEquals(newUser.getUsername(), "123123123");
        assertEquals(newUser.getPassword(), "123123123");
        newUser.setUsername("123123");
        newUser.setPassword("123123");
        assertEquals(newUser.getUsername(),"123123");
        assertEquals(newUser.getPassword(),"123123");
    }

    @Test
    void testGetandSetUserId(){
        user.setUserID(123123);
        assertTrue(user.getUserID() == 123123);
        assertFalse(user.getUserID() == 12123321);

    }

    @Test
    void testSetandGetWallet(){
        Wallet wallet = new Wallet(100.0,1);
        user.setWallet(wallet);
        assertEquals(user.getWallet().getUserid(), 1);
        assertEquals(user.getWallet().getBalance(), 100.0);
    }

    @Test
    void testSetandGetEmail(){
        user.setEmail("email@example.com");
        assertEquals(user.getEmail(),"email@example.com");
        assertFalse(user.getEmail().equals("email@examples.com"));
    }

    @Test
    void testLikedDogsList(){
        user.addLikedDogs(dog1);
        user.addLikedDogs(dog2);
        user.addLikedDogs(dog3);
        ArrayList<Dog> testListCorrect = new ArrayList<Dog>();
        ArrayList<Dog> testListWrong = new ArrayList<Dog>();
        testListCorrect.add(dog1);
        testListCorrect.add(dog2);
        testListCorrect.add(dog3);
        testListWrong.add(dog1);
        testListWrong.add(dog2);
        testListWrong.add(dog5);

        ArrayList<Dog> userDogs = user.getLikedDogs();
        int counter = 0;
        for (int i = 0; i < userDogs.size();i++){
            if(testListCorrect.contains(userDogs.get(i))){
                counter++;
            }
        }
        assertTrue(counter == userDogs.size());

        counter = 0;
        for (int i = 0; i < userDogs.size();i++){
            if(testListWrong.contains(userDogs.get(i))){
                counter++;
            }
        }
        assertFalse(counter == userDogs.size());
    }

    @Test
    void testPassedDogsList(){
        user.addPassedDogs(dog1);
        user.addPassedDogs(dog2);
        user.addPassedDogs(dog3);
        ArrayList<Dog> testListCorrect = new ArrayList<Dog>();
        ArrayList<Dog> testListWrong = new ArrayList<Dog>();
        testListCorrect.add(dog1);
        testListCorrect.add(dog2);
        testListCorrect.add(dog3);
        testListWrong.add(dog1);
        testListWrong.add(dog2);
        testListWrong.add(dog5);
        ArrayList<Dog> userDogs = user.getPassedDogs();
        int counter = 0;
        for (int i = 0; i < userDogs.size();i++){
            if(testListCorrect.contains(userDogs.get(i))){
                counter++;
            }
        }
        assertTrue(counter == userDogs.size());

        counter = 0;
        for (int i = 0; i < userDogs.size();i++){
            if(testListWrong.contains(userDogs.get(i))){
                counter++;
            }
        }
        assertFalse(counter == userDogs.size());
    }

    @Test
    void testGetandSetSizePreferences(){
        ArrayList<Attribute> sizePreferences = new ArrayList<Attribute>();
        Size small = new Size(1);
        Size medium = new Size(2);
        Size large = new Size(3);
        Size Humongous = new Size(4);
        sizePreferences.add(small);
        sizePreferences.add(medium);
        sizePreferences.add(large);
        user.setSizePreferences(sizePreferences);

        ArrayList<Attribute> testPreferencesRight = new ArrayList<Attribute>();
        testPreferencesRight.add(small);
        testPreferencesRight.add(medium);
        testPreferencesRight.add(large);

        ArrayList<Attribute> testPreferencesWrong = new ArrayList<Attribute>();
        testPreferencesWrong.add(Humongous);
        testPreferencesWrong.add(small);
        testPreferencesWrong.add(medium);

        ArrayList<Attribute> userSizePreferences = user.getSizePreferences();
        int counter = 0;
        for (int i = 0; i < userSizePreferences.size();i++){
            if(testPreferencesRight.contains(userSizePreferences.get(i))){
                counter++;
            }
        }
        assertTrue(counter == userSizePreferences.size() );

        counter = 0;
        for (int i = 0; i < userSizePreferences.size();i++){
            if(testPreferencesWrong.contains(userSizePreferences.get(i))){
                counter++;
            }
        }
        assertFalse(counter == userSizePreferences.size());
    }

    @Test
    void testGetandSetEnergyLevelPreferences(){
        ArrayList<Attribute> energyLevelPreferences = new ArrayList<Attribute>();
        EnergyLevel lazy = new EnergyLevel(1);
        EnergyLevel moderate = new EnergyLevel(2);
        EnergyLevel energetic = new EnergyLevel(3);
        EnergyLevel hyper = new EnergyLevel(4);
        energyLevelPreferences.add(lazy);
        energyLevelPreferences.add(moderate);
        energyLevelPreferences.add(energetic);
        user.setEnergyLevelPreferences(energyLevelPreferences);

        ArrayList<Attribute> testPreferencesRight = new ArrayList<Attribute>();
        testPreferencesRight.add(lazy);
        testPreferencesRight.add(moderate);
        testPreferencesRight.add(energetic);

        ArrayList<Attribute> testPreferencesWrong = new ArrayList<Attribute>();
        testPreferencesWrong.add(hyper);
        testPreferencesWrong.add(lazy);
        testPreferencesWrong.add(moderate);

        ArrayList<Attribute> userEnergyLevelPreferences = user.getEnergyLevelPreferences();
        int counter = 0;
        for (int i = 0; i < userEnergyLevelPreferences.size();i++){
            if(testPreferencesRight.contains(userEnergyLevelPreferences.get(i))){
                counter++;
            }
        }
        assertTrue(counter == userEnergyLevelPreferences.size());

        counter = 0;
        for (int i = 0; i < userEnergyLevelPreferences.size();i++){
            if(testPreferencesWrong.contains(userEnergyLevelPreferences.get(i))){
                counter++;
            }
        }
        assertFalse(counter == userEnergyLevelPreferences.size());
    }

    @Test
    void testGetandSetAgePreferences(){
        ArrayList<Attribute> agePreferences = new ArrayList<Attribute>();
        Age puppy = new Age(1);
        Age adolescent = new Age(2);
        Age adult = new Age(3);
        Age matureAdult = new Age(4);
        agePreferences.add(puppy);
        agePreferences.add(adolescent);
        agePreferences.add(adult);
        user.setAgePreferences(agePreferences);

        ArrayList<Attribute> testPreferencesRight = new ArrayList<Attribute>();
        testPreferencesRight.add(puppy);
        testPreferencesRight.add(adolescent);
        testPreferencesRight.add(adult);

        ArrayList<Attribute> testPreferencesWrong = new ArrayList<Attribute>();
        testPreferencesWrong.add(matureAdult);
        testPreferencesWrong.add(puppy);
        testPreferencesWrong.add(adolescent);

        ArrayList<Attribute> userAgePreferences = user.getAgePreferences();
        int counter = 0;
        for (int i = 0; i < userAgePreferences.size();i++){
            if(testPreferencesRight.contains(userAgePreferences.get(i))){
                counter++;
            }
        }
        assertTrue(counter == userAgePreferences.size());

        counter = 0;
        for (int i = 0; i < userAgePreferences.size();i++){
            if(testPreferencesWrong.contains(userAgePreferences.get(i))){
                counter++;
            }
        }
        assertFalse(counter == userAgePreferences.size());
    }

    @Test
    void testGetandSetSexPreferences(){
        ArrayList<Attribute> sexPreferences = new ArrayList<Attribute>();
        Sex male = new Sex(0);
        Sex female = new Sex(1);
        sexPreferences.add(male);
        sexPreferences.add(female);
        user.setSexPreferences(sexPreferences);

        ArrayList<Attribute> testPreferencesRight = new ArrayList<Attribute>();
        testPreferencesRight.add(male);
        testPreferencesRight.add(female);

        ArrayList<Attribute> testPreferencesWrong = new ArrayList<Attribute>();
        testPreferencesWrong.add(male);

        ArrayList<Attribute> userSexPreferences = user.getSexPreferences();
        int counter = 0;
        for (int i = 0; i < userSexPreferences.size();i++){
            if(testPreferencesRight.contains(userSexPreferences.get(i))){
                counter++;
            }
        }
        assertTrue(counter == userSexPreferences.size());

        counter = 0;
        for (int i = 0; i < userSexPreferences.size();i++){
            if(testPreferencesWrong.contains(userSexPreferences.get(i))){
                counter++;
            }
        }
        assertFalse(counter == userSexPreferences.size());
    }

    @Test
    void testGetandSetTagPreferences(){
        Hashtable<Integer, Tag> tagPreferences = new Hashtable<Integer, Tag>();
        tagPreferences.put(tag1.getWeight(), tag1);
        tagPreferences.put(tag2.getWeight(), tag2);
        tagPreferences.put(tag3.getWeight(), tag3);
        user.setTagPreferences(tagPreferences);

        Hashtable<Integer, Tag> testTagPreferencesRight = new Hashtable<Integer, Tag>();
        testTagPreferencesRight.put(tag1.getWeight(), tag1);
        testTagPreferencesRight.put(tag2.getWeight(), tag2);
        testTagPreferencesRight.put(tag3.getWeight(), tag3);

        Hashtable<Integer, Tag> testTagPreferencesWrong = new Hashtable<Integer, Tag>();
        testTagPreferencesWrong.put(tag4.getWeight(), tag4);
        testTagPreferencesWrong.put(tag1.getWeight(), tag1);


        Hashtable<Integer, Tag> userTagPreferences = user.getTagPreferences();
        ArrayList<Integer> userTagIds = new ArrayList<Integer>(userTagPreferences.keySet());
        int counter = 0;
        for (int i = 0; i < userTagPreferences.size();i++){
            if(testTagPreferencesRight.containsKey(userTagIds.get(i))){
                counter++;
            }
        }
        assertTrue(counter == userTagPreferences.size());

        counter = 0;
        for (int i = 0; i < userTagPreferences.size();i++){
            if(testTagPreferencesWrong.containsKey(userTagPreferences.get(i))){
                counter++;
            }
        }
        assertFalse(counter == userTagPreferences.size());
    }

    @Test
    void testArePreferencesEqual(){
        Hashtable<Integer, Tag> tagPreferences1 = new Hashtable<Integer, Tag>();
        tagPreferences1.put(tag1.getTagId(), tag1);
        tagPreferences1.put(tag2.getTagId(), tag2);
        tagPreferences1.put(tag3.getTagId(), tag3);


        Hashtable<Integer, Tag> tagPreferences2 = new Hashtable<Integer, Tag>();
        tagPreferences2.put(tag1.getTagId(), tag1);
        tagPreferences2.put(tag2.getTagId(), tag2);

        Hashtable<Integer, Tag> tagPreferences3 = new Hashtable<Integer, Tag>();
        tagPreferences3.put(tag1.getTagId(), tag1);

        Hashtable<Integer, Tag> tagPreferences4 = new Hashtable<Integer, Tag>();
        tagPreferences4.put(tag1.getTagId(), tag1);
        tagPreferences4.put(tag2.getTagId(), tag2);
        tagPreferences4.put(tag3.getTagId(), tag3);
        tagPreferences4.put(tag4.getTagId(), tag4);

        Hashtable<Integer, Tag> userTagPreferences = new Hashtable<Integer, Tag>();
        userTagPreferences.put(tag1.getTagId(), tag1);
        userTagPreferences.put(tag2.getTagId(), tag2);
        userTagPreferences.put(tag4.getTagId(), tag4);
        user.setTagPreferences(userTagPreferences);

        Hashtable<Integer, Tag> tagPreferences5 = new Hashtable<Integer, Tag>();
        tagPreferences5.put(tag1.getTagId(), tag1);
        tagPreferences5.put(tag2.getTagId(), tag2);
        tagPreferences5.put(tag4.getTagId(), tag4);


        assertFalse(user.arePreferencesEqual(tagPreferences1));
        assertFalse(user.arePreferencesEqual(tagPreferences2));
        assertFalse(user.arePreferencesEqual(tagPreferences3));
        assertFalse(user.arePreferencesEqual(tagPreferences4));
        assertTrue(user.arePreferencesEqual(tagPreferences5));
    }
    @Test
    void testAreAttributesEqual(){
        //sex
        ArrayList<Attribute> sexPreferences = new ArrayList<Attribute>();
        Sex male = new Sex(0);
        Sex female = new Sex(1);

        ArrayList<Attribute> testSexPreferencesRight = new ArrayList<Attribute>();
        testSexPreferencesRight.add(male);
        testSexPreferencesRight.add(female);

        ArrayList<Attribute> testSexPreferencesWrong = new ArrayList<Attribute>();
        testSexPreferencesWrong.add(male);

        sexPreferences.add(male);
        sexPreferences.add(female);

        //age

        ArrayList<Attribute> agePreferences = new ArrayList<Attribute>();
        Age puppy = new Age(1);
        Age adolescent = new Age(2);
        Age adult = new Age(3);

        ArrayList<Attribute> testAgePreferencesRight = new ArrayList<Attribute>();
        testAgePreferencesRight.add(puppy);
        testAgePreferencesRight.add(adolescent);
        testAgePreferencesRight.add(adult);

        ArrayList<Attribute> testAgePreferencesWrong = new ArrayList<Attribute>();
        testAgePreferencesWrong.add(puppy);
        testAgePreferencesWrong.add(adolescent);

        agePreferences.add(puppy);
        agePreferences.add(adolescent);
        agePreferences.add(adult);

        //energy level

        ArrayList<Attribute> energyLevelPreferences = new ArrayList<Attribute>();
        EnergyLevel lazy = new EnergyLevel(1);
        EnergyLevel moderate = new EnergyLevel(2);
        EnergyLevel energetic = new EnergyLevel(3);

        ArrayList<Attribute> testEnergyLevelPreferencesRight = new ArrayList<Attribute>();
        testEnergyLevelPreferencesRight.add(lazy);
        testEnergyLevelPreferencesRight.add(moderate);
        testEnergyLevelPreferencesRight.add(energetic);

        ArrayList<Attribute> testEnergyLevelPreferencesWrong = new ArrayList<Attribute>();
        testEnergyLevelPreferencesWrong.add(lazy);
        testEnergyLevelPreferencesWrong.add(moderate);

        energyLevelPreferences.add(lazy);
        energyLevelPreferences.add(moderate);
        energyLevelPreferences.add(energetic);
        //size

        ArrayList<Attribute> sizePreferences = new ArrayList<Attribute>();
        Size small = new Size(1);
        Size medium = new Size(2);
        Size large = new Size(3);

        ArrayList<Attribute> testSizePreferencesRight = new ArrayList<Attribute>();
        testSizePreferencesRight.add(small);
        testSizePreferencesRight.add(medium);
        testSizePreferencesRight.add(large);

        ArrayList<Attribute> testSizePreferencesWrong = new ArrayList<Attribute>();
        testSizePreferencesWrong.add(small);
        testSizePreferencesWrong.add(medium);

        sizePreferences.add(small);
        sizePreferences.add(medium);
        sizePreferences.add(large);

        user.setAgePreferences(agePreferences);
        user.setEnergyLevelPreferences(energyLevelPreferences);
        user.setSexPreferences(sexPreferences);
        user.setSizePreferences(sizePreferences);

        assertTrue(user.areAttributesEqual(testSexPreferencesRight,testAgePreferencesRight,testSizePreferencesRight,testEnergyLevelPreferencesRight));
        assertFalse(user.areAttributesEqual(testSexPreferencesWrong,testAgePreferencesWrong,testSizePreferencesWrong,testEnergyLevelPreferencesWrong));
    }

    @Test
    void testGetCopyOfSexPreferences(){
        ArrayList<Attribute> sexPreferences = new ArrayList<Attribute>();
        Sex male = new Sex(1);
        Sex female = new Sex(0);
        user.setSexPreferences(sexPreferences);
        ArrayList<Attribute> copyPreferences = user.getCopyOfSexPreferences(user.getSexPreferences());
        int counter = 0;
        ArrayList<Attribute> userSexPreferences = user.getSexPreferences();
        for(int i = 0; i < copyPreferences.size();i++){
            if(copyPreferences.contains(userSexPreferences.get(i))){
                counter++;
            }
        }
        ArrayList<Attribute> sexPreferenceEmpty = new ArrayList<Attribute>();
        user.setSexPreferences(sexPreferenceEmpty);
        assertTrue(user.getCopyOfSexPreferences(user.getSexPreferences()).isEmpty());
        assertTrue(counter == userSexPreferences.size());
    }

    @Test
    void testGetCopyOfAgePreferences(){
        ArrayList<Attribute> agePreferences = new ArrayList<Attribute>();
        Age puppy = new Age(1);
        Age adolescent = new Age(2);
        Age adult = new Age(3);
        user.setAgePreferences(agePreferences);
        ArrayList<Attribute> copyPreferences = user.getCopyOfAgePreferences(user.getAgePreferences());
        int counter = 0;
        ArrayList<Attribute> userAgePreferences = user.getAgePreferences();
        for(int i = 0; i < copyPreferences.size();i++){
            if(copyPreferences.contains(userAgePreferences.get(i))){
                counter++;
            }
        }

        assertTrue(counter == userAgePreferences.size());
    }

    @Test
    void testGetCopyOfSizePreferences(){
        ArrayList<Attribute> sizePreferences = new ArrayList<Attribute>();
        Size small = new Size(1);
        Size medium = new Size(2);
        Size large = new Size(3);
        user.setSizePreferences(sizePreferences);
        ArrayList<Attribute> copyPreferences = user.getCopyOfSizePreferences(user.getSizePreferences());
        int counter = 0;
        ArrayList<Attribute> userSizePreferences = user.getSizePreferences();
        for(int i = 0; i < copyPreferences.size();i++){
            if(copyPreferences.contains(userSizePreferences.get(i))){
                counter++;
            }
        }

        assertTrue(counter == userSizePreferences.size());
    }

    @Test
    void testGetCopyOfEnergyLevelPreferences(){
        ArrayList<Attribute> energyLevelPreferences = new ArrayList<Attribute>();
        EnergyLevel lazy = new EnergyLevel(1);
        EnergyLevel moderate = new EnergyLevel(2);
        EnergyLevel energetic = new EnergyLevel(3);
        user.setEnergyLevelPreferences(energyLevelPreferences);
        ArrayList<Attribute> copyPreferences = user.getCopyOfEnergyLevelPreferences(user.getEnergyLevelPreferences());
        int counter = 0;
        ArrayList<Attribute> userEnergyLevelPreferences = user.getEnergyLevelPreferences();
        for(int i = 0; i < copyPreferences.size();i++){
            if(copyPreferences.contains(userEnergyLevelPreferences.get(i))){
                counter++;
            }
        }

        assertTrue(counter == userEnergyLevelPreferences.size());
    }

    @Test
    void testGetCopyOfTagPreferences(){
        Hashtable<Integer, Tag> tagPreferences = new Hashtable<Integer, Tag>();
        tagPreferences.put(tag1.getTagId(), tag1);
        tagPreferences.put(tag2.getTagId(), tag2);
        tagPreferences.put(tag3.getTagId(), tag3);

        user.setTagPreferences(tagPreferences);
        Hashtable<Integer, Tag> userTagPreferences = user.getTagPreferences();

        Hashtable<Integer, Tag> copyPreferences = user.getCopyOfTagPreferences(userTagPreferences);
        int counter = 0;
        ArrayList<Integer> userTagIds = new ArrayList<Integer>(copyPreferences.keySet());
        for(int i = 0; i < copyPreferences.size();i++){
            if(userTagPreferences.containsKey(userTagIds.get(i))){
                counter++;
            }
        }

        assertTrue(counter == userTagPreferences.size());

        tagPreferences = new Hashtable<Integer, Tag>();
        user.setTagPreferences(tagPreferences);
        copyPreferences = user.getCopyOfTagPreferences(user.getTagPreferences());
        assertTrue(copyPreferences.isEmpty());

    }

    @Test
    void testGetSponsoredDogs(){
        assertNull(user.getSponsoredDogs());
    }

    @Test
    void testToString(){
        user.setEmail("test@example.com");
        assertEquals("User [username=testUser, email=test@example.com, password=password123]",user.toString());
    }

}