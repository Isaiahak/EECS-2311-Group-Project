package guicontrol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.PriorityQueue;

import backend.database.Database;
import backend.dog.Dog;
import backend.poster.Poster;
import backend.tag.Tag;
import backend.user.User;

public class AppData {
	
	private User user;
	private Hashtable<Integer, PriorityQueue<Dog>> dogProfiles;
	private HashMap<Integer, Tag> allTags;
	private Hashtable<Integer,Poster> posterProfiles; // poster profiles by id 
	
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

	public  Hashtable<Integer, PriorityQueue<Dog>> getDogProfiles() {
		return this.dogProfiles;
	}

	public void setDogProfiles() {
		
		this.dogProfiles = Database.getAllDogs(user, this.posterProfiles.keySet());
	}
	
	public HashMap<Integer,Tag> getallTags(){
		return this.allTags;
	}
	
	public void setAllTags(){
		this.allTags = Database.getAllTags();
	}
	
	public void setPosters() {
		// create all local poster objects, set their dogs in their dog list using na aggregate relationship	
		this.posterProfiles = Database.getAllPosters();
		
		
		// loop through dogProfiles and add to posters
		for(Poster p : this.posterProfiles.values()) {
			p.setDogList(this.dogProfiles.get(p.getUniqueId()));
		}
	}
	
	public void updateDogPriority() {
		// update dog list to factor in score changes
		
		
		
		
	}
	
	public PriorityQueue<Dog> initializeDogProfilesSorted() {  // to be optimized
		
		PriorityQueue<Dog> dogList = new PriorityQueue<Dog>();
		
		dogList.mergeAll(dogProfiles); 
		
	}
	
	
	
	public void onStart(String user, String pass) {
		
		getInstance(); 
		
		setUser(user, pass); // sets user, dog liked list, ideal dog attribtues
		
		setPosters();
		
		setDogProfiles(); 
		
		setAllTags(); 
		
	
	}
	
}
