package backend.dog.trait;

import java.util.HashMap;

public abstract class Attribute implements Comparable<Attribute>{
    // attributes abstract class to implement comparable, order-able attributes, such as size, energy level, etc
    protected int weight;
    private String name;
    protected static int type;
    private static HashMap<Integer, Attribute> allAttributes = new HashMap<Integer, Attribute>(); 

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
    public abstract Attribute cloneAttribute();
    
    public int getWeight() {
        return weight;
    }
    
    public abstract String[] getNames();
    
    public abstract int getType(); 

    public void setWeight(int weight) {
        this.weight = weight;
        this.name = this.getNames()[weight];
    }

    public String getName() {
    	
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object att) {
    	
    	if(this.getWeight() == ((Attribute) att).getWeight()) return true;
    	
    	
    	return false;
    }
}



	




