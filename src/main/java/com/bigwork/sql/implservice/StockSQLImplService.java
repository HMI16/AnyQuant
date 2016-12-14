package com.bigwork.sql.implservice;

import com.bigwork.model.Stock;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by 15HR-1528SS on 2016/5/7.
 */
public interface StockSQLImplService {
    void insertStock(Stock stock, String type) throws SQLException;

    Stock selectStock(String stock_id, String date, String type) throws SQLException;

    void insertArray(ArrayList<Stock> array, String type) throws SQLException;

    ArrayList<Stock> selectArray(String id, String datefrom, String dateto, String type) throws SQLException;
}
