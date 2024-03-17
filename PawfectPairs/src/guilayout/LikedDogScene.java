package guilayout;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.PriorityQueue;

import backend.dog.Dog;


public class LikedDogScene extends PrimaryScene{
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
		Components.updateCurrentScene("likedDogs");
		initailizePrimaryScene(stage);
		ArrayList<Dog> likedDogs = user.getLikedDogs();
		mainContainer.setAlignment(javafx.geometry.Pos.CENTER);
		VBox.setVgrow(root, Priority.ALWAYS);
		mainContainer.setSpacing(20);
    	
		VBox likedDogsDisplay = new VBox();
	    	
    	for(Dog d : likedDogs) {
    		likedDogsDisplay.getChildren().add(Components.likedDogView(d, stage, appData.getPosters()));
    	}
    	likedDogsDisplay.setAlignment(javafx.geometry.Pos.CENTER);
    	Label likedDogsLabel = Components.largeLabel("Dogs you've Liked: " + likedDogs.size(), Pos.CENTER);
    	 
    	
    	mainContainer.getChildren().addAll(
    			likedDogsLabel,
    			likedDogsDisplay
    			
    			);

    	
		stage.setTitle("Pawfect Pairs");
		stage.show();

//		stage.setOnCloseRequest(event -> {
//    	    System.out.println("Window is closing. Perform cleanup if needed.");
//    	    
//    	    Database.onApplicationClose(user, posterDogs);
//    	});
		
	}
}
