package com.bigwork.bl_service;

import com.bigwork.model.MACDValue;

import java.util.ArrayList;

/**
 * Created by asus on 2016/5/30.
 */
public interface MACD_service {

    public ArrayList<MACDValue> getMACDGraph(String id, String from, String to) throws Exception;
}
