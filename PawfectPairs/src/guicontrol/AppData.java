package guicontrol;

import java.util.ArrayList;

import backend.database.Database;
import backend.dog.Dog;
import backend.user.User;

public class AppData {
	
	private User user;
	private ArrayList<Dog> dogProfiles;
	private static AppData instance;
	
	public static AppData getInstance() {
	if (instance == null)
		instance = new AppData();
	return instance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(String user,String password) {
		Database.getUser(user, password);
	}

	public ArrayList<Dog> getDogProfiles() {
		return dogProfiles;
	}

	public void setDogProfiles() {
		this.dogProfiles = Database.getAllDogs();
	}
	

}
