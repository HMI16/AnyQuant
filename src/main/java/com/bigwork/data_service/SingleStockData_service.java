package com.bigwork.data_service;

import com.bigwork.model.Stock;
import com.bigwork.sql.SqlImplNullPointerException;
import com.bigwork.sql.SqlLinkException;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SingleStockData_service {

    /**
     * 处理单支股票原始数据，以Arraylist返回给bl
     * 每个VO里存储的是同一股票每一天的信息
     * time默认为一个月
     *
     * @return
     */
    public ArrayList<Stock> SingleStock(String id,String datefrom, String dateTo);


}
