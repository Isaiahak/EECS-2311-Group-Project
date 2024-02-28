package guicontrol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.PriorityQueue;

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

    private PriorityQueue<Dog> dogProfiles;
    private Label primaryInfoLabel;
    private Label sizeLabel;
    private Label energyLabel;
    private ImageView petImageView;
    private Label biographyText;
    private Hyperlink posterLink;
    private Stage primaryStage; 
    private StackPane tagsPane; 
    private Hashtable<Integer,Poster> posterProfiles;
//    private Label bioLabel;
//    private Label tagsLabel;

    public DogProfileController(Label primaryInfoLabel, Label sizeLabel, Label energyLabel, ImageView petImageView, Label biographyText, Hyperlink posterLink, PriorityQueue<Dog> dogProfiles, StackPane tagsPane, Stage primaryStage,Hashtable<Integer,Poster> posterProfiles) {
        this.primaryInfoLabel = primaryInfoLabel;
        this.sizeLabel = sizeLabel;
        this.energyLabel = energyLabel;
        this.petImageView = petImageView; 
        this.biographyText = biographyText;
        this.posterLink = posterLink;
        this.dogProfiles = dogProfiles;
        this.posterProfiles = posterProfiles;
        this.tagsPane = tagsPane;
        
        this.primaryStage = primaryStage;
    }
    
    public void changeProfile() {
    	dogProfiles.remove();
    }

    public void displayCurrentPetProfile() {
    	Dog currentProfile = dogProfiles.peek();
    	petImageView.setImage(new Image(currentProfile.getImagePath()));
    	primaryInfoLabel.setText(currentProfile.getName() +", " + currentProfile.getAge() + " years, " + currentProfile.getSex());
        sizeLabel.setText("Size: " + currentProfile.getSize());
        energyLabel.setText("Energy Level: " + currentProfile.getEnergyLevel());
        biographyText.setText(currentProfile.getBiography());
        
        posterLink.setText(posterProfiles.get(currentProfile.getPosterId()).getDisplayName());
		PosterProfileScene posterProfile = PosterProfileScene.getInstance();
		posterProfile.setCurrentPoster(posterProfiles.get(currentProfile.getPosterId()));
        
        posterLink.setOnAction(event -> {
        	try {
        		posterProfile.start(primaryStage);
			} catch (Exception e) {
				e.printStackTrace();
			}
        });
        
        tagsPane.getChildren().clear();
        
        tagsPane.getChildren().add(Components.createTags(currentProfile.getTags())); 
        
    }
    
    
    
    public int getDogListSize() {
    	return dogProfiles.size();
    }
    
}

