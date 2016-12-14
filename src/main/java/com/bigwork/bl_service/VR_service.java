package com.bigwork.bl_service;

import com.bigwork.model.VRValue;

import java.util.ArrayList;

/**
 * Created by ryysuke on 16/5/12.
 */
public interface VR_service {

    public double getVR(String ID, String date1, String date2);
    public double getVR(String ID, int day, String date);
    public double getVR(String ID,String date);
    public  ArrayList<VRValue> getVRGraphValue(String id, String from, String to, int day);
    public  ArrayList<VRValue> getVRGraphValue(String id, String from, String to);
}
