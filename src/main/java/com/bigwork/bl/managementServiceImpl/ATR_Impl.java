package com.bigwork.bl.managementServiceImpl;


import com.bigwork.bl.listServiceImpl.SingleStock_Impl;
import com.bigwork.bl_service.ATR_service;
import com.bigwork.bl_service.SingleStock_service;
import com.bigwork.model.ATRValue;
import com.bigwork.model.Stock;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;

public class ATR_Impl implements ATR_service {
    //均幅指标
    private String ID;
    private String date1;
    private String date2;
    private SingleStock_service single = new SingleStock_Impl();
    private CalcuDate cal = new CalcuDate();

    //private methods
    private double calcuTR(Stock stock2, Stock stock1){
        double d1 = 0, d2 = 0, d3 = 0;
        double max = 0;
        d1 = stock2.getHigh() - stock2.getLow();
        d2 = stock1.getClose() - stock2.getHigh();
        d3 = stock1.getClose() - stock2.getLow();
        if (d2 < 0)
            d2 = -d2;
        if (d3 < 0) {
            d3 = -d3;
        }
        max = d1;
        if (max < d2) {
            max = d2;
        }
        if (max < d3) {
            max = d3;
        }

        return max;
    }

    private double calcuATR(int day){
        ArrayList<Stock> list = single.setTimeFromDB(ID, date1, date2);
        if(list.size() == 0){
            return 0;
        }
        int n = list.size();
        double sum = 0;
        //当前交易日的最高价与最低价间的波幅；前一交易日收盘价与当个交易日最高价间的波幅；前一交易日收盘价与当个交易日最低价间的波幅
        double max = 0;
        if(day == -1) {
            for (int i = 1; i < n; i++) {
                max = calcuTR(list.get(i), list.get(i - 1));
                sum = sum + max;
            }
        }else{
//            System.out.println("n = " + n + "day = " + day);
            for(int i = n - 1; i >= n - day; i--){
                max = calcuTR(list.get(i), list.get(i - 1));
                sum = sum + max;
            }
        }
        BigDecimal b = new BigDecimal(sum / day);
        double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return f1;
    }




    //public methods
    public double GetATR(String id, String date1, String date2){
        this.ID = id;
        this.date1 = date1;
        this.date2 = date2;
        return calcuATR(-1);
    }

    public double GetATR(String id, int day, String to) throws ParseException {
        this.ID = id;
        int num = day/7;
        num = num * 2 + 2 + 3 + day + 80;
        this.date1 = cal.calDate(to, -num);
        this.date2 = to;
        return calcuATR(day);
    }

    public ArrayList<ATRValue> getATRGraphValue(String id, String from, String to, int day){
        ArrayList<ATRValue> result = new ArrayList<>();
        this.ID = id;
        //calcu the first
        double tmp = 0;
        try {
           tmp = GetATR(id, day, from);
        } catch (ParseException e){
            System.out.println("Parse-Error in ATRImpl");
            return null;
        }
        ATRValue value1 = new ATRValue();
        value1.setATR(tmp);
        value1.setDate(from);
        result.add(value1);
        //
        ArrayList<Stock> data = single.setTimeFromDB(id, from, to);
        if(data.size()==0){
            return null;
        }
        int size = data.size();
        double TR = 0;
        double ATR = tmp;
        for(int i = 1; i < size; i++){
            //to make the line graph more smoothly, use formal data to MA
            String date = data.get(i).getDate();
            TR = calcuTR(data.get(i), data.get(i-1));
            tmp = (tmp*(day-1) + TR) / day;
            ATRValue value = new ATRValue();
            BigDecimal temp = new BigDecimal(tmp);
            double atr = temp.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            value.setDate(date);
            value.setATR(atr);
            result.add(value);
        }
        return result;
    }


    public static void main(String[] args) throws ParseException {
        ATR_service a = new ATR_Impl();
        ArrayList<ATRValue> re = a.getATRGraphValue("sh600037", "2015-05-10", "2016-06-01", 6);
        for(ATRValue tmp : re){
            System.out.println("date: " + tmp.getDate() + "     ATR : " + tmp.getATR());
        }
//        if (re == null) {
//            System.out.println("this is null");
//        }
//        double num = a.GetATR("sh999979", "2015-08-01", "2016-05-10");
//        System.out.println(num);
       // double num = a.GetATR();
       // System.out.println(num);
    }


}
