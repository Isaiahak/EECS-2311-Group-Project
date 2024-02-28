package guilayout;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

import backend.database.Database;
import backend.dog.Dog;
import backend.tag.Tag;
import backend.user.User;
import guicontrol.AppData;

public class UserProfile extends Application{
	
	private static UserProfile instance;
	
    AppData appData;
	
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
    	appData = AppData.getInstance();
    	PriorityQueue<Dog> posterDogs = appData.getSortedDogProfiles();
    	User user = appData.getUser();
    	HashMap<Integer, Tag> tags = appData.getallTags();		
    	DogProfileScene dogProfileScene = DogProfileScene.getInstance();  
		Dog dog = user.getDog();   
    	System.out.println(dog.getName());	
    	VBox root = new VBox();
    	
    	
    	// create navBar
    	   	
    	// back button
    	HBox navTab = Components.navTab(UserProfile.getInstance(), LikedDogScene.getInstance(), dogProfileScene, primaryStage);
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
    	
    	GridPane tagsGrid =  Components.createTags(tags,dog);
    	tagsGrid.setAlignment(javafx.geometry.Pos.CENTER);
    	
    	VBox attributes = new VBox();
    	attributes.setAlignment(javafx.geometry.Pos.CENTER);
    	
    	
    	Label attributesTitle = Components.mediumLabel("Attributes", Pos.CENTER);
    	
    	Label sexAttributesTitle = Components.smallLabel("Sex", Pos.BASELINE_LEFT);
    	GridPane sexAttributeGrid = Components.createAttribute(dog.getSex(), dog);
    	sexAttributeGrid.setAlignment(javafx.geometry.Pos.CENTER);   	
    	
    	Label sizeAttributesTitle = Components.smallLabel("Size",  Pos.BASELINE_LEFT);
    	GridPane sizeAttributeGrid = Components.createAttribute(dog.getSize(), dog);
    	sizeAttributeGrid.setAlignment(javafx.geometry.Pos.CENTER);   	
    	  	
    	Label energyLevelAttributesTitle = Components.smallLabel("EnergyLevel",  Pos.BASELINE_LEFT);
    	GridPane energyLevelAttributeGrid = Components.createAttribute(dog.getEnergyLevel(), dog);
    	energyLevelAttributeGrid.setAlignment(javafx.geometry.Pos.CENTER);   	 	
    	
    	Label ageAttributesTitle = Components.smallLabel("Age",  Pos.BASELINE_LEFT);
    	GridPane ageAttributeGrid = Components.createAttribute(dog.getAge(), dog);
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