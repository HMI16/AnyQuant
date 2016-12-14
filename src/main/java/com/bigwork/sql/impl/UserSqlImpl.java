package com.bigwork.sql.impl;

import com.bigwork.model.User;
import com.bigwork.sql.implservice.UserSQLImplService;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by 15HR-1528SS on 2016/5/30.
 */
public class UserSqlImpl extends SQLImpl implements UserSQLImplService {
    public UserSqlImpl(Connection con) {
        super(con);
    }

    /**
     * 新增用户
     *
     * @param user 插入的user对象
     * @throws SQLException
     */
    @Override
    public void insertUser(User user) throws SQLException {
        try {
            psql = con.prepareStatement("INSERT INTO userinfo VALUES (?,?)");
            psql.setString(1, user.getName());
            psql.setString(2, user.getPassword());

            psql.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("插入用户信息数据失败");
        }
    }

    /**
     * 查询用户信息
     *
     * @param name 用户名
     * @return User对象
     * @throws SQLException
     */
    @Override
    public User selectUser(String name) throws SQLException {
        User user = new User();

        try {
            psql = con.prepareStatement("SELECT * FROM userinfo WHERE username = ?");
            psql.setString(1, name);

            rs = psql.executeQuery();

            while (rs.next()) {
                user = new User();
                user.setName(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }

            rs.close();
        } catch (SQLException e) {
            throw new SQLException("读取用户信息数据失败");
        }

        return user;
    }
}
