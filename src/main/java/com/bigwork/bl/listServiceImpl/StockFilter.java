package com.bigwork.bl.listServiceImpl;


import com.bigwork.model.Stock;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StockFilter {

    private Stock low;
    private Stock high;
    private Stock curr;

    StockFilter(Stock low, Stock high, Stock curr) {
        this.low = low;
        this.high = high;
        this.curr = curr;

//		System.out.println("in class Filter "+low.getHigh());

    }

    public boolean filtData() {

        return this.adjComp()&&this.volumeComp()&&this.peComp()
                &&this.pdComp()&&this.turnoverComp();
//        try {
//            if (this.dateComp() && this.openComp() && this.highComp()
//                    && this.closeComp() && this.lowComp() && this.adjComp()
//                    && this.volumeComp() && this.peComp() && this.pdComp()
//                    && this.turnoverComp())
//                return true;
//            else
//                return false;
//        } catch (ParseException e) {
//            return false;
//        }
    }

    public boolean filtTime(Stock low, Stock high, Stock curr){
        this.low = low;
        this.high = high;
        this.curr = curr;
        try {
            return this.dateComp();
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean dateComp() throws ParseException {
//		System.out.println("filt date");
//		System.out.println("~~"+low.getDate()+"~~"+curr.getDate()+"~~"+high.getDate());

        if (low.getDate().equals("") && high.getDate().equals("")) {
//			System.out.println("in null");
            return true;
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = sdf.parse(low.getDate());
            Date endDate = sdf.parse(high.getDate());
            Date currDate = sdf.parse(curr.getDate());

//		System.out.println(startDate+" "+endDate+" "+currDate);

            if (currDate.after(startDate) && currDate.before(endDate))
                return true;
            else {
                return false;
            }
        }
    }

    private boolean openComp() {
//		System.out.println("filt open");

        BigDecimal lowOpen = new BigDecimal(low.getOpen());
        BigDecimal highOpen = new BigDecimal(high.getOpen());
        BigDecimal currOpen = new BigDecimal(curr.getOpen());
        if (low.getOpen() != -1 && high.getOpen() != -1) {
            if ((currOpen.compareTo(lowOpen) == 1 || currOpen.compareTo(lowOpen) == 0)
                    && (currOpen.compareTo(highOpen) == -1 || currOpen.compareTo(highOpen) == 0))
                return true;
            else
                return false;
        } else return true;
    }

    private boolean highComp() {

//		System.out.println("filt high");

        BigDecimal lowHigh = new BigDecimal(low.getHigh());
        BigDecimal highHigh = new BigDecimal(high.getHigh());
        BigDecimal currHigh = new BigDecimal(curr.getHigh());
        if (low.getHigh() != -1 && high.getHigh() != -1) {
            if ((currHigh.compareTo(lowHigh) == 1 || currHigh.compareTo(lowHigh) == 0)
                    && (currHigh.compareTo(highHigh) == -1 || currHigh.compareTo(highHigh) == 0))
                return true;
            else
                return false;
        } else return true;
    }

    private boolean lowComp() {
//		System.out.println("filt low");

        BigDecimal lowLow = new BigDecimal(low.getLow());
        BigDecimal highLow = new BigDecimal(high.getLow());
        BigDecimal currLow = new BigDecimal(curr.getLow());
        if (low.getLow() != -1 && high.getLow() != -1) {
            if ((currLow.compareTo(lowLow) == 1 || currLow.compareTo(lowLow) == 0)
                    && (currLow.compareTo(highLow) == -1 || currLow.compareTo(highLow) == 0))
                return true;
            else
                return false;
        } else return true;
    }

    private boolean closeComp() {
//		System.out.println("filt close");

        BigDecimal lowClose = new BigDecimal(low.getClose());
        BigDecimal highClose = new BigDecimal(high.getClose());
        BigDecimal currClose = new BigDecimal(curr.getClose());
        if (low.getClose() != -1 && high.getClose() != -1) {
            if ((currClose.compareTo(lowClose) == 1 || currClose.compareTo(lowClose) == 0)
                    && (currClose.compareTo(highClose) == -1 || currClose.compareTo(highClose) == 0))
                return true;
            else
                return false;
        } else return true;
    }

    private boolean adjComp() {
//		System.out.println("filt adj");

        BigDecimal lowAdj = new BigDecimal(low.getAdj_price());
        BigDecimal highAdj = new BigDecimal(high.getAdj_price());
        BigDecimal currAdj = new BigDecimal(curr.getAdj_price());
        if (low.getAdj_price() != -1 && high.getAdj_price() != -1) {
            if ((currAdj.compareTo(lowAdj) == 1 || currAdj.compareTo(lowAdj) == 0)
                    && (currAdj.compareTo(highAdj) == -1 || currAdj.compareTo(highAdj) == 0))
                return true;
            else{
//                System.out.println("lowADJ : " + lowAdj + "     currADJ : " + currAdj + "   highADJ : " + highAdj);
//                System.out.println("die in adjComp");
                return false;
            }

        } else return true;
    }

    private boolean volumeComp() {
        if (low.getVolume() != -1 && high.getVolume() != -1) {
            if (curr.getVolume() >= low.getVolume()
                    && curr.getVolume() <= high.getVolume())
                return true;
            else {
//                System.out.println("die in volumeComp");
                return false;
            }
        } else return true;
    }

    private boolean turnoverComp() {
        BigDecimal lowTurnover = new BigDecimal(low.getTurnover());
        BigDecimal highTurnover = new BigDecimal(high.getTurnover());
        BigDecimal currTurnover = new BigDecimal(curr.getTurnover());
        if (low.getTurnover() != -1 && high.getTurnover() != -1) {
            if ((currTurnover.compareTo(lowTurnover) == 1 || currTurnover.compareTo(lowTurnover) == 0)
                    && (currTurnover.compareTo(highTurnover) == -1 || currTurnover.compareTo(highTurnover) == 0))
                return true;
            else {
//                System.out.println("die in turnoverComp");
                return false;
            }
        } else return true;
    }

    private boolean peComp() {
        BigDecimal lowPe = new BigDecimal(low.getPe());
        BigDecimal highPe = new BigDecimal(high.getPe());
        BigDecimal currPe = new BigDecimal(curr.getPe());
        if (low.getPe() != -1 && high.getPe() != -1) {
            if ((currPe.compareTo(lowPe) == 1 || currPe.compareTo(lowPe) == 0)
                    && (currPe.compareTo(highPe) == -1 || currPe.compareTo(highPe) == 0))
                return true;
            else
                return false;
        } else return true;
    }

    private boolean pdComp() {
        BigDecimal lowPd = new BigDecimal(low.getPd());
        BigDecimal highPd = new BigDecimal(high.getPd());
        BigDecimal currPd = new BigDecimal(curr.getPd());
        if (low.getPd() != -1 && high.getPd() != -1) {
            if ((currPd.compareTo(lowPd) == 1 || currPd.compareTo(lowPd) == 0)
                    && (currPd.compareTo(highPd) == -1 || currPd.compareTo(highPd) == 0))
                return true;
            else
                return false;
        } else return true;
    }

}
