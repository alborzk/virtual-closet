package com.example.virtualcloset.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClothesItem implements Serializable {

    //Instance Variables
    private int id;
    private String name;
    private ArrayList<Tag> tags;
    private int img;
    private boolean fave;

    //Constructor
    public ClothesItem(int id, String name, int img) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.tags = new ArrayList<Tag>();
        this.fave = false;
    }

    public ClothesItem(int id, String name, ArrayList<Tag> tags, int img) {
        this.id = id;
        this.name = name;
        this.tags = tags;
        this.img = img;
        this.fave = false;
    }

    //Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public int getImg() {
        return img;
    }

    public boolean getFave(){
        return fave;
    }

    //Get but in a particular format
    public String getTagsString() {
        String str = "";
        if (tags.size() > 0) {
            str = "|  ";
            for (int j = 0; j < tags.size(); j++) {
                str = str + tags.get(j).getName() + "  |  ";
            }
        }
        return str;
    }

    public List getTagNames() {
        List<String> names = new ArrayList();
        for (int j = 0; j < tags.size(); j++) {
            names.add(tags.get(j).getName());
        }
        return names;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    public void setImg(int img) {
        this.img = img;
    }

    //Tag management
    public boolean addTag(Tag newTag) {
        return tags.add(newTag);
    }

    public boolean removeTag(Tag r) {
        return tags.remove(r);
    }

    public Tag findTagByName(String name) {
        for (int j = 0; j < tags.size(); j++) {
            if (name.equalsIgnoreCase(tags.get(j).getName())) {
                return tags.get(j);
            }
        }
        return null;
    }

    //Favourite management
    public boolean favorite(){
        return fave = true;
    }

    public boolean unFavorite(){
        return fave = false;
    }
}
