package com.bigwork.bl_service;


import com.bigwork.model.MAValue;

import java.util.ArrayList;

/**
 * Created by ryysuke on 16/5/12.
 */
public interface MA_service {


    public double getMA(String ID, String date1, String date2);

    public double getMA(String ID, int day, String to);

    public ArrayList<MAValue> getMAGraph(String ID, String from, String to, int day);
}
