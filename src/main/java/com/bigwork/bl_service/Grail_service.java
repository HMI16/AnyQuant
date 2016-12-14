package com.bigwork.bl_service;

import com.bigwork.model.Grail;
import com.bigwork.sql.SqlImplNullPointerException;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public interface Grail_service {


    //public void ShowGrail();

    /**
     * 显示一个月内打大盘信息列表
     *
     * @return
     */
    public ArrayList<Grail> ShowGrailList();

    /**
     *
     * @param start
     * @param end
     * @return
     * @throws ParseException
     * @throws SQLException
     * @throws SqlImplNullPointerException
     */
    public ArrayList<Grail> setTime(String start, String end);

    /**
     * 关闭大盘列表，返回股票列表
     */
    public void close();

    /**
     * 根据某项数据条件对列表进行筛选
     *
     * @param F
     * @return
     */
    public ArrayList<Grail> filter(String F);

}
