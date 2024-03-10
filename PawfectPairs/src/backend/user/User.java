package backend.user;

import java.util.ArrayList;
import java.util.Hashtable;

import backend.dog.Dog;
import backend.dog.trait.*;
import backend.tag.Tag;


public class User {
	
	private TagPreferences tagPreferences;

	private AgePreferences agePreferences;

	private SizePreferences sizePreferences;

	private SexPreferences sexPreferences;

	private EnergyLevelPreferences energyLevelPreferences;
	
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
		return sizePreferences.getPreferences();
	}

	public ArrayList<Attribute> getEnergyLevelPreferences() {
		return energyLevelPreferences.getPreferences();
	}

	public ArrayList<Attribute> getSexPreferences() {
		return sexPreferences.getPreferences();
	}

	public ArrayList<Attribute> getAgePreferences() {
		return agePreferences.getPreferences();
	}

	public void setTagPreferences(TagPreferences tagPreferences) {
		this.tagPreferences = tagPreferences;
	}

	public void setSizePreferences(SizePreferences sizePreferences) {
		this.sizePreferences = sizePreferences;
	}

	public void setAgePreferences(AgePreferences agePreferences) {
		this.agePreferences = agePreferences;
	}

	public void setSexPreferences(SexPreferences sexPreferences) {
		this.sexPreferences = sexPreferences;
	}

	public void setEnergyLevelPreferences(EnergyLevelPreferences energyLevelPreferences) {
		this.energyLevelPreferences = energyLevelPreferences;
	}

	public Hashtable<Integer, Tag> getTagPreferences() {
		return tagPreferences.getTagPreferences();
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", email=" + email + ", password="
				+ password + "]";
	}
}
