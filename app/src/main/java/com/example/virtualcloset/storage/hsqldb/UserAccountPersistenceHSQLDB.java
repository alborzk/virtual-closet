package com.example.virtualcloset.storage.hsqldb;

import com.example.virtualcloset.ClothesItem;
import com.example.virtualcloset.Tag;
import com.example.virtualcloset.storage.UserAccountPersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserAccountPersistenceHSQLDB implements UserAccountPersistence {
    private final String dbPath;

    public UserAccountPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException{
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

}
