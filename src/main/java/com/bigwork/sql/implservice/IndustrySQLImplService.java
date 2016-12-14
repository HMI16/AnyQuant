package com.bigwork.sql.implservice;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by 15HR-1528SS on 2016/6/7.
 */
public interface IndustrySQLImplService {
    void insertIndustry(String id,String type) throws SQLException;

    ArrayList<ArrayList<String>> selectAll() throws SQLException;
}
