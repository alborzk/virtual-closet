package com.example.virtualcloset;

import java.io.Serializable;
import java.util.ArrayList;

public class ClothesItem implements Serializable {

    //Instance Variables
    int id;
    String name;
    ArrayList<Tag> tags;
    int img;
    public boolean fave;
    public int fav;

    //Constructor
    public ClothesItem(int id, String name, int img) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.tags = new ArrayList<Tag>();
        this.fave = false;
        this.fav = 0;
    }

    public ClothesItem(int id, String name, ArrayList<Tag> tags, int img) {
        this.id = id;
        this.name = name;
        this.tags = tags;
        this.img = img;
        this.fave = false;
        this.fav = 0;
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

    public String getTagsString() {
        String str = "|  ";
        for(int j = 0; j < tags.size(); j++){
            str = str + tags.get(j).getName() + "  |  ";
        }
        return str;
    }

    public int getImg() {
        return img;
    }

    public boolean isFave(){
        return fave;
    }

    public int getFav(){
        return fav;
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

    public boolean setFave(){
        return fave=!fave;
    }

    public void setFav(){
        if (fav == 0){
            fav = 1;
        }
        else{
            fav = 0;
        }
    }
}
