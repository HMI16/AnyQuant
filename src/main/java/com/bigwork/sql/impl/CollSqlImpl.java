package com.bigwork.sql.impl;

import com.bigwork.model.Collection;
import com.bigwork.model.User;
import com.bigwork.sql.implservice.CollSQLImplService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by 15HR-1528SS on 2016/5/30.
 */
public class CollSqlImpl extends SQLImpl implements CollSQLImplService {
    public CollSqlImpl(Connection con) {
        super(con);
    }

    /**
     * 新增收藏
     *
     * @param user 收藏的用户主体
     * @param id 该用户收藏股票的ID
     * @throws SQLException
     */
    @Override
    public void insertColl(User user, String id) throws SQLException {
        try {
            psql = con.prepareStatement("INSERT INTO usercoll VALUES (?,?)");
            psql.setString(1, user.getName());
            psql.setString(2, id);

            psql.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("插入用户信息数据失败");
        }
    }

    /**
     * 根据用户来获得该用户所有的股票收藏
     *
     * @param user User对象
     * @return Collection对象的ArrayList
     * @throws SQLException
     */
    @Override
    public ArrayList<Collection> selectColl(User user) throws SQLException {
        ArrayList<Collection> array = new ArrayList<>();

        try {
            psql = con.prepareStatement("SELECT * FROM usercoll WHERE username = ?");
            psql.setString(1, user.getName());

            rs = psql.executeQuery();

            while (rs.next()) {
                Collection coll = new Collection();
                coll.setUser_name(rs.getString("username"));
                coll.setStock_id(rs.getString("stockid"));

                array.add(coll);
            }

            rs.close();
        } catch (SQLException e) {
            throw new SQLException("读取用户信息数据失败");
        }

        return array;
    }
}
