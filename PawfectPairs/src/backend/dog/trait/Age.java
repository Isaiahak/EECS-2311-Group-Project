package backend.dog.trait;

public class Age extends Attribute {
	
	private String[] names = {"puppy 0 to 1","Adolescent 1 to 2","Adult 2 and 6","Mature Adult 6 to 9","Senior 9 +"};
	
    public Age(int weight){
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

