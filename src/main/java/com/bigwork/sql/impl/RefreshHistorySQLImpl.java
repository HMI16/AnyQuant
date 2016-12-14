package com.bigwork.sql.impl;


import com.bigwork.sql.implservice.RefreshHistorySQLImplService;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 15HR-1528SS on 2016/5/7.
 */
public class RefreshHistorySQLImpl extends SQLImpl implements RefreshHistorySQLImplService {
    public RefreshHistorySQLImpl(Connection con) {
        super(con);
    }

    /**
     * 新增刷新历史
     *
     * @throws SQLException
     */
    @Override
    public void insertRefreshHistory() throws SQLException {
        try {
            psql = con.prepareStatement("INSERT INTO refreshhistory VALUES (?,?)");

            Date date = new Date();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String time = format.format(date);

            psql.setInt(1, 0);
            psql.setString(2, time);

            psql.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("插入刷新历史数据失败");
        }
    }

    /**
     * 获得最新的刷新时间
     *
     * @return 日期String
     * @throws SQLException
     */
    @Override
    public String selectRefreshHistory() throws SQLException {
        String refreshTime = "";

        try {
            psql = con.prepareStatement("SELECT * FROM refreshhistory WHERE times = (SELECT max(times) FROM refreshhistory)");

            rs = psql.executeQuery();
            while (rs.next())
                refreshTime = rs.getString("refreshtime");
            rs.close();
        } catch (SQLException e) {
            throw new SQLException("读取刷新历史数据失败");
        }
        return refreshTime;
    }
}
