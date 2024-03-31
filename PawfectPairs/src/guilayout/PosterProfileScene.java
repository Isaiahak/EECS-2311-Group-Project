package guilayout;

import java.util.ArrayList;
import backend.database.Database;
import backend.dog.Dog;
import backend.poster.Poster;
import backend.user.User;
import guicontrol.AppData;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;  

public class PosterProfileScene extends PrimaryScene {
	AppData appData;
	private static PosterProfileScene instance;
	Poster currentPoster;

	public static void main(String[] args) {
		launch(); 
	}

	@Override  
	public void start(Stage primaryStage) throws Exception {  
		Components.updateCurrentScene("posterProfile");
		
		appData = AppData.getInstance();
		User user = appData.getUser();
		ArrayList<Dog> posterDogsList =  Database.getPosterDogs(currentPoster.getUniqueId());

		
		initailizePrimaryScene(primaryStage);


		Label name = Components.largeLabel(currentPoster.getDisplayName(), Pos.CENTER); 
		name.setAlignment(Pos.CENTER);
		VBox PosterInfo = new VBox();
		PosterInfo.setAlignment(Pos.CENTER);

		HBox stars = Components.generateStars((int)currentPoster.getScore());
		Label score = Components.mediumLabel("Total Score: "+currentPoster.getScore() + "/10", Pos.CENTER);
		Label numRatings = Components.mediumLabel("Number of Ratings: "+currentPoster.getNumberofRatings(), Pos.CENTER);
		Label email = Components.mediumLabel("Email 📧:  "+ currentPoster.getEmail(), Pos.CENTER);
		Label phone = Components.mediumLabel("Phone ☎:  "+ currentPoster.getPhone(), Pos.CENTER);
		Label ratePoster = Components.mediumLabel("Rate this poster", Pos.CENTER);
		ComboBox<String> howOftenBox = new ComboBox<>(FXCollections.observableArrayList("Once", "Weekly", "Biweekly", "Monthly"));
		VBox slider = Components.scoreSlider (user, currentPoster, primaryStage); 
		PosterInfo.getChildren().addAll(
				name, 
				email,
				phone,
				stars,
				score,
				numRatings,
				ratePoster,
				slider);


		VBox posterProfileDogsDisplay = new VBox();
		posterProfileDogsDisplay.setSpacing(50);

		for(Dog d : posterDogsList) {
			posterProfileDogsDisplay.getChildren().add(Components.posterDogView(d));
		}

		mainContainer.getChildren().addAll(
				PosterInfo,
				posterProfileDogsDisplay
				);

		
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void setCurrentPoster(Poster poster) {
		this.currentPoster = poster;
	}

	public int getCurrentPoster() {
		return this.currentPoster.getUniqueId();
	}

	public double getScore() {
		return currentPoster.getScore();
	}

	public String getDisplayName() {
		return currentPoster.getDisplayName();
	}


	public static PosterProfileScene getInstance() {
		if (instance == null) {
			instance = new PosterProfileScene();
		}
		return instance;
	}
	private PosterProfileScene() {

	}
}

