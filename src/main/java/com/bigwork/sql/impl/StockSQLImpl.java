package com.bigwork.sql.impl;

import com.bigwork.model.Stock;
import com.bigwork.sql.DateHelper;
import com.bigwork.sql.implservice.StockSQLImplService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by 15HR-1528SS on 2016/5/7.
 */
public class StockSQLImpl extends SQLImpl implements StockSQLImplService {
    public StockSQLImpl(Connection con) {
        super(con);
    }

    /**
     * 新增股票数据
     *
     * @param stock Stock对象
     * @throws SQLException
     */
    @Override
    public void insertStock(Stock stock, String type) throws SQLException {
        try {
            psql = con.prepareStatement("INSERT INTO stockdata_" + type + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
            psql.setString(1, stock.getId());
            psql.setString(2, null);
            psql.setDouble(3, stock.getOpen());
            psql.setDouble(4, stock.getHigh());
            psql.setDouble(5, stock.getLow());
            psql.setDouble(6, stock.getClose());
            psql.setDouble(7, stock.getAdj_price());
            psql.setInt(8, stock.getVolume());
            psql.setDouble(9, stock.getTurnover());
            psql.setDouble(10, stock.getPe());
            psql.setDouble(11, stock.getPd());
            psql.setString(12, stock.getDate());

            psql.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("插入单支股票单天数据失败");
        }
    }

    /**
     * 根据股票ID和日期筛选股票数据
     *
     * @param stock_id 股票ID
     * @param date     日期
     * @return Stock对象
     * @throws SQLException
     */
    @Override
    public Stock selectStock(String stock_id, String date, String type) throws SQLException {
        Stock stock = new Stock();

        try {
            psql = con.prepareStatement("SELECT * FROM stockdata_" + type + " WHERE id = ? AND dateTime = ?");
            psql.setString(1, stock_id);
            psql.setString(2, date);

            rs = psql.executeQuery();

            while (rs.next()) {
                stock = new Stock();
                stock.setId(rs.getString("id"));
                stock.setOpen(rs.getDouble("open"));
                stock.setHigh(rs.getDouble("high"));
                stock.setLow(rs.getDouble("low"));
                stock.setClose(rs.getDouble("close"));
                stock.setAdj_price(rs.getDouble("adj_price"));
                stock.setVolume(rs.getInt("volume"));
                stock.setTurnover(rs.getDouble("turnover"));
                stock.setPe(rs.getDouble("pe"));
                stock.setPd(rs.getDouble("pb"));
                stock.setDate(rs.getString("dateTime"));
                stock.setName();
            }

            rs.close();
        } catch (SQLException e) {
            throw new SQLException("读取单支股票单天数据失败");
        }
        return stock;
    }

    /**
     * 新增一组股票数据
     *
     * @param array Stock对象的ArrayList
     * @throws SQLException
     */
    @Override
    public void insertArray(ArrayList<Stock> array, String type) throws SQLException {
        for (Stock stock : array)
            insertStock(stock, type);
    }

    /**
     * 根据股票ID，时间范围筛选股票数据
     *
     * @param stock_id 股票ID
     * @param datefrom 开始日期
     * @param dateTo   结束日期
     * @return Stock对象的ArrayList
     * @throws SQLException
     */
    @Override
    public ArrayList<Stock> selectArray(String stock_id, String datefrom, String dateTo, String type) throws SQLException {
        ArrayList<Stock> array = new ArrayList<>();
        try {
            psql = con.prepareStatement("SELECT * FROM stockdata_" + type + " WHERE id = ? ");
            psql.setString(1, stock_id);

            rs = psql.executeQuery();

            while (rs.next()) {
                if (DateHelper.isIn(rs.getString("dateTime"), datefrom, dateTo)) {
                    Stock stock = new Stock();
                    stock.setId(rs.getString("id"));
                    stock.setOpen(rs.getDouble("open"));
                    stock.setHigh(rs.getDouble("high"));
                    stock.setLow(rs.getDouble("low"));
                    stock.setClose(rs.getDouble("close"));
                    stock.setAdj_price(rs.getDouble("adj_price"));
                    stock.setVolume(rs.getInt("volume"));
                    stock.setTurnover(rs.getDouble("turnover"));
                    stock.setPe(rs.getDouble("pe"));
                    stock.setPd(rs.getDouble("pb"));
                    stock.setDate(rs.getString("dateTime"));
                    array.add(stock);
                }
            }

            rs.close();
        } catch (SQLException e) {
            throw new SQLException("读取单支股票数据失败");
        }
        return array;
    }
}
