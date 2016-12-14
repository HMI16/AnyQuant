package com.bigwork.bl_service;

import com.bigwork.model.ARBRValue;

import java.util.ArrayList;

/**
 * Created by ryysuke on 16/5/12.
 */
public interface ARBR_service {


    public ArrayList<ARBRValue> getARBRGraph(String id, String from, String to, int day);

    public ArrayList<ARBRValue> getARBRGraph(String id, String from, String to);

}
