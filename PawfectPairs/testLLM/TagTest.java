import backend.tag.Tag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TagTest {
    private Tag tag1;
    private Tag tag2;
    private Tag tag3;

    @BeforeEach
    void setUp() {
        tag1 = new Tag("Friendly", 1);
        tag2 = new Tag("Friendly", 2);
        tag3 = new Tag("Aggressive", 3);
    }

    @Test
    void testGetTagName() {
        assertEquals("Friendly", tag1.getTagName());
    }

    @Test
    void testSetTagName() {
        tag1.setTagName("Playful");
        assertEquals("Playful", tag1.getTagName());
    }

    @Test
    void testGetTagId() {
        assertEquals(1, tag1.getTagId());
    }

    @Test
    void testSetTagId() {
        tag1.setTagId(5);
        assertEquals(5, tag1.getTagId());
    }

    @Test
    void testEquals_SameTagName() {
        assertEquals(tag1, tag2);
    }

    @Test
    void testEquals_DifferentTagName() {
        assertNotEquals(tag1, tag3);
    }

}
