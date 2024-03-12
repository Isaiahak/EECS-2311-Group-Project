package guilayout;

import java.util.PriorityQueue;

import backend.dog.Dog;
import backend.poster.Poster;
import backend.wallet.RecurringPayment;
import backend.wallet.Wallet.FundsTooLow;
import guicontrol.AppData;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DonateScene extends PrimaryScene {
	//	double SingleMaxWalletDepositLimit=1000;//in dollars
	//	double ValueSelectedInScrollBar=0;
	private static DonateScene instance;
	private Dog currentDog;
	private Label currentFunds;

	ComboBox<String>  howOftenBox;
	TextField howMuchMoney;

	public static DonateScene getInstance() {
		if (instance == null)
			instance = new DonateScene();
		return instance;
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void setCurrentDog(Dog d) {
		this.currentDog = d;
	}

	@Override
	public void start(Stage stage) {
		initailizePrimaryScene();
		HBox navTab = Components.navTab(userProfileScene, likedDogsScene, dogProfileScene, sponsoredDogsScene, bookedAppointmentsScene, stage,"likeddogs", appData);


		//wallet=appData.getWallet();
		//root is v box
		VBox root = new VBox();
		root.setSpacing(10);
		root.setAlignment(Pos.CENTER);
		wallet=user.getWallet();


		stage.setTitle("Pawfect Pairs");
		user = appData.getUser();		

		Label dogLabel = Components.largeLabel(currentDog.getName() + " is thankful for you ♥", Pos.CENTER); 
		ImageView image = Components.imageView(500,500); 
		image.setImage(new Image(currentDog.getImagePath()));

		Label howMuch = Components.mediumLabel("How much would you like to donate?", Pos.CENTER);
		howMuchMoney = new TextField();

		Label howOften = Components.mediumLabel("How often would you like to donate?", Pos.CENTER);
		howOftenBox = new ComboBox<>(FXCollections.observableArrayList("Once", "Weekly", "Biweekly", "Monthly"));

		// Optional: Set a default value
		howOftenBox.setValue("Once");


		currentFunds = Components.largeLabel("Current balance: " + String.format("%.2f",  wallet.getBalance()), Pos.CENTER);

		Button donateButton = new Button("Donate");

		donateButton.setOnAction(event -> {
			try {

				makePayment(appData, howOftenBox.getValue());

			} catch (FundsTooLow e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});


		root.getChildren().addAll(
				navTab,
				dogLabel,
				image,
				currentFunds,
				howMuch,
				howMuchMoney,
				howOften,
				howOftenBox,
				donateButton
				);

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
		StackPane stackPane = new StackPane(root);
		stackPane.setAlignment(javafx.geometry.Pos.CENTER);

		ScrollPane scrollPane = new ScrollPane(stackPane);

		scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scrollPane.setFitToWidth(true);

		Scene scene = new Scene(scrollPane, Components.screenWidth, Components.screenHeight);

		stage.setScene(scene);
		stage.setTitle("Pawfect Pairs");
		//		stage.setMaximized(true);
		stage.show();
	}

	private void makePayment(AppData appdata, String duration) throws FundsTooLow {
		String inputText = howMuchMoney.getText().trim();
		boolean Numberwithdecimal = false;
//		try
//		{
//			Double num = Double.parseDouble(inputText);
//			Numberwithdecimal=true;
//		}
//		catch(java.lang.NumberFormatException e)
//		
//		{ 
//			Numberwithdecimal=false;
//		    System.out.println("Error parsing as Double: " + e.getMessage());
//
//			
//		}
		String whyFalse="";
		int numofDecimalPoint=0;
		 for (char c : inputText.toCharArray()) {
		      if (Character.isDigit(c)) {
		    	  Numberwithdecimal= true;
		    	  whyFalse+="not a digit";
		    	  
		      }
		      else if(c == '.') {
		    	  Numberwithdecimal=true;
		      numofDecimalPoint++;
		      }
		      
		   }
		 if (numofDecimalPoint>1) {
			 Numberwithdecimal=false;
	    	  whyFalse+="more than one .";

			 }

		//if (!inputText.matches("\\d+(\\.\\d+)?")) {
		   if (!Numberwithdecimal)
				   {showAlert("Cannot enter non-numeric values ", "Please enter a number", AlertType.ERROR);

	    howMuchMoney.clear();
	    howMuchMoney.setText("");}
	
		else if (Double.parseDouble(howMuchMoney.getText())<=0)
			{
			showAlert("Cannot enter a negative number", "Please enter a non-negative number", AlertType.ERROR);
			howMuchMoney.clear();
			}
		else {//do the payments otherwise
		double amountToDonate = Double.parseDouble(howMuchMoney.getText()); 
		Poster poster = appData.getPosterProfiles().get(currentDog.getPosterId());
		int daysBetweenPayments = 0;


		switch(duration) {
		case "Weekly":
			daysBetweenPayments = 7;
			break;

		case "BiWeekly":
			daysBetweenPayments = 14;
			break;

		case "Monthly":
			daysBetweenPayments = 30;
			break;

		default:
			break;

		}
		if (amountToDonate>wallet.getBalance())
			showAlert("Insufficient funds", "Your recurring payment did not go through", AlertType.ERROR);
		else	{
			if(!howOftenBox.getValue().equals("Once")) {
				if(wallet.getRecurringPayments().containsKey(currentDog.getId()))
					{
					showAlert("Recurring Payment Updated", "Your existing recurring payment was updated for this dog", AlertType.INFORMATION);
					wallet.removeRecurringPayment(currentDog.getId());
					wallet.addRecurringPayment(new RecurringPayment(amountToDonate, daysBetweenPayments, currentDog.getId(), currentDog.getPosterId()));

					}
				else
					wallet.addRecurringPayment(new RecurringPayment(amountToDonate, daysBetweenPayments, currentDog.getId(), currentDog.getPosterId()));
					showAlert("Recurring Payment Created", "You have set up recurring payments for this dog", AlertType.INFORMATION);

			}
			
			wallet.donate(amountToDonate, poster);
			showAlert("Payment went through",currentDog.getName()+" is thankful for you! ♥", AlertType.CONFIRMATION);
		}
		


		currentFunds.setText("Your current balance "+String.format("%.2f",  wallet.getBalance()));
		//System.out.println("poster"+poster.getDisplayName() +"'s balance is"+poster.getBalance());//was for testing

		}
	}
	private void showAlert(String title, String message, Alert.AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);


		alert.showAndWait();
	}
}