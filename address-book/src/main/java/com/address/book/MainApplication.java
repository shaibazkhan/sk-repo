package com.address.book;


import com.address.book.core.CsvFileDataParser;
import com.address.book.domain.Person;

import java.util.Collection;

public class MainApplication {

    public static void main(String[] args) {
        Collection<Person> people = new CsvFileDataParser("AddressBook.txt").parse();
    }
}
