package com.bigwork.model;

/**
 * Created by asus on 2016/5/30.
 */
public class MACDValue {
    private String date;
    private double diff;
    private double dea;
    private double macd;

    public MACDValue(String date, double diff, double dea, double macd) {
        this.date = date;
        this.diff = diff;
        this.dea = dea;
        this.macd = macd;
    }

    public String getDate() {

        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getDiff() {
        return diff;
    }

    public void setDiff(double diff) {
        this.diff = diff;
    }

    public double getDea() {
        return dea;
    }

    public void setDea(double dea) {
        this.dea = dea;
    }

    public double getMacd() {
        return macd;
    }

    public void setMacd(double macd) {
        this.macd = macd;
    }
}
