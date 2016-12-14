package com.bigwork.bl.managementServiceImpl;

import com.bigwork.bl.listServiceImpl.Grail_Impl;
import com.bigwork.bl.listServiceImpl.SingleStock_Impl;
import com.bigwork.bl_service.CAPM_service;
import com.bigwork.bl_service.Grail_service;
import com.bigwork.bl_service.SingleStock_service;
import com.bigwork.model.Grail;
import com.bigwork.model.Stock;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by asus on 2016/6/17.
 */
public class CAPM_Impl implements CAPM_service{

    CalcuDate cal = new CalcuDate();
    String time;
    double stock_miu;
    double stock_sigma;
    double grail_miu;
    double grail_sigma;
    ArrayList<Stock> list;
    ArrayList<Grail> grails;

    @Override
    public double getExy(String id) {
        double rf = getRF();
        double mrp = calcu();
//        getGrailMiuAndSigma(grails.subList(20, grails.size()));
        getStockMiuAndSigma(id);
        double beta = getBeta();
        double result = rf+beta*mrp;
//        System.out.println("sigma = " + grail_sigma + " miu = " + grail_miu);
//        System.out.println("rf = " + rf + " mrp = " + mrp + "   beta = " + beta);
        if(fTest(beta))
        return result;
        else return -10;
    }

    private double getRF(){
        double guozhai = 0.0442;
        double fuxi = Math.pow((0.0442*5+1),0.2)-1;
        return fuxi;
    }

    private double calcu(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String now = df.format(new Date());
        time = cal.calDate(now, -10);
        ArrayList<Double> close = new ArrayList<>();
        Grail_service g = new Grail_Impl();
        grails = g.setTime("2009-12-01", time);
        //-----------------------------------------得到每月月底的收盘价
        int count = 0;
        while(count<grails.size()){
            if(count%20==0){
                close.add(grails.get(count).getClose());
            }
            count++;
        }
        //----------------------------------------计算每月指数收益率
        ArrayList<Double> exp = new ArrayList<>();
        int size = close.size();
        for(int i=1;i<size;i++){
            double tmp = (close.get(i)-close.get(i-1))/close.get(i-1);
            exp.add(tmp);
        }
        //-------------------------------------计算平均月收益率
        int year = exp.size()/12;
        int div = exp.size()%12;
        double ave_month = getSimAve(exp.subList(div, year*12));
        double ave_year = ave_month*12; //计算平均年收益率
        //---------------------------------------结束计算年收益率
        double rf = getRF();
        double mrp = ave_year-rf;   //风险溢价

        return mrp;
    }

    private double getBeta(){
        ArrayList<Double> grailClose = new ArrayList<>();
        ArrayList<Double> stockClose = new ArrayList<>();
        int countGrail=0;
        int countStock=0;
        while(countGrail<grails.size()&&countStock<list.size()){
            if(grails.get(countGrail).getDate().equals(list.get(countStock).getDate())){
                grailClose.add(grails.get(countGrail).getClose());
                stockClose.add(list.get(countStock).getClose());
                countGrail++;
                countStock++;
            }else{
                countGrail++;
            }
        }
        //只留下股票和大盘时间对等的数据，否则去掉
        ArrayList<Double> grail_increase = new ArrayList<>();
        ArrayList<Double> stock_increase = new ArrayList<>();
        for(int i=1;i<countStock;i++){
            Double tmp1 = (grailClose.get(i)-grailClose.get(i-1))/grailClose.get(i);
            Double tmp2 = (stockClose.get(i)-stockClose.get(i-1))/stockClose.get(i);
            grail_increase.add(tmp1);
            stock_increase.add(tmp2);
        }
        getGrailMiuAndSigma(grail_increase);

        double aveincrease_Grail = getSimAve(grail_increase);
        double aveincrease_Stock = getSimAve(stock_increase);
        grail_miu=aveincrease_Grail;
        stock_miu=aveincrease_Stock;
        getGrailMiuAndSigma(stock_increase);
        double aveBoth = 0;
        for(int i = 0; i< countStock-1; i++){
            aveBoth+=grail_increase.get(i)*stock_increase.get(i);
        }
        aveBoth/=countStock;
        System.out.println("both = " + aveBoth + "  aveincrease_Grail = " + aveincrease_Grail + " aveincrease_Stock = " + aveincrease_Stock);
        return (aveBoth-aveincrease_Grail*aveincrease_Stock)/Math.pow(grail_miu, 2)/10000;
//        return 0;
    }



    private void getGrailMiuAndSigma(List<Double> re){
        int size = re.size();
        double tmp_miu = 0;
        for(Double g:re){
            tmp_miu+=g;
        }
        grail_miu = tmp_miu/size;
        double tmp_sigma = 0;
        for(Double g : re){
            tmp_sigma+=Math.pow((g-grail_miu),2);
        }
        grail_sigma = Math.pow(tmp_sigma/size, 0.5);
    }


    private void getStockMiuAndSigma(String id){
        SingleStock_service single = new SingleStock_Impl();
        list = single.setTimeFromDB(id, "2010-01-01", time);
    }

    private void getStockMiuAndSigma2(ArrayList<Double> re){
        int size = re.size();
        double tmp_sigma = 0;
        for(Double g : re){
            tmp_sigma+=Math.pow((g-stock_miu),2);
        }
        stock_miu = Math.pow(tmp_sigma/size, 0.5);
    }


    private double getSimAve(List<Double> list){
        if(list.size()==0) return 0;
        double result = 0;
        for(Double d : list){
            result+=d;
        }
        return result/list.size();
    }

    private double getGeoMean(List<Double>list){
        if(list.size()==0) return 0;
        int size = list.size();
        double result = 1;
        for(Double d : list){
            result*=d;
        }
        return Math.pow(result, list.size());
    }

    private boolean fTest(double beta){
        if(beta<1.9)
            return true;
        else return false;
    }


    public static void main(String[] args) {
        CAPM_service c = new CAPM_Impl();
        System.out.println(c.getExy("sh600000"));
    }

}
