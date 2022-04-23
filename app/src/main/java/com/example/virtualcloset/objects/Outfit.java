package com.example.virtualcloset.objects;

import com.example.virtualcloset.R;

import java.io.Serializable;
import java.util.ArrayList;

public class Outfit implements Serializable {

    //Instance Variables
    private int id;
    private String name;
    private ArrayList<ClothesItem> clothesItems;
    private int img;

    //Constructor
    public Outfit(int id, String name) {
        this.id = id;
        this.name = name;
        this.clothesItems = new ArrayList<ClothesItem>();
    }

    public Outfit(int id, String name, ArrayList<ClothesItem> clothesItems) {
        this.id = id;
        this.name = name;
        this.clothesItems = clothesItems;
    }

    //Getters
    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getImg() {
        if (clothesItems.size() == 0) {
            this.img = R.drawable.placeholder_outfit;
        } else
            this.img = clothesItems.get(0).getImg();
        return img;
    }

    public ArrayList<ClothesItem> getClothesItems() {
        return clothesItems;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setClothesItems(ArrayList<ClothesItem> clothesItems) {
        this.clothesItems = clothesItems;
    }

    //Add & remove items from outfit
    //avoid adding same clothes in one outfit
    public boolean addClothesItem(ClothesItem newItem){
        int cID=newItem.getId();
        for(ClothesItem c:clothesItems ){
            if(c.getId()==cID){
                return false;
            }
        }
        clothesItems.add(0,newItem);
        return true;
    }

    public boolean removeClothesItem(ClothesItem toRemove) {
        return clothesItems.remove(toRemove);
    }
}
