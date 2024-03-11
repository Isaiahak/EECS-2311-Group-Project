package backend.poster;

import java.util.*;

import backend.dog.Dog;

public class Poster implements Comparable<Poster> {
private int score; 
private String displayName;
private int uniqueId;
private String phone; 
private String email; 
private ArrayList<Dog> dogList = new ArrayList<Dog>(); 


public int getScore() {
	return score;
}
public  Poster(int score, String displayName, int uniqueId, String phone, String email) {
	this.score = score;
	this.displayName = displayName;
	this.uniqueId = uniqueId;	
	this.phone = phone;
	this.email = email;
}

public String getEmail() {
	return this.email;
	
}

public String getPhone() {
	return this.phone;
}
	

public void setDogList(ArrayList<Dog> dogList) {
	//add a dog to the poster's dog list
	this.dogList = dogList;
	
}

public void setScore(int score) {
	this.score = score;
}
public String getDisplayName() {
	return displayName;
}
public void setDisplayName(String displayName) {
	this.displayName = displayName;
}
public int getUniqueId() {
	return uniqueId;
}
public void setUniqueId(int uniqueId) {
	this.uniqueId = uniqueId;
}
@Override
public String toString() {
	return "Poster [score=" + score + ", displayName=" + displayName + ", uniqueId=" + uniqueId + "]";
}
@Override
public int compareTo(Poster o) {
	if (Integer.compare(this.uniqueId,o.uniqueId) != 0) {
        return Integer.compare(this.uniqueId,o.uniqueId);
    }
    else if (this.displayName.compareTo(o.displayName)!=0) {
      //compare name if id is equal
        return this.displayName.compareTo(o.displayName);
    }
    else //compare score if both id and name are equal
    	return Integer.compare(this.score, o.score);
}



}