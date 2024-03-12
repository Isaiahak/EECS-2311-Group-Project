import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.TreeMap;
import backend.database.Database;
import backend.calendar.Appointment;
import backend.calendar.AppointmentManager;
import backend.dog.Dog;
import backend.dog.trait.Attribute;
import backend.poster.Poster;
import backend.tag.Tag;
import backend.user.User;

class IntegrationTestCalendar {

    // Test case to check if the deleteAppointment method deletes an appointment from the database.
//    @Test
//    public void testDeleteAppointment() {
//        // Create a temporary appointment to be deleted
//        int posterID = 5;
//        int dogID = 6;
//        Database.deleteAppointment(posterID, dogID);
//
//        // check if the appointment is successfully deleted
//        assertFalse(Database.appointmentExists(posterID, dogID));
//    }


    // Test case to check if the getUserAppointments method retrieves user appointments from the database.
   /* @Test
    public void testGetUserAppointments() {
        // Get appointments for a user
        int userID = 3;
        int expectedDogID = 2;
        TreeMap<Integer, Date> appointments = Database.getUserAppointments(userID);
        //assertTrue(appointments.containsKey(expectedDogID));
        assertEquals(false, appointments.containsKey(7));
    }*/

    
    // Test case to check if the addBookedDate method adds a new date to the database.
    @Test
    public void testAddBookedDate() {
        // Add a new booked date for a user
        int posterID = 1;
        int dogID = 2;
        Date date = Date.valueOf("2024-03-10");
        int userID = 999; //test userID

        ArrayList<Appointment> appointmentList = new ArrayList<>();
        Database.addBookedDate(posterID, dogID, date, userID);
       //Appointment appointment = new Appointment(posterID, dogID, date, userID);
        appointmentList = Database.getUserAppointments(userID);
       
       Appointment testAppointment = appointmentList.get(0);
       
       assertEquals(posterID, testAppointment.getPosterID());
        
        
//        boolean added = Database.addBookedDate(posterID, dogID, date, userID);
//
//        // check if the date is successfully added
//        assertTrue(added);
        //assertTrue(Database.addBookedDate(posterID, dogID, date, userID));
        
        //assertEquals(posterID, Poster.getUniqueId());
    }
    // Test case to check if the getDogById method retrieves a dog from the database.
    @Test
    public void testGetDogById() {
        // Get a dog by its ID
        int dogId = 37;
        Dog dog = Database.getDogById(dogId);
        
        assertEquals(dogId, dog.getId());
    }

 // Test case to check if the isDateExists method correctly identifies if a date exists in the database.
    @Test
    public void testIsDateExists() {
        // Check if a specific date exists for a dog and user
    	int posterID = 1;
        int dogID = 2;
        int userID = 999;
        Date date = Date.valueOf("2024-03-10");
       
        
        boolean dateExists = Database.addBookedDate(posterID, dogID, date, userID);
        Database.deleteAppointment(posterID, dogID);
        // check if the dateExists value is as expected
        assertFalse(dateExists);
        
    }
}