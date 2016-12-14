package com.bigwork.bl.managementServiceImpl;

import com.bigwork.bl.listServiceImpl.SingleStock_Impl;
import com.bigwork.bl_service.MACD_service;
import com.bigwork.bl_service.SingleStock_service;
import com.bigwork.model.MACDValue;
import com.bigwork.model.Stock;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by asus on 2016/5/30.
 */
public class MACD_Impl implements MACD_service {
    private SingleStock_service single = new SingleStock_Impl();
    ArrayList<Stock> list;
    double firstAve = 0;
    @Override
    public ArrayList<MACDValue> getMACDGraph(String id, String from, String to) throws Exception{
        list = single.setTimeFromDB(id, from, to);
        if(list.size() < 5){
            throw new Exception("there is little significance to study statistics in too few days");
        }
        for(int i = 0; i < 5; i++){
            firstAve += list.get(i).getClose();
        }
        firstAve /= 5;
        ArrayList<Double> ema1 = calEMA1();
        ArrayList<Double> ema2 = calEMA2();
        ArrayList<Double> diff = getDiff(ema1, ema2);
        ArrayList<Double> dea = getDEA(diff);
        ArrayList<Double> macd = getMACD(diff, dea);
        ArrayList<MACDValue> result = new ArrayList<>();
        int size = diff.size();
        for(int i = 0; i < size; i++){
            double macdDiff = diff.get(i);
            BigDecimal tmp1 = new BigDecimal(macdDiff);
            macdDiff = tmp1.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
            double macdDea = dea.get(i);
            BigDecimal tmp2 = new BigDecimal(macdDea);
            macdDea = tmp2.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
            double macdData = macd.get(i);
            BigDecimal tmp3 = new BigDecimal(macdData);
            macdData = tmp3.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
            MACDValue macdValue = new MACDValue(list.get(i).getDate(), macdDiff,
                    macdDea, macdData);
            result.add(macdValue);
        }
        return result;
    }

    private ArrayList<Double> calEMA1(){
        ArrayList<Double> ema1 = new ArrayList<>();
        ema1.add(firstAve);
        int size = list.size();
        for(int i = 1; i < size; i++){
            ema1.add(ema1.get(i - 1)*11/13 + list.get(i).getClose()*2/13);
        }
        return ema1;
    }

    private ArrayList<Double> calEMA2(){
        ArrayList<Double> ema2 = new ArrayList<>();
        firstAve = (firstAve*5+list.get(5).getClose())/6;
        ema2.add(firstAve);
        int size = list.size();
        for(int i = 1; i < size; i++){
            ema2.add(ema2.get(i - 1)*25/27 + list.get(i).getClose()*2/27);
        }
        return ema2;
    }

    private ArrayList<Double> getDiff(ArrayList<Double> ema1, ArrayList<Double> ema2){
        ArrayList<Double> diff = new ArrayList<>();
        int size = ema1.size();
        for(int i = 0; i <size; i++){
            diff.add(ema2.get(i) - ema1.get(i));
        }
        return diff;
    }

    private ArrayList<Double> getDEA(ArrayList<Double> diff){
        ArrayList<Double> dea = new ArrayList<>();
        int size = diff.size();
        dea.add(diff.get(0));
        for(int i = 1; i  < size; i++){
            dea.add(diff.get(i)*0.2 + dea.get(i-1)*0.8);
        }
        return dea;
    }

    private ArrayList<Double> getMACD(ArrayList<Double> diff, ArrayList<Double> dea){
        ArrayList<Double> macd = new ArrayList();
        int size = diff.size();
        for(int i = 0; i < size; i++){
            macd.add(diff.get(i) - dea.get(i));
        }
        return macd;
    }
}
