package guilayout;

import java.time.LocalDate;
import java.util.ArrayList;

import backend.dog.Dog;
import backend.poster.Poster;
import backend.user.User;
import backend.poster.*;
import backend.wallet.Wallet;
import backend.wallet.Wallet.FundsTooLow;
import guicontrol.AppData;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DonateScene<Textfield> extends PrimaryScene {
	ArrayList<Dog> posterDogs;
	User user;
	Wallet wallet;
	Poster poster;
	double SingleMaxWalletDepositLimit=1000;//in dollars
	double ValueSelectedInScrollBar=0;
	private static DonateScene instance;
	
	public static DonateScene getInstance() {
		if (instance == null)
			instance = new DonateScene();
		return instance;
	}
	private DonateScene() {
		
	}
	
	public static void main(String[] args) {
        launch(args);
    }

	@Override
	public void start(Stage stage) {
    	initailizePrimaryScene();
    	//wallet=appData.getWallet();
    	//root is v box
		VBox root = new VBox();
		root.setSpacing(10);
		root.setAlignment(Pos.CENTER);
    	wallet=user.getWallet();


        stage.setTitle("Pawfect Pairs");
        user = AppData.getInstance().getUser();		
//		//posterDogs = AppData.getInstance().getDogProfiles();//TEMP
//		
//		 
//	//	wallet = new Wallet(Database.getUserFunds(user.getUserID()), false, 0, user.getUserID(), Database.getUserRecurringAmount(user.getUserID()));//need to set up with db call to start
//		//wallet = AppData.getInstance().getWallet();
//		wallet=user.getWallet();
//		VBox root = new VBox();
////		root.setSpacing(15);
//		root.setAlignment(Pos.TOP_CENTER);
//		LikedDogScene likedDog = LikedDogScene.getInstance();
//		UserProfile userProfile = UserProfile.getInstance();
//	    DonateScene donatePage = DonateScene.getInstance();//ADDED DONATE PAGE
//
//		stage.setTitle("Pawfect Pairs");	
//		
//		//HBox navTab = Components.navTab(userProfile, likedDog, DogProfileScene.getInstance(), stage);
//    	HBox navTab = Components.navTab(userProfile, LikedDogScene.getInstance(), dogProfileScene, stage,"likedDogs", appData);
//
//	//	Poster poster = ;
//
//
//		
//		
//		Button donate = Components.button("Donate");
//		ScrollBar scrollBar = new ScrollBar();
//        //scrollBar.setStyle("-fx-pref-width: 1;");
//       scrollBar.setStyle("-fx-pref-height: 20;");
//
//        Label valueLabel =Components.mediumLabel();
//        valueLabel.setText("Selected Value: ");
//        VBox scrollContainer = new VBox();   
//        scrollContainer.setMargin(scrollBar, new Insets(20, 100, 20, 100));  // top, right, bottom, left
//        scrollContainer.getChildren().addAll(scrollBar);
//        // Set the range of the scroll bar
//        scrollBar.setMin(0);
//        scrollBar.setMax(SingleMaxWalletDepositLimit);//for now arbitrarily set max single deposit limit to 1000
//        
//        Label currentFunds =Components.mediumLabel();
//        currentFunds.setText("Your current balance: "+ wallet.getBalance());
//        // Add listener to capture value changes
//        scrollBar.valueProperty().addListener((observable, oldValue, newValue) -> {
//            valueLabel.setText("Selected Value: " + String.format("%.2f", newValue));
//            ValueSelectedInScrollBar=Double.parseDouble(String.format("%.2f", newValue));
//        });
//
//        donate.setOnAction(event -> {
//        				//wallet.deposit(ValueSelectedInScrollBar);
//        				try {
//							wallet.donate(ValueSelectedInScrollBar, wallet.getPosterToSponsorPending());
//							//wallet.setPosterToSponsorPending(null);
//						} catch (FundsTooLow e) {
//							// TODO Auto-generated catch block
//							showAlert(e.getMessage(), "Can't execute transaction");
//							e.printStackTrace();
//						}
//        				currentFunds.setText("Your current balance "+ wallet.getBalance());
//
//
//        	});
//
//        
//TextField howOften = new TextField("Enter how often you want payments to be made");
//       // howOften.setText("");
//howOften.setPrefWidth(200);
//
//TextField recurringAmount = new TextField("Enter the amount of money for recurring payments");
//recurringAmount.setPrefWidth(200);
//
//		Button redo = Components.button("Set up Recurring Payment");
//		redo.setOnAction(event -> {
//				//public void redo (double amount, int frequency, int id ) {
//			wallet.setRecurringPayment(true);
////wallet.setFrequency(60);//just set 45 s for testing
//			wallet.setFrequency(Integer.valueOf(howOften.getText()));
//			wallet.setRecurringAmount(Integer.valueOf(recurringAmount.getText()));
//currentFunds.setText("Your current balance "+ wallet.getBalance());
//
//				//wallet.redo(20, wallet.getFrequency(), 2);// just set donate value to 20
//LocalDate initialPaid= LocalDate.now().minusDays(Integer.valueOf(howOften.getText()));
//wallet.doRecurringPayment(Integer.valueOf(recurringAmount.getText()),initialPaid,  Integer.valueOf(howOften.getText()), wallet.getPosterToSponsorPending());
//wallet.setRecurringPoster(wallet.getPosterToSponsorPending());
//				currentFunds.setText("Your current balance "+ wallet.getBalance());
//
//		});
//		
//	
//		  ToggleButton toggleButton = new ToggleButton("Toggle Switch");
//
//	        // Add an event handler to handle the toggle action
//	        toggleButton.setOnAction(e -> {
//	            if (toggleButton.isSelected()) {
//	                System.out.println("Switch is ON");
//	                // Add your code for when the switch is ON
//	                wallet.setRecurringPayment(true); 
//	                System.out.println("true");
//
//	            } else {
//	                System.out.println("Switch is OFF");
//	                // Add your code for when the switch is OFF
//	                wallet.setRecurringPayment(false); 
//	                System.out.println("false");
//	            }
//	        });
//	
//		//Label Posterfunds = Components.mediumLabel("Poster funds");
//	        VBox SinglePayment = new VBox();
//	        SinglePayment.setSpacing(15);
//	        SinglePayment.setAlignment(Pos.TOP_CENTER);
//
//	        SinglePayment.getChildren().addAll(navTab, scrollContainer, valueLabel, currentFunds,donate);
//
//	        VBox SetUpRecurringPayment = new VBox();
//	        SetUpRecurringPayment.setSpacing(15);
//	        SetUpRecurringPayment.setAlignment(Pos.TOP_CENTER);
//
//	       // SetUpRecurringPayment.getChildren().addAll(howOften, toggleButton, redo);
//	        SetUpRecurringPayment.getChildren().addAll(howOften, recurringAmount,redo);
//
//	        root.setSpacing(60);
//	        root.getChildren().addAll(SinglePayment, SetUpRecurringPayment);
//	      //  root.getChildren().addAll(howOften);
//	      
//	        StackPane stackPane = new StackPane(root);
//		stackPane.setAlignment(javafx.geometry.Pos.CENTER);
//		Scene scene = new Scene(stackPane, Components.screenWidth, Components.screenHeight);		 
//		stage.setScene(scene);
//		stage.show();
//
//		
////		stage.setOnCloseRequest(event -> {
////    	    System.out.println("Window is closing. Perform cleanup if needed.");
////    	    
////    	    Database.onApplicationClose(user, posterDogs);
////    	});
		
	}
	  private void showAlert(String title, String message) {
	        Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle(title);
	        alert.setHeaderText(null);
	        alert.setContentText(message);
	        alert.showAndWait();
	    }
}