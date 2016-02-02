package com.address.book.core;

import com.address.book.domain.AddressBook;
import com.address.book.domain.Person;
import org.junit.Before;
import org.junit.Test;

import static java.util.Collections.emptySet;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AddressBookDataAnalyserTest {

    private AddressBookDataAnalyser dataAnalyser;
    private TestUtil util = new TestUtil();

    @Before
    public void setUpDataAnalyser(){
        dataAnalyser = new AddressBookDataAnalyser(util.addressBook());
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullAddressBook(){
        new AddressBookDataAnalyser(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addressBookWithNoEntries(){
        new AddressBookDataAnalyser(emptyAddressBook());
    }

    private AddressBook emptyAddressBook() {
        return new AddressBook(emptySet());
    }

    @Test
    public void howManyPeopleInTheListAreMale(){
        assertThat("Male people in the list", dataAnalyser.totalNumberOfMales(), is(3L));
    }

    @Test
    public void oldestPersonInTheList(){
        Person oldest = dataAnalyser.findOldestPerson();
        assertThat("oldest person in the list", oldest.getName(), is("Wes Jackson"));
    }

    @Test
    public void ageDifferenceBetweenTwoPersonsInDays(){
        assertThat("age difference in days",
                dataAnalyser.findAgeDifferenceInDaysBetween("Bill McKnight", "Paul Robinson"), is(1L));
        assertThat("age difference in days",
                dataAnalyser.findAgeDifferenceInDaysBetween("Paul Robinson", "Bill McKnight"), is(1L));
    }

    @Test(expected = PersonNotFoundException.class)
    public void attemptToFindAgeDifferenceWithNonExistentPerson(){
        dataAnalyser.findAgeDifferenceInDaysBetween("Bill McKnight", "Dummy");
    }
}