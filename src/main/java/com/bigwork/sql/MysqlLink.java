package com.bigwork.sql;


import com.bigwork.model.Industry;
import com.bigwork.sql.impl.*;
import com.bigwork.sql.implservice.*;
import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by 15HR-1528SS on 2016/5/5.
 */
public class MysqlLink {
    private Connection con;
    private StockSQLImplService stock_impl;
    private MarketSQLImplService market_impl;
    private RefreshHistorySQLImplService refreshHistory_impl;
    private StockListSQLService stockListSQL_Impl;
    private UserSQLImplService userSQL_Impl;
    private CollSQLImplService collSQL_Impl;
    private IndustrySQLImplService industrySQLImpl;
    private HotSQLImplService hotSQLImpl;
    private IndexSQLImplService indexSQLImpl;

    public void link(String ip, String user, String password) throws SqlLinkException, SQLException, ClassNotFoundException {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://" + ip + "/anyquant";

        try {
            Class.forName(driver);

            con = DriverManager.getConnection(url, user, password);
            if (!con.isClosed()) {
                System.out.println("Succeeded connecting to the Database!");
                stock_impl = new StockSQLImpl(con);
                market_impl = new MarketSqlImpl(con);
                refreshHistory_impl = new RefreshHistorySQLImpl(con);
                stockListSQL_Impl = new StockListSQLImpl(con);
                userSQL_Impl = new UserSqlImpl(con);
                collSQL_Impl = new CollSqlImpl(con);
                industrySQLImpl = new IndustrySQLImpl(con);
                hotSQLImpl = new HotSqlImpl(con);
                indexSQLImpl = new IndexSqlImpl(con);
            }

        } catch (CommunicationsException e) {
            throw e;
        } finally {
            System.out.println("Load data successfully!");
        }
    }

    public StockSQLImplService getStock_Impl() throws SqlImplNullPointerException {
        if (stock_impl != null)
            return stock_impl;
        else
            throw new SqlImplNullPointerException("Stock_impl is null");

    }

    public MarketSQLImplService getMarket_Impl() throws SqlImplNullPointerException {
        if (market_impl != null)
            return market_impl;
        else
            throw new SqlImplNullPointerException("Market_impl is null");

    }

    public RefreshHistorySQLImplService getRefreshHistory_Impl() throws SqlImplNullPointerException {
        if (refreshHistory_impl != null)
            return refreshHistory_impl;
        else
            throw new SqlImplNullPointerException("RefreshHistory_impl is null");

    }

    public StockListSQLService getStockList_Impl() throws SqlImplNullPointerException {
        if (stockListSQL_Impl != null) {
            return stockListSQL_Impl;
        } else
            throw new SqlImplNullPointerException("stockListSQL_Impl is null");
    }

    public UserSQLImplService getUserSQL_Impl() throws SqlImplNullPointerException {
        if (userSQL_Impl != null) {
            return userSQL_Impl;
        } else
            throw new SqlImplNullPointerException("userSQL_Impl is null");
    }

    public CollSQLImplService getCollSQL_Impl() throws SqlImplNullPointerException {
        if (collSQL_Impl != null) {
            return collSQL_Impl;
        } else
            throw new SqlImplNullPointerException("collSQL_Impl is null");
    }

    public IndustrySQLImplService getIndustrySQLImpl() throws SqlImplNullPointerException {
        if (industrySQLImpl != null) {
            return industrySQLImpl;
        } else
            throw new SqlImplNullPointerException("collSQL_Impl is null");
    }

    public HotSQLImplService getHotSQLImpl() throws SqlImplNullPointerException {
        if (hotSQLImpl != null) {
            return hotSQLImpl;
        } else
            throw new SqlImplNullPointerException("hotSQL_Impl is null");
    }

    public void close() throws SQLException {
        con.close();
    }

    public IndexSQLImplService getIndexSQLImpl() {
        return indexSQLImpl;
    }
}
