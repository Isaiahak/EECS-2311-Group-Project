package guilayout;
import java.util.Hashtable;
import java.util.PriorityQueue;

import backend.dog.Dog;

import backend.poster.Poster;
import backend.user.User;
import backend.wallet.Wallet;
import guicontrol.AppData;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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
    protected StackPane root;

    @Override
    public void start(Stage primaryStage) throws Exception {
        
    }
    
    public void initailizePrimaryScene(Stage primaryStage) {
//    	String css = this.getClass().getResource("/style.css").toExternalForm();
//    	primaryStage.getScene().getStylesheets().add(css);
    	
    	root = new StackPane();
    	root.getStyleClass().add("root-container");
    	
    	ScrollPane scrollPane = new ScrollPane(root);
    	scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    	scrollPane.setFitToWidth(true);
    	scrollPane.setFitToHeight(true);

    	
    	this.scene = new Scene(scrollPane, Components.screenWidth, Components.screenHeight);
    	
		root.setAlignment(Pos.CENTER);		
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
    	
    }

    public Scene getScene(){
        return this.scene;
    }


}
