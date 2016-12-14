package com.bigwork.bl.managementServiceImpl;

import com.bigwork.bl.listServiceImpl.SingleStock_Impl;
import com.bigwork.bl_service.SingleStock_service;
import com.bigwork.bl_service.VR_service;
import com.bigwork.model.Stock;
import com.bigwork.model.VRValue;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ryysuke on 16/5/12.
 */
public class VR_Impl implements VR_service {

    private String ID;
    private int n;
    private String date1;
    private String date2;

    private SingleStock_service single = new SingleStock_Impl();
    private CalcuDate cal = new CalcuDate();



    public double getVR(String ID, String date1, String date2){
        this.ID = ID;
        this.date1 = date1;
        this.date2 = date2;
        ArrayList<Stock> list = single.setTimeFromDB(ID, this.date1,this.date2);
        if(list.size() == 0){
            return 0;
        }
        System.out.println("size = " + list.size());
        return this.getVR(list);
    }

    public double getVR(String ID, int day, String date){
        this.ID = ID;
        this.date2 = date;
        int num = day/7;
        num = day + num * 2 + 2 + 3;
        System.out.println("num = " + num);
        this.date1 = cal.calDate(date, -num);
        ArrayList<Stock> list = single.setTimeFromDB(ID, this.date1,this.date2 = date2);
        if(list.size() == 0){
            return 0;
        }
        System.out.println("size = " + list.size());
        return this.getVR(list);
    }

    public double getVR(String ID,String date){
        return this.getVR(ID,26,date);
    }

    public  ArrayList<VRValue> getVRGraphValue(String id, String from, String to, int day){
        this.ID = id;
        this.date2 = to;
        int num = day/7;
        num = day + num * 2 + 2;
        System.out.println("num = " + num);

        ArrayList<Stock> list = single.setTimeFromDB(ID, from,this.date2);
        if(list.size() == 0){
            return null;
        }
       // System.out.println("list = " + list.size());
        ArrayList<Stock> all = new ArrayList<>();
        ArrayList<Stock> A = new ArrayList<>();
        ArrayList<VRValue> result = new ArrayList<VRValue>();
        this.date1 = cal.calDate(from, -num);
        all = single.setTimeFromDB(ID, this.date1,this.date2);
        A = single.setTimeFromDB(ID, this.date1,from);
       // System.out.println("A = " + A.size());
       // System.out.println("all = " + all.size());
        for(int i=0;i<list.size();i++){
            //this.date1 = cal.calDate(list.get(i).getDate(), -num);
            ArrayList<Stock> temp = new ArrayList<>();
            for(int j =0;j<A.size()-1;j++){
                temp.add(all.get(i+j));
            }
            //temp.addAll(list);
            //temp = single.setTimeFromDB(ID, this.date1,list.get(i).getDate());
            result.add(new VRValue(list.get(i).getDate(),this.getVR(temp)));
        }
        return result;
    }

    public  ArrayList<VRValue> getVRGraphValue(String id, String from, String to){
        return this.getVRGraphValue(id,from,to,26);
    }

    private double getVR(ArrayList<Stock> list){
        double result = 0.0;
        single = new SingleStock_Impl();
        //list = single.setTimeFromDB(ID, date1,date2);
        int up = this.getUp(list);
        int down = this.getDown(list);
        if(down == 0){
            return 400;
        }
        result = up * 100 / down;
        BigDecimal b = new BigDecimal(result);
        double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return f1;
    }


    private int getUp(ArrayList<Stock> list){
        int up = 0;
        for(Stock temp : list){
            if(temp.getClose() > temp.getOpen()){
                up++;
            }
        }
        return up;
    }

    private int getDown(ArrayList<Stock> list){
        int down = 0;
        for(Stock temp : list){
            if(temp.getClose() < temp.getOpen()){
                down++;
            }
        }
        return down;
    }

    public static void main(String[] args){
        VR_service test = new VR_Impl();
        System.out.println(test.getVRGraphValue("sz002644","2015-05-09","2016-05-20",13).size());
        System.out.println(test.getVRGraphValue("sz002644","2015-05-09","2016-05-20",26).size());
        System.out.println(test.getVRGraphValue("sz002644","2015-05-09","2016-05-20",52).size());
        System.out.println(test.getVRGraphValue("sz002644","2016-06-01","2016-06-13",13).size());
    }

}
