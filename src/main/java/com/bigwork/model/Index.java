package com.bigwork.model;

/**
 * Created by 15HR-1528SS on 2016/6/17.
 */
public class Index {
    private String id;
    private double flu;
    private double change;

    public Index() {
        this.id = "";
        this.flu = 0;
        this.change = 0;
    }

    public Index(String id, double flu, double change) {
        this.id = id;
        this.flu = flu;
        this.change = change;
    }

    public double getFlu() {
        return flu;
    }

    public void setFlu(double flu) {
        this.flu = flu;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }

}
