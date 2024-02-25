package guilayout;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;

import backend.database.Database;
import backend.dog.Dog;
import backend.user.User;
import guicontrol.AppData;

public class UserProfile extends Application{
	
	private static UserProfile instance;
	
    User user;
    ArrayList<Dog> posterDogs;
	
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
    	
    	posterDogs = AppData.getInstance().getDogProfiles();//TEMP
    	user = AppData.getInstance().getUser();
    	
    	LoginScene loginScene  = LoginScene.getInstance();
		DogProfileScene dogProfileScene = DogProfileScene.getInstance();  
    	Dog dog = user.getDog();    	  	
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
    	
    	GridPane tagsGrid =  Components.createTags(Database.getAllTags(),dog.getTags());
    	tagsGrid.setAlignment(javafx.geometry.Pos.CENTER);
    	
    	VBox attributes = new VBox();
    	attributes.setAlignment(javafx.geometry.Pos.CENTER);
    	
    	
    	Label attributesTitle = Components.mediumLabel("Attributes", Pos.CENTER);
    	
    	Label sexAttributesTitle = Components.smallLabel("Sex", Pos.BASELINE_LEFT);
    	GridPane sexAttributeGrid = Components.createAttribute(dog.getSex());
    	sexAttributeGrid.setAlignment(javafx.geometry.Pos.CENTER);   	
    	
    	Label sizeAttributesTitle = Components.smallLabel("Size",  Pos.BASELINE_LEFT);
    	GridPane sizeAttributeGrid = Components.createAttribute(dog.getSize());
    	sizeAttributeGrid.setAlignment(javafx.geometry.Pos.CENTER);   	
    	  	
    	Label energyLevelAttributesTitle = Components.smallLabel("EnergyLevel",  Pos.BASELINE_LEFT);
    	GridPane energyLevelAttributeGrid = Components.createAttribute(dog.getEnergyLevel());
    	energyLevelAttributeGrid.setAlignment(javafx.geometry.Pos.CENTER);   	 	
    	
    	Label ageAttributesTitle = Components.smallLabel("Age",  Pos.BASELINE_LEFT);
    	GridPane ageAttributeGrid = Components.createAttribute(dog.getAge());
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