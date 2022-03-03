package com.example.virtualcloset;
import java.util.ArrayList;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

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

    public ClothesItem(){
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

    public void openDetails(){
        Bundle bundle = new Bundle();
        bundle.putString("name", getName());
        bundle.putStringArrayList("tags", getTags());
        bundle.putString("img", getImg());

        DetailView detail = new DetailView();
        detail.setArguments(bundle);

    }
}
