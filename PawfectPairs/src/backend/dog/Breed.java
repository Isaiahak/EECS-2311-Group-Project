package backend.dog;
import java.util.ArrayList;

public class Breed {
    private int weight;
    private String name;
    private ArrayList<String> images; //change it to Image object afterward

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        // Return the first image from the list of images
        if (images != null && images.size() > 0) {
            return images.get(0);
        } else {
            return null;
        }
    }

    // Additional methods for managing the list of images
    public void addImage(String image) {
        if (images == null) {
            images = new ArrayList<>();
        }
        images.add(image);
    }

    public void removeImage(String image) {
        if (images != null) {
            images.remove(image);
        }
    }
}