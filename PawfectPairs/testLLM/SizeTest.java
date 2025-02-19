import backend.dog.trait.Size;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SizeTest {
    private Size tiny, small, medium, large, humongous;

    @BeforeEach
    void setUp() {
        tiny = new Size(0); // Tiny
        small = new Size(1); // Small
        medium = new Size(2); // Medium
        large = new Size(3); // Large
        humongous = new Size(4); // Humongous
    }

    @Test
    void testSizeNames() {
        assertEquals("Tiny", tiny.getName());
        assertEquals("Small", small.getName());
        assertEquals("Medium", medium.getName());
        assertEquals("Large", large.getName());
        assertEquals("Humongous", humongous.getName());
    }

    @Test
    void testGetWeight() {
        assertEquals(0, tiny.getWeight());
        assertEquals(1, small.getWeight());
        assertEquals(2, medium.getWeight());
        assertEquals(3, large.getWeight());
        assertEquals(4, humongous.getWeight());
    }

    @Test
    void testGetType() {
        assertEquals(3, tiny.getType());
    }

    @Test
    void testGetNames() {
        String[] expectedNames = {"Tiny", "Small", "Medium", "Large", "Humongous"};
        assertArrayEquals(expectedNames, tiny.getNames());
    }

    @Test
    void testToString() {
        assertEquals("Tiny", tiny.toString());
        assertEquals("Small", small.toString());
        assertEquals("Medium", medium.toString());
        assertEquals("Large", large.toString());
        assertEquals("Humongous", humongous.toString());
    }

    @Test
    void testEquals() {
        Size anotherSmall = new Size(1);
        Size anotherLarge = new Size(3);
        assertEquals(small, anotherSmall);
        assertEquals(large, anotherLarge);
        assertNotEquals(small, large);
    }
}
