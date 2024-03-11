package guilayout;
import backend.dog.trait.Age;
import backend.dog.trait.Attribute;
import backend.dog.trait.EnergyLevel;
import backend.dog.trait.Sex;
import backend.dog.trait.Size;
import backend.user.*;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.PriorityQueue;
import java.util.TreeSet;

import backend.database.Database;
import backend.dog.Dog;
import backend.tag.Tag;
import guicontrol.AppData;

public class UserProfile extends PrimaryScene{
	private ArrayList<Attribute> oldAgePreferences;
	private ArrayList<Attribute> oldSexPreferences;
	private ArrayList<Attribute> oldSizePreferences;
	private ArrayList<Attribute> oldEnergyLevelPreferences;
	private Hashtable<Integer, Tag> oldTagPreferences; 
	
	private static UserProfile instance;

	public ArrayList<Attribute> getOldEnergyLevelPreferences() {
		return oldEnergyLevelPreferences;
	}

	public ArrayList<Attribute> getOldSizePreferences() {
		return oldSizePreferences;
	}

	public ArrayList<Attribute> getOldSexPreferences() {
		return oldSexPreferences;
	}
	
	public ArrayList<Attribute> getOldAgePreferences() {
		return oldAgePreferences;
	}
	public Hashtable<Integer, Tag> getOldTagPreferences(){
		return oldTagPreferences; 
	}

	public static UserProfile getInstance() {
		if (instance == null) {
			instance = new UserProfile();		
		}
		return instance;
	}
	
	private UserProfile() {
		
	}

	public static void main(String[] args) {
        launch(args);
    }


	

	@Override
    public void start(Stage primaryStage) {
		initailizePrimaryScene();
    	HashMap<Integer, Tag> tags = appData.getallTags();
    	HashMap<Integer,ArrayList<Attribute>> allAttributes = appData.getAllAttributes();
//    	System.out.println(dog.getName());	
    	VBox root = new VBox();
    	
    	// save an copy of the user's preferences prior to changing them (optimise)
    	
    	oldSexPreferences = user.getSexPreferences();
		oldAgePreferences = user.getAgePreferences();
		oldEnergyLevelPreferences = user.getEnergyLevelPreferences();
		oldSizePreferences = user.getSizePreferences();
		oldTagPreferences = user.getTagPreferences();

    	
    	// create navBar
    	   	
    	// back button
    	HBox navTab = Components.navTab(UserProfile.getInstance(), LikedDogScene.getInstance(), dogProfileScene, primaryStage, "userProfile", appData);
    	// set up preferences vbox
    	VBox preferences = new VBox();
    	preferences.setAlignment(javafx.geometry.Pos.CENTER);
    	
    	// preferences title
    	Label preferencesTitle = Components.mediumLabel("Tags", Pos.CENTER);
    	
    	
    	// sliders
    	

    	// TO BE IMPLEMENTED: GET JAVAFX CONTROLSFX LIBRARY WITH GRADLE!
//    	RangeSlider ageSlider = new RangeSlider();
    	
    	preferences.getChildren().addAll(
    			preferencesTitle
//    			energyComboBox,
//    			sizeComboBox
//    			ageSlider
    			
    			);
    	
    	//display all tags
    	
    	GridPane tagsGrid =  Components.createTags(tags,user);
    	tagsGrid.setAlignment(javafx.geometry.Pos.CENTER);
    	
    	VBox attributes = new VBox();
    	attributes.setAlignment(javafx.geometry.Pos.CENTER);
    	
    	
    	Label attributesTitle = Components.mediumLabel("Attributes", Pos.CENTER);
    	
    	Label sexAttributesTitle = Components.smallLabel("Sex", Pos.BASELINE_LEFT);
    	GridPane sexAttributeGrid = Components.createAttribute(user.getSexPreferences(), 1,allAttributes);
    	sexAttributeGrid.setAlignment(javafx.geometry.Pos.CENTER);   	
    	
    	Label sizeAttributesTitle = Components.smallLabel("Size",  Pos.BASELINE_LEFT);
    	GridPane sizeAttributeGrid = Components.createAttribute(user.getSizePreferences(), 3,allAttributes);
    	sizeAttributeGrid.setAlignment(javafx.geometry.Pos.CENTER);   	
    	  	
    	Label energyLevelAttributesTitle = Components.smallLabel("EnergyLevel",  Pos.BASELINE_LEFT);
    	GridPane energyLevelAttributeGrid = Components.createAttribute(user.getEnergyLevelPreferences(),2,allAttributes);
    	energyLevelAttributeGrid.setAlignment(javafx.geometry.Pos.CENTER);   	 	
    	
    	Label ageAttributesTitle = Components.smallLabel("Age",  Pos.BASELINE_LEFT);
    	GridPane ageAttributeGrid = Components.createAttribute(user.getAgePreferences(), 0,allAttributes);
    	ageAttributeGrid.setAlignment(javafx.geometry.Pos.CENTER);   	
    	
    	
    	
    	attributes.getChildren().addAll(
    	attributesTitle,
    	sizeAttributesTitle,
    	sizeAttributeGrid,
    	sexAttributesTitle,
		sexAttributeGrid,
		ageAttributesTitle,
		ageAttributeGrid,
		energyLevelAttributesTitle,
		energyLevelAttributeGrid
    	);
    	
    	root.getChildren().addAll(
    			navTab,
    			preferences,
    			tagsGrid,
    			attributes
    			);
    	
    	root.setAlignment(javafx.geometry.Pos.CENTER);
    
    	root.setSpacing(20);
    
    	StackPane stackPane = new StackPane(root);
    	stackPane.setAlignment(javafx.geometry.Pos.CENTER);
    	
    	ScrollPane scrollPane = new ScrollPane(stackPane);
    	scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
	    scrollPane.setFitToWidth(true);

    	
//        scrollPane.setAlignment(javafx.geometry.Pos.CENTER);
        
        
    	Scene scene = new Scene(scrollPane, Components.screenWidth, Components.screenHeight);
    	
		primaryStage.setScene(scene);
		primaryStage.setTitle("Pawfect Pairs");
//		primaryStage.setMaximized(true);
		primaryStage.show();
		
//		primaryStage.setOnCloseRequest(event -> {
//    	    System.out.println("Window is closing. Perform cleanup if needed.");
//    	    Database.onApplicationClose(user, posterDogs);
//    	});
		
		

	} 	 
}