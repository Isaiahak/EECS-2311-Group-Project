package backend.dog.trait;

public class Sex extends Attribute {
	private static final String[] names = {"Male","Female"};
	private static final int type = 1;
	
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

	@Override 
	public int getType() {
		return type; 
	}
}


