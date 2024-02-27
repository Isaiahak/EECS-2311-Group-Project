package guicontrol;

import java.util.ArrayList;

import backend.database.Database;
import backend.dog.Dog;
import backend.tag.Tag;
import backend.user.User;

public class AppData {
	
	private User user;
	private ArrayList<Dog> dogProfiles;
	private ArrayList<Tag> allTags;
	private static AppData instance;
	
	public static AppData getInstance() {
	if (instance == null)
		instance = new AppData();
	return instance;
	}
	
	public void setUser(String user, String password) {
		this.user = Database.getUser(user, password);
	}
	
	public User getUser() {
		return this.user;
	}

	public ArrayList<Dog> getDogProfiles() {
		return this.dogProfiles;
	}

	public void setDogProfiles() {
		this.dogProfiles = Database.getAllDogs(user.getUserID());
	}
	
	public ArrayList<Tag> getallTags(){
		return this.allTags;
	}
	
	public void setAllTags(){
		this.allTags = Database.getAllTags();
		}
	
	
	
}
