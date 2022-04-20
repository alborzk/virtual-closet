package com.example.virtualcloset.storage;

import com.example.virtualcloset.Closet;
import com.example.virtualcloset.ClothesItem;
import com.example.virtualcloset.Outfit;
import com.example.virtualcloset.Tag;
import com.example.virtualcloset.UserAccount;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//Will 'implement' the IDatabase interface
public class SQLDatabase implements Serializable, IDatabase{
    private static Connection con;

    public SQLDatabase(){
        this.getConnection();
    }

    /*
    This function is called to connect this SQLDatabase object with
    the actual database file.
    It returns true if the connection is established and false otherwise
     */
    public boolean getConnection(){
        boolean connectionEstablished = false;

        try {
            Class.forName("org.sqlite.JDBC");
        } catch(ClassNotFoundException e){
            System.out.println(e);
        }

        String jdbcURL = "jdbc:sqlite:VirtualClosetDatabase.db";
        try{
            con = DriverManager.getConnection(jdbcURL);
            connectionEstablished = true;
            System.out.println("Connection Established");
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println(e.getStackTrace());
        }

        return connectionEstablished;
    }

    /*Interface method
    * This method queries the database and returns ALL the clothes items
    * that are stored in the database
    * */
    public ArrayList<ClothesItem> getClothesItems(){
        if(con == null){
            return null;
        }

        ArrayList<ClothesItem> clothes = new ArrayList<ClothesItem>();
        try {
            //Get the set of ClothesItems
            String selectString = "SELECT * FROM ClothesItems";
            Statement statement = con.createStatement();
            ResultSet resultClothes = statement.executeQuery(selectString);

            //Build the ClothesItem objects for each item in the DB. Add them to the list
            while(resultClothes.next()){
                int currClothesID = resultClothes.getInt("clothesID"); //gets the data from "clothesID" field
                String currClothingName = resultClothes.getString("clothingName");
                int currImg = resultClothes.getInt("clothingImg");//NOTE: many images are currently set to null in database. according to documentation, it sets the int to 0
                ArrayList<Tag> currClothesTags = getTagsFromClothesID(currClothesID);
                int currFav = resultClothes.getInt("fav");

                ClothesItem currClothing = new ClothesItem(currClothesID, currClothingName, currClothesTags, currImg);
                if(currFav == 1){
                    //Fav is always set to 0 for newly constructed ClothesItems -> this will update that if necessary
                    currClothing.favorite();
                }
                clothes.add(currClothing);
            }
            statement.close();
            resultClothes.close();
        } catch (SQLException e){
            System.out.println(e);
        }
        return clothes;
    }


    /*
    * This method gets all the clothesItems in the database that belong to a certain closetID
    * */
    public ArrayList<ClothesItem> getClothesItemsFromClosetID(int closetID){
        if(con == null){
            return null;
        }

        ArrayList<ClothesItem> clothes = new ArrayList<ClothesItem>();
        try {
            //Get the set of ClothesItems
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM ClothesItems WHERE closetID = ?");
            preparedStatement.setInt(1, closetID);
            ResultSet resultClothes = preparedStatement.executeQuery();

            //Build the ClothesItem objects for each item in the DB. Add them to the list
            while(resultClothes.next()){
                int currClothesID = resultClothes.getInt("clothesID"); //gets the data from "clothesID" field
                String currClothingName = resultClothes.getString("clothingName");
                int currImg = resultClothes.getInt("clothingImg");//NOTE: many images are currently set to null in database. according to documentation, it sets the int to 0
                ArrayList<Tag> currClothesTags = getTagsFromClothesID(currClothesID);
                int currFav = resultClothes.getInt("fav");

                ClothesItem currClothing = new ClothesItem(currClothesID, currClothingName, currClothesTags, currImg);
                if(currFav == 1){
                    //Fav is always set to 0 for newly constructed ClothesItems -> this will update that if necessary
                    currClothing.favorite();
                }
                clothes.add(currClothing);
            }
            preparedStatement.close();
            resultClothes.close();
        } catch (SQLException e){
            System.out.println(e);
        }
        return clothes;
    }

    /*
     * This method gets all the clothesItems in the database that belong to a certain outfit
     * */
    public ArrayList<ClothesItem> getClothesItemsFromOutfitID(int outfitID){
        if(con == null){
            return null;
        }

        ArrayList<ClothesItem> clothes = new ArrayList<ClothesItem>();
        try {
            //Get the set of ClothesItems
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM OutfitClothing WHERE outfitID = ?");
            preparedStatement.setInt(1, outfitID);
            ResultSet resultClothes = preparedStatement.executeQuery();

            //Build the ClothesItem objects for each item in the DB. Add them to the list
            while(resultClothes.next()){
                int currClothesID = resultClothes.getInt("clothesID"); //gets the data from "clothesID" field
                ClothesItem currClothesItem = getClothesItemFromClothesID(currClothesID);

                clothes.add(currClothesItem);
            }
            preparedStatement.close();
            resultClothes.close();
        } catch (SQLException e){
            System.out.println(e);
        }
        return clothes;
    }

    /*
     * This method gets item from the database with a specific clothesID
     * */
    public ClothesItem getClothesItemFromClothesID(int clothesID){
        if(con == null){
            return null;
        }

        ClothesItem clothesItem = null;
        try {
            //Get the set of ClothesItem
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM ClothesItems WHERE clothesID = ?");
            preparedStatement.setInt(1, clothesID);
            ResultSet resultClothes = preparedStatement.executeQuery();

            //Build the ClothesItem object
            resultClothes.next();
            String clothesName = resultClothes.getString("clothingName");
            int currImg = resultClothes.getInt("clothingImg");//NOTE: many images are currently set to null in database. according to documentation, it sets the int to 0
            ArrayList<Tag> currClothesTags = getTagsFromClothesID(clothesID);
            int fav = resultClothes.getInt("fav");

            clothesItem = new ClothesItem(clothesID, clothesName, currClothesTags, currImg);
            if(fav == 1){
                //Fav is always set to 0 for newly constructed ClothesItems -> this will update that if necessary
                clothesItem.favorite();
            }

            preparedStatement.close();
            resultClothes.close();
        } catch (SQLException e){
            System.out.println(e);
        }
        return clothesItem;
    }



    /*
    * This method queries the database and returns ALL the outfits
    * that are stored in the database
    * */
    public ArrayList<Outfit> getOutfits(){
        if(con == null){
            return null;
        }

        ArrayList<Outfit> outfits = new ArrayList<Outfit>();
        try {
            //Get the set of ClothesItems
            String selectString = "SELECT * FROM Outfits";
            Statement statement = con.createStatement();
            ResultSet resultOutfits = statement.executeQuery(selectString);

            //Build the ClothesItem objects for each item in the DB. Add them to the list
            while(resultOutfits.next()){
                int currOutfitID = resultOutfits.getInt("outfitID"); //gets the data from "outfitID" field
                String currOutfitName = resultOutfits.getString("outfitName");
                int currClosetID = resultOutfits.getInt("closetID");

                ArrayList<ClothesItem> currOutfitSet = getClothesItemsFromOutfitID(currOutfitID);

                Outfit currOutfit = new Outfit(currOutfitID, currOutfitName, currOutfitSet);
                outfits.add(currOutfit);
            }
            statement.close();
            resultOutfits.close();
        } catch (SQLException e){
            System.out.println(e);
        }
        return outfits;
    }

    /*
     * This method queries the database and returns the outfits
     * that are belong to a certain closet
     * */
    public ArrayList<Outfit> getOutfitsFromClosetID(int closetID){
        if(con == null){
            return null;
        }

        ArrayList<Outfit> outfits = new ArrayList<Outfit>();
        try {
            //Get the set of outfits
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Outfits WHERE closetID = ?");
            preparedStatement.setInt(1, closetID);
            ResultSet resultOutfits = preparedStatement.executeQuery();

            //Build the ClothesItem objects for each item in the DB. Add them to the list
            while(resultOutfits.next()){
                int currOutfitID = resultOutfits.getInt("outfitID"); //gets the data from "outfitID" field
                String currOutfitName = resultOutfits.getString("outfitName");

                ArrayList<ClothesItem> currOutfitSet = getClothesItemsFromOutfitID(currOutfitID);

                Outfit currOutfit = new Outfit(currOutfitID, currOutfitName, currOutfitSet);
                outfits.add(currOutfit);
            }
            preparedStatement.close();
            resultOutfits.close();
        } catch (SQLException e){
            System.out.println(e);
        }
        return outfits;
    }




    //Interface method
    public ArrayList<Tag> getTags(){
        if(con == null){
            return null;
        }

        ArrayList<Tag> tags = new ArrayList<Tag>();
        try {
            //Get the set of Tags
            String selectString = "SELECT * FROM Tags";
            Statement statement = con.createStatement();
            ResultSet resultTags = statement.executeQuery(selectString);

            //Build the Tag objects for each item in the DB. Add them to the list
            while(resultTags.next()){
                int currTagID = resultTags.getInt("tagID"); //gets the data from "tagID" field
                String currTagName = resultTags.getString("tagName");
//                String currTagType = resultTags.getString("tagType");

                Tag currTag = new Tag(currTagID, currTagName);
                tags.add(currTag);
            }
            statement.close();
            resultTags.close();
        } catch (SQLException e){
            System.out.println(e);
        }
        return tags;
    }

    /*
    * returns a list of tags that belong to a certain clothesItem
    * */
    public ArrayList<Tag> getTagsFromClothesID(int clothesID){
        if(con == null){
            return null;
        }

        ArrayList<Tag> tags = new ArrayList<Tag>();
        try {
            //Get the set of Tags
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM ClothesTags WHERE clothesID = ?");
            preparedStatement.setInt(1, clothesID);
            ResultSet resultTags = preparedStatement.executeQuery();

            //Build the Tag objects for each item in the DB. Add them to the list
            while(resultTags.next()){
                int currTagID = resultTags.getInt("tagID"); //gets the data from "tagID" field
                Tag currTag = getTagFromID(currTagID);

                tags.add(currTag);
            }
            preparedStatement.close();
            resultTags.close();
        } catch (SQLException e){
            System.out.println(e);
        }
        return tags;
    }

    /*
     * returns a list of tags that belong to a certain outfit
     * */
    public ArrayList<Tag> getTagsFromOutfitID(int outfitID){
        if(con == null){
            return null;
        }

        ArrayList<Tag> tags = new ArrayList<Tag>();
        try {
            //Get the set of Tags
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM OutfitTags WHERE outfitID = ?");
            preparedStatement.setInt(1, outfitID);
            ResultSet resultTags = preparedStatement.executeQuery();

            //Build the Tag objects for each item in the DB. Add them to the list
            while(resultTags.next()){
                int currTagID = resultTags.getInt("tagID"); //gets the data from "tagID" field
                Tag currTag = getTagFromID(currTagID);

                tags.add(currTag);
            }
            preparedStatement.close();
            resultTags.close();
        } catch (SQLException e){
            System.out.println(e);
        }
        return tags;
    }

    /*
     * returns a single tag, based on the passed tagID
     * */
    public Tag getTagFromID(int tagID){
        if(con == null){
            return null;
        }

        Tag tag = null;
        try {
            //Get the Tag
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Tags WHERE tagID = ?"); //should only return 1 entry
            preparedStatement.setInt(1, tagID);
            ResultSet resultTag = preparedStatement.executeQuery();

            //Build the Tag objects for each item in the DB. Add them to the list
            resultTag.next();
            String tagName = resultTag.getString("tagName");
//            String tagType = resultTag.getString("tagType");
            tag = new Tag(tagID, tagName);

            preparedStatement.close();
            resultTag.close();
        } catch (SQLException e){
            System.out.println(e);
        }
        return tag;
    }

    //Interface method
    public ArrayList<UserAccount> getAccounts(){
        if(con == null){
            return null;
        }

        ArrayList<UserAccount> accounts = new ArrayList<UserAccount>();
        try {
            //Get the set of UserAccounts
            String selectString = "SELECT * FROM UserAccounts";
            Statement statement = con.createStatement();
            ResultSet resultAccounts = statement.executeQuery(selectString);

            //Build the user account objects for each account in the DB. Add them to the list
            while(resultAccounts.next()){
                String currUsername = resultAccounts.getString("username"); //gets the data from "username" field
                String currEmail = resultAccounts.getString("email");
                String currPassword = resultAccounts.getString("password");
                int currClosetID = resultAccounts.getInt("closetID");
                int currUserID = resultAccounts.getInt("userID");

                Closet currCloset = getClosetFromID(currClosetID);
                ArrayList<Closet> currAccountClosets = new ArrayList<Closet>();
                //<-------------------------------Right now accounts have a list of closets. this method I think will create a new account for each closet--------------
                currAccountClosets.add(currCloset);

                UserAccount currAccount = new UserAccount(currUserID, currUsername, currPassword, currEmail, currAccountClosets); //add currUserID once merged
                accounts.add(currAccount);
            }
            statement.close();
            resultAccounts.close();
        } catch (SQLException e){
            System.out.println(e);
        }
        return accounts;
    }


    public Closet getClosetFromID(int closetID){
        //Query database for list of clothes for this closet
        ArrayList<ClothesItem> closetClothes = getClothesItemsFromClosetID(closetID);

        //Query database for list of outfits for this closet
        ArrayList<Outfit> closetOutfits = getOutfitsFromClosetID(closetID);

        //Build closet object with closetID and above data
        Closet resultCloset = new Closet(closetID, closetClothes, closetOutfits);

        return resultCloset;
    }

    /*
    * adds a passed useraccount into the UserAccounts table in the database
    * returns true if the userAccount was added, null if the connection isn't established,
    * and false otherwise
    * */
    public void addUserAccount(UserAccount newAccount){
        if(con == null){
            return;
        }

        try {
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO UserAccounts (username, email, password, closetID, userID) VALUES (?,?,?,?,?)");

            preparedStatement.setString(1, newAccount.getUsername());
            preparedStatement.setString(2, newAccount.getEmail());
            preparedStatement.setString(3, newAccount.getPassword());
            preparedStatement.setInt(4, newAccount.getClosets().get(0).getID()); //This assumes the user has one closet, only gets the first closet in the list
            preparedStatement.setInt(5, newAccount.getID());

            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e){
            System.out.println(e);
        }

    }
    /*
     * adds a passed ClothesItem and passed closetID into the the ClothesItems
     * table in the database, with appropriate closetID
     * may want to update this method to read the tags from the item and add those
     * to the database as well.
     */
    public void addClothesItem(ClothesItem newItem, int closetID){
        if(con == null){
            return;
        }

        try {
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO ClothesItems (clothesID, clothingName, clothingImg, closetID) VALUES (?,?,?,?)");

            preparedStatement.setInt(1, newItem.getId());
            preparedStatement.setString(2, newItem.getName());
            preparedStatement.setInt(3, newItem.getImg());
            preparedStatement.setInt(4, closetID);

            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e){
            System.out.println(e);
        }
    }


//    public static void deleteAccount(int accountID){ //could make another method that's passed a UserAccount object instead
//        if(con == null){
//            return;
//        }
//
//        try {
//            PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM UserAccounts WHERE clothesID = ?");
//            preparedStatement.setInt(1, accountID);
//            //WOULD LIKELY NEED TO DELETE ALL CLOTHES, ALL CLOSETS, ALL OUTFITS, ETC.
//            //WILL SAVE DELETION FOR LATER
//
//            preparedStatement.executeUpdate();
//
//            preparedStatement.close();
//        } catch (SQLException e){
//            System.out.println(e);
//        }
//    }






    /*
    This is a template method for getting data from the database.
    selectString can be replaced with a 'PreparedStatement' to get
    specific data from the database
     */
//    public void selectClothes(){
//        if(con == null){
//            return;
//        }
//
//        try {
//            String selectString = "SELECT * FROM ClothesItems";
//            Statement statement = con.createStatement();
//            ResultSet resultClothes = statement.executeQuery(selectString);
//            while(resultClothes.next()){
//                System.out.println(resultClothes.getInt("clothesID") + ": " + resultClothes.getString("clothingName"));
//            }
//            statement.close();
//            resultClothes.close();
//        } catch (SQLException e){
//            System.out.println(e);
//        }
//    }




}
