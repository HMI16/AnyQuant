package com.bigwork.data.api.dataManagement;

import com.bigwork.model.Stock;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;


public class SingleStockData_management {

    public ArrayList<Stock> SingleStock(String data) {
        // TODO Auto-generated method stub
        String Name;

        ArrayList<Stock> result = new ArrayList<Stock>();


        JSONObject jsonObj;
        JSONArray jsonarray;
        jsonObj = JSONObject.fromObject(data);
        jsonarray = JSONArray.fromObject(jsonObj.get("data"));
        //System.out.println(jsonarray.getString(0));

        jsonObj = JSONObject.fromObject(jsonarray.getString(0));
        Name = (String) jsonObj.get("name");
        //System.out.println(Name);
        //System.out.println(jsonObj.get("trading_info"));

        jsonarray = JSONArray.fromObject(jsonObj.get("trading_info"));
        Object[] obj = jsonarray.toArray();
        //System.out.println(obj.length);
        for (int i = 0; i < obj.length; i++) {
//            System.out.println("a");
            jsonObj = JSONObject.fromObject(jsonarray.getString(i));
//            System.out.println("b");
            Stock tempVO = new Stock(Name, Integer.parseInt(jsonObj.getString("volume")), Double.parseDouble(jsonObj.getString("pb")),
                    Double.parseDouble(jsonObj.getString("high")), Double.parseDouble(jsonObj.getString("pe_ttm")),
                    Double.parseDouble(jsonObj.getString("adj_price")), Double.parseDouble(jsonObj.getString("low")),
                    jsonObj.getString("date"), Double.parseDouble(jsonObj.getString("close")), Double.parseDouble(jsonObj.getString("open")),
                    Double.parseDouble(jsonObj.getString("turnover")));
            //System.out.println(jsonObj.getString("volume"));
            result.add(tempVO);
//            System.out.println("~~~~~");
        }
        return result;
    }

}
