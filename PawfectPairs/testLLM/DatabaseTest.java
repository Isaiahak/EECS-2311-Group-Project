import backend.database.Database;
import backend.user.User;
import backend.wallet.Wallet;
import backend.dog.Dog;
import backend.tag.Tag;
import backend.poster.Poster;
import backend.dog.trait.*;
import backend.calendar.Appointment;
import backend.calendar.AppointmentManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {
    private Connection connection;

    @BeforeEach
    void setUp() {
        connection = Database.connect();
    }

    @Test
    void testDatabaseConnection() {
        assertNotNull(connection, "Database connection should not be null");
    }

    @Test
    void testGetAllTags() {
        HashMap<Integer, Tag> tags = Database.getAllTags();
        assertNotNull(tags, "Tags should not be null");
    }

    @Test
    void testGetAllPosters() {
        Hashtable<Integer, Poster> posters = Database.getAllPosters();
        assertNotNull(posters, "Posters should not be null");
    }

    @Test
    void testAddAndRetrieveUser() {
        User user = new User("testUser", "testPassword");
        boolean result = Database.addUser(user.getUsername(), user.getPassword(), new HashMap<Integer,ArrayList<Attribute>>());
        assertTrue(result);
        User retrievedUser = Database.getUser(user.getUsername(), user.getPassword());
        assertNotNull(retrievedUser, "Retrieved user should not be null");
        assertEquals(user.getUsername(), retrievedUser.getUsername());
    }

    @Test
    void testAddAndRetrieveAppointments() {
        AppointmentManager appointmentManager = new AppointmentManager(1, new ArrayList<>());
        Database.setUserAppointments(appointmentManager);
        ArrayList<Appointment> appointments = Database.getUserAppointments(1);
        assertNotNull(appointments, "Appointments should not be null");
    }
}
