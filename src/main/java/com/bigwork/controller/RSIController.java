package com.bigwork.controller;

import com.bigwork.bl.managementServiceImpl.RSI_Impl;
import com.bigwork.bl_service.RSI_service;
import com.bigwork.model.RSI3Value;
import com.bigwork.model.RSIValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/1.
 */
@Controller
public class RSIController {

    RSI_service rsi = new RSI_Impl();
    double rsiTip = 0;

    @RequestMapping(value = "/RSIdayGraph", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<RSI3Value> getRSIday(@RequestParam(value = "from",required = false)String from, @RequestParam(value = "to",required = false)String to, @RequestParam("id")String id){
        if(from==null){
            from = "2015-05-10";
        }
        if(to==null){
            to="2016-06-10";
        }

        ArrayList<RSIValue> rsi6 = rsi.getRSIGraphValue(id, from, to, 6);
        ArrayList<RSIValue> rsi12 = rsi.getRSIGraphValue(id, from, to, 12);
        ArrayList<RSIValue> rsi24 = rsi.getRSIGraphValue(id, from, to, 24);
        ArrayList<RSI3Value> result = new ArrayList<>();
        int size = rsi6.size();
        for(int i=0;i<size;i++){
            RSI3Value tmp = new RSI3Value(rsi6.get(i).getdate(), rsi6.get(i).getRsi(),
                    rsi12.get(i).getRsi(), rsi24.get(i).getRsi());
            result.add(tmp);
        }
        double rsi=rsi6.get(rsi6.size()-1).getRsi();
        rsiTip=rsi;
//        if(rsi>=80){
//            rsiTip="RSI指标极强，整个股市行情达到或逼近顶峰，推荐卖出";
//        }else if(50<=rsi&&rsi<=80){
//            rsiTip="RSI指标较强，建议买入，股市行情走高";
//        }else if(20<=rsi&&rsi<50){
//            rsiTip="RSI指标较弱，建议观望，股市行情走低";
//        }else{
//            rsiTip="RSI指标极弱，建议买入，整个股市行情达到或逼近低谷";
//        }
        return result;
    }


    @RequestMapping(value = "/RSITips", method = RequestMethod.GET)
    @ResponseBody
    public double getRSITip(){
        return rsiTip;
    }


    @RequestMapping(value = "/RSI6Graph", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<RSIValue> getRSI6(@RequestParam("from")String from, @RequestParam("to")String to, @RequestParam("id")String id){
        ArrayList<RSIValue> result = rsi.getRSIGraphValue(id, from, to);
        return result;
    }

    @RequestMapping(value = "/RSISinLen", method = RequestMethod.GET)
    @ResponseBody
    public double getRSISingleValue(@RequestParam("id")String id, @RequestParam("from")String from, @RequestParam("to")String to){
        return rsi.getRSI(id, from, to);
    }

    @RequestMapping(value = "/RSISinDay", method = RequestMethod.GET)
    @ResponseBody
    public double getRSISingleValue(@RequestParam("id")String id, @RequestParam("day")int day, @RequestParam("to")String to){
        return rsi.getRSI(id, day, to);
    }

    @RequestMapping(value = "/RSISinDay6", method = RequestMethod.GET)
    @ResponseBody
    public double getRSISingleValue(@RequestParam("id")String id, @RequestParam("to")String to){
        return rsi.getRSI(id, to);
    }


    public static void main(String[] args) {
        RSIController r = new RSIController();

    }
}
