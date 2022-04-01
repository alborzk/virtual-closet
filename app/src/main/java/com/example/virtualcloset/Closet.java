package com.example.virtualcloset;
import java.io.Serializable;
import java.util.ArrayList;

/*Notes:
- int ID or String name to keep track of closets?
*/

public class Closet implements Serializable {

    //Instance Variables
    int id;
    ArrayList<ClothesItem> clothesItems;
    ArrayList<Outfit> outfits;

    //Constructor
    public Closet(int id, ArrayList<ClothesItem> clothesItems, ArrayList<Outfit> outfits){
        this.id = id;
        this.clothesItems = clothesItems;
        this.outfits = outfits;
    }

    //Getters
    public int getID() {
        return id;
    }

    public ArrayList<ClothesItem> getClothesItems() {
        return clothesItems;
    }

    public ArrayList<Outfit> getOutfits() {
        return outfits;
    }

    public int getNumClothes(){
        return clothesItems.size();
    }

    public int getNumOutfits(){
        return outfits.size();
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setClothesItems(ArrayList<ClothesItem> clothesItems) {
        this.clothesItems = clothesItems;
    }

    public void setOutfits(ArrayList<Outfit> outfits) {
        this.outfits = outfits;
    }

    //Other Methods
    public boolean addClothesItem(ClothesItem newItem){
        return clothesItems.add(newItem);
    }

    public boolean addOutfit(Outfit newOutfit){
        return outfits.add(newOutfit);
    }

    public void initializeDefaultCloset(){
        ArrayList<Tag> tags1 = new ArrayList<Tag>();
        tags1.add(new Tag(0, "Black"));
        tags1.add(new Tag(1, "Pants"));
        tags1.add(new Tag(2, "Workout"));
        clothesItems.add(new ClothesItem(0, "Gymshark Joggers", tags1, R.drawable.placeholder_0));

        ArrayList<Tag> tags2 = new ArrayList<Tag>();
        tags2.add(new Tag(10, "Blue"));
        tags2.add(new Tag(11, "Pants"));
        clothesItems.add(new ClothesItem(1, "Blue Levi's Jeans", tags2, R.drawable.placeholder_1));

        ArrayList<Tag> tags3 = new ArrayList<Tag>();
        tags3.add(new Tag(20,"Black"));
        tags3.add(new Tag(21,"Pants"));
        clothesItems.add(new ClothesItem(2, "Black Hollister Jeans", tags3, R.drawable.placeholder_2));

        ArrayList<Tag> tags4 = new ArrayList<Tag>();
        tags4.add(new Tag(30,"Brown"));
        tags4.add(new Tag(31,"T-Shirt"));
        clothesItems.add(new ClothesItem(3, "Brown Zara Tee", tags4, R.drawable.placeholder_3));

        ArrayList<Tag> tags5 = new ArrayList<Tag>();
        tags5.add(new Tag(40,"Grey"));
        tags5.add(new Tag(41,"T-Shirt"));
        clothesItems.add(new ClothesItem(4, "Marvel Graphic Tee", tags5, R.drawable.placeholder_4));

        ArrayList<Tag> tags6 = new ArrayList<Tag>();
        tags6.add(new Tag(50,"White"));
        tags6.add(new Tag(51,"T-Shirt"));
        tags6.add(new Tag(52,"Basic"));
        clothesItems.add(new ClothesItem(5, "White H&M Tee", tags6, R.drawable.placeholder_5));

        ArrayList<Tag> tags7 = new ArrayList<Tag>();
        tags7.add(new Tag(60,"Green"));
        tags7.add(new Tag(61,"Sweater"));
        tags7.add(new Tag(62,"Camping"));
        clothesItems.add(new ClothesItem(6, "Beaver Canoe Hoodie", tags7, R.drawable.placeholder_6));

        ArrayList<Tag> tags8 = new ArrayList<Tag>();
        tags8.add(new Tag(70,"Cream"));
        tags8.add(new Tag(71,"Sweater"));
        tags8.add(new Tag(72,"Oversized"));
        clothesItems.add(new ClothesItem(7, "Vintage Crewneck", tags8, R.drawable.placeholder_7));

        ArrayList<Tag> tags9 = new ArrayList<Tag>();
        tags9.add(new Tag(80,"Black"));
        tags9.add(new Tag(81,"Jacket"));
        tags9.add(new Tag(82,"Winter"));
        tags9.add(new Tag(83,"Cold"));
        clothesItems.add(new ClothesItem(8, "Canada Goose Jacket", tags9, R.drawable.placeholder_8));

        ArrayList<Tag> tags10 = new ArrayList<Tag>();
        tags10.add(new Tag(90,"Black"));
        tags10.add(new Tag(91,"Shoes"));
        tags10.add(new Tag(92,"Summer"));
        tags10.add(new Tag(93,"Spring"));
        clothesItems.add(new ClothesItem(9, "Black Vans Sneakers", tags10, R.drawable.placeholder_9));

        ArrayList<ClothesItem> clothesList1 = new ArrayList<ClothesItem>();
        clothesList1.add(clothesItems.get(5));
        clothesList1.add(clothesItems.get(1));
        outfits.add(new Outfit(0,"Work", clothesList1));
        outfits.get(0).setImg(clothesItems.get(5).getImg());

        ArrayList<ClothesItem> clothesList2 = new ArrayList<ClothesItem>();
        clothesList2.add(clothesItems.get(3));
        clothesList2.add(clothesItems.get(2));
        outfits.add(new Outfit(1,"Casual", clothesList2));
        outfits.get(1).setImg(clothesItems.get(3).getImg());

        ArrayList<ClothesItem> clothesList3 = new ArrayList<ClothesItem>();
        clothesList3.add(clothesItems.get(4));
        clothesList3.add(clothesItems.get(2));
        clothesList3.add(clothesItems.get(9));
        outfits.add(new Outfit(2,"Party", clothesList3));
        outfits.get(2).setImg(clothesItems.get(4).getImg());

        ArrayList<ClothesItem> clothesList4 = new ArrayList<ClothesItem>();
        clothesList4.add(clothesItems.get(8));
        clothesList4.add(clothesItems.get(6));
        clothesList4.add(clothesItems.get(1));
        outfits.add(new Outfit(3,"Winter", clothesList4));
        outfits.get(3).setImg(clothesItems.get(8).getImg());
    }

}
