package DogTinder;

import java.util.*;

public class Poster {
private int score; 
private String displayName;
private int uniqueId;

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
@Override
public String toString() {
	return "Poster [score=" + score + ", displayName=" + displayName + ", uniqueId=" + uniqueId + "]";
}


}
