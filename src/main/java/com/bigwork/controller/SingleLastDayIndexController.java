package com.bigwork.controller;

import com.bigwork.bl.listServiceImpl.SingleLastDayIndex;
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
public class SingleLastDayIndexController {
    private SingleLastDayIndex singleLastDayIndex = new SingleLastDayIndex();
    @RequestMapping(value = "/GetLastDayIndex", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Double> GetLastDayIndex(@RequestParam("id")String id){

        ArrayList<Double> result = singleLastDayIndex.getLastDayIndex(id);
        return result;

    }

}
