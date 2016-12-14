package com.bigwork.data.api.refreshImpl;


import com.bigwork.bl.listServiceImpl.SingleStock_Impl;
import com.bigwork.controller.TestConnect;
import com.bigwork.data.api.dataGetter.DataGetter;
import com.bigwork.data.api.dataManagement.StockListData_management;
import com.bigwork.model.Stock;
import com.bigwork.model.TypeGetter;

import java.text.ParseException;
import java.util.ArrayList;

public class StockList_refreshImpl {

    private DataGetter getter = new DataGetter();
//    private StockListData_management stockListDataImpl = new StockListData_management();
    private TypeGetter typeGetter = new TypeGetter();
//	private SingleStockData_management singleDataImpl = new SingleStockData_management();

    public ArrayList<Stock> ShowAll() {
        SingleStock_Impl singlestockImpl = new SingleStock_Impl();
        ArrayList<Stock> result = new ArrayList<Stock>();
        ArrayList<Stock> temp = new ArrayList<Stock>();
        ArrayList<String> IDlist = typeGetter.getAllID();

        for(int i = 0;i<IDlist.size(); i++){
            System.out.println(i);
            temp = singlestockImpl.Show(IDlist.get(i));
            System.out.println(IDlist.get(i));
            result.add(temp.get(temp.size() - 1));
        }
        return result;
    }

}
