package guilayout;
import java.util.Hashtable;
import java.util.PriorityQueue;

import backend.dog.Dog;

import backend.poster.Poster;
import backend.user.User;
import backend.wallet.Wallet;
import guicontrol.AppData;
import javafx.application.Application;
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
protected GiveVBox box;
    @Override
    public void start(Stage primaryStage) throws Exception {
        

    }
    
    public void initailizePrimaryScene() {
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
    	this.box= GiveVBox.getInstance();
    	
    }
}
