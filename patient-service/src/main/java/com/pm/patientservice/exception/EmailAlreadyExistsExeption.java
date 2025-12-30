package com.pm.patientservice.exception;

public class EmailAlreadyExistsExeption extends RuntimeException {

    public EmailAlreadyExistsExeption(String message) {
        super(message);
    }
}
