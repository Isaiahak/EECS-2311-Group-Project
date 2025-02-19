import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import backend.database.Database;
import backend.dog.Dog;
import backend.dog.trait.Age;
import backend.dog.trait.EnergyLevel;
import backend.dog.trait.Sex;
import backend.dog.trait.Size;
import backend.user.User;
import backend.poster.Poster;
import guicontrol.AppData;

class AppDataTest {
    private AppData appData;
    private User testUser;

    @BeforeEach
    void setUp() {
        appData = AppData.getInstance();
        testUser = new User("123", "123");
        appData.setUser("123", "123");
    }

    @Test
    void testSingletonInstance() {
        assertNotNull(AppData.getInstance());
        assertSame(AppData.getInstance(), appData);
    }

    @Test
    void testSetAndGetUser() {
        assertEquals("123", appData.getUser().getUsername());
    }

    @Test
    void testSetAndGetDogProfiles() {
        appData.setDogProfiles();
        assertNotNull(appData.getDogProfiles());
    }

    @Test
    void testSetAndGetPosters() {
        appData.setPosters();
        assertNotNull(appData.getPosters());
    }

    @Test
    void testInitializeAttributes() {
        appData.initializeAttributes();
        assertNotNull(appData.getAllAttributes());
    }

}
