package guilayout;

import backend.calendar.AppointmentManager;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import backend.database.Database;
import backend.dog.Dog;
import backend.user.User;
import guicontrol.AppData;
import guicontrol.Authenticator;


public class LoginScene extends Application{
	private Authenticator authenticator = new Authenticator(); 

	private static LoginScene instance;
	AppData appData;
	ArrayList<User> userlist = new ArrayList<User>();
	
	public static LoginScene getInstance() {
		if (instance == null) {
			instance = new LoginScene();		
		}
		return instance;
	}

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	DogProfileScene dogProfileScene = DogProfileScene.getInstance();
        primaryStage.setTitle("PawfectPairs");
        int width = 900;
		int height = 900;

        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label userLabel = new Label("Username:");
        TextField userTextField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Button signUpButton = new Button("Sign Up");
        Button loginButton = new Button("Login");
        Button changeUsernamePassword = new Button("Change username \nand password?");
        
        
//        String css = this.getClass().getResource("/style.css").toExternalForm();
//    	primaryStage.getScene().getStylesheets().add(css);
        
        signUpButton.getStyleClass().add("login-page-buttons");
        loginButton.getStyleClass().add("login-page-buttons");
        
        grid.add(userLabel, 0, 0);
        grid.add(userTextField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(signUpButton, 0, 2);
        grid.add(loginButton, 1, 2);
        grid.add(changeUsernamePassword, 2, 2);
        appData = AppData.getInstance();    
        appData.initializeAttributes(); 
                
        signUpButton.setOnAction(e -> {
            String username = userTextField.getText();
            String password = passwordField.getText();
            boolean result = Database.addUser(username, password, appData.getAllAttributes());
            if ( password == "" && username == "")
                showAlert("Sign up failed","Empty username or password not allowed!");
            else if (Database.usernameChecker(username) != username && result == false)
                showAlert("Sign up failed","Username in use, try logging in!");
            else {
                User user = Database.getUser(username, password);
                appData.setAppointmentManager(new AppointmentManager(user.getUserID(), new ArrayList<>()));
            }
            clearFields(userTextField, passwordField);
        });

        changeUsernamePassword.setOnAction(e -> {// Create a text field
            TextField username = new TextField();
            username.setPromptText("Enter username");

            TextField password = new TextField();
            password.setPromptText("Enter password");

            // Create a VBox to hold the text field
            VBox textInputforPopUp = new VBox();
            textInputforPopUp.getChildren().addAll(username, password);

            // Create a new Alert
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Changing your username and password");
            alert.setHeaderText("Please enter your old username and password:");
            alert.getDialogPane().setContent(textInputforPopUp);

            // Show the alert and wait for the user response
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                   /* // Retrieve the text entered by the user
                    String inputText = textField.getText();
                    // Display the input text in another alert
                    Alert resultAlert = new Alert(Alert.AlertType.INFORMATION);
                    resultAlert.setTitle("Input Text");
                    resultAlert.setHeaderText("You entered:");
                    resultAlert.setContentText(inputText);
                    resultAlert.showAndWait();
                    */
                	 alert.setTitle("Changing your username and password");
                     alert.setHeaderText("Now enter your new desired username and password:");
                     alert.getDialogPane().setContent(textInputforPopUp);
                }
            });
        });

        loginButton.setOnAction(e -> {
            String username = userTextField.getText();
            String password = passwordField.getText();
            appData.onStart(username, password);
            if (username == "" && password == "" ) {
                showAlert("Login failed","Empty username or password not allowed!");
            }
            else if (Database.usernameChecker(username).compareTo(username) != 0){
                showAlert("Login failed","invalid username");
            }
            else if (Database.passwordChecker(username,password).compareTo(username) != 0){
                showAlert("Login failed", "incorrect password");
            }
            else {
                System.out.println("Logging the user in!");
                dogProfileScene.start(primaryStage);
            }
            clearFields(userTextField, passwordField);
        });

        Scene scene = new Scene(grid, width, height);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void clearFields(TextField usernameField, PasswordField passwordField) {
        usernameField.clear();
        passwordField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
