package com.bigwork.sql;

/**
 * Created by 15HR-1528SS on 2016/5/7.
 */
public class DateHelper {
    private static boolean isLater(String date1, String date2) {
        int[] i = formatChange(date1, date2);

        if (i[0] > i[3])
            return true;
        else if (i[0] < i[3])
            return false;

        if (i[1] > i[4])
            return true;
        else if (i[1] < i[4])
            return false;

        if (i[2] >= i[5])
            return true;

        return false;
    }

    private static boolean isEarlier(String date1, String date2) {
        int[] i = formatChange(date1, date2);

        if (i[0] < i[3])
            return true;
        else if (i[0] > i[3])
            return false;

        if (i[1] < i[4])
            return true;
        else if (i[1] > i[4])
            return false;

        if (i[2] <= i[5])
            return true;

        return false;
    }

    public static boolean isIn(String date, String from, String to) {
        if (isLater(date, from) && isEarlier(date, to))
            return true;

        return false;
    }

    private static int[] formatChange(String date1, String date2) {
        int[] i = new int[6];

        String[] str1 = date1.split("-");
        String[] str2 = date2.split("-");

        i[0] = Integer.parseInt(str1[0]);
        i[1] = Integer.parseInt(str1[1]);
        i[2] = Integer.parseInt(str1[2]);
        i[3] = Integer.parseInt(str2[0]);
        i[4] = Integer.parseInt(str2[1]);
        i[5] = Integer.parseInt(str2[2]);

        return i;
    }

    public static void main(String[] args) {
        if (DateHelper.isIn("2016-04-15", "2016-05-13", "2017-05-15"))
            System.out.println("haha?");
        else
            System.out.println("hehe?");
    }
}
