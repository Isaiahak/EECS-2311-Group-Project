package guicontrol;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import backend.calendar.AppointmentManager;
import backend.database.Database;
import backend.user.User;
import guilayout.DogProfileScene;
import guilayout.LoginScene;
import javafx.scene.control.Label;

public class Authenticator {
	private AppData appData;
	private LoginScene loginScene;
	private Label message;
	
	public  Authenticator(Label message) {
		this.message = message;
	}
	
	private boolean containsInvalidCharacters(String str) {
		String regex = "[^a-zA-Z0-9\\-_]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        return matcher.find();
	}
	
	private boolean checkFieldsAreValid(String username, String password){
		if(username.isEmpty() || username.equals("")) {
			setLoginPageMessage("Username is empty.", "red");
			System.out.println("empty user");
			return false;
		}else if(password.isEmpty() || password.equals("")) {
			setLoginPageMessage("Password is empty.", "red");
			System.out.println("empty pass");
			return false;
		}
		else if(containsInvalidCharacters(username)) {
			setLoginPageMessage("Username contains invalid characters. Username must contain only alphanumeric characters, or - and _", "red");return false;
		}
		else if(containsInvalidCharacters(password)) {
			setLoginPageMessage("Password contains invalid characters. Password must contain only alphanumeric characters, or - and _", "red");return false;
		}
		return true;
	}

	public boolean authenticateSignUp(String username, String password) {
		if(checkFieldsAreValid(username, password)) {
			if(Database.usernameChecker(username).equals(username)) {
				setLoginPageMessage("Sign up failed. Username in use, try logging in!", "red");
				return false;
			}
			else {
				setLoginPageMessage("Your account was created successfully!", "green");
				return true;
			}
		}
		return false;
	}

	public boolean authenticateLogIn(String username, String password) {
		 
		if(checkFieldsAreValid(username, password)) {
			if(Database.usernameChecker(username).compareTo(username) != 0) {
				setLoginPageMessage("Log in failed. That user doesn't exist.", "red");
				return false;
			}else if(Database.passwordChecker(username, password).compareTo(username) != 0) {
				setLoginPageMessage("Log in failed. Password is incorrect.", "red");
				return false;
			}
			else {
				setLoginPageMessage("Logging in!", "green");
				
				return true;
			}
		}

		return false;
	}
	
	private void setLoginPageMessage(String string, String color){
		this.message.setText(string);
		this.message.setId(color);
	}

}
