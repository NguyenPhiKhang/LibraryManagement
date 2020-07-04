package com.javafx.librarian.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class Util {
    public static final String URL_JDBC = "jdbc:mysql://localhost:3306/qltv?useUnicode=yes&characterEncoding=UTF-8";
    public static final String USERNAME_JDBC = "root";
    public static final String PASSWORD_JDBC = "";

    public static String dateFormat(Date date){
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date);
    }

    public static LocalDate convertDateToLocalDate(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return LocalDate.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));
    }
}
