package guilayout;
import java.util.Hashtable;
import java.util.PriorityQueue;

import backend.dog.Dog;

import backend.poster.Poster;
import backend.user.User;
import backend.wallet.Wallet;
import guicontrol.AppData;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class PrimaryScene extends Application{

    protected AppData appData;
    protected PriorityQueue<Dog> allDogs;
    protected Hashtable<Integer,Poster> posterList;
    protected UserProfile userProfileScene;
    protected LikedDogScene likedDogsScene;
    protected DogProfileScene dogProfileScene;
    protected SponsoredDogsScene sponsoredDogsScene; 
    protected User user;
    protected Wallet wallet;
	protected BookedAppointmentScene bookedAppointmentsScene;
    protected Scene scene;
    protected BorderPane root;
    protected VBox mainContainer;

    @Override
    public void start(Stage primaryStage) throws Exception {
        
    }
    
    public void initailizePrimaryScene(Stage primaryStage) {
//    	String css = this.getClass().getResource("/style.css").toExternalForm();
//    	primaryStage.getScene().getStylesheets().add(css);
    	
    	root = new BorderPane();
    	root.setMinHeight(Components.screenHeight);
    	
    	//this is where you would attach all the nodes/ scene
    	mainContainer = new VBox();
    	mainContainer.setSpacing(30);
    	mainContainer.setAlignment(Pos.CENTER);
    	mainContainer.getStyleClass().add("root-container");
      
      double paddingX = Components.screenWidth * 0.1; // 10% of screen width
      double paddingY = Components.screenHeight * 0.1; // 10% of screen height
      
      // Calculate padding based on screen size (e.g., 10% of screen width and height)
    	mainContainer.setPadding(new Insets(paddingY/20,paddingX, paddingY/2, paddingX));//based on screen size(top, right, bottom, left)
    	VBox.setVgrow(root, Priority.ALWAYS);
      // Set VBox size to match screen resolution
       mainContainer.setMaxSize(Components.screenWidth, Components.screenHeight);
      
      mainContainer.setMaxHeight(Components.screenHeight);

    	ScrollPane scrollPane = new ScrollPane(mainContainer);
    	scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    	scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Set vertical scrollbar policy to "as needed"
    	scrollPane.setFitToWidth(true);
    	scrollPane.setFitToHeight(true);
    	
    	root.setCenter(scrollPane);
//    	root.setRight(null);
//    	root.setLeft(null);

    	
    	this.scene = new Scene(root, Components.screenWidth, Components.screenHeight);

    	
    	primaryStage.setTitle("Pawfect Pairs");
		primaryStage.setScene(scene);
		
		String css = this.getClass().getResource("/style.css").toExternalForm();
    	primaryStage.getScene().getStylesheets().add(css);
    	
    	this.appData = AppData.getInstance();
        this.allDogs = appData.getSortedDogProfiles();
        this.posterList = appData.getPosters();
        this.userProfileScene = UserProfile.getInstance();
        this.likedDogsScene = LikedDogScene.getInstance();
        this.dogProfileScene = DogProfileScene.getInstance();
        this.sponsoredDogsScene = SponsoredDogsScene.getInstance();
        this.user = appData.getUser();
        this.bookedAppointmentsScene = BookedAppointmentScene.getInstance();
    	this.wallet = user.getWallet();
    	
    	root.setTop(Components.navTab(userProfileScene, likedDogsScene, dogProfileScene, sponsoredDogsScene, bookedAppointmentsScene, primaryStage, appData));
    	
    }
    
    public void initailizePrimaryScene(Stage primaryStage, String test) {
    	root = new BorderPane();
    	root.getStyleClass().add("root-container");
    //	root.setMinHeight(Components.screenHeight);
    	root.setMaxHeight(Components.screenHeight);    //testing to see if this displays better than min	
    	
    	mainContainer = new VBox();
        mainContainer.setMaxSize(Components.screenWidth, Components.screenHeight);

		mainContainer.setPadding(new Insets(Components.screenHeight/10, Components.screenWidth/40, Components.screenHeight/30, Components.screenWidth/40));//based on screen size(top, right, bottom, left)
		
    	ScrollPane scrollPane = new ScrollPane(mainContainer);
    	scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    	scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Set vertical scrollbar policy to "as needed"
    	scrollPane.setFitToWidth(true);
    	scrollPane.setFitToHeight(true);
    	
    	root.setCenter(scrollPane);
//    	root.setRight(null);
//    	root.setLeft(null);

    	
    	this.scene = new Scene(root, Components.screenWidth, Components.screenHeight);
    	
//		root.setAlignment(Pos.CENTER);	
    	
		primaryStage.setScene(scene);
		
		String css = this.getClass().getResource("/style.css").toExternalForm();
    	primaryStage.getScene().getStylesheets().add(css);
    	
    	this.appData = AppData.getInstance();
        this.allDogs = appData.getSortedDogProfiles();
        this.posterList = appData.getPosters();
        this.userProfileScene = UserProfile.getInstance();
        this.likedDogsScene = LikedDogScene.getInstance();
        this.dogProfileScene = DogProfileScene.getInstance();
        this.sponsoredDogsScene = SponsoredDogsScene.getInstance();
        this.user = appData.getUser();
        this.bookedAppointmentsScene = BookedAppointmentScene.getInstance();
    	this.wallet = user.getWallet();
    	
    	root.setTop(Components.navTab(userProfileScene, likedDogsScene, dogProfileScene, sponsoredDogsScene, bookedAppointmentsScene, primaryStage, appData));
    	
    }

    public Scene getScene(){
        return this.scene;
    }


}