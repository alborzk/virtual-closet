package com.example.virtualcloset.logic;

import com.example.virtualcloset.ClothesItem;
import com.example.virtualcloset.Outfit;
import com.example.virtualcloset.Tag;
import com.example.virtualcloset.UserAccount;
import com.example.virtualcloset.application.Services;
import com.example.virtualcloset.storage.DBPersistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
