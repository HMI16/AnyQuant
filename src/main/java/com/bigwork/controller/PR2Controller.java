package com.bigwork.controller;

import com.bigwork.bl.managementServiceImpl.pR_Impl;
import com.bigwork.bl_service.pR_service;
import com.bigwork.model.PR2Value;
import com.bigwork.model.pRValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * Created by asus on 2016/6/13.
 */
@Controller
public class PR2Controller {
    @RequestMapping(value = "/PrGraph", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<PR2Value> getPRGraph(@RequestParam("id")String id, @RequestParam(value="from",required = false)String from,
                                          @RequestParam(value = "to", required = false)String to
    ){
        ArrayList<PR2Value> result = new ArrayList<>();
        pR_service pr = new pR_Impl();
        if(from==null){
            from = "2015-05-10";
        }
        if(to==null){
            to="2016-06-10";
        }
        ArrayList<pRValue> pr1 = pr.getpRGraphValue(id, from, to, 20);
        ArrayList<pRValue> pr2 = pr.getpRGraphValue(id, from, to, 40);
        int size = pr1.size();
        for(int i=0;i<size;i++){
            PR2Value tmp = new PR2Value(pr1.get(i).getdate(), pr1.get(i).getpr(), pr2.get(i).getpr());
            result.add(tmp);
        }
        return result;
    }

    public static void main(String[] args) {
        PR2Controller p = new PR2Controller();
        p.getPRGraph("hs300",null,null);
    }
}
