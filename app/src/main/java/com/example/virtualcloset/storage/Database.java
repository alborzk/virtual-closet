package com.example.virtualcloset.storage;

import com.example.virtualcloset.Closet;
import com.example.virtualcloset.ClothesItem;
import com.example.virtualcloset.Outfit;
import com.example.virtualcloset.R;
import com.example.virtualcloset.Tag;
import com.example.virtualcloset.UserAccount;

import java.io.Serializable;
import java.util.ArrayList;

import android.os.Parcelable;
import android.os.Parcel;

public class Database implements Serializable, IDatabase{

    ArrayList<ClothesItem> clothesItems;
    ArrayList<UserAccount> accounts;
    ArrayList<Outfit> outfits;
    boolean useSQL; //Change this to true if you want to change database usage
    SQLDatabase sqlDatabase;

    public Database(boolean useSQL){
        accounts = new ArrayList<UserAccount>();
        if(useSQL){
            this.sqlDatabase = new SQLDatabase();
        }
        initializeDefaultAccount();
    }

    public ArrayList<ClothesItem> getClothesItems() {
        ArrayList<ClothesItem> result;
        if(useSQL){
            result = sqlDatabase.getClothesItems();
        }
        else {
            result = clothesItems;
        }
        return result;
    }

    public ArrayList<Outfit> getOutfits() {
        ArrayList<Outfit> result;
        if(useSQL){
            result = sqlDatabase.getOutfits();
        }
        else {
            result = outfits;
        }
        return result;
    }

    public ArrayList<UserAccount> getAccounts(){
        ArrayList<UserAccount> result;
        if(useSQL){
            result = sqlDatabase.getAccounts();
            if(result == null){
                System.out.println("get accounts is returning null");
            }
        }
        else {
            result = accounts;
        }
        return result;
    }

    public boolean addAccount(UserAccount account){
        return accounts.add(account);
    }

    public int getNumAccounts(){
        int results = 0;
        if(useSQL){
            results = sqlDatabase.getAccounts().size();
        }
        else {
            results = accounts.size();
        }
        return results;
    }

    public SQLDatabase getSqlDatabase() {
        return sqlDatabase;
    }

    public void initializeDefaultAccount(){
        //Add default user account to accounts
        UserAccount defaultAccount = new UserAccount(0, "user", "password", "user@email.com");
        addAccount(defaultAccount);

        //Set up default user account with a closet
        Closet defaultCloset = new Closet(0, new ArrayList<ClothesItem>(), new ArrayList<Outfit>());
        initializeDefaults(defaultCloset);
        defaultAccount.addCloset(defaultCloset);
    }

    public void initializeDefaults(Closet closet){

        clothesItems = closet.getClothesItems();
        outfits = closet.getOutfits();

        ArrayList<Tag> tags1 = new ArrayList<Tag>();
        tags1.add(new Tag(0, "Black"));
        tags1.add(new Tag(1, "Pants"));
        tags1.add(new Tag(2, "Workout"));
        clothesItems.add(new ClothesItem(0, "Gymshark Joggers", tags1, R.drawable.clothing_sweats));

        ArrayList<Tag> tags2 = new ArrayList<Tag>();
        tags2.add(new Tag(10, "Blue"));
        tags2.add(new Tag(11, "Pants"));
        clothesItems.add(new ClothesItem(1, "Blue Levi's Jeans", tags2, R.drawable.clothing_jeans));

        ArrayList<Tag> tags3 = new ArrayList<Tag>();
        tags3.add(new Tag(20,"Black"));
        tags3.add(new Tag(21,"Pants"));
        clothesItems.add(new ClothesItem(2, "Black Hollister Jeans", tags3, R.drawable.clothing_jeans));

        ArrayList<Tag> tags4 = new ArrayList<Tag>();
        tags4.add(new Tag(30,"Brown"));
        tags4.add(new Tag(31,"T-Shirt"));
        clothesItems.add(new ClothesItem(3, "Brown Zara Tee", tags4, R.drawable.clothing_mtee));

        ArrayList<Tag> tags5 = new ArrayList<Tag>();
        tags5.add(new Tag(40,"Grey"));
        tags5.add(new Tag(41,"T-Shirt"));
        clothesItems.add(new ClothesItem(4, "Marvel Graphic Tee", tags5, R.drawable.clothing_mtee));

        ArrayList<Tag> tags6 = new ArrayList<Tag>();
        tags6.add(new Tag(50,"White"));
        tags6.add(new Tag(51,"T-Shirt"));
        tags6.add(new Tag(52,"Basic"));
        clothesItems.add(new ClothesItem(5, "White H&M Tee", tags6, R.drawable.clothing_mtee));

        ArrayList<Tag> tags7 = new ArrayList<Tag>();
        tags7.add(new Tag(60,"Green"));
        tags7.add(new Tag(61,"Sweater"));
        tags7.add(new Tag(62,"Camping"));
        clothesItems.add(new ClothesItem(6, "Beaver Canoe Hoodie", tags7, R.drawable.clothing_hoodie));

        ArrayList<Tag> tags8 = new ArrayList<Tag>();
        tags8.add(new Tag(70,"Cream"));
        tags8.add(new Tag(71,"Sweater"));
        tags8.add(new Tag(72,"Oversized"));
        clothesItems.add(new ClothesItem(7, "Vintage Crewneck", tags8, R.drawable.clothing_sweater));

        ArrayList<Tag> tags9 = new ArrayList<Tag>();
        tags9.add(new Tag(80,"Black"));
        tags9.add(new Tag(81,"Jacket"));
        tags9.add(new Tag(82,"Winter"));
        tags9.add(new Tag(83,"Cold"));
        clothesItems.add(new ClothesItem(8, "Canada Goose Jacket", tags9, R.drawable.clothing_coat));

        ArrayList<Tag> tags10 = new ArrayList<Tag>();
        tags10.add(new Tag(90,"Black"));
        tags10.add(new Tag(91,"Shoes"));
        tags10.add(new Tag(92,"Summer"));
        tags10.add(new Tag(93,"Spring"));
        clothesItems.add(new ClothesItem(9, "Black Vans Sneakers", tags10, R.drawable.clothing_sneakers));

        ArrayList<ClothesItem> clothesList1 = new ArrayList<ClothesItem>();
        clothesList1.add(clothesItems.get(5));
        clothesList1.add(clothesItems.get(1));
        outfits.add(new Outfit(0,"Work", clothesList1));

        ArrayList<ClothesItem> clothesList2 = new ArrayList<ClothesItem>();
        clothesList2.add(clothesItems.get(3));
        clothesList2.add(clothesItems.get(2));
        outfits.add(new Outfit(1,"Casual", clothesList2));

        ArrayList<ClothesItem> clothesList3 = new ArrayList<ClothesItem>();
        clothesList3.add(clothesItems.get(4));
        clothesList3.add(clothesItems.get(2));
        clothesList3.add(clothesItems.get(9));
        outfits.add(new Outfit(2,"Party", clothesList3));

        ArrayList<ClothesItem> clothesList4 = new ArrayList<ClothesItem>();
        clothesList4.add(clothesItems.get(8));
        clothesList4.add(clothesItems.get(6));
        clothesList4.add(clothesItems.get(1));
        outfits.add(new Outfit(3,"Winter", clothesList4));
    }
}
