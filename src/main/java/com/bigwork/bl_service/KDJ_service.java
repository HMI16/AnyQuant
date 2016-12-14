package com.bigwork.bl_service;

import com.bigwork.model.KDJValue;

import java.util.ArrayList;

/**
 * Created by ryysuke on 16/5/13.
 */
public interface KDJ_service {

    public ArrayList<KDJValue> getKDJGraph(String ID, String from, String to, int day);

}