package com.example.virtualcloset.logic;

import com.example.virtualcloset.ClothesItem;
import com.example.virtualcloset.storage.Database;

import java.io.Serializable;
import java.util.ArrayList;

public class DataManager implements Serializable {

    public Database db;
    ArrayList<ClothesItem> clothesItems;

    public DataManager(Database db){
        this.db = db;
        clothesItems = db.getClothesItems();
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
