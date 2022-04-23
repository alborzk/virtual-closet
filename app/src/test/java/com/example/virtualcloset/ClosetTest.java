package com.example.virtualcloset;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;


public class ClosetTest {
    Closet closet;
    private ArrayList<ClothesItem> clothesItems;
    private ClothesItem clothesItem1;
    private ArrayList<Outfit> outfits;
    private Outfit outfit1;
    @Before
    public void setUp() throws IOException {
        clothesItem1=mock(ClothesItem.class);
        clothesItems = new ArrayList<ClothesItem>();
        clothesItems.add(clothesItem1);
        outfits = new ArrayList<Outfit>();
        outfit1=new Outfit(1,"ABCD");
        outfits.add(outfit1);
        this.closet = new Closet(2, clothesItems, outfits);
    }

    @Test
    public void constructorTest() {
        assertEquals("id should be equal", 2, closet.getID());
        assertEquals("clothesItems should be equal", clothesItems, closet.getClothesItems());
        assertEquals("outfits should be equal", outfits, closet.getOutfits());
    }

    // Test Getters
    @Test
    public void testGetID() {
        assertEquals("id should be equal", 2, closet.getID());
    }

    @Test
    public void testGetClothesItems() {
        assertEquals("clothesItems should be equal", clothesItems, closet.getClothesItems());
    }

    @Test
    public void testGetOutfits() {
        assertEquals("outfits should be equal", outfits, closet.getOutfits());
    }

    @Test
    public void testGetNumClothes() {
        assertEquals("numClothes should be equal", 1, closet.getNumClothes());
    }

    @Test
    public void testGetNumOutfits() {
        assertEquals("numOutfits should be equal", 1, closet.getNumOutfits());
    }

    // Test Setters
    @Test
    public void testSetId() {
        closet.setId(4); // getID and setId. Uppercase and Lowercase. It's a bit confusing
        assertEquals("id should be equal", 4, closet.getID());
    }

    @Test
    public void testSetClothesItems() {
        ClothesItem clothesItem2=null;
        ArrayList<ClothesItem> clothesItems2 = new ArrayList<ClothesItem>();
        clothesItems2.add(clothesItem2);
        closet.setClothesItems(clothesItems2);
        assertEquals("clothItems should be equal", clothesItems2, closet.getClothesItems());
    }

    @Test
    public void testSetOutfits() {
        Outfit outfit2=null;
        ArrayList<Outfit> outfits2 = new ArrayList<Outfit>();
        outfits2.add(outfit2);
        closet.setOutfits(outfits2);
        assertEquals("outfits should be equal", outfits2, closet.getOutfits());
    }

    // Test methods
    @Test
    public void testAddClothesItem() {
        ClothesItem clothesItem3=null;
        assertEquals("should add a clothesItem", true, closet.addClothesItem(clothesItem3));
    }

    @Test
    public void testAddOutfit() {
        Outfit outfit3=new Outfit(99,"1234");
        assertEquals("should add a outfit", true, closet.addOutfit(outfit3));
    }
    @Test
    public void findOutfit() {
        assertEquals("return Outfit",outfit1,closet.findOutfit("ABCD"));;
    }

    @Test
    public void removeClothesItem() {
        assertEquals("return true if removed", true,closet.removeClothesItem(clothesItem1));
    }

    @Test
    public void removeOutfit() {
        assertEquals("return true if removed outfit",true,closet.removeOutfit(outfit1));
    }
    @After
    public void tearDown() {
    }


}