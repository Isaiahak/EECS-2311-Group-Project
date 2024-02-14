package DogTinder;

public class MainTester {

	public static void main(String[] args) {
		
		//Create Poster 
		Poster post1 = new Poster (0, "John", 1); //we should use Math.rand for the IDs 
		Poster post2 = new Poster (0, "Anna", 2);
		// Create three dogs
        Dog dog1 = new Dog("Max", 1, 3, new EnergyLevel(0), new Size(2), "M", post1); //we should use Math.rand for the IDs
        Dog dog2 = new Dog("Buddy", 2, 4, new EnergyLevel(1), new Size(2), "M", post1);
        Dog dog3 = new Dog("Bella", 3, 2, new EnergyLevel(1), new Size(1), "F", post2);
        
        DogList list = new DogList();

        // Create tags for each dog
        Tag tag1 = new Tag(10, "Friendly");
        Tag tag2 = new Tag(8, "Active");
        Tag tag3 = new Tag(6, "Playful");

        // Add tags to each dog
        dog1.getTags().add(tag1);
        dog1.getTags().add(tag2);
        dog2.getTags().add(tag2);
        dog3.getTags().add(tag3);

        //System.out.println(dog1);
        //System.out.println(dog2);
        //System.out.println(dog3);
        
        list.addDog(dog1.getName(), dog1);
        list.addDog(dog2.getName(), dog2);
        list.addDog(dog3.getName(), dog3);
        
        System.out.println(list.getDog("Max"));
        System.out.println(list);

	}

}
