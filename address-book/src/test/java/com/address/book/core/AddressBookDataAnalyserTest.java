package com.address.book.core;

import com.address.book.domain.Person;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AddressBookDataAnalyserTest {

    private AddressBookDataAnalyser dataAnalyser;
    private TestUtil util = new TestUtil();

    @Before
    public void setUpDataAnalyser(){
        dataAnalyser = new AddressBookDataAnalyser(util.addressBook());
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
        long days = dataAnalyser.findAgeDifferenceInDaysBetween("Bill McKnight", "Paul Robinson");
        assertThat("age difference in days", days, is(2862L));
    }

    @Test(expected = PersonNotFoundException.class)
    public void attemptToFindAgeDifferenceWithNonExistentPerson(){
        dataAnalyser.findAgeDifferenceInDaysBetween("Bill McKnight", "Dummy");
    }
}