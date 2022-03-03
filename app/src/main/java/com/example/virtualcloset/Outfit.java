package com.example.virtualcloset;
import java.util.ArrayList;

public class Outfit {

    //Instance Variables
    int id;
    String name;
    ArrayList<ClothesItem> clothesItems;

    //Constructor
    public Outfit(int id, String name){
        this.id = id;
        this.name = name;
        this.clothesItems = new ArrayList<ClothesItem>();
    }

    public Outfit(int id, String name, ArrayList<ClothesItem> clothesItems){
        this.id = id;
        this.name = name;
        this.clothesItems = clothesItems;
    }

    //Getters
    public String getName() {
        return name;
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

    public boolean addClothesItem(ClothesItem newItem){
        return clothesItems.add(newItem);
    }
}
