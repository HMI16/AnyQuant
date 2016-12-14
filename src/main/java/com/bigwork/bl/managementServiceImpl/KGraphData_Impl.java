package com.bigwork.bl.managementServiceImpl;


import com.bigwork.bl.listServiceImpl.SingleStock_Impl;
import com.bigwork.bl_service.KGraphData_service;
import com.bigwork.bl_service.SingleStock_service;
import com.bigwork.model.KGraphValue;
import com.bigwork.model.Stock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class KGraphData_Impl implements KGraphData_service {

    private String ID;
    private String date1;
    private String date2;
    private int day;

    private SingleStock_service single = new SingleStock_Impl();

    public String[] s;

    public KGraphData_Impl(String ID, String date1, String date2) {
        this.ID = ID;
        this.date1 = date1;
        this.date2 = date2;
    }

    public void setDate(String date1, String date2) {
        this.date1 = date1;
        this.date2 = date2;
    }

    public KGraphData_Impl(String ID) {
        this.ID = ID;
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
        if (month < 10) {
            this.date1 = cal.get(Calendar.YEAR) + "-0" + month + "-" + cal.get(Calendar.DATE);
        } else {
            this.date1 = cal.get(Calendar.YEAR) + "-" + month + "-" + cal.get(Calendar.DATE);
        }
        //System.out.println(this.date1);
    }

    /**
     * 获取K线图所需要的数据
     *
     * @throws ParseException
     */
    private String[][] GetKGraphData()  {
        /*ArrayList<StockVO> list = single.Show(ID);
		Date startDate = sdf.parse(list.get(0).getDate());
		Date endDate = sdf.parse(list.get(list.size()-1).getDate());*/

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


        Date startDate = null;
        try {
            startDate = sdf.parse(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date endDate = null;
        try {
            endDate = sdf.parse(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ArrayList<Stock> list = single.setTimeFromDB(ID, date1, date2);
        if(list.size()==0)  return null;
        try {
            startDate = sdf.parse(list.get(0).getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            endDate = sdf.parse(list.get(list.size() - 1).getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(list.get(0).getDate());
        System.out.println(list.get(list.size() - 1).getDate());


        // DAY, OPEN, CLOSE, HIGH, LOW
        String data[][] = new String[list.size() - 1][5];

        //double dat[][] = new double[list.size() - 1][5];
        for (int j = 0; j < 5; j++) {
            if (j == 0) {
                for (int i = 0; i < list.size() - 1; i++) {
                    data[i][j] =list.get(i).getDate();
                }
            } else if (j == 1) {
                for (int i = 0; i < list.size() - 1; i++) {
                    data[i][j] =""+ list.get(i).getOpen();
                }
            } else if (j == 2) {
                for (int i = 0; i < list.size() - 1; i++) {
                    data[i][j] = ""+list.get(i).getClose();
                }
            } else if (j == 3) {
                for (int i = 0; i < list.size() - 1; i++) {
                    data[i][j] = ""+list.get(i).getHigh();
                }
            } else if (j == 4) {
                for (int i = 0; i < list.size() - 1; i++) {
                    data[i][j] =""+ list.get(i).getLow();
                }
            }
        }



        return data;
    }


    public ArrayList<KGraphValue> getKGraphData(){
        ArrayList<KGraphValue> result = new ArrayList<>();
        String[][] str = this.GetKGraphData();
        if(str==null)   return null;
        for(int i=0;i<str.length;i++){
            KGraphValue temp = new KGraphValue();
            temp.setDate(str[i][0]);
            temp.setOpen(Double.parseDouble(str[i][1]));
            temp.setClose(Double.parseDouble(str[i][2]));
            temp.setHigh(Double.parseDouble(str[i][3]));
            temp.setLow(Double.parseDouble(str[i][4]));
            result.add(temp);
        }
        return result;
    }
    public int getDay() {
        // TODO Auto-generated method stub
        return day;
    }

    public String[] getDate() {
        return s;
    }

    public static void main(String args[]){
        KGraphData_service kGraphData = new KGraphData_Impl("sz002644","2016-05-10","2016-05-20");
        ArrayList<KGraphValue> result = kGraphData.getKGraphData();
        for(int i=0;i<result.size();i++){
            System.out.println("_____________");
            System.out.println(result.get(i).getDate());
        }


    }
}
