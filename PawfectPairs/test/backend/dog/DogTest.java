package dogs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

//Note: currently containing some errors because of the poster class
//Needs double check

class DogTest {

	@Test
    public void testParameterlessConstructor() {
        Dog dog = new Dog(0, 0);

        assertNotNull(dog);
        assertEquals(0, dog.getId());
        assertEquals(0, dog.getAge());
        assertEquals("Small", dog.getSize().toString());
        assertEquals("Lazy", dog.getEnergyLevel().toString());
        assertNull(dog.getName());
        assertNull(dog.getSex());
        assertNull(dog.getPoster()); 
        assertTrue(dog.getTags().isEmpty());
    }

    @Test
    public void testFullConstructor() {
        EnergyLevel energyLevel = new EnergyLevel(1);
        Size size = new Size(2);
        Poster poster = new Poster(); 
        ArrayList<Tag> tags = new ArrayList<>();
        tags.add(new Tag("Tag1", 3));

        Dog dog = new Dog("Buddy", 1, 3, energyLevel, size, "M", poster);
        dog.setTags(tags);

        assertNotNull(dog);
        assertEquals("Buddy", dog.getName());
        assertEquals(1, dog.getId());
        assertEquals(3, dog.getAge());
        assertEquals("Moderate", dog.getEnergyLevel().toString());
        assertEquals("Large", dog.getSize().toString());
        assertEquals("M", dog.getSex());
        assertEquals(poster, dog.getPoster());
        assertEquals(tags, dog.getTags());
    }

    @Test
    public void testToString() {
        Dog dog = new Dog("Buddy", 1, 3, new EnergyLevel(1), new Size(2), "M", null);
        ArrayList<Tag> tags = new ArrayList<>();
        tags.add(new Tag("Tag1", 3));
        tags.add(new Tag("Tag2", 5));
        dog.setTags(tags);

        String expected = "Dog: Buddy\n" +
                "ID: 1\n" +
                "Age: 3\n" +
                "Energy Level: Moderate\n" +
                "Size: Large\n" +
                "Sex: M\n" +
                "Tags:\n" +
                "- Tag1: 3\n" +
                "- Tag2: 5\n";

        assertEquals(expected, dog.toString());
    }

}
