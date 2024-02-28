package guilayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

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

public class PosterProfileScene extends Application {
	
	AppData appData;
	private static PosterProfileScene instance; 
	

//	
//	 ArrayList<Dog> posterDogs = AppData.getInstance().getDogProfiles();//TEMP
//	 User user = AppData.getInstance().getUser();
	 Poster currentPoster; 
	 public void setCurrentPoster(Poster poster) {
		 this.currentPoster = poster; // set current poster
	 }
	 
	 public static PosterProfileScene getInstance() {
			if (instance == null) {
				instance = new PosterProfileScene();		
			}
			return instance;
		}
	 private PosterProfileScene() {
			
		}
	 public static void main(String[] args) {
	        launch(); // launch THIS class
	    }
	
				 
			
	


	 @Override  
     public void start(Stage primaryStage) throws Exception {  
	 appData = AppData.getInstance();
	 PriorityQueue<Dog> posterDogs = appData.getSortedDogProfiles();
	 User user = appData.getUser();
	 DogProfileScene dogProfileScene = DogProfileScene.getInstance();
	 UserProfile userProfile = UserProfile.getInstance();
	
	 // find a way to set the posters dogs so we dont have to do a db call, this is because we would then be required to update the db and then pull which is just not necessary
	 ArrayList<Dog> posterDogsList = Database.getPosterDogs(currentPoster.getUniqueId()); // too lazy to fix other method names, so this is what its gonna be called lol
	
	
	
	
	 HBox navTab = Components.navTab(userProfile, LikedDogScene.getInstance(), dogProfileScene, primaryStage);
	 navTab.setAlignment(Pos.CENTER);
	
	
	 VBox root = new VBox();
		
	
	
	 Label name = Components.largeLabel(currentPoster.getDisplayName(), Pos.CENTER); 
	 name.setAlignment(Pos.CENTER);
	 VBox PosterInfo = new VBox();
	 PosterInfo.setAlignment(Pos.CENTER);
	  
	 HBox stars = Components.generateStars(currentPoster.getScore());
	  
	  // generate stars and display name 
	 PosterInfo.getChildren().addAll(
			  name, 
			  stars);
	
	  
	 VBox posterProfileDogsDisplay = new VBox();
	 posterProfileDogsDisplay.setSpacing(50);
	 
	  // poster's dogs display
	 for(Dog d : posterDogsList) {
		posterProfileDogsDisplay.getChildren().add(Components.posterDogView(d));
	}
	  
	root.getChildren().addAll(
			 navTab,
			 PosterInfo,
			 posterProfileDogsDisplay
			 );
	root.setAlignment(Pos.CENTER);
	  
	StackPane base = new StackPane(root);  
	base.setAlignment(Pos.CENTER);
	 
	ScrollPane scrollPane = new ScrollPane(base);
	scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
	scrollPane.setFitToWidth(true);
	
	Scene scene = new Scene(scrollPane, Components.screenWidth, Components.screenHeight);
	primaryStage.setScene(scene);
	primaryStage.show();
	
//		primaryStage.setOnCloseRequest(event -> {
//		    System.out.println("Window is closing. Perform cleanup if needed.");
//		    
//		    Database.onApplicationClose(user, posterDogs);
//		});
//	        
//	  System.out.println(currentPoster.getScore());
	        
	    }  
	 
	 }

