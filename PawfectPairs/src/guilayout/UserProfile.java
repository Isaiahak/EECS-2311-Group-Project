package guilayout;

import backend.dog.trait.Attribute;
import backend.calendar.AppointmentManager;
import backend.database.Database;
import backend.wallet.Wallet;
import guicontrol.Authenticator;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Optional;

import backend.tag.Tag;
import backend.user.User;

import static guilayout.Components.showAlert;


public class UserProfile extends PrimaryScene{
	private LoginScene loginScene;
	private ArrayList<Attribute> oldAgePreferences;
	private ArrayList<Attribute> oldSexPreferences;
	private ArrayList<Attribute> oldSizePreferences;
	private ArrayList<Attribute> oldEnergyLevelPreferences;
	private Hashtable<Integer, Tag> oldTagPreferences;
	private Wallet wallet;
	double SingleMaxWalletDepositLimit=1000;//in dollars
	double ValueSelectedInScrollBar=0;
	private static UserProfile instance;
	private        Authenticator authenticator;

	public static UserProfile getInstance() {
		if (instance == null) {
			instance = new UserProfile();		
		}
		return instance;
	}

	private UserProfile() {

	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		Components.updateCurrentScene("userProfile");

		initailizePrimaryScene(primaryStage);

		HashMap<Integer, Tag> tags = appData.getallTags();
		HashMap<Integer,ArrayList<Attribute>> allAttributes = appData.getAllAttributes();
		wallet=user.getWallet();

		oldSexPreferences = user.getCopyOfSexPreferences(user.getSexPreferences());
		oldAgePreferences = user.getCopyOfAgePreferences(user.getAgePreferences());
		oldEnergyLevelPreferences = user.getCopyOfEnergyLevelPreferences(user.getEnergyLevelPreferences());
		oldSizePreferences = user.getCopyOfSizePreferences(user.getSizePreferences());
		oldTagPreferences = user.getCopyOfTagPreferences(user.getTagPreferences());

		VBox preferences = new VBox();
		preferences.setAlignment(javafx.geometry.Pos.CENTER);

		Label preferencesTitle = Components.mediumLabel("Tags", Pos.CENTER);


		preferences.getChildren().addAll(
				preferencesTitle
				);

		GridPane tagsGrid =  Components.createTags(tags,user);
		tagsGrid.setAlignment(javafx.geometry.Pos.CENTER);

		VBox attributes = new VBox();
		attributes.setAlignment(javafx.geometry.Pos.CENTER);

		Label attributesTitle = Components.mediumLabel("Attributes", Pos.CENTER);

		Label sexAttributesTitle = Components.smallLabel("Sex", Pos.BASELINE_LEFT);
		GridPane sexAttributeGrid = Components.createAttribute(user.getSexPreferences(), 1,allAttributes);
		sexAttributeGrid.setAlignment(javafx.geometry.Pos.CENTER);   	

		Label sizeAttributesTitle = Components.smallLabel("Size",  Pos.BASELINE_LEFT);
		GridPane sizeAttributeGrid = Components.createAttribute(user.getSizePreferences(), 3,allAttributes);
		sizeAttributeGrid.setAlignment(javafx.geometry.Pos.CENTER);   	

		Label energyLevelAttributesTitle = Components.smallLabel("EnergyLevel",  Pos.BASELINE_LEFT);
		GridPane energyLevelAttributeGrid = Components.createAttribute(user.getEnergyLevelPreferences(),2,allAttributes);
		energyLevelAttributeGrid.setAlignment(javafx.geometry.Pos.CENTER);   	 	

		Label ageAttributesTitle = Components.smallLabel("Age",  Pos.BASELINE_LEFT);
		GridPane ageAttributeGrid = Components.createAttribute(user.getAgePreferences(), 0,allAttributes);
		ageAttributeGrid.setAlignment(javafx.geometry.Pos.CENTER);   	


		loginScene = LoginScene.getInstance();


		attributes.getChildren().addAll(
				attributesTitle,
				sizeAttributesTitle,
				sizeAttributeGrid,
				sexAttributesTitle,
				sexAttributeGrid,
				ageAttributesTitle,
				ageAttributeGrid,
				energyLevelAttributesTitle,
				energyLevelAttributeGrid
				);



		Button deposit = Components.button("Deposit funds into your wallet");

		Label currentFunds =Components.mediumLabel();
		currentFunds.setText("Your current balance: "+ wallet.getBalance());

		TextField amount = new TextField();

		deposit.setOnAction(event -> {
			boolean isValid=Components.checkInput(amount.getText());
			if (!isValid)
			{
				showAlert("Cannot enter non-numeric values ", "Please enter a number", AlertType.ERROR);
				amount.clear();
				amount.setText("");}

			else 
			{
				try {
					boolean result = (BigDecimal.valueOf(Double.valueOf(amount.getText())).scale() > 2);
					if(!result) {
						wallet.deposit(Double.parseDouble(amount.getText()));
						currentFunds.setText("Your current balance "+ wallet.getBalance());
					}
					else{
						showAlert("invalid dollar amount","please try entering a correct dollar amount.",Alert.AlertType.ERROR);
					}


				} catch (IllegalArgumentException e) {
					// TODO: handle exception
					showAlert("Cannot enter a negative number", "Please enter a non-negative number", AlertType.ERROR);
					amount.clear();
				}


			}

		});

		Button signOutButton = new Button("sign out");

		//Sign out button
		signOutButton.setOnMouseClicked(event -> {
			Database.onApplicationClose(user, allDogs, appData.getAppointmentManager(), appData.getOkToClose());
			appData.setOkToClose(false);//Prevent double saving with a null dog list
			user.reset();
			appData.reset();
			//appData.addToOtherUsersAppointments(appData.getAppointmentManager());
			//appData.setAppointmentManagerToEmpty();
//			System.out.println(appData.getAppointmentManager().toString());

			loginScene.start(primaryStage);
		});

		VBox allWalletUserComponents = new VBox();
		allWalletUserComponents.setSpacing(30);
		allWalletUserComponents.getChildren().addAll( amount, currentFunds, deposit);
		allWalletUserComponents.setAlignment(Pos.TOP_CENTER);
		// Set padding for the VBox (top, right, bottom, left)
		allWalletUserComponents.setPadding(new javafx.geometry.Insets(20, 10, 20, 10));


		Button changeUsernamePassword = new Button("Change username \nand password?");
		changeUsernamePassword.getStyleClass().add("user-profile-page-buttons");
		deposit.getStyleClass().add("user-profile-page-buttonss");



		changeUsernamePassword.setOnAction(e -> {// Create a text field
			TextField username = new TextField();

			TextField password = new TextField();
			Label outcome=new Label();
			Label placeholder = new Label();


			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			VBox textInputforPopUp = initialInputs( username, password, alert);
			alert.getDialogPane().setContent(textInputforPopUp);
			authenticator= new Authenticator(placeholder);


			check ( username,  password, alert,  textInputforPopUp);
		});

		//END OF WALLET UI STUFF
		mainContainer.getChildren().addAll(
				preferences,
				tagsGrid,
				attributes,
				allWalletUserComponents,changeUsernamePassword,
				signOutButton
				);

		String css = this.getClass().getResource("/style.css").toExternalForm();
		primaryStage.getScene().getStylesheets().add(css);
		primaryStage.show();


	}
	private void check(TextField username, TextField password, Alert alert, VBox textInputforPopUp) {
		// Show the alert and wait for the user response
	//	alert.getDialogPane().setContent(textInputforPopUp);

		Optional<ButtonType> result = alert.showAndWait();

		// Check if the user has made a choice
		if (result.isPresent()) {
			// Proceed if the user clicked OK
			if (result.get() == ButtonType.OK) {
				// Authenticate user input
				if (authenticator.authenticateLogIn(username.getText(), password.getText())&&username.getText().equals(user.getUsername())) {
					System.out.println("authentis" + authenticator.authenticateLogIn(username.getText(), password.getText()));
					System.out.println("same user? "+username.getText().equals(user.getUsername()));
					// If authentication is successful, proceed with success input
					successInput(username, password, alert, textInputforPopUp);
//					textInputforPopUp.getChildren().addAll(username, password);
					//alert.getDialogPane().setContent(textInputforPopUp);
				} else {
					// If authentication fails, prompt for valid input
					ResetFields(username, password);
					alert.setTitle("The entered username and/or password was incorrect");
					alert.setHeaderText("Please enter your valid username and password:");
					alert.getDialogPane().setContent(textInputforPopUp);
					// Recursively call check to handle new input
					check(username, password, alert, textInputforPopUp);
					
				}
			}
		} else {
			// If the user closes the dialog without choosing an option, close the alert
			alert.close();
		}
	}
	private void successInput(TextField username, TextField password, Alert alert, VBox textInputforPopUp) {
	    // Set title and header for the alert
	    alert.setTitle("Changing your username and password");
	    alert.setHeaderText("Now enter your new desired username and password:");
	    // Set content of the alert to the input fields
	    alert.getDialogPane().setContent(textInputforPopUp);
	    
	    // Show the alert again to get new inputs
	    Optional<ButtonType> result = alert.showAndWait();
	    if (result.isPresent() && result.get() == ButtonType.OK) {
	        // Process input if user clicks OK
	    	//System.out.println("authentic sign up" + authenticator.authenticateLogIn(username.getText(), password.getText()));
			System.out.println("same user? "+username.getText().equals(user.getUsername()));
	        if (authenticator.checkFieldsAreValid(username.getText(), password.getText())&&(appData.getAllUsernames().contains(username.getText())&&!username.getText().equals(user.getUsername()))) {
	            // If authentication is successful, create a success dialog
	            Alert resultAlert = new Alert(Alert.AlertType.INFORMATION);
	            // Create UI elements for the success dialog
	            Button showPassword = new Button("👁");
	            Label loginInfo = new Label("Your new username and password are:\nusername: " + username.getText() + "\npassword: " +
	                    hidePassword(password.getText()));
	            resultAlert.setTitle("Change Successful");
	            resultAlert.setHeaderText("Your username and password have been changed successfully ");
	            HBox updatedLogin = new HBox();
	            updatedLogin.getChildren().addAll(loginInfo, showPassword);
	            resultAlert.getDialogPane().setContent(updatedLogin);
	            // Toggle password visibility when button is clicked
	            showPassword.setOnMouseClicked(event -> {
	                if (loginInfo.getText().contains("*")) {
	                    loginInfo.setText("Your new username and password are:\nusername: " + username.getText() + "\npassword: " + password.getText());
	                } else {
	                    loginInfo.setText("Your new username and password are:\nusername: " + username.getText() + "\npassword: " + hidePassword(password.getText()));
	                }
	            });
	            // Show the success dialog
	            resultAlert.showAndWait();
	           //update username and password 
	            Database.updateUsernamePassword(username.getText(), password.getText(), user.getUserID());
	            user.setUsername(username.getText());
	            user.setPassword(password.getText());
	        } else {
	            // If authentication fails, allow user to try again
	            ResetFields(username, password);
	            alert.setTitle("The entered username and/or password were invalid");
	            alert.setHeaderText("Please enter valid options for username and password:");
	            // Call successInput recursively to handle new input
	            successInput(username, password, alert, textInputforPopUp);
	        }
	    } else {
	        // If user cancels, close the alert
	        alert.close();
	    }
	}
	private String hidePassword (String pswd) {
		StringBuilder toStars = new StringBuilder();
		for(int i=0;i<pswd.length();i++)
		{
			toStars.append("*");

		}
		return toStars.toString();
	}
	private VBox initialInputs (TextField username,TextField password, Alert alert) {
		username.setPromptText("Enter username");
		password.setPromptText("Enter password");
		// Create a VBox to hold the text field
		VBox textInputforPopUp = new VBox();
		textInputforPopUp.getChildren().addAll(username, password);

		alert.setTitle("Changing your username and password");
		alert.setHeaderText("Please enter your old username and password:");

		return textInputforPopUp;
	}

	private void ResetFields (TextField username, TextField password) {

		username.clear();
		password.clear();
		username.setPromptText("Enter username");
		password.setPromptText("Enter password");
	}
	public ArrayList<Attribute> getOldEnergyLevelPreferences() {
		return oldEnergyLevelPreferences;
	}

	public ArrayList<Attribute> getOldSizePreferences() {
		return oldSizePreferences;
	}

	public ArrayList<Attribute> getOldSexPreferences() {
		return oldSexPreferences;
	}

	public ArrayList<Attribute> getOldAgePreferences() {
		return oldAgePreferences;
	}

	public Hashtable<Integer, Tag> getOldTagPreferences(){
		return oldTagPreferences;
	}
}
