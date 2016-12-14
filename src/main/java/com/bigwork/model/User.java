package com.bigwork.model;

/**
 * Created by 15HR-1528SS on 2016/5/30.
 */
public class User {
    private String name;
    private String password;

    public User() {
        name = "";
        password = "";
    }

    public User(String name) {
        this.name = name;
        password = "";
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
