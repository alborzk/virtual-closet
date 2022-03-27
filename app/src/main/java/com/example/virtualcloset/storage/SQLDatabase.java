package com.example.virtualcloset.storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//Will 'implement' the IDatabase interface
public class SQLDatabase {
    private static Connection con;

    public SQLDatabase(){
        //this.getConnection();
    }

    /*

     */
    public boolean getConnection(){
        boolean connectionEstablished = false;

        //Class.forName("org.sqlite.JDBC");
        String jdbcURL = "jdbc:sqlite:VirtualClosetDatabase.db";
        try{
            con = DriverManager.getConnection(jdbcURL);
            connectionEstablished = true;
            //System.out.println("Connection Established");
        } catch (SQLException e) {
            System.out.println(e);
        }

        return connectionEstablished;
    }


    /*
    This is a template method for getting data from the database.
    selectString can be replaced with a 'PreparedStatement' to get
    specific data from the database
     */
    public void selectClothes(){
        if(con == null){
            return;
        }

        try {
            String selectString = "SELECT * FROM ClothesItems";
            Statement statement = con.createStatement();
            ResultSet resultClothes = statement.executeQuery(selectString);
            while(resultClothes.next()){
                System.out.println(resultClothes.getInt("clothesID") + ": " + resultClothes.getString("clothingName"));
            }
            statement.close();
            resultClothes.close();
        } catch (SQLException e){
            System.out.println(e);
        }
    }



}
