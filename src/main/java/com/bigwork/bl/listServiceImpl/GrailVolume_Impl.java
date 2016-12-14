package com.bigwork.bl.listServiceImpl;

import com.bigwork.bl_service.GrailVolume_service;
import com.bigwork.bl_service.Grail_service;
import com.bigwork.model.Grail;
import com.bigwork.model.GrailVolume;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/19.
 */
public class GrailVolume_Impl implements GrailVolume_service{
    Grail_service grail = new Grail_Impl();

    @Override
    public ArrayList<GrailVolume> getGrailVolume(String start, String end) {
        ArrayList<Grail> graillist = grail.setTime(start,end);
        ArrayList<GrailVolume> result = new ArrayList<GrailVolume>();
        for(int i = 0;i<graillist.size();i++){
            GrailVolume temp = new GrailVolume();
            temp.setDate(graillist.get(i).getDate());
            String a = graillist.get(i).getVolume().substring(0,graillist.get(i).getVolume().length()-6);
            int b = Integer.parseInt(a);
            temp.setVolume(b);
            result.add(temp);
        }
        return result;
    }
}
