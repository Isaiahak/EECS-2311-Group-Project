package guicontrol;

import java.util.ArrayList;
import java.util.List;

import backend.dog.Dog;
import backend.poster.Poster;
import guilayout.Components;
import javafx.scene.control.*;

import javafx.scene.image.*;
import javafx.scene.text.*;
import javafx.stage.*;

public class DogProfileController {
    private int currentIndex = 0;
    private ArrayList<Dog> dogProfiles = new ArrayList<>(List.of(
    


    		new Dog("Charles", 1, 2, 1, 2, 1, new Poster(5, "doglover123", 12), false, "/guilayout/dog.png", "Charles is involed in some shady stuff. Don't ask questions you don't want to know the answers to."), 
    		new Dog("Sandy", 2, 3, 1, 2, 0, new Poster(5, "doglover123", 12), false,"/guilayout/dog1.png", "Sandy looks like she knows something you don't... but that's what makes her so loveable!"),
    		new Dog("Chuckles", 3, 3, 0, 0, 1, new Poster(5, "doglover123", 12), false,"/guilayout/dog4.png", "Lazy and old Chuckles is the perfect lap dog for anyone who doesn't like to move.")



));
    
    private Label primaryInfoLabel;
    private Label sizeLabel;
    private Label energyLabel;
    private ImageView petImageView;
    private Label biographyText;
    private Hyperlink posterLink;
    private Stage primaryStage; 
//    private Label bioLabel;
//    private Label tagsLabel;

    public DogProfileController(Label primaryInfoLabel, Label sizeLabel, Label energyLabel, ImageView petImageView, Label biographyText, Hyperlink posterLink) {
        this.primaryInfoLabel = primaryInfoLabel;
        this.sizeLabel = sizeLabel;
        this.energyLabel = energyLabel;
        this.petImageView = petImageView; 
        this.biographyText = biographyText;
        this.posterLink = posterLink;
        
//        this.primaryStage = primaryStage;
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
    	petImageView.setImage(new Image(currentProfile.getImagePath()));
    	primaryInfoLabel.setText(currentProfile.getName() +", " + currentProfile.getAge() + " years, " + currentProfile.getSex());
        sizeLabel.setText("Size: " + currentProfile.getSize());
        energyLabel.setText("Energy Level: " + currentProfile.getEnergyLevel());
        biographyText.setText(currentProfile.getBiography());
        
        posterLink.setText(currentProfile.getPoster().getDisplayName());
//        posterLink.setOnAction(event -> {
//        	userScene.start(stage);
//        }); // change this when database is implemented
//        
        // change button for posterProfile
        
//        tagsLabel.setText("Tags: " + currentProfile.getTags());
        
    }
    
    public Dog getCurrentDog() {
    	return this.dogProfiles.get(currentIndex);
    }
    
    
}
