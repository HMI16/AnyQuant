package com.bigwork.controller;

import com.bigwork.bl.managementServiceImpl.KDJ_Impl;
import com.bigwork.bl_service.KDJ_service;
import com.bigwork.model.KDJValue;
import org.hibernate.mapping.Array;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * Created by asus on 2016/5/31.
 */
@Controller
public class KDJController {

    private KDJ_service kdj = new KDJ_Impl();

    @RequestMapping(value = "/KDJGraph", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<KDJValue> getKDJGraph(){
        ArrayList<KDJValue> re = new ArrayList<>();
        re.add(new KDJValue("2015-01-01", 100.2, 123.2, 154.3));
        re.add(new KDJValue("2015-01-02", 101.2, 113.2, 153.3));
        re.add(new KDJValue("2015-01-03", 102.2, 125.2, 152.3));
        re.add(new KDJValue("2015-01-04", 103.2, 127.2, 151.3));
        re.add(new KDJValue("2015-01-05", 104.2, 154.2, 150.3));
        return re;

        //return kdj.getKDJGraph("sz002644", "2015-01-15", "2015-12-29", 9);
    }
}
