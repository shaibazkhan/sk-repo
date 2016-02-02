package com.address.book.core;

import com.address.book.domain.Gender;
import com.address.book.domain.Person;
import com.address.book.util.DateFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;

public class CsvFileDataParser {

    private final String fileSource;
    private final DateFactory dateFactory = new DateFactory();

    public CsvFileDataParser(String fileSource) {
        this.fileSource = fileSource;
    }

    /**
     * Parses CSV file of address book records, converts them into {@link Collection}
     * of {@link Person}.
     *
     * @return {@link Collection} of {@link Person}
     * @throws UnsuccessfulAttemptException if there is any {@link IOException}
     * @throws InvalidAddressBookEntryException any row in the list of records has invalid age or date.
     */
    public Collection<Person> parse() throws UnsuccessfulAttemptException, InvalidAddressBookEntryException {
        File file = new File(fileSource);
        System.out.println(file.getName() + " file exists = " + file.exists());

        Collection<Person> persons = new LinkedHashSet<>();
        String line = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while ((line = bufferedReader.readLine()) != null) {
                if(!persons.add(createPersonFrom(line))) {
                    System.out.println("Duplicate record ignored:>" + line + "<");
                }
            }
            bufferedReader.close();

        } catch (IOException e) {
            throw new UnsuccessfulAttemptException(e);
        } catch (DateTimeParseException|NumberFormatException e) {
            throw new InvalidAddressBookEntryException(line, e);
        }

        return Collections.unmodifiableCollection(persons);
    }

    private Person createPersonFrom(String line)  {
        String[] columns = line.split(",");
        if(columns.length < 3){
            throw new InvalidAddressBookEntryException(line);
        }

        return new Person(
                columns[0].trim(),
                Gender.valueOf(columns[1].toUpperCase().trim()),
                dateFactory.convertStringToDate(columns[2].trim()));
    }
}