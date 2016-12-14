package com.bigwork.controller;

import com.bigwork.bl.managementServiceImpl.ATR_Impl;
import com.bigwork.bl_service.ATR_service;
import com.bigwork.model.ATRValue;

import java.util.ArrayList;

/**
 * Created by ryysuke on 16/6/2.
 */
public class TestATR {
    public static void main(String[] args) {
        ATR_service a = new ATR_Impl();
        ArrayList<ATRValue> re = a.getATRGraphValue("sh600979", "2015-10-11", "2016-05-01", 6);
        System.out.println(re.size());
    }
}
