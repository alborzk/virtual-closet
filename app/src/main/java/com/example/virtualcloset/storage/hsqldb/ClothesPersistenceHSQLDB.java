package com.example.virtualcloset.storage.hsqldb;

import com.example.virtualcloset.storage.ClothesPersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ClothesPersistenceHSQLDB implements ClothesPersistence {
    private final String dbPath;

    public ClothesPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }


}
