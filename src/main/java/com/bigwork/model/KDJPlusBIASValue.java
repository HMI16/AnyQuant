package com.bigwork.model;

/**
 * Created by asus on 2016/6/11.
 */
public class KDJPlusBIASValue {
    private String date;
    private double k;
    private double d;
    private double j;
    private double bias;

    public KDJPlusBIASValue(String date, double k, double d, double j, double bias) {
        this.date = date;
        this.k = k;
        this.d = d;
        this.j = j;
        this.bias = bias;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getK() {
        return k;
    }

    public void setK(double k) {
        this.k = k;
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }

    public double getJ() {
        return j;
    }

    public void setJ(double j) {
        this.j = j;
    }

    public double getBias() {
        return bias;
    }

    public void setBias(double bias) {
        this.bias = bias;
    }
}
