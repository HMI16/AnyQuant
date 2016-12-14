package com.bigwork.bl.listServiceImpl;

import com.bigwork.bl_service.Grail_service;
import com.bigwork.bl_service.SingleStock_service;
import com.bigwork.controller.TestConnect;
import com.bigwork.data.api.dataGetter.DataGetter;
import com.bigwork.data.api.dataManagement.SingleStockData_management;
import com.bigwork.data.dataServiceImpl.SingleStockData_Impl;
import com.bigwork.data_service.SingleStockData_service;
import com.bigwork.model.Grail;
import com.bigwork.model.Stock;
import com.bigwork.sql.SqlImplNullPointerException;
import com.bigwork.sql.SqlLinkException;
import com.mysql.jdbc.CommunicationsException;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class SingleStock_Impl implements SingleStock_service {

    private DataGetter getter = new DataGetter();
    private SingleStockData_management singleDataImpl = new SingleStockData_management();
    private String date1;
    private String date2;

    public ArrayList<Stock> Show(String id)  {
    	Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        if (month < 10) {
            this.date2 = cal.get(Calendar.YEAR) + "-0" + month + "-" + cal.get(Calendar.DATE);
        } else {
            this.date2 = cal.get(Calendar.YEAR) + "-" + month + "-" + cal.get(Calendar.DATE);
        }
        //System.out.println(this.date2);
        cal.add(Calendar.MONTH, -1);
        month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DATE) + 1;
        if (month < 10) {
            if(day<10){
                this.date1 = cal.get(Calendar.YEAR) + "-0" + month + "-0" + cal.get(Calendar.DATE);
            }else{
                this.date1 = cal.get(Calendar.YEAR) + "-0" + month + "-" + cal.get(Calendar.DATE);
            }

        } else {
            if(day<10){
                this.date1 = cal.get(Calendar.YEAR) + "-" + month + "-0" + cal.get(Calendar.DATE);
            }else{
                this.date1 = cal.get(Calendar.YEAR) + "-" + month + "-" + cal.get(Calendar.DATE);
            }

        }
        ArrayList<Stock> s = new ArrayList<>();
       // System.out.println("date1:"+date1);
        s = this.setTimeFromDB(id, date1, date2);
        /* SingleStockData_service singleStockData = new SingleStockData_Impl();
        return singleStockData.SingleStock(id);*/
        return  s;
    }

    public void close() {
        // TODO Auto-generated method stub
    }
    //界面没有实现这个功能？


    public ArrayList<Stock> setTimeFromDB(String id, String startTime, String endTime){
        SingleStockData_service singleStockData = new SingleStockData_Impl();
        if(id.length()==5){
            Grail_service grail = new Grail_Impl();
            ArrayList<Grail> grails = grail.setTime(startTime, endTime);
            int size = grails.size();
            ArrayList<Stock> stocks = new ArrayList<>();
            for(Grail tmp : grails){
                Stock stock = new Stock(id, -1, -1, tmp.getHigh(), -1, tmp.getAdj_price(), tmp.getLow(),
                        "", tmp.getClose(), tmp.getOpen(), -1);
                stocks.add(stock);
            }
            return stocks;
        }else {
            ArrayList<Stock> R = new ArrayList();
            ArrayList<Stock> result = null;
            result = singleStockData.SingleStock(id, startTime, endTime);
            R = result;

            return R;
        }
    }

	public static void main(String[] args){
		SingleStock_Impl test = new SingleStock_Impl();
		ArrayList<Stock> res = test.setTimeFromDB("sh999999", "2016-03-15", "2016-03-23");
        System.out.println(res);
        System.out.println("size = "+res.size());
		for(Stock vo : res){
		//	System.out.println(vo.getClose());
		}
        if(res.toString() == "[]"){
            System.out.println("the list is []");
        }
        if(res == null){
            System.out.println("the list is null");
        }
	}

}