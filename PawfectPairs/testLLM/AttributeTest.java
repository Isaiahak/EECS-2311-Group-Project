import backend.dog.trait.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AttributeTest {
    private Attribute attr1, attr2;

    @BeforeEach
    void setUp() {
        attr1 = new Age(2); // Adult
        attr2 = new EnergyLevel(3); // Energetic
    }

    @Test
    void testGetWeight() {
        assertEquals(2, attr1.getWeight());
        assertEquals(3, attr2.getWeight());
    }

    @Test
    void testSetWeight() {
        attr1.setWeight(1);
        assertEquals(1, attr1.getWeight());
        assertEquals("Adolescent: 1 to 2", attr1.getName());
    }

    @Test
    void testGetName() {
        assertEquals("Adult: 2 to 6", attr1.getName());
        assertEquals("Energetic", attr2.getName());
    }

    @Test
    void testSetName() {
        attr1.setName("Custom Name");
        assertEquals("Custom Name", attr1.getName());
    }

    @Test
    void testCompareTo() {
        Attribute lesser = new Age(1);
        Attribute greater = new Age(3);
        assertTrue(attr1.compareTo(lesser) > 0);
        assertTrue(attr1.compareTo(greater) < 0);
        assertEquals(0, attr1.compareTo(new Age(2)));
    }

    @Test
    void testEquals() {
        assertEquals(new Age(2), attr1);
        assertNotEquals(new Age(1), attr1);
        assertNotEquals(attr1, attr2);
    }
}
