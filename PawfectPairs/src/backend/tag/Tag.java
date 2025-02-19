package backend.tag;

public class Tag{
    private String tagName;
    private int tagId;

    public Tag(String tagName, int tagId) {
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