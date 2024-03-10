package backend.dog;
import java.util.ArrayList;
import java.sql.*;

import backend.dog.trait.Age;
import backend.dog.trait.EnergyLevel;
import backend.dog.trait.Sex;
import backend.dog.trait.Size;
import backend.poster.Poster;
import backend.tag.Tag;

// download this: import javax.Persistence.api;
public class Dog {
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
	    private String biography;
	    private ArrayList<Tag> tags = new ArrayList<Tag>();
	   
		
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
	        //this.age = age;
	        //this.energyLevel = energyLevel;
	        //this.size = size;
	        //this.sex = sex;
//	        this.poster = poster;
	        this.posterId = posterId; 
	        this.adopted = adopted;
	        this.biography = biography;
	    }
	    
	    public Dog(String name, int id, int age, int energyLevel, int size, int sex, int posterId, boolean adopted) {
	        this.name = name;
	        this.id = id;
	       
	        setAge(new Age(age));
	        setSex(new Sex(sex));
	        setSize(new Size(size));
	        setEnergyLevel(new EnergyLevel(energyLevel));
	        //this.energyLevel = energyLevel;
	        // this.age = age;
	        //this.size = size;
	        //this.sex = sex;
//	        this.poster = poster;
	        this.posterId = posterId;
	        this.adopted = adopted;
	    }
	    
	    public Dog(String name, int id, int age, int energyLevel, int size, int sex) {
	        this.name = name;
	        this.id = id;
	       
	        setAge(new Age(age));
	        setSex(new Sex(sex));
	        setSize(new Size(size));
	        setEnergyLevel(new EnergyLevel(energyLevel));
	        //this.energyLevel = energyLevel;
	        // this.age = age;
	        //this.size = size;
	        //this.sex = sex;
	        //this.poster = poster;
	    }
	    
	    public String getImagePath() {
	    	return this.imagePath;
	    }
	   
	    public ArrayList<Tag> getTags() {
	        return tags;
	    }
	   
	    public void setTags(ArrayList<Tag> tags) {
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
	        for (Tag tag : this.getTags()) {
	            stringBuilder.append("- ").append(tag.getTagName()).append(": ").append(tag.getWeight()).append("\n");
	        }
	        return stringBuilder.toString();
	    }
}



