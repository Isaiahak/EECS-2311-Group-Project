package backend.user;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

import backend.dog.Dog;
import backend.dog.trait.*;
import backend.tag.Tag;


public class User {
	
	private Hashtable<Integer,Tag> tagPreferences = new Hashtable<Integer,Tag>();

	private ArrayList<Attribute> agePreferences = new ArrayList<Attribute>();

	private ArrayList<Attribute> sizePreferences  = new ArrayList<Attribute>() ;

	private ArrayList<Attribute> sexPreferences  = new ArrayList<Attribute>();

	private ArrayList<Attribute> energyLevelPreferences  = new ArrayList<Attribute>();
	
	private String username;
	
	private String email;
	
	private int userID;
	
	private String password;
	
	private Wallet wallet;
	
	private ArrayList<Dog> likedDogs = new ArrayList<Dog>();
	
	private ArrayList<Dog> passedDogs = new ArrayList<Dog>();
	

	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public User(String username, String email, String password) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	/*
	 * For adding and for removing attribute/tag preferences
	 */

	public int getUserID() {
		return userID;
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	public ArrayList<Dog> getLikedDogs() {
		return likedDogs;
	}

	public void addLikedDogs(Dog likedDog) {
		likedDogs.add(likedDog);
	}
	
	public ArrayList<Dog> getPassedDogs() {
		return passedDogs;
	}

	public void addPassedDogs(Dog likedDog) {
		passedDogs.add(likedDog);
	}

	public ArrayList<Attribute> getSizePreferences() {
		return sizePreferences;
	}

	public ArrayList<Attribute> getEnergyLevelPreferences() {
		return energyLevelPreferences;
	}

	public ArrayList<Attribute> getSexPreferences() {
		return sexPreferences;
	}

	public ArrayList<Attribute> getAgePreferences() {
		return agePreferences;
	}

	public void setTagPreferences(Hashtable<Integer,Tag> tagPreferences) {
		this.tagPreferences = tagPreferences;
	}

	public void setSizePreferences(ArrayList<Attribute> sizePreferences) {
		this.sizePreferences=sizePreferences;
	}

	public void setAgePreferences(ArrayList<Attribute> agePreferences) {
		this.agePreferences=agePreferences;
	}

	public void setSexPreferences(ArrayList<Attribute> sexPreferences) {
		this.sexPreferences=sexPreferences;
	}

	public void setEnergyLevelPreferences(ArrayList<Attribute> energyLevelPreferences) {
		this.energyLevelPreferences=energyLevelPreferences;
	}

	public Hashtable<Integer, Tag> getTagPreferences() {
		return tagPreferences;
	}
	
	public boolean arePreferencesEqual(ArrayList<Attribute> sex, ArrayList<Attribute> age, ArrayList<Attribute> size, ArrayList<Attribute> energyLevel, Hashtable<Integer, Tag> tags) { // check if tags and attributes are the same 	
    	// compare 'old' tags/preferences (provided in parameters) to current user's to detect any change
		
    	Hashtable<Integer,Tag> currTags = this.getTagPreferences(); 
    	Hashtable<Integer,Tag> oldTags = tags;
    	Set<Integer >oldKeys = oldTags.keySet();
    	Set<Integer >currKeys = currTags.keySet();
    	
    	if(currKeys.size() != oldKeys.size()) return false;
    	
	    	
    	for(int key : oldKeys) {
    		if(!currTags.containsKey(key)) {
    			return false;
    		}
    	}
    	
    	for(int key : currKeys) {
    		if(!oldTags.containsKey(key)) {
    			return false;
    		}
    	}
    	
    	
    	if(!this.agePreferences.equals(age)|| !this.sexPreferences.equals(sex)  // check if attrubutes have changed 
    			|| !this.sizePreferences.equals(size)|| !this.energyLevelPreferences.equals(energyLevel)) {
    		return false;
    	}
    	
		return true;
    }

	@Override
	public String toString() {
		return "User [username=" + username + ", email=" + email + ", password="
				+ password + "]";
	}
}
