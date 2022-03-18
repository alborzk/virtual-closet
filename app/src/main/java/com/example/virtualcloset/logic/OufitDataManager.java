package com.example.virtualcloset.logic;

import com.example.virtualcloset.ClothesItem;
import com.example.virtualcloset.Outfit;
import com.example.virtualcloset.storage.Database;

import java.util.ArrayList;

public class OufitDataManager {

    public Database db;
    ArrayList<Outfit> outfits;
    ArrayList<ClothesItem> clothesItems;

    public OufitDataManager(Database db){
        this.db = db;
        outfits = db.getOutfits();
        clothesItems=outfits.get(1).getClothesItems();
    }

    public String[] getNames() {
        String[] str = new String[clothesItems.size()];
        for (int i = 0; i < clothesItems.size(); i++) {
            str[i] = clothesItems.get(i).getName();
        }
        return str;
    }

    public String[] getTags() {
        ClothesItem item;
        String[] allTags = new String[clothesItems.size()];

        for (int i = 0; i < clothesItems.size(); i++) {
            item = clothesItems.get(i);
            String str = "|  ";
            for(int j = 0; j < item.getTags().size(); j++){
                str = str + item.getTags().get(j).getName() + "  |  ";
            }
            allTags[i] = str;
        }
        return allTags;
    }

    public int[] getImgs(){
        int[] imgs = new int[clothesItems.size()];
        for (int i = 0; i < clothesItems.size(); i++) {
            imgs[i] = clothesItems.get(i).getImg();
        }
        return imgs;
    }
}
