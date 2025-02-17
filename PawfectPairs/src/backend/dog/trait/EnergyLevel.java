package backend.dog.trait;

public class EnergyLevel extends Attribute {
	private static final String[] names = {"Vegetable", "Lazy", "Moderate", "Energetic", "Hyper"};
	private static final int type = 2;
    public EnergyLevel(int weight){
    	setName(names[weight]);       	
    	this.weight = weight;
    }

	@Override
	public String toString() {
		return this.getName();
	}
	
	@Override
	public String[] getNames() {
		// TODO Auto-generated method stub
		return names;
	}

	@Override 
	public int getType() {
		return type; 
	}

	@Override
	public Attribute cloneAttribute() {
		return new EnergyLevel(this.weight);
	}
}


