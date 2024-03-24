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
import backend.dog.DogList;
import backend.poster.Poster;
import backend.poster.PosterList;

public class PosterTest {
    private Poster poster;
    
    
    
    @Test
    public void testGetScore() {
    	poster = new Poster(100, "John", 1, "123456789", "john@example.com", 500.0);
        assertEquals(100, poster.getScore());
    }
    
    @Test
    public void testSetScore() {
    	poster = new Poster(100, "John", 1, "123456789", "john@example.com", 500.0);
        poster.setScore(200);
        assertEquals(200, poster.getScore());
    }
    
    @Test
    public void testGetDisplayName() {
    	poster = new Poster(100, "John", 1, "123456789", "john@example.com", 500.0);
        assertEquals("John", poster.getDisplayName());
    }
    
    @Test
    public void testSetDisplayName() {
    	poster = new Poster(100, "John", 1, "123456789", "john@example.com", 500.0);
        poster.setDisplayName("Jane");
        assertEquals("Jane", poster.getDisplayName());
    }
    
    @Test
    public void testGetUniqueId() {
    	poster = new Poster(100, "John", 1, "123456789", "john@example.com", 500.0);
        assertEquals(1, poster.getUniqueId());
    }
    
    @Test
    public void testSetUniqueId() {
    	poster = new Poster(100, "John", 1, "123456789", "john@example.com", 500.0);
        poster.setUniqueId(2);
        assertEquals(2, poster.getUniqueId());
    }
    
    @Test
    public void testGetPhone() {
    	poster = new Poster(100, "John", 1, "123456789", "john@example.com", 500.0);
        assertEquals("123456789", poster.getPhone());
    }
    
    @Test
    public void testGetEmail() {
    	poster = new Poster(100, "John", 1, "123456789", "john@example.com", 500.0);
        assertEquals("john@example.com", poster.getEmail());
    }
    
    @Test
    public void testDepositDonation() {
    	poster = new Poster(100, "John", 1, "123456789", "john@example.com", 500.0);
        poster.depositDonation(100.0);
        assertEquals(600.0, poster.getBalance(), 0.01);
    }
    
    @Test
    public void testGetBalance() {
    	poster = new Poster(100, "John", 1, "123456789", "john@example.com", 500.0);
        assertEquals(500.0, poster.getBalance(), 0.01);
    }
    
    @Test
    public void testSetBalance() {
    	poster = new Poster(100, "John", 1, "123456789", "john@example.com", 500.0);
        poster.setBalance(700.0);
        assertEquals(700.0, poster.getBalance(), 0.01);
    }
    
    @Test
    public void testToString() {
    	poster = new Poster(100, "John", 1, "123456789", "john@example.com", 500.0);
        String expected = "Poster [score=100, displayName=John, uniqueId=1]";
        assertEquals(expected, poster.toString());
    }
    
    @Test
    public void testCompareTo() {
    	poster = new Poster(100, "John", 1, "123456789", "john@example.com", 500.0);
        Poster poster2 = new Poster(200, "Jane", 2, "987654321", "jane@example.com", 300.0);
        
        assertTrue(poster.compareTo(poster2) < 0); // poster's uniqueId is less than poster2's uniqueId
        
        poster2.setUniqueId(1); // Setting poster2's uniqueId to the same as poster
        
        poster2.setScore(50); // Lower score than poster
        assertTrue(poster.compareTo(poster2) > 0); // poster's score is greater than poster2's score
    }
}
