package com.bigwork.controller;

import com.bigwork.bl.listServiceImpl.IndexImpl;
import com.bigwork.bl.listServiceImpl.SingleStock_Impl;
import com.bigwork.bl_service.Index_service;
import com.bigwork.bl_service.SingleStock_service;
import com.bigwork.model.Index;
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
public class IndexController {

    @RequestMapping(value = "/SingleStockIndexs", method = RequestMethod.GET)
    @ResponseBody
    public Double[] SingleStockIndexs(@RequestParam("id")String id){
        ArrayList<Stock>  list =  new ArrayList<>();
        SingleStock_service singleStock = new SingleStock_Impl();
        list = singleStock.Show(id);

        Double[] index= {1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0,9.0,10.0};
        return index;
    }

    @RequestMapping(value = "/PartIndex", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Index> getIndex(){
        ArrayList<Index> indexArrayList = new ArrayList<>();
        for(int i = 1;i < 99; i++){
            indexArrayList.add(new Index("111",i,i));
        }
//        Index_service index = new IndexImpl();
//        return index.getIndecies();
        return indexArrayList;
    }
}
