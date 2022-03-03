package com.example.virtualcloset;

import java.util.ArrayList;

public class Database {

    ArrayList<Tag> tags;
    ArrayList<ClothesItem> clothesItems;

    public Database(){
        tags = new ArrayList<Tag>();
        clothesItems = new ArrayList<ClothesItem>();
    }

    public void initializeTags(){
        tags.add(new Tag("Red","Color"));
        tags.add(new Tag("Blue", "Color"));
        tags.add(new Tag("Green","Color"));
        tags.add(new Tag("Yellow", "Color"));
        tags.add(new Tag("Black","Color"));
        tags.add(new Tag("White", "Color"));
        tags.add(new Tag("Brown","Color"));
        tags.add(new Tag("Purple", "Color"));
        tags.add(new Tag("Pink","Color"));
        tags.add(new Tag("Gray", "Color"));

        tags.add(new Tag("Summer","Season"));
        tags.add(new Tag("Spring", "Season"));
        tags.add(new Tag("Fall","Season"));
        tags.add(new Tag("Winter", "Season"));

        tags.add(new Tag("Hot","Weather"));
        tags.add(new Tag("Cold", "Weather"));
        tags.add(new Tag("Rainy","Weather"));
        tags.add(new Tag("Windy", "Weather"));

        tags.add(new Tag("Shirt","Type"));
        tags.add(new Tag("Jacket", "Type"));
        tags.add(new Tag("Pants","Type"));
        tags.add(new Tag("Sweater", "Type"));
        tags.add(new Tag("Shorts","Type"));
        tags.add(new Tag("Shoes", "Type"));
    }

    public void initializeClothes(){
        clothesItems.add(new ClothesItem(1, "Gymshark Joggers", new ArrayList<Tag>(), "..."));
        clothesItems.add(new ClothesItem(2, "Blue Levi's Jeans", new ArrayList<Tag>(), "..."));
        clothesItems.add(new ClothesItem(3, "Black Hollister Jeans", new ArrayList<Tag>(), "..."));

        clothesItems.add(new ClothesItem(4, "Brown Zara Tee", new ArrayList<Tag>(), "..."));
        clothesItems.add(new ClothesItem(5, "Marvel Graphic Tee", new ArrayList<Tag>(), "..."));
        clothesItems.add(new ClothesItem(6, "White H&M Tee", new ArrayList<Tag>(), "..."));

        clothesItems.add(new ClothesItem(7, "Beaver Canoe Hoodie", new ArrayList<Tag>(), "..."));
        clothesItems.add(new ClothesItem(8, "Vintage Crewneck", new ArrayList<Tag>(), "..."));

        clothesItems.add(new ClothesItem(9, "Canada Goose Jacket", new ArrayList<Tag>(), "..."));

        clothesItems.add(new ClothesItem(10, "Black Vans Sneakers", new ArrayList<Tag>(), "..."));
    }

    public ArrayList<ClothesItem> getClothesItems() {
        initializeClothes();
        return clothesItems;
    }

    public ArrayList<Tag> getTags() {
        initializeTags();
        return tags;
    }
}
