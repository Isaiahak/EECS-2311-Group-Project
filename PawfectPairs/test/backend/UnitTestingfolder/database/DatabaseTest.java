import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Hashtable;

import java.sql.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import backend.database.Database;
import backend.dog.Dog;
import backend.user.User;
import backend.poster.Poster;
import backend.calendar.*;
import java.util.Set;
import java.util.Hashtable;
import java.util.HashSet;

class DatabaseTest {
    
	 private static Database database =new Database();// Declare a class-level variable
	 private static Dog testDog;
	 private static User userTest;
	 private static Poster posterTest;
	 private static Appointment appointmentTest;
	 
	    
	    @BeforeAll
	    public static void setUpClass() {
	    	userTest = new User ("UserTest", "testPassword");
	    	if (database.getUser(userTest.getUsername(), userTest.getPassword())== null) {
	        database.addUser("UserTest", "testPassword", null);	
	    	}
	    	//Set<Integer> posterIds = new HashSet<>();
	        /*String name = "TestDog";
	        int id = 1;
	        int age = 3;
	        int energyLevel = 2; // Assuming some predefined values
	        int size = 2; // Assuming some predefined values
	        int sex = 1; // Assuming some predefined values
	        
	        // Create a new Dog object using the predefined values
	        testDog = new Dog(name, id, age, energyLevel, size, sex);
	        testDog.setAdopted(false);
	        
	        // Manually add to db Dog and Poster :( OR get exisitng ones and play around with it :)
	        int score = 2;
	        String displayName = "TestPoster";
	        int uniqueId = 99;
	        String phone= "NA";
	        String email = "NA";
	        double balance = 0.0;
	        posterTest = new Poster(score, displayName, uniqueId, phone, email, balance);
	        
	        //(int posterID, int dogID, Date date, int userID)
	        
	        appointmentTest = new Appointment();*/
	    	
	    	Hashtable<Integer, Poster> posters = database.getAllPosters();

	    	// Check if the hashtable is not empty
	    	if (!posters.isEmpty()) {
	    	    // Get the first key (assuming it's an Integer)
	    	    Integer firstKey = posters.keys().nextElement();
	    	    
	    	    // Get the Poster associated with the first key
	    	    posterTest = posters.get(firstKey);
	    	    
	    	}
	    	//Database.getAllDogs(userTest, null)
	        
	         
	        
	        
	        
	    }
	    
	    @Test
	    public void deleteAppointmentTest() {
	    	
	    }
}