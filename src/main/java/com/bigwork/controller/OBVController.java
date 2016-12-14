package com.bigwork.controller;

import com.bigwork.bl.managementServiceImpl.OBV_Impl;
import com.bigwork.bl_service.OBV_service;
import com.bigwork.model.OBVValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/1.
 */
@Controller
public class OBVController {
    private OBV_service obv = new OBV_Impl();

    @RequestMapping(value = "/OBVGraph", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<OBVValue> getBIASGraph(@RequestParam("id")String id, @RequestParam("from")String from, @RequestParam("to")String to){
        return obv.getOBVGraphValue(id, from, to);
    }

    public static void main(String[] args) {
        OBVController obv = new OBVController();
        ArrayList<OBVValue> re = obv.getBIASGraph("sh600000", "2015-12-11", "2016-05-20");
        System.out.println("````````````realFrom = " + re.get(0).getdate() + "  realTo = " + re.get(re.size()-1).getdate());
    }
}
