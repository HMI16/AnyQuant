package com.bigwork.data.dataServiceImpl;

import com.bigwork.controller.TestConnect;
import com.bigwork.data.api.dataGetter.DataGetter;
import com.bigwork.data.api.dataManagement.GrailListData_management;
import com.bigwork.data_service.GrailListData_service;
import com.bigwork.model.Grail;
import com.bigwork.sql.MysqlLink;
import com.bigwork.sql.SqlLinkException;

import java.sql.SQLException;
import java.util.ArrayList;

public class GrailListData_Impl implements GrailListData_service {

    private DataGetter getter = new DataGetter();
    private GrailListData_management grailListDataImpl = new GrailListData_management();

    @Override
    public ArrayList<Grail> GrailList(String market_id,String datefrom, String dateTo){
        // TODO Auto-generated method stub
        boolean dbFine = true;
        ArrayList<Grail> result = new ArrayList<Grail>();
        MysqlLink link = new MysqlLink();
        try {
            link.link("115.28.40.144", "root", "141250185");
        } catch (SqlLinkException e) {
          //  e.printStackTrace();
        } catch (SQLException e) {
            // e.printStackTrace();
        } catch (ClassNotFoundException e) {
          //  e.printStackTrace();
        }
        try {
            result = link.getMarket_Impl().selectArray(market_id, datefrom, dateTo);
        }catch (com.mysql.jdbc.exceptions.jdbc4.CommunicationsException e) {
            System.out.println("catch the exception");
            //e.printStackTrace();
            dbFine = false;
        } catch (Exception e) {
            //e.printStackTrace();
            dbFine = false;
        }
        if(!dbFine){
            result = this.SetTimeFromAPI(datefrom, dateTo);
        }
        return result;
    }

    private ArrayList<Grail> SetTimeFromAPI(String start, String end) {
        ArrayList<Grail> result = new ArrayList<Grail>();
        System.out.println("Out");
        String url = "http://121.41.106.89:8010/api/benchmark/hs300?start="
                + start + "&end=" + end;
        if (TestConnect.getConnectionState()) {
            String temp = getter.getData(url);
            result = grailListDataImpl.GrailList(temp);
            return result;
        }
        return null;
    }
}
