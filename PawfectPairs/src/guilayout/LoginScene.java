package guilayout;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import backend.user.User;


public class LoginScene extends Application{
	private User userInfo = new User("","");
	private static LoginScene instance;
	private List<User> userList = new ArrayList<>();
	
	public static LoginScene getInstance() {
		if (instance == null) {
			instance = new LoginScene();		
		}
		return instance;
	}
	private LoginScene() {
		
	}

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	DogProfileScene dogProfileScene = DogProfileScene.getInstance();
    	User userInfo = new User("","");
        primaryStage.setTitle("Login UI");
        int width = 900;
		int height = 900;

        // Create a GridPane layout
        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Add controls to the GridPane
        Label userLabel = new Label("Username:");
        TextField userTextField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Button signUpButton = new Button("Sign Up");
        Button loginButton = new Button("Login");

        grid.add(userLabel, 0, 0);
        grid.add(userTextField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(signUpButton, 0, 2);
        grid.add(loginButton, 1, 2);

        // Event handling for Sign Up button
        signUpButton.setOnAction(e -> {
            String username = userTextField.getText();
            String password = passwordField.getText();
            if (password == "" && username == "")
            	showAlert("Sign up Failed", "Please enter a valid username or password.");
            else {
            	userInfo.setUsername(username);
            	userInfo.setPassword(password);
            	userList.add(userInfo);
            }
            clearFields(userTextField, passwordField);
        });

        // Event handling for Login button
        loginButton.setOnAction(e -> {
            String username = userTextField.getText();
            String password = passwordField.getText();

            // Check if the entered credentials match any user in the list
            if (userList.stream().anyMatch(user -> user.getUsername().equals(username) && user.getPassword().equals(password)) && username != "" && password != "") {
                
				// Successful login, navigate to the main scene
                dogProfileScene.start(primaryStage);
            } else {
                // Display an alert for unsuccessful login
                showAlert("Login Failed", "Invalid username or password.");
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
    
    public User sendUserInfo() {
    return this.userInfo;
    }

}
