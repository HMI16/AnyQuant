package com.bigwork.bl.managementServiceImpl;


import com.bigwork.bl.listServiceImpl.SingleStock_Impl;
import com.bigwork.bl_service.BIAS_service;
import com.bigwork.bl_service.SingleStock_service;
import com.bigwork.model.BIASValue;
import com.bigwork.model.Stock;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class BIAS_Impl implements BIAS_service {
    // 乖离率
    private String ID;
    private String date1;
    private String date2;

    private SingleStock_service single  = new SingleStock_Impl();
    private CalcuDate cal = new CalcuDate();

    //private methods

    /*
        根据类中的ID, date1，和date2以及参数中的day来到date
     *            为止的计算day天中的BIAS值
     */
    private double getBIAS(int day){
        double result = 0;
        ArrayList<Stock> list = single.setTimeFromDB(ID, date1, date2);
        double bias;

        int n = list.size();
        if(n == 0){
            return 0;
        }
        double sum = 0;
        for (int i = n - 1; i >= n - day; i--) {
            sum = sum + list.get(i).getClose();
          //  System.out.println("Date : " + list.get(i).getDate() + "    ClosePrice : " + list.get(i).getClose() );
        }
        //sum为一般移动平均线
        sum = sum / day;

        bias = (list.get(n - 1).getClose() - sum) / sum * 100;
        BigDecimal b = new BigDecimal(bias);
        result = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return result;
    }

    /*
        根据这个ArrayList中的数据计算其中数据的BIAS值
     */
    private double getBIAS(List<Stock> list){
        double result = 0;
        double bias;

        int n = list.size();
        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum = sum + list.get(i).getClose();
           // System.out.println("Date : " + list.get(i).getDate() + "    ClosePrice : " + list.get(i).getClose() );
        }
        //sum为一般移动平均线
        sum = sum / n;

        bias = (list.get(n - 1).getClose() - sum) / sum * 100;
        BigDecimal b = new BigDecimal(bias);
        result = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return result;
    }
    //


    public double getBIAS(String ID, String date1, String date2) throws ParseException {
        // 乖离率
        this.ID = ID;
        this.date1 = date1;
        this.date2 = date2;

        ArrayList<Stock> list = single.setTimeFromDB(ID, date1, date2);
        if(list.size() == 0){
            return 0;
        }
        double result = getBIAS(list);
        return result;
    }

    public double getBIAS(String ID, int day, String to){
        int num = day/7;
        num = num*2 + 2;
        num = day + num;
        this.ID = ID;
        this.date2 = to;
        this.date1 = cal.calDate(to, 1 - num);
        double result = getBIAS(day);
        return result;
    }

    public ArrayList<BIASValue> getBIASGraph(String ID, String from, String to, int day){
        ArrayList<BIASValue> result = new ArrayList<>();
        String realStart = cal.calDate(from, 1-day);
        ArrayList<Stock> list = single.setTimeFromDB(ID, realStart, to);
        List<Stock> tmp;
        int num = list.size();
        if(num == 0){
            return null;
        }
        for(int i = 0; i < num - day; i++){
            tmp = list.subList(i, i + day);
            double biasValue = getBIAS(tmp);
            String date = list.get(i + day).getDate();
            BIASValue value = new BIASValue();
            value.setDate(date);
            value.setBIASValue(biasValue);
//            System.out.println("date : " + date + "     BIAS : " + biasValue);

            result.add(value);
        }
        return result;
    }



    public static void main(String[] args) throws ParseException {
        BIAS_service a = new BIAS_Impl();
        if(a.getBIASGraph("sh999979", "2015-05-02", "2015-11-10",6) == null){
            System.out.println("this is null");
        }
       // System.out.println(a.getBIASGraph("sh999979", "2015-05-02", "2015-11-10",6).size());
        System.out.println(a.getBIAS("sh999979", "2015-05-02", "2015-11-10"));
        //System.out.println(num + "%");
    }
}
