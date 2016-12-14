package com.bigwork.bl_service;

import com.bigwork.model.RSIValue;

import java.util.ArrayList;

/**
 * Created by ryysuke on 16/5/12.
 */
public interface RSI_service {

    public double getRSI(String ID, String date1, String date2);
    public double getRSI(String ID, int day, String date);
    public double getRSI(String ID,  String date);
    public ArrayList<RSIValue> getRSIGraphValue(String id, String from, String to, int day);
    public ArrayList<RSIValue> getRSIGraphValue(String id, String from, String to);
}
