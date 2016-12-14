package com.bigwork.bl.managementServiceImpl;

import com.bigwork.bl.listServiceImpl.SingleStock_Impl;
import com.bigwork.bl_service.SingleStock_service;
import com.bigwork.bl_service.pR_service;
import com.bigwork.model.Stock;
import com.bigwork.model.pRValue;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ryysuke on 16/5/12.
 */
public class pR_Impl implements pR_service {

    private String ID;
    private int n;
    private String end;
    private String start;

    private SingleStock_service single= new SingleStock_Impl();
    private CalcuDate cal = new CalcuDate();



    private double getpR( ArrayList<Stock> list){
        double result = 0.0;
        double ln = 0.0;
        double hn = 0.0;
        double c = 0.0;

        try {
            ln = this.getLow(list);
            hn = this.getHigh(list);
            int num = list.size();
            c = list.get(num - 1).getClose();
            result = 100 - (c - ln) * 100 / (hn - ln);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        BigDecimal b = new BigDecimal(result);
        double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return f1;
    }

    public double getpR(String ID, String date1, String date2){
        this.ID = ID;
        this.start = date1;
        this.end = date2;
        ArrayList<Stock> list = single.setTimeFromDB(ID, this.start,this.end);
        if(list.size() == 0){
            return 0;
        }
        return this.getpR(list);
    }

    public double getpR(String ID, int day, String date){
        this.ID = ID;
        this.end = date;
        int num = day/7;
        num = day + num * 2 + 2 + 3;
        System.out.println("num = " + num);
        this.start = cal.calDate(date, -num);
        ArrayList<Stock> list = single.setTimeFromDB(ID, this.start,this.end);
        if(list.size() == 0){
            return 0;
        }
        return this.getpR(list);
    }
    public double getpR(String ID, String date){
        return this.getpR(ID,20,date);
    }

    public ArrayList<pRValue> getpRGraphValue(String id, String from, String to, int day){
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

        ArrayList<pRValue> result = new ArrayList<pRValue>();
        this.start = cal.calDate(from, -num);
        all = single.setTimeFromDB(ID, this.start,this.end);
        A = single.setTimeFromDB(ID, this.start,from);
        for(int i=0;i<list.size();i++){
            ArrayList<Stock> temp = new ArrayList<>();
            for(int j =0;j<A.size()-1;j++){
                temp.add(all.get(i+j));
            }
            result.add(new pRValue(list.get(i).getDate(),this.getpR(temp)));
        }
        return result;
    }
    public ArrayList<pRValue> getpRGraphValue(String id, String from, String to){
        return this.getpRGraphValue(id, from, to ,20);
    }

    private double getLow(ArrayList<Stock> list){
        double ln = list.get(0).getClose();
        for(Stock temp : list){
            if(temp.getClose() <= ln){    // 是close的是吧....
                ln = temp.getClose();
            }
        }
        return ln;
    }

    private double getHigh(ArrayList<Stock> list){
        double hn = list.get(0).getClose();
        for(Stock temp : list){
            if(temp.getClose() >= hn){
                hn = temp.getClose();
            }
        }
        return hn;
    }


    public static void main(String[] args){
        pR_service test = new pR_Impl();
        if(test.getpRGraphValue("sz9992644","2015-05-03", "2016-05-23",20) == null){
            System.out.println("this is null");
        }

    }
}
