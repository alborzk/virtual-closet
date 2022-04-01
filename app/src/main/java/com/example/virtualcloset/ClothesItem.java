package com.example.virtualcloset;

import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.util.ArrayList;

public class ClothesItem implements Serializable {

    //Instance Variables
    int id;
    String name;
    ArrayList<Tag> tags;
    int img;
    boolean fave;

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

    public boolean isFave(){
        return fave;
    }

    public String getTagsString() {
        String str = "";
        if (tags.size() > 0){
            str = "|  ";
            for(int j = 0; j < tags.size(); j++){
                str = str + tags.get(j).getName() + "  |  ";
            }
        }
        return str;
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

    public boolean addTag(Tag newTag) {
        return tags.add(newTag);
    }

    public boolean favorite(){
        return fave = true;
    }

    public boolean unFavorite(){
        return fave = false;
    }


}
