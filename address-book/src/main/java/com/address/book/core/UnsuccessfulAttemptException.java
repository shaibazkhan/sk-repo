package com.address.book.core;

public class UnsuccessfulAttemptException extends RuntimeException {
    public UnsuccessfulAttemptException(Exception e) {
        super(e);
    }
}
