package guilayout;

import backend.user.User;
import guicontrol.AppData;
import javafx.application.Application;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.ArrayList;

import backend.database.Database;
import backend.dog.Dog;


public class LikedDogScene extends Application{
	
	
	ArrayList<Dog> posterDogs;
	ArrayList<Dog> likedDogs;
	User user;

	private static LikedDogScene instance;

	public static LikedDogScene getInstance() {
		if (instance == null) {
			instance = new LikedDogScene();		
		}
		return instance;
	}
	private LikedDogScene() {
		
	}
	
	@Override
	public void start(Stage stage){
		posterDogs = AppData.getInstance().getDogProfiles();//TEMP
		user = AppData.getInstance().getUser();
		
		DogProfileScene dogProfileScene = DogProfileScene.getInstance();  
		likedDogs = user.getLikedDogs();
		UserProfile userProfile = UserProfile.getInstance();
		
		VBox root = new VBox();
		root.setAlignment(javafx.geometry.Pos.CENTER);
    	root.setSpacing(20);
    	
    	HBox navTab = Components.navTab(userProfile, LikedDogScene.getInstance(), DogProfileScene.getInstance(), BookedAppointmentScene.getInstance(), stage);
    	
//		Button backButton = Components.button("Back");
//		backButton.setAlignment(Pos.TOP_LEFT);
		
//		 backButton.setOnAction(e -> {
//			 dogProfileScene.start(stage);
//	     });
		 
		VBox likedDogsDisplay = new VBox();
	    	
    	for(Dog d : likedDogs) {
    		likedDogsDisplay.getChildren().add(Components.likedDogView(d, stage));
    	}
    	likedDogsDisplay.setAlignment(javafx.geometry.Pos.CENTER);
    	
//    	ScrollPane scrollPane = new ScrollPane(likedDogsDisplay);  
    	
    	Label likedDogsLabel = Components.largeLabel("Dogs you've Liked", Pos.CENTER);
    	 
    	
    	root.getChildren().addAll(
    			navTab,
    			likedDogsLabel,
    			likedDogsDisplay
    			);
	    	

	    	
    	StackPane stackPane = new StackPane(root);
    	stackPane.setAlignment(javafx.geometry.Pos.CENTER);
    	
    	ScrollPane scrollPane = new ScrollPane(stackPane);
    	
    	scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setFitToWidth(true);
    	
    	
//	        scrollPane.setAlignment(javafx.geometry.Pos.CENTER);
        
        
    	Scene scene = new Scene(scrollPane, Components.screenWidth, Components.screenHeight);
    	
		stage.setScene(scene);
		stage.setTitle("Pawfect Pairs");
//		stage.setMaximized(true);
		stage.show();
//		stage.setOnCloseRequest(event -> {
//    	    System.out.println("Window is closing. Perform cleanup if needed.");
//    	    
//    	    Database.onApplicationClose(user, posterDogs);
//    	});
		
	}	
	// Method to get the list of liked dogs
    public ArrayList<Dog> getLikedDogs() {
        return likedDogs;
    }
	

}
