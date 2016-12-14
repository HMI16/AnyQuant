package com.bigwork.bl_service;

import com.bigwork.model.BIASValue;

import java.text.ParseException;
import java.util.ArrayList;

public interface BIAS_service {
    public double getBIAS(String ID, String date1, String date2) throws ParseException;

    public double getBIAS(String ID, int day, String to);

    public ArrayList<BIASValue> getBIASGraph(String ID, String from, String to, int day);

}