package com.example.virtualcloset;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

import com.example.virtualcloset.objects.ClothesItem;
import com.example.virtualcloset.objects.Tag;

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
        ArrayList<Tag> tags2 = new ArrayList<Tag>();
        tags2.add(new Tag(11, "Black"));
        tags2.add(new Tag(12, "Pants"));
        tags2.add(new Tag(13, "Workout"));
        clothesItem.setTags(tags2);
        assertEquals("tags should be equal", tags2, clothesItem.getTags());
    }

    @Test
    public void getTagsStr() {
        ArrayList<Tag> tags2 = new ArrayList<Tag>();
        tags2.add(new Tag(11, "Black"));
        tags2.add(new Tag(12, "Pants"));
        tags2.add(new Tag(13, "Workout"));
        clothesItem.setTags(tags2);
        assertEquals("the first clothes tags should match", "|  Black  |  Pants  |  Workout  |  ", clothesItem.getTagsString());
    }

    @Test
    public void testSetImg() {
        clothesItem.setImg(R.drawable.placeholder_0);
        assertEquals("image should be equal", R.drawable.placeholder_0, clothesItem.getImg());
    }
    
    // Iteration 2
    @Test
    public void testAddTags() { // ..?
        Tag tag3 = null;
        assertEquals("should add a tag", true, clothesItem.addTag(tag3));
    }

    @Test
    public void testIsFave() { // ..?
        assertEquals("should be unfavorited initially", false, clothesItem.getFave());
    }

    @Test
    public void testFavorite() { // ..?
        clothesItem.favorite();
        assertEquals("should set the item to favorite", true, clothesItem.getFave());
    }

    @Test
    public void testUnfavorite() { // ..?
       clothesItem.unFavorite();
        assertEquals("should set the item to unfavorite", false, clothesItem.getFave());
    }

    @After
    public void tearDown() {
    }
}