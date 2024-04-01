package test.backend.poster;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import backend.dog.Dog;
import backend.poster.Poster;
class PosterTest {

    @Test
    void testConstructorAndGetters() {
        Poster poster = new Poster(5, "John", 1,"123-456","poster@email.com", 123.5,0);
        assertEquals(5, poster.getScore());
        assertEquals("John", poster.getDisplayName());
        assertEquals(1, poster.getUniqueId());
    }

    @Test
    void testSetters() {
        Poster poster = new Poster(5, "John", 1,"123-456","poster@email.com", 123.5,0);
        poster.setScore(10);
        poster.setDisplayName("Doe");
        poster.setUniqueId(2);

        assertEquals(10, poster.getScore());
        assertEquals("Doe", poster.getDisplayName());
        assertEquals(2, poster.getUniqueId());
    }

    @Test
    void testToString() {
    	Poster poster = new Poster(5, "John", 1,"123-456","poster@email.com", 123.5,0);
        String expected = "Poster [score=5.0, displayName=John, uniqueId=1]";
        assertEquals(expected, poster.toString());
    }

    @Test
    void testCompareTo() {
        Poster poster1 = new Poster(5, "John", 1,"123-456","poster@email.com", 123.5,0);
        Poster poster2 = new Poster(10, "Doe", 2,"123-456","poster@email.com", 123.5,0);

        assertTrue(poster1.compareTo(poster2) < 0); // poster1 should be less than poster2

        // Make two posters with the same uniqueId and displayName
        Poster poster3 = new Poster(10, "Doe", 2,"123-456","poster@email.com", 123.5,0);
        assertEquals(0, poster2.compareTo(poster3)); // poster2 and poster3 should be equal

        // Make two posters with the same uniqueId and different displayName
        Poster poster4 = new Poster(8, "Smith", 2,"123-456","poster@email.com", 123.5,0);
        assertTrue(poster3.compareTo(poster4) < 0); // poster3 should be less than poster4
    }
}

