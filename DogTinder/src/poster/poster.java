package poster;

import java.util.*;

public class poster {
private int score; 
private String displayName;
private String uniqueId;
//TODO:add list of "dog" object


public int getScore() {
	return score;
}
public poster(int score, String displayName, String uniqueId) {
	super();
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
public String getUniqueId() {
	return uniqueId;
}
public void setUniqueId(String uniqueId) {
	this.uniqueId = uniqueId;
}
}
