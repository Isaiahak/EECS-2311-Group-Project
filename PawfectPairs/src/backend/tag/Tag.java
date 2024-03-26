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

    public int getWeight() {
        return preference;
    }

    public void setWeight(int preferenceWeight) {
        this.preference = preferenceWeight;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
    
    @Override 
    public boolean equals(Object o) {
    	
    	if(this.getTagName().equals(((Tag) o).getTagName())) return true;
    	
    	return false;
    	
    }
}