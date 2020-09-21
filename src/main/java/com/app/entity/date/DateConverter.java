package com.app.entity.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateConverter {

    public Calendar convertFromStringToCalendar(String stringDate) throws ParseException {
        System.out.println(stringDate);
        DateFormat df = new SimpleDateFormat("yyyy-dd-MM");
        Date date = df.parse(stringDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

}
