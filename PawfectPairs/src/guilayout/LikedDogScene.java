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
		initailizePrimaryScene();
		ArrayList<Dog> likedDogs = user.getLikedDogs();
		VBox root = new VBox();
		root.setAlignment(javafx.geometry.Pos.CENTER);
    	root.setSpacing(20);
    	

    	HBox navTab = Components.navTab(userProfileScene, LikedDogScene.getInstance(), dogProfileScene, sponsoredDogsScene, BookedAppointmentScene.getInstance(),stage,"likedDogs", appData);
		VBox likedDogsDisplay = new VBox();
	    	
    	for(Dog d : likedDogs) {
    		likedDogsDisplay.getChildren().add(Components.likedDogView(d, stage, appData.getPosters()));
    	}
    	likedDogsDisplay.setAlignment(javafx.geometry.Pos.CENTER);
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
    	
    	

        
        
    	Scene scene = new Scene(scrollPane, Components.screenWidth, Components.screenHeight);
    	
		stage.setScene(scene);
		stage.setTitle("Pawfect Pairs");
		stage.show();

//		stage.setOnCloseRequest(event -> {
//    	    System.out.println("Window is closing. Perform cleanup if needed.");
//    	    
//    	    Database.onApplicationClose(user, posterDogs);
//    	});
		
	}
}
