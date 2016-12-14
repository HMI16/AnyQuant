package com.bigwork.model;

/**
 * Created by asus on 2016/6/13.
 */
public class PR2Value {
    private String date;
    private double pr20;
    private double pr40;

    public PR2Value(String date, double pr20, double pr40) {
        this.date = date;
        this.pr20 = pr20;
        this.pr40 = pr40;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPr20() {
        return pr20;
    }

    public void setPr20(double pr20) {
        this.pr20 = pr20;
    }

    public double getPr40() {
        return pr40;
    }

    public void setPr40(double pr40) {
        this.pr40 = pr40;
    }
}
