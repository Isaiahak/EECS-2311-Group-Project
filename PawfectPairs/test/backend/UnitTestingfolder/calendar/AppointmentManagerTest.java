package test.backend.calendar;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.ArrayList;

import backend.calendar.Appointment;
import backend.calendar.AppointmentManager;

class AppointmentManagerTest {

	// check if getUserID() returns the correct userID
	@Test
	public void testGetUserID() {
        ArrayList<Appointment> userAppointments = new ArrayList<>();
        AppointmentManager appointmentManager = new AppointmentManager(1, userAppointments);
        assertEquals(1, appointmentManager.getUserID());
    }

	// check if setUserID() sets the userID correctly
    @Test
    public void testSetUserID() {
        ArrayList<Appointment> userAppointments = new ArrayList<>();
        AppointmentManager appointmentManager = new AppointmentManager(1, userAppointments);
        appointmentManager.setUserID(2);
        assertEquals(2, appointmentManager.getUserID());
    }

    // check if getUserAppointments() returns the correct list of appointments
    @Test
    public void testGetUserAppointments() {
        ArrayList<Appointment> userAppointments = new ArrayList<>();
        AppointmentManager appointmentManager = new AppointmentManager(1, userAppointments);
        assertEquals(userAppointments, appointmentManager.getUserAppointments());
    }

    // check if setUserAppointments() sets the list of appointments correctly
    @Test
    public void testSetUserAppointments() {
        ArrayList<Appointment> userAppointments = new ArrayList<>();
        AppointmentManager appointmentManager = new AppointmentManager(1, userAppointments);

        ArrayList<Appointment> newAppointments = new ArrayList<>();
        newAppointments.add(new Appointment(1, 2, Date.valueOf("2024-03-10"), 3));

        appointmentManager.setUserAppointments(newAppointments);
        assertEquals(newAppointments, appointmentManager.getUserAppointments());
    }

    // check if addAppointment() correctly adds an appointment to the list
    @Test
    public void testAddAppointment() {
        ArrayList<Appointment> userAppointments = new ArrayList<>();
        AppointmentManager appointmentManager = new AppointmentManager(1, userAppointments);

        Appointment newAppointment = new Appointment(1, 2, Date.valueOf("2024-03-10"), 3);
        appointmentManager.addAppointment(newAppointment);

        assertTrue(appointmentManager.getUserAppointments().contains(newAppointment));
    }

    // check if removeAppointment() correctly removes an appointment from the list
    @Test
    public void testRemoveAppointment() {
        ArrayList<Appointment> userAppointments = new ArrayList<>();
        AppointmentManager appointmentManager = new AppointmentManager(1, userAppointments);

        Appointment appointmentToRemove = new Appointment(1, 2, Date.valueOf("2024-03-10"), 3);
        appointmentManager.addAppointment(appointmentToRemove);
        appointmentManager.removeAppointment(appointmentToRemove);

        assertFalse(appointmentManager.getUserAppointments().contains(appointmentToRemove));
    }

    // check if appointmentExists() correctly identifies if an appointment exists
    @Test
    public void testAppointmentExists() {
        ArrayList<Appointment> userAppointments = new ArrayList<>();
        AppointmentManager appointmentManager = new AppointmentManager(1, userAppointments);

        Appointment existingAppointment = new Appointment(1, 2, Date.valueOf("2024-03-10"), 3);
        appointmentManager.addAppointment(existingAppointment);

        Appointment newAppointment = new Appointment(1, 2, Date.valueOf("2024-03-10"), 3);
        assertTrue(appointmentManager.appointmentExists(newAppointment));

        Appointment differentAppointment = new Appointment(4, 5, Date.valueOf("2024-03-10"), 6);
        assertFalse(appointmentManager.appointmentExists(differentAppointment));
    }

}
