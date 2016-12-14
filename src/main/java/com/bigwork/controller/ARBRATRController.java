package com.bigwork.controller;

import com.bigwork.bl.managementServiceImpl.ARBR_Impl;
import com.bigwork.bl.managementServiceImpl.ATR_Impl;
import com.bigwork.bl_service.ARBR_service;
import com.bigwork.bl_service.ATR_service;
import com.bigwork.model.ARBRATRValue;
import com.bigwork.model.ARBRValue;
import com.bigwork.model.ATRValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by asus on 2016/6/13.
 */
@Controller
public class ARBRATRController {
    @RequestMapping(value = "/ARBRATRGraph", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<ARBRATRValue> getABTRGraph(@RequestParam("id")String id, @RequestParam(value="from",required = false)String from,
                                                @RequestParam(value = "to", required = false)String to){
        ArrayList<ARBRATRValue> result = new ArrayList<>();
        ATR_service atr = new ATR_Impl();
        ARBR_service arbr = new ARBR_Impl();
        if(from==null){
            from = "2015-05-10";
        }
        if(to==null){
            to="2016-06-10";
        }
        ArrayList<ATRValue> dataATR = atr.getATRGraphValue(id, from, to, 26);
        ArrayList<ARBRValue> dataARBR = arbr.getARBRGraph(id, from, to, 26);
        int count=0;
        int m = dataATR.size();
        int n = dataARBR.size();
        if(m>n){
            for(int i=0;i<n;i++) {
                if (dataARBR.get(i).getDate().equals(dataATR.get(i + m - n).getDate())) {
                    count++;
                }
                ARBRATRValue tmp = new ARBRATRValue(dataARBR.get(i).getDate(), dataATR.get(i+m-n).getATR(),
                        dataARBR.get(i).getAR(), dataARBR.get(i).getBR());
                result.add(tmp);
            }
        }else{
            for(int i=0;i<m;i++) {
                if (dataARBR.get(i+n-m).getDate().equals(dataATR.get(i).getDate())) {
                    count++;
                }
                ARBRATRValue tmp = new ARBRATRValue(dataATR.get(i).getDate(), dataATR.get(i).getATR(),
                        dataARBR.get(i+m-n).getAR(), dataARBR.get(i+m-n).getBR());
                result.add(tmp);
            }
        }
        System.out.println("m = " + m + "   n = " + n + "   count = " + count);
        return result;
    }

//    public static void main(String[] args) {
//        ARBRATRController t = new ARBRATRController();
//        ArrayList<ARBRATRValue> re = t.getABTRGraph("hs300",null,null);
//        for(ARBRATRValue temp : re){
//            System.out.println(temp.getAr());
//        }
//    }
}
