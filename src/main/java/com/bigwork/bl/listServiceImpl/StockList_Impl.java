package com.bigwork.bl.listServiceImpl;


import com.bigwork.bl_service.SingleStock_service;
import com.bigwork.bl_service.StockList_service;
import com.bigwork.data.api.dataGetter.DataGetter;
import com.bigwork.data.dataServiceImpl.StockListData_Impl;
import com.bigwork.data_service.StockListData_service;
import com.bigwork.model.Stock;
import com.bigwork.model.TypeGetter;
import com.bigwork.sql.SqlImplNullPointerException;
import com.bigwork.sql.SqlLinkException;

import java.sql.SQLException;
import java.util.ArrayList;

public class StockList_Impl implements StockList_service {

    public ArrayList<Stock> ShowAll(){
        StockListData_service stockListData = new StockListData_Impl();
        ArrayList<Stock> s = new ArrayList<>();
        System.out.println("carry on listImpl");
        s = stockListData.StockList();
        System.out.println("impl : " + s.size());
        return s;
    }

    public ArrayList<Stock> Search(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    public void Refresh(){
        // TODO Auto-generated method stub
        this.ShowAll();
    }

    public ArrayList<Stock> filter(Stock low, Stock high){
        // TODO Auto-generated method stub
        ArrayList<Stock> result = new ArrayList<Stock>();
        ArrayList<Stock> all = this.ShowAll();

        for (Stock current : all) {
            StockFilter filter = new StockFilter(low, high, current);
            if (filter.filtData())
                result.add(current);
        }
        return result;
    }

    public ArrayList<Stock> setTime(String startTime, String endTime) {
        // TODO Auto-generated method stub
        return null;
    }

    public static void main(String[] args) throws SQLException, SqlImplNullPointerException {
        StockList_service test = new StockList_Impl();
        ArrayList<Stock> result = test.ShowAll();
        for(Stock stock : result){
            System.out.println("name = " + stock.getName());
        }
//        System.out.println(test.ShowAll().size());
    }

}
