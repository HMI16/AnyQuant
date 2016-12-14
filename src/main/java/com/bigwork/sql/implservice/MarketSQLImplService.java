package com.bigwork.sql.implservice;



import com.bigwork.model.Grail;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by 15HR-1528SS on 2016/5/7.
 */
public interface MarketSQLImplService {
    void insertMarket(Grail grail) throws SQLException;

    Grail selectMarket(String market_id, String date) throws SQLException;

    void insertArray(ArrayList<Grail> array) throws SQLException;

    ArrayList<Grail> selectArray(String id, String datefrom, String dateto) throws SQLException;
}
