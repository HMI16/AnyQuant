package com.bigwork.model;

/**
 * Created by asus on 2016/5/7.
 */
import com.bigwork.data.dataHelper.GetName;
import com.bigwork.model.TypeGetter;

public class Stock {

    final private String split = ":%:%:";

    private String id;
    private int volume;
    private double pd;
    private double high;
    private double pe_ttm;
    private double adj_price;
    private double low;
    private String date;
    private double close;
    private double open;
    private double turnover;

    private String name;
    private Type type;
    private double dealsum;

    /*newly added 16/04/08*/
    private double hotValue;

    private TypeGetter typegeter = new TypeGetter();

	 	/*open: 开盘价
        high: 最高价
		low: 最低价
		close: 收盘价
		adj_price: 后复权价
		volume: 成交量
		turnover: 换手率
		pe: 市盈率
		pd: 市净率!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!简直坑

		name：名称
		type：行业
		dealsum：成交金额

		hotValue：热门指数*/

    public Stock() {

    }

    public Stock(double adj_price, int volume, double turnover, double pe, double pb) {
        this.adj_price = adj_price;
        this.volume = volume;
        this.turnover = turnover;
        this.pe_ttm = pe;
        this.pd = pb;
        this.id = "";
        this.volume = -1;
        this.high = -1;
        this.low = -1;
        this.date = "";
        this.close = -1;
        this.open = -1;
    }

    public Stock(String str) {
        super();
        String[] strs = str.split(this.split);
        this.id = strs[0];
        this.volume = Integer.parseInt(strs[1]);
        this.pd = Double.parseDouble(strs[2]);
        this.high = Double.parseDouble(strs[3]);
        this.pe_ttm = Double.parseDouble(strs[4]);
        this.adj_price = Double.parseDouble(strs[5]);
        this.low = Double.parseDouble(strs[6]);
        this.date = strs[7];
        this.close = Double.parseDouble(strs[8]);
        this.open = Double.parseDouble(strs[9]);
        this.turnover = Double.parseDouble(strs[10]);

        // this.name = wrapper.getName(id);
        this.type = typegeter.getType(id);
        this.dealsum = this.getAdj_price() * this.getVolume();

        this.hotValue = 0.0;    //initialized to 0, gets value through HotIndustryData
    }

   /* public String toString() {
        return this.id + this.split + this.volume + this.split + this.pd
                + this.split + this.high + this.split + this.pe_ttm + this.split
                + this.adj_price + this.split + this.low + this.split + this.date
                + this.split + this.close + this.split + this.open + this.split
                + this.turnover + this.split + this.type.toString()
                + this.split + this.dealsum + this.split + this.hotValue;
        //added the last 4 members   by zjx14
    }*/

    public String getId() {
        return id;
    }

    public Stock(String id, int volume, double pd, double high,
                   double pe_ttm, double adj_price, double low, String date,
                   double close, double open, double turnover) {
        super();
        this.id = id;
        this.volume = volume;
        this.pd = pd;
        this.high = high;
        this.pe_ttm = pe_ttm;
        this.adj_price = adj_price;
        this.low = low;
        this.date = date;
        this.close = close;
        this.open = open;
        this.turnover = turnover;

        // this.name = wrapper.getName(id);
        this.type = typegeter.getType(id);
        this.dealsum = this.getAdj_price() * this.getVolume();

        this.hotValue = 0.0;
    }

    /*used in getPieChartData*/
    public Stock(Type type, double hotValue) {
        this.type = type;
        this.hotValue = hotValue;
    }


    public void setId(String id) {
        this.id = id;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public double getPd() {
        return pd;
    }
    public void setPd(double pd) {
        this.pd = pd;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getPe() {
        return pe_ttm;
    }

    public void setPe(double pe_ttm) {
        this.pe_ttm = pe_ttm;
    }

    public double getAdj_price() {
        return adj_price;
    }

    public void setAdj_price(double adj_price) {
        this.adj_price = adj_price;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getTurnover() {
        return turnover;
    }

    public void setTurnover(double turnover) {
        this.turnover = turnover;
    }

//    public String getName() {
//        return name;
//    }

    public String getType() {
        this.type = typegeter.getType(id);
        return type.toString();
    }

    public void setType(Type type) {
        this.type = type;
    }

    public double getDealSum() {
        return this.dealsum;
    }

    public void setHotValue(double value) {
        hotValue = value;
    }

    public double getHotValue() {
        return hotValue;
    }

    public String getTypeName() {
        String name = "";
        switch (this.type) {
            case a:
                name = "农林牧渔业";
                break;
            case b:
                name = "采矿业";
                break;
            case c:
                name = "制造业";
                break;
            case d:
                name = "电力热力燃气及水生产和供应业";
                break;
            case e:
                name = "建筑业";
                break;
            case f:
                name = "批发和零售";
                break;
            case g:
                name = "交通运输仓储和邮政业";
                break;
            case h:
                name = "住宿和餐饮业";
                break;
            case i:
                name = "信息传输软件和信息技术服务业";
                break;
            case j:
                name = "金融业";
                break;
            case k:
                name = "房地产业";
                break;
            case l:
                name = "租赁和商务服务业";
                break;
            case m:
                name = "科学研究和技术服务业";
                break;
            case n:
                name = "水利环境和公共设施管理业";
                break;
            case q:
                name = "卫生和社会工作";
                break;
            case r:
                name = "文化体育和娱乐业";
                break;
        }
        return name;
    }

    public void setName(){
        GetName get = new GetName();
        this.name = get.getname(this.id);
    }

    public String getName(){
        GetName get = new GetName();
        String name = get.getname(this.id);
        return name;
    }
}
