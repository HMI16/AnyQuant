package com.bigwork.bl.listServiceImpl;

import com.bigwork.controller.TestConnect;
import com.bigwork.model.Grail;
import com.bigwork.bl_service.Grail_service;
import com.bigwork.data.api.dataGetter.DataGetter;
import com.bigwork.data.api.dataManagement.GrailListData_management;
import com.bigwork.data.dataServiceImpl.GrailListData_Impl;
import com.bigwork.data_service.GrailListData_service;
import com.bigwork.sql.SqlImplNullPointerException;
import com.bigwork.sql.SqlLinkException;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

//import data_service.GrailData_service;

public class Grail_Impl implements Grail_service {

    private DataGetter getter = new DataGetter();
    private GrailListData_management grailListDataImpl = new GrailListData_management();
//	private GrailData_service grailData_Impl = new GrailData_Impl();
    private String date1;
    private String date2;

    public ArrayList<Grail> ShowGrailList(){
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
        GrailListData_service grailListData = new GrailListData_Impl();
        ArrayList<Grail> g = new ArrayList<>();
            g = grailListData.GrailList("hs300",date1,date2);

        return g;
    }


    public ArrayList<Grail> setTime(String start, String end){
        GrailListData_service grailListData = new GrailListData_Impl();
        ArrayList<Grail> R = new ArrayList();
        ArrayList<Grail> result = null;
        result = grailListData.GrailList("hs300",start,end);
        R = result;
        return R;
    }

    public void close() {
        // TODO Auto-generated method stub

    }

    public ArrayList<Grail> filter(String F) {
        // TODO Auto-generated method stub
        return null;
    }

	public static void main(String[] args){
		Grail_Impl test = new Grail_Impl();
		ArrayList<Grail> t = test.setTime("2015-01-11", "2015-02-02");
        //ArrayList<Grail> t = test.ShowGrailList();
		System.out.println(t.size());

	}

}
