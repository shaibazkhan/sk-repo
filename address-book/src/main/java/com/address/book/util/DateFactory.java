package com.address.book.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateFactory {

    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");

    public LocalDate convertStringToDate(String value) throws DateTimeParseException {
        return LocalDate.parse(value, formatter);
    }
}
