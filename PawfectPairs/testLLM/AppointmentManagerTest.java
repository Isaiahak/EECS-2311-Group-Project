package backend.calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentManagerTest {
    private AppointmentManager appointmentManager;
    private Appointment appointment1;
    private Appointment appointment2;

    @BeforeEach
    void setUp() {
        appointmentManager = new AppointmentManager(1, new ArrayList<>());
        appointment1 = new Appointment(1, 2, Date.valueOf(LocalDate.of(2024, 3, 10)), 3);
        appointment2 = new Appointment(4, 5, Date.valueOf(LocalDate.of(2024, 4, 15)), 6);
    }

    @Test
    void testGetAndSetUserID() {
        assertEquals(1, appointmentManager.getUserID());
        appointmentManager.setUserID(2);
        assertEquals(2, appointmentManager.getUserID());
    }

    @Test
    void testGetAndSetUserAppointments() {
        ArrayList<Appointment> appointments = new ArrayList<>();
        appointments.add(appointment1);
        appointmentManager.setUserAppointments(appointments);
        assertEquals(appointments, appointmentManager.getUserAppointments());
    }

    @Test
    void testAddAppointment() {
        appointmentManager.addAppointment(appointment1);
        assertTrue(appointmentManager.getUserAppointments().contains(appointment1));
    }

    @Test
    void testRemoveAppointment() {
        appointmentManager.addAppointment(appointment1);
        appointmentManager.removeAppointment(appointment1);
        assertFalse(appointmentManager.getUserAppointments().contains(appointment1));
    }

    @Test
    void testAppointmentExists() {
        appointmentManager.addAppointment(appointment1);
        assertTrue(appointmentManager.appointmentExists(appointment1));
        assertFalse(appointmentManager.appointmentExists(appointment2));
    }

}
