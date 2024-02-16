package dog;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;

public class DogList {
private TreeMap<String, Dog> dogMap = new TreeMap<>();
    
    public void addDog(String name, Dog dog) {
        dogMap.put(name, dog);
    }
    
    public TreeMap<String, Dog> getDogMap() {
        return dogMap;
    }
    
    public Dog getDog(String name) {
        return dogMap.get(name);
    }

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
        sb.append("DogList [");
        
        // Get the set of keys from the TreeMap
        Set<String> keys = dogMap.keySet();
        
        // Iterate over the keys and append them to the StringBuilder
        for (String key : keys) {
            sb.append(key).append(", ");
        }
        
        // Remove the trailing ", " and close the string
        if (!dogMap.isEmpty()) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("]");
        
        return sb.toString();
	}
    
    
    
}
