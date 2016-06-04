package com.usimedia.chitchat.model;

import java.util.Date;

/**
 * Created by USI IT on 6/3/2016.
 */
public class ChatContact {
    private  String name;
    private Date lastseen;
    private String status;

    public Date getLastseen() {
        return lastseen;
    }

    public void setLastseen(Date lastseen) {
        this.lastseen = lastseen;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public ChatContact() {
    }


}
