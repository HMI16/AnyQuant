package com.bigwork.data.api.refreshImpl;

import com.bigwork.controller.TestConnect;
import com.bigwork.data.api.dataGetter.DataGetter;
import com.bigwork.data.api.dataManagement.GrailListData_management;
import com.bigwork.model.Grail;

import java.util.ArrayList;
import java.util.Calendar;

public class Grail_refreshImpl {
    private String date;
    private DataGetter getter = new DataGetter();
    private GrailListData_management grailListDataImpl = new GrailListData_management();
//	private GrailData_Impl grailData_Impl = new GrailData_Impl();

    public ArrayList<Grail> ShowGrailList(String  lasttime) {
        ArrayList<Grail> result = new ArrayList<Grail>();
        //System.out.println("1");
        //String url = "http://121.41.106.89:8010/api/benchmark/hs300";
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DATE) + 1;
        if (month < 10) {
            if(day < 10){
                this.date = cal.get(Calendar.YEAR) + "-0" + month + "-0" + cal.get(Calendar.DATE);
            }else{
                this.date = cal.get(Calendar.YEAR) + "-0" + month + "-" + cal.get(Calendar.DATE);
            }

        } else {
            if(day < 10){
                this.date = cal.get(Calendar.YEAR) + "-" + month + "-0" + cal.get(Calendar.DATE);
            }else{
                this.date = cal.get(Calendar.YEAR) + "-" + month + "-" + cal.get(Calendar.DATE);
            }

        }
        System.out.println(this.date);
        String url = "http://121.41.106.89:8010/api/benchmark/hs300?start="+lasttime+"&end=" + this.date;
        if (TestConnect.getConnectionState()) {
            String temp = getter.getData(url);
            result = grailListDataImpl.GrailList(temp);

            return result;
        }
        return null;
    }
}

