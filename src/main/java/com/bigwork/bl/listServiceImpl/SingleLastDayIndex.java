package com.bigwork.bl.listServiceImpl;

import com.bigwork.bl.managementServiceImpl.CAPM_Impl;
import com.bigwork.bl_service.CAPM_service;
import com.bigwork.bl_service.SingleStock_service;
import com.bigwork.model.Stock;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/19.
 */
public class SingleLastDayIndex {

    private SingleStock_service single = new SingleStock_Impl();
    private CAPM_service c = new CAPM_Impl();
    public ArrayList<Double> getLastDayIndex(String id){
        ArrayList<Stock> stocklist = single.Show(id);
        ArrayList<Double> result = new ArrayList<Double>();
        Stock stock = stocklist.get(stocklist.size()-1);

        result.add(stock.getHigh());
        result.add(stock.getLow());
        result.add(stock.getOpen());
        result.add(stock.getClose());
        double change = (stock.getOpen()-stock.getClose())/stock.getOpen();
        DecimalFormat df = new DecimalFormat("######0.00");
        BigDecimal bg = new BigDecimal(change);
        double d = bg.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
        result.add(d);
        result.add(c.getExy(id));

        return result;
    }

    public static void main(String args[]){
        SingleLastDayIndex a = new SingleLastDayIndex();
        ArrayList<Double> b = a.getLastDayIndex("sh600000");
        System.out.println(b);
    }
}
