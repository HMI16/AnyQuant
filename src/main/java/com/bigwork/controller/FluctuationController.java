package com.bigwork.controller;

import com.bigwork.bl.managementServiceImpl.Fluctuation_Impl;
import com.bigwork.bl_service.Fluctuation_service;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by asus on 2016/5/31.
 */
@Controller
public class FluctuationController {

    private Fluctuation_service fluc = new Fluctuation_Impl();

    @RequestMapping(value = "/FluctuationYear", method = RequestMethod.GET)
    @ResponseBody
    public double getFluc(@RequestParam("id")String id, @RequestParam("year")String year){
        return fluc.getFluctuation(id, year);
    }

    @RequestMapping(value = "/FluctuationMonth", method = RequestMethod.GET)
    @ResponseBody
    public double getFluc(@RequestParam("id")String id, @RequestParam("year")String year, @RequestParam("month")String month){
        return fluc.getFluctuation(id, year, month);
    }
}
