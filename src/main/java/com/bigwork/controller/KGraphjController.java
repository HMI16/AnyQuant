package com.bigwork.controller;

import com.bigwork.bl.managementServiceImpl.KGraphData_Impl;
import com.bigwork.bl.managementServiceImpl.OBV_Impl;
import com.bigwork.bl_service.KGraphData_service;
import com.bigwork.bl_service.OBV_service;
import com.bigwork.model.KGraphValue;
import com.bigwork.model.OBVValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/2.
 */
@Controller
public class KGraphjController {
    @RequestMapping(value = "/getKraphData", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<KGraphValue> getKGraphData(@RequestParam("id")String id, @RequestParam(value="from",required = false)String from,
                                                @RequestParam(value = "to", required = false)String to
    ){//
                                    // ,@RequestParam("start")String start,@RequestParam("end")String end){
        if(from==null){
            from = "2015-05-10";
        }
        if(to==null){
            to="2016-06-10";
        }
        KGraphData_service kGraphData = new KGraphData_Impl(id,from,to);
        OBV_service obv = new OBV_Impl();
        ArrayList<OBVValue> obvValues = obv.getOBVGraphValue(id, from, to);
        ArrayList<KGraphValue> k = kGraphData.getKGraphData();
        ArrayList<KGraphValue> result = new ArrayList<>();
        int m=k.size();
        int n = obvValues.size();
        if(m>n){
            for(int i=0;i<n;i++){
                KGraphValue tmp = new KGraphValue();
                tmp.setDate(obvValues.get(i).getdate());
                tmp.setClose(k.get(i+m-n).getClose());
                tmp.setHigh(k.get(i+m-n).getHigh());
                tmp.setLow(k.get(i+m-n).getLow());
                tmp.setOpen(k.get(i+m-n).getOpen());
                tmp.setObv(obvValues.get(i).getObvsi());
                result.add(tmp);
            }
        }else{
            for(int i=0;i<m;i++){
                KGraphValue tmp = new KGraphValue();
                tmp.setDate(k.get(i).getDate());
                tmp.setClose(k.get(i).getClose());
                tmp.setHigh(k.get(i).getHigh());
                tmp.setLow(k.get(i).getLow());
                tmp.setOpen(k.get(i).getOpen());
                tmp.setObv(obvValues.get(i+n-m).getObvsi());
                result.add(tmp);
            }
        }
        //String[][] data = kGraphData.GetKGraphData();
        return result;
    }


}
