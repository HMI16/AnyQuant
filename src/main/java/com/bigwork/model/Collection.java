package com.bigwork.model;

/**
 * Created by 15HR-1528SS on 2016/5/30.
 */
public class Collection {
    private String stock_id;
    private String user_name;

    public Collection() {
        stock_id = "";
        user_name = "";
    }

    public Collection(String id, String name) {
        this.stock_id = id;
        this.user_name = name;
    }

    public String getStock_id() {
        return stock_id;
    }

    public void setStock_id(String stock_id) {
        this.stock_id = stock_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
