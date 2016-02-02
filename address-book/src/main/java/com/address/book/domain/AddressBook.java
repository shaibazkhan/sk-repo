package com.address.book.domain;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;

public class AddressBook {

    private Collection<Person> entries;

    public AddressBook(Collection<Person> entries) {
        this.entries = Collections.unmodifiableSet(new LinkedHashSet<>(entries));
    }

    public Collection<Person> entries() {
        return entries;
    }
}
