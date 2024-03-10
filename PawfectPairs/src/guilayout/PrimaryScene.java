package guilayout;
import java.util.ArrayList;
import java.util.Hashtable;

import backend.dog.Dog;

import backend.poster.Poster;
import backend.user.User;
import guicontrol.AppData;
import javafx.application.Application;
import javafx.stage.Stage;

public class PrimaryScene  extends Application{

    protected AppData appData = AppData.getInstance();
    protected ArrayList<Dog> posterDogs = appData.getSortedDogProfiles();
    protected Hashtable<Integer,Poster> posterList = appData.getPosters();
    protected UserProfile userProfile = UserProfile.getInstance();
    protected LikedDogScene likedDog = LikedDogScene.getInstance();
    protected DogProfileScene dogProfileScene = DogProfileScene.getInstance();
    protected User user = appData.getUser();



    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}
