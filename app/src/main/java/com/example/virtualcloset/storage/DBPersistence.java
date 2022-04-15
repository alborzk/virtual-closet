package com.example.virtualcloset.storage;

import com.example.virtualcloset.ClothesItem;
import com.example.virtualcloset.Outfit;
import com.example.virtualcloset.UserAccount;

import java.util.ArrayList;

public interface DBPersistence {
    public ArrayList<ClothesItem> getClothesItems();

    public ArrayList<Outfit> getOutfits();

    public ArrayList<UserAccount> getAccounts();
}
