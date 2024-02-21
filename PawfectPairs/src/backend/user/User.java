package backend.user;

import java.util.ArrayList;

import backend.dog.Attribute;
import backend.dog.Dog;
import backend.tag.Tag;

public class User {
	
	private ArrayList<Tag> tagPreferences =  new ArrayList<Tag>();
	
	private ArrayList<Attribute> attributePreferences = new ArrayList<Attribute>();
	
	private String username;
	
	private String email;
	
	private String password;
	
	private Wallet wallet;
	
	private ArrayList<Dog> likedDogs = new ArrayList<Dog>();
	
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

	public ArrayList<Attribute> getAttributePreferences() {
		return attributePreferences;
	}
	
	public void addAttributePreference(Attribute att) {
		this.attributePreferences.add(att);
	}
	public void removeAttributePreference(Attribute att) {
		this.tagPreferences.remove(att);
	}

	public void setAttributePreferences(ArrayList<Attribute> preferences) {
		this.attributePreferences = preferences;
	}
	
	public ArrayList<Tag> getTagPreferences() {
		return tagPreferences;
	}
	
	public void addTagPreference(Tag tag) {
		this.tagPreferences.add(tag);
	}
	public void removeTagPreference(Tag tag) {
		this.tagPreferences.remove(tag);
	}

	public void setTagPreferences(ArrayList<Tag> preferences) {
		this.tagPreferences = preferences;
	}

	
	/*********************************/
	
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
	
	@Override
	public String toString() {
		return "User [username=" + username + ", email=" + email + ", password="
				+ password + "]";
	}
	
	
	

}
