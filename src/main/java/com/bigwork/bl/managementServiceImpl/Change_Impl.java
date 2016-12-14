package com.bigwork.bl.managementServiceImpl;

import com.bigwork.bl.listServiceImpl.SingleStock_Impl;
import com.bigwork.bl_service.Change_service;
import com.bigwork.bl_service.SingleStock_service;
import com.bigwork.model.Stock;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by 15HR-1528SS on 2016/6/16.
 */
public class Change_Impl implements Change_service {
    String id;
    String date;

    SingleStock_service single = new SingleStock_Impl();
    CalcuDate cal = new CalcuDate();

    @Override
    public double getChange(String id, String date) {
        this.id = id;
        this.date = date;

        double result = 0;
        ArrayList<Stock> list = single.setTimeFromDB(id, cal.calDate(date, -6), date);
        if (list.get(0).getClose() == 0)
            return 0;

        result = (list.get(list.size() - 1).getClose() - list.get(0).getClose()) / list.get(0).getClose();
        DecimalFormat df = new DecimalFormat("######0.00");

        BigDecimal bg = new BigDecimal(result);
        double d = bg.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
        return d;
    }

    public static void main(String[] args) {
        Change_service c = new Change_Impl();
        double result = c.getChange("sh600724", "2016-06-17");
        System.out.println(result);
    }
}
