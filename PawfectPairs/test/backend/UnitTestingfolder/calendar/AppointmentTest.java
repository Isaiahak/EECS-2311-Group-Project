package test.backend.calendar;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import backend.calendar.Appointment;

import java.sql.Date;

class AppointmentTest {

	// check if getPosterID() returns the correct posterID
	@Test
    public void testGetPosterID() {
        Appointment appointment = new Appointment(1, 2, Date.valueOf("2024-03-10"), 3);
        assertEquals(1, appointment.getPosterID());
    }

	// check if setPosterID() sets the posterID correctly
    @Test
    public void testSetPosterID() {
        Appointment appointment = new Appointment(1, 2, Date.valueOf("2024-03-10"), 3);
        appointment.setPosterID(4);
        assertEquals(4, appointment.getPosterID());
    }

    // check if getDogID() returns the correct dogID
    @Test
    public void testGetDogID() {
        Appointment appointment = new Appointment(1, 2, Date.valueOf("2024-03-10"), 3);
        assertEquals(2, appointment.getDogID());
    }

    // check if setDogID() sets the dogID correctly
    @Test
    public void testSetDogID() {
        Appointment appointment = new Appointment(1, 2, Date.valueOf("2024-03-10"), 3);
        appointment.setDogID(5);
        assertEquals(5, appointment.getDogID());
    }

    // check if getDate() returns the correct date
    @Test
    public void testGetDate() {
        Date testDate = Date.valueOf("2024-03-10");
        Appointment appointment = new Appointment(1, 2, testDate, 3);
        assertEquals(testDate, appointment.getDate());
    }

    // check if setDate() sets the date correctly
    @Test
    public void testSetDate() {
        Date testDate = Date.valueOf("2024-03-10");
        Appointment appointment = new Appointment(1, 2, Date.valueOf("2022-01-01"), 3);
        appointment.setDate(testDate);
        assertEquals(testDate, appointment.getDate());
    }

    // check if getUserID() returns the correct userID
    @Test
    public void testGetUserID() {
        Appointment appointment = new Appointment(1, 2, Date.valueOf("2024-03-10"), 3);
        assertEquals(3, appointment.getUserID());
    }

    // check if setUserID() sets the userID correctly
    @Test
    public void testSetUserID() {
        Appointment appointment = new Appointment(1, 2, Date.valueOf("2024-03-10"), 3);
        appointment.setUserID(6);
        assertEquals(6, appointment.getUserID());
    }
    
    // check if equals() method returns true when comparing two appointments with the same dogID
   /* @Test
    public void testEquals_SameDogID() {
        Appointment appointment1 = new Appointment(1, 2, Date.valueOf("2024-03-10"), 3);
        Appointment appointment2 = new Appointment(4, 2, Date.valueOf("2024-03-10"), 5);
        assertTrue(appointment1.equals(appointment2));
    }*/
    
 // check if equals() method returns false when comparing two appointments with different dogIDs
    @Test
    public void testEquals_DifferentDogID() {
        Appointment appointment1 = new Appointment(1, 2, Date.valueOf("2024-03-10"), 3);
        Appointment appointment2 = new Appointment(4, 5, Date.valueOf("2024-03-10"), 6);
        assertFalse(appointment1.equals(appointment2));
    }

 
}