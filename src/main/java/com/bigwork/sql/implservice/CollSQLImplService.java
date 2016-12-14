package com.bigwork.sql.implservice;

import com.bigwork.model.Collection;
import com.bigwork.model.User;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by 15HR-1528SS on 2016/5/30.
 */
public interface CollSQLImplService {
    void insertColl(User user, String id) throws SQLException;

    ArrayList<Collection> selectColl(User user) throws SQLException;
}
