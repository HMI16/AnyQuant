package com.bigwork.bl.managementServiceImpl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by asus on 2016/5/28.
 */
public class CalcuDate {
    public String calDate(String date, int gap){
        String result = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dateTo = sdf.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateTo);
            calendar.add(Calendar.DAY_OF_MONTH, gap);
            result = sdf.format(calendar.getTime());
            System.out.println(result);
        }catch (ParseException e){
            System.out.println("time-format-error in ATR-Impl");
            //though if set properly it won't happen
        }
        return result;
    }

    public int getDiff(String date1, String date2){
        long result = 0;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date from = df.parse(date1);
            Date to = df.parse(date2);
            result = to.getTime() - from.getTime();
            result /= (1000*60*60*24);
        }catch (ParseException e){
            System.out.println("Parse-exception in manaImpl.CalcuDate");
        }

        return (int)result;
    }

//    public static void main(String[] args) {
//        CalcuDate c = new CalcuDate();
//        System.out.println(c.getDiff("2015-03-08", "2015-03-10"));
//    }
}
