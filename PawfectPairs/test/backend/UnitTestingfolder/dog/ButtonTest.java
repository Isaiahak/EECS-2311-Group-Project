package guilayout;

import static org.junit.jupiter.api.Assertions.*;

import java.util.PriorityQueue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import guilayout.DogProfileScene;
import guilayout.PrimaryScene;
import backend.dog.Dog;
import backend.user.User;
import guicontrol.AppData;

class UndoButtonTest {

	private PriorityQueue<Dog> allDogs;
	private static User user;
	private static Dog dog;

	@BeforeEach
    public void setUp() {
        // Initialize user and dog queue objects
		user = new User("test", "tests");
		user.setUserID(-1);
		user.setEmail("test@gmail.com");
        Dog dog1 = new Dog ("testDog1",-1,0,0,0,0,-1,false,".","test");
        Dog dog2 = new Dog ("testDog2",-1,0,0,0,0,-1,false,".","test");
        allDogs = new PriorityQueue<Dog>();
        // Populate the dog queue with test data
        allDogs.add(dog1);
        allDogs.add(dog2);
        // Set up user's last removed dog for testing
        user.setLastRemovedDog(dog1);
    }

    @Test
    public void testLikeButton() {
        // Add last removed dog to liked dogs
        user.addLikedDogs(user.getLastRemovedDog());
        // Assert that the last removed dog is removed from liked dogs
        assertTrue(user.getLikedDogs().contains(user.getLastRemovedDog()));
    }

    @Test
    public void testPassButton() {
        // Add last removed dog to passed dogs
        user.addPassedDogs(user.getLastRemovedDog());
        // Assert that the last removed dog is removed from passed dogs
        assertTrue(user.getPassedDogs().contains(user.getLastRemovedDog()));
    }

    @Test
    public void testUndoButtonAddsDogBackToQueue() {
        // Assert that the last removed dog is added back to the dog queue
        assertTrue(allDogs.contains(user.getLastRemovedDog()));
    }
}
