package com.zowie.appmanagementservice.util;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {

    public static String getQuarter(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int quarter = (calendar.get(Calendar.MONTH) / 3) + 1;
        switch (quarter){
            case 2 : return calendar.get(Calendar.YEAR) + "Q2";
            case 3 : return calendar.get(Calendar.YEAR) + "Q3";
            case 4 : return calendar.get(Calendar.YEAR) + "Q4";
            default: return calendar.get(Calendar.YEAR) + "Q1";
        }
    }

    public static String getPreviousQuarter(String quarter){
        if(quarter == null || quarter.isEmpty()){
            return getQuarter(new Date(Instant.now().toEpochMilli()));
        }
        String[] qSplit = quarter.split("Q");
        if(qSplit == null || qSplit.length != 2){
            return getQuarter(new Date(Instant.now().toEpochMilli()));
        }
        if(Integer.parseInt(qSplit[1]) <= 1){
            return (Integer.parseInt(qSplit[0]) - 1) + "Q4";
        }
        else {
            return qSplit[0] + "Q" + (Integer.parseInt(qSplit[1]) - 1);
        }
    }
}
