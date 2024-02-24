package guilayout;
import javafx.application.Application;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;

import backend.dog.Dog;
import backend.dog.trait.Age;
import backend.dog.trait.EnergyLevel;
import backend.dog.trait.Sex;
import backend.dog.trait.Size;
import backend.user.User;

public class UserProfile extends Application{
	
	private static UserProfile instance;
	
	public static UserProfile getInstance() {
		if (instance == null) {
			instance = new UserProfile();		
		}
		return instance;
	}

  public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	LoginScene loginScene  = LoginScene.getInstance();
		DogProfileScene dogProfileScene = DogProfileScene.getInstance();  
    	User user = loginScene.sendUserInfo();	
    	Dog dog = user.getDog();    	  	
    	// root container 
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
    	
    	GridPane tagsGrid =  Components.createTags(Components.allTags,dog.getTags());
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
    	
//        scrollPane.setAlignment(javafx.geometry.Pos.CENTER);
        
        
    	Scene scene = new Scene(scrollPane, Components.screenWidth, Components.screenHeight);
    	
		primaryStage.setScene(scene);
		primaryStage.setTitle("Pawfect Pairs");
//		primaryStage.setMaximized(true);
		primaryStage.show();
		
		
//		LoginScene loginScene  = LoginScene.getInstance();
//		DogProfileScene dogProfileScene = DogProfileScene.getInstance();  
//		User user = loginScene.sendUserInfo();	
//		ArrayList<Dog> likedDogs = user.getLikedDogs();
//		ObservableList<Dog> observableList = FXCollections.observableArrayList(likedDogs);
//		int width = 900;
//		int height = 900;
//		
//		GridPane gridPane = new GridPane();
//		gridPane.setAlignment(javafx.geometry.Pos.CENTER);
//        gridPane.setHgap(10);
//        gridPane.setVgap(10);
//        gridPane.setPadding(new Insets(25, 25, 25, 25));
//        
//        Label emailLabel = new Label("Email:");
//        TextField emailTextField = new TextField();
//        Label passwordLabel = new Label("Password:");
//        PasswordField passwordField = new PasswordField();
//
//        Button updateButton = new Button("Update");
//        
//        gridPane.add(emailLabel, 0, 0);
//        gridPane.add(emailTextField, 1, 0);
//        gridPane.add(passwordLabel, 0, 1);
//        gridPane.add(passwordField, 1, 1);
//        gridPane.add(updateButton, 0, 2);
//        
//        updateButton.setOnAction(e -> {
//            String email = emailTextField.getText();
//            String password = passwordField.getText();
//            if (password == "" && email == "") {
//	            Alert alert = new Alert(Alert.AlertType.ERROR);
//	            alert.setTitle("Update Failed");
//	            alert.setHeaderText(null);
//	            alert.setContentText("Please enter a valid email or password.");
//	            alert.showAndWait();
//            }
//            else {
//            	user.setEmail(email);
//            	user.setPassword(password);
//            	
//           }
//        });
//        
//        
//		
//		
//		
//		Button backButton = new Button("Back");
//		backButton.setAlignment(Pos.TOP_RIGHT);
//		
//		 backButton.setOnAction(e -> {
//			 dogProfileScene.start(primaryStage);
//	            });
//		// Convert ArrayList to ObservableList for the ListView
//		 ListView<Dog> listView = new ListView<Dog>(observableList);
//
//        // Create ScrollPane and set ListView as its content
//	 
//        ScrollPane scrollPane = new ScrollPane(listView);
//        scrollPane.setFitToWidth(true);
//        scrollPane.setFitToHeight(true);
//        
//
//        // Create BorderPane
//        BorderPane borderPane = new BorderPane();
//        borderPane.setBottom(scrollPane);
//        borderPane.setCenter(gridPane);
//        borderPane.setTop(backButton);
//        // Create Scene
//        Scene scene = new Scene(borderPane, width, height);
//
//        // Set the scene to the stage
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("ScrollPane Example");
//        primaryStage.show();
	} 	 
}