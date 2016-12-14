package com.bigwork.model;

/**
 * Created by asus on 2016/6/11.
 */
public class VR3Value {
    private String date;
    private double vr13;
    private double vr26;
    private double vr52;

    public VR3Value(String date, double vr13, double vr26, double vr52) {
        this.date = date;
        this.vr13 = vr13;
        this.vr26 = vr26;
        this.vr52 = vr52;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getVr13() {
        return vr13;
    }

    public void setVr13(double vr13) {
        this.vr13 = vr13;
    }

    public double getVr26() {
        return vr26;
    }

    public void setVr26(double vr26) {
        this.vr26 = vr26;
    }

    public double getVr52() {
        return vr52;
    }

    public void setVr52(double vr52) {
        this.vr52 = vr52;
    }
}
