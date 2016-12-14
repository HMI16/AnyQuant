package com.bigwork.bl_service;


import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.text.ParseException;
import java.util.Date;

public interface SingleLineGraphData_service {
    /**
     * 获取单只股票折线图所需要的数据
     * <p>
     * <p>
     * （成交金额＝成交价格＊成交量？……于是还是用那个带Name的API了因为我觉得这个应该算不出来＝＝）
     */
    public void GetSingleLineGraphData();

    /**
     * 返回振幅，涨跌幅（等？）数据
     */
    public void GetStockOffset();


    public ObservableList<XYChart.Series<Date, Number>> getVolData(String start, String end)throws ParseException;

    public ObservableList<XYChart.Series<Date, Number>> getDealData(String start, String end)throws ParseException;

    public ObservableList<XYChart.Series<Date, Number>> getVolData() throws ParseException;

    public ObservableList<XYChart.Series<Date, Number>> getDealData() throws ParseException;



}
