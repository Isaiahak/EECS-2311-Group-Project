package guicontrol;

import java.util.Optional;

import backend.database.Database;
import backend.user.User;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ChangeProfileDetails {
static User user;
static Authenticator authenticator;


	public ChangeProfileDetails(User user, Authenticator authenticator) {
	super();
	this.user = user;
	this.authenticator = authenticator;
}

	public static boolean otherUsersUsername (String username, User user) {
		return Database.usernameChecker(username).equals(username)&&!username.equals(user.getUsername());
	}
	
	public static void check(TextField username, TextField password, Alert alert, VBox textInputforPopUp) {
		// Show the alert and wait for the user response
	//	alert.getDialogPane().setContent(textInputforPopUp);

		Optional<ButtonType> result = alert.showAndWait();

		// Check if the user has made a choice
		if (result.isPresent()) {
			// Proceed if the user clicked OK
			if (result.get() == ButtonType.OK) {
				// Authenticate user input
				if (authenticator.authenticateLogIn(username.getText(), password.getText())&&username.getText().equals(user.getUsername())) {
					// If authentication is successful, proceed with success input
					successInput(username, password, alert, textInputforPopUp);
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
	

	public static VBox initialInputs (TextField username,TextField password, Alert alert) {
		username.setPromptText("Enter username");
		password.setPromptText("Enter password");
		// Create a VBox to hold the text field
		VBox textInputforPopUp = new VBox();
		textInputforPopUp.getChildren().addAll(username, password);

		alert.setTitle("Changing your username and password");
		alert.setHeaderText("Please enter your old username and password:");

		return textInputforPopUp;
	}

	private static void ResetFields (TextField username, TextField password) {

		username.clear();
		password.clear();
		username.setPromptText("Enter username");
		password.setPromptText("Enter password");
	}
	private static void successInput(TextField username, TextField password, Alert alert, VBox textInputforPopUp) {
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
			//System.out.println("same user? "+username.getText().equals(user.getUsername()));
			
	        if (authenticator.checkFieldsAreValid(username.getText(), password.getText())&&!otherUsersUsername(username.getText(),user)) {
	            // If authentication is successful, create a success dialog
	            Alert resultAlert = new Alert(Alert.AlertType.INFORMATION);
	            // Create UI elements for the success dialog
	            Button showPassword = new Button("👁");
	            Label loginInfo = new Label("Your new username and password are:\nusername: " + 
	            username.getText() + "\npassword: " +
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
	public static String hidePassword (String pswd) {
		StringBuilder toStars = new StringBuilder();
		for(int i=0;i<pswd.length();i++)
		{
			toStars.append("*");

		}
		return toStars.toString();
	}
}
