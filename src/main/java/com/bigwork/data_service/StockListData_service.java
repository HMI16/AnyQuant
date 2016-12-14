package com.bigwork.data_service;

import com.bigwork.model.Stock;
import com.bigwork.sql.SqlImplNullPointerException;
import com.bigwork.sql.SqlLinkException;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StockListData_service {

    /**
     * 处理股票列表原始数据，以Arraylist返回给bl
     * 每个VO里存储的是不同支股票的信息
     *
     * @return
     */
    public ArrayList<Stock> StockList();

}
