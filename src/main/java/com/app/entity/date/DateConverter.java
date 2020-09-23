package com.app.entity.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateConverter {

    public Calendar convertFromStringToCalendar(String stringDate) throws ParseException {
        Date date = convertFromStringToDate(stringDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public Date convertFromStringToDate(String stringDate) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.parse(stringDate);
    }

    public String convertFromDateToString(Date date) throws ParseException {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(date);
    }

}
