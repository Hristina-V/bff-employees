package com.academy.sirma.bff.employees.exceptions;

public class UnsupportedFileEncodingException extends RuntimeException {

    public UnsupportedFileEncodingException(Exception e) {
        super(e);
    }
}
