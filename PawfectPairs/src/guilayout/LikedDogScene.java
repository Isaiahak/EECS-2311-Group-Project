package guilayout;

import backend.user.User;
import javafx.application.Application;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.ArrayList;
import backend.dog.Dog;


public class LikedDogScene extends Application{

	private static LikedDogScene instance;
	
	public static LikedDogScene getInstance() {
		if (instance == null) {
			instance = new LikedDogScene();		
		}
		return instance;
	}

	
	@Override
	public void start(Stage stage){
		LoginScene loginScene  = LoginScene.getInstance();
		DogProfileScene dogProfileScene = DogProfileScene.getInstance();  
		User user = loginScene.sendUserInfo();
		ArrayList<Dog> likedDogs = user.getLikedDogs();
		
		VBox root = new VBox();
		root.setAlignment(javafx.geometry.Pos.CENTER);
    	root.setSpacing(20);
    	
		Button backButton = Components.button("Back");
		backButton.setAlignment(Pos.TOP_LEFT);
		
		 backButton.setOnAction(e -> {
			 dogProfileScene.start(stage);
	     });
		 
		VBox likedDogsDisplay = new VBox();
	    	
    	for(Dog d : likedDogs) {
    		likedDogsDisplay.getChildren().add(Components.likedDogView(d));
    	}
    	likedDogsDisplay.setAlignment(javafx.geometry.Pos.CENTER);
    	
    	Label likedDogsLabel = Components.largeLabel("Dogs you've Liked", Pos.CENTER);
    	   	
    	root.getChildren().addAll(
    			backButton,
    			likedDogsLabel,
    			likedDogsDisplay
    			);
	    	

	    	
    	StackPane stackPane = new StackPane(root);
    	stackPane.setAlignment(javafx.geometry.Pos.CENTER);
    	
    	ScrollPane scrollPane = new ScrollPane(stackPane);
    	
    	
//	        scrollPane.setAlignment(javafx.geometry.Pos.CENTER);
        
        
    	Scene scene = new Scene(scrollPane, Components.screenWidth, Components.screenHeight);
    	
		stage.setScene(scene);
		stage.setTitle("Pawfect Pairs");
		stage.setMaximized(true);
		stage.show();
		
	}	
	

}
