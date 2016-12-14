package com.bigwork.bl.listServiceImpl;

import com.bigwork.bl_service.Index_service;
import com.bigwork.model.Index;
import com.bigwork.sql.MysqlLink;
import com.bigwork.sql.SqlLinkException;
import com.bigwork.sql.impl.IndexSqlImpl;
import com.bigwork.sql.implservice.IndexSQLImplService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by asus on 2016/6/17.
 */
public class IndexImpl implements Index_service {
    @Override
    public ArrayList<Index> getIndecies() {
        MysqlLink link = new MysqlLink();
        try {
            link.link("115.28.40.144", "root", "141250185");
        } catch (SqlLinkException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        IndexSQLImplService index =  link.getIndexSQLImpl();
        try {
            return index.selectArray();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        IndexImpl s = new IndexImpl();
        System.out.println(s.getIndecies().size());
    }
}

