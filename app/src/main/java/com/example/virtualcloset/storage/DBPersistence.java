package com.example.virtualcloset.storage;

import com.example.virtualcloset.objects.ClothesItem;
import com.example.virtualcloset.objects.Outfit;
import com.example.virtualcloset.objects.Tag;
import com.example.virtualcloset.objects.UserAccount;

import java.util.ArrayList;

public interface DBPersistence {
    public ArrayList<ClothesItem> getClothesItems();

    public ArrayList<Outfit> getOutfits();

    public ArrayList<UserAccount> getAccounts();

    public ArrayList<Tag> getTags();
}
