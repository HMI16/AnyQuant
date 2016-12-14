package com.bigwork.sql.implservice;

import com.bigwork.model.Stock;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by 15HR-1528SS on 2016/5/14.
 */
public interface StockListSQLService {
    void insertStock(Stock stock) throws SQLException;

    void insertArray(ArrayList<Stock> array) throws SQLException;

    ArrayList<Stock> selectArray() throws SQLException;
}
