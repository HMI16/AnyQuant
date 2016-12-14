package com.bigwork.data_service;

import com.bigwork.model.Grail;
import com.bigwork.sql.SqlImplNullPointerException;
import com.bigwork.sql.SqlLinkException;

import java.sql.SQLException;
import java.util.ArrayList;

public interface GrailListData_service {

    /**
     * 处理大盘一段时间内原始数据，以ArrayList返回给bl
     *
     * @return
     */
    public ArrayList<Grail> GrailList(String market_id,String datefrom, String dateTo);


}
