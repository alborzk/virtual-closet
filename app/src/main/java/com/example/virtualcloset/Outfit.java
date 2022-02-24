package com.example.virtualcloset;
import java.util.ArrayList;

public class Outfit {

    //Instance Variables
    String name;
    ArrayList<ClothesItem> clothesItems;

    //Constructor
    public Outfit(String name, ArrayList<ClothesItem> clothesItems){
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
}
