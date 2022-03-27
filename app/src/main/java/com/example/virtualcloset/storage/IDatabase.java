package com.example.virtualcloset.storage;

import com.example.virtualcloset.ClothesItem;
import com.example.virtualcloset.Tag;
import com.example.virtualcloset.UserAccount;

import java.util.ArrayList;

public interface IDatabase {
    //TODO: Discuss what we want to be able to get out of the database.
    //      Add those needs as interface methods here.
    //      Implement those methods in "SQLDatabase" and "Database"
    public ArrayList<ClothesItem> getClothesItems();

    public ArrayList<Tag> getTags();

    public ArrayList<UserAccount> getAccounts();
}
