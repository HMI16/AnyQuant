package com.bigwork.model;

/**
 * Created by Administrator on 2016/5/28.
 */
public class pRValue {
    private String date;
    private Double pr;

    public pRValue(String date, Double pr){
        this.date = date;
        this.pr = pr;
    }

    public String getdate(){
        return this.date;
    }

    public Double getpr() {
        return pr;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setpr(Double pr) {
        this.pr = pr;
    }
}
