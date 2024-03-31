package test.backend.tag;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import backend.tag.Tag;

class TagTest {

    @Test
    void tagWeightTest() {
        Tag tag1 = new Tag("Shy", 0);
        int expected = 0;
        assertEquals(expected, tag1.getTagId());
    }

    @Test
    void tagNameTest() {
        Tag tag1 = new Tag("Shy",0);
        String expected = "Shy";
        assertEquals(expected, tag1.getTagName());
    }

    @Test
    void tagNameTest2() {
        Tag tag1 = new Tag("Angry",0);
        tag1.setTagName("Shy");
        String expected = "Shy";
        assertEquals(expected, tag1.getTagName());
    }

    @Test
    void tagNameWeight2() {
        Tag tag1 = new Tag("Shy",0);
        tag1.setTagId(3);
        int expected = 3;
        assertEquals(expected, tag1.getTagId());
    }
}

