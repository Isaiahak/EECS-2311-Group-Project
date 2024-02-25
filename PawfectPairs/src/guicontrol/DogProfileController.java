package guicontrol;

import java.util.ArrayList;
import java.util.List;

import backend.database.Database;
import backend.dog.Dog;
import backend.poster.Poster;
import guilayout.Components;
import guilayout.PosterProfileScene;
import javafx.scene.control.*;

import javafx.scene.image.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.*;
import javafx.stage.*;

public class DogProfileController {
    private int currentIndex = 0;

    private ArrayList<Dog> dogProfiles;
    private Label primaryInfoLabel;
    private Label sizeLabel;
    private Label energyLabel;
    private ImageView petImageView;
    private Label biographyText;
    private Hyperlink posterLink;
    private Stage primaryStage; 
    private StackPane tagsPane; 
//    private Label bioLabel;
//    private Label tagsLabel;

    public DogProfileController(Label primaryInfoLabel, Label sizeLabel, Label energyLabel, ImageView petImageView, Label biographyText, Hyperlink posterLink, ArrayList<Dog> dogProfiles, StackPane tagsPane, Stage primaryStage) {
        this.primaryInfoLabel = primaryInfoLabel;
        this.sizeLabel = sizeLabel;
        this.energyLabel = energyLabel;
        this.petImageView = petImageView; 
        this.biographyText = biographyText;
        this.posterLink = posterLink;
        this.dogProfiles = dogProfiles;
        
        this.tagsPane = tagsPane;
        
        this.primaryStage = primaryStage;
    }
    public void changeProfile(int direction) {
        currentIndex = (currentIndex + direction + dogProfiles.size()) % dogProfiles.size();
        if (dogProfiles.get(currentIndex).getAdopted() == true) {
        	dogProfiles.remove(currentIndex);        	
        }
        if (dogProfiles.size() == 0) {
        	
        	
        	
        	
        }
    
    }

    public void displayCurrentPetProfile() {
    	Dog currentProfile = dogProfiles.get(currentIndex);
    	petImageView.setImage(new Image(currentProfile.getImagePath()));
    	primaryInfoLabel.setText(currentProfile.getName() +", " + currentProfile.getAge() + " years, " + currentProfile.getSex());
        sizeLabel.setText("Size: " + currentProfile.getSize());
        energyLabel.setText("Energy Level: " + currentProfile.getEnergyLevel());
        biographyText.setText(currentProfile.getBiography());
        
        posterLink.setText(Database.getPosterById(currentProfile.getPosterId()).getDisplayName());
        
        
        Poster poster = Database.getPosterById(dogProfiles.get(currentIndex).getPosterId());
		PosterProfileScene posterProfile = PosterProfileScene.getInstance();
		posterProfile.setCurrentPoster(poster);
        
        posterLink.setOnAction(event -> {
        	try {
        		posterProfile.start(primaryStage);
			} catch (Exception e) {
				e.printStackTrace();
			}
        });
        
        tagsPane.getChildren().clear();
        
        tagsPane.getChildren().add(Components.createTags(Database.getDogTags(dogProfiles.get(currentIndex).getId()))); 
        
    }
    
    public Dog getCurrentDog() {
    	return this.dogProfiles.get(currentIndex);
    }
    
    public int getDogListSize() {
    	return dogProfiles.size();
    }
    
}

