package com.bigwork.model;

/**
 * Created by Administrator on 2016/5/28.
 */
public class OBVValue {
    private String date;
    private Double obv;

    public OBVValue(String date, Double obv){
        this.date = date;
        this.obv = obv;
    }

    public String getdate(){
        return this.date;
    }

    public Double getObvsi() {
        return obv;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setObv(Double obv) {
        this.obv = obv;
    }
}
