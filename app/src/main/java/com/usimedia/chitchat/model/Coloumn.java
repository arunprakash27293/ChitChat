package com.usimedia.chitchat.model;

/**
 * Created by USI IT on 6/4/2016.
 */
public class Coloumn {
    String name;
    String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Coloumn(String name, String type) {

        this.name = name;
        this.type = type;
    }
}
