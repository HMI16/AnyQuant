package com.bigwork.bl_service;

import com.bigwork.model.ATRValue;

import java.text.ParseException;
import java.util.ArrayList;

public interface ATR_service {


    /**
     *
     * @param id  id of stock
     * @param date1 start time
     * @param date2 end time
     * @return ATR value of these days
     */
    double GetATR(String id, String date1, String date2);

    /**
     * 获取一段时间内的单支股票均幅指标
     *
     * @param ID : id of stock
     * @param day : choose from 6/12/24 days
     * @param date : to day
     * @return ATR Value of these days
     * @throws ParseException
     */
    double GetATR(String ID, int day, String date) throws ParseException;

    /**
     *
     * @param id stock id
     * @param from start time
     * @param to end time
     * @param day date gap
     * @return Arraylist of ATRValue, including date&ATRValue in every single day
     */
    ArrayList<ATRValue> getATRGraphValue(String id, String from, String to, int day);
}
