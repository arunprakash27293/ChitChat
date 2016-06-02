package com.usimedia.chitchat.model;

/**
 * Created by USI IT on 5/31/2016.
 */
public class Result {
    public Result() {
    }

    private  String name;
    private  Boolean response;

    public Boolean getResponse() {
        return response;
    }

    public void setResponse(Boolean response) {
        this.response = response;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
