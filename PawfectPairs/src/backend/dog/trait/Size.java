package backend.dog.trait;

public class Size extends Attribute {
	private static final String[] names = {"Small", "Medium","Large"};
	private static final int type = 3;
	public Size(int weight){    
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



