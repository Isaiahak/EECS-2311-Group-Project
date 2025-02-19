package backend.user;

import java.util.*;

import backend.calendar.AppointmentManager;
import backend.dog.Dog;
import backend.dog.trait.*;
import backend.poster.Poster;
import backend.tag.Tag;
import backend.wallet.Wallet;
import guicontrol.AppData;


public class User {
	
	private Hashtable<Integer,Tag> tagPreferences = new Hashtable<Integer,Tag>();
	private ArrayList<Attribute> agePreferences = new ArrayList<Attribute>();
	private ArrayList<Attribute> sizePreferences  = new ArrayList<Attribute>() ;
	private ArrayList<Attribute> sexPreferences  = new ArrayList<Attribute>();
	private ArrayList<Attribute> energyLevelPreferences  = new ArrayList<Attribute>();
	private ArrayList<Poster> postersRatedByUser = new ArrayList<Poster>();
	private ArrayList<Dog> localAdoptedDog = new ArrayList<Dog>();
	private AppointmentManager bookedDates = new AppointmentManager(getUserID(), new ArrayList<>());
	private String username;
	private int userID;
	private String password;
	private Wallet wallet;
	private ArrayList<Dog> likedDogs = new ArrayList<Dog>();
	private ArrayList<Dog> passedDogs = new ArrayList<Dog>();
	private Dog lastRemovedDog;
	private Dog undoDog;
	
	
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;

	}
	
	public void reset() {
		setUsername(null);
		setPassword(null);
		this.likedDogs.clear();
		this.passedDogs.clear();
	}
	
	public void addToAdoptedDogs(Dog dog) {
		this.localAdoptedDog.add(dog);
	}


	public ArrayList<Dog> getAdoptedDogs(){
		return this.localAdoptedDog; 
	}
	
	public void addToPostersRatedByUser(Poster poster) {
		this.postersRatedByUser.add(poster);
	}


	public ArrayList<Poster> getPostersRatedByUser(){
		return this.postersRatedByUser; 
	}
	
	public void setPosterRatedByUser (ArrayList<Poster> postersRatedByUser) {
		this.postersRatedByUser=postersRatedByUser;
	}
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
	
	public void removeUnlikedDog(Dog dog) {
		likedDogs.remove(dog);
	}
	
	public ArrayList<Dog> getPassedDogs() {
		return passedDogs;
	}

	public void addPassedDogs(Dog likedDog) {
		passedDogs.add(likedDog);
	}

	public ArrayList<Attribute> getSizePreferences() {
		return this.sizePreferences;
	}

	public ArrayList<Attribute> getEnergyLevelPreferences() {
		return this.energyLevelPreferences;
	}

	public ArrayList<Attribute> getSexPreferences() {
		return this.sexPreferences;
	}

	public ArrayList<Attribute> getAgePreferences() {
		return this.agePreferences;
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
	
	public Dog getLastRemovedDog() {
		return lastRemovedDog;
	}

	public void setLastRemovedDog(Dog lastRemovedDog) {
		this.lastRemovedDog = lastRemovedDog;
	}
	
	
	
	public Dog getUndoDog() {
		return undoDog;
	}

	public void setUndoDog(Dog undoDog) {
		this.undoDog = undoDog;
	}

	public boolean arePreferencesEqual(Hashtable<Integer, Tag> tags) {
		
    	Hashtable<Integer,Tag> currTags = this.tagPreferences;
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
		return true;
    }

	public boolean areAttributesEqual(ArrayList<Attribute> sex, ArrayList<Attribute> age, ArrayList<Attribute> size, ArrayList<Attribute> energyLevel){
		if(!this.agePreferences.equals(age) || !this.sexPreferences.equals(sex)  // check if attrubutes have changed
				|| !this.sizePreferences.equals(size)|| !this.energyLevelPreferences.equals(energyLevel)) {
			return false;
		}
		return true;
	}

	
	public ArrayList<Attribute> getCopyOfPreferences(ArrayList<Attribute> preferences){
		ArrayList<Attribute> returnPreferences = new ArrayList<>();
		for(Attribute att : preferences){
			returnPreferences.add(att.cloneAttribute());
		}
		return returnPreferences;
	}


	public Hashtable<Integer,Tag> getCopyOfTagPreferences(Hashtable<Integer,Tag> TagPreferences){
		Hashtable<Integer,Tag> preferences = new Hashtable<>();
		Collection<Tag> tags = TagPreferences.values();
		for(Tag tag : tags){

			preferences.put(tag.getTagId(),(new Tag(tag.getTagName(),tag.getTagId())));

		}
		return preferences;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", email=" + null + ", password="
				+ password + "]";
	}
}
