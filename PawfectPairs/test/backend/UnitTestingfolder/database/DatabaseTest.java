/*package backend.database;
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



import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Hashtable;
import java.util.Iterator;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import backend.database.*;
import backend.dog.Dog;
import backend.user.User;
import backend.poster.Poster;
import backend.tag.Tag;
import backend.calendar.*;
import java.util.Set;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.HashSet;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class DatabaseTest {
	
    
	 private static Database database =new Database();// Declare a class-level variable
	 private static Dog testDog;
	 private static User userTest;
	 private static Poster posterTest;
	 private static Appointment appointmentTest;
	 private static Appointment appointment;
	 private static ArrayList<Appointment> userAppointment;
	 private static AppointmentManager appointmentManagerTest;
	 private static java.sql.Date sqlDate;

	 
		 
	 
	    
	    @BeforeAll
	    public static void setUpClass() {
	    	if (Database.getUser("123", "123") == null) {
	    		Database.addUser("123", "123", null);
	    	}
	    	userTest = Database.getUser("123", "123");
			//System.out.println(userTest);
	   	
	   	Hashtable<Integer, Poster> posters = database.getAllPosters();
	   	
	   	// Check if the hashtable is not empty
	   	if (!posters.isEmpty()) {
	   	    // Get the first key (assuming it's an Integer)
	   	    Integer firstKey = posters.keys().nextElement();
	   	    // Get the Poster associated with the first key
	   	    posterTest = posters.get(firstKey);
	   	   
	   	}
	   	
	   	Hashtable<Integer, ArrayList<Dog>> dogHashtable = database.getAllDogs(userTest, posters.keySet());
	   	ArrayList<Dog> dogList0 = dogHashtable.get(userTest.getUserID());
	   	testDog = dogList0.get(0);
	   	
	   	//System.out.println(testDog);
	   	String dateString = "2028-03-20";
	   	sqlDate = java.sql.Date.valueOf(dateString);
	   	
	   	appointment = new Appointment(posterTest.getUniqueId(),testDog.getId(),sqlDate,userTest.getUserID());
	   	
	   	userAppointment = new ArrayList<>();
	   	userAppointment.add(appointment);
	   	
	   	appointmentManagerTest = new AppointmentManager(userTest.getUserID(),userAppointment);

	    }
	    
	    @Test
	    public void testEqual() {
	    	assertEquals(2,2);
	    }
	    
	    @Test
	    public void testAppointment() {
	    	database.setUserAppointments(appointmentManagerTest);
	        ArrayList<Appointment> appointmentsTester = database.getUserAppointments(userTest.getUserID());
	       
	        //System.out.println(appointmentsTester.get(appointmentsTester.size()-1).getDate());
	        assertEquals(sqlDate,appointmentsTester.get(appointmentsTester.size()-1).getDate());
	        database.deleteAppointment(userTest.getUserID());

	    }
	    
	    @Test
	    public void testAdoption() {
	    	testDog.setAdopted(false);
	        assertEquals(false, testDog.getAdopted());
	        testDog.setAdopted(true);
	        System.out.println(testDog.getAdopted());
	       
	        database.setDogAdopted(testDog);
	        Hashtable<Integer, Poster> posters = database.getAllPosters();
	        Hashtable<Integer, ArrayList<Dog>> dogHashtables = Database.getAllDogs(userTest, posters.keySet());
	        ArrayList<Dog> dogList1 = dogHashtables.get(userTest.getUserID());
	        Dog dogTest = null;
	        //System.out.println(dogList0.size());
	        //System.out.println(dogList1.size());
	        int counter = 0;
	        for (Dog dog : dogList1) {
	        	if (counter == 0) {
	            if (dog.getId() == testDog.getId()) {
	                dogTest =  dog;
	                counter = counter +1;
	            }
	        	}
	        }
	        testDog = dogTest;
	       
	    	//testDog = dogHashtables.get(1).get(4);
	    	System.out.println("Hey "+ testDog.getAdopted());
	        //System.out.println(testDog.getAdopted());
	        assertEquals(true, testDog.getAdopted());
	        testDog.setAdopted(false);
	      
	        //set it back to false in the db!!!! use connection
	        Connection connection = Database.connect();
	     // SQL query to update adopted status to false where dogid is 174
	        String sql = "UPDATE dog SET adopted = false WHERE dogid = ?";
	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            // Set the parameter for dogId
	            statement.setInt(1, testDog.getId());
	            // Execute the update statement
	            int rowsAffected = statement.executeUpdate();
	           
	        } catch (SQLException e) {
	        	e.printStackTrace();
	        }

	    }
	    
	    @Test
	    public void testTag() {
	    	Connection connection = Database.connect();

	    	Statement statement = null;
			try {
				statement = connection.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       Hashtable<Integer, Tag> tags = Database.getDogTags(testDog.getId(), statement);
	       //System.out.println(tags.size());
	       assertNotNull(tags.size());

	    }
	    
	    @Test
	    public void testGetAllDogs() {
	    	Hashtable<Integer, Poster> posters = database.getAllPosters();
	    	Hashtable<Integer, ArrayList<Dog>> dogHashtableTest = database.getAllDogs(userTest, posters.keySet());
	       	Dog testDog2 = dogHashtableTest.get(userTest.getUserID()).get(0);
	       	assertEquals(testDog.getId(), testDog2.getId());

	    }
	    
	    @Test
	    public void testLikedDogs() {
	    	ArrayList<Dog> dogList = Database.getUsersLikedOrPassedDogs(userTest.getUserID(), "userdogs");
	        //System.out.print(dogList.size());
	        assertNotNull(dogList);

	    }
	    
	    	    
	    
	    
}*/