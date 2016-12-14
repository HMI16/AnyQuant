package com.bigwork.data.api.dataManagement;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.bigwork.model.Grail;

import java.util.ArrayList;

public class GrailListData_management {

    public ArrayList<Grail> GrailList(String data) {
        // TODO Auto-generated method stub
        String Name;

        ArrayList<Grail> result = new ArrayList<Grail>();

        JSONObject jsonObj;
        JSONArray jsonarray;
        jsonObj = JSONObject.fromObject(data);
        jsonarray = JSONArray.fromObject(jsonObj.get("data"));

        jsonObj = JSONObject.fromObject(jsonarray.getString(0));
        Name = (String) jsonObj.get("name");

        jsonarray = JSONArray.fromObject(jsonObj.get("trading_info"));
        Object[] obj = jsonarray.toArray();
        for (int i = 0; i < obj.length; i++) {
            jsonObj = JSONObject.fromObject(jsonarray.getString(i));
            Grail tempVO = new Grail(Name, jsonObj.getString("date"), Double.parseDouble(jsonObj.getString("high")),
                    Double.parseDouble(jsonObj.getString("open")), Double.parseDouble(jsonObj.getString("close")),
                    jsonObj.getString("volume"), Double.parseDouble(jsonObj.getString("adj_price")),
                    Double.parseDouble(jsonObj.getString("low")));
            //System.out.println(jsonObj.getString("volume"));
            result.add(tempVO);
        }
        return result;
    }


}
