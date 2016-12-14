package com.bigwork.bl.managementServiceImpl;

import com.bigwork.bl.listServiceImpl.SingleStock_Impl;
import com.bigwork.bl_service.HotIndustry_service;
import com.bigwork.bl_service.SingleStock_service;
import com.bigwork.model.HotStock;
import com.bigwork.model.Stock;
import com.bigwork.model.TypeGetter;
import com.bigwork.sql.MysqlLink;
import com.bigwork.sql.SqlImplNullPointerException;
import com.bigwork.sql.SqlLinkException;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2016/6/4.
 */
public class HotIndustry_Impl implements HotIndustry_service{


    private CalcuDate cal = new CalcuDate();
    private SingleStock_service single = new SingleStock_Impl();
    private TypeGetter typeGetter = new TypeGetter();
    int day;

    public ArrayList<HotStock> getHotListfromDB(){
        ArrayList<HotStock> hotStocks = new ArrayList<HotStock>();
        MysqlLink link = new MysqlLink();
        try {
            try {
                link.link("115.28.40.144", "root", "141250185");
                try {
                    hotStocks = link.getHotSQLImpl().selectArray();
                } catch (SqlImplNullPointerException e) {
                    e.printStackTrace();
                }
            } catch (SqlLinkException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return hotStocks;
    }

    public double getHot(String type, int day, String to){
        this.day = day;
        ArrayList<String> idList = typeGetter.getStringwithID(type);
        System.out.println("--------------getID-----------------------------------");
        int size = idList.size();
        double[] increase = new double[size];

        int num = day/7;
        num = num*2+2+day+40;
        String from = cal.calDate(to, -num);

        for(int i = 0; i < size; i++){
            increase[i] = getIncrease(idList.get(i),from, to);
        }

        double result = 0;
        for(int i = 0; i<size;i++){
            result+=increase[i];
        }
        result/=size;
        BigDecimal tmp = new BigDecimal(result);
        result = tmp.setScale(2, BigDecimal.ROUND_CEILING).doubleValue();

        return result;
    }

    //increase at least 2%, turnover at least 5%
    private double getIncrease(String id, String from, String to){
        double count = 0;
        System.out.println("--------------------gonna getlist--------------");
        ArrayList<Stock> list = single.setTimeFromDB(id, from, to);
        System.out.println("---------------------get list !!!!!!--------------------");
        int size = list.size();
        System.out.println("id :"+id);
        System.out.println("size:"+size);
        List<Stock> slist = list.subList(size-day, size);
        for(Stock stock : slist){
           double increase = (stock.getClose() - stock.getOpen()) / stock.getOpen();
           if(increase > 0.02&&stock.getTurnover()>0.05){
               count++;
           }
       }
        return count;

    }

    @Override
    public ArrayList<HotStock> getHotList(int day, String to) {
        ArrayList<HotStock> result = new ArrayList<>();
        char type = 'a';
        for(int i = 0; i< 16; i++){
            if(type == 'o') type+=2;
            System.out.println("i = " + i);
            double tmp = getHot(type+"",day,to);
            String name = typeGetter.getTypeName(type+"");
            HotStock hotStock = new HotStock(name, tmp);
            result.add(hotStock);

            type++;
        }
        return result;
    }

    @Override
    public ArrayList<Stock> getStockList(String type) {
        String ty = typeGetter.getABCFromtype(type);
        ArrayList<String> ids = typeGetter.getStringwithID(ty);
        ArrayList<Stock> result = new ArrayList<>();
        for(String s : ids){
            ArrayList<Stock> tmp = single.Show(s);
            result.add(tmp.get(tmp.size()-1));
        }
        return result;
    }

    @Override
    public ArrayList<Stock> getAllStocks() {
        ArrayList<String> ids = typeGetter.getAllID();
        ArrayList<Stock> result = new ArrayList<>();
        for(String s : ids){
            ArrayList<Stock> tmp = single.Show(s);
            result.add(tmp.get(tmp.size()-1));
        }
        return result;
    }

    public static void main(String[] args) {
        HotIndustry_service h = new HotIndustry_Impl();
        //ArrayList<HotStock> r = h.getHotList(5, "2016-02-08");
        ArrayList<HotStock> r = h.getHotListfromDB();
        for(HotStock hh : r){
            System.out.println("type : " + hh.getType() + "     increase : " + hh.getIncrease());
        }
    }

}
