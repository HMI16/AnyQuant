package com.bigwork.bl.managementServiceImpl;

import com.bigwork.bl.listServiceImpl.SingleStock_Impl;
import com.bigwork.bl_service.MA_service;
import com.bigwork.bl_service.SingleStock_service;
import com.bigwork.model.MAValue;
import com.bigwork.model.Stock;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ryysuke on 16/5/12.
 */
public class MA_Impl implements MA_service {

    private String ID;
    private String date1;
    private String date2;

    private SingleStock_service single = new SingleStock_Impl();
    private CalcuDate cal = new CalcuDate();

    //private methods;
    private double getMA(List<Stock> list){
        double result = 0;
        int num = list.size();
        for(Stock tmp : list){
            result += tmp.getClose();
        }
        result /= num;
        return result;
    }

    private double getMA(int day){
        double result = 0;
        ArrayList<Stock> list = single.setTimeFromDB(ID, date1, date2);
        int num = list.size();
        if(num == 0){
            return 0;
        }
        for(int i = num - 1; i >= num - day; i--){
            result += list.get(i).getClose();
        }
        result /= day;
        return result;
    }
    //


    public double getMA(String ID, int day, String date2){
        double result = 0;
        int num = day/7;
        num = num*2+2 + day;
        this.ID = ID;
        this.date1 = cal.calDate(date2, -num);
        this.date2 = date2;
        result = getMA(day);
        return result;
    }

    public double getMA(String ID, String date1, String date2) {
        double result = 0;
        this.ID = ID;
        this.date1 = date1;
        this.date2 = date2;
        ArrayList<Stock> list = single.setTimeFromDB(ID, date1, date2);
        if(list.size() == 0){
            return 0;
        }
        result = getMA(list);
        return result;
    }

    public ArrayList<MAValue> getMAGraph(String ID, String from, String to, int day){
        ArrayList<MAValue> result = new ArrayList<>();
        String realStart = cal.calDate(from, 1-day);
        ArrayList<Stock> list = single.setTimeFromDB(ID, from, to);
        int num = list.size();
        if(num == 0){
            return null;
        }
        double sum;
        for(int i = 0; i < num - day; i++){
            sum = getMA(list.subList(i, i+day));
            BigDecimal tmp = new BigDecimal(sum);
            double value = tmp.setScale(2, BigDecimal.ROUND_CEILING).doubleValue();
            String date = list.get(i+day-1).getDate();
            MAValue maValue = new MAValue();
            maValue.setDate(date);
            maValue.setValue(value);
            result.add(maValue);
        }
        return result;
    }
    public static void main(String[] args){
        MA_service test = new MA_Impl();
        ArrayList<MAValue> list = test.getMAGraph("sh999979", "2015-05-20", "2015-11-20" ,5);
        if(list == null){
            System.out.println("this is null");
        }
//        for(MAValue m : list){
//            System.out.println("Date : " + m.getDate() + "     value : " + m.getValue());
//        }

       // System.out.println(test.getMA());
    }


}
