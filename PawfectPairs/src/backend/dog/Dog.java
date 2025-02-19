package backend.dog;

import java.util.Hashtable;
import java.util.Set;

import backend.dog.trait.Age;
import backend.dog.trait.EnergyLevel;
import backend.dog.trait.Sex;
import backend.dog.trait.Size;
import backend.tag.Tag;

public class Dog  implements Comparable<Dog>{
	 	private String name;
	    private int id;
	    private Age age;
	    private EnergyLevel energyLevel ; 
	    private Size size;
	    private Sex sex; 
	    private int posterId;
	    private boolean adopted;
	    private String imagePath; 
	    private int score; 
	    private String biography;
	    private Hashtable<Integer, Tag> tags = new Hashtable<Integer, Tag>();    
		
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

	    public Dog(Dog dog) { // copy
	    	setName(dog.getName());
	        setId(dog.getId());
	        setImagePath(dog.getImagePath());
	        setPosterId(dog.getPosterId());
	        setAdopted(dog.getAdopted());
	        setBiography(dog.getBiography());
	       
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


	    private void setBiography(String biography) {
			this.biography = biography; 
			
		}

		private void setImagePath(String imagePath) {
			this.imagePath = imagePath; 
			
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
	        
	        return stringBuilder.toString();
	    }
		@Override
		public int compareTo(Dog o) { // for priorityqueue
			return Integer.compare(o.score,this.score);
		}
		
		@Override
		public boolean equals(Object d) {
			if(this.getId() == ((Dog) d).getId())return true;
			
			return false; 
		}
}


