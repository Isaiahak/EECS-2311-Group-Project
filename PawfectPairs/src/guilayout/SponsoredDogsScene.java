package guilayout;

import backend.wallet.RecurringPayment;
import backend.wallet.Wallet.FundsTooLow;
import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

import backend.dog.Dog;


public class SponsoredDogsScene extends PrimaryScene{

	private static SponsoredDogsScene instance;

	public static SponsoredDogsScene getInstance() {
		if (instance == null) {
			instance = new SponsoredDogsScene();		
		}
		return instance;
	}

	private SponsoredDogsScene() {
		
	}
	
	@Override
	public void start(Stage stage){
		VBox mainContainer = new VBox();
		initailizePrimaryScene(stage);
		HashMap<Integer, RecurringPayment> recurringPayments = user.getWallet().getRecurringPayments();
		mainContainer.setAlignment(javafx.geometry.Pos.CENTER);
		mainContainer.setSpacing(20);
		VBox.setVgrow(mainContainer, Priority.ALWAYS);
    	
    	HBox navTab = Components.navTab(userProfileScene, likedDogsScene, dogProfileScene, sponsoredDogsScene, bookedAppointmentsScene, stage,"sponsoredDogs", appData);
 
		VBox sponsoredDogsDisplay = new VBox();
	    	
    	for(RecurringPayment pay : recurringPayments.values()) {
    		ArrayList<Dog> dogs = appData.getUser().getLikedDogs();
    		Dog d = findDogById(dogs, pay.getDogId());
    		sponsoredDogsDisplay.getChildren().add(Components.sponsoredDogView(d, stage, appData.getPosters(), appData, sponsoredDogsScene));
    	}
    	sponsoredDogsDisplay.setAlignment(javafx.geometry.Pos.CENTER);
    	Label sponsoredDogsLabel = Components.largeLabel("Dogs you've Sponsored", Pos.CENTER);
    	 
    	
    	mainContainer.getChildren().addAll(
    			navTab,
    			sponsoredDogsLabel,
    			sponsoredDogsDisplay
    			);
	    	
   
    	
   
    	
    	root.getChildren().add(mainContainer);
    	
		stage.setScene(scene);
		stage.setTitle("Pawfect Pairs");
		stage.show();
	//	VBox info= box.getBox();

		
	}	

	
	private Dog findDogById(ArrayList<Dog> list, int targetId) {
        for (Dog d : list) {
            if (d.getId() == targetId) {
                return d; // Return the object if the ID matches
            }
        }
        return null; // Return null if the object is not found
    }
}