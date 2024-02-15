package DogTinder;

import java.util.*;

public class Poster {
private int score; 
private String displayName;
private int uniqueId;

private TreeMap<String, Dog> dogPosterMap = new TreeMap<>();
//TODO:add list of "dog" object


public int getScore() {
	return score;
}
public  Poster(int score, String displayName, int uniqueId) {
	//super(); //why did we add super here?
	this.score = score;
	this.displayName = displayName;
	this.uniqueId = uniqueId;
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

public TreeMap <String, Poster> getPosterDogs () {
	//Iterate through the Dog Tree Map and if poster is the Poster we want then you save the values in a new list
	TreeMap<String, Dog> posterDogs = new TreeMap<>();
    
    for (Dog dog : dogMap.values()) {
        if (dog.getPoster().equals(desiredPoster)) {
            posterDogs.put(dog.getName(), dog);
        }
}
}
