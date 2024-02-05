package dogs;

public class Tag {
    private int preferenceWeight;
    private String tagName;

    public Tag(){
        this.preferenceWeight = 0;
        this.tagName = "Unnamed Tag";
    }

    public Tag(int weight, String tagName){
        this.preferenceWeight = weight;
        this.tagName = tagName;
    }

    public int getWeight() {
        return this.preferenceWeight;
    }

    public void setWeight(int weight) {
        this.preferenceWeight = weight;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
