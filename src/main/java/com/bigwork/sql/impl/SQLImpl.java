package com.bigwork.sql.impl;



import com.bigwork.sql.implservice.SQLImplService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by 15HR-1528SS on 2016/5/5.
 */
public class SQLImpl implements SQLImplService {
    Connection con;
    PreparedStatement psql;
    ResultSet rs;

    public SQLImpl(Connection con) {
        this.con = con;
    }
}
