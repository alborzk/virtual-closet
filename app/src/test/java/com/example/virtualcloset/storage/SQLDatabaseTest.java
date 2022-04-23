//package com.example.virtualcloset.storage;
//
//import static org.junit.Assert.*;
//
//import android.accounts.Account;
//
//import com.example.virtualcloset.objects.ClothesItem;
//import com.example.virtualcloset.objects.UserAccount;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//public class SQLDatabaseTest {
//    SQLDatabase db;
//    @Before
//    public void setUp() throws Exception {
//        db=new SQLDatabase();
//        assertNotNull(db);
//    }
//
//    @After
//    public void tearDown() throws Exception {
//    }
//
//    @Test
//    public void getConnection() {
//        db.getConnection();
//        assertNotNull(db);
//
//    }
//
//    @Test
//    public void getClothesItems() {
//        assertEquals(10,db.getClothesItems().size());
//    }
//
//    @Test
//    public void getClothesItemsFromClosetID() {
//        assertEquals("The name of first item in Closet should match ","Blue Levi's Jeans",db.getClothesItemsFromClosetID(1).get(1).getName());
//    }
//
//    @Test
//    public void getClothesItemsFromOutfitID() {
//        assertEquals("The name of first cloth in first Outfit","Blue Levi's Jeans",db.getClothesItemsFromOutfitID(0).get(0).getName());
//    }
//
//    @Test
//    public void getClothesItemFromClothesID() {
//        assertEquals("The name of third cloth in db should match","Black Hollister Jeans",db.getClothesItemFromClothesID(2).getName());
//    }
//
//    @Test
//    public void getOutfits() {
//        assertEquals("The number of outfits in db should be 2",2,db.getOutfits().size());
//        assertEquals("The id of first outfit in Outfit should be 0",0,db.getOutfits().get(0).getID());
//    }
//
//    @Test
//    public void getOutfitsFromClosetID() {
//        assertEquals("The number of outfits in specific Closet should match",2,db.getOutfitsFromClosetID(1).size());
//    }
//
//    @Test
//    public void getTags() {
//        assertEquals("Tag's name should match", "Black", db.getTags().get(0).getName());
//    }
//
//    @Test
//    public void getTagsFromClothesID() {
//        assertEquals("The numbers of Cloth's tag should match", 3,db.getTagsFromClothesID(0).size());
//    }
//
////    @Test
////    public void getTagsFromOutfitID() {
////        assertEquals("tag's name match with cloth item", "Black",db.getTagsFromOutfitID(1).size());
////    }
//
//    @Test
//    public void getTagFromID() {
//        assertEquals("Tag name should match with using TagID","Blue",db.getTagFromID(10).getName());
//
//    }
//
//    @Test
//    public void getAccounts() {
//        assertEquals("Default Account with ID 0",0, db.getAccounts().get(0).getID());
//    }
//
//    @Test
//    public void getClosetFromID() {
//        assertEquals("Closet should match", 10,db.getClosetFromID(1).getNumClothes());
//    }
//
////    @Test
////    public void addUserAccount() {
////
////        UserAccount newAcc=new UserAccount(9,"igumi","testworld","3350@gmail.com");
////        db.addUserAccount(newAcc);
////        int size=db.getAccounts().size();
////        assertEquals("after adding one account , the info of new Accounts should match", 9,db.getAccounts().get(size).getID());
////
////    }
//
////    @Test
////    public void addClothesItem() {
////        ClothesItem newCloth= new ClothesItem(99,"Essential Jacket",null,1234);
////        db.addClothesItem(newCloth,1);
////        assertEquals("The name of new cloth in db should match","Essential Jacket",db.getClothesItemFromClothesID(99).getName());
////
////    }
//}