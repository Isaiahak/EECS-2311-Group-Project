package guilayout;

import backend.user.User;
import backend.wallet.RecurringPayment;
import guicontrol.AppData;
import javafx.application.Application;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.TreeSet;

import backend.database.Database;
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
		
		initailizePrimaryScene();
		
		HashMap<Integer, RecurringPayment> recurringPayments = user.getWallet().getRecurringPayments();
		VBox root = new VBox();
		root.setAlignment(javafx.geometry.Pos.CENTER);
    	root.setSpacing(20);
    	
    	HBox navTab = Components.navTab(userProfile, likedDog, dogProfileScene, sponsoredDog, bookedAppointment, stage,"sponsoredDogs", appData);
 
		VBox sponsoredDogsDisplay = new VBox();
	    	
    	for(RecurringPayment pay : recurringPayments.values()) {
    		ArrayList<Dog> dogs = appData.getDogProfiles().get(pay.getPosterId()); 
    		Dog d = findDogById(dogs, pay.getDogId());
    		
    		
    		sponsoredDogsDisplay.getChildren().add(Components.sponsoredDogView(d, stage, appData.getPosters(), appData, sponsoredDog));
    	}
    	sponsoredDogsDisplay.setAlignment(javafx.geometry.Pos.CENTER);
    	
//    	ScrollPane scrollPane = new ScrollPane(likedDogsDisplay);  
    	
    	Label sponsoredDogsLabel = Components.largeLabel("Dogs you've Sponsored", Pos.CENTER);
    	 
    	
    	root.getChildren().addAll(
    			navTab,
    			sponsoredDogsLabel,
    			sponsoredDogsDisplay
    			);
	    	

	    	
    	StackPane stackPane = new StackPane(root);
    	stackPane.setAlignment(javafx.geometry.Pos.CENTER);
    	
    	ScrollPane scrollPane = new ScrollPane(stackPane);
    	
    	scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setFitToWidth(true);
    	
    	
//	        scrollPane.setAlignment(javafx.geometry.Pos.CENTER);
        
        
    	Scene scene = new Scene(scrollPane, Components.screenWidth, Components.screenHeight);
    	
		stage.setScene(scene);
		stage.setTitle("Pawfect Pairs");
//		stage.setMaximized(true);
		stage.show();

//		stage.setOnCloseRequest(event -> {
//    	    System.out.println("Window is closing. Perform cleanup if needed.");
//    	    
//    	    Database.onApplicationClose(user, posterDogs);
//    	});
		
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
