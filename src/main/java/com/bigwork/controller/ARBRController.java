package com.bigwork.controller;

import com.bigwork.bl.managementServiceImpl.ARBR_Impl;
import com.bigwork.bl_service.ARBR_service;
import com.bigwork.model.ARBRValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;

/**
 * Created by asus on 2016/5/31.
 */
@Controller
public class ARBRController {
    @RequestMapping(value = "/ARBRdayGraph", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<ARBRValue> getARBRday(){
//            @RequestParam("from")String from, @RequestParam("to")String to, @RequestParam("id")String id, @RequestParam("day")int day){
        ARBR_service arbr = new ARBR_Impl();
        ArrayList<ARBRValue> result = arbr.getARBRGraph("sh600979", "2015-10-11", "2016-05-01");
        return result;
    }

    @RequestMapping(value = "/ARBR26Graph", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<ARBRValue> getARBR26(){
//        @RequestParam("from")String from,
//    } @RequestParam("to")String to, @RequestParam("id")String id){
        ARBR_service arbr = new ARBR_Impl();
        ArrayList<ARBRValue> result = arbr.getARBRGraph("sh600979", "2015-10-11", "2016-05-01");
        return result;
    }

}
