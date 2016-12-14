package com.bigwork.model;

/**
 * Created by asus on 2016/6/18.
 */
public class RSI3Value {
    private String date;
    private double rsi6;
    private double rsi12;
    private double rsi24;

    public RSI3Value(String date, double rsi6, double rsi12, double rsi24) {
        this.date = date;
        this.rsi6 = rsi6;
        this.rsi12 = rsi12;
        this.rsi24 = rsi24;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getRsi6() {
        return rsi6;
    }

    public void setRsi6(double rsi6) {
        this.rsi6 = rsi6;
    }

    public double getRsi12() {
        return rsi12;
    }

    public void setRsi12(double rsi12) {
        this.rsi12 = rsi12;
    }

    public double getRsi24() {
        return rsi24;
    }

    public void setRsi24(double rsi24) {
        this.rsi24 = rsi24;
    }
}
