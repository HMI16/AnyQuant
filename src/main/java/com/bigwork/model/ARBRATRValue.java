package com.bigwork.model;

/**
 * Created by asus on 2016/6/13.
 */
public class ARBRATRValue {
    private String date;
    private double atr;
    private double ar;
    private double br;

    public ARBRATRValue(String date, double atr, double ar, double br) {
        this.date = date;
        this.atr = atr;
        this.ar = ar;
        this.br = br;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAtr() {
        return atr;
    }

    public void setAtr(double atr) {
        this.atr = atr;
    }

    public double getAr() {
        return ar;
    }

    public void setAr(double ar) {
        this.ar = ar;
    }

    public double getBr() {
        return br;
    }

    public void setBr(double br) {
        this.br = br;
    }
}
