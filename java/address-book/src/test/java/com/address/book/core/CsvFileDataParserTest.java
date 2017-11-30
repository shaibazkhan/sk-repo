package com.address.book.core;

import com.address.book.domain.AddressBook;
import org.junit.Test;

import static com.address.book.domain.Gender.FEMALE;
import static com.address.book.domain.Gender.MALE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;

public class CsvFileDataParserTest {

    private TestUtil util = new TestUtil();

    @Test
    public void parse(){
        AddressBook addressBook = new CsvFileDataParser(file("/test-data.txt")).parse();
        assertThat(addressBook.entries(), hasSize(2));
        assertThat(addressBook.entries(), hasItem(util.person("Bill McKnight", MALE, "16/03/77")));
        assertThat(addressBook.entries(), hasItem(util.person("Sarah Stone", FEMALE, "20/09/80")));
    }

    @Test
    public void parseDuplicateRecords(){
        AddressBook addressBook = new CsvFileDataParser(file("/duplicate-data.txt")).parse();
        assertThat(addressBook.entries(), hasSize(1));
        assertThat(addressBook.entries(), hasItem(util.person("Bill McKnight", MALE, "16/03/77")));
    }

    @Test(expected = InvalidAddressBookEntryException.class)
    public void parseFailDueToInvalidDateOfBirth(){
        new CsvFileDataParser(file("/invalid-date.txt")).parse();
    }

    @Test(expected = InvalidAddressBookEntryException.class)
    public void attemptToParseWithInvalidColumns(){
        new CsvFileDataParser(file("/invalid-columns.txt")).parse();
    }

    private String file(String name) {
        return this.getClass().getResource(name).getFile();
    }
}