package com.example.virtualcloset;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class OutfitTest {
    ArrayList<ClothesItem> clothesItems = new ArrayList<ClothesItem>();
    ArrayList<ClothesItem> clothesItems2 = new ArrayList<ClothesItem>();
    ClothesItem item1;
    Outfit newOutfit;
    @Before
    public void setUp() throws Exception {
        newOutfit = new Outfit("ESSENTIAL",clothesItems);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void constructorTest() {
        assertEquals("Outfit's name should match", "ESSENTIAL",newOutfit.getName());
        assertEquals("Outfit's clothitems should match", clothesItems,newOutfit.getClothesItems());
    }

    @Test
    public void getName() {
        assertNotNull(newOutfit);
        assertEquals("Outfit's name should match", "ESSENTIAL",newOutfit.getName());
    }

    @Test
    public void getClothesItems() {
        assertNotNull(newOutfit);
        assertEquals("Outfit's clothitems should match", clothesItems,newOutfit.getClothesItems());
    }

    @Test
    public void setName() {
        assertNotNull(newOutfit);
        newOutfit.setName("FOG");
        assertEquals("Outfit's name should match after set", "FOG",newOutfit.getName());
    }

    @Test
    public void setClothesItems() {
        assertNotNull(newOutfit);
        newOutfit.setClothesItems(clothesItems2);
        assertEquals("Outfit's clothes item should match after set", clothesItems2,newOutfit.getClothesItems());
    }

    @Test
    public void addClothesItem() {
        assertEquals("return true if addClothesItem pass", true, newOutfit.addClothesItem(item1));
    }
}