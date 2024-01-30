package user;

import java.util.List;

import dogs.Dogs;

public class User {
	
	private List<Tags> preferences;
	
	private String username;
	
	private String email;
	
	private String password;
	
	private Wallet wallet;
	
	private List<Dogs> likedDogs;

	
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

	public List<Dogs> getLikedDogs() {
		return likedDogs;
	}

	public void setLikedDogs(List<Dogs> likedDogs) {
		this.likedDogs = likedDogs;
	}
	

}
