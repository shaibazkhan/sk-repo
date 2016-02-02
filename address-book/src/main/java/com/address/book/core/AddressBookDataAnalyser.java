package com.address.book.core;

import com.address.book.domain.AddressBook;
import com.address.book.domain.Person;

import static com.address.book.domain.Gender.MALE;
import static java.time.temporal.ChronoUnit.DAYS;

public class AddressBookDataAnalyser {

    private final AddressBook addressBook;

    public AddressBookDataAnalyser(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    public long totalNumberOfMales() {
        return addressBook
                .entries()
                .stream()
                .filter(person -> person.getGender() == MALE)
                .count();
    }

    public Person findOldestPerson() {
        return addressBook
                .entries()
                .stream()
                .reduce((p1, p2) -> p1.age() > p2.age() ? p1 : p2)
                .get();
    }

    public long findAgeDifferenceInDaysBetween(String firstPersonName, String secondPersonName) throws PersonNotFoundException {
        Person firstPerson = findPersonByName(firstPersonName);
        Person secondPerson = findPersonByName(secondPersonName);

        return DAYS.between(firstPerson.getDateOfBirth(), secondPerson.getDateOfBirth());
    }

    private Person findPersonByName(String name) {
        return addressBook
                 .entries().stream()
                 .filter(person -> person.getName().equals(name)).findFirst()
                 .orElseThrow(() -> new PersonNotFoundException(name));
    }
}
