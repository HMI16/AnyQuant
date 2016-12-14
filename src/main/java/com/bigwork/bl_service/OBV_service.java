package com.bigwork.bl_service;

import com.bigwork.model.OBVValue;

import java.util.ArrayList;

/**
 * Created by ryysuke on 16/5/12.
 */
public interface OBV_service {

    public ArrayList<OBVValue> getOBVGraphValue(String id, String from, String to);
}
