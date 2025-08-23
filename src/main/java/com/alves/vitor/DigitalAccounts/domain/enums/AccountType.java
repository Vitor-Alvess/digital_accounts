package com.alves.vitor.DigitalAccounts.domain.enums;

public enum AccountType {
    POUPANCA('P'),
    CORRENTE('C');

    private char description;

    AccountType(char description) {
        this.description = description;
    }

    public char get() {
        return description;
    }
}
