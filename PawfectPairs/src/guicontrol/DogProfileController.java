package guicontrol;

import java.util.ArrayList;
import java.util.List;

import backend.dog.Dog;
import backend.poster.Poster;
import javafx.scene.control.Label;

public class DogProfileController {
    private int currentIndex = 0;
    private ArrayList<Dog> dogProfiles = new ArrayList<>(List.of(
    
    		//public Dog(String name, int id, int age, int energyLevel, int size, String sex, Poster poster, boolean adopted) {
    		//public  Poster(int score, String displayName, int uniqueId) {
    		new Dog("Charles", 1, 2, 1, 2, "F", new Poster(5, "doglover123", 12), false), 
    		new Dog("Sandy", 2, 3, 1, 2, "M", new Poster(5, "doglover123", 12), false),
    		new Dog("Paul Anka", 3, 2, 0, 0, "M", new Poster(5, "doglover123", 12), false),
    		new Dog("Chuckles", 4, 5, 1, 2, "F", new Poster(5, "doglover123", 12), false)	
));
    
    private Label nameLabel;
    private Label ageLabel;
    private Label sizeLabel;
    private Label energyLabel;
//    private Label bioLabel;
//    private Label tagsLabel;

    public DogProfileController(Label nameLabel, Label ageLabel, Label sizeLabel, Label energyLabel) {
        this.nameLabel = nameLabel;
        this.ageLabel = ageLabel;
        this.sizeLabel = sizeLabel;
        this.energyLabel = energyLabel;
//        this.bioLabel = bioLabel;
//        this.tagsLabel = tagsLabel;
    }
    public void changeProfile(int direction) {
        currentIndex = (currentIndex + direction + dogProfiles.size()) % dogProfiles.size();
        if (dogProfiles.get(currentIndex).getAdopted() == true) {
        	dogProfiles.remove(currentIndex);        	
        }
        if (dogProfiles.size() == 0) {
        	// figure out what to do when there are no dog profiles
        }
    
    }

    public void displayCurrentPetProfile() {
    	Dog currentProfile = dogProfiles.get(currentIndex);
//        petImageView.setImage(new Image(getClass().getResourceAsStream(currentProfile.getPhotoPath())));
        nameLabel.setText("Name: " + currentProfile.getName());
        ageLabel.setText("Age: " + currentProfile.getAge() + " years");
        sizeLabel.setText("Size: " + currentProfile.getSize());
        energyLabel.setText("Energy Level: " + currentProfile.getEnergyLevel());
//        bioLabel.setText("Biography: " + currentProfile.getBiography());
//        tagsLabel.setText("Tags: " + currentProfile.getTags());
        
    }
    
    public Dog getCurrentDog() {
    	return this.dogProfiles.get(currentIndex);
    }
    
    
}
