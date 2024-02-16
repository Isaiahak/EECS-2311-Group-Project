package DogTinder;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PosterTester {
	Poster poster1;
	Poster poster2;
	Dog dog1;
	Dog dog2;
	Dog dog3;
	int weight;
	int weight2; 
	int weight3;

	/*things to test : 
	 * - add dogs to poster - verify if poster is correct
	 * - change name, id and rating
	 * - add dog to list
	 * -remove dog from list
	 * -get poster dogs */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		poster1=new Poster (3,"Jen", 12);
		poster2= new Poster(5, "Harry", 1);
		weight=2;
		weight2=1; 
		weight3=0;

		dog1= new Dog("Pal", 2, 8, new EnergyLevel(weight), new Size(weight), "M", poster1, false);
		dog2= new Dog("Dog", 3, 8, new EnergyLevel(weight2), new Size(weight2), "M", poster1, false);
		dog3= new Dog("Nala", 3, 8, new EnergyLevel(weight3), new Size(weight3), "F", poster1, true);
	}

	@AfterEach
	void tearDown() throws Exception {
	}



	@Test
	void verifyDogsPoster() {
		int weight=2;
		dog1= new Dog("Pal", 3, 8, new EnergyLevel(weight), new Size(weight), "M", poster1, false);
		Poster actual=dog1.getPoster();
		Poster expected= poster1;
		assertEquals(expected, actual, "Correct poster was not identified, expected "+ expected +" but was "+actual);


	}

	@Test
	void changes() {
		poster1.setDisplayName("John");
		poster1.setScore(10);
		poster1.setUniqueId(100);
		ArrayList<Object> expected= new ArrayList <> (List.of("John", 10,100));
		ArrayList<Object> actual = new ArrayList <> (List.of(poster1.getDisplayName(), poster1.getScore(), poster1.getUniqueId()));
		for (int i=0; i<expected.size(); i++) {
			assertEquals(expected.get(i), actual.get(i),"Change failed");

		}
	}

	@Test
	void assignDogtoPoster() {
		dog1.setPoster(poster1);
		String actual=dog1.getPoster().toString();
		String expected= poster1.toString();
		assertEquals(expected, actual, "Correct poster was not identified");


	}
	@Test
	void addDogtoList() {
		int weight=2;
		dog1= new Dog("Pal", 3, 8, new EnergyLevel(weight), new Size(weight), "M", null, false);
		dog1.setPoster(poster1);
		DogList listOfDogs= new DogList();
		listOfDogs.addDog("Pal", dog1);
		Dog actual =listOfDogs.getDog("Pal");
		Dog expected= dog1;
		assertEquals(expected, actual, "Correct dog was not returned");

	}

	@Test
	void RemoveDogsVerifyNum() {
		int numDogs=3;
		DogList newList= new DogList();
		newList.addDog(dog1.getName(), dog1);
		newList.addDog(dog2.getName(), dog2);
		newList.addDog(dog3.getName(), dog3);
		//		TreeMap<String, Dog> actual = newList.getDogMap();
		//		
		//		TreeMap<String, Dog>  expected= new TreeMap<>();
		int actual = newList.getNumDogs();

		assertEquals(numDogs, actual, "Correct number of dogs was not returned");

		newList.removeDog(dog1.getName(), dog1);
		numDogs--;
		actual= newList.getNumDogs();
		assertEquals(numDogs, actual, "Correct number of dogs was not returned");
	}


	@Test
	void GetDogsListBelongingToAPoster() {
		DogList newList= new DogList();
		dog2.setPoster(poster2);

		newList.addDog(dog1.getName(), dog1);
		newList.addDog(dog2.getName(), dog2);
		newList.addDog(dog3.getName(), dog3);
		TreeMap<String, Dog> totalDogs = newList.getDogMap();
		PosterList posterDogs = new PosterList();
		TreeMap<String,Dog> actual = posterDogs.getPosterDogs(poster1, totalDogs);


		TreeMap<String, Dog>  expected= new TreeMap<>();
		expected.put(dog1.getName(), dog1);
		expected.put(dog3.getName(),dog3);

		assertEquals(expected, actual, "Correct list of dogs was not returned");
	}

	@Test
	void RemoveDogsVerifyList() {
		int numDogs=3;
		DogList newList= new DogList();
		newList.addDog(dog1.getName(), dog1);
		newList.addDog(dog2.getName(), dog2);
		newList.addDog(dog3.getName(), dog3);
		newList.removeDog(dog1.getName(), dog1);
		numDogs--;
		TreeMap<String, Dog> totalDogs = newList.getDogMap();
		PosterList posterDogs = new PosterList();
		TreeMap<String,Dog> actual = posterDogs.getPosterDogs(poster1, totalDogs);


		TreeMap<String, Dog>  expected= new TreeMap<>();
		expected.put(dog3.getName(), dog3);
		expected.put(dog2.getName(),dog2);
		


		
		assertEquals(expected, actual, "Correct list of dogs was not returned");
	}
}
