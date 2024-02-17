package guilayout;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.util.ArrayList;

import backend.dog.Dog;
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
		ArrayList<Dog> likedDogs = user.getLikedDogs();
		
		
		
		
		Button backButton = new Button("Login");
		
		 backButton.setOnAction(e -> {
			 dogProfileScene.start(primaryStage);
	            });
		// Convert ArrayList to ObservableList for the ListView
		 ListView<Dog> listView = new ListView<Dog>();

	        // Create ScrollPane and set ListView as its content
	        ScrollPane scrollPane = new ScrollPane(listView);
	        scrollPane.setFitToWidth(true);
	        scrollPane.setFitToHeight(true);

	        // Create BorderPane
	        BorderPane borderPane = new BorderPane();
	        borderPane.setCenter(scrollPane);

	        // Create Scene
	        Scene scene = new Scene(borderPane, 300, 200);

	        // Set the scene to the stage
	        primaryStage.setScene(scene);
	        primaryStage.setTitle("ScrollPane Example");
	        primaryStage.show();
	} 	 
}
