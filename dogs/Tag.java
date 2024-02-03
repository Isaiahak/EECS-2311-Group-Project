package dogs;

public class Tag {
    private int weight;
    private String tagName;

    public Tag(){
        this.weight = 0;
        this.tagName = "Unnamed Tag";
    }

    public Tag(int weight, String tagName){
        this.weight = weight;
        this.tagName = tagName;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
