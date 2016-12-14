package com.bigwork.controller;

import org.springframework.stereotype.Controller;

/**
 * Created by asus on 2016/5/3.
 */
@Controller
public class MainController {
   // @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(){
        return "login.html";
    }
}
