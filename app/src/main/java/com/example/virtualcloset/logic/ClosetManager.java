package com.example.virtualcloset.logic;

import com.example.virtualcloset.objects.Closet;
import com.example.virtualcloset.objects.ClothesItem;
import com.example.virtualcloset.objects.Outfit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// The ClosetManager class provides methods for accessing data from a closet
// They also format the data in various ways to be used in a grid, spinner
public class ClosetManager implements Serializable {

    public Closet c;

    public ClosetManager(Closet c){
        this.c = c;
    }

    // return arrays to be used by a GridAdapter
    public String[] getClothesNames() {
        return getClothesNames(c.getClothesItems());
    }
    public String[] getClothesNames(ArrayList<ClothesItem> clothesItems){
        String[] str = new String[clothesItems.size()];
        for (int i = 0; i < clothesItems.size(); i++) {
            str[i] = clothesItems.get(i).getName();
        }
        return str;
    }

    public int[] getClothesImgs(){
        return getClothesImgs(c.getClothesItems());
    }
    public int[] getClothesImgs(ArrayList<ClothesItem> clothesItems){
        int[] imgs = new int[clothesItems.size()];
        for (int i = 0; i < clothesItems.size(); i++) {
            imgs[i] = clothesItems.get(i).getImg();
        }
        return imgs;
    }

    public String[] getOutfitsNames() {
        ArrayList<Outfit> outfits = c.getOutfits();
        String[] str = new String[outfits.size()];
        for (int i = 0; i < outfits.size(); i++) {
            str[i] = outfits.get(i).getName();
        }
        return str;
    }

    public int[] getOutfitsImgs(){
        ArrayList<Outfit> outfits = c.getOutfits();
        int[] imgs = new int[outfits.size()];
        for (int i = 0; i < outfits.size(); i++) {
            imgs[i] = outfits.get(i).getImg();
        }
        return imgs;
    }

    //returns a list of all unique tags from the entire closet
    public List<String> getAllTags(ArrayList<ClothesItem> clothesItems){
        List<String> allTags = new ArrayList<>();

        for (int i = 0; i < clothesItems.size(); i++) {
            List<String> currTags = clothesItems.get(i).getTagNames();
            for(int j = 0; j < currTags.size(); j++){
                String t = currTags.get(j);
                if(!allTags.contains(t)){
                    allTags.add(t);
                }
            }
        }
        return allTags;
    }
}
