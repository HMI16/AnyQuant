package com.bigwork.sql.impl;


import com.bigwork.model.Grail;
import com.bigwork.sql.DateHelper;
import com.bigwork.sql.implservice.MarketSQLImplService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by 15HR-1528SS on 2016/5/7.
 */
public class MarketSqlImpl extends SQLImpl implements MarketSQLImplService {
    public MarketSqlImpl(Connection con) {
        super(con);
    }

    /**
     * 新增大盘数据
     *
     * @param market Grail对象
     * @throws SQLException
     */
    @Override
    public void insertMarket(Grail market) throws SQLException {
        try {
            psql = con.prepareStatement("INSERT  INTO marketdata VALUES (?,?,?,?,?,?,?,?,?)");
            psql.setString(1, market.getID());
            psql.setString(2, null);
            psql.setDouble(3, market.getOpen());
            psql.setDouble(4, market.getHigh());
            psql.setDouble(5, market.getLow());
            psql.setDouble(6, market.getClose());
            psql.setDouble(7, market.getAdj_price());
            psql.setString(8, market.getVolume());
            psql.setString(9, market.getDate());

            psql.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("插入大盘单天数据失败");
        }
    }

    /**
     * 根据大盘id和日期筛选大盘数据
     *
     * @param market_id 大盘ID
     * @param date 日期
     * @return
     * @throws SQLException
     */
    @Override
    public Grail selectMarket(String market_id, String date) throws SQLException {
        Grail market = new Grail();
        try {
            psql = con.prepareStatement("SELECT * FROM marketdata WHERE id = ? AND dateTime = ?");
            psql.setString(1, market_id);
            psql.setString(2, date);

            rs = psql.executeQuery();

            while (rs.next()) {
                market = new Grail();

                market.setID(rs.getString("id"));
                market.setOpen(rs.getDouble("open"));
                market.setHigh(rs.getDouble("high"));
                market.setLow(rs.getDouble("low"));
                market.setClose(rs.getDouble("close"));
                market.setAdj_price(rs.getDouble("adj_price"));
                market.setVolume(rs.getString("volume"));
                market.setDate(rs.getString("dateTime"));
            }

            rs.close();
        } catch (SQLException e) {
            throw new SQLException("读取大盘单天数据失败");
        }
        return market;
    }

    /**
     * 插入一组大盘数据
     *
     * @param array Grail对象的ArrayList
     * @throws SQLException
     */
    @Override
    public void insertArray(ArrayList<Grail> array) throws SQLException {
        for (Grail market : array)
            insertMarket(market);
    }

    /**
     *  根据大盘ID，时间范围筛选大盘数据
     *
     * @param market_id 大盘ID
     * @param datefrom 开始日期
     * @param dateTo 结束日期
     * @return Grail对象的ArrayList
     * @throws SQLException
     */
    @Override
    public ArrayList<Grail> selectArray(String market_id, String datefrom, String dateTo) throws SQLException {
        ArrayList<Grail> array = new ArrayList<>();

        try {
            psql = con.prepareStatement("SELECT * FROM marketdata WHERE id = ? ");
            psql.setString(1, market_id);

            rs = psql.executeQuery();


            while (rs.next()) {
                if (DateHelper.isIn(rs.getString("dateTime"), datefrom, dateTo)) {
                    Grail market = new Grail();
                    market.setID(rs.getString("id"));
                    market.setOpen(rs.getDouble("open"));
                    market.setHigh(rs.getDouble("high"));
                    market.setLow(rs.getDouble("low"));
                    market.setClose(rs.getDouble("close"));
                    market.setAdj_price(rs.getDouble("adj_price"));
                    market.setVolume(rs.getString("volume"));
                    market.setDate(rs.getString("dateTime"));
                    array.add(market);
                }
            }

            rs.close();
        } catch (SQLException e) {
            throw new SQLException("读取大盘数据失败");
        }
        return array;
    }
}
