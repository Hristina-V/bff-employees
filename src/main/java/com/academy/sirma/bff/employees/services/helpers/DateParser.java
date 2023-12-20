package com.academy.sirma.bff.employees.services.helpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateParser {

    public static final String DATE_FORMAT_STRING = "yyyy-MM-dd";

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT_STRING);

    public static LocalDate parseLocalDate(String dateStr) {
        return LocalDate.parse(dateStr, formatter);
    }

    public static String formatLocalDateAsString(LocalDate date) {
        return date.format(formatter);
    }
}
