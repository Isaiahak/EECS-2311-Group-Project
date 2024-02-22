package backend.dog.Attributes;

public class Size extends Attribute {
	
	private String[] names = {"Small", "Medium","Large"};

	public Size(int weight){
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



