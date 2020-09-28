package com.app.converter;

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

    public Date convertFromStringToDate(String date) throws ParseException {
        String result = removeDifferences(date);
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        return df.parse(result);
    }

    public String convertFromDateToString(Date date) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(date);
    }

    private String removeDifferences(String date) {
        if (date.contains("/") || date.contains("-")) {
            date = date.replace("/", ".");
            date = date.replace("-", ".");
        }
        return date;
    }

}
