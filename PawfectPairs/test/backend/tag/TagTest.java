package test.backend.tag;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import backend.tag.Tag;

class TagTest {

    @Test
    void test1() {
        Tag tag1 = new Tag("Shy", 0);
        int expected = 0;
        assertEquals(expected, tag1.getWeight());
    }

    @Test
    void test2() {
        Tag tag1 = new Tag("Shy",0);
        String expected = "Shy";
        assertEquals(expected, tag1.getTagName());
    }

    @Test
    void test3() {
        Tag tag1 = new Tag("Angry",0);
        tag1.setTagName("Shy");
        String expected = "Shy";
        assertEquals(expected, tag1.getTagName());
    }

    @Test
    void test4() {
        Tag tag1 = new Tag("Shy",0);
        tag1.setWeight(3);
        int expected = 3;
        assertEquals(expected, tag1.getWeight());
    }
}

