package com.bigwork.bl.listServiceImpl;

import com.bigwork.bl_service.GrailPrice_service;
import com.bigwork.bl_service.Grail_service;
import com.bigwork.model.Grail;
import com.bigwork.model.GrailPrice;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/19.
 */
public class GrailPrice_Impl implements GrailPrice_service {

    Grail_service grail = new Grail_Impl();
    public ArrayList<GrailPrice> getGrailPrice(String start,String end) {
        ArrayList<Grail> graillist = grail.setTime(start,end);
        ArrayList<GrailPrice> result = new ArrayList<>();
        for(int i = 0; i< graillist.size(); i++){
            GrailPrice temp = new GrailPrice();
            temp.setOpen(graillist.get(i).getOpen());
            temp.setClose(graillist.get(i).getClose());
            temp.setHigh(graillist.get(i).getHigh());
            temp.setLow(graillist.get(i).getLow());
            result.add(temp);
        }
        return result;
    }
}
