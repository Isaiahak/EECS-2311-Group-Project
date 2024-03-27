package backend.user;

import java.util.*;

import backend.calendar.AppointmentManager;
import backend.dog.Dog;
import backend.dog.trait.*;
import backend.tag.Tag;
import backend.wallet.Wallet;
import guicontrol.AppData;


public class User {
	
	private Hashtable<Integer,Tag> tagPreferences = new Hashtable<Integer,Tag>();
	private ArrayList<Attribute> agePreferences = new ArrayList<Attribute>();
	private ArrayList<Attribute> sizePreferences  = new ArrayList<Attribute>() ;
	private ArrayList<Attribute> sexPreferences  = new ArrayList<Attribute>();
	private ArrayList<Attribute> energyLevelPreferences  = new ArrayList<Attribute>();
	private ArrayList<Integer> postersRatedByUser = new ArrayList<Integer>();
	private AppointmentManager bookedDates = new AppointmentManager(getUserID(), new ArrayList<>());
	private String username;
	private String email;
	private int userID;
	private String password;
	private Wallet wallet;
	private ArrayList<Dog> likedDogs = new ArrayList<Dog>();
	private ArrayList<Dog> passedDogs = new ArrayList<Dog>();
	
	
	
	
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
	
	public void addToPostersRatedByUser(int posterId) {
		this.postersRatedByUser.add(posterId);
	}


	public ArrayList<Integer> getPostersRatedByUser(){
		return this.postersRatedByUser; 
	}
	
	public void setPosterRatedByUser (ArrayList<Integer> postersRatedByUser) {
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
	
	public boolean arePreferencesEqual(Hashtable<Integer, Tag> tags) {
		
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
		return true;
    }

	public boolean areAttributesEqual(ArrayList<Attribute> sex, ArrayList<Attribute> age, ArrayList<Attribute> size, ArrayList<Attribute> energyLevel){
		if(!this.agePreferences.equals(age) || !this.sexPreferences.equals(sex)  // check if attrubutes have changed
				|| !this.sizePreferences.equals(size)|| !this.energyLevelPreferences.equals(energyLevel)) {
			return false;
		}
		return true;
	}

	public ArrayList<Attribute> getCopyOfAgePreferences(ArrayList<Attribute> agePreferences){
		ArrayList<Attribute> preferences = new ArrayList<>();
		for(Attribute att : agePreferences){
			preferences.add(new Age(att.getWeight()));
		}
		return preferences;
	}

	public ArrayList<Attribute> getCopyOfSizePreferences(ArrayList<Attribute> SizePreferences){
		ArrayList<Attribute> preferences = new ArrayList<>();
		for(Attribute att : SizePreferences){
			preferences.add(new Age(att.getWeight()));
		}
		return preferences;
	}

	public ArrayList<Attribute> getCopyOfSexPreferences(ArrayList<Attribute> SexPreferences){
		ArrayList<Attribute> preferences = new ArrayList<>();
		for(Attribute att : SexPreferences){
			preferences.add(new Age(att.getWeight()));
		}
		return preferences;
	}

	public ArrayList<Attribute> getCopyOfEnergyLevelPreferences(ArrayList<Attribute> EnergyLevelPreferences){
		ArrayList<Attribute> preferences = new ArrayList<>();
		for(Attribute att : EnergyLevelPreferences){
			preferences.add(new Age(att.getWeight()));
		}
		return preferences;
	}

	public Hashtable<Integer,Tag> getCopyOfTagPreferences(Hashtable<Integer,Tag> TagPreferences){
		Hashtable<Integer,Tag> preferences = new Hashtable<>();
		Collection<Tag> tags = TagPreferences.values();
		for(Tag tag : tags){
			preferences.put(tag.getWeight(),(new Tag(tag.getTagName(),tag.getWeight())));
		}
		return preferences;
	}

	public ArrayList<Dog> getSponsoredDogs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", email=" + email + ", password="
				+ password + "]";
	}
}
