package test;

import dogs.*;
import poster.*;
import tags.*;
import user.*;
import matchmaking.*;

public class Main {

	public static void main(String[] args) {
		
		//Create Poster 
		Poster post1 = new Poster (0, "John", 1); //we should use Math.rand for the IDs 
		Poster post2 = new Poster (0, "Anna", 2);
		
		// Create three dogs
        Dog dog1 = new Dog("Max", 1, 3, 0, 2, "M", post1, false); //we should use Math.rand for the IDs
        Dog dog2 = new Dog("Buddy", 2, 4, 1,2, "M", post1,false);
        Dog dog3 = new Dog("Bella", 3, 2, 1, 1, "F", post2,false);
        
        DogList list = new DogList();
        
        PosterList plist = new PosterList();

        // Create tags for each dog
        Tag tag1 = new Tag(10, "Friendly");
        Tag tag2 = new Tag(8, "Active");
        Tag tag3 = new Tag(6, "Playful");

        // Add tags to each dog
        dog1.getTags().add(tag1);
        dog1.getTags().add(tag2);
        dog2.getTags().add(tag2);
        dog3.getTags().add(tag3);

        System.out.println("////////////////////////////DISPLAY ALL DOGS//////////////////////////////////////////////////");
        System.out.println(dog1);
        System.out.println(dog2);
        System.out.println(dog3);
        System.out.println("////////////////////////////DISPLAY DOG LIST//////////////////////////////////////////////////");
        list.addDog(dog1.getName(), dog1);
        list.addDog(dog2.getName(), dog2);
        list.addDog(dog3.getName(), dog3);
        
        //System.out.println(list.getDog("Max"));
        System.out.println(list);
        System.out.println("////////////////////////////DISPLAY POSTERS//////////////////////////////////////////////////");
        System.out.println(post1);
        System.out.println(post2);
        System.out.println("////////////////////////////DISPLAY POSTER LIST//////////////////////////////////////////////////");
        plist.addPoster(post1.getDisplayName(), post1);
        plist.addPoster(post2.getDisplayName(), post2);
        
        System.out.println(plist);
        
        System.out.println("////////////////////////////DISPLAY USER//////////////////////////////////////////////////");
        User user1 = new User ("Garfield", "lovedogs@gmail.com", "1234");
        System.out.println(user1);
        System.out.println("////////////////////////////DISPLAY USER LIST//////////////////////////////////////////////////");
        //Ill work on it later
        System.out.println("ill work on it later");
        System.out.println("////////////////////////////DISPLAY USER PREFERRED DOGS//////////////////////////////////////////////////");
        Tag user1_Tags = new Tag(10, "Active");
        user1.getTags().add(user1_Tags);
        MatchMaking matchAlg = new MatchMaking();
        Dog preferredDog = matchAlg.match(list, user1);
        System.out.println(preferredDog); //Currently we print only 1 prefferedDog based on first tag
        System.out.println("////////////////////////////ADOPT DOG//////////////////////////////////////////////////");
        matchAlg.adopt(user1, true, preferredDog);
        System.out.println(preferredDog); 
	}

}
