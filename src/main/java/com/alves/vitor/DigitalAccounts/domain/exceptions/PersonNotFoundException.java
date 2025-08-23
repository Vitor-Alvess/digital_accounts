package com.alves.vitor.DigitalAccounts.domain.exceptions;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException() {
        super("Pessoa não encontrada");
    }
}
