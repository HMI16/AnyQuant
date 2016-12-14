package com.bigwork.bl.managementServiceImpl;

import com.bigwork.bl.listServiceImpl.SingleStock_Impl;
import com.bigwork.bl_service.OBV_service;
import com.bigwork.bl_service.SingleStock_service;
import com.bigwork.model.OBVValue;
import com.bigwork.model.Stock;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by ryysuke on 16/5/12.
 */
public class OBV_Impl implements OBV_service {

    private String ID;
    private String start;
    private String end;

    private SingleStock_service single= new SingleStock_Impl();
    private CalcuDate cal = new CalcuDate();


    public double getOBV(ArrayList<Stock> list){
        double obv = 0.0;
        obv = list.get(0).getVolume();
        for(int a = 1; a < list.size(); a++){
            if(list.get(a).getVolume() < list.get(a - 1).getVolume()){
                obv -= list.get(a).getVolume();
            }
            else{
                obv += list.get(a).getVolume();
            }
        }

        BigDecimal b = new BigDecimal(obv);
        double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return f1;
    }

    public ArrayList<OBVValue> getOBVGraphValue(String id, String from, String to){

        ArrayList<Stock> list = single.setTimeFromDB(id, from,to);
        if(list.size() == 0){
            return null;
        }
        ArrayList<OBVValue> result = new ArrayList<OBVValue>();
        double obv = 0.0;
        obv = list.get(0).getVolume();
        for(int a = 1; a < list.size(); a++){
            //System.out.println("---");
            if(list.get(a).getVolume() < list.get(a - 1).getVolume()){
                obv -= list.get(a).getVolume();
            }
            else{
                obv += list.get(a).getVolume();
            }
            result.add(new OBVValue(list.get(a).getDate(),obv));
        }
        return result;
    }

    public static void main(String[] args){
        OBV_service test = new OBV_Impl();
        if(test.getOBVGraphValue("sz999644", "2015-10-21", "2015-12-21")==null){
            System.out.println("this is null");
        }

    }
}
