package com.example.virtualcloset.application;

import com.example.virtualcloset.storage.DBPersistence;
import com.example.virtualcloset.storage.hsqldb.DBPersistenceHSQLDB;

public class Services {
//    private static ClothesPersistence clothesPersistence = null;
//    private static UserAccountPersistence userAccountPersistence = null;
    private static DBPersistence dbPersistence = null;

//    public static synchronized ClothesPersistence getClothesPersistence(){
//        if(clothesPersistence == null){
//            clothesPersistence = new ClothesPersistenceHSQLDB(Main.getDBPathName());
//        }
//        return clothesPersistence;
//    }
//
//    public static synchronized UserAccountPersistence getUserAccountPersistence(){
//        if (userAccountPersistence == null){
//            userAccountPersistence = new UserAccountPersistenceHSQLDB(Main.getDBPathName());
//        }
//        return userAccountPersistence;
//    }

    public static synchronized DBPersistence getDbPersistence(){
        if (dbPersistence == null){
            dbPersistence = new DBPersistenceHSQLDB(Main.getDBPathName());
        }
        return dbPersistence;
    }
}
