package com.bigwork.bl.managementServiceImpl;

import com.bigwork.bl.listServiceImpl.Grail_Impl;
import com.bigwork.bl_service.Grail_service;
import com.bigwork.bl_service.MarketLineGraphData_service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import com.bigwork.model.Grail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MarketLineGraphData_Impl implements MarketLineGraphData_service {

    private String date1;
    private String date2;

    private Grail_service grail = new Grail_Impl();


    public MarketLineGraphData_Impl(String date1, String date2) {
        this.date1 = date1;
        this.date2 = date2;
    }

    public MarketLineGraphData_Impl() {

    }

    /**
     * 获取大盘数据折线图所需要的数据
     */
    public void GetMarketLineGraphData() {

    }


    public ObservableList<Series<Date, Number>> getAdjData(String start, String end) throws ParseException {
        ObservableList<Series<Date, Number>> series = FXCollections.observableArrayList();

        ObservableList<XYChart.Data<Date, Number>> data = FXCollections.observableArrayList();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        ArrayList<Grail> list = grail.setTime(start, end);
        for (Grail item : list) {
            try {
                System.out.println(sdf.parse(item.getDate()) + "  " + (Number) item.getAdj_price());
                data.add(new XYChart.Data<Date, Number>(sdf.parse(item.getDate()), (Number) item.getAdj_price()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        series.add(new Series<>("后复权价", data));
        return series;
    }

    public ObservableList<Series<Date, Number>> getVolData(String start, String end) throws ParseException {
        ObservableList<Series<Date, Number>> series = FXCollections.observableArrayList();

        ObservableList<XYChart.Data<Date, Number>> data = FXCollections.observableArrayList();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        ArrayList<Grail> list = grail.setTime(start, end);
        for (Grail item : list) {
            try {
                System.out.println(sdf.parse(item.getDate()) + "  " + (Number) Long.parseLong(item.getVolume()));
                data.add(new XYChart.Data<Date, Number>(sdf.parse(item.getDate()), (Number) Long.parseLong(item.getVolume())));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        series.add(new Series<>("成交量", data));
        return series;
    }


    /**
     * 返回振幅，涨跌幅（等？）数据
     */
    public void GetMarketOffset() {

    }
}