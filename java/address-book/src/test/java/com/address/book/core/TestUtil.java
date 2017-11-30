package com.address.book.core;


import com.address.book.domain.AddressBook;
import com.address.book.domain.Gender;
import com.address.book.domain.Person;
import com.address.book.util.DateFactory;

import java.util.Collection;
import java.util.LinkedHashSet;

import static com.address.book.domain.Gender.FEMALE;
import static com.address.book.domain.Gender.MALE;

public class TestUtil {

    private DateFactory dateFactory = new DateFactory();

    public AddressBook addressBook(){
        Collection<Person> people = new LinkedHashSet<Person>();
        people.add(person("Bill McKnight", MALE, "14/12/97"));
        people.add(person("Paul Robinson", MALE, "15/12/97"));
        people.add(person("Gemma Lane", FEMALE, "20/11/91"));
        people.add(person("Sarah Stone", FEMALE, "20/09/80"));
        people.add(person("Wes Jackson", MALE, "14/08/74"));

        return new AddressBook(people);
    }

    public Person person(String name, Gender gender, String dateOfBirth){
        return new Person(name, gender, dateFactory.convertStringToDate(dateOfBirth));
    }
}
