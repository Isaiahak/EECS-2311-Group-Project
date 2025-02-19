package backend.dog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import java.sql.*;

import backend.dog.trait.Age;
import backend.dog.trait.EnergyLevel;
import backend.dog.trait.Sex;
import backend.dog.trait.Size;
import backend.poster.Poster;
import backend.tag.Tag;

// download this: import javax.Persistence.api;
public class Dog  implements Comparable<Dog>{
	 	private String name;
	    private int id;
	    private Age age;
	    private EnergyLevel energyLevel ; // possibly do a class + implement a comparable interface ?
	    private Size size; // class + implement comparable?
	    private Sex sex; // M or F // maybe do 0 or 1?
//	    private Poster poster;
	    private int posterId;
	    private boolean adopted;
	    private String imagePath; 
	    private int oldScore; // used to help optimise sorting algorithms, and identify which elemnets have been changed
	    private int score; //score calculated based on intersection of this dog's tags with ideal dog's tags
	    private String biography;
	    private Hashtable<Integer, Tag> tags = new Hashtable<Integer, Tag>();    // tags are stored by their ids
		
	    public Dog(int dogSize, int dogEnergyLevel){ //why do we have this here? //its a constructor that does not set the other attributes
	        setSize(new Size(dogSize));
	        setEnergyLevel(new EnergyLevel(dogEnergyLevel));
	    }

	    public Dog(String name, int id, int age, int energyLevel, int size, int sex, int posterId, boolean adopted, String imagePath, String biography) {
	        this.name = name;
	        this.imagePath = imagePath; 
	        this.id = id;
	        setAge(new Age(age));
	        setSex(new Sex(sex));
	        setSize(new Size(size));
	        setEnergyLevel(new EnergyLevel(energyLevel));

	        this.posterId = posterId; 
	        this.adopted = adopted;
	        this.biography = biography;

	    }

	    public Dog(Dog dog) { //copy constructor (for ideal dog)
	    	this.name = dog.getName();
	        this.id = dog.getId();
	       
	        setAge(new Age(dog.getAge().getWeight()));
	        setSex(new Sex(dog.getSex().getWeight()));
	        setSize(new Size(dog.getSize().getWeight()));
	        setEnergyLevel(new EnergyLevel(dog.getEnergyLevel().getWeight()));
	        
	        Hashtable<Integer,Tag> tags = new Hashtable<Integer, Tag>();
	        
	        for(Tag t : dog.getTags().values()) {
	        	// copy
	        	tags.put(t.getTagId(), t);
	        }
	        
	        setTags(tags);
	    	
	    }

	    public Dog(String name, int id, int age, int energyLevel, int size, int sex) {// ideal dog constructor
	        this.name = name;
	        this.id = id;
	        setAge(new Age(age));
	        setSex(new Sex(sex));
	        setSize(new Size(size));
	        setEnergyLevel(new EnergyLevel(energyLevel));
	    }

	    public int calculateScore(Hashtable<Integer, Tag> tags) {
	    	int scoreCalc = 0 ;
	    	Set<Integer> currDogTags = this.tags.keySet();
	    	for(int key : currDogTags) {
	    		if(tags.containsKey(key)) {
	    			scoreCalc++;
	    		}
	    	}
	    	this.score = scoreCalc;
	    	return scoreCalc;
	    }

	    public void setOldScore(int score) {
	    	this.oldScore = score; 
	    }

	    public int getOldScore() {
	    	return this.oldScore;
	    }

	    public int getScore() {return this.score;}

	    public String getImagePath() {
	    	return this.imagePath;
	    }

		public void setImagePath(String path){
			this.imagePath = path;
		}
	    public Hashtable<Integer, Tag> getTags() {
	        return tags;
	    }

	    public void setTags(Hashtable<Integer, Tag> tags) {
	        this.tags = tags;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public Age getAge() {
	        return age;
	    }

	    public void setAge(Age age) {
	        this.age = age;
	    }

	    public EnergyLevel getEnergyLevel() {
	    	
	        return energyLevel;
	    }

	    public void setEnergyLevel(EnergyLevel energyLevel) {
	        this.energyLevel = energyLevel;
	    }

	    public Size getSize() {
	    	
	        return size;
	    }

	    public void setSize(Size size) {
	        this.size = size;
	    }

	    public Sex getSex() {
	        return sex;
	    }

	    public void setSex(Sex sex) {
	        this.sex = sex;
	    }

		public int getPosterId() {
			// TODO Auto-generated method stub
			return this.posterId;
		}

	    public boolean getAdopted() {
			return adopted;
		}

		public void setAdopted(boolean adopted) {
			this.adopted = adopted;
		}

		public void setPosterId(int posterId) {
			this.posterId = posterId;
		}

		public String getBiography() {
			return this.biography;
		}

		public void setBiography(String bio){
			this.biography = bio;
		}
		@Override
	    public String toString() {
	        StringBuilder stringBuilder = new StringBuilder();
	        stringBuilder.append("Dog: ").append(this.getName()).append("\n");
	        stringBuilder.append("ID: ").append(this.getId()).append("\n");
	        stringBuilder.append("Age: ").append(this.getAge()).append("\n");
	        stringBuilder.append("Energy Level: ").append(this.getEnergyLevel()).append("\n");
	        stringBuilder.append("Size: ").append(this.getSize()).append("\n");
	        stringBuilder.append("Sex: ").append(this.getSex()).append("\n");
	        stringBuilder.append("Adopted: ").append(this.getAdopted()).append("\n");
	        stringBuilder.append("Tags:\n");
	        
//	        for (Tag tag : this.getTags()) {
//	            stringBuilder.append("- ").append(tag.getTagName()).append(": ").append(tag.getWeight()).append("\n");
//	        }
	        return stringBuilder.toString();
	    }

		@Override
		public boolean equals(Object o) { // for checking if two dogs are the same
			if(this.getId() == ((Dog) o).getId())
				return true;
			return false;
		    }

		@Override
		public int compareTo(Dog o) { // for priorityqueue
			return Integer.compare(o.score,this.score);
		}
}


