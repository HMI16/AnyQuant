package com.bigwork.sql.implservice;

import java.sql.SQLException;

/**
 * Created by 15HR-1528SS on 2016/5/7.
 */
public interface RefreshHistorySQLImplService {
    void insertRefreshHistory() throws SQLException;

    String selectRefreshHistory() throws SQLException;
}
