package com.bigwork.controller;

import com.bigwork.bl.managementServiceImpl.BIAS_Impl;
import com.bigwork.bl_service.BIAS_service;
import com.bigwork.model.BIASValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by asus on 2016/5/31.
 */
@Controller
public class BIASController {

    private BIAS_service bias = new BIAS_Impl();

    @RequestMapping(value = "/BIASSinLen", method = RequestMethod.GET)
    @ResponseBody
    public double getBIASSingleValue(@RequestParam("id")String id, @RequestParam("from")String from, @RequestParam("to")String to){
        try{
            return bias.getBIAS(id, from, to);
        }catch (ParseException e){
            return 0;
        }
    }

    @RequestMapping(value = "/BIASSinDay", method = RequestMethod.GET)
    @ResponseBody
    public double getBIASSingleValue(@RequestParam("id")String id, @RequestParam("day")int day, @RequestParam("to")String to){
        return bias.getBIAS(id, day, to);
    }


    @RequestMapping(value = "/BIASGraph", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<BIASValue> getBIASGraph(@RequestParam("id")String id, @RequestParam("from")String from, @RequestParam("to")String to, @RequestParam("day")int day){
        return bias.getBIASGraph(id, from, to, day);
    }
}
