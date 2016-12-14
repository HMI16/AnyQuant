package com.bigwork.model;

/**
 * Created by Administrator on 2016/6/13.
 */
public class KGraphValue {
    private String date;
    private Double open;
    private Double close;
    private Double high;
    private Double low;
    private double obv;

    public void setDate(String date) {
        this.date = date;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public String getDate() {
        return date;
    }

    public Double getOpen() {
        return open;
    }

    public Double getClose() {
        return close;
    }

    public Double getHigh() {
        return high;
    }

    public Double getLow() {
        return low;
    }

    public double getObv() {
        return obv;
    }

    public void setObv(double obv) {
        this.obv = obv;
    }
}
