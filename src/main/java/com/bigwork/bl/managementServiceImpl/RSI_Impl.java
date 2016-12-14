package com.bigwork.bl.managementServiceImpl;

import com.bigwork.bl.listServiceImpl.SingleStock_Impl;
import com.bigwork.bl_service.RSI_service;
import com.bigwork.bl_service.SingleStock_service;
import com.bigwork.bl_service.VR_service;
import com.bigwork.model.RSIValue;
import com.bigwork.model.Stock;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ryysuke on 16/5/12.
 */
public class RSI_Impl implements RSI_service {

    private String ID;
    private int n;
    private String start;
    private String end;

    private SingleStock_service single= new SingleStock_Impl();
    private CalcuDate cal = new CalcuDate();

    private double getRSI(ArrayList<Stock> list){
        double result = 0.0;
        double rise = this.getRise(list);
        double dec = this.getDec(list);
       // System.out.println(rise);
       // System.out.println(dec);
        if(rise + dec==0){
            return 0;
        }
        result = rise * 100 / (rise + dec);
        System.out.println(result);
        BigDecimal b = new BigDecimal(result);
        double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return f1;
    }

    public double getRSI(String ID, String date1, String date2){
        this.ID = ID;
        this.start = date1;
        this.end = date2;
        ArrayList<Stock> list = single.setTimeFromDB(ID, this.start,this.end);
        if(list.size() == 0){
            return 0;
        }
        return  this.getRSI(list);
    }

    public double getRSI(String ID, int day, String date){
        this.ID = ID;
        this.end = date;
        int num = day/7;
        num = day + num * 2 + 2;
        System.out.println("num = " + num);
        this.start = cal.calDate(date, -num);
        ArrayList<Stock> list = single.setTimeFromDB(ID, this.start,this.end);
        if(list.size() == 0){
            return 0;
        }
        return this.getRSI(list);
    }

    public double getRSI(String ID,  String date){
        return this.getRSI(ID,6,date);
    }

    public ArrayList<RSIValue> getRSIGraphValue(String id, String from, String to, int day){
        this.ID = id;
        this.end = to;
        int num = day/7;
        num = day + num * 2 + 2;
        System.out.println("num = " + num);

        ArrayList<Stock> list = single.setTimeFromDB(ID, from,this.end);
        if(list.size() == 0){
            return null;
        }
        ArrayList<Stock> all = new ArrayList<>();
        ArrayList<Stock> A = new ArrayList<>();

        ArrayList<RSIValue> result = new ArrayList<RSIValue>();
        this.start = cal.calDate(from, -num);
        all = single.setTimeFromDB(ID, this.start,this.end);
        A = single.setTimeFromDB(ID, this.start,from);
        for(int i=0;i<list.size();i++){
            ArrayList<Stock> temp = new ArrayList<>();
            for(int j =0;j<A.size()-1;j++){
                temp.add(all.get(i+j));
            }
            result.add(new RSIValue(list.get(i).getDate(),this.getRSI(temp)));
        }
        return result;
    }

    public ArrayList<RSIValue> getRSIGraphValue(String id, String from, String to){
        return this.getRSIGraphValue(id,from,to,6);
    }
    private double getRise(ArrayList<Stock> list){
        double r = 0;
        for(int a = 1; a<list.size(); a++){
            if(list.get(a).getClose() > list.get(a-1).getClose()){
                r += (list.get(a).getClose() - list.get(a-1).getClose());
            }
        }
        r /= (list.size()-1) ;
        return r;
    }

    private double getDec(ArrayList<Stock> list){
        double d = 0;
        for(int a = 1; a<list.size(); a++){
            if(list.get(a).getClose() < list.get(a-1).getClose()){
//                System.out.println("dec "+(list.get(a-1).getClose() - list.get(a).getClose()));

                d += (list.get(a-1).getClose() - list.get(a).getClose());
            }
        }
        d /= (list.size()-1);
        return d;
    }


    public static void main(String[] args){
        RSI_service test = new RSI_Impl();
        System.out.println(test.getRSIGraphValue("sz002644","2015-05-13", "2016-05-23",6).size());
    }

}
