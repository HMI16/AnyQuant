package com.bigwork.data.dataServiceImpl;


import com.bigwork.controller.TestConnect;
import com.bigwork.data.api.dataGetter.DataGetter;
import com.bigwork.data.api.dataManagement.SingleStockData_management;
import com.bigwork.data.dataHelper.FileHelper;
import com.bigwork.data.dataHelper.SingleStockFileFinder;
import com.bigwork.data_service.SingleStockData_service;
import com.bigwork.model.Stock;
import com.bigwork.model.TypeGetter;
import com.bigwork.sql.MysqlLink;
import com.bigwork.sql.SqlLinkException;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class SingleStockData_Impl implements SingleStockData_service {

    private SingleStockFileFinder finder = new SingleStockFileFinder();
    private DataGetter getter = new DataGetter();
    private SingleStockData_management singleDataImpl = new SingleStockData_management();
    private TypeGetter typeGetter = new TypeGetter();

    /*public ArrayList<Stock> SingleStock(String id,String datefrom, String dateTo) {
        // TODO Auto-generated method stub
        ArrayList<Stock> result = new ArrayList<Stock>();
        boolean dbFine = true;
        MysqlLink link = new MysqlLink();
        try {
            link.link("115.28.40.144", "root", "141250185");
        } catch (SqlLinkException e) {
          //  e.printStackTrace();
        } catch (SQLException e) {
          //  e.printStackTrace();
        } catch (ClassNotFoundException e) {
          //  e.printStackTrace();
        }
        try {
            result = link.getStock_Impl().selectArray(id, datefrom, dateTo,typeGetter.getABC(id));
        } catch (com.mysql.jdbc.exceptions.jdbc4.CommunicationsException e) {
            System.out.println("catch the exception");
            //e.printStackTrace();
            dbFine = false;
        } catch (Exception e) {
            //e.printStackTrace();
            dbFine = false;
        }

        if(!dbFine){
            result = this.SetTimeFromAPI(id, datefrom, dateTo);
        }
        return result;
    }*/

    public ArrayList<Stock> SingleStock(String id,String datefrom, String dateTo) {
        // TODO Auto-generated method stub
        System.out.println("-------");
        ArrayList<Stock> result = new ArrayList<Stock>();
        ArrayList<String> re = new ArrayList<String>();
        System.out.println(finder.getFilePath(id));
        System.out.println("-------");
        FileHelper helper = new FileHelper(finder.getFilePath(id));
        re = helper.read();

        for(int i = re.size()-31; i < re.size();i++){
            result.add(new Stock(re.get(i)));
        }
        /*for (String string : re) {
            result.add(new Stock(string));
        }*/
        return result;
    }

    private ArrayList<Stock> SetTimeFromAPI(String id, String startTime, String endTime) {
        ArrayList<Stock> result = new ArrayList<Stock>();
        String realEnd = "";
        System.out.println("Out");
        //���ؽ���������һ��
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date end = sdf.parse(endTime);
            Calendar cal = Calendar.getInstance();
            cal.setTime(end);
            cal.add(Calendar.DATE, 1);
            realEnd = sdf.format(cal.getTime());
            System.out.println(realEnd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String url = "http://121.41.106.89:8010/api/stock/"
                + id + "/?start=" + startTime + "&end="
                + realEnd;
        if (TestConnect.getConnectionState()) {
            String temp = getter.getData(url);

            result = singleDataImpl.SingleStock(temp);
            return result;
        }
        return null;
    }
}