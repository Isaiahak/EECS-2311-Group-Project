package guilayout;


import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.TreeSet;

import backend.database.Database;
import backend.dog.Dog;
import backend.user.User;
import guicontrol.AppData;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class OutOfDogsScene extends PrimaryScene{
	AppData appData;
	private static OutOfDogsScene instance;
	public static OutOfDogsScene getInstance() {
		if (instance == null)
			instance = new OutOfDogsScene();
		return instance;
	}
	private OutOfDogsScene() {
		
	}
	
	public static void main(String[] args) {
        launch(args);
    }

	@Override
	public void start(Stage stage) {
		Components.updateCurrentScene("dogProfile");
		mainContainer = new VBox();
		initailizePrimaryScene(stage);
		appData = AppData.getInstance();
		PriorityQueue<Dog> posterDogs = appData.getSortedDogProfiles();
		User user = appData.getUser();

		mainContainer.setSpacing(15);
		mainContainer.setAlignment(Pos.TOP_CENTER);
		Label pageLabel = Components.largeLabel("Out of Dogs!",Pos.TOP_CENTER);
		stage.setTitle("Pawfect Pairs");	

		mainContainer.getChildren().add(pageLabel);
		StackPane stackPane = new StackPane(root);
		stackPane.setAlignment(javafx.geometry.Pos.CENTER);


//		root.getChildren().add(mainContainer);
		
		stage.setScene(scene);
		stage.show();
		
//		stage.setOnCloseRequest(event -> {
//    	    System.out.println("Window is closing. Perform cleanup if needed.");
//    	    
//    	    Database.onApplicationClose(user, posterDogs);
//    	});
	}
}
