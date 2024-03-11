package guilayout;


import java.util.ArrayList;

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

public class PosterInfoPage extends PrimaryScene{
	//ArrayList<Dog> posterDogs;
	User user;

	
	private static PosterInfoPage instance;
	
	public static PosterInfoPage getInstance() {
		if (instance == null)
			instance = new PosterInfoPage();
		return instance;
	}
	private PosterInfoPage() {
		
	}
	
	public static void main(String[] args) {
        launch(args);
    }

	@Override
	public void start(Stage stage) {
		
	//	posterDogs = AppData.getInstance().getDogProfiles();//TEMP
		user = AppData.getInstance().getUser();
		
		VBox root = new VBox();
		root.setSpacing(15);
		root.setAlignment(Pos.TOP_CENTER);
		LikedDogScene likedDog = LikedDogScene.getInstance();
		UserProfile userProfile = UserProfile.getInstance();
		int posterProfile = PosterProfileScene.getInstance().getCurrentPoster();
		
		
		 Label name = Components.largeLabel(PosterProfileScene.getInstance().getDisplayName(), Pos.CENTER); 
	      name.setAlignment(Pos.CENTER);
	      VBox PosterInfo = new VBox();
	      PosterInfo.setAlignment(Pos.CENTER);
	      
	      HBox stars = Components.generateStars(PosterProfileScene.getInstance().getScore());
	      // generate stars and display name 
	      PosterInfo.getChildren().addAll(
	    		  name, 
	    		  stars);

		Label pageLabel = Components.largeLabel("Email ",Pos.TOP_CENTER);
		Label PosterPhone= Components.largeLabel("Phone ",Pos.TOP_CENTER);
		//pageLabel.setText(Database.getPosterEmail(posterProfile));
		ArrayList<String> info = Database.getPosterInfo(posterProfile);
		//String email= info.get(0);
		pageLabel.setText("Email 📧:  "+info.get(0));
		PosterPhone.setText("Phone ☎:  "+info.get(1));

		

		
		stage.setTitle("Pawfect Pairs");	
		HBox navTab = Components.navTab(userProfile, LikedDogScene.getInstance(), dogProfileScene, stage,"likedDogs", appData);

		//HBox navTab = Components.navTab(userProfile, likedDog, DogProfileScene.getInstance(), stage);
		root.getChildren().addAll(navTab,PosterInfo, pageLabel, PosterPhone);
		StackPane stackPane = new StackPane(root);
		stackPane.setAlignment(javafx.geometry.Pos.CENTER);
		Scene scene = new Scene(stackPane, Components.screenWidth, Components.screenHeight);		 
		stage.setScene(scene);
		stage.show();
//		stage.setOnCloseRequest(event -> {
//    	    System.out.println("Window is closing. Perform cleanup if needed.");
//    	    
//    	    Database.onApplicationClose(user, posterDogs);
//    	});
		
	}

}