package guilayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

import backend.database.Database;
import backend.dog.Dog;
import backend.poster.Poster;
import backend.user.User;
import guicontrol.AppData;
//import guicontrol.PosterProfileController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;  

public class PosterProfileScene extends PrimaryScene {
	AppData appData;
    private Hyperlink posterinfoLink;
	private static PosterProfileScene instance;
	Poster currentPoster;

	public static void main(String[] args) {
		launch(); // launch THIS class
	}

	@Override  
	public void start(Stage primaryStage) throws Exception {  
		Components.updateCurrentScene("posterProfile");
		
		appData = AppData.getInstance();
		PriorityQueue<Dog> posterDogs = appData.getSortedDogProfiles();
		User user = appData.getUser();
		DogProfileScene dogProfileScene = DogProfileScene.getInstance();
		UserProfile userProfile = UserProfile.getInstance();
		ArrayList<Dog> posterDogsList =  appData.getDogProfiles().get(currentPoster.getUniqueId());
		
		initailizePrimaryScene(primaryStage, currentPoster.getDisplayName());

//		mainContainer = new VBox();
		Label name = Components.largeLabel(currentPoster.getDisplayName(), Pos.CENTER); 
		name.setAlignment(Pos.CENTER);
		VBox PosterInfo = new VBox();
		PosterInfo.setAlignment(Pos.CENTER);

		HBox stars = Components.generateStars(currentPoster.getScore());
		Label email = Components.mediumLabel("Email 📧:  "+ currentPoster.getEmail(), Pos.CENTER);
		Label phone = Components.mediumLabel("Phone ☎:  "+ currentPoster.getPhone(), Pos.CENTER);

		PosterInfo.getChildren().addAll(
				name, 
				email,
				phone,
				stars);


		VBox posterProfileDogsDisplay = new VBox();
		posterProfileDogsDisplay.setSpacing(50);

		for(Dog d : posterDogsList) {
			posterProfileDogsDisplay.getChildren().add(Components.posterDogView(d));
		}

		mainContainer.getChildren().addAll(
				PosterInfo,
				posterProfileDogsDisplay
				);
		mainContainer.setAlignment(Pos.CENTER);

//		root.getChildren().add(mainContainer);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void setCurrentPoster(Poster poster) {
		this.currentPoster = poster;
	}

	public int getCurrentPoster() {
		return this.currentPoster.getUniqueId();
	}

	public int getScore() {
		// TODO Auto-generated method stub
		return currentPoster.getScore();
	}

	public String getDisplayName() {
		// TODO Auto-generated method stub
		return currentPoster.getDisplayName();
	}
	//end of methods to add back

	public static PosterProfileScene getInstance() {
		if (instance == null) {
			instance = new PosterProfileScene();
		}
		return instance;
	}
	private PosterProfileScene() {

	}
}

