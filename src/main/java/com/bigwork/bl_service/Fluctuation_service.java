package com.bigwork.bl_service;

import java.util.ArrayList;

public interface Fluctuation_service {

    /**
     * 获取一段时间内的某只股票的历史波动率
     *
     * @param ID : id of stock
     * @param year : year of fluctuation
     * @return value
     */

    public double getFluctuation(String ID, String year);

    /**
     * 获取一段时间内的某只股票的历史波动率
     *
     * 在写month的时候一定一定要用两位数字表示，如5月用05表示！！！
     *
     * @param ID : id of stock
     * @param year : year of fluctuation
     * @param month : month of fluctuation
     * @return value
     */

    public double getFluctuation(String ID, String year, String month);



}
