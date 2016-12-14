package com.bigwork.model;

/**
 * Created by asus on 2016/5/7.
 */
public class Grail {
    final private String split = ":%:%:";

    private String id;
    private String date;
    private double high;
    private double open;
    private double close;
    private String volume;
    private double adj_price;
    private double low;
    private String name = "上证指数";
    public Grail() {

    }

    public Grail(String date, int flag) {
        this.date = date;
    }

    public Grail(String id, String date, double high, double open,
                 double close, String volume, double adj_price, double low) {
        super();
        this.id = id;
        this.date = date;
        this.high = high;
        this.open = open;
        this.close = close;
        this.volume = volume;
        this.adj_price = adj_price;
        this.low = low;
    }

    public Grail(String str) {
        super();
        String[] strs = str.split(this.split);
        this.id = strs[0];
        this.date = strs[1];
        this.high = Double.parseDouble(strs[2]);
        this.open = Double.parseDouble(strs[3]);
        this.close = Double.parseDouble(strs[4]);
        this.volume = strs[5];
        this.adj_price = Double.parseDouble(strs[6]);
        this.low = Double.parseDouble(strs[7]);
    }

    public String toString() {
        return this.id + this.split + this.date + this.split + this.high + this.split + this.open
                + this.split + this.close + this.split + this.volume + this.split + this.adj_price
                + this.split + this.low;
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public double getAdj_price() {
        return adj_price;
    }

    public void setAdj_price(double adj_price) {
        this.adj_price = adj_price;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public String getName(){
        return name;
    }

}
