package backend.dog.trait;

public class Age extends Attribute {
	
	private static final String[] names = {"puppy 0 to 1","Adolescent 1 to 2","Adult 2 and 6","Mature Adult 6 to 9","Senior 9 +"};
	private static final int type = 0;
    public Age(int weight){
        setName(names[weight]);
        this.weight = weight;
      
    }
    
	@Override
	public String toString() {
		return this.getName();
	}

	@Override
	public String[] getNames() {
		return names;
	}
	
	@Override 
	public int getType() {
		return type; 
	}

}

