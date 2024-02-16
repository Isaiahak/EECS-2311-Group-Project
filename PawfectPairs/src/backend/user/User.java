package backend.user;

import java.util.ArrayList;

import backend.dog.Dog;
import backend.tag.Tag;

public class User {
	
	private ArrayList<Tag> preferences =  new ArrayList<Tag>();
	
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

	public ArrayList<Tag> getPreferences() {
		return preferences;
	}

	public void setPreferences(ArrayList<Tag> preferences) {
		this.preferences = preferences;
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

	public void setLikedDogs(ArrayList<Dog> likedDogs) {
		this.likedDogs = likedDogs;
	}
	
	 public ArrayList<Tag> getTags() {
	        return preferences;
	    }

	@Override
	public String toString() {
		return "User [username=" + username + ", email=" + email + ", password="
				+ password + "]";
	}
	
	
	

}
