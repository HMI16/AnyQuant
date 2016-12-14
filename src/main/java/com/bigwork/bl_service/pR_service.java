package com.bigwork.bl_service;

import com.bigwork.model.pRValue;

import java.util.ArrayList;

/**
 * Created by ryysuke on 16/5/12.
 */
public interface pR_service {

    public double getpR(String ID, String date1, String date2);
    public double getpR(String ID, int day, String date);
    public double getpR(String ID, String date);
    public ArrayList<pRValue> getpRGraphValue(String id, String from, String to);
    public ArrayList<pRValue> getpRGraphValue(String id, String from, String to, int day);
}
