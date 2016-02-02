package com.address.book.core;

public class PersonNotFoundException extends RuntimeException {

    public PersonNotFoundException(String name) {
        super(String.format("No person could be found for the supplied name:>%s<", name));
    }
}
