package guilayout;
import java.util.ArrayList;
import java.util.Hashtable;

import backend.dog.Dog;

import backend.poster.Poster;
import backend.user.User;
import backend.wallet.Wallet;
import guicontrol.AppData;
import javafx.application.Application;
import javafx.stage.Stage;

public class PrimaryScene extends Application{

    protected AppData appData;
    protected ArrayList<Dog> posterDogs;
    protected Hashtable<Integer,Poster> posterList;
    protected UserProfile userProfile;
    protected LikedDogScene likedDog;
    protected DogProfileScene dogProfileScene;
    protected SponsoredDogsScene sponsoredDog; 
    protected User user;
    protected Wallet wallet;
	protected BookedAppointmentScene bookedAppointment;

    @Override
    public void start(Stage primaryStage) throws Exception {
        

    }
    
    public void initailizePrimaryScene() {
    	this.appData = AppData.getInstance();
        this.posterDogs = appData.getSortedDogProfiles();
        this.posterList = appData.getPosters();
        this.userProfile = UserProfile.getInstance();
        this.likedDog = LikedDogScene.getInstance();
        this.dogProfileScene = DogProfileScene.getInstance();
        this.sponsoredDog = SponsoredDogsScene.getInstance();
        this.user = appData.getUser();
        this.bookedAppointment = BookedAppointmentScene.getInstance();
//        this.wallet= appData.getWallet();
    }
}
