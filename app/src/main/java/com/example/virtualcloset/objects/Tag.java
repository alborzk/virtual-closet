package com.example.virtualcloset.objects;

import java.io.Serializable;

public class Tag implements Serializable {

    //Instance Variables
    private int id;
    private String name;

    //Constructor
    public Tag(int id, String name) {
        this.id = id;
        this.name = name;
    }

    //Getters
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }
}
