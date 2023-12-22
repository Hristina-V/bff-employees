package com.academy.sirma.bff.employees.exceptions;

public class ParseFIleException extends RuntimeException {

    public ParseFIleException(Exception e) {
        super(e);
    }
}
