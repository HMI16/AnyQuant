package com.bigwork.sql.impl;

import com.bigwork.model.Stock;
import com.bigwork.sql.implservice.IndustrySQLImplService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by 15HR-1528SS on 2016/6/7.
 */
public class IndustrySQLImpl extends SQLImpl implements IndustrySQLImplService {
    public IndustrySQLImpl(Connection con) {
        super(con);
    }

    @Override
    public void insertIndustry(String id, String type) throws SQLException {
        try {
            psql = con.prepareStatement("INSERT INTO industry VALUES (?,?)");
            psql.setString(1, id);
            psql.setString(2, type);

            psql.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("插入行业数据失败");
        }
    }

    @Override
    public ArrayList<ArrayList<String>> selectAll() throws SQLException {
        ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
        try {
            psql = con.prepareStatement("SELECT * FROM industry");
            rs = psql.executeQuery();
            while (rs.next()) {
                ArrayList<String> A = new ArrayList<>();
                A.add(rs.getString("id"));
                A.add(rs.getString("type"));
                list.add(A);
            }

            rs.close();
        } catch (SQLException e) {
            throw new SQLException("读取行业数据失败");
        }
        return list;
    }
}
