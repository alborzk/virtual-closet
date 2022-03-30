package com.example.virtualcloset;
import java.io.Serializable;
import java.util.ArrayList;

/*Notes:
- int ID or String name to keep track of closets?
*/

public class Closet implements Serializable {

    //Instance Variables
    int id;
    ArrayList<ClothesItem> clothesItems;
    ArrayList<Outfit> outfits;

    //Constructor
    public Closet(int id, ArrayList<ClothesItem> clothesItems, ArrayList<Outfit> outfits){
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

    public int getNumClothes(){
        return clothesItems.size();
    }

    public int getNumOutfits(){
        return outfits.size();
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

    //Other Methods
    public boolean addClothesItem(ClothesItem newItem){
        return clothesItems.add(newItem);
    }

    public boolean addOutfit(Outfit newOutfit){
        return outfits.add(newOutfit);
    }

    public boolean setFavorite(int id){
        return clothesItems.get(id).setFave();
    }

    public boolean addTag(int id, Tag tag){
        return clothesItems.get(id).addTag(tag);
    }

}
