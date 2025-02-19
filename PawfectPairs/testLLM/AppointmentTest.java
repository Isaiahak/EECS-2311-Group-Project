import backend.calendar.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentTest {
    private Appointment appointment1;
    private Appointment appointment2;
    private Appointment appointment3;

    @BeforeEach
    void setUp() {
        appointment1 = new Appointment(1, 2, Date.valueOf(LocalDate.of(2024, 3, 10)), 3);
        appointment2 = new Appointment(1, 2, Date.valueOf(LocalDate.of(2024, 3, 10)), 3);
        appointment3 = new Appointment(4, 5, Date.valueOf(LocalDate.of(2024, 4, 15)), 6);
    }

    @Test
    void testGetAndSetPosterID() {
        assertEquals(1, appointment1.getPosterID());
        appointment1.setPosterID(7);
        assertEquals(7, appointment1.getPosterID());
    }

    @Test
    void testGetAndSetDogID() {
        assertEquals(2, appointment1.getDogID());
        appointment1.setDogID(8);
        assertEquals(8, appointment1.getDogID());
    }

    @Test
    void testGetAndSetDate() {
        assertEquals(Date.valueOf(LocalDate.of(2024, 3, 10)), appointment1.getDate());
        appointment1.setDate(Date.valueOf(LocalDate.of(2024, 5, 20)));
        assertEquals(Date.valueOf(LocalDate.of(2024, 5, 20)), appointment1.getDate());
    }

    @Test
    void testGetAndSetUserID() {
        assertEquals(3, appointment1.getUserID());
        appointment1.setUserID(9);
        assertEquals(9, appointment1.getUserID());
    }

    @Test
    void testEquals_SameAppointments() {
        assertEquals(appointment1, appointment2);
    }

    @Test
    void testEquals_DifferentAppointments() {
        assertNotEquals(appointment1, appointment3);
    }


}
