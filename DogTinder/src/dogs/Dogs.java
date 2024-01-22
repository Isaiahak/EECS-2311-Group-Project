package dogs;

import java.util.List;

import breeds.Breed;
import breeds.Breeds;

public class Dogs {
	
	private String name;
	private Breeds breed;
	private int age;
	private EnergyLevel energyLevel;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Breed> getBreed() {
		return breed.getBreeds();
	}
	public void setBreed() {
		
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public EnergyLevel getEnergyLevel() {
		return energyLevel.getEnergyLevel();
	}
	public void setEnergyLevel(EnergyLevel energyLevel) {
		energyLevel.setEnergyLevel();
	}
	
	

}
