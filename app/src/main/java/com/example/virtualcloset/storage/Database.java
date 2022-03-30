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

public class Database implements Serializable{

    ArrayList<ClothesItem> clothesItems;
    ArrayList<UserAccount> accounts;
    ArrayList<Outfit> outfits;
    ArrayList<ClothesItem> clothesList1;
    ArrayList<ClothesItem> clothesList2;

    public Database(){
        clothesItems = new ArrayList<ClothesItem>();
        accounts = new ArrayList<UserAccount>();
        outfits = new ArrayList<Outfit>();
        initializeClothes();
        initializeOutfits();
    }

    public void initializeDefaultAccount(){
        //Add default user account to accounts
        accounts.add(new UserAccount("user", "password", "user@email.com"));
        UserAccount defaultAccount = accounts.get(0);

        //Set up default user account with a closet
        defaultAccount.newCloset();
        Closet defaultCloset = defaultAccount.getClosets().get(0);

        //Set up closet with outfits and clothes
        initializeDefaultOutfits(defaultCloset);
        initializeDefaultClothes(defaultCloset);
    }

    private void initializeDefaultOutfits(Closet closet) {
        outfits = closet.getOutfits();
        outfits.add(new Outfit(0,"WORK", clothesList1));
        outfits.add(new Outfit(1,"CASUAL", clothesList2));
    }

    public void initializeDefaultClothes(Closet closet){
        clothesItems = closet.getClothesItems();
        clothesList1=new ArrayList<ClothesItem>();
        clothesList2=new ArrayList<ClothesItem>();

        ArrayList<Tag> tags1 = new ArrayList<Tag>();
        tags1.add(new Tag("Black","Color"));
        tags1.add(new Tag("Pants","Type"));
        tags1.add(new Tag("Workout","Setting"));
        clothesItems.add(new ClothesItem(0, "Gymshark Joggers", tags1, R.drawable.placeholder_0));

        ArrayList<Tag> tags2 = new ArrayList<Tag>();
        tags2.add(new Tag("Blue","Color"));
        tags2.add(new Tag("Pants","Type"));
        clothesItems.add(new ClothesItem(1, "Blue Levi's Jeans", tags2, R.drawable.placeholder_1));
        clothesList1.add(new ClothesItem(1, "Blue Levi's Jeans", tags2, R.drawable.placeholder_1));

        ArrayList<Tag> tags3 = new ArrayList<Tag>();
        tags3.add(new Tag("Black","Color"));
        tags3.add(new Tag("Pants","Type"));
        clothesItems.add(new ClothesItem(2, "Black Hollister Jeans", tags3, R.drawable.placeholder_2));
        clothesList2.add(new ClothesItem(2, "Black Hollister Jeans", tags3, R.drawable.placeholder_2));

        ArrayList<Tag> tags4 = new ArrayList<Tag>();
        tags4.add(new Tag("Brown","Color"));
        tags4.add(new Tag("T-Shirt","Type"));
        clothesItems.add(new ClothesItem(3, "Brown Zara Tee", tags4, R.drawable.placeholder_3));
        clothesList2.add(new ClothesItem(3, "Brown Zara Tee", tags4, R.drawable.placeholder_3));

        ArrayList<Tag> tags5 = new ArrayList<Tag>();
        tags5.add(new Tag("Grey","Color"));
        tags5.add(new Tag("T-Shirt","Type"));
        clothesItems.add(new ClothesItem(4, "Marvel Graphic Tee", tags5, R.drawable.placeholder_4));

        ArrayList<Tag> tags6 = new ArrayList<Tag>();
        tags6.add(new Tag("White","Color"));
        tags6.add(new Tag("T-Shirt","Type"));
        tags6.add(new Tag("Basic","Style"));
        clothesItems.add(new ClothesItem(5, "White H&M Tee", tags6, R.drawable.placeholder_5));
        clothesList1.add(new ClothesItem(5, "White H&M Tee", tags6, R.drawable.placeholder_5));

        ArrayList<Tag> tags7 = new ArrayList<Tag>();
        tags7.add(new Tag("Green","Color"));
        tags7.add(new Tag("Sweater","Type"));
        tags7.add(new Tag("Camping","Setting"));
        clothesItems.add(new ClothesItem(6, "Beaver Canoe Hoodie", tags7, R.drawable.placeholder_6));

        ArrayList<Tag> tags8 = new ArrayList<Tag>();
        tags8.add(new Tag("Cream","Color"));
        tags8.add(new Tag("Sweater","Type"));
        tags8.add(new Tag("Oversized","Fit"));
        clothesItems.add(new ClothesItem(7, "Vintage Crewneck", tags8, R.drawable.placeholder_7));

        ArrayList<Tag> tags9 = new ArrayList<Tag>();
        tags9.add(new Tag("Black","Color"));
        tags9.add(new Tag("Jacket","Type"));
        tags9.add(new Tag("Winter","Season"));
        tags9.add(new Tag("Cold","Weather"));
        clothesItems.add(new ClothesItem(8, "Canada Goose Jacket", tags9, R.drawable.placeholder_8));

        ArrayList<Tag> tags10 = new ArrayList<Tag>();
        tags10.add(new Tag("Black","Color"));
        tags10.add(new Tag("Shoes","Type"));
        tags10.add(new Tag("Summer","Season"));
        tags10.add(new Tag("Spring","Season"));
        clothesItems.add(new ClothesItem(9, "Black Vans Sneakers", tags10, R.drawable.placeholder_9));
    }

    private void initializeOutfits() {
        outfits.add(new Outfit(0,"WORK", clothesList1));
        outfits.add(new Outfit(1,"CASUAL", clothesList2));
    }

    public void initializeClothes(){
        clothesList1=new ArrayList<ClothesItem>();
        clothesList2=new ArrayList<ClothesItem>();
        ArrayList<Tag> tags1 = new ArrayList<Tag>();
        tags1.add(new Tag("Black","Color"));
        tags1.add(new Tag("Pants","Type"));
        tags1.add(new Tag("Workout","Setting"));
        clothesItems.add(new ClothesItem(0, "Gymshark Joggers", tags1, R.drawable.placeholder_0));

        ArrayList<Tag> tags2 = new ArrayList<Tag>();
        tags2.add(new Tag("Blue","Color"));
        tags2.add(new Tag("Pants","Type"));
        clothesItems.add(new ClothesItem(1, "Blue Levi's Jeans", tags2, R.drawable.placeholder_1));
        clothesList1.add(new ClothesItem(1, "Blue Levi's Jeans", tags2, R.drawable.placeholder_1));

        ArrayList<Tag> tags3 = new ArrayList<Tag>();
        tags3.add(new Tag("Black","Color"));
        tags3.add(new Tag("Pants","Type"));
        clothesItems.add(new ClothesItem(2, "Black Hollister Jeans", tags3, R.drawable.placeholder_2));
        clothesList2.add(new ClothesItem(2, "Black Hollister Jeans", tags3, R.drawable.placeholder_2));

        ArrayList<Tag> tags4 = new ArrayList<Tag>();
        tags4.add(new Tag("Brown","Color"));
        tags4.add(new Tag("T-Shirt","Type"));
        clothesItems.add(new ClothesItem(3, "Brown Zara Tee", tags4, R.drawable.placeholder_3));
        clothesList2.add(new ClothesItem(3, "Brown Zara Tee", tags4, R.drawable.placeholder_3));

        ArrayList<Tag> tags5 = new ArrayList<Tag>();
        tags5.add(new Tag("Grey","Color"));
        tags5.add(new Tag("T-Shirt","Type"));
        clothesItems.add(new ClothesItem(4, "Marvel Graphic Tee", tags5, R.drawable.placeholder_4));

        ArrayList<Tag> tags6 = new ArrayList<Tag>();
        tags6.add(new Tag("White","Color"));
        tags6.add(new Tag("T-Shirt","Type"));
        tags6.add(new Tag("Basic","Style"));
        clothesItems.add(new ClothesItem(5, "White H&M Tee", tags6, R.drawable.placeholder_5));
        clothesList1.add(new ClothesItem(5, "White H&M Tee", tags6, R.drawable.placeholder_5));

        ArrayList<Tag> tags7 = new ArrayList<Tag>();
        tags7.add(new Tag("Green","Color"));
        tags7.add(new Tag("Sweater","Type"));
        tags7.add(new Tag("Camping","Setting"));
        clothesItems.add(new ClothesItem(6, "Beaver Canoe Hoodie", tags7, R.drawable.placeholder_6));

        ArrayList<Tag> tags8 = new ArrayList<Tag>();
        tags8.add(new Tag("Cream","Color"));
        tags8.add(new Tag("Sweater","Type"));
        tags8.add(new Tag("Oversized","Fit"));
        clothesItems.add(new ClothesItem(7, "Vintage Crewneck", tags8, R.drawable.placeholder_7));

        ArrayList<Tag> tags9 = new ArrayList<Tag>();
        tags9.add(new Tag("Black","Color"));
        tags9.add(new Tag("Jacket","Type"));
        tags9.add(new Tag("Winter","Season"));
        tags9.add(new Tag("Cold","Weather"));
        clothesItems.add(new ClothesItem(8, "Canada Goose Jacket", tags9, R.drawable.placeholder_8));

        ArrayList<Tag> tags10 = new ArrayList<Tag>();
        tags10.add(new Tag("Black","Color"));
        tags10.add(new Tag("Shoes","Type"));
        tags10.add(new Tag("Summer","Season"));
        tags10.add(new Tag("Spring","Season"));
        clothesItems.add(new ClothesItem(9, "Black Vans Sneakers", tags10, R.drawable.placeholder_9));

        accounts.add(new UserAccount( "user", "password", "user@email.com"));
        accounts.add(new UserAccount( "user2", "password", "user2@email.com"));
    }

    public ArrayList<ClothesItem> getClothesItems() {
        return clothesItems;
    }

    public ArrayList<Outfit> getOutfits() {
        return outfits;
    }

    public ArrayList<UserAccount> getAccounts(){
        return accounts;
    }

}
