package com.alves.vitor.DigitalAccounts.domain.exceptions;

public class InvalidDataException extends RuntimeException {
    public InvalidDataException() {
        super("Os dados foram preenchidos incorretamente!");
    }
}
