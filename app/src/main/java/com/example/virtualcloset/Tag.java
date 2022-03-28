package com.example.virtualcloset;

import java.io.Serializable;

public class Tag implements Serializable {

    //Instance Variables
    String name;
    String type;

    //Constructor
    public Tag(String name, String type){
        this.name = name;
        this.type = type;
    }

    //Getters
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }
}
