//package com.example.virtualcloset;
//
//import com.example.virtualcloset.storage.Database;
//import com.example.virtualcloset.storage.SQLDatabase;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.ArrayList;
//
//public class SQLDatabaseTest {
//    SQLDatabase db;
//    Database stub;
//
//    @Before
//    public void setUp() throws Exception{
//        db = new SQLDatabase();
//        stub = new Database();
//    }
//
//    @After
//    public void tearDown() throws Exception{
//    }
//
//    @Test
//    public void connectionTest(){
//        assert(db.getConnection());
//    }
//
////    @Test
////    public void testImages(){
////        ArrayList<ClothesItem> list = stub.getClothesItems();
////        for(int i = 0; i < list.size(); i++){
////            System.out.println(list.get(i).getName() + ": " + list.get(i).getImg());
////        }
////    }
//
////    @Test
////    public void queryTest(){
////        db.getConnection();
////        db.selectClothes();
////    }
//}
