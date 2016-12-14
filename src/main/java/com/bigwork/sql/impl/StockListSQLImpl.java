package com.bigwork.sql.impl;

import com.bigwork.model.Stock;
import com.bigwork.sql.implservice.StockListSQLService;
import org.hibernate.SQLQuery;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by 15HR-1528SS on 2016/5/14.
 */
public class StockListSQLImpl extends SQLImpl implements StockListSQLService {
    public StockListSQLImpl(Connection con) {
        super(con);
    }

    /**
     * 新增股票数据
     *
     * @param stock Stock对象
     * @throws SQLException
     */
    public void insertStock(Stock stock) throws SQLException {
        try {
            psql = con.prepareStatement("INSERT INTO stocklist VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
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
     * 新增一组股票数据
     *
     * @param array Stock对象的ArrayList
     * @throws SQLException
     */
    public void insertArray(ArrayList<Stock> array) throws SQLException {
        psql = con.prepareStatement("DELETE FROM stocklist");
        psql.executeUpdate();

        for (Stock stock : array)
            insertStock(stock);
    }


    /**
     * 获得股票列表
     *
     * @return Stock对象的ArrayList
     * @throws SQLException
     */
    public ArrayList<Stock> selectArray() throws SQLException {
        ArrayList<Stock> array = new ArrayList<>();

        try {
            psql = con.prepareStatement("SELECT * FROM stocklist");

            rs = psql.executeQuery();

            while (rs.next()) {
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
                stock.setName();
                array.add(stock);
            }
        } catch (SQLException e) {
            throw new SQLException("读取股票列表数据失败");
        }

        return array;
    }
}
