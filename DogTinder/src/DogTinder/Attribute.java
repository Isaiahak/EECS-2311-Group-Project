package DogTinder;

public class Attribute implements Comparable<Attribute>{
    // attributes abstract class to implement comparable, order-able attributes, such as size, energy level, etc
    private int weight;
    private String name;

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

class Size extends Attribute {
    public Size(int weight){
        super(weight);

        switch(weight) {
            case 0:
                this.setName("Small");
                break;
            case 1:
                this.setName("Medium");
                break;
            case 2:
                this.setName("Large");
                break;
            default:
                throw new IllegalArgumentException("Error. No matching name for weight entered.");
        }
    }
    @Override
	public String toString() {
		return this.getName()+"";
	}
}

class EnergyLevel extends Attribute {
    public EnergyLevel(int weight){
        super(weight);

        switch(weight) {
            case 0:
                this.setName("Lazy");
                break;
            case 1:
                this.setName("Moderate");
                break;
            case 2:
                this.setName("Energetic");
                break;
            default:
                throw new IllegalArgumentException("Error. No matching name for weight entered.");
        }
    }

	@Override
	public String toString() {
		return this.getName()+"";
	}
    
    
}

