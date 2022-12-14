package com.example.virtualcloset.objects;

import java.io.Serializable;
import java.util.ArrayList;

public class Closet implements Serializable {

    //Instance Variables
    int id;
    ArrayList<ClothesItem> clothesItems;
    ArrayList<Outfit> outfits;

    //Constructor
    public Closet(int id, ArrayList<ClothesItem> clothesItems, ArrayList<Outfit> outfits) {
        this.id = id;
        this.clothesItems = clothesItems;
        this.outfits = outfits;
    }

    //Getters
    public int getID() {
        return id;
    }

    public ArrayList<ClothesItem> getClothesItems() {
        return clothesItems;
    }

    public ArrayList<Outfit> getOutfits() {
        return outfits;
    }

    public int getNumClothes() {
        return clothesItems.size();
    }

    public int getNumOutfits() {
        return outfits.size();
    }

    public ClothesItem getItem(int id) {
        ClothesItem item = null;
        for (int i = 0; i < clothesItems.size(); i++) {
            ClothesItem curr = clothesItems.get(i);
            if (curr.getId() == id) {
                item = curr;
            }
        }
        return item;
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setClothesItems(ArrayList<ClothesItem> clothesItems) {
        this.clothesItems = clothesItems;
    }

    public void setOutfits(ArrayList<Outfit> outfits) {
        this.outfits = outfits;
    }

    //Adding and removing from closet
    public boolean addClothesItem(ClothesItem newItem) {
        return clothesItems.add(newItem);
    }

    public boolean addOutfit(Outfit newOutfit) {
        String name = newOutfit.getName();
        for (Outfit o : outfits) {
            if (o.getName().equals(name)) {
                return false;
            }
        }
        outfits.add(0, newOutfit);
        return true;
    }
    //find Outfit by OutfitName
    public Outfit findOutfit(String name){
        for (Outfit o : outfits) {
            if (o.getName().equals(name)) {
                return o;
            }
        }
        return  null;
    }

    public boolean removeClothesItem(ClothesItem toRemove) {
        for (Outfit o : outfits) {
            if (o.getClothesItems().contains(toRemove)) {
                o.removeClothesItem(toRemove);
            }
        }
        return clothesItems.remove(toRemove);
    }

    public boolean removeOutfit(Outfit toRemove) {
        return outfits.remove(toRemove);
    }
}
