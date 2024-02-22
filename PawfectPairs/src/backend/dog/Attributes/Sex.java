package backend.dog.Attributes;

public class Sex extends Attribute {
	
	private String[] names = {"Male","Female"};
	
	public Sex(int weight) {
		super(weight);
		this.setName(names[weight]);     
	    
	}
	

	@Override
	public String toString() {
		return this.getName()+"";
		}

	@Override
	public String[] getNames() {
		// TODO Auto-generated method stub
		return names;
	}
}


