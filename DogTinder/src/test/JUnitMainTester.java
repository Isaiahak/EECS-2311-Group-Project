package test;


import dogs.*;
import poster.*;
import tags.*;
import user.*;
import matchmaking.*;
import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JUnitMainTester {

	private DogList dogList;
    private PosterList posterList;
    private User user;
    private Dog dog1;
    private Dog dog2;
    private Dog dog3;
    private Tag tag1;
    private Tag tag2;
    private Tag tag3;

    @BeforeEach
    public void setUp() {
        dogList = new DogList();
        posterList = new PosterList();
        

        // Create dogs
        dog1 = new Dog("Max", 1, 3, 0, 2, "M", new Poster(0, "John", 1), false);
        dog2 = new Dog("Buddy", 2, 4, 1, 2, "M", new Poster(0, "John", 2), false);
        dog3 = new Dog("Bella", 3, 2, 1, 1, "F", new Poster(0, "Anna", 1), false);

        // Add dogs to list
        dogList.addDog(dog1.getName(), dog1);
        dogList.addDog(dog2.getName(), dog2);
        dogList.addDog(dog3.getName(), dog3);

        // Create user
        user = new User("Garfield", "lovedogs@gmail.com", "1234");
        
     // Create tags for each dog
        Tag tag1 = new Tag(10, "Friendly");
        Tag tag2 = new Tag(8, "Active");
        Tag tag3 = new Tag(6, "Playful");
        
        dog1.getTags().add(tag1);
        dog1.getTags().add(tag2);
        dog2.getTags().add(tag2);
        dog3.getTags().add(tag3);
    }
	
    
//////////////////////////////DOG///////////////////////////////////////////////////////////////////////////
    @Test
    public void addDog() {
    	Dog dog4 = new Dog("Jerry", 3, 2, new EnergyLevel(1), new Size(1), "F", new Poster(0, "Anna", 1), false);
    	
        
    }
    
    
    
    //////////////////////////////DOG MATCHING///////////////////////////////////////////////////////////////////////////
	@Test
    public void testMatchWithPreferredDog() {
        // Add user's preferred tag
        Tag userTag = new Tag(10, "Active");
        user.getTags().add(userTag);

        // Matchmaking
        MatchMaking matchAlg = new MatchMaking();
        Dog preferredDog = matchAlg.match(dogList, user);

        // Check if a preferred dog is found
        assertTrue(preferredDog != null, "There is no preferred dog");
        assertEquals("Preffered Dog is not correct","Buddy", preferredDog.getName()); 
        
    }
	
	@Test
    public void testMatchNoPrefferedDog() {
        // Add user's preferred tag
        Tag userTag = new Tag(10, "Social");
        user.getTags().add(userTag);

        // Matchmaking
        MatchMaking matchAlg = new MatchMaking();
        Dog preferredDog = matchAlg.match(dogList, user);

        // Check if a preferred dog is found
        assertTrue(preferredDog == null, "There is a preferred dog");
        
        
    }
	
	@Test
    public void testAdoptionConfirmed() {
        // Add user's preferred tag
        Tag userTag = new Tag(10, "Active");
        user.getTags().add(userTag);

        // Matchmaking
        MatchMaking matchAlg = new MatchMaking();
        Dog preferredDog = matchAlg.match(dogList, user);
        
        //Adopt
        matchAlg.adopt(user, true, preferredDog);
        // Check Adoption
        assertEquals("Dog was not adopted",preferredDog.getAdopted(),true);
        
    }
	
	@Test
    public void testAdoptionNotConfirmed() {
        // Add user's preferred tag
        Tag userTag = new Tag(10, "Active");
        user.getTags().add(userTag);

        // Matchmaking
        MatchMaking matchAlg = new MatchMaking();
        Dog preferredDog = matchAlg.match(dogList, user);
        
        
        //Adopt
        matchAlg.adopt(user, false, preferredDog);
        // Check Adoption
        assertEquals("Dog was not adopted",preferredDog.getAdopted(),false);
        
    }
	
	//maybe add a test for dog who has already been adopted 
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	
	

}
