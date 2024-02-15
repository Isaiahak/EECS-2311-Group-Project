package DogTinder;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;

public class PosterList {
	private TreeMap<String, Poster> PosterMap = new TreeMap<>();
    
    public void addPoser(String name, Poster poster) {
    	PosterMap.put(name, poster);
    }
    
    public TreeMap<String, Poster> getDogMap() {
        return PosterMap;
    }
    
    public Poster getPoster(String name, TreeMap<String, Dog> DogMap) {
    	
        return PosterMap.get(name);
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
