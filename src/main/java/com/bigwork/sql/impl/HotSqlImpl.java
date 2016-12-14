package com.bigwork.sql.impl;

import com.bigwork.model.HotStock;
import com.bigwork.model.Stock;
import com.bigwork.sql.implservice.HotSQLImplService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by 15HR-1528SS on 2016/6/13.
 */
public class HotSqlImpl extends SQLImpl implements HotSQLImplService {
    public HotSqlImpl(Connection con) {
        super(con);
    }

    @Override
    public void insertHot(HotStock hot) throws SQLException {
        try {
            psql = con.prepareStatement("INSERT INTO hot VALUES (?,?)");
            psql.setString(1, hot.getType());
            psql.setDouble(2, hot.getIncrease());

            psql.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("插入行业信息数据失败");
        }
    }

    @Override
    public void insertArray(ArrayList<HotStock> array) throws SQLException {
        psql = con.prepareStatement("DELETE FROM hot");
        psql.executeUpdate();

        for(HotStock hot:array)
            insertHot(hot);
    }

    @Override
    public ArrayList<HotStock> selectArray() throws SQLException {
        ArrayList<HotStock> array = new ArrayList<>();

        try {
            psql = con.prepareStatement("SELECT * FROM hot");

            rs = psql.executeQuery();

            while (rs.next()) {
                HotStock hot = new HotStock();
                hot.setType(rs.getString("name"));
                hot.setIncrease(rs.getDouble("rate"));
                array.add(hot);
            }
        } catch (SQLException e) {
            throw new SQLException("读取行业数据失败");
        }

        return array;
    }
}
