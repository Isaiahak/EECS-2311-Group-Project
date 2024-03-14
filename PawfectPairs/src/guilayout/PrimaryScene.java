package guilayout;
import java.util.Hashtable;
import java.util.PriorityQueue;

import backend.dog.Dog;

import backend.poster.Poster;
import backend.user.User;
import backend.wallet.Wallet;
import guicontrol.AppData;
import javafx.application.Application;
import javafx.scene.Scene;
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
    protected VBox root;

    @Override
    public void start(Stage primaryStage) throws Exception {
        

    }
    
    public void initailizePrimaryScene() {
        this.root.setStyle("-fx-background-color: linear-gradient(to bottom, #87FF83, #FFFFFF)");
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
