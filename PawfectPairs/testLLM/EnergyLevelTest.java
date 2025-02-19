import backend.dog.trait.EnergyLevel;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EnergyLevelTest {
    private EnergyLevel energy0, energy1, energy2, energy3, energy4;

    @BeforeEach
    void setUp() {
        energy0 = new EnergyLevel(0); // Vegetable
        energy1 = new EnergyLevel(1); // Lazy
        energy2 = new EnergyLevel(2); // Moderate
        energy3 = new EnergyLevel(3); // Energetic
        energy4 = new EnergyLevel(4); // Hyper
    }

    @Test
    void testEnergyLevelNames() {
        assertEquals("Vegetable", energy0.getName());
        assertEquals("Lazy", energy1.getName());
        assertEquals("Moderate", energy2.getName());
        assertEquals("Energetic", energy3.getName());
        assertEquals("Hyper", energy4.getName());
    }

    @Test
    void testGetWeight() {
        assertEquals(0, energy0.getWeight());
        assertEquals(1, energy1.getWeight());
        assertEquals(2, energy2.getWeight());
        assertEquals(3, energy3.getWeight());
        assertEquals(4, energy4.getWeight());
    }

    @Test
    void testGetType() {
        assertEquals(2, energy0.getType());
    }

    @Test
    void testGetNames() {
        String[] expectedNames = {"Vegetable", "Lazy", "Moderate", "Energetic", "Hyper"};
        assertArrayEquals(expectedNames, energy0.getNames());
    }

    @Test
    void testToString() {
        assertEquals("Vegetable", energy0.toString());
        assertEquals("Lazy", energy1.toString());
        assertEquals("Moderate", energy2.toString());
        assertEquals("Energetic", energy3.toString());
        assertEquals("Hyper", energy4.toString());
    }

    @Test
    void testEquals() {
        EnergyLevel anotherEnergy = new EnergyLevel(2);
        assertEquals(energy2, anotherEnergy);
        assertNotEquals(energy0, energy1);
    }
}
