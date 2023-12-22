package com.academy.sirma.bff.employees.services.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class DateParser {

    public static final String DATE_FORMAT_STRING = "yyyy-MM-dd";

    private static final List<String> SUPPORTED_DATE_FORMATS = Arrays.asList(
            DATE_FORMAT_STRING,
            "yyyy.MM.dd",
            "yyyyMMdd",
            "yyyy/MM/dd",

            "dd-MMM-yyyy",
            "dd-MM-yyyy",
            "dd/MM/yyyy",
            "dd/MM/yy",

            "MM-dd-yyyy",
            "MM/dd/yyyy",
            "MM/dd/yy",
            "M/d/yy"

            //Add more date formats if needed.
    );



    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT_STRING);


    public static LocalDate parseLocalDate(String dateStr) {
        DateTimeFormatter formatter = getDateTimeFormatter(dateStr);
        return LocalDate.parse(dateStr, formatter);
    }

    public static String formatLocalDateAsString(LocalDate date) {
        return date.format(formatter);
    }

    private static DateTimeFormatter getDateTimeFormatter(String dateStr) {
        for (String format : SUPPORTED_DATE_FORMATS) {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
                simpleDateFormat.setLenient(false);
                simpleDateFormat.parse(dateStr);
                return DateTimeFormatter.ofPattern(format);
            } catch (ParseException ignored) {
                // Ignore the exception because we want to try the next format
            }
        }

        throw new IllegalArgumentException("Unsupported date format for the following date: " + dateStr);
    }
}
