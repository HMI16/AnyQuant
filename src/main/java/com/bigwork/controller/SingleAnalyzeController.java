package com.bigwork.controller;

import com.bigwork.bl.managementServiceImpl.SingleAnalyze_Impl;
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
public class SingleAnalyzeController {
    private SingleAnalyze_Impl singleAnalyze = new SingleAnalyze_Impl();
    @RequestMapping(value = "/GetSingleAnalyze", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<String> getSingleAnalyze(@RequestParam("id")String id, @RequestParam(value = "data", required = false) String data){
        ArrayList<String> result = new ArrayList<String>();
        try {
            if(data==null){
                data = "2016-06-15";
            }
            result = singleAnalyze.getSingleAnalyze(id, data);
            return result;
        } catch (Exception e) {
            return null;
        }
    }
}
