package breeds;

import java.util.List;

public class Breeds {
	
	protected List<Breed> breeds;
	
	public List<Breed> getBreeds() {
		return breeds;
	}
	
	public void setBreeds(Breed breed) {
		this.breeds.add(breed);
	}
}
