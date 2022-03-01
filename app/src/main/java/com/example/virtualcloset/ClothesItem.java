package com.example.virtualcloset;
import java.util.ArrayList;

public class ClothesItem {

    //Instance Variables
    int id;
    String name;
    ArrayList<Tag> tags;
    String img;

    //Constructor
    public ClothesItem(int id, String name, String img){
        this.id = id;
        this.name = name;
        this.img = img;
        this.tags = new ArrayList<Tag>();
    }

    public ClothesItem(int id, String name, ArrayList<Tag> tags, String img){
        this.id = id;
        this.name = name;
        this.tags = tags;
        this.img = img;
    }

    //Getters
    public String getName() {
        return name;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public String getImg() {
        return img;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public boolean addTag(Tag newTag){
        return tags.add(newTag);
    }
}
