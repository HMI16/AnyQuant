package com.bigwork.bl_service;

import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart.Series;

import java.text.ParseException;
import java.util.Date;

public interface MarketLineGraphData_service {
    /**
     * 获取大盘数据折线图所需要的数据
     */
    public void GetMarketLineGraphData();

    /**
     * 返回振幅，涨跌幅（等？）数据
     */
    public void GetMarketOffset();

    public ObservableList<Series<Date, Number>> getAdjData(String start, String end) throws ParseException;

    public ObservableList<Series<Date, Number>> getVolData(String start, String end) throws ParseException;
}