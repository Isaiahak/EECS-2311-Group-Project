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
import backend.dog.trait.Age;
import backend.dog.trait.Attribute;
import backend.dog.trait.EnergyLevel;
import backend.dog.trait.Sex;
import backend.dog.trait.Size;
import backend.poster.Poster;
import backend.tag.Tag;
import backend.user.User;
import guilayout.DogProfileScene;
import backend.wallet.Wallet;
import guilayout.UserProfile;
public class AppData {
	
	private User user;//comment

	private Hashtable<Integer, ArrayList<Dog>> dogProfileHashtable;
	private HashMap<Integer, Tag> allTags;
	private Hashtable<Integer,Poster> posterProfiles; // poster profiles by id 
	private ArrayList<Dog> sortedDogProfiles;
	private static AppData instance;
	
	private HashMap<Integer, ArrayList<Attribute>> allAttributes; 
	
	//Wallet Methods
	public void initializeWallet (int userid, String password) {
		this.user.setWallet(Database.getWallet(userid, password));
	}
	public Wallet getWallet () {
		return this.user.getWallet();
	}
	//
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
		if(this.user.arePreferencesEqual(UserProfile.getInstance().getOldTagPreferences()) == false){
			for(Dog d : this.sortedDogProfiles) {
				d.calculateScore(user.getTagPreferences());
			}
			// re-sort dogs
			Collections.sort(this.sortedDogProfiles);
		}
	}

	public void CheckIfAttributePreferencesHaveBeenChanged(){
		if(!this.user.areAttributesEqual(UserProfile.getInstance().getOldSexPreferences(),
				UserProfile.getInstance().getOldAgePreferences(),
				UserProfile.getInstance().getOldSizePreferences(),
				UserProfile.getInstance().getOldEnergyLevelPreferences())){
					DogProfileScene.getInstance().setCurrentProfileIndex(0);
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

	public void initializeAttributes(){
		this.allAttributes = new HashMap<Integer, ArrayList<Attribute>>();
		
		Attribute attEx = new Sex(0);
		ArrayList<Attribute> sexList = new ArrayList<Attribute>();
		for(int i = 0 ; i < attEx.getNames().length; i++) {
			sexList.add(new Sex(i));
			
		}
		this.allAttributes.put(attEx.getType(), sexList);
		
		attEx = new Size(0);
		ArrayList<Attribute> sizeList = new ArrayList<Attribute>();
		for(int i = 0 ; i < attEx.getNames().length; i++) {
			sizeList.add(new Size(i));
			
		}
		this.allAttributes.put(attEx.getType(), sizeList);
		
		attEx = new EnergyLevel(0);
		ArrayList<Attribute> energyLevelList = new ArrayList<Attribute>();
		for(int i = 0 ; i < attEx.getNames().length; i++) {
			energyLevelList.add(new EnergyLevel(i));
			
		}
		this.allAttributes.put(attEx.getType(), energyLevelList);
		
		attEx = new Age(0);
		ArrayList<Attribute> ageList = new ArrayList<Attribute>();
		for(int i = 0 ; i < attEx.getNames().length; i++) {
			ageList.add(new Age(i));
			
		}
		
		this.allAttributes.put(attEx.getType(), ageList);
		
	}
	public HashMap<Integer, ArrayList<Attribute>> getAllAttributes(){
		return this.allAttributes;
		
	}

	
	public void onStart(String user, String pass) {
		getInstance(); 	
		
		setUser(user, pass); // sets user, dog liked list, ideal dog attribtues
		
		initializeWallet(getUser().getUserID(), pass);
		
		setAllTags();
		
		setPosters();
		
		setDogProfiles(); 
		
		setPosterDogLists();
		
		initializeDogProfilesSorted();
		
		
		

	}
	
}
