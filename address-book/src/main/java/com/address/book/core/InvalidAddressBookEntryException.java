package com.address.book.core;

public class InvalidAddressBookEntryException extends RuntimeException {

    public InvalidAddressBookEntryException(String row, Exception e) {
        super(String.format("Error occurred:%s, at row:>%s<, ", e.getMessage(), row));
    }

    public InvalidAddressBookEntryException(String row) {
        super(String.format("Invalid row:>%s<, ", row));
    }
}
