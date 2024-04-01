import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import backend.dog.Dog;
import backend.poster.Poster;
import backend.user.User;
import backend.wallet.Wallet;

class PosterRatingTest {
	private static User user;
	private static Dog dog;
	private static Poster poster;
	
	@BeforeAll
    public static void setUpClass() {
		user = new User("test", "tests");
		user.setUserID(-1);
		
		dog = new Dog("testDog",-1,0,0,0,0,-1,false,".","test");
		user.addLikedDogs(dog);
		
		poster = new Poster(5.0, "testPoster", -1, "000","poster@gmail.com", 0.0,0);
		ArrayList<Dog> posterDogs = new ArrayList<Dog>();
		posterDogs.add(dog);
		poster.setDogList(posterDogs);
	}	

	@Test
	void ratePosterTest() {
		user.addToAdoptedDogs(dog);
		double originalPoster = poster.getScore();
		assertEquals(poster.getScore()== poster.getScore(),originalPoster < ((poster.getScore() * poster.getNumberofRatings()) + 10) / (poster.getNumberofRatings() + 1));
		
	}
	
	@Test
	void numberRatingsTest() {
		double numberScores = poster.getNumberofRatings();
		poster.setNumberofRatings(poster.getNumberofRatings() + 1);
		assertEquals(numberScores+1, poster.getNumberofRatings());
	}
	

	
	

}
