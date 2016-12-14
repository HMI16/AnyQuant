package com.bigwork.model;

/**
 * Created by asus on 2016/6/4.
 */
public class HotStock {
    private String type;
    private double increase;

    public HotStock(String type, double increase) {
        this.type = type;
        this.increase = increase;
    }

    public HotStock() {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getIncrease() {
        return increase;
    }

    public void setIncrease(double increase) {
        this.increase = increase;
    }
}
