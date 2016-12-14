package com.bigwork.controller;

import com.bigwork.bl.listServiceImpl.GrailPrice_Impl;
import com.bigwork.bl_service.GrailPrice_service;
import com.bigwork.model.GrailPrice;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/19.
 */
@Controller
public class GrailPriceController {
    private GrailPrice_service grailPrice = new GrailPrice_Impl();
    @RequestMapping(value = "/GetGrailPrice", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<GrailPrice> GetGrailPrice(@RequestParam("id")String id, @RequestParam(value = "from", required = false) String from,@RequestParam(value = "to", required = false)String to){
        try {
            if(from==null){
                from = "2015-05-10";
            }
            if(to==null){
                to = "2016-06-10";
            }
            ArrayList<GrailPrice> result = grailPrice.getGrailPrice(from, to);
            return result;
        } catch (Exception e) {
            return null;
        }
    }
}
