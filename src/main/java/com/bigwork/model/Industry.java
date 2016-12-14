package com.bigwork.model;

/**
 * Created by asus on 2016/5/9.
 */

public class Industry {

    private String name;
    private int rank;

    public Industry(String name, int rank) {
        this.name = name;
        this.rank = rank;
    }

    public String getName() {
        return this.name;
    }

    public int getRank() {
        return this.rank;
    }
}
