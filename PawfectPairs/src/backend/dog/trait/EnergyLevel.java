package backend.dog.trait;

public class EnergyLevel extends Attribute {
	private static final String[] names = {"Lazy","Moderate","Energetic"};
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
	
}


