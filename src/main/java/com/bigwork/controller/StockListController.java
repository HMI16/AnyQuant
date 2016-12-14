package com.bigwork.controller;

import com.bigwork.bl.listServiceImpl.Grail_Impl;
import com.bigwork.bl.listServiceImpl.IndexImpl;
import com.bigwork.bl.listServiceImpl.StockList_Impl;
import com.bigwork.bl.managementServiceImpl.HotIndustry_Impl;
import com.bigwork.bl_service.Grail_service;
import com.bigwork.bl_service.HotIndustry_service;
import com.bigwork.bl_service.Index_service;
import com.bigwork.bl_service.StockList_service;
import com.bigwork.model.*;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Administrator on 2016/5/24.
 */
@Controller
public class StockListController {


//    public static void main(String[] args) {
//        StockList_service stockList_service = new StockList_Impl();
//        ArrayList<Stock> result = stockList_service.ShowAll();
//        System.out.println("stocklist = " + result.size());
//        System.out.println("adj_price = " + result.get(0).getAdj_price());
//        System.out.println("pb = " + result.get(0).getPd());
//    }

    HotIndustry_service hot = new HotIndustry_Impl();


    @RequestMapping(value = "/StockList", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Stock> getAllStocks(){
        StockList_service stockList_service = new StockList_Impl();
        ArrayList<Stock> result = stockList_service.ShowAll();
        System.out.println("stocklist = " + result.size());
        return result;
    }




    //��Ʊ�б�
    @RequestMapping(value = "/IndustryList", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<HotStock> IndustryList(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date = df.format(new Date());
        ArrayList<HotStock> re = new ArrayList<>();
        re = hot.getHotListfromDB();
        return re;
//        return hot.getHotList(7,date);

    }

    //����ɸѡ����ɸѡ
    @RequestMapping(value = "/StockFilt", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Stock> StockFilt(@RequestParam("Ladj_price")double Hadj_price,@RequestParam("Hadj_price")double Ladj_price,
                                      @RequestParam("Lvolume")int Hvolume,@RequestParam("Hvolume")int Lvolume ,
                                      @RequestParam("Lturnover")double Hturnover,@RequestParam("Hturnover")double Lturnover,
                                      @RequestParam("Lpe")double Hpe,@RequestParam("Hpe")double Lpe,
                                      @RequestParam("Lpd")double Hpd,@RequestParam("Hpd")double Lpd){
        ArrayList<Stock> list = new ArrayList<>();
        Stock LOW = new Stock(Ladj_price,Lvolume,Lturnover,Lpe,Lpd);
        Stock HIGH = new Stock(Hadj_price,Hvolume,Hturnover,Hpe,Hpd);
        StockList_service stockList = new StockList_Impl();
        list = stockList.filter(HIGH,LOW);
        return list;
    }

    @RequestMapping(value = "/SingleIndustryList", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Stock> getSingleIndustryList(@RequestParam("type")String type){
        return hot.getStockList(type);
    }




    @RequestMapping(value = "/IndexTips", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Index> getPartIndecies(){
        Index_service index = new IndexImpl();
        return index.getIndecies();
    }



    public static void main(String[] args) {
        StockListController t = new StockListController();
        ArrayList<HotStock> re = t.IndustryList();
        System.out.println("----------------num = " + re.size());
    }
}
