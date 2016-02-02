package com.address.book.core;


import com.address.book.domain.Gender;
import com.address.book.domain.Person;
import com.address.book.util.DateFactory;

import java.util.Collection;
import java.util.LinkedHashSet;

public class TestUtil {

    private DateFactory dateFactory = new DateFactory();

    public Person person(String name, Gender gender, String dateOfBirth){
        return new Person(name, gender, dateFactory.convertStringToDate(dateOfBirth));
    }
}
