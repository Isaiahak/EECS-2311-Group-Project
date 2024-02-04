package dogs;

import java.util.ArrayList;

public class Dog {
    private String name;
    private int id;
    private int age;
    private EnergyLevel energyLevel ; // possibly do a class + implement a comparable interface ?
    private Size size; // class + implement comparable?
    private String sex; // M or F // maybe do 0 or 1?

    private ArrayList<Tag> tags = new ArrayList<Tag>();

    public Dog(int dogSize, int dogEnergyLevel){
        setSize(new Size(0));
        setEnergyLevel(new EnergyLevel(0));

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
}
