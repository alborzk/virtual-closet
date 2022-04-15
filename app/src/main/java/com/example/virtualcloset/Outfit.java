package com.example.virtualcloset;
import java.io.Serializable;
import java.util.ArrayList;

public class Outfit implements Serializable {

    //Instance Variables
    int id;
    String name;
    ArrayList<ClothesItem> clothesItems;
    int img;

    //Constructor
    public Outfit(int id, String name){
        this.id = id;
        this.name = name;
        this.clothesItems = new ArrayList<ClothesItem>();
        this.img = R.drawable.placeholder_outfit;
    }

    public Outfit(int id, String name, ArrayList<ClothesItem> clothesItems){
        this.id = id;
        this.name = name;
        this.clothesItems = clothesItems;
        this.img = R.drawable.placeholder_outfit;
    }

    //Getters
    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getImg() {
        if(clothesItems.size()==0){
            this.img = R.drawable.placeholder_outfit;
        }
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

    public boolean addClothesItem(ClothesItem newItem){
        return clothesItems.add(newItem);
    }

    public boolean removeClothesItem(ClothesItem toRemove){
        return clothesItems.remove(toRemove);
    }
}
