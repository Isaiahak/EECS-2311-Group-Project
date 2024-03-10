package backend.user;

import backend.dog.trait.Attribute;

import java.util.ArrayList;

public abstract class AttributePreference {


    private ArrayList<Attribute> Preferences = new ArrayList<Attribute>();

    public ArrayList<Attribute> getPreferences(){
        return this.Preferences;
    }
}
