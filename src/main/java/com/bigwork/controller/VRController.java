package com.bigwork.controller;

import com.bigwork.bl.managementServiceImpl.VR_Impl;
import com.bigwork.bl_service.VR_service;
import com.bigwork.model.VR3Value;
import com.bigwork.model.VRValue;
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
public class VRController {
    private VR_service vr = new VR_Impl();
    double vrTip=0;

    @RequestMapping(value = "/VRGraph", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<VR3Value> getGraph(@RequestParam("id")String id, @RequestParam(value="from",required = false)String from,
                                        @RequestParam(value = "to", required = false)String to){
        ArrayList<VR3Value> result = new ArrayList<>();
        if(from==null){
            from = "2015-05-10";
        }
        if(to==null){
            to="2016-06-10";
        }
        ArrayList<VRValue> tmp1 = vr.getVRGraphValue(id, from, to, 13);
        ArrayList<VRValue> tmp2 = vr.getVRGraphValue(id, from, to, 26);
        ArrayList<VRValue> tmp3 = vr.getVRGraphValue(id, from, to, 52);
        int size = tmp1.size();
        for(int i=0;i<size;i++){
            VR3Value temp = new VR3Value(tmp1.get(i).getDate(), tmp1.get(i).getVr(),
                    tmp2.get(i).getVr(), tmp3.get(i).getVr());
            result.add(temp);
        }
        double vr = tmp1.get(tmp1.size()-1).getVr();
        vrTip=vr;
//        if(vr<=70){
//            vrTip="VR指标表明股票处于低价区域，推荐买入";
//        }else if(160<=vr&&vr<=450){
//            vrTip="VR指标表明股票处于获利区域，应考虑获利情况，行情走高";
//        }else if(vr>450){
//            vrTip="VR指标表明股票处于警戒区域，股市行情达到或逼近顶峰，推荐卖出";
//        }else{
//            vrTip="VR指标表明股票目前应持续观望";
//        }
        return result;
    }


    @RequestMapping(value = "/VRTip", method = RequestMethod.GET)
    @ResponseBody
    public double getVrTip(){
        return vrTip;
    }







    @RequestMapping(value = "/VRdayGraph", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<VRValue> getVRday(@RequestParam("from")String from, @RequestParam("to")String to, @RequestParam("id")String id, @RequestParam("day")int day){

        ArrayList<VRValue> result = vr.getVRGraphValue(id, from, to, day);
        return result;
    }

    @RequestMapping(value = "/VRGraph26", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<VRValue> getVR26(@RequestParam("from")String from, @RequestParam("to")String to, @RequestParam("id")String id){
        ArrayList<VRValue> result = vr.getVRGraphValue(id, from, to);
        return result;
    }

    @RequestMapping(value = "/VRSinLen", method = RequestMethod.GET)
    @ResponseBody
    public double geVRSingleValue(@RequestParam("id")String id, @RequestParam("from")String from, @RequestParam("to")String to){
        return vr.getVR(id, from, to);
    }

    @RequestMapping(value = "/VRSinDay", method = RequestMethod.GET)
    @ResponseBody
    public double getVRSingleValue(@RequestParam("id")String id, @RequestParam("day")int day, @RequestParam("to")String to){
        return vr.getVR(id, day, to);
    }

    @RequestMapping(value = "/VRSinDay26", method = RequestMethod.GET)
    @ResponseBody
    public double getVRSingleValue(@RequestParam("id")String id, @RequestParam("to")String to) {
        return vr.getVR(id, to);
    }

    public static void main(String[] args) {
        VRController v = new VRController();
        v.getGraph("hs300",null,null);
    }
}
