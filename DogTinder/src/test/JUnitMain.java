package DogTinder;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

class JUnitMain {

	private DogList dogList;
    private PosterList posterList;
    private User user;

    @BeforeEach
    public void setUp() {
        dogList = new DogList();
        posterList = new PosterList();

        // Create dogs
        Dog dog1 = new Dog("Max", 1, 3, new EnergyLevel(0), new Size(2), "M", new Poster(0, "John", 1), false);
        Dog dog2 = new Dog("Buddy", 2, 4, new EnergyLevel(1), new Size(2), "M", new Poster(0, "John", 2), false);
        Dog dog3 = new Dog("Bella", 3, 2, new EnergyLevel(1), new Size(1), "F", new Poster(0, "Anna", 1), false);

        // Add dogs to list
        dogList.addDog(dog1.getName(), dog1);
        dogList.addDog(dog2.getName(), dog2);
        dogList.addDog(dog3.getName(), dog3);

        // Create user
        user = new User("Garfield", "lovedogs@gmail.com", "1234");
    }

    @Test
    public void testMatchWithPreferredDog() {
        // Add user's preferred tag
        Tag userTag = new Tag(10, "Active");
        user.getTags().add(userTag);

        // Matchmaking
        MatchMaking matchAlg = new MatchMaking();
        Dog preferredDog = matchAlg.match(dogList, user);

        // Check if a preferred dog is found
        assertTrue(preferredDog != null);
        assertEquals("Buddy", preferredDog.getName());
    }

    @Test
    public void testNoMatchWithUserPreferences() {
        // Add user's preferred tag that doesn't match any dog
        Tag userTag = new Tag(10, "Lazy");
        user.getTags().add(userTag);

        // Matchmaking
        MatchMaking matchAlg = new MatchMaking();
        Dog preferredDog = matchAlg.match(dogList, user);

        // Check if no dog is found
        assertNull(preferredDog);
    }

    @Test
    public void testNoDogAvailable() {
        // Empty dog list
        DogList emptyDogList = new DogList();

        // Add user's preferred tag
        Tag userTag = new Tag(10, "Active");
        user.getTags().add(userTag);

        // Matchmaking
        MatchMaking matchAlg = new MatchMaking();
        Dog preferredDog = matchAlg.match(emptyDogList, user);

        // Check if no dog is found
        assertNull(preferredDog);
    }

    @Test
    public void testDogNotAdopted() {
        // Add user's preferred tag
        Tag userTag = new Tag(10, "Active");
        user.getTags().add(userTag);

        // Matchmaking
        MatchMaking matchAlg = new MatchMaking();
        Dog preferredDog = matchAlg.match(dogList, user);

        // Try to adopt the dog
        matchAlg.adopt(user, false, preferredDog);

        // Check if the dog is not adopted
        assertTrue(!preferredDog.getAdopted());
    }

    @Test
    public void testDogAdopted() {
        // Add user's preferred tag
        Tag userTag = new Tag(10, "Active");
        user.getTags().add(userTag);

        // Matchmaking
        MatchMaking matchAlg = new MatchMaking();
        Dog preferredDog = matchAlg.match(dogList, user);

        // Adopt the dog
        matchAlg.adopt(user, true, preferredDog);

        // Check if the dog is adopted
        assertTrue(preferredDog.getAdopted());
    }
   
}

