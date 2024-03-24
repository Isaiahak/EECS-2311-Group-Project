package test.backend.tag;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import backend.tag.Tag;

public class TagTest {
    private Tag tag;
    
    
    
    @Test
    public void testGetTagName() {
    	tag = new Tag("Friendly", 1);
        assertEquals("Friendly", tag.getTagName());
    }
    
    @Test
    public void testSetTagName() {
    	tag = new Tag("Friendly", 1);
        tag.setTagName("Active");
        assertEquals("Active", tag.getTagName());
    }
    
    @Test
    public void testGetTagId() {
    	tag = new Tag("Friendly", 1);
        assertEquals(1, tag.getTagId());
    }
    
    @Test
    public void testSetTagId() {
    	tag = new Tag("Friendly", 1);
        tag.setTagId(2);
        assertEquals(2, tag.getTagId());
    }
    
    @Test
    public void testGetWeight() {
    	tag = new Tag("Friendly", 1);
        assertEquals(0, tag.getWeight());
    }
    
    @Test
    public void testSetWeight() {
    	tag = new Tag("Friendly", 1);
        tag.setWeight(3);
        assertEquals(3, tag.getWeight());
    }
    
    @Test
    public void testEquals() {
    	tag = new Tag("Friendly", 1);
        Tag sameTag = new Tag("Friendly", 1);
        Tag differentTag = new Tag("Energetic", 2);
        
        assertTrue(tag.equals(sameTag)); // Same tag name and ID
        assertFalse(tag.equals(differentTag)); // Different tag name and ID
    }
}
