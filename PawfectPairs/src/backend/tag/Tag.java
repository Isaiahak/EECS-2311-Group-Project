package backend.tag;

public class Tag{
    private int preference;
    private String tagName;
    private int tagId;

    // Constructor
    public Tag(String tagName, int tagId) {
        this.preference = 0; // def
        this.tagName = tagName;
        this.tagId = tagId;
    }
    
    public int getTagId() {
		return tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
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
    
    @Override 
    public boolean equals(Object o) {
    	
    	if(this.getTagName().equals(((Tag) o).getTagName())) return true;
    	
    	return false;
    	
    }
}