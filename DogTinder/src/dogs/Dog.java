package dogs;

import java.util.ArrayList;

import poster.Poster;

public class Dog {
    private String name;
    private int id;
    private int age;
    private EnergyLevel energyLevel ; // possibly do a class + implement a comparable interface ?
    private Size size; // class + implement comparable?
    private String sex; // M or F // maybe do 0 or 1?
    private Poster poster;

    private ArrayList<Tag> tags = new ArrayList<Tag>();
    
	

    public Dog(int dogSize, int dogEnergyLevel){ //why do we have this here? //its a constructor that does not set the other attributes
        setSize(new Size(0));
        setEnergyLevel(new EnergyLevel(0));

    }
    
    public Dog(String name, int id, int age, EnergyLevel energyLevel, Size size, String sex, Poster poster) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.energyLevel = energyLevel;
        this.size = size;
        this.sex = sex;
        this.poster = poster;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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
        stringBuilder.append("Tags:\n");
        for (Tag tag : this.getTags()) {
            stringBuilder.append("- ").append(tag.getTagName()).append(": ").append(tag.getWeight()).append("\n");
        }
        return stringBuilder.toString();
    }
}
