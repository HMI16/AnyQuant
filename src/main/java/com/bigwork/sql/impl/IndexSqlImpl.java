package com.bigwork.sql.impl;

import com.bigwork.model.Index;
import com.bigwork.sql.implservice.IndexSQLImplService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by 15HR-1528SS on 2016/6/17.
 */
public class IndexSqlImpl extends SQLImpl implements IndexSQLImplService {
    public IndexSqlImpl(Connection con) {
        super(con);
    }

    @Override
    public void insertIndex(Index index) throws SQLException {
        try {
            psql = con.prepareStatement("INSERT INTO indexdata VALUES (?,?,?)");
            psql.setString(1, index.getId());
            psql.setDouble(2, index.getFlu());
            psql.setDouble(3, index.getChange());

            psql.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("插入股票指标数据失败");
        }
    }

    @Override
    public ArrayList<Index> selectArray() throws SQLException {
        ArrayList<Index> array = new ArrayList<>();

        try {
            psql = con.prepareStatement("SELECT * FROM indexdata");

            rs = psql.executeQuery();

            while (rs.next()) {
                Index Index = new Index();
                Index.setId(rs.getString("id"));
                Index.setFlu(rs.getDouble("flu"));
                Index.setChange(rs.getDouble("change"));

                array.add(Index);
            }
        } catch (SQLException e) {
            throw new SQLException("读取股票指标数据失败");
        }

        return array;
    }

    @Override
    public void insetArray(ArrayList<Index> array) throws SQLException {
        psql = con.prepareStatement("DELETE FROM indexdata");
        psql.executeUpdate();

        for (Index index : array)
            insertIndex(index);
    }
}
