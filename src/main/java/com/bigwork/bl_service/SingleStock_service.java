package com.bigwork.bl_service;

import com.bigwork.model.Stock;
import com.bigwork.sql.SqlImplNullPointerException;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public interface SingleStock_service {

    /**
     * 显示单支股票一段时间内的信息
     *
     * @param id
     * @return
     * @throws ParseException
     */
    public ArrayList<Stock> Show(String id) ;

    //public void Search();

    //public ArrayList<StockVO> filter();

    /**
     * 关闭单支股票详细列表，返回股票列表界面
     */
    public void close();

    /**
     * 返回一段时间内单支股票的详细信息
     *
     * @param id
     * @param startTime, endTime (yyyy-MM-dd)
     * @return
     * @throws ParseException
     */
    public ArrayList<Stock> setTimeFromDB(String id, String startTime, String endTime) ;

}
