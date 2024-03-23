
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
import static org.junit.Assert.assertNotNull;
public class MainTester {

	 private static Database database =new Database();// Declare a class-level variable
	 private static Dog testDog;
	 private static User userTest;
	 private static Poster posterTest;
	 private static Appointment appointmentTest;
	 private static Appointment appointment;
	 private static ArrayList<Appointment> userAppointment;
	 private static AppointmentManager appointmentManagerTest;
	 private static java.sql.Date sqlDate;
	public static void main(String[] args) {
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

    	
    	Hashtable<Integer, ArrayList<Dog>> dogHashtable = Database.getAllDogs(userTest, posters.keySet());
    	testDog = dogHashtable.get(1).get(4);
    	
    	//System.out.println(testDog);
    	String dateString = "2028-03-20";
    	sqlDate = java.sql.Date.valueOf(dateString);
    	
    	appointment = new Appointment(posterTest.getUniqueId(),testDog.getId(),sqlDate,userTest.getUserID());
    	
    	userAppointment = new ArrayList<>();
    	userAppointment.add(appointment);
    	
    	appointmentManagerTest = new AppointmentManager(userTest.getUserID(),userAppointment);
    	///////////////////////////////////////////////////////////////////////////////////////////////////////////
    	//test assertEquals
        assertEquals(2,2);
         //////////////////////////////////////////////////////////////////////////////////////////////////
        //test Appointment
        database.setUserAppointments(appointmentManagerTest);
        ArrayList<Appointment> appointmentsTester = database.getUserAppointments(userTest.getUserID());
        
        //System.out.println(appointmentsTester.get(appointmentsTester.size()-1).getDate());
        assertEquals(sqlDate,appointmentsTester.get(appointmentsTester.size()-1).getDate());
        database.deleteAppointment(userTest.getUserID());
        //////////////////////////////////////////////////////////////////////////////////////////////////
        //test adoption of dog
        //System.out.println(testDog.getAdopted());
        //System.out.println(testDog.getName());
        testDog.setAdopted(false);
        assertEquals(false, testDog.getAdopted());
        testDog.setAdopted(true);
        System.out.println(testDog.getId());
        database.setDogAdopted(testDog);
        Hashtable<Integer, ArrayList<Dog>> dogHashtables = Database.getAllDogs(userTest, posters.keySet());
        testDog = dogHashtables.get(userTest.getUserID()).get(0);
    	//testDog = dogHashtables.get(1).get(4);
    	System.out.println(testDog.getId());
        //System.out.println(testDog.getAdopted());
        assertEquals(true, testDog.getAdopted());
        testDog.setAdopted(false);
       
        //set it back to false in the db!!!! use connection
        DatabaseConnectorTest databaseConnector = new DatabaseConnectorTest();
        Connection connection = databaseConnector.connect();
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
        //////////////////////////////////////////////////////////////////////////////////////////////////
        //Test tags
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
        //////////////////////////////////////////////////////////////////////////////////////////////////
        //getAllDogs Test
        Hashtable<Integer, ArrayList<Dog>> dogHashtableTest = Database.getAllDogs(userTest, posters.keySet());
    	Dog testDog2 = dogHashtableTest.get(userTest.getUserID()).get(0);
    	assertEquals(testDog.getId(), testDog2.getId());
        //////////////////////////////////////////////////////////////////////////////////////////////////
    	//updateAllAdoptedDogs
    	/*
    	ArrayList<Dog> doglist = new ArrayList<>();
    	testDog.setAdopted(true);
    	doglist.add(testDog);
    	System.out.println(doglist.size());
    	Database.updateAllAdoptedDogs(doglist);
    	Hashtable<Integer, ArrayList<Dog>> dogHashtable2 = Database.getAllDogs(userTest, posters.keySet());
    	testDog = dogHashtable2.get(1).get(4);
        assertEquals(true, testDog.getAdopted());
        testDog.setAdopted(false);
      //set it back to false in the db!!!! use connection
        //DatabaseConnectorTest databaseConnector2 = new DatabaseConnectorTest();
        //Connection connection2 = databaseConnector2.connect();
     // SQL query to update adopted status to false where dogid is 174
        String sql2 = "UPDATE dog SET adopted = false WHERE dogid = ?";

        try (PreparedStatement statement2 = connection.prepareStatement(sql2)) {
            // Set the parameter for dogId
            statement2.setInt(1, testDog.getId());

            // Execute the update statement
            int rowsAffected = statement2.executeUpdate();
            
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        */
        //////////////////////////////////////////////////////////////////////////////////////////////////
        //getUser Liked or Passed Dog Test
        ArrayList<Dog> dogList = Database.getUsersLikedOrPassedDogs(userTest.getUserID(), "userdogs");
        //System.out.print(dogList.size());
        assertNotNull(dogList);
//////////////////////////////////////////////////////////////////////////////////////////////////
        //removeLikedDogsTest
        /*DatabaseConnectorTest databaseConnector3 = new DatabaseConnectorTest();
        Connection connection3 = databaseConnector3.connect();
        System.out.println(userTest.getUserID());
        String sql2 = "DELETE INTO userdogs (dogid, userid) VALUES (?, ?)";
        try (PreparedStatement statement2 = connection3.prepareStatement(sql2)) {
            // Set the parameters for dogId and userId
            statement2.setInt(1, testDog.getId());
            statement2.setInt(2, userTest.getUserID());

            // Execute the update statement
            int rowsAffected = statement2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        

	}
        ArrayList<Dog> dogList2 = Database.getUsersLikedOrPassedDogs(userTest.getUserID(), "userdogs");
        Database.removeLikedDog(testDog.getId(), userTest.getUserID());
        ArrayList<Dog> dogList3 = Database.getUsersLikedOrPassedDogs(userTest.getUserID(), "userdogs");
        //assertEquals(dogList2.size(),dogList3.size()-1);*/
	}
        
	

}

//this needs to be fixed cause database connector is private
	class DatabaseConnectorTest {
		public Connection connect() {
			try {
				Class.forName("org.postgresql.Driver"); // Replace with your database driver
				//Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pawitr2", "postgres", "1234"); // zainab
				//Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/thebestoneyet", "postgres", "doglover123"); // katya
//				Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5434/pawsome", "postgres", "321123"); // isaiah
				Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/finaldb", "postgres", "123"); // Edson
				//System.out.println( "Connected to the PostgreSQL server successfully.");
				return connection;
			} catch (ClassNotFoundException | SQLException e) {
				System.out.println("Connection failed");
				e.printStackTrace();
			}
			return null;
		}
		
	}
