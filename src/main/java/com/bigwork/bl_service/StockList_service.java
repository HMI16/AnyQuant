package com.bigwork.bl_service;

import com.bigwork.model.Stock;
import com.bigwork.sql.SqlImplNullPointerException;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StockList_service {

    /**
     * 显示股票列表
     *
     * @return
     */
    public ArrayList<Stock> ShowAll();

    /**
     * 选中某支股票，返回该股票的详细信息
     * @param id
     * @return
     *//*
    public ArrayList<StockVO> Select(String id);*/

    /**
     * 根据id搜索股票，得到一段时间内该股票的信息
     *
     * @param id
     * @return
     */
    public ArrayList<Stock> Search(String id);

    /**
     * 刷新列表
     */
    public void Refresh();

    /**
     * 根据某项数据条件对列表进行筛选
     *
     * @param low: 下界；high：上界
     * @return
     */
    public ArrayList<Stock> filter(Stock low, Stock high);

    /**
     * 返回一段时间内的股票列表
     *
     * @param startTime：起始；endTime：结束
     * @return
     */
    public ArrayList<Stock> setTime(String startTime, String endTime);


}
