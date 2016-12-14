package com.bigwork.sql;


import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;

import java.sql.SQLException;

/**
 * Created by 15HR-1528SS on 2016/5/6.
 */
public class SqlTest {
    public static void main(String[] args) throws SqlImplNullPointerException, SQLException, SqlLinkException{
        MysqlLink link = new MysqlLink();
        try {
            link.link(FileRead.read("ip"), "root", "141250185");
        } catch (CommunicationsException e) {
            System.out.println("catch the exception");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String r = link.getRefreshHistory_Impl().selectRefreshHistory();
        System.out.println(r);
//        link.getStock_Impl().insertStock(new Stock("141250111", 1200, 0.1, 0.2, 0.3, 0.4, 0.5, "2016-05-09", 0.6, 0.7, 0.8));
        // link.getStock_Impl().insertStock(new Stock("141250169", 2500, 1.1, 1.2, 1.3, 1.4, 1.5, "2016-05-16", 1.6, 1.7, 1.8));
//        Stock stock = link.getStock_Impl().selectStock("141250169", "2016-05-16");
//        System.out.println("succeed!!\nID: " + stock.getId());

        //refresh
//        link.getRefreshHistory_Impl().insertRefreshHistory();
//        System.out.println(link.getRefreshHistory_Impl().selectRefreshHistory());
//        link.close();
    }
}
