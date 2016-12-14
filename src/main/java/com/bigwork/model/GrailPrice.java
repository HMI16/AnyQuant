package com.bigwork.model;

/**
 * Created by Administrator on 2016/6/18.
 */
public class GrailPrice {
    private double high;
    private double low;
    private double open;
    private double close;

    public double getHigh(){
        return high;
    }

    public double getLow() {
        return low;
    }

    public double getOpen() {
        return open;
    }

    public double getClose() {
        return close;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public void setClose(double close) {
        this.close = close;
    }

}
