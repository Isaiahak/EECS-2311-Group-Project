package guicontrol;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.PriorityQueue;
import java.util.TreeSet;

import backend.database.Database;
import backend.dog.Dog;
import backend.poster.Poster;
import backend.tag.Tag;
import backend.user.User;
import guilayout.UserProfile;

public class AppData {
	
	private User user;
	private Hashtable<Integer, ArrayList<Dog>> dogProfileHashtable;
	private HashMap<Integer, Tag> allTags;
	private Hashtable<Integer,Poster> posterProfiles; // poster profiles by id 
	private ArrayList<Dog> sortedDogProfiles;
	
	private static AppData instance;
	
	public ArrayList<Dog> getSortedDogProfiles() {
		
		
		return sortedDogProfiles;
	}

	public void setSortedDogProfiles(ArrayList<Dog> sortedDogProfiles) {
		this.sortedDogProfiles = sortedDogProfiles;
	}

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

	public  Hashtable<Integer, ArrayList<Dog>> getDogProfiles() {
		return this.dogProfileHashtable;
	}

	public void setDogProfiles() {
		
		this.dogProfileHashtable = Database.getAllDogs(user, this.posterProfiles.keySet());
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

	}
	
	public Hashtable<Integer, Poster> getPosters(){
		return posterProfiles;
	}
	
	
	// calculate dog scores
	public void updateDogScores() {
	// perform check on if the user's preferences have changed before updating scores	
	if(!this.user.getDog().arePreferencesEqual(UserProfile.getInstance().getOldPreferences())) { 
		
		for(Dog d : this.sortedDogProfiles) {
			d.calculateScore(user.getDog().getTags());

		}
		// re-sort dogs
		Collections.sort(this.sortedDogProfiles);
		

		
			
	}
	
	}
	
	public void initializeDogProfilesSorted() {  // to be optimized
		ArrayList<Dog> dogList = new ArrayList<Dog>();
		
		for (ArrayList<Dog> dogs : dogProfileHashtable.values()) {
			dogList.addAll(dogs);
		}
		

		
		setSortedDogProfiles(dogList);

	}

	public void setPosterDogLists() {
		// loop through dogProfiles and add to posters
		for(Poster p : this.posterProfiles.values()) {
			p.setDogList(this.dogProfileHashtable.get(p.getUniqueId()));

		}
	}
	
	public void onStart(String user, String pass) {
		getInstance(); 	
		
		setUser(user, pass); // sets user, dog liked list, ideal dog attribtues
		
		setAllTags();
		
		setPosters();
		
		setDogProfiles(); 
		
		setPosterDogLists();
		
		initializeDogProfilesSorted();
		

	}
	
}
