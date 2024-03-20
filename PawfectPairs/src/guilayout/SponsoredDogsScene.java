package guilayout;

import backend.wallet.RecurringPayment;
import backend.wallet.Wallet.FundsTooLow;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

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
		Components.updateCurrentScene("sponsoredDogs");
		initailizePrimaryScene(stage);
		
		HashMap<Integer, RecurringPayment> recurringPayments = user.getWallet().getRecurringPayments();
		
		Label sponsoredDogsLabel = Components.largeLabel("Dogs you've Sponsored", Pos.CENTER);
		mainContainer.getChildren().add(
    			sponsoredDogsLabel
    			);
	    	
    	for(RecurringPayment pay : recurringPayments.values()) {
    		ArrayList<Dog> dogs = appData.getUser().getLikedDogs();
    		Dog d = findDogById(dogs, pay.getDogId());
    		mainContainer.getChildren().add(Components.sponsoredDogView(d, stage, appData.getPosters(), appData, sponsoredDogsScene));
    	}

		stage.show();

	}	


    public static void applyBounceAnimation(Button button) {
        // Create Timeline for the animation
        Timeline timeline = new Timeline();

        // Define keyframes for the animation
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, new KeyValue(button.translateYProperty(), 0)),
                new KeyFrame(Duration.seconds(0.5), new KeyValue(button.translateYProperty(), -50)),
                new KeyFrame(Duration.seconds(1), new KeyValue(button.translateYProperty(), 0))
        );

    /*    // Set cycle count to indefinite for continuous bouncing
        timeline.setCycleCount(Timeline.INDEFINITE);
	*/
        timeline.setCycleCount(5);
        button.setOnAction(e -> {
            // Play the animation when the button is clicked
            timeline.playFromStart();
        });
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