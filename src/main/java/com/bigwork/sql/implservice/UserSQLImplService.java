package com.bigwork.sql.implservice;

import com.bigwork.model.User;

import java.sql.SQLException;

/**
 * Created by 15HR-1528SS on 2016/5/30.
 */
public interface UserSQLImplService {
    void insertUser(User user) throws SQLException;

    User selectUser(String name) throws SQLException;
}
