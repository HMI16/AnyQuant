package com.bigwork.data.dataHelper;

import com.bigwork.bl.managementServiceImpl.HotIndustry_Impl;
import com.bigwork.model.HotStock;
import com.bigwork.sql.MysqlLink;
import com.bigwork.sql.SqlImplNullPointerException;
import com.bigwork.sql.SqlLinkException;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/13.
 */
public class hotHelper {
    public static void main(String[] args){
        hotHelper hothelper = new hotHelper();
        hothelper.hotrefresh();
    }

    public void hotrefresh(){
        ArrayList<HotStock> hotStocks = new ArrayList<HotStock>();
        HotIndustry_Impl hotIndustry = new HotIndustry_Impl();
        hotStocks =hotIndustry.getHotList(5,"2016-06-03");
        MysqlLink link = new MysqlLink();
        try {
            try {
                link.link("115.28.40.144", "root", "141250185");
            } catch (SqlLinkException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            link.getHotSQLImpl().insertArray(hotStocks);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (SqlImplNullPointerException e) {
            e.printStackTrace();
        }
    }
}
