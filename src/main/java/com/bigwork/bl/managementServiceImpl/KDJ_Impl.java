package com.bigwork.bl.managementServiceImpl;

import com.bigwork.bl.listServiceImpl.SingleStock_Impl;
import com.bigwork.bl_service.KDJ_service;
import com.bigwork.bl_service.SingleStock_service;
import com.bigwork.model.KDJValue;
import com.bigwork.model.Stock;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ryysuke on 16/5/13.
 */
public class KDJ_Impl implements KDJ_service {

    ArrayList<Double> k = new ArrayList<>();
    ArrayList<Double> d = new ArrayList<>();
    ArrayList<Double> j = new ArrayList<>();
    ArrayList<Double> rsv = new ArrayList<>();

    String id;
    String from;
    String to;
    int day;

    SingleStock_service single = new SingleStock_Impl();
    CalcuDate cal = new CalcuDate();

    private ArrayList<KDJValue> getKDJs(){
        int num = day/7;
        num = num*2+2 + day;
        String realStart = cal.calDate(from, -num);
        ArrayList<Stock> list = single.setTimeFromDB(id, realStart, to);
        if(list.size()==0){
            return null;
        }
        
        ArrayList<KDJValue> result = new ArrayList<>();
        int size = list.size();
        for(int i = day - 1; i < size; i++){
            double l = getLow(list.subList(i - day + 1, i + 1));
            double h = getHigh(list.subList(i - day + 1, i + 1));
            double c = list.get(i).getClose();
//            System.out.println("c="+c+",l="+l+",h="+h);
            rsv.add((c - l) / (h - l) * 100);
        }
        initArrayData(list);
        int n = rsv.size();
        for(int i = 0; i < n; i++){
           // System.out.println("list.size=" + size + "      real pr = " + (i+day-1));
            String date = list.get(i+day-1).getDate();
            BigDecimal tmp = new BigDecimal(k.get(i));
            double kValue = tmp.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
            tmp = new BigDecimal(d.get(i));
            double dValue = tmp.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
            tmp = new BigDecimal(j.get(i));
            double jValue = tmp.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
            KDJValue kdjValue = new KDJValue(date, kValue, dValue, jValue);
            result.add(kdjValue);
        }
        return result;
    }

    private void initArrayData(ArrayList<Stock> list){
        k.add(2.0/3 * 50 + 1.0/3*rsv.get(0));
        d.add(2.0/3*50+1.0/3*k.get(0));
        j.add(3*k.get(0) - 2*d.get(0));

        int num = rsv.size();
        for(int i = 1; i  < num; i ++){
            k.add(2.0/3*k.get(i-1) + 1.0/3*rsv.get(i));
            d.add(2.0/3*d.get(i-1) + 1.0/3*k.get(i));
            j.add(3*k.get(i) - 2*d.get(i));
        }


    }

    private double getHigh(List<Stock> list){
        double max = list.get(0).getHigh();
        int num = list.size();
        for(Stock stock : list){
            if(max < stock.getHigh()){
                max = stock.getHigh();
            }
        }
        return max;
    }

    private double getLow(List<Stock> list){
        double min = list.get(0).getLow();
        int num = list.size();
        for(Stock stock : list){
            if(min > stock.getLow()){
                min = stock.getLow();
            }
        }
        return min;
    }





    @Override
    public ArrayList<KDJValue> getKDJGraph(String ID, String from, String to, int day) {
        this.id = ID;
        this.from = from;
        this.to = to;
        this.day = day;

        ArrayList<KDJValue> result = getKDJs();
        rsv.clear();
        k.clear();
        d.clear();
        j.clear();
        return result;
    }

//    public static void main(String[] args){
//        KDJ_service test = new KDJ_Impl();
//        for(int i = 0;i<5;i++) {
//            ArrayList<KDJValue> re = test.getKDJGraph("sz002644", "2015-01-15", "2015-12-29", 9);
//            for (KDJValue k : re) {
//                System.out.println("Date: " + k.getDate() + " k: " + k.getK() + " d: " + k.getD()
//                        + " j: " + k.getJ());
//            }
//            System.out.println("------------------------------------我是分割线---------------------------");
//        }
//}
}
