package backend.dog.Attributes;

public abstract class Attribute implements Comparable<Attribute>{
    // attributes abstract class to implement comparable, order-able attributes, such as size, energy level, etc
    private int weight;
    private String name;

    public abstract String[] getNames();

	

	public Attribute(int weight) {
        setWeight(weight);
    }

    public int compareTo(Attribute o) {
        // if 'weight' is the same, return 0,
        // if 'weight' > other object, return 1
        // else, return -1
        if(o.getWeight() == this.getWeight()){
            return 0;
        }else if(o.getWeight() < this.getWeight()){
            return 1;
        }else{
            return -1;
        }
    }
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

 


	

	




