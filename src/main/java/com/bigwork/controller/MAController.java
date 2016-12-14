package com.bigwork.controller;

import com.bigwork.bl.managementServiceImpl.MA_Impl;
import com.bigwork.bl_service.MA_service;
import com.bigwork.model.MAValue;
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
public class MAController {

    private MA_service ma = new MA_Impl();

    @RequestMapping(value = "MASinLen", method = RequestMethod.GET)
    @ResponseBody
    public double getSingleValue(@RequestParam("id")String id, @RequestParam("from")String from, @RequestParam("to")String to){
        return ma.getMA(id, from, to);
    }


    @RequestMapping(value = "MASinDay", method = RequestMethod.GET)
    @ResponseBody
    public double getSingleValue(@RequestParam("id")String id, @RequestParam("day")int day, @RequestParam("to")String to){
        return ma.getMA(id, day, to);
    }


    @RequestMapping(value = "MAGraph", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<MAValue> getMAGraph(@RequestParam("id")String id, @RequestParam("day")int day, @RequestParam("to")String to, @RequestParam("from")String from){
        return ma.getMAGraph(id, from, to, day);
    }
}
