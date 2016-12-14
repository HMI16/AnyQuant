package com.bigwork.controller;

import com.bigwork.bl.managementServiceImpl.pR_Impl;
import com.bigwork.bl_service.pR_service;
import com.bigwork.model.pRValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/1.
 */
@Controller
public class pRController {
    pR_service pr = new pR_Impl();

    @RequestMapping(value = "/pRdayGraph", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<pRValue> getpRday(){
//            @RequestParam("from")String from, @RequestParam("to")String to, @RequestParam("id")String id, @RequestParam("day")int day){

//        ArrayList<pRValue> result = pr.getpRGraphValue(id, from, to, day);
        ArrayList<pRValue> result = pr.getpRGraphValue("sh600979", "2015-10-11", "2016-05-01", 40);
        return result;
    }

    @RequestMapping(value = "/pR20Graph", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<pRValue> getpR26(){
//    @RequestParam("from")String from, @RequestParam("to")String to, @RequestParam("id")String id){
//        ArrayList<pRValue> result = pr.getpRGraphValue(id, from, to);
        ArrayList<pRValue> result = pr.getpRGraphValue("sh600979", "2015-10-11", "2016-05-01");
        return result;
    }

    @RequestMapping(value = "/pRSinLen", method = RequestMethod.GET)
    @ResponseBody
    public double gepRSingleValue(@RequestParam("id")String id, @RequestParam("from")String from, @RequestParam("to")String to){
        return pr.getpR(id, from, to);
    }

    @RequestMapping(value = "/pRSinDay", method = RequestMethod.GET)
    @ResponseBody
    public double getpRSingleValue(@RequestParam("id")String id, @RequestParam("day")int day, @RequestParam("to")String to){
        return pr.getpR(id, day, to);
    }

    @RequestMapping(value = "/pRSinDay20", method = RequestMethod.GET)
    @ResponseBody
    public double getpRSingleValue(@RequestParam("id")String id, @RequestParam("to")String to){
        return pr.getpR(id, to);
    }
}
