package com.bigwork.model;

/**
 * Created by asus on 2016/5/29.
 */
public class KDJValue {
    private String date;
    private double K;
    private double D;
    private double J;

    public KDJValue(String date, double K, double D, double J){
        this.date = date;
        this.K = K;
        this.D = D;
        this.J = J;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getK() {
        return K;
    }

    public void setK(double k) {
        K = k;
    }

    public double getD() {
        return D;
    }

    public void setD(double d) {
        D = d;
    }

    public double getJ() {
        return J;
    }

    public void setJ(double j) {
        J = j;
    }
}
