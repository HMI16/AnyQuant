package com.bigwork.data.dataHelper;

import com.bigwork.data.api.refreshImpl.SingleStock_refreshImpl;
import com.bigwork.data.api.refreshImpl.StockList_refreshImpl;
import com.bigwork.model.Stock;
import com.bigwork.sql.MysqlLink;
import com.bigwork.sql.SqlImplNullPointerException;
import com.bigwork.sql.SqlLinkException;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class Test {

    /*public static void main(String args[]) throws SqlImplNullPointerException, SQLException {
        SingleStock_refreshImpl singlestock_refresh = new SingleStock_refreshImpl();
        StockList_refreshImpl stockList_refresh = new StockList_refreshImpl();
        MysqlLink link;
        String LastTime;
        link = new MysqlLink();
        LastTime = link.getRefreshHistory_Impl().selectRefreshHistory();
        link.getRefreshHistory_Impl().insertRefreshHistory();

        ArrayList<Stock> stocks = new ArrayList<>();
        stocks = stockList_refresh.ShowAll();

        System.out.println(stocks.size());
        String name = "";
        for(int i=0;i<stocks.size();i++){
            link.getStock_Impl().insertArray(singlestock_refresh.Show(stocks.get(i).getId(),LastTime),"");
           // name = name +stocks.get(i).getId()+",";
        }
        System.out.println(name);
    }*/

    public static void main(String args[]) throws ParseException, SQLException, SqlImplNullPointerException, SqlLinkException {
        Refresh ref = new Refresh();
        ref.refresh();
        //System.out.println("0");
    }

}
