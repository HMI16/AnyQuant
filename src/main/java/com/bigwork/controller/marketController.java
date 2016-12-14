package com.bigwork.controller;

import com.bigwork.bl.listServiceImpl.Grail_Impl;
import com.bigwork.bl_service.Grail_service;
import com.bigwork.model.Grail;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/21.
 */
@Controller
public class marketController {

    @RequestMapping(value = "/marketList", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Grail> marketList(){
        ArrayList<Grail> list = new ArrayList<>();
        Grail_service grail = new Grail_Impl();
        list = grail.ShowGrailList();
//        list.add(new Grail("0001", "2015-10-12", 10000, 300,
//                800, "1232", 12, 100));
//        list.add(new Grail("0002", "2015-12-12", 20000, 400,
//                1000, "452", 3, 230));
//        list.add(new Grail("0003", "2015-12-23", 15000, 900,
//                70, "989", 312, 540));
        return list;
    }

    @RequestMapping(value = "/marketListByTime", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Grail> marketListByTime(@RequestParam("startTime")String startTime, @RequestParam("endTime")String endTime){
        ArrayList<Grail> list = new ArrayList<>();
        Grail_service grail = new Grail_Impl();
        list = grail.setTime(startTime,endTime);
//        list.add(new Grail("Hello~~", "2015-12-23", 15000, 900,
//                70, "989", 312, 540));
//        list.add(new Grail("Bye~~", "2015-12-23", 15000, 900,
//                70, "989", 312, 540));
//        list.add(new Grail("See you~~~~~", "2015-12-23", 15000, 900,
//                70, "989", 312, 540));
//        list.add(new Grail("XDDDD", "2015-12-23", 15000, 900,
//                70, "989", 312, 540));
        return list;
    }

    //����KDJֵ
    @RequestMapping(value = "/KDJ", method = RequestMethod.GET)
    @ResponseBody
    public Double[] KDJ(){
        ArrayList<Grail> list = new ArrayList<>();
        Grail_service grail = new Grail_Impl();
        list = grail.ShowGrailList();

        Double[] kdj = {1.0,2.0,3.0};
        return kdj;
    }

    //����һ���������
    @RequestMapping(value = "/market", method = RequestMethod.GET)
    @ResponseBody
    public Grail market(){
        ArrayList<Grail> list = new ArrayList<>();
        Grail_service grail = new Grail_Impl();
        list = grail.ShowGrailList();
        int l = list.size();
        return list.get(l-1);
    }

    //�ǵ���
    @RequestMapping(value = "/applies", method = RequestMethod.GET)
    @ResponseBody
    public Double applies(){
        ArrayList<Grail> list = new ArrayList<>();
        Grail_service grail = new Grail_Impl();
        list = grail.ShowGrailList();

        return 3.14;
    }


    public static void main(String[] args) {
        marketController m  = new marketController();
        System.out.println(m.marketList().size());
    }
}
