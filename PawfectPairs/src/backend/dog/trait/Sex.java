package backend.dog.trait;

public class Sex extends Attribute {
	private static final String[] names = {"Male","Female"};
	
	public Sex(int weight) {
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


