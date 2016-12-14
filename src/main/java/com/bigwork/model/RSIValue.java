package com.bigwork.model;

/**
 * Created by Administrator on 2016/5/28.
 */
public class RSIValue {
    private String date;
    private Double rsi;

    public RSIValue(String date, Double rsi){
        this.date = date;
        this.rsi = rsi;
    }

    public String getdate(){
        return this.date;
    }

    public Double getRsi() {
        return rsi;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setRsi(Double rsi) {
        this.rsi = rsi;
    }
}
