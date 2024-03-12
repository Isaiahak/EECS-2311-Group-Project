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

	@Override
	public void start(Stage stage) {
		initailizePrimaryScene();
		HBox navTab = Components.navTab(userProfileScene, likedDogsScene, dogProfileScene, sponsoredDogsScene, bookedAppointmentsScene, stage,"likeddogs", appData);
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

		//		stage.setOnCloseRequest(event -> {
		//    	    System.out.println("Window is closing. Perform cleanup if needed.");
		//
		//    	    Database.onApplicationClose(user, posterDogs);
		//    	});
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

	public void setCurrentDog(Dog d) {
		this.currentDog = d;
	}
}