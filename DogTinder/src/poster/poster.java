package poster;

import java.util.*;

import dogs.Dog;
public class poster {
private int score; 
private String displayName;
private String uniqueId;

private ArrayList<Dog> dogs;

public int getScore() {
	return score;
}
public poster(int score, String displayName, String uniqueId, ArrayList<Dog> dogs) {
	super();
	this.score = score;
	this.displayName = displayName;
	this.uniqueId = uniqueId;
	this.dogs=dogs;
}
public ArrayList<Dog> getDogs() {
	return dogs;
}
public void setDogs(ArrayList<Dog> dogs) {
	this.dogs = dogs;
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
public String getUniqueId() {
	return uniqueId;
}
public void setUniqueId(String uniqueId) {
	this.uniqueId = uniqueId;
}
}
