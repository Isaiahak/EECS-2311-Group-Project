package backend.poster;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;

import backend.database.Database;
import backend.dog.Dog;

public class PosterList {
	private TreeMap<String, Poster> PosterMap = new TreeMap<>();
	
    
    public void addPoster(String name, Poster poster) {//name of poster and poster object
    	PosterMap.put(name, poster);
    }
    
    public TreeMap<String, Poster> getDogMap() {
        return PosterMap;
    }
    
    public Poster getPoster(String name) {
        return PosterMap.get(name);
    }
    
    public TreeMap<String, Dog> getPosterDogs(Poster poster, TreeMap<String, Dog> dogMap) {
    	TreeMap<String, Dog> PosterDogsMap = new TreeMap<>();
    
    	for (Dog dog : dogMap.values()) {
         //   if (Database.getPosterById(dog.getPosterId()).equals(poster)) { //implement Comparable Interface: ->finished comparable interface for poster
                PosterDogsMap.put(dog.getName(), dog);
            }
    	return PosterDogsMap;
    	}
        
    

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
        sb.append("PosterList [");
        
        // Get the set of keys from the TreeMap
        Set<String> keys = PosterMap.keySet();
        
        // Iterate over the keys and append them to the StringBuilder
        for (String key : keys) {
            sb.append(key).append(", ");
        }
        
        // Remove the trailing ", " and close the string
        if (!PosterMap.isEmpty()) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("]");
        
        return sb.toString();
	}
    
    
    
}