package com.usimedia.chitchat.model;

import com.google.common.base.Objects;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by USI IT on 6/3/2016.
 */
public class ChatContact implements Serializable{
    private  String name;
    private Date lastseen;
    private String status;
    private  String email;




    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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



    @Override
    public String toString() {
        return "ChatContact{" +
                "name='" + name + '\'' +
                ", lastseen=" + lastseen +
                ", status='" + status + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatContact that = (ChatContact) o;
        return Objects.equal(name, that.name) &&
                Objects.equal(lastseen, that.lastseen) &&
                Objects.equal(status, that.status) &&
                Objects.equal(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, lastseen, status, email);
    }

    public ChatContact() {
    }


}
