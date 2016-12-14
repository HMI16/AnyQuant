package com.bigwork.controller;

import com.bigwork.model.LoginBean;
import com.bigwork.model.Stock;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.util.Map;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by asus on 2016/5/4.
 */
@Controller
public class LoginServlet{
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView start(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("../pages/list.html");
        return modelAndView;
    }

    /*public String index(){
        return "login.html";
    }*/

    public String getString(String str){
        if(str==null){
            str="";
        }else{
            try{
                byte[] b = str.getBytes("ISO-8859-1");
                str = new String(b);
            }catch (Exception e){
                System.out.println(e.toString());
            }
        }
        return str;
    }


    @RequestMapping(value = "/doGe", method = RequestMethod.GET)
    public ModelAndView  doGe(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        name = getString(name);
        password = getString(password);

        LoginBean login = new LoginBean();
        boolean result = false;
        result = login.checkUser(name, password);
        ModelAndView modelAndView = new ModelAndView();
        if(result){
            modelAndView.setViewName("/WEB-INF/static/js/index.jsp");
            //return  "success.jsp";
            return modelAndView;
            //request.getRequestDispatcher("success.jsp").forward(request, response);
        }else{
            System.out.println("!!!!!!!!");
            modelAndView.setViewName("login.html");
            //return "forward:login.html";
            return modelAndView;
            //request.getRequestDispatcher("login.html").forward(request, response);
        }
    }

    @RequestMapping("/prarm")
    @ResponseBody
    public  ModelAndView Param(@RequestParam("name")String name, @RequestParam("password")String password){
        System.out.println(name);
        System.out.println(password);
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("!!!!!!!!");
        modelAndView.setViewName("login.html");
        return modelAndView;
    }


    @RequestMapping("/test")
    @ResponseBody
    public  String test(){
       // ModelAndView modelAndView = new ModelAndView();
        ArrayList<Stock>  list =  new ArrayList<>();
        Stock stock1 = new Stock("141250111", 1200, 0.1, 0.2, 0.3, 0.4, 0.5, "2016-05-09", 0.6, 0.7, 0.8);
        Stock stock2 = new Stock("141250169", 2500, 1.1, 1.2, 1.3, 1.4, 1.5, "2016-05-16", 1.6, 1.7, 1.8);
        list.add(stock1);
        list.add(stock2);
        String str = "30";
        //JSONArray jsonArray2 = JSONArray.fromObject( list );
        // modelAndView.addObject("list",list);
        //       modelAndView.setViewName("login.html");
//        return modelAndView;
        return str;
    }

    @RequestMapping("getdatabyresponsebody.json")
    public @ResponseBody Map<String, Object> getAjaxDataByResponseBody() {
        System.out.println("ͨ��ע��@ResponseBody����JSON����");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", true);
        map.put("message", "Successfully returning the data.");
        return map;
    }
    /*public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        name = getString(name);
        password = getString(password);

        LoginBean login = new LoginBean();
        boolean result = false;
        result = login.checkUser(name, password);
        if(result){
            request.getRequestDispatcher("success.jsp").forward(request, response);
        }else{
            System.out.println("!!!!!!!!");
            request.getRequestDispatcher("login.html").forward(request, response);
        }
    }*/
}
