package DogTinder;
public class Tag {
    private int preferenceWeight;
    private String tagName;

    // Constructor
    public Tag(int preferenceWeight, String tagName) {
        this.preferenceWeight = preferenceWeight;
        this.tagName = tagName;
    }

    // Getter for preferenceWeight
    public int getWeight() {
        return preferenceWeight;
    }

    // Setter for preferenceWeight
    public void setWeight(int preferenceWeight) {
        this.preferenceWeight = preferenceWeight;
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
