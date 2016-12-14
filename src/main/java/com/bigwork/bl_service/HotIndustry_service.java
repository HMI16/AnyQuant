package com.bigwork.bl_service;

import com.bigwork.model.HotStock;
import com.bigwork.model.Stock;

import java.util.ArrayList;

/**
 * Created by asus on 2016/6/4.
 */
public interface HotIndustry_service {
    public ArrayList<HotStock> getHotList(int day, String to);

    public ArrayList<Stock> getStockList(String type);

    public ArrayList<Stock> getAllStocks();

    public ArrayList<HotStock> getHotListfromDB();
}
