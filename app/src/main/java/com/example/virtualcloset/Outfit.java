package com.example.virtualcloset;

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

    public void setImg(int img) {
        this.img = img;
    }

    public boolean addClothesItem(ClothesItem newItem) {
        return clothesItems.add(newItem);
    }

    public boolean removeClothesItem(ClothesItem toRemove) {
        return clothesItems.remove(toRemove);
    }
}
