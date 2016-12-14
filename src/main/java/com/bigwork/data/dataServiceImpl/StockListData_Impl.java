package com.bigwork.data.dataServiceImpl;


import com.bigwork.controller.TestConnect;
import com.bigwork.data.api.dataGetter.DataGetter;
import com.bigwork.data.api.dataManagement.StockListData_management;
import com.bigwork.data_service.StockListData_service;
import com.bigwork.model.Stock;
import com.bigwork.sql.MysqlLink;
import com.bigwork.sql.SqlLinkException;

import java.sql.SQLException;
import java.util.ArrayList;

public class StockListData_Impl implements StockListData_service {
    private DataGetter getter = new DataGetter();
    private StockListData_management stockListDataImpl = new StockListData_management();


    public ArrayList<Stock> StockList(){
        // TODO Auto-generated method stub
        ArrayList<Stock> result = new ArrayList<Stock>();
        boolean dbFine = true;
        MysqlLink link = new MysqlLink();
        try {
            link.link("115.28.40.144", "root", "141250185");
        } catch (SqlLinkException e) {
           // e.printStackTrace();
        } catch (SQLException e) {
           // e.printStackTrace();
        } catch (ClassNotFoundException e) {
            //e.printStackTrace();
        }
        try {
            result = link.getStockList_Impl().selectArray();
        }catch (com.mysql.jdbc.exceptions.jdbc4.CommunicationsException e) {
            System.out.println("catch the exception");
            //e.printStackTrace();
            dbFine = false;
        } catch (Exception e) {
            //e.printStackTrace();
            dbFine = false;
        }

        if(!dbFine){
            System.out.println("not fine");
            result = this.SetTimeFromAPI();
        }
        return result;
    }

    private ArrayList<Stock> SetTimeFromAPI(){
        ArrayList<Stock> result = new ArrayList<Stock>();
        String url = "http://121.41.106.89:8010/api/stocks/";
        System.out.println("before if");
        if (TestConnect.getConnectionState()) {
            System.out.println("aft if");
            String temp = getter.getData(url);
            System.out.println("middle");
            result = stockListDataImpl.StockList(temp);
            System.out.println("end if");
        }
        return result;
    }


}
