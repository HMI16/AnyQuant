package com.bigwork.bl.managementServiceImpl;

import com.bigwork.bl.listServiceImpl.SingleStock_Impl;
import com.bigwork.bl_service.ARBR_service;
import com.bigwork.bl_service.SingleStock_service;
import com.bigwork.model.ARBRValue;
import com.bigwork.model.Stock;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ryysuke on 16/5/12.
 */
public class ARBR_Impl implements ARBR_service {


    private String ID;
    private String from;
    private String to;
    private int day;


    private SingleStock_service single = new SingleStock_Impl();
    private CalcuDate cal = new CalcuDate();

    @Override
    public ArrayList<ARBRValue> getARBRGraph(String id, String from, String to, int day) {
        this.ID = id;
        this.from = from;
        this.to = to;
        this.day = day;
        return getARBR();
    }

    @Override
    public ArrayList<ARBRValue> getARBRGraph(String id, String from, String to) {
        this.ID = id;
        this.from = from;
        this.to = to;
        this.day = 26;
        return getARBR();
    }

    private ArrayList<ARBRValue> getARBR(){
        ArrayList<ARBRValue> result = new ArrayList<>();

        int num = day/7;
        num = num*2 + 2 + 1;
        String realStart = cal.calDate(from, -num-day);
        ArrayList<Stock> list = single.setTimeFromDB(ID, realStart, to);
        if(list.size()==0){
            return null;
        }

        int size = list.size();

        for(int i = 1; i < size - day; i++){
            double a1 = getARSum1(list.subList(i, day+i));
            double a2 = getARSum2(list.subList(i, day+i));
            double b1 = getBRSum1(list.subList(i-1, day+i));
            double b2 = getBRSum2(list.subList(i-1, day+i));
            double AR = a1/a2*100;
            double BR = b1/b2*100;
            BigDecimal ar = new BigDecimal(AR);
            BigDecimal br = new BigDecimal(BR);
            AR = ar.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            BR = br.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            String date = list.get(day+i).getDate();
            ARBRValue arbrValue = new ARBRValue(date, AR, BR);
            result.add(arbrValue);
        }

        return result;
    }

    private double getARSum1(List<Stock> list){
        double result = 0;
        for(Stock stock : list){
            result += stock.getHigh() - stock.getOpen() ;
           // System.out.println("high : " + stock.getHigh() + "  open : " + stock.getOpen());
        }
        return result;
    }

    private double getARSum2(List<Stock> list){
        double result = 0;
        for(Stock stock : list){
            result += stock.getOpen() - stock.getLow();
            //System.out.println("high : " + stock.getHigh() + "  open : " + stock.getOpen());
        }
        return result;

    }

    private double getBRSum1(List<Stock> list){
        double result = 0;
        int num = list.size();
        for(int i = 1; i < num; i++){
            result+= list.get(i).getHigh() - list.get(i - 1).getClose();
        }
        return result;
    }

    private double getBRSum2(List<Stock> list){
        double result = 0;
        int num = list.size();
        for(int i = 1; i < num; i++){
            result+=  list.get(i - 1).getClose() - list.get(i).getLow();
        }
        return result;
    }

    public static void main(String[] args) {
        ARBR_service t =new ARBR_Impl();
        ArrayList<ARBRValue> r = t.getARBRGraph("sh999979", "2015-03-01", "2015-11-12");
//        for(ARBRValue a : r){
//            System.out.println("Date : " + a.getDate() + "  AR : " + a.getAR() + "  BR : " + a.getBR());
//        }
        if(r == null){
            System.out.println("this is null");
        }
    }
}
