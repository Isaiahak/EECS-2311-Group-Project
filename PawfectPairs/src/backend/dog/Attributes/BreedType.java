package backend.dog.Attributes;

public class BreedType extends Attribute {
	private String[] names = {"Pure","Mixed","Unknown"};
    public BreedType(int weight){
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


