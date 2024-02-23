package backend.dog.trait;

public class EnergyLevel extends Attribute {
	private String[] names = {"Lazy","Moderate","Energetic"};
    public EnergyLevel(int weight){
        super(weight);
        this.setName(names[weight]);       	
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


