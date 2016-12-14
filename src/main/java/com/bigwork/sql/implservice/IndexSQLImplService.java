package com.bigwork.sql.implservice;

import com.bigwork.model.Index;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by 15HR-1528SS on 2016/6/17.
 */
public interface IndexSQLImplService {
    void insertIndex(Index index) throws SQLException;

    ArrayList<Index> selectArray() throws SQLException;

    void insetArray(ArrayList<Index> array) throws SQLException;
}
