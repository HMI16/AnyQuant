package com.bigwork.bl_service;

import com.bigwork.model.KGraphValue;

import java.text.ParseException;
import java.util.ArrayList;

public interface KGraphData_service {
    /**
     * 获取K线图所需要的数据
     *
     * @return
     * @throws ParseException
     */
    //public String[][] GetKGraphData();

    public int getDay();

    public void setDate(String date1, String date2);

    public String[] getDate();

    public ArrayList<KGraphValue> getKGraphData();
}