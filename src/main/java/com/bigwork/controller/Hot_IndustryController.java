package com.bigwork.controller;

import com.bigwork.bl.listServiceImpl.StockList_Impl;
import com.bigwork.bl.managementServiceImpl.HotIndustry_Impl;
import com.bigwork.bl_service.HotIndustry_service;
import com.bigwork.bl_service.StockList_service;
import com.bigwork.model.Industry;
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
public class Hot_IndustryController {

    @RequestMapping(value = "/IndustryStockList", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Stock> getStocks(@RequestParam("type") String type){
        HotIndustry_service hot = new HotIndustry_Impl();
        ArrayList<Stock> result = hot.getStockList(type);
        return result;
    }
//    //���Ź�Ʊ�б�
//    @RequestMapping(value = "/HotStockList", method = RequestMethod.GET)
//    @ResponseBody
//    public ArrayList<Stock> HotStockList(){
//        ArrayList<Stock> list = new ArrayList<>();
//        StockList_service stockList = new StockList_Impl();
//        list = stockList.ShowAll();
//        return list;
//    }
//
//    //������ҵ�б�
//    @RequestMapping(value = "/HotIndustryList", method = RequestMethod.GET)
//    @ResponseBody
//    public ArrayList<Industry> HotIndustryList(){
//        ArrayList<Industry> list = new ArrayList<>();
//        StockList_service stockList = new StockList_Impl();
////        list = stockList.ShowAll();
//        list.add(new Industry("aaa", 1));
//        list.add(new Industry("bbb", 2));
//        list.add(new Industry("ccc", 3));
//        return list;
//    }
}
