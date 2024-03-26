package guilayout;

import backend.calendar.AppointmentManager;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
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
	private Authenticator authenticator;

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
    	
    	Label message = Components.mediumLabel();
    	
    	DogProfileScene dogProfileScene = DogProfileScene.getInstance();
        primaryStage.setTitle("PawfectPairs");
        authenticator = new Authenticator(message);
		VBox mainContainer = new VBox();
		mainContainer.setSpacing(Components.screenHeight * 0.02);
		HBox root = new HBox(mainContainer);
		root.setAlignment(Pos.CENTER);
		this.appData = appData.getInstance();
		appData.initializeAttributes();
		
		
		double paddingX = Components.screenWidth * 0.2; // 20% of screen width
		double paddingY = Components.screenHeight * 0.2; // 20% of screen height


     	mainContainer.setAlignment(Pos.CENTER);

        Label userLabel = Components.mediumLabel("Username:", Pos.CENTER);
        TextField userTextField = new TextField();
        HBox usernameFieldAndLabel = new HBox(userLabel, userTextField);
        usernameFieldAndLabel.setSpacing(0.01 * Components.screenWidth);
        usernameFieldAndLabel.setAlignment(Pos.CENTER);
        
        Label passwordLabel = Components.mediumLabel("Password:", Pos.CENTER);
        PasswordField passwordField = new PasswordField();
        HBox passwordFieldAndLabel = new HBox(passwordLabel, passwordField);
        passwordFieldAndLabel.setSpacing(0.01 * Components.screenWidth);
        passwordFieldAndLabel.setAlignment(Pos.CENTER);
        VBox textFields = new VBox(usernameFieldAndLabel,
        		passwordFieldAndLabel);
        
        textFields.setAlignment(Pos.CENTER);
        Button signUpButton = new Button("Sign Up");
        Button loginButton = new Button("Login");
        HBox buttons = new HBox(signUpButton, loginButton);
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(0.05 * Components.screenWidth);
        
        signUpButton.getStyleClass().add("login-page-buttons");
        loginButton.getStyleClass().add("login-page-buttons");

        mainContainer.getChildren().addAll(
        		message,
        		textFields,
        		buttons

        		);
       
        signUpButton.setOnAction(e -> {
        	String username = userTextField.getText();
            String password = passwordField.getText();
            
            if(authenticator.authenticateSignUp(username, password)) {
            	Database.addUser(username, password, appData.getAllAttributes());
            	User user = Database.getUser(username, password);
            	appData.setAppointmentManager(new AppointmentManager(user.getUserID(), new ArrayList<>()));
            };
        	
        	clearFields(userTextField, passwordField);
        });

        loginButton.setOnAction(e -> {
            String username = userTextField.getText();
            String password = passwordField.getText();
            
            if(authenticator.authenticateLogIn(username, password)) {
            	appData.onStart(username, password); 
		appData.setOkToClose(true);
            	dogProfileScene.start(primaryStage);
            }
            clearFields(userTextField, passwordField);
        });

        Scene scene = new Scene(root, Components.screenWidth, Components.screenHeight);
        primaryStage.setScene(scene);        
        String css = this.getClass().getResource("/style.css").toExternalForm();
    	primaryStage.getScene().getStylesheets().add(css);
        primaryStage.show();
            
    }

    private void clearFields(TextField usernameField, PasswordField passwordField) {
        usernameField.clear();
        passwordField.clear();
    }

}
