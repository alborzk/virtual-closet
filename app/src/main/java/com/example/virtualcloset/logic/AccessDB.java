package com.example.virtualcloset.logic;

import com.example.virtualcloset.objects.ClothesItem;
import com.example.virtualcloset.objects.Outfit;
import com.example.virtualcloset.objects.Tag;
import com.example.virtualcloset.objects.UserAccount;
import com.example.virtualcloset.application.Services;
import com.example.virtualcloset.storage.DBPersistence;

import java.util.Collections;
import java.util.List;

// The AccessDB class provides methods for accessing persistence data
public class AccessDB {
    private DBPersistence dbPersistence;

    public AccessDB(){
        dbPersistence = Services.getDbPersistence();
    }

    public List<ClothesItem> getClothesItems() {
        return Collections.unmodifiableList(dbPersistence.getClothesItems());
    }
    public List<Outfit> getOutfits() {
        return Collections.unmodifiableList(dbPersistence.getOutfits());
    }

    public List<UserAccount> getAccounts() {
        return Collections.unmodifiableList(dbPersistence.getAccounts());
    }

    public List<Tag> getTags(){
        return Collections.unmodifiableList(dbPersistence.getTags());
    }
}
