package com.example.virtualcloset.storage.hsqldb;

import com.example.virtualcloset.objects.Closet;
import com.example.virtualcloset.objects.ClothesItem;
import com.example.virtualcloset.objects.Outfit;
import com.example.virtualcloset.objects.Tag;
import com.example.virtualcloset.objects.UserAccount;
import com.example.virtualcloset.storage.DBPersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBPersistenceHSQLDB implements DBPersistence {

    private final String dbPath;

    public DBPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    @Override
    public ArrayList<ClothesItem> getClothesItems() {
        ArrayList<ClothesItem> clothes = new ArrayList<ClothesItem>();

        try (final Connection con = connection()){
            //Get the set of ClothesItems
            String selectString = "SELECT * FROM CLOTHESITEMS";
            Statement statement = con.createStatement();
            ResultSet resultClothes = statement.executeQuery(selectString);
            System.out.println("made it to 40");
            //Build the ClothesItem objects for each item in the DB. Add them to the list
            while(resultClothes.next()){
                int currClothesID = resultClothes.getInt("clothesID"); //gets the data from "clothesID" field
                String currClothingName = resultClothes.getString("clothingName");
                int currImg = resultClothes.getInt("clothingImg");//NOTE: many images are currently set to null in database. according to documentation, it sets the int to 0
                //ArrayList<Tag> currClothesTags = getTagsFromClothesID(currClothesID);
                int currFav = resultClothes.getInt("fav");

                ClothesItem currClothing = new ClothesItem(currClothesID, currClothingName, null, currImg);         //For testing purposes
                //ClothesItem currClothing = new ClothesItem(currClothesID, currClothingName, currClothesTags, currImg); //
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

    @Override
    public ArrayList<Outfit> getOutfits() {
        ArrayList<Outfit> outfits = new ArrayList<Outfit>();
        try (final Connection con = connection()){
            //Get the set of ClothesItems
            String selectString = "SELECT * FROM Outfits";
            Statement statement = con.createStatement();
            ResultSet resultOutfits = statement.executeQuery(selectString);

            //Build the ClothesItem objects for each item in the DB. Add them to the list
            while(resultOutfits.next()){
                int currOutfitID = resultOutfits.getInt("outfitID"); //gets the data from "outfitID" field
                String currOutfitName = resultOutfits.getString("outfitName");
                int currClosetID = resultOutfits.getInt("closetID");

                //ArrayList<ClothesItem> currOutfitSet = getClothesItemsFromOutfitID(currOutfitID);

                Outfit currOutfit = new Outfit(currOutfitID, currOutfitName, null); //for testing purposes
                //Outfit currOutfit = new Outfit(currOutfitID, currOutfitName, currOutfitSet);//
                outfits.add(currOutfit);
            }
            statement.close();
            resultOutfits.close();
        } catch (SQLException e){
            System.out.println(e);
        }
        return outfits;
    }

    @Override
    public ArrayList<UserAccount> getAccounts() {
        ArrayList<UserAccount> accounts = new ArrayList<UserAccount>();

        try (final Connection con = connection()){
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

                UserAccount currAccount = new UserAccount(currUserID, currUsername, currPassword, currEmail, currAccountClosets);
                accounts.add(currAccount);
            }
            statement.close();
            resultAccounts.close();
        } catch (SQLException e){
            System.out.println(e);
        }
        return accounts;
    }

    @Override
    public ArrayList<Tag> getTags(){
        ArrayList<Tag> tags = new ArrayList<Tag>();
        try (final Connection con = connection()){
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

        ArrayList<Tag> tags = new ArrayList<Tag>();
        try (final Connection con = connection()){
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
     * returns a single tag, based on the passed tagID
     * */
    public Tag getTagFromID(int tagID){

        Tag tag = null;
        try (final Connection con = connection()){
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

    /*
     * This method gets all the clothesItems in the database that belong to a certain outfit
     * */
    public ArrayList<ClothesItem> getClothesItemsFromOutfitID(int outfitID){


        ArrayList<ClothesItem> clothes = new ArrayList<ClothesItem>();
        try (final Connection con = connection()){
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

        ClothesItem clothesItem = null;
        try (final Connection con = connection()){
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
     * This method gets all the clothesItems in the database that belong to a certain closetID
     * */
    public ArrayList<ClothesItem> getClothesItemsFromClosetID(int closetID){


        ArrayList<ClothesItem> clothes = new ArrayList<ClothesItem>();
        try (final Connection con = connection()){
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

    public ArrayList<Outfit> getOutfitsFromClosetID(int closetID){


        ArrayList<Outfit> outfits = new ArrayList<Outfit>();
        try (final Connection con = connection()){
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
}
