package guilayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import backend.database.Database;
import backend.dog.Dog;
import backend.poster.Poster;
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
	String name="doglover123";
	int rating=5;
	int id = 0;
	Poster poster1= new Poster(rating, name, id);
	int width =550;
	int height =500;
	//        Dog dog = new Dog("Buddy", 1, 3, new EnergyLevel(1), new Size(2), "M", null);
	
	int weight =1;
	int weight2=2;
	int weight3=0;

	private static PosterProfileScene instance; 
	
	
	    
//	ArrayList<Dog> posterDogs = new ArrayList<Dog>(Arrays.asList(
//			new Dog("Charles", 1, 2, 1, 2, 1, new Poster(5, "doglover123", 12), false, "file:src/guilayout/dog.png", "Charles is involed in some shady stuff. Don't ask questions you don't want to know the answers to."),
//			new Dog("Sandy", 2, 3, 1, 2, 0, new Poster(5, "doglover123", 12), false,"file:src/guilayout/dog1.png", "Sandy looks like she knows something you don't... but that's what makes her so loveable!"),
//			new Dog("Chuckles", 3, 3, 0, 0, 1, new Poster(5, "doglover123", 12), false,"file:src/guilayout/dog4.png", "Lazy and old Chuckles is the perfect lap dog for anyone who doesn't like to move.")
//	));
//	
	
	 ArrayList<Dog> posterDogs = Database.getAllDogs(); //TEMP
	


	 @Override  
	    public void start(Stage primaryStage) throws Exception {  
	    	DogProfileScene dogProfileScene = DogProfileScene.getInstance();
	    	LoginScene login = LoginScene.getInstance();
	    	UserProfile userProfile = UserProfile.getInstance();
	    	
	    	

	    	
	    	HBox navTab = Components.navTab(userProfile, LikedDogScene.getInstance(), dogProfileScene, primaryStage);
	    	navTab.setAlignment(Pos.CENTER);
	    	
	    	
	    	VBox root = new VBox();
	    	


	      Label name = Components.largeLabel(poster1.getDisplayName(), Pos.CENTER); 
	      name.setAlignment(Pos.CENTER);
	      VBox PosterInfo = new VBox();
	      PosterInfo.setAlignment(Pos.CENTER);
	      
	      HBox stars = Components.generateStars(poster1.getScore());
	      
	      // generate stars and display name 
	      PosterInfo.getChildren().addAll(
	    		  name, 
	    		  stars);

	      
	      VBox posterProfileDogsDisplay = new VBox();
	      posterProfileDogsDisplay.setSpacing(50);
	      
	      // poster's dogs display
	      for(Dog d : posterDogs) {
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
//	     ScrollPane scrollPane = new ScrollPane(base);

	    Scene scene = new Scene(base, Components.screenWidth, Components.screenHeight);
		primaryStage.setScene(scene);
		primaryStage.show();
	        
	  
	        
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
}

