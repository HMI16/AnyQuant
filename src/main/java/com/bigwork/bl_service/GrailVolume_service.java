package com.bigwork.bl_service;

import com.bigwork.model.GrailVolume;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/19.
 */
public interface GrailVolume_service {
    public ArrayList<GrailVolume> getGrailVolume(String start, String end);
}
