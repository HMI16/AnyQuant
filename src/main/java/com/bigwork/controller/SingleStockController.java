package com.bigwork.controller;

import com.bigwork.bl.listServiceImpl.SingleStock_Impl;
import com.bigwork.bl_service.SingleStock_service;
import com.bigwork.model.Stock;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/24.
 */
@Controller
public class SingleStockController {

    //Ĭ��һ�����ڹ�Ʊ��Ϣ
    @RequestMapping("/SingleStockList")
    @ResponseBody
    public ArrayList<Stock> SingleStockList(@RequestParam("id")String id){
        ArrayList<Stock>  list =  new ArrayList<>();
        SingleStock_service singleStock = new SingleStock_Impl();
        list = singleStock.Show(id);
        return list;
    }

    @RequestMapping("/SingleStockListByTime")
    @ResponseBody
    public ArrayList<Stock> SingleStockListByTime(@RequestParam("id")String id, @RequestParam("startTime")String startTime, @RequestParam("endTime")String endTime){
        ArrayList<Stock>  list =  new ArrayList<>();
        SingleStock_service singleStock = new SingleStock_Impl();
        list = singleStock.setTimeFromDB(id,startTime,endTime);
        return list;
    }

}
