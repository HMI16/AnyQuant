package com.bigwork.bl.managementServiceImpl;


import com.bigwork.bl.listServiceImpl.SingleStock_Impl;
import com.bigwork.bl_service.Fluctuation_service;
import com.bigwork.bl_service.SingleStock_service;
import com.bigwork.model.Stock;
import com.bigwork.sql.SqlImplNullPointerException;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

//import org.python.util.PythonInterpreter;
//import bl.ListserviceImpl.SingleStock_Impl;


/**
 * 下面以计算股票的历史波动率为例加以说明。   
 *  1、从市场上获得标的股票在固定时间间隔(如每天、每周或每月等)上的价格。    
 *  2、对于每个时间段，求出该时间段末的股价与该时段初的股价之比的自然对数。    
 *  3、求出这些对数值的标准差，再乘以一年中包含的时段数量的平方根(如，选取时间间隔为每天，
 *  	则若扣除闭市，每年中有250个交易日，应乘以根号250)，得到的即为历史波动率。
 */

public class Fluctuation_Impl implements Fluctuation_service {

    private SingleStock_service singleStock = new SingleStock_Impl();
    private String[] days = {"31","28","31", "30", "31", "30", "31", "31", "30", "31", "30", "31"};

    private String ID;
    private String from;
    private String to;

    @Override
    public double getFluctuation(String ID, String year) {
        this.ID = ID;
        this.from = year+"-01-01";
        this.to = year + "-12-31";
        return getFluc();
    }

    @Override
    public double getFluctuation(String ID, String year, String month) {
        this.ID = ID;
        this.from = year + "-"+month+"-01";
        String day = days[Integer.valueOf(month) - 1];
        this.to = year + "-" + month + "-" + day;
        return getFluc();
    }

    private double getFluc(){
        ArrayList<Stock> list = singleStock.setTimeFromDB(ID, from, to);
        if(list.size()==0){
            return 0;
        }
        ArrayList<Double> diff = new ArrayList<>();
        int num = list.size();
        for(int i = 1; i < num; i++){
            double tmp =Math.log(list.get(i).getClose()*1.0 / list.get(i-1).getClose());
            diff.add(tmp);
        }
        double ave = getAver(diff);
        double result = getStanDiff(diff, ave);
        result*=100;
        BigDecimal t = new BigDecimal(result);
        result = t.setScale(2, BigDecimal.ROUND_CEILING).doubleValue();
        return result;
    }

    private double getAver(ArrayList<Double> list){
        double total = 0.0;
        for(int i = 0; i<list.size(); i++){
            total += list.get(i);
        }
        return total/list.size();
    }

    private double getStanDiff(ArrayList<Double> list, double ave){
        double result = 0;
        for(double d : list){
            result += Math.pow((d-ave), 2);
        }
        return result;
    }

    public static void main(String[] args) {
        Fluctuation_service t = new Fluctuation_Impl();
        System.out.println(t.getFluctuation("sh999979", "2015", "07"));
    }
}
