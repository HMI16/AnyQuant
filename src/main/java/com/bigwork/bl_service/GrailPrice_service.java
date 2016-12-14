package com.bigwork.bl_service;

import com.bigwork.model.GrailPrice;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/18.
 */
public interface GrailPrice_service {
    public ArrayList<GrailPrice> getGrailPrice(String start,String end);
}
