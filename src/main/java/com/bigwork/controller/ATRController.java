package com.bigwork.controller;

import com.bigwork.bl.managementServiceImpl.ATR_Impl;
import com.bigwork.bl_service.ATR_service;
import com.bigwork.model.ATR3Value;
import com.bigwork.model.ATRValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by asus on 2016/5/31.
 */
@Controller
public class ATRController {

    private ATR_service atr = new ATR_Impl();

    @RequestMapping(value = "/ATRGraph", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<ATR3Value> getATRGraph(@RequestParam("id")String id, @RequestParam(value = "from", required = false)String from, @RequestParam(value = "to", required = false) String to){
        if(from==null){
            from = "2015-05-10";
        }
        if(to==null){
            to="2016-06-10";
        }
        ArrayList<ATR3Value> result = new ArrayList<>();
        ArrayList<ATRValue> atr1 = atr.getATRGraphValue(id, from, to , 6);
        ArrayList<ATRValue> atr2 = atr.getATRGraphValue(id, from, to , 26);
        ArrayList<ATRValue> atr3 = atr.getATRGraphValue(id, from, to , 65);
//        System.out.println(atr1.size() + "  " + atr1.get(0).getDate() + "   " + atr1.get(atr1.size()-1).getDate());
//        System.out.println(atr2.size() + "  " + atr2.get(0).getDate() + "   " + atr2.get(atr2.size()-1).getDate());
//        System.out.println(atr3.size() + "  " + atr3.get(0).getDate() + "   " + atr3.get(atr3.size()-1).getDate());
        int size = atr1.size();
        for(int i = 0; i< size; i++){
            ATR3Value tmp = new ATR3Value(atr1.get(i).getDate(), atr1.get(i).getATR(), atr2.get(i).getATR(), atr3.get(i).getATR());
            result.add(tmp);
        }
        return result;
    }

    @RequestMapping(value = "ATRSinDay", method = RequestMethod.GET)
    @ResponseBody
    public double getSingleATR(@RequestParam("id")String id, @RequestParam("day")int day, @RequestParam("to") String to){
       try{
           double result = atr.GetATR(id, day, to);
           return result;
       }catch (ParseException e){
           return 0;
       }

    }

//    @RequestMapping(value = "ATRGraph", method = RequestMethod.GET)
//    @ResponseBody
//    public ArrayList<ATRValue> getSingleATR(/*@RequestParam("id")String id*/){
//        //@RequestParam("id")String id, @RequestParam("from")String from, @RequestParam("to") String to, @RequestParam("day")int day){
//        ArrayList<ATRValue> result = atr.getATRGraphValue("sz002644", "2015-10-11", "2016-05-01", 6);
//        return result;
//    }

    public static void main(String[] args) {
        ATRController atr = new ATRController();
        atr.getATRGraph("sh300", "2015-05-10", "2016-06-01");
    }
}
