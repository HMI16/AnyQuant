package com.bigwork.model;

/**
 * Created by Administrator on 2016/6/2.
 */
public class VRValue {
    private String date;
    private Double vr;

    public VRValue(String date, Double vr){
        this.date = date;
        this.vr = vr;
    }

    public String getDate(){
        return this.date;
    }

    public Double getVr() {
        return vr;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setVr(Double vr) {
        this.vr = vr;
    }
}
