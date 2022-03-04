package com.example.virtualcloset;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ClothesItemTest {

    private ArrayList<Tag> tags;
    private Tag tag1;
    private ClothesItem clothesItem;
    private ClothesItem clothesItem2;

    @Before
    public void setUp() throws IOException {
        tags = new ArrayList<Tag>();
        tags.add(tag1);
        this.clothesItem = new ClothesItem(2,"blue_skirt", tags, R.drawable.placeholder_1);
        this.clothesItem2 = new ClothesItem(3,"green_shirt", R.drawable.placeholder_2);
    }
    @Test
    public  void constructorTest(){
        assertEquals("id should match",2,clothesItem.getId());
        assertEquals("name should be equal", "blue_skirt", clothesItem.getName());
        assertEquals("tags should be equal", tags, clothesItem.getTags());
        assertEquals("image should be equal", R.drawable.placeholder_1, clothesItem.getImg());
    }
    @Test
    public  void constructorTest2(){
        assertEquals("id should match",3,clothesItem2.getId());
        assertEquals("name should be equal", "green_shirt", clothesItem2.getName());
        assertEquals("image should be equal", R.drawable.placeholder_2, clothesItem2.getImg());
    }

    // Test Getters
    @Test
    public void getId(){
        assertEquals("id should match",2,clothesItem.getId());
        assertEquals("id should match",3,clothesItem2.getId());
    }

    @Test
    public void testGetName() {
        assertEquals("name should be equal", "blue_skirt", clothesItem.getName());
    }

    @Test
    public void testGetTags() {
        assertEquals("tags should be equal", tags, clothesItem.getTags());
    }

    @Test
    public void testGetImg() {
        assertEquals("image should be equal", R.drawable.placeholder_1, clothesItem.getImg());
    }

    // Test Setters
    @Test
    public void testSetName() {
        clothesItem.setName("black_shoes");
        assertEquals("name should be equal", "black_shoes", clothesItem.getName());
    }

    @Test
    public void testSetTags() {
        Tag tag2 = null;
        ArrayList<Tag> tags2 = new ArrayList<Tag>();
        tags2.add(tag2);
        clothesItem.setTags(tags2);
        assertEquals("tags should be equal", tags2, clothesItem.getTags());
    }

    @Test
    public void testSetImg() {
        clothesItem.setImg(R.drawable.placeholder_0);
        assertEquals("image should be equal", R.drawable.placeholder_0, clothesItem.getImg());
    }
    
    // Test Methods
    @Test
    public void testAddTags() { // ..?
        Tag tag3 = null;
        assertEquals("should add a tag", true, clothesItem.addTag(tag3));
    }

    @After
    public void tearDown() {
    }
}