package com.bigwork.sql.implservice;

import com.bigwork.model.HotStock;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by 15HR-1528SS on 2016/6/13.
 */
public interface HotSQLImplService {
    void insertHot(HotStock hot) throws SQLException;

    void insertArray(ArrayList<HotStock> array) throws SQLException;

    ArrayList<HotStock> selectArray() throws SQLException;
}
