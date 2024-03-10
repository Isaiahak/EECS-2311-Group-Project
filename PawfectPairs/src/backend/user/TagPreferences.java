package backend.user;

import java.util.Hashtable;

import backend.tag.Tag;
public class TagPreferences {

    Hashtable<Integer,Tag> tagPreferences = new Hashtable<Integer,Tag>();

    public Hashtable<Integer, Tag> getTagPreferences(){
        return this.tagPreferences;
    }


}
