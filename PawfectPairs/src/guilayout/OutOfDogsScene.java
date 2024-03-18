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
		initailizePrimaryScene(stage);
		appData = AppData.getInstance();
		PriorityQueue<Dog> posterDogs = appData.getSortedDogProfiles();
		User user = appData.getUser();

		Label pageLabel = Components.largeLabel("Out of Dogs!",Pos.TOP_CENTER);

		mainContainer.getChildren().add(pageLabel);

		stage.show();
		
	}
}
