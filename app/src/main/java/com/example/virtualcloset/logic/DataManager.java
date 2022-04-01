package com.example.virtualcloset.logic;

import com.example.virtualcloset.Closet;
import com.example.virtualcloset.ClothesItem;
import com.example.virtualcloset.UserAccount;
import com.example.virtualcloset.storage.Database;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class DataManager implements Serializable {

    public Database db;

    public DataManager(Database db){
        this.db = db;
    }

    //Finds and returns a UserAccount given a username/password combination and a list of accounts
    public UserAccount findAccount(String userInput, String passInput){
        ArrayList<UserAccount> accounts = db.getAccounts();
        UserAccount account = null;
        boolean accountFound = false;
        for (int i = 0; i < accounts.size() && !accountFound; i++) {
            UserAccount curr = accounts.get(i);
            if ((curr.getUsername().equals(userInput) || curr.getEmail().equals(userInput)) && curr.getPassword().equals(passInput)) {
                accountFound = true;
                account = curr;
            }
        }
        return account;
    }

//    //Finds and returns a UserAccount given a username/password combination and its ID
//    public int findAID(String userInput, String passInput){
//        ArrayList<UserAccount> accounts = db.getAccounts();
//        int aID = -1;
//        boolean accountFound = false;
//        for (int i = 0; i < accounts.size() && !accountFound; i++) {
//            UserAccount curr = accounts.get(i);
//            if ((curr.getUsername().equals(userInput) || curr.getEmail().equals(userInput)) && curr.getPassword().equals(passInput)) {
//                accountFound = true;
//                aID = i;
//            }
//        }
//        return aID;
//    }

    //Checks whether a UserAccount already exists in the system
    public boolean accountExists(String userInput) {
        ArrayList<UserAccount> accounts = db.getAccounts();
        boolean accountFound = false;
        for (int i = 0; i < accounts.size() && !accountFound; i++) {
            UserAccount curr = accounts.get(i);
            if ((curr.getUsername().equals(userInput) || curr.getEmail().equals(userInput))) {
                accountFound = true;
            }
        }
        return accountFound;
    }
}
