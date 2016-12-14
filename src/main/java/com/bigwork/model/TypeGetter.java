package com.bigwork.model;

import com.bigwork.sql.FileRead;
import com.bigwork.sql.MysqlLink;
import com.bigwork.sql.SqlImplNullPointerException;
import com.bigwork.sql.SqlLinkException;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by asus on 2016/5/7.
 */
public class TypeGetter {

    private String charsetName = "UTF-8";
    private File file ;
    private MysqlLink link;

    private void in(){
        link = new MysqlLink();
        try {
            link.link("115.28.40.144", "root", "141250185");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (SqlLinkException e) {
            e.printStackTrace();
        }

        file = new File("file/IDwithType.txt");
        String[] temp;
        ArrayList<String> stringlist = new ArrayList<>();
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        try {
            if (!file.exists()) {
                System.out.println(file.getAbsolutePath() + "notExits");
            }
            InputStreamReader fr = new InputStreamReader(new FileInputStream(file), this.charsetName);
            BufferedReader br = new BufferedReader(fr);
            String record = null;
            while ((record = br.readLine()) != null) {
                temp = record.split(" ");
                stringlist.add(temp[0]);
                stringlist.add(temp[1]);
                link.getIndustrySQLImpl().insertIndustry(temp[0],temp[1]);
                result.add(stringlist);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (SqlImplNullPointerException e) {
            e.printStackTrace();
        }
        System.out.println(result.size());

    }
    private ArrayList<ArrayList<String>> getlist(){
        link = new MysqlLink();
        try {
            link.link(FileRead.read("ip"), "root", "141250185");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (SqlLinkException e) {
            e.printStackTrace();
        }
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        try {
            result = link.getIndustrySQLImpl().selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (SqlImplNullPointerException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<String> getStringwithID(String ABC){
        String s = getClass().getResource("/").toString();
        s = s.replace("target/classes/","file/IDwithType.txt");
        String t = s.replace("target/springmvcdemo/WEB-INF/classes/","file/IDwithType.txt");
        String r = t.replace("file:/","");
        r = r.replace("Users","/Users");
        //System.out.println(r);
        file = new File(r);
      //  file = new File("file/IDwithType.txt");
        String[] temp;
        ArrayList<String> stringlist = new ArrayList<>();
        try {
            if (!file.exists()) {
                System.out.println(file.getAbsolutePath() + "notExits");
            }
            InputStreamReader fr = new InputStreamReader(new FileInputStream(file), this.charsetName);
            BufferedReader br = new BufferedReader(fr);

            String record = null;
            while ((record = br.readLine()) != null) {

                temp = record.split(" ");
                // System.out.println(temp[1]);
                if(temp[1].equals(ABC)){
                    stringlist.add(temp[0]);
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringlist;
    }

    public ArrayList<String> getAllID(){
        String s = getClass().getResource("/").toString();
        s = s.replace("target/classes/","file/IDwithType.txt");
        String t = s.replace("target/springmvcdemo/WEB-INF/classes/","file/IDwithType.txt");
        String r = t.replace("file:/","");
        r = r.replace("Users","/Users");
        //System.out.println(r);
        file = new File(r);
        //  file = new File("file/IDwithType.txt");
        String[] temp;
        ArrayList<String> stringlist = new ArrayList<>();
        try {
            if (!file.exists()) {
                System.out.println(file.getAbsolutePath() + "notExits");
            }
            InputStreamReader fr = new InputStreamReader(new FileInputStream(file), this.charsetName);
            BufferedReader br = new BufferedReader(fr);
            String record = null;
            while ((record = br.readLine()) != null) {

                temp = record.split(" ");
                // System.out.println(temp[1]);
                    stringlist.add(temp[0]);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringlist;
    }

    public String getABC(String id){
        String s = getClass().getResource("/").toString();
        s = s.replace("target/classes/","file/IDwithType.txt");
        s = s.replace("target/test-classes/","file/IDwithType.txt");
        String t = s.replace("target/springmvcdemo/WEB-INF/classes/","file/IDwithType.txt");
        String r = t.replace("file:/","");
        r = r.replace("Users","/Users");
        //System.out.println(r);
        file = new File(r);
        String[] temp;
        try {
            if (!file.exists()) {
                System.out.println(file.getAbsolutePath() + "notExits");
            }
            InputStreamReader fr = new InputStreamReader(new FileInputStream(file), this.charsetName);
            BufferedReader br = new BufferedReader(fr);

            String record = null;
            while ((record = br.readLine()) != null) {

                temp = record.split(" ");
               // System.out.println(temp[1]);
                if(temp[0].equals(id)){
                    return temp[1];
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "a";
    }

    public Type getType(String id) {
        Type type = Type.a;
        String ABC = this.getABC(id);
        switch (ABC){
            case "a":
                type = Type.a;
                break;
            case "b":
                type = Type.b;
                break;
            case "c":
                type = Type.c;
                break;
            case "d":
                type = Type.d;
                break;
            case "e":
                type = Type.e;
                break;
            case "f":
                type = Type.f;
                break;
            case "g":
                type = Type.g;
                break;
            case "h":
                type = Type.h;
                break;
            case "i":
                type = Type.i;
                break;
            case "j":
                type = Type.j;
                break;
            case "k":
                type = Type.k;
                break;
            case "l":
                type = Type.l;
                break;
            case "m":
                type = Type.m;
                break;
            case "n":
                type = Type.n;
                break;
            case "q":
                type = Type.q;
                break;
            case "r":
                type = Type.r;
                break;
        }
        return type;
    }

    public String getTypeName(String ABC) {
        String name = "";
        switch (ABC) {
            case "a":
                name = "农林牧渔业";
                break;
            case "b":
                name = "采矿业";
                break;
            case "c":
                name = "制造业";
                break;
            case "d":
                name = "电力热力燃气及水生产和供应业";
                break;
            case "e":
                name = "建筑业";
                break;
            case "f":
                name = "批发和零售";
                break;
            case "g":
                name = "交通运输仓储和邮政业";
                break;
            case "h":
                name = "住宿和餐饮业";
                break;
            case "i":
                name = "信息传输软件和信息技术服务业";
                break;
            case "j":
                name = "金融业";
                break;
            case "k":
                name = "房地产业";
                break;
            case "l":
                name = "租赁和商务服务业";
                break;
            case "m":
                name = "科学研究和技术服务业";
                break;
            case "n":
                name = "水利环境和公共设施管理业";
                break;
            case "q":
                name = "卫生和社会工作";
                break;
            case "r":
                name = "文化体育和娱乐业";
                break;
        }
        return name;
    }

    public String getABCFromtype(String type) {
        String name = "";
        switch (type) {
            case "农林牧渔业":
                name = "a";
                break;
            case "采矿业":
                name = "b";
                break;
            case "制造业":
                name = "c";
                break;
            case "电力热力燃气及水生产和供应业":
                name = "d";
                break;
            case "建筑业":
                name = "e";
                break;
            case "批发和零售":
                name = "f";
                break;
            case "交通运输仓储和邮政业":
                name = "g";
                break;
            case "住宿和餐饮业":
                name = "h";
                break;
            case "信息传输软件和信息技术服务业":
                name = "i";
                break;
            case "金融业":
                name = "j";
                break;
            case "房地产业":
                name = "k";
                break;
            case "租赁和商务服务业":
                name = "l";
                break;
            case "科学研究和技术服务业":
                name = "m";
                break;
            case "水利环境和公共设施管理业":
                name = "n";
                break;
            case "卫生和社会工作":
                name = "q";
                break;
            case "文化体育和娱乐业":
                name = "r";
                break;
        }
        return name;
    }

    public static void main(String args[]){
//        Stock stock= new Stock("sh600000",3,1.0,2.0,3.0,4.0,5.0,"2010-01-01",6.0,7.0,8.0);
//        System.out.println(stock.getTypeName());
        TypeGetter typeGetter = new TypeGetter();
        System.out.println(typeGetter.getAllID());
//        System.out.println(typeGetter.getABCFromtype("采矿业"));
        //    System.out.println(typeGetter.getlist());

    }
}
