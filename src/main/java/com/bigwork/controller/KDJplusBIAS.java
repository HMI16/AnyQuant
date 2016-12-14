package com.bigwork.controller;

import com.bigwork.bl.managementServiceImpl.BIAS_Impl;
import com.bigwork.bl.managementServiceImpl.KDJ_Impl;
import com.bigwork.bl_service.BIAS_service;
import com.bigwork.bl_service.KDJ_service;
import com.bigwork.model.BIASValue;
import com.bigwork.model.KDJPlusBIASValue;
import com.bigwork.model.KDJValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2016/6/11.
 */
@Controller
public class KDJplusBIAS {

    private KDJ_service kdj_service = new KDJ_Impl();
    private BIAS_service bias_service = new BIAS_Impl();

    @RequestMapping(value = "/kdjPlusBias", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<KDJPlusBIASValue> getKDJPlusBIAS(@RequestParam("id")String id, @RequestParam(value="from",required = false)String from,
                                                      @RequestParam(value = "to", required = false)String to){
        ArrayList<KDJPlusBIASValue> result = new ArrayList<>();
        if(from==null){
            from = "2015-05-10";
        }
        if(to==null){
            to="2016-06-10";
        }
        ArrayList<KDJValue> kdj = kdj_service.getKDJGraph(id, from, to, 9);
        ArrayList<BIASValue> bias = bias_service.getBIASGraph(id, from, to, 9);
        int m = kdj.size();
        int n = bias.size();
        int count = 0;
        if(m>n){
            for(int i=0;i<n;i++){
                if(bias.get(i).getDate().equals(kdj.get(i+m-n).getDate())){
                    count++;
                }
                KDJPlusBIASValue tmp = new KDJPlusBIASValue(bias.get(i).getDate(), kdj.get(i+m-n).getK(),
                        kdj.get(i+m-n).getD(), kdj.get(i+m-n).getJ(), bias.get(i).getBIASValue());
                result.add(tmp);
            }
//            System.out.println("kdj.size() = " + m);
//            System.out.println("bias.size() = " + n);
//            System.out.println("count = " + count);
        }else{
            for(int i=0;i<m;i++){
                KDJPlusBIASValue tmp = new KDJPlusBIASValue(kdj.get(i).getDate(), kdj.get(i).getK(),
                        kdj.get(i).getD(), kdj.get(i).getJ(), bias.get(i+n-m).getBIASValue());
                result.add(tmp);
            }
//            System.out.println("kdj.size() = " + m);
//            System.out.println("bias.size() = " + n);
//            System.out.println("count = " + count);
        }

        return result;
    }

//    public static void main(String[] args) {
//        KDJplusBIAS t = new KDJplusBIAS();
//        t.getKDJPlusBIAS("sz002644");
//    }
}
