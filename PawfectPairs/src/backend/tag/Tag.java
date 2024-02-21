package backend.tag;

public class Tag {
    private int preference;
    private String tagName;

    // Constructor
    public Tag(int preferenceWeight, String tagName) {
        this.preference = preferenceWeight;
        this.tagName = tagName;
    }
    
    public Tag(String tagName) {
    	this.tagName = tagName;
    	this.preference = 0; // default
    }


	// Getter for preferenceWeight
    public int getWeight() {
        return preference;
    }

    // Setter for preferenceWeight
    public void setWeight(int preferenceWeight) {
        this.preference = preferenceWeight;
    }

    // Getter for tagName
    public String getTagName() {
        return tagName;
    }

    // Setter for tagName
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
