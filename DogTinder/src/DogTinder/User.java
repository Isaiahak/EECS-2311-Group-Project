package DogTinder;

import java.util.ArrayList;

//import dogs.Dog;
//import dogs.Tag;

public class User {
	
	private ArrayList<Tag> preferences;
	
	private String username;
	
	private String email;
	
	private String password;
	
	private Wallet wallet;
	
	private ArrayList<Dog> likedDogs;

	
	/*public List<Tags> getPreferences() {
		return preferences;
	}*/

	/*public void setPreferences(List<Tags> preferences) {
		this.preferences = preferences;
	}*/

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
	

}
