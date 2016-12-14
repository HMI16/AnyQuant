package com.bigwork.model;

/**
 * Created by asus on 2016/5/30.
 */
public class ARBRValue {
    private String date;
    private double AR;
    private double BR;

    public ARBRValue(String date, double AR, double BR) {
        this.date = date;
        this.AR = AR;
        this.BR = BR;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAR() {
        return AR;
    }

    public void setAR(double AR) {
        this.AR = AR;
    }

    public double getBR() {
        return BR;
    }

    public void setBR(double BR) {
        this.BR = BR;
    }
}
