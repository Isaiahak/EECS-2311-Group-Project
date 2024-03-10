package backend.user;

import backend.dog.trait.*;

import java.util.HashMap;

public class AttributePreferenceFactor {

    private static HashMap<Integer, Attribute> attributePreferenceFactor = new HashMap<>();

    private static void setAttributePreferenceFactor(Integer weight){
        attributePreferenceFactor.clear();
        attributePreferenceFactor.put(1,new Age(weight));
        attributePreferenceFactor.put(2,new Size(weight));
        attributePreferenceFactor.put(3,new Sex(weight));
        attributePreferenceFactor.put(4,new EnergyLevel(weight));
    }
    
    public static Attribute getAttribute(Integer attributeType, Integer weight){
        setAttributePreferenceFactor(weight);
        return attributePreferenceFactor.get(attributeType);
    }
    


}
