import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import backend.database.Database;
import backend.dog.Dog;
import backend.poster.Poster;

import java.util.ArrayList;

import backend.tag.Tag;

class Integration {
    String url = "jdbc:postgresql://localhost:5432/pawdb";
    String user1 = "postgres";
    String password = "12345";
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    //test to check access to db poster
    @Test
    void testGetPosterById() {
        // Test getting a poster by ID
        Poster poster = Database.getPosterById(1);

        assertNotNull(poster);
        assertEquals("doglover123", poster.getDisplayName()); // Replace with expected display name
        assertEquals(5, poster.getScore()); // Replace with expected score
        assertEquals(1, poster.getUniqueId()); // Replace with expected poster ID
    }


    @Test
    void testGetAllDogs() {
        // Test getting all dogs
        ArrayList<Dog> dogs = Database.getAllDogs();


        assertNotNull(dogs);
        assertFalse(dogs.isEmpty());
        for(int i=0; i<dogs.size();i++) {

            if(dogs.get(i).getName().equals("Charles")) //verifies that the db contains a certain dog
                assertEquals("Charles",dogs.get(i).getName());
        }

    }
    
    



}