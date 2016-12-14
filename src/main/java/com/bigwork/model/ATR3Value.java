package com.bigwork.model;

/**
 * Created by asus on 2016/6/17.
 */
public class ATR3Value {
    private String date;
    private double atr6;
    private double atr26;
    private double atr65;

    public ATR3Value(String date, double atr6, double atr26, double atr65) {
        this.date = date;
        this.atr6 = atr6;
        this.atr26 = atr26;
        this.atr65 = atr65;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAtr6() {
        return atr6;
    }

    public void setAtr6(double atr6) {
        this.atr6 = atr6;
    }

    public double getAtr26() {
        return atr26;
    }

    public void setAtr26(double atr26) {
        this.atr26 = atr26;
    }

    public double getAtr65() {
        return atr65;
    }

    public void setAtr65(double atr65) {
        this.atr65 = atr65;
    }
}
