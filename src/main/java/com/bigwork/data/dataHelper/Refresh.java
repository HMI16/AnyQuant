package com.bigwork.data.dataHelper;


import com.bigwork.bl.managementServiceImpl.Change_Impl;
import com.bigwork.bl.managementServiceImpl.Fluctuation_Impl;
import com.bigwork.bl_service.Change_service;
import com.bigwork.bl_service.Fluctuation_service;
import com.bigwork.data.api.refreshImpl.Grail_refreshImpl;
import com.bigwork.data.api.refreshImpl.SingleStock_refreshImpl;
import com.bigwork.data.api.refreshImpl.StockList_refreshImpl;
import com.bigwork.model.Index;
import com.bigwork.model.TypeGetter;
import com.bigwork.sql.MysqlLink;
import com.bigwork.sql.SqlImplNullPointerException;
import com.bigwork.sql.SqlLinkException;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.function.BooleanSupplier;

public class Refresh {

    private StockList_refreshImpl stocklist_refresh = new StockList_refreshImpl();
    private SingleStock_refreshImpl singlestock_refresh = new SingleStock_refreshImpl();
    private Grail_refreshImpl grail_refresh = new Grail_refreshImpl();
    private MysqlLink link;
    private String LastTime;
    private TypeGetter typeGetter = new TypeGetter();
    private Fluctuation_service flu_impl = new Fluctuation_Impl();
    private Change_service change_impl = new Change_Impl();

    /**
     * 更新所有本地文件
     *
     * @return
     * @throws ParseException
     */
    public boolean refresh() throws ParseException, SqlImplNullPointerException, SQLException, SqlLinkException {
        //this.GrailList();
        link = new MysqlLink();
        ArrayList<String> IDlist = new ArrayList<>();
        String[] type = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "q", "r"};
        try {
            link.link("115.28.40.144", "root", "141250185");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        LastTime = link.getRefreshHistory_Impl().selectRefreshHistory();
        link.getRefreshHistory_Impl().insertRefreshHistory();

         //LastTime = "2016-06-14";
        for (int i = 0; i < type.length; i++) {
            IDlist = typeGetter.getStringwithID(type[i]);
            for (int j = 0; j < IDlist.size(); j++) {
                this.SingleStock(IDlist.get(j), type[i]);
            }
        }
        hotHelper hothelper = new hotHelper();
        hothelper.hotrefresh();
        this.Index();

        //this.SingleStock("sz300148","r");
        //return true;
         return this.GrailList() && this.StockList();
    }

    /*
                    && this.SingleStockEight()
                    && this.SingleStockFive() && this.SingleStockFour() && this.SingleStockNine()
                    && this.SingleStockOne() && this.SingleStockSeven() && this.SingleStockSix()
                    && this.SingleStockThree() && this.SingleStockTwo()
     */
    private boolean GrailList() throws SqlImplNullPointerException, SQLException {
        System.out.println("-1");
        link.getMarket_Impl().insertArray(grail_refresh.ShowGrailList(LastTime));
        return true;
    }

    private Boolean StockList() throws ParseException, SqlImplNullPointerException, SQLException {
        System.out.println("0");
        link.getStockList_Impl().insertArray(stocklist_refresh.ShowAll());
        return true;
    }

    private Boolean SingleStock(String id, String type) throws SqlImplNullPointerException, SQLException {
        link.getStock_Impl().insertArray(singlestock_refresh.Show(id, LastTime), type);
        return true;
    }

//    private  Boolean SingleStock() throws SqlImplNullPointerException, SQLException{
//        ArrayList<Stock> stocks = new ArrayList<>();
//        stocks = stocklist_refresh.ShowAll();
//        for(int i=0;i<stocks.size();i++){
//            link.getStock_Impl().insertArray(singlestock_refresh.Show(stocks.get(i).getId(),LastTime),"");
//        }
//        return true;
//    }

    private Boolean Index() throws SQLException {
        ArrayList<Index> array = new ArrayList<>();
//System.out.println(LastTime);
        for (String id : typeGetter.getAllID()) {
            double flu = flu_impl.getFluctuation(id, LastTime.substring(0, 4));
            double change = change_impl.getChange(id, LastTime);
            array.add(new Index(id, flu, change));

            System.out.println(id + " flu : " + flu + " change : " + change);
        }

        link.getIndexSQLImpl().insetArray(array);

        return true;
    }
}
