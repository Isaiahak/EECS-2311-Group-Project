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
    
    
    public boolean equals(Attribute att) {
    	
    	if(this.getType() == att.getType() && this.getWeight() == att.getWeight()) return true;
    	return false;
    }
    
    
//    public static void initializeAttributes() {
//		Attribute.allAttributes.clear();
//		
//		Age age = new Age(0);
//		Size size = new Size(0);
//		Sex sex = new Sex(0);
//		EnergyLevel energyLevel = new EnergyLevel(0);
//
//		Attribute.allAttributes.put(age.getType(), age);
//		Attribute.allAttributes.put(size.getType(), size );
//		Attribute.allAttributes.put(sex.getType(), sex);
//		Attribute.allAttributes.put(energyLevel.getType(), energyLevel);
//		
//		
//		
//	}
//	
//	public static Attribute getAttributeByTypeAndWeight(int type, int weight) {
//		Attribute att = Attribute.allAttributes.get(type);
//		att.setWeight(weight);
//		
//		System.out.println(att.getName());
//		
//		return att; 
//	}
	
    
    
}



	




