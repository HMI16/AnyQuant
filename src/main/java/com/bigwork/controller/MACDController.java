package com.bigwork.controller;

import com.bigwork.bl.managementServiceImpl.MACD_Impl;
import com.bigwork.bl_service.MACD_service;
import com.bigwork.model.MACDValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * Created by asus on 2016/5/31.
 */
@Controller
public class MACDController {

    private MACD_service macd = new MACD_Impl();

    @RequestMapping(value = "/MACDGraph", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<MACDValue> getMACDGraph(@RequestParam("id")String id, @RequestParam(value = "from", required = false) String from,@RequestParam(value = "to", required = false)String to){
        try {
            if(from==null){
                from = "2015-05-10";
            }
            if(to==null){
                to="2016-06-10";
            }
            ArrayList<MACDValue> result = macd.getMACDGraph(id, from, to);
            return result;
        } catch (Exception e) {
            return null;
        }
    }
}
